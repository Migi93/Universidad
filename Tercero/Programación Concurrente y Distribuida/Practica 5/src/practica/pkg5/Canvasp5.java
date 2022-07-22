/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg5;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Miguel Sánchez
 */
public class Canvasp5 extends Canvas {

    private ArrayList<Long> viaje;
    private ArrayList<Long> entrada;
    private Long a;
    private Long b;
    private boolean atipo;
    private boolean btipo;
    private Font empleadoAB = new Font("Agency FB", Font.BOLD, 15);

    public Canvasp5() {
        viaje = new ArrayList<>();
        entrada = new ArrayList<>();
    }

    public void listaE(long id) {
        entrada.add(id);
        repaint();
    }

    public void saleE(long id) {
        entrada.remove(id);
        repaint();
    }

    public void listaV(long id) {
        viaje.add(id);
        repaint();
    }

    public void saleV(long id) {
        viaje.remove(id);
        repaint();
    }

    public void atendiendo(char tipo, char empleado) {
        if (tipo == 'V') {
            if (empleado == 'A') {
                a = Thread.currentThread().getId();
                atipo = true; //Es un viaje y lo atiende empleadoA
            } else {
                b = Thread.currentThread().getId();
                btipo = true; //Es un viaje y lo atiende empleadoB
            }
        } else {
            if (empleado == 'A') {
                a = Thread.currentThread().getId();
                atipo = false; //Es una entrada y lo atiende empleadoA
            } else {
                b = Thread.currentThread().getId();
                btipo = false; //Es una entrada y lo atienede empleadoB
            }
        }
        repaint();
    }

    public void desatendiendo() {
        if (a != null && a == Thread.currentThread().getId()) {
            a = null;
        } else if(b != null && b == Thread.currentThread().getId()){
            b = null;
        }
        repaint();
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics og) {
        Image i = createImage(getWidth(), getHeight());
        Graphics g = i.getGraphics();

        g.setFont(empleadoAB);
        g.setColor(Color.RED);
        g.drawString("Empleado A", 10, 20);
        g.drawString("Viajes", 10, 650);
        g.setColor(Color.blue);
        g.drawString("Empleado B", 330, 20);
        g.drawString("Entradas", 10, 680);
        g.setColor(Color.BLACK);
        g.drawString("Clientes", 180, 100);

        for (int j = 0; j < viaje.size(); j++) {
            g.setColor(Color.red);
            g.drawString(Long.toString(viaje.get(j)), 130, 128 + 35 * j);
            g.fillOval(150, 110 + 35 * j, 25, 25);
        }

        for (int j = 0; j < entrada.size(); j++) {
            g.setColor(Color.blue);
            g.drawString(Long.toString(entrada.get(j)), 250, 128 + 35 * j);
            g.fillOval(220, 110 + 35 * j, 25, 25);
        }

        if (a != null && atipo == true) { //Es de tipo viajes y lo atiende el empleadoA
            g.setColor(Color.red);
            g.drawString(Long.toString(a), 40, 60);
            g.fillOval(10, 40, 25, 25);
        }

        if (a != null && atipo == false) { //Es de tipo entrada y lo atiende el empleadoA
            g.setColor(Color.blue);
            g.drawString(Long.toString(a), 40, 60);
            g.fillOval(10, 40, 25, 25);
        }

        if (b != null && btipo == true) {
            g.setColor(Color.red);
            g.drawString(Long.toString(b), 310, 60);
            g.fillOval(330, 40, 25, 25);
        }

        if (b != null && btipo == false) {
            g.setColor(Color.blue);
            g.drawString(Long.toString(b), 310, 60);
            g.fillOval(330, 40, 25, 25);
        }

        og.drawImage(i, 0, 0, null);
    }

}
