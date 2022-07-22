/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Miguel Sánchez
 */
public class Canvasp6 extends Canvas {

    private final int[] SiloM1 = new int[1];
    private final int[] SiloM2 = new int[1];
    private int cantidadfSacoA;
    private int cantidadfSacoB;
    private int cojoM1A;
    private int cojoM1B;
    private int cojoM2A;
    private int cojoM2B;
    private final Font f = new Font("Agency FB", Font.BOLD, 15);

    public Canvasp6() {
        SiloM1[0] = 0;
        SiloM2[0] = 0;
        cantidadfSacoA = 0;
        cantidadfSacoB = 0;
        cojoM1A = 0;
        cojoM1B = 0;
        cojoM2A = 0;
        cojoM2B = 0;
    }

    public void introduceSiloM1() {
        SiloM1[0]++;
        repaint();
    }

    public void introduceSiloM2() {
        SiloM2[0]++;
        repaint();
    }

    public void restaSiloM1() {
        SiloM1[0]--;
        repaint();
    }

    public void restaSiloM2() {
        SiloM2[0]--;
        repaint();
    }

    public void sacosAfabricado() {
        cantidadfSacoA++;
        repaint();
    }

    public void sacosBfabricado() {
        cantidadfSacoB++;
        repaint();
    }

    public void cogerM1A() {
        cojoM1A++;
        repaint();
    }

    public void cogerM1B() {
        cojoM1B++;
        repaint();
    }

    public void cogerM2A() {
        cojoM2A++;
        repaint();
    }

    public void cogerM2B() {
        cojoM2B++;
        repaint();
    }

    public void soltarM1M2A() {
        cojoM1A = 0;
        cojoM2A = 0;
        repaint();
    }

    public void soltarM1M2B() {
        cojoM1B = 0;
        cojoM2B = 0;
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

        g.setFont(f);
        g.setColor(Color.blue);
        g.drawString("Silo M1", 260, 200);
        for (int j = 0; j < SiloM1[0]; j++) {
            if (SiloM1[0] >= 13) {
                g.drawString("Silo M1 lleno.", 250, 225);
            } else {
                g.fillOval(260, 225 + 35 * j, 25, 25);
            }
        }
        g.setColor(Color.red);
        g.drawString("Silo M2", 380, 200);
        for (int j = 0; j < SiloM2[0]; j++) {
            g.fillOval(380, 225 + 35 * j, 25, 25);
        }

        g.setColor(Color.black);
        g.drawString("Materias para el Saco A", 10, 20);
        g.drawString("Sacos A fabricados: " + cantidadfSacoA, 10, 40);
        /*for (int j = 0; j < 10; j++) {
            
        }*/

        g.drawString("Materias para el Saco B", 560, 20);
        g.drawString("Sacos B fabricados: " + cantidadfSacoB, 560, 40);
        /*for (int j = 0; j < 10; j++) {
            
        }*/

        og.drawImage(i, 0, 0, null);
    }

}
