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
public class Practica6_MVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConversorEurosPesetas modelo = new ConversorEurosPesetas();
        ConversorEurosDolares modelodolar = new ConversorEurosDolares();
        ConversorEurosFrancoSuizo modelofrancosuizo = new ConversorEurosFrancoSuizo();
        //System.out.println("El resultado es: " + modelo.eurosAmoneda(12));
        InterfazVista vista = new InterfazTextualConversor();
        ControlConversor control = new ControlConversor(vista, modelo, modelodolar, modelofrancosuizo);
        vista.setControlador(control);
        vista.arranca();
    }
}
