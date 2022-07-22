/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg2.cliente;

import Comun.IGestorAlmacenes;
import Comun.TFecha;
import Comun.TProducto;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author Miguel Sánchez
 */
public class Practica2Cliente {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws InterruptedException, RemoteException, NotBoundException, MalformedURLException {
        int puerto = 1999;
        String Host = "localhost";
        IGestorAlmacenes ig = (IGestorAlmacenes) Naming.lookup("rmi://" + Host + ":" + puerto + "/Almacen");
        Scanner sc = new Scanner(System.in);
        boolean resultado;
        int posicion;
        String nmf;
        int opcion;
        int almacenabierto = -1;
        String NomAlmacen = "Ninguno";
        do {
            System.out.println("----- Menú Almacenes ----- " + NomAlmacen + "\n"
                    + "1.- Crear un almacén vacío.\n"
                    + "2.- Abrir un fichero de almacén.\n"
                    + "3.- Cerrar un almacén.\n"
                    + "4.- Guardar Datos.\n"
                    + "5.- Listar productos del almacén.\n"
                    + "6.- Añadir un producto.\n"
                    + "7.- Actualizar un producto.\n"
                    + "8.- Consultar un producto.\n"
                    + "9.- Eliminar un producto.\n"
                    + "0.- Salir.\n");
            System.out.println("Eliga una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    if (almacenabierto != -1) {
                        ig.CerrarAlmacen(almacenabierto);
                        almacenabierto = -1;
                    }
                    System.out.println("Nombre: ");
                    String nom = sc.nextLine();
                    System.out.println("Direccion: ");
                    String dir = sc.nextLine();
                    System.out.println("Nombre del fichero:");
                    String nomf = sc.nextLine();
                    almacenabierto = ig.CrearAlmacen(nom, dir, nomf);
                    if (almacenabierto != -1) {
                        NomAlmacen = nom;
                        System.out.println("\n***Almacen creado correctamente.***\n");
                        Thread.sleep(2500);
                    } else {
                        System.out.println("\n***Error al crear el almacen.***\n");
                        Thread.sleep(2500);
                    }
                    break;
                case 2:
                    if (almacenabierto != -1) {
                        ig.CerrarAlmacen(almacenabierto);
                        almacenabierto = -1;
                    }
                    System.out.println("Nombre del fichero:");
                    String nmff = sc.nextLine();
                    almacenabierto = ig.AbrirAlmacen(nmff);
                    if (almacenabierto != -1) {
                        NomAlmacen = ig.DatosAlmacen(almacenabierto).getNombre();
                        System.out.println("\n***Almacen abierto correctamente.***\n");
                        Thread.sleep(2500);
                    } else {
                        System.out.println("\n***Error al abrir el almacen.***\n");
                        Thread.sleep(2500);
                    }
                    break;
                case 3:
                    if (almacenabierto != -1) {
                        ig.CerrarAlmacen(almacenabierto);
                        almacenabierto = -1;
                        NomAlmacen = "Ninguno";
                        System.out.println("\n***Almacen cerrado correctamente.***\n");
                        Thread.sleep(2500);
                    } else {
                        System.out.println("\n***Error al cerrar el almacen.***\n");
                        Thread.sleep(2500);
                    }
                    break;
                case 4:
                    if (almacenabierto == -1) {
                        System.out.println("\n***No hay ningun almacen abierto actualmente.***\n");
                        Thread.sleep(2500);
                    } else {
                        resultado = ig.GuardarAlmacen(almacenabierto);
                        if (resultado == true) {
                            System.out.println("\n***Almacen guardado correctamente.***\n");
                            Thread.sleep(2500);
                        } else {
                            System.out.println("\n***Error al guardar el almacen.***\n");
                            Thread.sleep(2500);
                        }
                    }
                    break;
                case 5:
                    if (almacenabierto == -1) {
                        System.out.println("\n***No hay ningun almacen abierto actualmente.***\n");
                        Thread.sleep(2500);
                    } else {
                        if (ig.NProductos(almacenabierto) < 1) {
                            System.out.println("\n***El almacen no contiene productos actualmente.***\n");
                            Thread.sleep(2500);
                        } else {
                            System.out.println("\nListado del almacen " + ig.DatosAlmacen(almacenabierto).getNombre() + " localizado en " + ig.DatosAlmacen(almacenabierto).getDireccion());
                            System.out.println("CODIGO   NOMBRE                       PRECIO   CANTIDAD   FECHA DE CADUCIDAD");
                            for (int i = 0; i < ig.NProductos(almacenabierto); i++) {
                                System.out.println(String.format("%-8s %-28s %-8.2f %-10d %-15s", ig.ObtenerProducto(almacenabierto, i).getCodProd(), ig.ObtenerProducto(almacenabierto, i).getNombre(),
                                        ig.ObtenerProducto(almacenabierto, i).getPrecio(), ig.ObtenerProducto(almacenabierto, i).getCantidad(), ig.ObtenerProducto(almacenabierto, i).getCaducidad().toString()));
                            }
                            System.out.println("\n");
                        }
                    }
                    break;
                case 6:
                    if (almacenabierto == -1) {
                        System.out.println("\n***No hay ningun almacen abierto actualmente.***\n");
                        Thread.sleep(2500);
                    } else {
                        System.out.println("Codigo del producto: ");
                        String codpro = sc.nextLine();
                        posicion = ig.BuscaProducto(almacenabierto, codpro);
                        if (posicion != -1) {
                            System.out.println("\n***El codigo de producto ya existe actualmente en este almacen.***\n");
                            Thread.sleep(2500);
                        } else {
                            System.out.println("Nombre del producto: ");
                            String nomprod = sc.nextLine();
                            System.out.println("Descripcion del producto: ");
                            String descripcion = sc.nextLine();
                            System.out.println("Cantidad del producto: ");
                            int cantidad = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Precio del producto: ");
                            float precio = sc.nextFloat();
                            sc.nextLine();
                            System.out.println("Fecha de caducidad (Día): ");
                            int Dia = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Fecha de caducidad (Mes): ");
                            int Mes = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Fecha de caducidad (Año): ");
                            int Anyo = sc.nextInt();
                            sc.nextLine();
                            TFecha tf = new TFecha(Dia, Mes, Anyo);
                            TProducto tpro = new TProducto(codpro, nomprod, descripcion, cantidad, precio, tf);
                            resultado = ig.AnadirProducto(almacenabierto, tpro);
                            if (resultado == true) {
                                System.out.println("\n***El producto ha sido añadido correctamente.***\n");
                                Thread.sleep(2500);
                            } else {
                                System.out.println("\n***El producto no ha podido ser anñadido correctamente.***\n");
                                Thread.sleep(2500);
                            }
                        }
                    }
                    break;
                case 7:
                    if (almacenabierto == -1) {
                        System.out.println("\n***No hay ningun almacen abierto actualmente.***\n");
                        Thread.sleep(2500);
                    } else {
                        System.out.println("Codigo del producto: ");
                        String codprod = sc.nextLine();
                        posicion = ig.BuscaProducto(almacenabierto, codprod);
                        if (posicion == -1) {
                            System.out.println("\n***El producto no ha sido encontrado en este almacen.***\n");
                            Thread.sleep(2500);
                        } else {
                            TProducto tpro = ig.ObtenerProducto(almacenabierto, posicion);
                            if (tpro == null) {
                                System.out.println("\n***Error al obtener el producto.***\n");
                                Thread.sleep(2500);
                            } else {
                                int resp;
                                float precio;
                                String modifica;
                                String respuesta;
                                System.out.println("¿Desea modificar el nombre? s/n");
                                respuesta = sc.nextLine();
                                if (respuesta.equals("s")) {
                                    System.out.println("Nuevo nombre: ");
                                    modifica = sc.nextLine();
                                    tpro.setNombre(modifica);
                                }
                                System.out.println("¿Desea modificar la descripcion? s/n");
                                respuesta = sc.nextLine();
                                if (respuesta.equals("s")) {
                                    System.out.println("Nueva descripcion: ");
                                    modifica = sc.nextLine();
                                    tpro.setDescripcion(modifica);
                                }
                                System.out.println("¿Desea modificar la cantidad? s/n");
                                respuesta = sc.nextLine();
                                if (respuesta.equals("s")) {
                                    System.out.println("Nueva cantidad: ");
                                    resp = sc.nextInt();
                                    sc.nextLine();
                                    tpro.setCantidad(resp);
                                }
                                System.out.println("¿Desea modificar el precio? s/n");
                                respuesta = sc.nextLine();
                                if (respuesta.equals("s")) {
                                    System.out.println("Nueva cantidad: ");
                                    precio = sc.nextFloat();
                                    sc.nextLine();
                                    tpro.setPrecio(precio);
                                }
                                System.out.println("¿Desea modificar la fecha? s/n");
                                respuesta = sc.nextLine();
                                if (respuesta.equals("s")) {
                                    System.out.println("Nueva caducidad (Dia): ");
                                    int dia = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Nueva caducidad (Mes): ");
                                    int mes = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Nueva caducidad (Año): ");
                                    int anyo = sc.nextInt();
                                    sc.nextLine();
                                    TFecha tfecha = new TFecha(dia, mes, anyo);
                                    tpro.setCaducidad(tfecha);
                                }
                                boolean actualizado = ig.ActualizarProducto(almacenabierto, tpro);
                                if (actualizado == true) {
                                    System.out.println("\n***Producto actualizado correctamente.***\n");
                                    Thread.sleep(2500);
                                } else {
                                    System.out.println("\n***Error al actualizar el producto.***\n");
                                    Thread.sleep(2500);
                                }
                            }
                        }
                    }
                    break;
                case 8:
                    if (almacenabierto == -1) {
                        System.out.println("\n***No hay ningun almacen abierto actualmente.***\n");
                        Thread.sleep(2500);
                    } else {
                        System.out.println("Introduce el codigo del producto a mostrar: ");
                        String codprodm = sc.nextLine();
                        posicion = ig.BuscaProducto(almacenabierto, codprodm);
                        if (posicion != -1) {
                            TProducto tobt;
                            tobt = ig.ObtenerProducto(almacenabierto, posicion);
                            if (tobt == null) {
                                System.out.println("\n***Erroral mostrar el producto.***\n");
                                Thread.sleep(2500);
                            } else {
                                System.out.println("\n-Codigo: " + tobt.getCodProd() + " -Producto: " + tobt.getNombre() + " -Cantidad: " + tobt.getCantidad() + " -Precio: " + tobt.getPrecio()
                                        + " -Descripcion: " + tobt.getDescripcion() + " -Caducidad: " + tobt.getCaducidad().getDia() + "/" + tobt.getCaducidad().getMes() + "/" + tobt.getCaducidad().getAnyo() + "\n");
                            }
                        } else {
                            System.out.println("\n***Error. El producto no existe.***\n");
                            Thread.sleep(2500);
                        }
                    }
                    break;
                case 9:
                    if (almacenabierto == -1) {
                        System.out.println("\n***No hay ningun almacen abierto actualmente.***\n");
                        Thread.sleep(2500);
                    } else {
                        System.out.println("Introduce el codigo del producto a eliminar: ");
                        String codprode = sc.nextLine();
                        posicion = ig.BuscaProducto(almacenabierto, codprode);
                        if (posicion != -1) {
                            boolean eliminado = ig.EliminarProducto(almacenabierto, codprode);
                            if (eliminado == true) {
                                System.out.println("\n***Producto eliminado correctamente.***\n");
                                Thread.sleep(2500);
                            } else {
                                System.out.println("\n***El producto no ha podido ser eliminado correctamente.***\n");
                                Thread.sleep(2500);
                            }
                        } else {
                            System.out.println("\n***Error. El producto no existe.***\n");
                            Thread.sleep(2500);
                        }
                    }
                    break;
                default:
                    if (opcion != 0) {
                        System.out.println("\n****ERROR. Eliga una opcion valida.****\n");
                        Thread.sleep(2000);
                    }
                    break;
            }
        } while (opcion != 0);
        System.out.println("\n¡¡¡HASTA PRONTO!!!");
    }
}
