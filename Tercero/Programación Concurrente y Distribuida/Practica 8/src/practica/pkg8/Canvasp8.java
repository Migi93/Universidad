/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg8;

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
public class Canvasp8 extends Canvas {

    private Font fuente = new Font("Agency FB", Font.BOLD, 15);
    ArrayList<Long> colaNotas;
    ArrayList<Long> colaRegistro;
    Long idAn;
    Long idBn;
    Long idCn;
    Long idRn;
    Long idAr;
    Long idBr;
    Long idCr;
    Long idRr;

    public Canvasp8() {
        colaNotas = new ArrayList<>();
        colaRegistro = new ArrayList<>();
    }

    public void entranota() {
        colaNotas.add(Thread.currentThread().getId());
        repaint();
    }

    public void salenota() {
        colaNotas.remove(Thread.currentThread().getId());
        repaint();
    }

    public void entraregistro() {
        colaRegistro.add(Thread.currentThread().getId());
        repaint();
    }

    public void saleregistro() {
        colaRegistro.remove(Thread.currentThread().getId());
        repaint();
    }

    public void atiendenotasA() {
        idAn = Thread.currentThread().getId();
        repaint();
    }

    public void atiendenotasB() {
        idBn = Thread.currentThread().getId();
        repaint();
    }

    public void atiendenotasC() {
        idCn = Thread.currentThread().getId();
        repaint();
    }

    public void atiendenotasR() {
        idRn = Thread.currentThread().getId();
        repaint();
    }

    public void desatiendenotas(char cliente) {
        if (cliente == 'A') {
            idAn = null;
        }
        if (cliente == 'B') {
            idBn = null;
        }
        if (cliente == 'C') {
            idCn = null;
        }
        if (cliente == 'R') {
            idRn = null;
        }
        repaint();
    }

    public void atienderegistrosA() {
        idAr = Thread.currentThread().getId();
        repaint();
    }
    public void atienderegistrosB() {
        idBr = Thread.currentThread().getId();
        repaint();
    }
    public void atienderegistrosC() {
        idCr = Thread.currentThread().getId();
        repaint();
    }
    
    public void atienderegistrador(){
        idRr = Thread.currentThread().getId();
        repaint();
    }

    public void desatienderegistros(char cliente) {
        if (cliente == 'A') {
            idAr = null;
        }
        if (cliente == 'B') {
            idBr = null;
        }
        if (cliente == 'C') {
            idCr = null;
        }
        idRr = null;
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
        g.setColor(Color.RED);
        g.drawString("Oficial A", 10, 20);
        g.drawString("Oficial B", 80, 20);
        g.drawString("Oficial C", 160, 20);
        g.drawString("Clientes Notas Simples Informativas", 10, 650);
        g.setColor(Color.blue);
        g.drawString("Registrador", 530, 20);
        g.drawString("Clientes Registro Prpiedades", 10, 680);
        g.setColor(Color.BLACK);
        g.drawString("Clientes", 280, 150);

        for (int j = 0; j < colaNotas.size(); j++) {
            g.setColor(Color.red);
            g.fillOval(270, 180 + 30 * j, 25, 25);
            g.drawString(Long.toString(colaNotas.get(j)), 255, 200 + 30 * j);
        }

        for (int k = 0; k < colaRegistro.size(); k++) {
            g.setColor(Color.blue);
            g.fillOval(310, 180 + 30 * k, 25, 25);
            g.drawString(Long.toString(colaRegistro.get(k)), 340, 200 + 30 * k);
        }

        if (idAn != null) {
            g.setColor(Color.red);
            g.fillOval(15, 30, 25, 25);
            g.drawString(Long.toString(idAn), 20, 75);
        }
        
        if (idAr != null) {
            g.setColor(Color.blue);
            g.fillOval(15, 30, 25, 25);
            g.drawString(Long.toString(idAr), 20, 75);
        }

        if (idBn != null) {
            g.setColor(Color.red);
            g.fillOval(85, 30, 25, 25);
            g.drawString(Long.toString(idBn), 90, 75);
        }
        
        if (idBr != null) {
            g.setColor(Color.blue);
            g.fillOval(85, 30, 25, 25);
            g.drawString(Long.toString(idBr), 90, 75);
        }

        if (idCn != null) {
            g.setColor(Color.red);
            g.fillOval(165, 30, 25, 25);
            g.drawString(Long.toString(idCn), 170, 75);
        }
        
        if (idCr != null) {
            g.setColor(Color.blue);
            g.fillOval(165, 30, 25, 25);
            g.drawString(Long.toString(idCr), 170, 75);
        }

        if (idRn != null) {
            g.setColor(Color.red);
            g.fillOval(545, 30, 25, 25);
            g.drawString(Long.toString(idRn), 550, 75);
        }
        
        if (idRr != null) {
            g.setColor(Color.blue);
            g.fillOval(545, 30, 25, 25);
            g.drawString(Long.toString(idRr), 550, 75);
        }

        og.drawImage(i, 0, 0, null);
    }
}
