/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4_adapter;

/**
 *
 * @author Miguel Sánchez
 */
public class AdaptadorBarco extends Barco implements IMandos {

    private int uno;

    public AdaptadorBarco() {
        super();
        uno = 0;
    }

    @Override
    public void Acelera() {
        if (uno < 1) {
            Conectar();
            Activar();
            MoverMasRapido(); 
        } else {
            MoverMasRapido();
        }
        uno++;
    }

    @Override
    public void Frena() {
        Detener();
        if(getNudos() <= 0){
            Desconectar();
        }
    }
}
