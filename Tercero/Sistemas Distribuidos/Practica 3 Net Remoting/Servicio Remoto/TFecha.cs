using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Servicio_Remoto {
    public class TFecha : MarshalByRefObject {
        private int Dia, Mes, Anyo;

        public TFecha(int Dia, int Mes, int Anyo) {
            this.Dia = Dia;
            this.Mes = Mes;
            this.Anyo = Anyo;
        }

        public void setDia(int Dia) {
            this.Dia = Dia;
        }

        public void setMes(int Mes) {
            this.Mes = Mes;
        }

        public void setAnyo(int Anyo) {
            this.Anyo = Anyo;
        }

        public int getDia() {
            return Dia;
        }

        public int getMes() {
            return Mes;
        }

        public int getAnyo() {
            return Anyo;
        }
    }
}
