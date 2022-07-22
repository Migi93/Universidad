package practica.pkg2.servidor;

import Comun.TDatosAlmacen;
import Comun.TProducto;
import java.util.ArrayList;

public class TAlmacen {

        private final TDatosAlmacen Datos;
        private int nAbierto; //Numero de usuarios que tienen abierto ese almacen
        private final ArrayList<TProducto> Productos;

        public TAlmacen(TDatosAlmacen Datos) {
            this.Datos = Datos;
            this.nAbierto = 1;
            this.Productos = new ArrayList<>();
        }

        public TDatosAlmacen getDatos() {
            return Datos;
        }

        public ArrayList<TProducto> getProductos() {
            return Productos;
        }

        public int getNAbierto() {
            return nAbierto;
        }

        public void setNAbierto(int pNAbierto) {
            nAbierto = pNAbierto;
        }
    
}
