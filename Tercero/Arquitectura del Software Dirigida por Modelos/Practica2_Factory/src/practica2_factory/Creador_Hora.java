/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_factory;

/**
 *
 * @author Miguel Sánchez
 */
public class Creador_Hora extends Creador{
    protected int tipo;
    
    public Creador_Hora(int t){
    tipo = t;
    }
    
    public Hora Horario(){
        if(tipo == 24){
            return new HorarioDe24();
        } else if(tipo == 12){
            return new HorarioDe12();
        } else {
            return new Hora();
        }
    }
    
}
