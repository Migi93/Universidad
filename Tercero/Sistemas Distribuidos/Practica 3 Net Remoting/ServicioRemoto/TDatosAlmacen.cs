using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ServicioRemoto {
    public class TDatosAlmacen : MarshalByRefObject {
        private String Nombre, Direccion, Fichero;

        public TDatosAlmacen() {

        }

        public String getNombre() {
            return Nombre;
        }

        public String getDireccion() {
            return Direccion;
        }

        public String getFichero() {
            return Fichero;
        }

        public void setNombre(String Nombre) {
            this.Nombre = Nombre;
        }

        public void setDireccion(String Direccion) {
            this.Direccion = Direccion;
        }

        public void setFichero(String Fichero) {
            this.Fichero = Fichero;
        }

        public TDatosAlmacen(String Nombre, String Direccion, String Fichero) {
            this.Nombre = Nombre;
            this.Direccion = Direccion;
            this.Fichero = Fichero;
        }
    }
}
