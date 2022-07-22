/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg5;

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
        // TODO code application logic here
        JFrame f = new JFrame();
        Canvasp5 c = new Canvasp5();
        c.setSize(400, 700);
        f.setResizable(false);//Para que la ventana no se le pueda cambiar el tamaño
        f.add(c);//Agregamos el canvas a la ventana
        f.pack(); //Ajusta el tamaño de la venta a lo que tiene dentro (el canvas en este caso)
        f.setLocationRelativeTo(null); //Para que te centre la ventana (Hacer siempre despues del pack)
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Para que al cerrar la ventana se pare el programa
        f.setTitle("Practica 5. Miguel Á. Sánchez De La Rosa");
        c.setBackground(Color.LIGHT_GRAY);
        f.setVisible(true);
        
        int MAX_CLIENTES = 25;
        Agencia a = new Agencia(c);  
        
        Thread[] t = new Thread[MAX_CLIENTES];
        
        for (int i = 0; i < MAX_CLIENTES; i++) {
            int numeroAleatorio = (int) (Math.random() * 10 + 1);
            if (numeroAleatorio <= 3) {
                t[i] = new Viajes(a);
                t[i].start();
            } else {
                t[i] = new Thread(new Entradas(a));
                t[i].start();
            }
            int segundos = (int) (Math.random() * 3 + 1);
            Thread.sleep(1000 * segundos);
        }
        
        for (int i = 0; i < MAX_CLIENTES; i++) {
            t[i].join();
        }
        
        Thread.sleep(1000 * 2);
        System.exit(0);
    }
}
