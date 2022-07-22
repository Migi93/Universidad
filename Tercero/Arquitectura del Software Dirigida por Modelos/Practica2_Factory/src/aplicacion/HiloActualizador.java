/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import practica2_factory.Hora;

/**
 *
 * @author Miguel Sánchez
 */
public class HiloActualizador extends Thread {

    private Hora h;
    private JLabel hora;

    public HiloActualizador(Hora h, JLabel hora) {
        this.h = h;
        this.hora = hora;
    }

    @Override
    public void run() {
        try {
            while (!interrupted()) {
                hora.setText(h.hora());
                sleep(10);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloActualizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
