/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Miguel Sánchez
 */
public class CanvasCola extends Canvas {

    private int head;
    private int tail;
    private final int capacidad;
    private int numelementos;
    private Object[] datos;
    private String mensaje;
    private final Font f1 = new Font("Lucida Sans", Font.PLAIN + Font.ITALIC, 20); //Letra
    private final Font f2 = new Font("Tahoma", Font.PLAIN, 20); //Numeros
    private final Color c = new Color(88, 173, 169);

    public CanvasCola(int capacidad) {
        this.capacidad = capacidad;
        this.setBackground(c);
        this.datos = new Object[capacidad];
    }

    @Override
    public void paint(Graphics g) { //
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
        int pos1 = 130, pos2 = 180, centro = 280;

        for (int i = 0; i < this.capacidad; i++) {
            og.setColor(Color.WHITE);
            og.fillRect(pos1, pos2, 40, 40);
            if (datos[i] != null) {
                og.setColor(Color.BLACK);
                og.setFont(f2);
                og.drawString(datos[i].toString(), pos1 + 10, pos2 + 28);
            }
            pos1 = pos1 + 41;
        }
        
        og.setColor(Color.yellow);
        int poshead = 150 + head * 41;
        int[] xh = new int[]{poshead, poshead - 10, poshead + 10};
        int[] yh = new int[]{179, 169, 169};
        og.fillPolygon(xh, yh, 3);

        og.setColor(Color.RED);
        int postail = 150 + tail * 41;
        int[] xt = new int[]{postail, postail - 10, postail + 10};
        int[] yt = new int[]{221, 231, 231};
        og.fillPolygon(xt, yt, 3);

        if (mensaje != null) {
            og.setFont(f1);
            og.setColor(Color.ORANGE);
            og.drawString(mensaje, centro, pos2 - 50);
        }

        g.drawImage(offscreen, 0, 0, null);
    }

    @Override
    public void update(Graphics g) { //Elimina el parpadeo
        paint(g);
    }

    public void avisa(String mensaje) {
        this.mensaje = mensaje;
        repaint();
    }

    public void representa(Object[] buf, int head, int tail, int numele) {
        this.datos = buf;
        this.head = head;
        this.tail = tail;
        this.numelementos = numele;
        this.mensaje = null;
        repaint();
    }
}
