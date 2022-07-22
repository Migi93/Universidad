/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_factory;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Miguel Sánchez
 */
public class HorarioDe12 extends Hora {

    public HorarioDe12() {
        actualizarHora();

    }

    @Override
    public void actualizarHora() {
        Calendar horario = Calendar.getInstance();
        horario.setTime(new Date());
        String ampm = horario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        cambiarhora(horario.get(Calendar.HOUR), horario.get(Calendar.MINUTE), horario.get(Calendar.SECOND),ampm);
    }

    @Override
    protected String tipohora() {
        return "Formato de hora -> 12 Horas - AM PM.";
    }
}
