/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1;

import java.util.Random;

/**
 *
 * @author Miguel Sánchez
 */
public class UsaCola {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Random r = new Random();
        r.setSeed(System.nanoTime());
        Cola c = new Cola(4);
            for(int i=0;i<10;i++){
                int aleatorio = r.nextInt(2);
                System.out.println("----- " + aleatorio + " -----");
                if(aleatorio == 1){
                        try{
                        c.Acola(i);
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                }
                else{
                    try{
                    c.Desacola();
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println(c.toString());
            }                         
    }
}
