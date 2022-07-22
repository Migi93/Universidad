using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Practica4_Servicio {
    public class TAlmacen {
        private TDatosAlmacen Datos;
        private int nAbierto; //Numero de usuarios que tienen abierto ese almacen
        private List<TProducto> Productos;

        public TAlmacen(TDatosAlmacen Datos) {
            this.Datos = Datos;
            this.nAbierto = 1;
            this.Productos = new List<TProducto>();
        }

        public TDatosAlmacen getDatos() {
            return Datos;
        }

        public List<TProducto> getProductos() {
            return Productos;
        }

        public int getNAbierto() {
            return nAbierto;
        }

        public void setNAbierto(int pNAbierto) {
            nAbierto = pNAbierto;
        }
    }
}
