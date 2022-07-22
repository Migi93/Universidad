/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4_adapter;

/**
 *
 * @author Miguel Sánchez
 */
public class Barco {

    private boolean grifo;
    private int nudos;

    public int getNudos() {
        return nudos;
    }

    public Barco() {
        grifo = false;
        nudos = 0;
    }

    public void Conectar() {
        grifo = true;
        System.out.println("Se ha conectado el grifo.");
    }

    public void Activar() {
        if (grifo == true) {
            System.out.println("El Grifo se ha activado.");
        } else {
            System.out.println("El grifo aun no esta activado.");
        }
    }

    public void MoverMasRapido() {
        if (grifo == true && nudos < 20) {
            nudos = nudos + 1;
            nudos = (int) (nudos + (nudos * 1.7));
            System.out.println("Acelerando a... " + nudos + " nudos.");
        } else if(grifo == false){
            System.out.println("El grifo no esta conectado.");
        } else {
            System.out.println("Maxima velocidad alcanzada. No se pueden aumentar los nudos.");
        }
    }

    public void Detener() {
        nudos = nudos - 1;
        if (nudos <= 0) {
            System.out.println("El barco esta totalmente parado y se ha desconcectado el grifo.");
            grifo = false;
            nudos = 0;
        } else {
            nudos = (int) (nudos - (nudos * 0.2));
            System.out.println("Deteniendo el barco a... " + nudos + " nudos.");
        }
    }

    public void Desconectar() {
        if (nudos < 1) {
            grifo = false;
            System.out.println("Barco detenido y desconectado.");
        } else {
            System.out.println("El Barco no puede pararse en movimiento, debes frenar antes.");
        }
    }
}
