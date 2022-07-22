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
public class Consumidor implements Runnable {

    private final ColaLenta lacola;

    public Consumidor(ColaLenta lacola) {
        this.lacola = lacola;
    }

    public void consumir() {
        Random r = new Random();
        r.setSeed(System.nanoTime());
        float desacolado;
        Thread t = Thread.currentThread();
        int ale2 = 0;
        try {
            for (int i = 0; i < 15; i++) {
                ale2 = r.nextInt(1) + 2;
                Thread.sleep(ale2 * 1000);
                desacolado = (int) this.lacola.Desacola();
                System.out.println("El numero desacolado es: " + desacolado + " y el identificador del hilo es: " + t.getId());
            }
        } catch (Exception ex) {
            //System.out.println("Error al desacolar un numero de la cola, la cola esta vacia");
            System.out.println("El hilo: " + t.getId() + " ha finalizado." + ex.getMessage());
        }
    }

    @Override
    public void run() {
        consumir();
    }
}
