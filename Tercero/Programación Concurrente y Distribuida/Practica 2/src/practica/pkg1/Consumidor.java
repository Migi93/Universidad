/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1;

/**
 *
 * @author Miguel Sánchez
 */
public class Consumidor implements Runnable{
    
    private final ColaLenta lacola;
    
    public Consumidor(ColaLenta lacola){
        this.lacola = lacola;
    }
    
    public void consumir(){
        float desacolado;
        Thread t = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            try{
            desacolado = (float) this.lacola.Desacola();
                System.out.println("El numero desacolado es: " + desacolado + " y el identificador del hilo es: " + t.getId());
            }catch (Exception ex){
                //System.out.println("Error al desacolar un numero de la cola, la cola esta vacia");
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void run() {
        consumir();
    }
}
