package practica.pkg2.servidor;

import Comun.IGestorAlmacenes;
import Comun.TDatosAlmacen;
import Comun.TFecha;
import Comun.TProducto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class GestorAlmacenes extends UnicastRemoteObject implements Serializable, IGestorAlmacenes {

    private final Map<Integer, TAlmacen> talmacen;
    private String Path;
    private int contador;

    public GestorAlmacenes() throws RemoteException {
        super();
        talmacen = new HashMap<>();
        contador = 0;
        try {
            this.Path = (new File(".").getCanonicalPath()).split("build")[0] + "/";
        } catch (IOException ex) {
            System.out.println("Error En el constructor." + ex.getMessage());
        }
    }

    @Override
    public TDatosAlmacen DatosAlmacen(int pAlmacen) throws RemoteException {
        TDatosAlmacen tdatosal;
        if (talmacen.get(pAlmacen) == null) {
            tdatosal = null;
        } else {
            tdatosal = talmacen.get(pAlmacen).getDatos();
        }
        return tdatosal;
    }

    @Override
    public int NProductos(int pAlmacen) throws RemoteException {
        if (talmacen.get(pAlmacen) == null) {
            return -1;
        } else {
            return talmacen.get(pAlmacen).getProductos().size();
        }
    }

    @Override
    public int CrearAlmacen(String pNombre, String pDireccion, String pNomFichero) throws RemoteException {
        int existe = AbrirAlmacen(pNomFichero);
        if (existe == -1) {
            try {
                RandomAccessFile f = new RandomAccessFile(Path + pNomFichero, "rw");
                f.writeInt(0);
                f.writeUTF(pNombre);
                f.writeUTF(pDireccion);
                f.close();
                return AbrirAlmacen(pNomFichero);
            } catch (FileNotFoundException ex) {
                System.out.println("Error en CrearAlmacen. No se ha podido crear el fichero." + ex.getMessage());
                return -1;
            } catch (IOException ex) {
                System.out.println("Error en CrearAlmacen. Algo ha ocurrido al retornar el valor de la funcion de AbrirAlmacen." + ex.getMessage());
                return -1;
            }
        } else {
            return -1;
        }
    }

    @Override
    public int AbrirAlmacen(String pNomFiche) throws RemoteException {
        boolean encontrado;
        int r = -1;
        int temporal = -1;
        for (Map.Entry<Integer, TAlmacen> entry : talmacen.entrySet()) {
            Integer key = entry.getKey();
            TAlmacen value = entry.getValue();
            encontrado = value.getDatos().getFichero().equals(pNomFiche);
            if (encontrado == true) {
                r = key;
            }
        }
        if (r != -1) {
            temporal = r;
        } else {
            try {
                RandomAccessFile f = new RandomAccessFile(Path + pNomFiche, "r");
                TDatosAlmacen tdnuevo = new TDatosAlmacen();
                int cantidadp = f.readInt();
                tdnuevo.setFichero(pNomFiche);
                tdnuevo.setNombre(f.readUTF());
                tdnuevo.setDireccion(f.readUTF());
                TAlmacen nuevotalma = new TAlmacen(tdnuevo);
                for (int i = 0; i < cantidadp; i++) {
                    String codpro = f.readUTF();
                    int cantidad = f.readInt();
                    String nompro = f.readUTF();
                    float precio = f.readFloat();
                    String descripcion = f.readUTF();
                    int dia = f.readInt();
                    int mes = f.readInt();
                    int anio = f.readInt();
                    TFecha tf = new TFecha(dia, mes, anio);
                    TProducto tprod = new TProducto(codpro, nompro, descripcion, cantidad, precio, tf);
                    nuevotalma.getProductos().add(tprod);
                }
                f.close();
                talmacen.put(contador, nuevotalma);
                temporal = contador;
                contador++;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                //ex.printStackTrace();
            }
        }
        return temporal;
    }

    @Override
    public boolean GuardarAlmacen(int pAlmacen) throws RemoteException {
        if (talmacen.get(pAlmacen) == null) {
            return false;
        } else {
            try {
                Files.deleteIfExists(new File(Path + talmacen.get(pAlmacen).getDatos().getFichero()).toPath());
                RandomAccessFile f = new RandomAccessFile(Path + talmacen.get(pAlmacen).getDatos().getFichero(), "rw");
                f.writeInt(talmacen.get(pAlmacen).getProductos().size());
                f.writeUTF(talmacen.get(pAlmacen).getDatos().getNombre());
                f.writeUTF(talmacen.get(pAlmacen).getDatos().getDireccion());
                for (TProducto tprod : talmacen.get(pAlmacen).getProductos()) {
                    f.writeUTF(tprod.getCodProd());
                    f.writeInt(tprod.getCantidad());
                    f.writeUTF(tprod.getNombre());
                    f.writeFloat(tprod.getPrecio());
                    f.writeUTF(tprod.getDescripcion());
                    f.writeInt(tprod.getCaducidad().getDia());
                    f.writeInt(tprod.getCaducidad().getMes());
                    f.writeInt(tprod.getCaducidad().getAnyo());
                }
                f.close();
                return true;
            } catch (FileNotFoundException ex) {
                System.out.println("Error con el fichero en el metodo GuardarAlmacen" + ex.getMessage());
                return false;
            } catch (IOException ex) {
                System.out.println("Error con el fichero en el metodo GuardarAlmacen" + ex.getMessage());
                return false;
            }
        }
    }

    @Override
    public boolean CerrarAlmacen(int pAlmacen) throws RemoteException {
        boolean resultado;
        boolean guardado;
        if (talmacen.get(pAlmacen) == null) {
            resultado = false;
        } else {
            guardado = GuardarAlmacen(pAlmacen);
            if (guardado == false) {
                resultado = false;
            } else {
                if (talmacen.get(pAlmacen).getNAbierto() > 1) {
                    talmacen.get(pAlmacen).setNAbierto(talmacen.get(pAlmacen).getNAbierto() - 1);
                    resultado = true;
                } else if (talmacen.get(pAlmacen).getNAbierto() < 1) {
                    resultado = false;
                } else {
                    talmacen.remove(pAlmacen);
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    @Override
    public boolean AlmacenAbierto(int pAlmacen) throws RemoteException {
        if (talmacen.get(pAlmacen) == null) {
            return false;
        } else {
            return talmacen.get(pAlmacen).getNAbierto() > 0;
        }
    }

    @Override
    public int BuscaProducto(int pAlmacen, String pCodProducto) throws RemoteException {
        boolean encontrado = false;
        int posicion = -1;
        int i = 0;
        if (talmacen.get(pAlmacen) == null) {
            return posicion;
        } else {
            while (i < talmacen.get(pAlmacen).getProductos().size() && encontrado == false) {
                if (talmacen.get(pAlmacen).getProductos().get(i).getCodProd().equals(pCodProducto) == true) {
                    encontrado = true;
                    posicion = i;
                }
                i++;
            }
        }
        return posicion;
    }

    @Override
    public TProducto ObtenerProducto(int pAlmacen, int pPosProducto) throws RemoteException {
        TProducto tprod;
        if (talmacen.get(pAlmacen) == null) {
            tprod = null;
        } else {
            if (talmacen.get(pAlmacen).getProductos().get(pPosProducto) == null) {
                tprod = null;
            } else {
                tprod = talmacen.get(pAlmacen).getProductos().get(pPosProducto);
            }
        }
        return tprod;
    }

    @Override
    public boolean AnadirProducto(int pAlmacen, TProducto pProdNuevo) throws RemoteException {
        boolean aniadido;
        if (talmacen.get(pAlmacen) == null) {
            aniadido = false;
        } else {
            if (BuscaProducto(pAlmacen, pProdNuevo.getCodProd()) == -1) {
                talmacen.get(pAlmacen).getProductos().add(pProdNuevo);
                aniadido = true;
            } else {
                aniadido = false;
            }
        }
        return aniadido;
    }

    @Override
    public boolean ActualizarProducto(int pAlmacen, TProducto pProducto) throws RemoteException {
        boolean actualizado;
        int posicion;
        if (talmacen.get(pAlmacen) == null) {
            actualizado = false;
        } else {
            posicion = BuscaProducto(pAlmacen, pProducto.getCodProd());
            if (posicion != -1) {
                talmacen.get(pAlmacen).getProductos().get(posicion).setCaducidad(pProducto.getCaducidad());
                talmacen.get(pAlmacen).getProductos().get(posicion).setCantidad(pProducto.getCantidad());
                talmacen.get(pAlmacen).getProductos().get(posicion).setCodProd(pProducto.getCodProd());
                talmacen.get(pAlmacen).getProductos().get(posicion).setDescripcion(pProducto.getDescripcion());
                talmacen.get(pAlmacen).getProductos().get(posicion).setNombre(pProducto.getNombre());
                talmacen.get(pAlmacen).getProductos().get(posicion).setPrecio(pProducto.getPrecio());
                actualizado = true;
            } else {
                actualizado = false;
            }
        }
        return actualizado;
    }

    @Override
    public boolean EliminarProducto(int pAlmacen, String pCodProducto) throws RemoteException {
        boolean eliminado;
        int posicion;
        if (talmacen.get(pAlmacen) == null) {
            eliminado = false;
        } else {
            posicion = BuscaProducto(pAlmacen, pCodProducto);
            if (posicion != -1) {
                talmacen.get(pAlmacen).getProductos().remove(posicion);
                eliminado = true;
            } else {
                eliminado = false;
            }
        }
        return eliminado;
    }

}
