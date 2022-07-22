/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Miguel Sánchez
 */
public class RobotB implements Runnable {

    Canvasp6 c;
    private final Semaphore SiloM1;
    private final Semaphore SiloM2;

    public RobotB(Semaphore m1, Semaphore m2, Canvasp6 c) {
        this.c = c;
        this.SiloM1 = m1;
        this.SiloM2 = m2;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                int segundos = (int) (Math.random() * 3 + 1);
                Thread.sleep(500);
                SiloM1.acquire();//Es el wait()
                c.restaSiloM1();
                c.cogerM1B();
                for (int j = 0; j < 3; j++) {
                    Thread.sleep(500);
                    SiloM2.acquire();//Es el wait()
                    c.restaSiloM2();
                    c.cogerM2B();
                }
                Thread.sleep(segundos * 1000);
                c.soltarM1M2B();
                c.sacosBfabricado();
                System.out.println("Saco numero " + i + " Creado por el RobotB.");
            }
        } catch (InterruptedException ex) {
            System.out.println("Problema al crear el saco con el RobotB. " + ex.getMessage());
        }
    }
}
