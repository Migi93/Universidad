/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Miguel Sánchez
 */
public class Avion {
    private static final Avion avion = new Avion();
    Map<Usuario, List<Billete>> reservas = new HashMap<>();
    private boolean[] asientoslibres = new boolean[180];
    
    public Avion(){
        Arrays.fill(asientoslibres, true);
    }

    public boolean[] getAsientoslibres() {
        return asientoslibres;
    }

    public void setAsientoslibres(int asiento, boolean estado) {
        asientoslibres[asiento] = estado;
    }
    
    
    
    public static Avion get_avion(){
        return avion;
    }
    
    public List<Billete> VendeBillete(int billetes, Usuario usuario){
        List<Billete> billetenuevo = new ArrayList<>();
        if(billetes > CuantosAsientosQuedan()){
            System.out.println("ERROR. Solo quedan " + CuantosAsientosQuedan() + " asientos. \n");
        } else{
            List<Billete> billetesusuario;
            billetesusuario = reservas.get(usuario);
            if(billetesusuario == null){
                billetesusuario = new ArrayList<>();
            }
            for (int i = 0; i < billetes; i++) {
                Billete billete = Billete.generarBillete(avion, usuario);
                billetesusuario.add(billete);
                billetenuevo.add(billete);
            }
            reservas.put(usuario, billetesusuario);
        }
        return billetenuevo;
    }
    
    public List<Billete> DevuelveBilletes(int billetes, Usuario usuario){
        List<Billete> billeteeliminados = new ArrayList<>();
        List<Billete> billeteusu = reservas.get(usuario);
        if(billeteusu != null){
            if(billetes > billeteusu.size()){
                System.out.println("ERROR. El usuario esta intentado devolver mas billetes de los que ha comprado. \n");
            } else {
                for (int i = 0; i < billetes; i++) {
                    Billete billete = billeteusu.get(0);
                    asientoslibres[billete.getAsiento()] = true;
                    billeteeliminados.add(billete);
                    billeteusu.remove(0);
                }
            }
        }
        return billeteeliminados;
    }
    
    public int CuantosAsientosQuedan(){
        Integer asientos = 0;
        for (Map.Entry<Usuario, List<Billete>> entry : reservas.entrySet()) {
            List<Billete> numasientos = entry.getValue();
            asientos = asientos + numasientos.size();
        }
        return 180 - asientos;
    }
    
}
