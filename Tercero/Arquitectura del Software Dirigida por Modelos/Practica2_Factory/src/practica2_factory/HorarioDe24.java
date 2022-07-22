/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_factory;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Miguel Sánchez
 */
public class HorarioDe24 extends Hora {

    public HorarioDe24() {
        actualizarHora();
    }

    @Override
    public void actualizarHora() {
        Calendar horario = Calendar.getInstance();
        horario.setTime(new Date());
        cambiarhora(horario.get(Calendar.HOUR_OF_DAY), horario.get(Calendar.MINUTE), horario.get(Calendar.SECOND));
    }

    /**
     *
     * @return
     */
    @Override
    protected String tipohora() {
        return "Formato de hora -> 24 Horas.";
    }
}
