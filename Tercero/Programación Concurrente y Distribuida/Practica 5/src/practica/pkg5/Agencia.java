/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg5;

/**
 *
 * @author Miguel Sánchez
 */
public class Agencia {

    private boolean EmpleadoAlibre;
    private boolean EmpleadoBlibre;
    private int Hayviajes;
    private Canvasp5 canvas;

    public Agencia(Canvasp5 c) {
        this.EmpleadoAlibre = true;
        this.EmpleadoBlibre = true;
        this.Hayviajes = 0;
        this.canvas = c;
    }

    public synchronized char EntraViaje() throws InterruptedException {
        char c;
        Hayviajes++;
        canvas.listaV(Thread.currentThread().getId());
        while (!EmpleadoAlibre && !EmpleadoBlibre) {
            wait();
        }
        Hayviajes--;
        if (EmpleadoAlibre) {
            c = 'A';
            EmpleadoAlibre = false;
        } else {
            c = 'B';
            EmpleadoBlibre = false;
        }
        canvas.saleV(Thread.currentThread().getId());
        canvas.atendiendo('V',c);
        return c;
    }

    public synchronized void SaleViaje(char c) {
        if (c == 'A') {
            EmpleadoAlibre = true;
        } else {
            EmpleadoBlibre = true;
        }
        canvas.desatendiendo();
        notifyAll();
    }

    public synchronized char EntraEntradas() throws InterruptedException {
        char c;
        canvas.listaE(Thread.currentThread().getId());
        while ((!EmpleadoAlibre && !EmpleadoBlibre) || (EmpleadoAlibre && Hayviajes > 0 && !EmpleadoBlibre)) {
            wait();
        }
        if (EmpleadoBlibre) {
            c = 'B';
            EmpleadoBlibre = false;
        } else {
            c = 'A';
            EmpleadoAlibre = false;
        }
        canvas.saleE(Thread.currentThread().getId());
        canvas.atendiendo('E',c);
        return c;
    }

    public synchronized void SaleEntradas(char c) {
        if (c == 'A') {
            EmpleadoAlibre = true;
        } else {
            EmpleadoBlibre = true;
        }
        canvas.desatendiendo();
        notifyAll();
    }
}
