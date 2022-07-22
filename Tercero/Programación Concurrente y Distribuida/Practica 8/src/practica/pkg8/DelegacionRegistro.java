/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Miguel Sánchez
 */
public class DelegacionRegistro {

    private boolean oficialLibreA;
    private boolean oficialLibreB;
    private boolean oficialLibreC;
    private int oficialesLibres;
    private boolean registradorLibre;
    private int HayClienteRegistro;
    private final Canvasp8 c;
    ReentrantLock l = new ReentrantLock(true);
    Condition Cnotas = l.newCondition();
    Condition Cregistro = l.newCondition();

    public DelegacionRegistro(Canvasp8 c) {
        this.c = c;
        this.oficialLibreA = true;
        this.oficialLibreB = true;
        this.oficialLibreC = true;
        this.oficialesLibres = 3;
        this.registradorLibre = true;
        this.HayClienteRegistro = 0;
    }

    public char EntraNotas() throws InterruptedException {
        l.lock();
        char atender;
        c.entranota();
        try {
            while (oficialesLibres == 0 && registradorLibre == false) {
                Cnotas.await();
            }
            c.salenota();
            if (oficialLibreA == true) {
                oficialLibreA = false;
                oficialesLibres--;
                atender = 'A';
                c.atiendenotasA();
            } else if (oficialLibreB == true) {
                oficialLibreB = false;
                oficialesLibres--;
                atender = 'B';
                c.atiendenotasB();
            } else if (oficialLibreC == true) {
                oficialLibreC = false;
                oficialesLibres--;
                atender = 'C';
                c.atiendenotasC();
            } else {
                registradorLibre = false;
                atender = 'R';
                c.atiendenotasR();
            }
        } finally {
            l.unlock();
        }
        return atender;
    }

    public void SaleNotas(char liberar) {
        l.lock();
        try {
            if (liberar == 'A') {
                oficialLibreA = true;
                oficialesLibres++;
            } else if (liberar == 'B') {
                oficialLibreB = true;
                oficialesLibres++;
            } else if (liberar == 'C') {
                oficialLibreC = true;
                oficialesLibres++;
            } else if(liberar == 'R'){
                registradorLibre = true;
            }
            c.desatiendenotas(liberar);
            if(HayClienteRegistro == 0 && oficialesLibres == 0 && registradorLibre == true){
                Cnotas.signal();
            } else if(HayClienteRegistro == 0 && oficialesLibres > 0 && registradorLibre == false){
                Cnotas.signal();
            } else if(HayClienteRegistro == 0 && oficialesLibres > 0 && registradorLibre == true){
                Cnotas.signal();
                Cnotas.signal();
            } else if(HayClienteRegistro > 0 && oficialesLibres > 0 && registradorLibre == true){
                Cregistro.signal();
            }else if(HayClienteRegistro > 0 && oficialesLibres > 0 && registradorLibre == false){
                Cnotas.signal();
            }
            
        } finally {
            l.unlock();
        }
    }

    public char EntraRegistro() throws InterruptedException {
        l.lock();
        c.entraregistro();
        char atender = '0';
        HayClienteRegistro++;
        try {
            while (registradorLibre == false || oficialesLibres == 0) {
                Cregistro.await();
            }
            c.saleregistro();
            HayClienteRegistro--;
            registradorLibre = false;
            c.atienderegistrador();
            if (oficialLibreA == true) {
                oficialLibreA = false;
                oficialesLibres--;
                atender = 'A';
                c.atienderegistrosA();
            } else if (oficialLibreB == true) {
                oficialLibreB = false;
                oficialesLibres--;
                atender = 'B';
                c.atienderegistrosB();
            } else if (oficialLibreC == true) {
                oficialLibreC = false;
                oficialesLibres--;
                atender = 'C';
                c.atienderegistrosC();
            }
        } finally {
            l.unlock();
        }
        return atender;
    }

    public void SaleRegistro(char liberar) {
        l.lock();
        try {
            registradorLibre = true;
             if (liberar == 'A') {
                oficialLibreA = true;
                oficialesLibres++;
            } else if (liberar == 'B') {
                oficialLibreB = true;
                oficialesLibres++;
            } else if (liberar == 'C') {
                oficialLibreC = true;
                oficialesLibres++;
            }
             c.desatienderegistros(liberar);
            if(HayClienteRegistro == 0 && oficialesLibres == 0 && registradorLibre == true){
                Cnotas.signal();
            } else if(HayClienteRegistro == 0 && oficialesLibres > 0 && registradorLibre == false){
                Cnotas.signal();
            } else if(HayClienteRegistro == 0 && oficialesLibres > 0 && registradorLibre == true){
                Cnotas.signal();
                Cnotas.signal();
            } else if(HayClienteRegistro > 0 && oficialesLibres > 0 && registradorLibre == true){
                Cregistro.signal();
            }
        } finally {
            l.unlock();
        }
    }
}
