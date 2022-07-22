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
public class ConversorEurosFrancoSuizo extends ConversorMoneda{

    public ConversorEurosFrancoSuizo() {
        super(1.10);
    }

    public double eurosAfrancosuizo(double cantidad) {
        return eurosAmoneda(cantidad);
    }

    public double francosuizoAeuros(double cantidad) {
        return monedasAeuros(cantidad);
    }
}
