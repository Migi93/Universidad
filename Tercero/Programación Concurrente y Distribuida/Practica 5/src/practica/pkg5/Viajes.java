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
public class Viajes extends Thread {

    private final Agencia v;

    public Viajes(Agencia v) {
        this.v = v;
    }

    @Override
    public void run() {
        int numeroAleatorio = 0;
        char c;
        try {
            System.out.println("V" + getId());
            c = v.EntraViaje();
            numeroAleatorio = (int) (Math.random() * 3 + 3);
            Thread.sleep(numeroAleatorio * 1000);
            v.SaleViaje(c);
        } catch (Exception ex) {
            System.out.println("Error al generar el numero aleatorio " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
