/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg9;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Sánchez
 */
public class Supervisor extends Thread {
    
    private Canvasp9 c;
    private String hash;
    private int hilos;
    
    public Supervisor(Canvasp9 c, String hash, int hilos) {
        this.c = c;
        this.hash = hash;
        this.hilos = hilos;
    }
    
    @Override
    public void run() {
        c.reinicio();
        ExecutorService thp = Executors.newFixedThreadPool(hilos);
        Future[] divide = new Future[10];
        for (int i = 0; i < 10; i++) {
            divide[i] = thp.submit(new Calculador(hash, 100000 * (i), 100000 * (i + 1) - 1, c, i)); //Dividimos en 10 y la ThreadPool lanzara el hilo cuando lo necesite (en total 10 hilos)
        }
        int j = 0;
        Integer resultado = -1;
        while (j < 10 && resultado == -1) {
            try {
                resultado = (Integer) divide[j].get();
            } catch (InterruptedException ex) {
                Logger.getLogger(Supervisor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(Supervisor.class.getName()).log(Level.SEVERE, null, ex);
            }
            j++;
        }
        thp.shutdownNow();//Una vez encontrado para los que se van a ejecutar futuramente
        if (resultado != -1) {
            System.out.println("La PASSWORD es: " + resultado + " -> El hilo que lo ha encontrado es el: " + j);
            c.dibujarclave(j - 1);
        }
    }
    
}
