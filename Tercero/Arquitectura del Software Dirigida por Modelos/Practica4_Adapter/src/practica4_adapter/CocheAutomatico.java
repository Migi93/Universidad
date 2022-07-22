/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4_adapter;

/**
 *
 * @author Miguel Sánchez
 */
public class CocheAutomatico implements IMandos{

    private int Revoluciones;
    private int Velocidad;
    
    public CocheAutomatico(){
        Revoluciones = 0;
        Velocidad = 0;
    }

    @Override
    public void Acelera() {
        Revoluciones = Revoluciones + 1000;
        if (Revoluciones > 5000) {
            System.out.println("No se puede acelerar mas");
        } else {
            Velocidad = (int) (Velocidad + (Revoluciones * 0.0099));
            System.out.println("Acelerando... Velocidad de " + Velocidad + " Km/h");
        }
    }

    @Override
    public void Frena() {
        Revoluciones = Revoluciones - 1000;
        if (Revoluciones <= 0) {
            System.out.println("El coche se ha parado y esta totalmente frenado");
        } else {
            Velocidad = (int) (Velocidad - (Revoluciones * 0.01));
            System.out.println("Frenando... Velocidad de " + Velocidad + " Km/h");
        }
        
    }
}
