/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Sánchez
 */
public class RobotR extends Thread {

    private final Canvasp6 c;
    private final Semaphore SiloM1;
    private final Semaphore SiloM2;

    public RobotR(Semaphore m1, Semaphore m2, Canvasp6 c) {
        this.c = c;
        this.SiloM1 = m1;
        this.SiloM2 = m2;
    }

    @Override
    public void run() {
        try {
            for (;;) {
                Thread.sleep(4000);
                int aleatorioM1 = (int) (Math.random() * 3 + 3);
                int aleatorioM2 = (int) (Math.random() * 3 + 3);
                for (int i = 0; i < aleatorioM1; i++) {
                    SiloM1.release();//Es el Signal
                    c.introduceSiloM1();
                }
                System.out.println("La cantidad depositada en el SiloM1 es: " + aleatorioM1);
                for (int i = 0; i < aleatorioM2; i++) {
                    SiloM2.release();//Es el Signal
                    c.introduceSiloM2();
                }
                System.out.println("La cantidad depositada en el SiloM2 es: " + aleatorioM2);
            }
        } catch (InterruptedException ex) {
            System.out.println("RobotR interrumpido debido a que se han terminado los Robot A y B. " + ex.getMessage());
        }

    }

}
