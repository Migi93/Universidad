/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1;

import java.util.Random;

/**
 *
 * @author Miguel Sánchez
 */
public class Productor extends Thread {
    
    private final ColaLenta lacola;
    
    public Productor(ColaLenta lacola){
        this.lacola = lacola;
    }
    
    public void producir(){
        Random r = new Random();
        r.setSeed(System.nanoTime());
        int ale2 = 0;
        for (int i = 0; i < 10; i++) { 
            ale2 = Math.abs(r.nextInt() % 10);
            int aleatorio = Math.abs(r.nextInt() % 100);
            try{
                Thread.sleep(ale2 * 500);
                this.lacola.Acola(aleatorio);
                System.out.println("El numero introducido es: " + aleatorio + " y el identificador del hilo es: " + getId());
            }catch (Exception ex){
                //System.out.println("Error al introducir el numero aleatorio: " + aleatorio);
                System.out.println(ex.getMessage());
            }
        }        
    }
    
    @Override
    public void run() {
        producir(); 
    }
}
