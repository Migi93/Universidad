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
public class UsaCola {
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        // TODO code application logic here
        
        ColaLenta c = new ColaLenta(10);
        
        Productor hp1 = new Productor(c);
        
        Consumidor cn1 =  new Consumidor(c);
        
        Thread t1 = new Thread(cn1);
        Thread t2 = new Thread(cn1);        
        
        
        hp1.start();
        hp1.join();
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        
    }
}
