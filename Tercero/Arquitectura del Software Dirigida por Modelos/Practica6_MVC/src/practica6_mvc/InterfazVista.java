/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6_mvc;

/**
 *
 * @author Miguel Sánchez
 */
public interface InterfazVista {

    static final String AEUROS = "Pesetas a Euros", APESETAS = "Euros a Pesetas", EUROADOLAR = "Euros a Dolares", DOLARAEUROS = "Dolare a Euros", 
            EUROAFRANCOSUIZO = "Euros a Franco Suizo", FRANCOSUIZOAEUROS = "Franco suizo a Euros";

    public void setControlador(ControlConversor c);

    public void arranca();

    public double getCantidad();

    public void escribeCambio(String s);
}
