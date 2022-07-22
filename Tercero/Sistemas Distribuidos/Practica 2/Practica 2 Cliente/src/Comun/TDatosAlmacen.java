package Comun;

import java.io.Serializable;

public class TDatosAlmacen implements Serializable {

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
