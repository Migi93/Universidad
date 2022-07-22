/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.awt.Color;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Miguel Sánchez
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Canvasp6 c = new Canvasp6();
        JFrame f = new JFrame();
        c.setSize(700, 700);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        c.setBackground(Color.lightGray);
        f.setTitle("Practica 6 - Miguel Á. Sánchez De La Rosa");
        f.setVisible(true);
        
        Semaphore M1 = new Semaphore(0);
        Semaphore M2 = new Semaphore(0);

        Thread[] t = new Thread[3];
        t[0] = new RobotA(M1, M2, c);
        t[1] = new Thread(new RobotB(M1, M2, c));
        t[2] = new RobotR(M1, M2, c);
        
        t[0].start();
        t[1].start();
        t[2].start();
        
        t[0].join();
        t[1].join();
        t[2].interrupt();
        t[2].join();
        
        Thread.sleep(5000);
        System.exit(0);
    }
}
