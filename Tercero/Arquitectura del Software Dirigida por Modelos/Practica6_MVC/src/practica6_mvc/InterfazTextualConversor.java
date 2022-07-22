/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6_mvc;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterfazTextualConversor implements InterfazVista {

    private ControlConversor Controlador;
    private final int nroOperacion = 0;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private int leeOpcion() {
        String s = null;
        try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException ex) {
            OperacionIncorrecta();
            return 0;
        }
    }

    private double leeCantidad() {
        String s = null;
        try {
            s = in.readLine();
            return Double.parseDouble(s);
        } catch (IOException ex) {
            System.out.println("El formato debe der 99.9:");
            return leeCantidad();
        }
    }

    private void soliticaOperacion() {
        System.out.println("Indique la operacion: ");
        System.out.println("1.- Pesetas a Euros.");
        System.out.println("2.- Euros a Pesetas. ");
        System.out.println("3.- Euros a Dolares.");
        System.out.println("4.- Dolares a Euros. ");
        System.out.println("5.- Euros a Francos Suizo.");
        System.out.println("6.- Francos Suizo a Euros. ");
        System.out.println("0.- Salir.");
    }

    private void OperacionIncorrecta() {
        System.out.println("\nOperacion incorrecta, intentelo de nuevo.\n");
        procesaNuevaOperacion();
    }

    private void procesaNuevaOperacion() {
        int operacion;
        soliticaOperacion();
        operacion = leeOpcion();
        if (operacion == 0) {
            System.out.println("\n***ADIOS***\n");
            System.exit(0);
        } else if (operacion == 1) {
            Controlador.actionPerformed(new ActionEvent(this, nroOperacion, AEUROS));
        } else if (operacion == 2) {
            Controlador.actionPerformed(new ActionEvent(this, nroOperacion, APESETAS));
        } else if (operacion == 3) {
            Controlador.actionPerformed(new ActionEvent(this, nroOperacion, EUROADOLAR));
        } else if (operacion == 4) {
            Controlador.actionPerformed(new ActionEvent(this, nroOperacion, DOLARAEUROS));
        } else if (operacion == 5) {
            Controlador.actionPerformed(new ActionEvent(this, nroOperacion, EUROAFRANCOSUIZO));
        } else if (operacion == 6) {
            Controlador.actionPerformed(new ActionEvent(this, nroOperacion, FRANCOSUIZOAEUROS));
        } else {
            OperacionIncorrecta();
        }
    }

    @Override
    public void setControlador(ControlConversor c) {
        Controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public double getCantidad() {
        System.out.println("Cantidad que quiere convertir 99.9: ");
        return leeCantidad();
    }

    @Override
    public void escribeCambio(String s) {
        System.out.println(s);
        procesaNuevaOperacion();
    }

}
