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

    public Productor(ColaLenta lacola) {
        this.lacola = lacola;
    }

    public void producir() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        int ale2 = 0;
        try {
            for (int i = 0; i < 15; i++) {
                ale2 = r.nextInt(1) + 2;
                int aleatorio = Math.abs(r.nextInt() % 100);
                Thread.sleep(ale2 * 1000);
                this.lacola.Acola(aleatorio);
                System.out.println("El numero introducido es: " + aleatorio + " y el identificador del hilo es: " + getId());
            }
        } catch (Exception ex) {
            System.out.println("El hilo: " + getId() + " a finalizado." + ex.getMessage());
        }
    }

    @Override
    public void run() {
        producir();
    }
}
