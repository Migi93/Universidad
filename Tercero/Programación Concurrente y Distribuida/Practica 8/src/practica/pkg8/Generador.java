/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg8;

import java.awt.Color;
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

        int MAX_CLIENTES = 25;
        JFrame f = new JFrame();
        Canvasp8 c = new Canvasp8();
        c.setSize(600, 700);
        f.setResizable(false);
        f.add(c);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setTitle("Practica 8 - Miguel Á. Sánchez De La Rosa");
        c.setBackground(Color.lightGray);
        f.setVisible(true);
        
        DelegacionRegistro dr = new DelegacionRegistro(c);
        Thread[] t = new Thread[MAX_CLIENTES];

        //int n = (int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
        for (int i = 0; i < MAX_CLIENTES; i++) {
            int aleatorio = (int) (Math.random() * 101);
            if (aleatorio <= 35) {
                t[i] = new ClienteRegistroPropiedades(dr);
                t[i].start();
            } else {
                t[i] = new Thread(new ClienteNsimplesInformativas(dr));
                t[i].start();
            }
            int segundos = (int) (Math.random() * 2 + 1);
            Thread.sleep(1000 * segundos);
        }

        for (int i = 0; i < MAX_CLIENTES; i++) {
            t[i].join();
        }
        
        Thread.sleep(1000 * 2);
        System.exit(0);
    }
}
