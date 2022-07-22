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
public class Usuario {
    private final Avion avion;
    private final int dni;
    
    public Usuario(int dni){
        avion = Avion.get_avion();
        this.dni = dni;
    }
    
    public List<Billete> QuieroComprarBilletes(int billetes){
        return avion.VendeBillete(billetes, this);
    }
    
}
