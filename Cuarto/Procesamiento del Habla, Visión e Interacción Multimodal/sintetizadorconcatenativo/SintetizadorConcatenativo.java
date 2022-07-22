/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizadorconcatenativo;

import java.util.ArrayList;

/**
 *
 * @author Miguel Sánchez
 */
public class SintetizadorConcatenativo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //String o = "efemaka";
            //String o = "esemeketrefe?";
            //String ruta = "esemeketrefe46.wav";
            //String ruta = "pruebaUno.wav";
            String o = args[0];
            String ruta = args[1];
            FonosAceptados f = new FonosAceptados(o);
            GeneraScript g = new GeneraScript();
            ArrayList<String> difonos = new ArrayList<>();
            difonos = f.fonosCorrectos();
            /*for (String s : difonos) {
                System.out.println(s);
            }*/
            g.generaScriptVoz(ruta, difonos);
            //System.out.println(g.dondeEstaElFilename());
            if (f.isPregunta() == true) {
                g.generarEntonacion(ruta);
                System.out.println("Audio con prosodia creado correctamente...");
            } else {
                System.out.println("Audio sin prosodia creado correctamente...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
