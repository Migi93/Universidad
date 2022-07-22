using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace ServicioRemoto {
    public class Servicio : MarshalByRefObject {
        private Dictionary<int, TAlmacen> talmacen;

        private int contador;

        public Servicio() {
            talmacen = new Dictionary<int, TAlmacen>();
            contador = 0;
        }


        public TDatosAlmacen DatosAlmacen(int pAlmacen) {
            TDatosAlmacen tdatosal;
            if (talmacen[pAlmacen] == null) {
                tdatosal = null;
            } else {
                tdatosal = talmacen[pAlmacen].getDatos();
            }
            return tdatosal;
        }


        public int NProductos(int pAlmacen) {
            if (talmacen[pAlmacen] == null) {
                return -1;
            } else {
                return talmacen[pAlmacen].getProductos().Count();
            }
        }


        public int CrearAlmacen(String pNombre, String pDireccion, String pNomFichero) {
            int existe = AbrirAlmacen(pNomFichero);
            if (existe == -1) {
                try {
                    int i = 0;
                    using (BinaryWriter writer = new BinaryWriter(File.Open(pNomFichero, FileMode.Create))) {
                        writer.Write(i);
                        writer.Write(pNombre);
                        writer.Write(pDireccion);
                    }
                    return AbrirAlmacen(pNomFichero);
                } catch (FileNotFoundException ex) {
                    Console.WriteLine("Error en CrearAlmacen. No se ha podido crear el fichero." + ex.Message);
                    return -1;
                } catch (IOException ex) {
                    Console.WriteLine("Error en CrearAlmacen. Algo ha ocurrido al retornar el valor de la funcion de AbrirAlmacen." + ex.Message);
                    return -1;
                }
            } else {
                return -1;
            }
        }

        public int AbrirAlmacen(String pNomFiche) {
            bool encontrado;
            int r = -1;
            int temporal = -1;
            foreach (KeyValuePair<int, TAlmacen> entry in talmacen) {
                int key = entry.Key;
                TAlmacen value = entry.Value;
                encontrado = value.getDatos().getFichero().Equals(pNomFiche);
                if (encontrado == true) {
                    r = key;
                }
            }
            if (r != -1) {
                temporal = r;
            } else {
                try {
                    using (BinaryReader Reader = new BinaryReader(File.Open(pNomFiche, FileMode.Open))) {
                        TDatosAlmacen tdnuevo = new TDatosAlmacen();
                        int cantidadp = Reader.ReadInt32();
                        tdnuevo.setFichero(pNomFiche);
                        tdnuevo.setNombre(Reader.ReadString());
                        tdnuevo.setDireccion(Reader.ReadString());
                        TAlmacen nuevotalma = new TAlmacen(tdnuevo);
                        for (int i = 0; i < cantidadp; i++) {
                            String codpro = Reader.ReadString();
                            int cantidad = Reader.ReadInt32();
                            String nompro = Reader.ReadString();
                            float precio = Reader.ReadSingle();
                            String descripcion = Reader.ReadString();
                            int dia = Reader.ReadInt32();
                            int mes = Reader.ReadInt32();
                            int anio = Reader.ReadInt32();
                            TFecha tf = new TFecha(dia, mes, anio);
                            TProducto tprod = new TProducto(codpro, nompro, descripcion, cantidad, precio, tf);
                            nuevotalma.getProductos().Add(tprod);
                        }
                        talmacen[contador] = nuevotalma;
                    }
                    temporal = contador;
                    contador++;
                } catch (IOException ex) {
                    Console.WriteLine(ex.Message);
                }
            }
            return temporal;
        }


        public bool GuardarAlmacen(int pAlmacen) {
            if (talmacen[pAlmacen] == null) {
                return false;
            } else {
                try {
                    using (BinaryWriter writer = new BinaryWriter(File.Open(talmacen[pAlmacen].getDatos().getFichero(), FileMode.Create))) {
                        writer.Write(talmacen[pAlmacen].getProductos().Count());
                        writer.Write(talmacen[pAlmacen].getDatos().getNombre());
                        writer.Write(talmacen[pAlmacen].getDatos().getDireccion());
                        foreach (TProducto tprod in talmacen[pAlmacen].getProductos()) {
                            writer.Write(tprod.getCodProd());
                            writer.Write(tprod.getCantidad());
                            writer.Write(tprod.getNombre());
                            writer.Write(tprod.getPrecio());
                            writer.Write(tprod.getDescripcion());
                            writer.Write(tprod.getCaducidad().getDia());
                            writer.Write(tprod.getCaducidad().getMes());
                            writer.Write(tprod.getCaducidad().getAnyo());
                        }
                    }
                    return true;
                } catch (FileNotFoundException ex) {
                    Console.WriteLine("Error con el fichero en el metodo GuardarAlmacen" + ex.Message);
                    return false;
                } catch (IOException ex) {
                    Console.WriteLine("Error con el fichero en el metodo GuardarAlmacen" + ex.Message);
                    return false;
                }
            }
        }


        public bool CerrarAlmacen(int pAlmacen) {
            bool resultado;
            bool guardado;
            if (talmacen[pAlmacen] == null) {
                resultado = false;
            } else {
                guardado = GuardarAlmacen(pAlmacen);
                if (guardado == false) {
                    resultado = false;
                } else {
                    if (talmacen[pAlmacen].getNAbierto() > 1) {
                        talmacen[pAlmacen].setNAbierto(talmacen[pAlmacen].getNAbierto() - 1);
                        resultado = true;
                    } else if (talmacen[pAlmacen].getNAbierto() < 1) {
                        resultado = false;
                    } else {
                        talmacen.Remove(pAlmacen);
                        resultado = true;
                    }
                }
            }
            return resultado;
        }


        public bool AlmacenAbierto(int pAlmacen) {
            if (talmacen[pAlmacen] == null) {
                return false;
            } else {
                return talmacen[pAlmacen].getNAbierto() > 0;
            }
        }


        public int BuscaProducto(int pAlmacen, String pCodProducto) {
            bool encontrado = false;
            int posicion = -1;
            int i = 0;
            if (talmacen[pAlmacen] == null) {
                return posicion;
            } else {
                while (i < talmacen[pAlmacen].getProductos().Count() && encontrado == false) {
                    if (talmacen[pAlmacen].getProductos()[i].getCodProd().Equals(pCodProducto) == true) {
                        encontrado = true;
                        posicion = i;
                    }
                    i++;
                }
            }
            return posicion;
        }


        public TProducto ObtenerProducto(int pAlmacen, int pPosProducto) {
            TProducto tprod;
            if (talmacen[pAlmacen] == null) {
                tprod = null;
            } else {
                if (talmacen[pAlmacen].getProductos()[pPosProducto] == null) {
                    tprod = null;
                } else {
                    tprod = talmacen[pAlmacen].getProductos()[pPosProducto];
                }
            }
            return tprod;
        }


        public bool AnadirProducto(int pAlmacen, TProducto pProdNuevo) {
            bool aniadido;
            if (talmacen[pAlmacen] == null) {
                aniadido = false;
            } else {
                if (BuscaProducto(pAlmacen, pProdNuevo.getCodProd()) == -1) {
                    talmacen[pAlmacen].getProductos().Add(pProdNuevo);
                    aniadido = true;
                } else {
                    aniadido = false;
                }
            }
            return aniadido;
        }


        public bool ActualizarProducto(int pAlmacen, TProducto pProducto) {
            bool actualizado;
            int posicion;
            if (talmacen[pAlmacen] == null) {
                actualizado = false;
            } else {
                posicion = BuscaProducto(pAlmacen, pProducto.getCodProd());
                if (posicion != -1) {
                    talmacen[pAlmacen].getProductos()[posicion].setCaducidad(pProducto.getCaducidad());
                    talmacen[pAlmacen].getProductos()[posicion].setCantidad(pProducto.getCantidad());
                    talmacen[pAlmacen].getProductos()[posicion].setCodProd(pProducto.getCodProd());
                    talmacen[pAlmacen].getProductos()[posicion].setDescripcion(pProducto.getDescripcion());
                    talmacen[pAlmacen].getProductos()[posicion].setNombre(pProducto.getNombre());
                    talmacen[pAlmacen].getProductos()[posicion].setPrecio(pProducto.getPrecio());
                    actualizado = true;
                } else {
                    actualizado = false;
                }
            }
            return actualizado;
        }


        public bool EliminarProducto(int pAlmacen, String pCodProducto) {
            bool eliminado;
            int posicion;
            if (talmacen[pAlmacen] == null) {
                eliminado = false;
            } else {
                posicion = BuscaProducto(pAlmacen, pCodProducto);
                if (posicion != -1) {
                    talmacen[pAlmacen].getProductos().RemoveAt(posicion);//El removeAt elimina un elemento en una posicion en una lista, el Remove eliminar el elemento
                    eliminado = true;
                } else {
                    eliminado = false;
                }
            }
            return eliminado;
        }
    }
}
