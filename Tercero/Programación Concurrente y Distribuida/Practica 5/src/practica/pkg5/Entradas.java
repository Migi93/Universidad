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
public class Entradas implements Runnable {

    private final Agencia e;

    public Entradas(Agencia e) {
        this.e = e;
    }

    @Override
    public void run() {
        int numeroAleatorio = 0;
        char c;
        try {
            System.out.println("E" + Thread.currentThread().getId());
            c = e.EntraEntradas();
            numeroAleatorio = (int) (Math.random() * 2 + 2);
            Thread.sleep(numeroAleatorio * 1000);
            e.SaleEntradas(c);
        } catch (Exception ex) {
            System.out.println("Error al generar el numero aleatorio " + ex.getMessage());
            ex.printStackTrace();//Para ver el error de donde es y seguir la traza de este
        }
    }
}
