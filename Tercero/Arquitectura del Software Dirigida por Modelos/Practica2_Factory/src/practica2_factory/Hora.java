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
public class Hora {
    protected int hora;
    protected int minutos;
    protected int segundos;
    protected String ampm;
    
    public Hora(){
        ampm = "   ";
    }
    
    public void cambiarhora(int h, int m, int s, String ampm){
        hora = h;
        minutos = m;
        segundos = s;
        this.ampm = ampm;
    }
    
    public void cambiarhora(int h, int m, int s){
        cambiarhora(h, m, s, "");
    }
    
    public String hora(){
        actualizarHora();
        String horario;
        if(minutos < 10 && segundos < 10 ){
            horario = hora + ":" + "0" + minutos + ":" + "0" + segundos + " " + ampm;
        }   else if(minutos < 10 && segundos > 10){
            horario = hora + ":" + "0" + minutos + ":" + segundos + " " + ampm;
        } else if(minutos > 10 && segundos < 10){
            horario = hora + ":" + minutos + ":" + "0" + segundos + " " + ampm;
        } else {
            horario = hora + ":" + minutos + ":" + segundos + " " + ampm;
        }
        return horario;
    }
    
    public void actualizarHora(){
        
    }
    
    protected String tipohora(){
        return "Tipo de horario generico";
    }
}
