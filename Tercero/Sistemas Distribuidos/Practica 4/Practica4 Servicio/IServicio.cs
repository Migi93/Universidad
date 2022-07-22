using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace Practica4_Servicio {
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de interfaz "IService1" en el código y en el archivo de configuración a la vez.
    [ServiceContract]
    public interface IServicio {

        /*Dado una posición del almacén, devuelve sus datos */
        [OperationContract]
        TDatosAlmacen DatosAlmacen(int pAlmacen);

        /*Dado una posición de almacén, devuelve el número de productos o -1 si no existe.*/
        [OperationContract]
        int NProductos(int pAlmacen);

        /*Crea un almacén vacío de productos y devuelve la posición donde se encuentra. Si
       previamente ya estaba creado y cargado en memoria, devolverá la posición donde se
       encuentra en memoria. */
        [OperationContract]
        int CrearAlmacen(String pNombre, String pDireccion, String pNomFichero);

        /*Abre un fichero de almacén y lo carga en memoria y devuelve su posición. Si previamente
        ya estaba cargado devuelve la posición donde se encuentra.*/
        [OperationContract]
        int AbrirAlmacen(String pNomFichero);

        /*Dado la posición de un almacén vuelca sus datos al fichero. Devuelve true si ha podido
       guardarlo.*/
        [OperationContract]
        bool GuardarAlmacen(int pAlmacen);

        /*Dado la posición de un almacén, vuelca sus datos al fichero y elimina la memoria
       dinámica asociada. Si el Almacén es compartido, solo el último cliente es el que
       eliminará la memoria dinámica.*/
        [OperationContract]
        bool CerrarAlmacen(int pAlmacen);

        /*Dado la posición del almacén devuelve true si el almacén está cargado en memoria.*/
        [OperationContract]
        bool AlmacenAbierto(int pAlmacen);

        /*Dado la posición del almacén y un código de producto, devuelve la posición dentro del
       vector de dinámico donde se encuentra el producto. Si no lo encuentra devuelve -1.*/
        [OperationContract]
        int BuscaProducto(int pAlmacen, String pCodProducto);

        /*Dado la posición del almacén y la del producto, lo devuelve.*/
        [OperationContract]
        TProducto ObtenerProducto(int pAlmacen, int pPosProducto);

        /*Dado la posición de un almacén y un producto lo añadirá a la memoria dinámica del
       almacén y devuelve true. No puede existir dos productos con el mismo código en el
       almacén.*/
        [OperationContract]
        bool AnadirProducto(int pAlmacen, TProducto pProdNuevo);

        /*Dado la posición de un almacén y un producto lo actualizará en la memoria dinámica del
       almacén.*/
        [OperationContract]
        bool ActualizarProducto(int pAlmacen, TProducto pProducto);

        /*Dado la posición del almacén y un código de producto, lo elimina de la memoria dinámica
       y devuelve true.*/
        [OperationContract]
        bool EliminarProducto(int pAlmacen, String pCodProducto);
    }

    // Utilice un contrato de datos, como se ilustra en el ejemplo siguiente, para agregar tipos compuestos a las operaciones de servicio.
    // Puede agregar archivos XSD al proyecto. Después de compilar el proyecto, puede usar directamente los tipos de datos definidos aquí, con el espacio de nombres "Practica4_Servicio.ContractType".
        [DataContract]
        public class TDatosAlmacen {
            string nombre, direccion, fichero;

            [DataMember]
            public string Nombre {
                get { return nombre; }
                set { nombre = value; }
            }

            [DataMember]
            public string Direccion {
                get { return direccion; }
                set { direccion = value; }
            }

            [DataMember]
            public string Fichero {
                get { return fichero; }
                set { fichero = value; }
            }
        }

        [DataContract]
        public class TFecha {
            int dia, mes, anyo;

        [DataMember]
            public int Dia {
                get { return dia; }
                set { dia = value; }
            }

            [DataMember]
            public int Mes {
                get { return mes; }
                set { mes = value; }
            }

            [DataMember]
            public int Anyo {
                get { return anyo; }
                set { anyo = value; }
            }
        }

            [DataContract]
            public class TProducto {
            String codProd, nombre, descripcion;
             int cantidad;
             float precio;
             TFecha caducidad;

                [DataMember]
                public String CodProd {
                    get { return codProd; }
                    set { codProd = value; }
                }

                [DataMember]
                public String Nombre {
                    get { return nombre; }
                    set { nombre = value; }
                }

                [DataMember]
                public String Descripcion {
                    get { return descripcion; }
                    set { descripcion = value; }
                }

                [DataMember]
                public int Cantidad {
                    get { return cantidad; }
                    set { cantidad = value; }
                }
                [DataMember]
                public float Precio {
                    get { return precio; }
                    set { precio = value; }
                }

                [DataMember]
                public TFecha Caducidad {
                    get { return caducidad; }
                    set { caducidad = value; }
                }
    }
}
