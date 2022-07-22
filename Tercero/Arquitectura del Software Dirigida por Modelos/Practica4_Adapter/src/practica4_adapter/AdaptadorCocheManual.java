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
public class AdaptadorCocheManual extends CocheManual implements IMandos {

    public AdaptadorCocheManual() {
        super();
    }

    @Override
    public void Acelera() {
        SubeMarcha(); //En este caso, no necesitamos llamar a super.SubeMarcha() porque esta clase no contiene ningun metodo que se llame asi, aunque se podria sin problemas.
        super.Acelera();//Si no lo llamamos con el super, estaria llamandose a sí mismo, y no llamaria al Acelera del padre, que es el CocheManual.
    }

    @Override
    public void Frena() {
        super.Frena();
        BajaMarcha();
    }
}
