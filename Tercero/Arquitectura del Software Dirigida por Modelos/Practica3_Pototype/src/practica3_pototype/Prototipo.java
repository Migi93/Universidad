/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3_pototype;

/**
 *
 * @author Miguel Sánchez
 */
public interface Prototipo {

    public void setIdfamilia(int idfamilia);

    public void setTelefono(String telefono);

    public void setDireccion(String direccion);

    public void setCod_postal(String cod_postal);

    public void setCiudad(String ciudad);

    public void setNivel_economico(String nivel_economico);

    public String getTelefono();

    public String getDireccion();

    public String getCod_postal();

    public String getCiudad();

    public String getNivel_economico();
    
    public int getIdfamilia();

    public Prototipo clonar();

}
