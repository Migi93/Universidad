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
public class ConversorEurosDolares extends ConversorMoneda{

    public ConversorEurosDolares() {
        super(1.22);
    }

    public double eurosAdolares(double cantidad) {
        return eurosAmoneda(cantidad);
    }

    public double dolaresAeuros(double cantidad) {
        return monedasAeuros(cantidad);
    }
}
