using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ServicioRemoto {
    public class TProducto : MarshalByRefObject {
        private String CodProd, Nombre, Descripcion;
        private int Cantidad;
        private float Precio;
        private TFecha Caducidad;

        public TProducto(String CodProd, String Nombre, String Descripcion, int Cantidad, float Precio, TFecha Caducidad) {
            this.CodProd = CodProd;
            this.Nombre = Nombre;
            this.Descripcion = Descripcion;
            this.Cantidad = Cantidad;
            this.Precio = Precio;
            this.Caducidad = Caducidad;
        }

        public void setCodProd(String CodProd) {
            this.CodProd = CodProd;
        }

        public void setNombre(String Nombre) {
            this.Nombre = Nombre;
        }

        public void setDescripcion(String Descripcion) {
            this.Descripcion = Descripcion;
        }

        public void setCantidad(int Cantidad) {
            this.Cantidad = Cantidad;
        }

        public void setPrecio(float Precio) {
            this.Precio = Precio;
        }

        public void setCaducidad(TFecha Caducidad) {
            this.Caducidad = Caducidad;
        }

        public String getCodProd() {
            return CodProd;
        }

        public String getNombre() {
            return Nombre;
        }

        public String getDescripcion() {
            return Descripcion;
        }

        public int getCantidad() {
            return Cantidad;
        }

        public float getPrecio() {
            return Precio;
        }

        public TFecha getCaducidad() {
            return Caducidad;
        }
    }
}
