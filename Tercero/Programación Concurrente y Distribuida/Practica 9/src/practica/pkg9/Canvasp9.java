/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg9;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Miguel Sánchez
 */
public class Canvasp9 extends Canvas {

    private Integer[] contador;
    private int seleccionado;
    private final Font fuente = new Font("Agency FB", Font.BOLD, 15);

    public Canvasp9() {
        contador = new Integer[10];
        this.seleccionado = -1;
        reinicio();
    }

    public void reinicio() {
        for (int i = 0; i < 10; i++) {
            contador[i] = 0;
        }
        seleccionado = -1;
        repaint();
    }
    
    public void dibujarclave(int solucion){
        seleccionado = solucion;
        repaint();
    }

    public void comprobando(int num, int identificador) {
        contador[identificador] = num;
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics og) {
        Image i = createImage(getWidth(), getHeight());
        Graphics g = i.getGraphics();
        g.setFont(fuente);
        for (int j = 0; j < 10; j++) {
            if(seleccionado == j){
                g.setColor(Color.red);
            }else{
                g.setColor(Color.black);
            }
            if (j < 5) {
                g.drawString(contador[j].toString(), 110 * j + 20, 80);
            } else {
                g.drawString(contador[j].toString(), 110 * (j - 5) + 20, 150);
            }
            
        }
        og.drawImage(i, 0, 0, null);
    }
}
