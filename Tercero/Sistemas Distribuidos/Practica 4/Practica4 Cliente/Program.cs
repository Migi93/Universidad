using Practica4_Cliente.ServiceGAlmacenesClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Practica4_Cliente {
    class Program {
        static void Main(string[] args) {
            ServicioClient ig = new ServicioClient();

            bool resultado;
            int posicion;
            int opcion;
            int almacenabierto = -1;
            String NomAlmacen = "Ninguno";
            do {
                Console.WriteLine("----- Menú Almacenes ----- " + NomAlmacen + "\n"
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
                Console.WriteLine("Eliga una opcion: ");
                opcion = Int32.Parse(Console.ReadLine());
                switch (opcion) {
                    case 1:
                        if (almacenabierto != -1) {
                            ig.CerrarAlmacen(almacenabierto);
                            almacenabierto = -1;
                        }
                        Console.WriteLine("Nombre: ");
                        String nom = Console.ReadLine();
                        Console.WriteLine("Direccion: ");
                        String dir = Console.ReadLine();
                        Console.WriteLine("Nombre del fichero:");
                        String nomf = Console.ReadLine();
                        almacenabierto = ig.CrearAlmacen(nom, dir, nomf);
                        if (almacenabierto != -1) {
                            NomAlmacen = nom;
                            Console.WriteLine("\n***Almacen creado correctamente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            Console.WriteLine("\n***Error al crear el almacen.***\n");
                            Thread.Sleep(2500);
                        }
                        break;
                    case 2:
                        if (almacenabierto != -1) {
                            ig.CerrarAlmacen(almacenabierto);
                            almacenabierto = -1;
                        }
                        Console.WriteLine("Nombre del fichero:");
                        String nmff = Console.ReadLine();
                        almacenabierto = ig.AbrirAlmacen(nmff);
                        if (almacenabierto != -1) {
                            NomAlmacen = ig.DatosAlmacen(almacenabierto).Nombre;
                            Console.WriteLine("\n***Almacen abierto correctamente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            Console.WriteLine("\n***Error al abrir el almacen.***\n");
                            Thread.Sleep(2500);
                        }
                        break;
                    case 3:
                        if (almacenabierto != -1) {
                            ig.CerrarAlmacen(almacenabierto);
                            almacenabierto = -1;
                            NomAlmacen = "Ninguno";
                            Console.WriteLine("\n***Almacen cerrado correctamente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            Console.WriteLine("\n***Error al cerrar el almacen.***\n");
                            Thread.Sleep(2500);
                        }
                        break;
                    case 4:
                        if (almacenabierto == -1) {
                            Console.WriteLine("\n***No hay ningun almacen abierto actualmente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            resultado = ig.GuardarAlmacen(almacenabierto);
                            if (resultado == true) {
                                Console.WriteLine("\n***Almacen guardado correctamente.***\n");
                                Thread.Sleep(2500);
                            } else {
                                Console.WriteLine("\n***Error al guardar el almacen.***\n");
                                Thread.Sleep(2500);
                            }
                        }
                        break;
                    case 5:
                        if (almacenabierto == -1) {
                            Console.WriteLine("\n***No hay ningun almacen abierto actualmente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            if (ig.NProductos(almacenabierto) < 1) {
                                Console.WriteLine("\n***El almacen no contiene productos actualmente.***\n");
                                Thread.Sleep(2500);
                            } else {
                                Console.WriteLine("\nListado del almacen " + ig.DatosAlmacen(almacenabierto).Nombre + " localizado en " + ig.DatosAlmacen(almacenabierto).Direccion);
                                Console.WriteLine("    CODIGO          NOMBRE           PRECIO     CANTIDAD FECHA DE CADUCIDAD");
                                for (int i = 0; i < ig.NProductos(almacenabierto); i++) {
                                    Console.WriteLine(String.Format("{0,10} {1,25} {2,5} {3,10} {4,2}/{5,2}/{6,4}", ig.ObtenerProducto(almacenabierto, i).CodProd, ig.ObtenerProducto(almacenabierto, i).Nombre,
                                            ig.ObtenerProducto(almacenabierto, i).Precio, ig.ObtenerProducto(almacenabierto, i).Cantidad, ig.ObtenerProducto(almacenabierto, i).Caducidad.Dia,
                                            ig.ObtenerProducto(almacenabierto, i).Caducidad.Mes, ig.ObtenerProducto(almacenabierto, i).Caducidad.Anyo));
                                }
                                Console.WriteLine("\n");
                            }
                        }
                        break;
                    case 6:
                        if (almacenabierto == -1) {
                            Console.WriteLine("\n***No hay ningun almacen abierto actualmente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            Console.WriteLine("Codigo del producto: ");
                            String codpro = Console.ReadLine();
                            posicion = ig.BuscaProducto(almacenabierto, codpro);
                            if (posicion != -1) {
                                Console.WriteLine("\n***El codigo de producto ya existe actualmente en este almacen.***\n");
                                Thread.Sleep(2500);
                            } else {
                                Console.WriteLine("Nombre del producto: ");
                                String nomprod = Console.ReadLine();
                                Console.WriteLine("Descripcion del producto: ");
                                String descripcion = Console.ReadLine();
                                Console.WriteLine("Cantidad del producto: ");
                                int cantidad = Int32.Parse(Console.ReadLine());
                                Console.WriteLine("Precio del producto: ");
                                float precio = Single.Parse(Console.ReadLine());
                                Console.WriteLine("Fecha de caducidad (Día): ");
                                int dia = Int32.Parse(Console.ReadLine());
                                Console.WriteLine("Fecha de caducidad (Mes): ");
                                int mes = Int32.Parse(Console.ReadLine());
                                Console.WriteLine("Fecha de caducidad (Año): ");
                                int anyo = Int32.Parse(Console.ReadLine());
                                TFecha tf = new TFecha{Dia = dia, Mes = mes, Anyo = anyo};
                                TProducto tpro = new TProducto { CodProd = codpro,Nombre = nomprod, Descripcion = descripcion, Cantidad = cantidad, Precio = precio, Caducidad = tf };
                                resultado = ig.AnadirProducto(almacenabierto, tpro);
                                if (resultado == true) {
                                    Console.WriteLine("\n***El producto ha sido añadido correctamente.***\n");
                                    Thread.Sleep(2500);
                                } else {
                                    Console.WriteLine("\n***El producto no ha podido ser anñadido correctamente.***\n");
                                    Thread.Sleep(2500);
                                }
                            }
                        }
                        break;
                    case 7:
                        if (almacenabierto == -1) {
                            Console.WriteLine("\n***No hay ningun almacen abierto actualmente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            Console.WriteLine("Codigo del producto: ");
                            String codprod = Console.ReadLine();
                            posicion = ig.BuscaProducto(almacenabierto, codprod);
                            if (posicion == -1) {
                                Console.WriteLine("\n***El producto no ha sido encontrado en este almacen.***\n");
                                Thread.Sleep(2500);
                            } else {
                                TProducto tpro = ig.ObtenerProducto(almacenabierto, posicion);
                                if (tpro == null) {
                                    Console.WriteLine("\n***Error al obtener el producto.***\n");
                                    Thread.Sleep(2500);
                                } else {
                                    int resp;
                                    float precio;
                                    String modifica;
                                    String respuesta;
                                    Console.WriteLine("¿Desea modificar el nombre? s/n");
                                    respuesta = Console.ReadLine();
                                    if (respuesta.Equals("s")) {
                                        Console.WriteLine("Nuevo nombre: ");
                                        modifica = Console.ReadLine();
                                        tpro.Nombre = modifica;
                                    }
                                    Console.WriteLine("¿Desea modificar la descripcion? s/n");
                                    respuesta = Console.ReadLine();
                                    if (respuesta.Equals("s")) {
                                        Console.WriteLine("Nueva descripcion: ");
                                        modifica = Console.ReadLine();
                                        tpro.Descripcion = modifica;
                                    }
                                    Console.WriteLine("¿Desea modificar la cantidad? s/n");
                                    respuesta = Console.ReadLine();
                                    if (respuesta.Equals("s")) {
                                        Console.WriteLine("Nueva cantidad: ");
                                        resp = Int32.Parse(Console.ReadLine());
                                        tpro.Cantidad = resp;
                                    }
                                    Console.WriteLine("¿Desea modificar el precio? s/n");
                                    respuesta = Console.ReadLine();
                                    if (respuesta.Equals("s")) {
                                        Console.WriteLine("Nueva precio: ");
                                        precio = Single.Parse(Console.ReadLine());
                                        tpro.Precio = precio;
                                    }
                                    Console.WriteLine("¿Desea modificar la fecha? s/n");
                                    respuesta = Console.ReadLine();
                                    if (respuesta.Equals("s")) {
                                        Console.WriteLine("Nueva caducidad (Dia): ");
                                        int dia = Int32.Parse(Console.ReadLine());
                                        Console.WriteLine("Nueva caducidad (Mes): ");
                                        int mes = Int32.Parse(Console.ReadLine());
                                        Console.WriteLine("Nueva caducidad (Año): ");
                                        int anyo = Int32.Parse(Console.ReadLine());
                                        TFecha tfecha = new TFecha { Dia = dia, Mes = mes, Anyo = anyo };
                                        tpro.Caducidad = tfecha;
                                    }
                                    bool actualizado = ig.ActualizarProducto(almacenabierto, tpro);
                                    if (actualizado == true) {
                                        Console.WriteLine("\n***Producto actualizado correctamente.***\n");
                                        Thread.Sleep(2500);
                                    } else {
                                        Console.WriteLine("\n***Error al actualizar el producto.***\n");
                                        Thread.Sleep(2500);
                                    }
                                }
                            }
                        }
                        break;
                    case 8:
                        if (almacenabierto == -1) {
                            Console.WriteLine("\n***No hay ningun almacen abierto actualmente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            Console.WriteLine("Introduce el codigo del producto a mostrar: ");
                            String codprodm = Console.ReadLine();
                            posicion = ig.BuscaProducto(almacenabierto, codprodm);
                            if (posicion != -1) {
                                TProducto tobt;
                                tobt = ig.ObtenerProducto(almacenabierto, posicion);
                                if (tobt == null) {
                                    Console.WriteLine("\n***Erroral mostrar el producto.***\n");
                                    Thread.Sleep(2500);
                                } else {
                                    Console.WriteLine("\n-Codigo: " + tobt.CodProd + " -Producto: " + tobt.Nombre + " -Cantidad: " + tobt.Cantidad + " -Precio: " + tobt.Precio
                                            + " -Descripcion: " + tobt.Descripcion + " -Caducidad: " + tobt.Caducidad.Dia + "/" + tobt.Caducidad.Mes + "/" + tobt.Caducidad.Anyo + "\n");
                                }
                            } else {
                                Console.WriteLine("\n***Error. El producto no existe.***\n");
                                Thread.Sleep(2500);
                            }
                        }
                        break;
                    case 9:
                        if (almacenabierto == -1) {
                            Console.WriteLine("\n***No hay ningun almacen abierto actualmente.***\n");
                            Thread.Sleep(2500);
                        } else {
                            Console.WriteLine("Introduce el codigo del producto a eliminar: ");
                            String codprode = Console.ReadLine();
                            posicion = ig.BuscaProducto(almacenabierto, codprode);
                            if (posicion != -1) {
                                bool eliminado = ig.EliminarProducto(almacenabierto, codprode);
                                if (eliminado == true) {
                                    Console.WriteLine("\n***Producto eliminado correctamente.***\n");
                                    Thread.Sleep(2500);
                                } else {
                                    Console.WriteLine("\n***El producto no ha podido ser eliminado correctamente.***\n");
                                    Thread.Sleep(2500);
                                }
                            } else {
                                Console.WriteLine("\n***Error. El producto no existe.***\n");
                                Thread.Sleep(2500);
                            }
                        }
                        break;
                    default:
                        if (opcion != 0) {
                            Console.WriteLine("\n****ERROR. Eliga una opcion valida.****\n");
                            Thread.Sleep(2000);
                        }
                        break;
                }
            } while (opcion != 0);

            Console.WriteLine("Pulsa Enter para salir");
            Console.ReadLine();

            ig.Close();
        }
    }
}
