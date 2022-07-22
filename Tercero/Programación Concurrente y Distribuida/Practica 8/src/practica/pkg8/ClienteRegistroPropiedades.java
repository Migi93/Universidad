/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg8;

/**
 *
 * @author Miguel Sánchez
 */
public class ClienteRegistroPropiedades extends Thread{
    private DelegacionRegistro dr;
    
    public ClienteRegistroPropiedades(DelegacionRegistro dr){
        this.dr = dr;
    }
    
    @Override
    public void run(){
        //int n = (int) (Math.random() * (<número_máximo + 1> - <número_mínimo>)) + <numero_mínimo>;
        try {
            char atender;
            System.out.println("Cliente Registro con id: " + Thread.currentThread().getId());
            atender = dr.EntraRegistro();
            int aleatorio = (int) (Math.random() * 1 + 3);
            Thread.sleep(1000 * aleatorio);
            dr.SaleRegistro(atender);
        } catch (InterruptedException ex) {
            System.out.println("Error con el ClienteRegistroPropiedades. " + ex.getMessage());
        }
    }
    
}
