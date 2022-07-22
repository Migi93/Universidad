/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintetizadorconcatenativo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Sánchez
 */
public class FonosAceptados {

    private boolean pregunta;
    private final String lenguajeReconocido;
    private final List<Character> lenguajeAceptado;
    private String oracion;
    private char[] letras;

    public FonosAceptados(String o) {
        pregunta = false;
        lenguajeReconocido = " -aAeEfkmrst";
        oracion = o;
        lenguajeAceptado = new ArrayList<>();
    }

    public void crearLenguaje() {
        char[] lenguaje = lenguajeReconocido.toCharArray();
        for (int i = 0; i < lenguaje.length; i++) {
            lenguajeAceptado.add(lenguaje[i]);
            //System.out.println("Letras del lenguaje: " + lenguaje[i]);
        }
    }

    public ArrayList<String> fonosCorrectos() throws Exception {
        crearLenguaje();
        if (oracion.indexOf('?') == oracion.length() - 1) {//Esto significa que seria una pregunta
            oracion = oracion.substring(0, oracion.length() - 1);
            pregunta = true;
            //System.out.println("Es pregunta");
        }
        if (oracion.indexOf('.') == oracion.length() - 1) {//Esto significa que seria una oracion normal
            oracion = oracion.substring(0, oracion.length() - 1);
            //System.out.println("Es una oracion con punto");
        }
        oracion = "-" + oracion + "-";
        letras = oracion.toCharArray();
        ArrayList<String> difonosCreados = new ArrayList<>();
        for (int i = 0; i < letras.length - 1; i++) { //Se cuentan los espaciones tambien y es length - 1 porque la ultima letra esta en una posicion anterior a la dada en la cadena 
            if (lenguajeAceptado.indexOf(letras[i]) == -1) { //Te devuelve -1 si no existe, en caso contrario te devuelve la posicion donde este
                throw new Exception("El caracter '" + letras[i] + "' no pertenece al lenguaje reconocido.");
            } else {
                if (letras[i] == '-' && letras[i + 1] == 'r') { //La posicion numero 1 no puede ser una r, la 0 siempre sera el guin para poder llamar correctamente a los difonos de la libreria
                    throw new Exception("La letra '" + letras[1] + "' no puede ser inicial en una frase.");
                }
                if (letras[i] == 'r' && letras[i + 1] == 's') {
                    throw new Exception("La letra '" + letras[i] + "' no puede suceder a una '" + letras[i + 1] + "'.");
                }
            }
            if (letras[i] == ' ') { //Si esto ocurre es porque ha reconocido un espacio, por tanto, debemos realizar una pausa en la frase
                //System.out.println("Se permiten los espacios");
                //En lugar de poner letras[i] me creo un audio en blanco y pongo letras[nombredelaudiodelSilencio]
                letras[i] = 'x'; //Metemos la x, que es el sonido que pertenece a un silencion minimo
                difonosCreados.add("" + letras[i]);
            } else {
                difonosCreados.add("" + letras[i] + letras[i + 1]);
                //System.out.println("Letras normales " + letras[i]);
            }
        }
        /*for (String d : difonosCreados) {
            System.out.println(d);
        }*/
        return difonosCreados;
    }

    public boolean isPregunta() {
        return pregunta;
    }

}
