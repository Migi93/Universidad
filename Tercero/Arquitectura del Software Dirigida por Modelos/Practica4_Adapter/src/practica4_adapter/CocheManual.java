package practica4_adapter;

/**
 *
 * @author Miguel Sánchez
 */
public class CocheManual{

    private int Revoluciones;
    private int Velocidad;
    private int marcha;

    public CocheManual() {
        Revoluciones = 0;
        Velocidad = 0;
        marcha = 0;
    }

    public void Acelera() {
        if (Revoluciones > 5000) {
            System.out.println("El motor se ha parado por una averia");
            Velocidad = 0;
            Revoluciones = 0;
        } else {
            Revoluciones = Revoluciones + 1000;
            if (marcha == 0) {
                SubeMarcha();
            }
            Velocidad = (int) (Velocidad + ((Revoluciones * 0.004) * marcha));
            System.out.println("Acelerando... Velocidad de " + Velocidad + " Km/h");
        }
    }

    public void SubeMarcha() {
        marcha++;
        if (marcha > 5) {
            System.out.println("El coche no tiene mas de 5 marchas");
        } else {
            System.out.println("Subiendo Marcha... Marcha " + marcha);
        }
    }

    public void BajaMarcha() {
        marcha--;
        if (marcha < 1) {
            System.out.println("El coche no puede bajar mas marchas");
        }
        System.out.println("Bajando Marcha... Marcha " + marcha);

    }

    public void Frena() {
        Revoluciones = Revoluciones - 1000;
        if (Revoluciones <= 0 || Velocidad == 0) {
            System.out.println("El coche se ha frenado y esta totalmente parado.");
            Velocidad = 0;
            Revoluciones = 0;
        } else {
            Velocidad = (int) (Velocidad - ((Revoluciones * 0.006) * marcha));
            if (Velocidad <= 0) {
                Velocidad = 0;
            }
            System.out.println("Frenando... Velocidad de " + Velocidad + " Km/h");
        }
    }

}
