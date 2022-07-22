/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_singleton;

/**
 *
 * @author Miguel Sánchez
 */
public class Billete {
    private final int codigo;
    private final int asiento;
    private final Usuario usuario;
    private static int contador;

    public Billete(int codigo, int asiento, Usuario usuario) {
        this.codigo = codigo;
        this.asiento = asiento;
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getAsiento() {
        return asiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public static Billete generarBillete(Avion avion, Usuario usuario){
        int asiento = -1;
        contador++;
        boolean encontrado = false;
        int i = 0;
        boolean[] alibre = avion.getAsientoslibres();
        while (i < alibre.length && encontrado == false) {            
            if(alibre[i] == true){
                encontrado = true;
                asiento = i;
            } else {
                i++;
            }
        }
        avion.setAsientoslibres(asiento, false);
        return new Billete(contador, asiento, usuario);
    }
}
