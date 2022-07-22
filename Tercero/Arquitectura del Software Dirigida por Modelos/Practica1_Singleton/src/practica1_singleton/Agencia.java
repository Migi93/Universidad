/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_singleton;

import java.util.List;

/**
 *
 * @author Miguel Sánchez
 */
public class Agencia {
    private final Avion avion;
    
    public Agencia(){
        avion = Avion.get_avion();
    }
    
    public List<Billete> QuieroDevolverBilletes(int billetes, Usuario usuario){
        return avion.DevuelveBilletes(billetes, usuario);
    }
}
