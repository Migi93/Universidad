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
public class ConversorEurosPesetas extends ConversorMoneda{
    
    public ConversorEurosPesetas() {
        super(166.386);
    }
    
    public double eurosApesetas(double cantidad){
        return eurosAmoneda(cantidad);
    }
    
    public double pesetasAeuros(double cantidad){
        return monedasAeuros(cantidad);
    }
}
