package Persistencia;

/**
 * Colabora
 */
public class colabora {

    private String codExperto;
    private String codCaso;
    private String fecha;
    private String descripcionColaboracion;

    /**
     * Constructor por defecto
     */
    public colabora() {
        // TODO implementar operaciones
        codExperto = null;
        codCaso = null;
        fecha = null;
        descripcionColaboracion = null;
    }

    /**
     * Constructor con parametros
     *
     * @param ce
     * @param cc
     * @param f
     * @param dc
     */
    public colabora(String ce, String cc, String f, String dc) {
        // TODO implementar operaciones
        codExperto = ce;
        codCaso = cc;
        fecha = f;
        descripcionColaboracion = dc;
    }

    public String getCodExperto() {
        // TODO implementar operaciones
        return codExperto;
    }

    public String getCodCaso() {
        // TODO implementar operaciones
        return codCaso;
    }

    public String getFecha() {
        // TODO implementar operaciones
        return fecha;
    }

    public String getDescripcionColaboracion() {
        // TODO implementar operaciones
        return descripcionColaboracion;
    }

    public void setCodExperto(String ce) {
        // TODO implementar operaciones
        codExperto = ce;
    }

    public void setCodcaso(String cc) {
        // TODO implementar operaciones
        codCaso = cc;
    }

    public void setFecha(String f) {
        // TODO implementar operaciones
        fecha = f;
    }

    public void setDescripcionColaboracion(String dc) {
        // TODO implementar operaciones
        descripcionColaboracion = dc;
    }
}
