/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg9;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.PasswordView;

/**
 *
 * @author Miguel Sánchez
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f = new JFrame();
        Canvasp9 c = new Canvasp9();
        c.setBackground(Color.white);
        
        f.setResizable(false);
        
        f.setLayout(null);
        
        c.setBounds(0, 100, 520, 300);//Establece la posicion y el tamaño de un elemento
        f.add(c);
        
        JLabel contraseña = new JLabel("Contraseña");
        contraseña.setBounds(180, 35, 70, 25); 
        JTextField password = new JTextField("000000");
        password.setBounds(250, 35, 60, 25);
        
        JLabel hilos = new JLabel("Hilos");
        hilos.setBounds(40, 35, 70, 25);
        JTextField numhilos = new JTextField("1");
        numhilos.setBounds(70, 35, 40, 25);

        JButton ejecutar = new JButton("Iniciar");
        ejecutar.setBounds(400, 35, 70, 25);
        ejecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Supervisor s = new Supervisor(c,Calculador.hashString(password.getText()),Integer.parseInt(numhilos.getText()));
                s.start();
            }
        });
        
        f.add(password);
        f.add(numhilos);
        f.add(contraseña);
        f.add(hilos);
        f.add(ejecutar);
        f.setSize(535, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setTitle("Practica 9 - Miguel Á. Sánchez De La Rosa");
        f.setBackground(Color.lightGray);
        f.setVisible(true);
    }
}
