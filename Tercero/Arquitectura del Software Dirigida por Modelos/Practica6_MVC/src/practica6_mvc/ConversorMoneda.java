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
public class ConversorMoneda {

    private final double cambio;

    public ConversorMoneda(double valorCambio) {
        cambio = valorCambio;
    }

    public double eurosAmoneda(double cantidad) {
        return cantidad * cambio;
    }

    public double monedasAeuros(double cantidad) {
        return cantidad/cambio;
    }
}
