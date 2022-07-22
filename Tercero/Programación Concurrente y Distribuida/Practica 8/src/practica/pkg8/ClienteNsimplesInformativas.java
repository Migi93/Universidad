/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Sánchez
 */
public class ClienteNsimplesInformativas implements Runnable{
    private DelegacionRegistro dr;
    
    public ClienteNsimplesInformativas(DelegacionRegistro dr){
        this.dr = dr;
    }
    
    @Override
    public void run(){
        //int n = (int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
        try {
            char atender;
            System.out.println("Cliente Notas con id: " + Thread.currentThread().getId());
            atender = dr.EntraNotas();
            int aleatorio = (int) (Math.random() * 2 + 4);
            Thread.sleep(1000 * aleatorio);
            dr.SaleNotas(atender);
        } catch (InterruptedException ex) {
            System.out.println("Error con el ClienteNsimplesInformativas. " + ex.getMessage());
        }
    }
}
