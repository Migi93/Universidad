/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg9;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

/**
 *
 * @author Miguel Sánchez
 */
public class Calculador implements Callable<Integer> { //Un callable es un hilo que te devuelve algo

    private String hash;
    private int comienzo;
    private int fin;
    private Canvasp9 c;
    private int identificador;

    public Calculador(String hash, int comienzo, int fin, Canvasp9 c, int identificador) {
        this.hash = hash;
        this.comienzo = comienzo;
        this.fin = fin;
        this.c = c;
        this.identificador = identificador;
    }

    @Override
    public Integer call() {
        int i = comienzo;
        boolean encontrado = false;
        int numero = 0;
        while (i <= fin && !encontrado) {
            c.comprobando(i,identificador);
            if (hashString(Integer.toString(i)).equals(hash)) {
                encontrado = true;
                numero = i;
            } else {
                i++;
            }
        }
        if (encontrado == true) {
            return numero;
        } else {
            return -1;
        }
    }

    public static String hashString(String text) { //Al meter la cadena texto, te saca el hash(te saca muchos caracteres) de la cadena de texto que hayamos introducido
        if (text.length() < 6) {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < 6 - text.length()) {
                sb.append('0');
            }
            text = sb.append(text).toString();
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(text.getBytes());
            StringBuilder hex = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hex.append("0").append(Integer.toHexString((0xFF & hash[i])).toUpperCase());
                } else {
                    hex.append(Integer.toHexString(0xFF & hash[i]).toUpperCase());
                }
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }
}
