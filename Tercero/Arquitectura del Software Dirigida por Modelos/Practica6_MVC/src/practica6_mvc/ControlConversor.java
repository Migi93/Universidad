/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6_mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlConversor implements ActionListener {

    private InterfazVista vista;
    private ConversorEurosPesetas modelo;
    private ConversorEurosDolares modelodolar;
    private ConversorEurosFrancoSuizo modelofrancosuizo;

    public ControlConversor(InterfazVista vista, ConversorEurosPesetas modelo, ConversorEurosDolares modelodolar, ConversorEurosFrancoSuizo modelofrancosuizo) {
        this.vista = vista;
        this.modelo = modelo;
        this.modelodolar = modelodolar;
        this.modelofrancosuizo = modelofrancosuizo;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        double cantidad = vista.getCantidad();
        if (evento.getActionCommand().equals(InterfazVista.AEUROS)) {
            vista.escribeCambio("\n" + cantidad + " Pesetas son " + modelo.pesetasAeuros(cantidad) + " Euros.\n");
        } else if (evento.getActionCommand().equals(InterfazVista.APESETAS)) {
            vista.escribeCambio("\n" + cantidad + " Euros son " + modelo.eurosApesetas(cantidad) + " Pesetas.\n");
        } else if (evento.getActionCommand().equals(InterfazVista.EUROADOLAR)) {
            vista.escribeCambio("\n" + cantidad + " Euros son " + modelodolar.eurosAdolares(cantidad) + " Dolares.\n");
        } else if (evento.getActionCommand().equals(InterfazVista.DOLARAEUROS)) {
            vista.escribeCambio("\n" + cantidad + " Dolares son " + modelodolar.dolaresAeuros(cantidad) + " Euros.\n");
        } else if (evento.getActionCommand().equals(InterfazVista.EUROAFRANCOSUIZO)) {
            vista.escribeCambio("\n" + cantidad + " Euros son " + modelofrancosuizo.eurosAfrancosuizo(cantidad) + " Franzo Suizos.\n");
        } else if (evento.getActionCommand().equals(InterfazVista.FRANCOSUIZOAEUROS)) {
            vista.escribeCambio("\n" + cantidad + " Franco Suizo son " + modelofrancosuizo.francosuizoAeuros(cantidad) + " Euros.\n");
        } else {
            vista.escribeCambio("ERROR");
        }
    }

}
