package Comun;

import java.io.Serializable;

public class TFecha implements Serializable {

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
    
    @Override
    public String toString() {
        return Dia + "/" + Mes + "/" + Anyo;
    }
}
