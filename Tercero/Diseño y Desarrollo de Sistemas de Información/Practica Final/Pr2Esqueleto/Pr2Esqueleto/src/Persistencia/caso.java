package Persistencia;
/**
 * Caso 
 */


public class caso {
    private String codCaso;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
	
   /**
   * Constructor por defecto   
    */
    public caso() {
	// TODO implementar operaciones
        codCaso = null;
        nombre = null;
        fechaInicio = null;
        fechaFin = null;
    }
    
    /**
   * Constructor con parametros
   * @param cc
     * @param n
     * @param fi
     * @param ff
    */
    public caso(String cc,String n, String fi, String ff) {
	// TODO implementar operaciones
        codCaso = cc;
        nombre = n;
        fechaInicio = fi;
        fechaFin = ff;
    }
	     
    public String getCodCaso(){
      // TODO implementar operaciones
      return codCaso;
    }	
	
    public String getNombre(){
     // TODO implementar operaciones
     return nombre;
    }

    public String getFechaInicio(){
       // TODO implementar operaciones
       return fechaInicio;
    }

    public String getFechaFin(){
        // TODO implementar operaciones
        return fechaFin;
    }

    public void setCodCaso(String c){
       // TODO implementar operaciones
       codCaso = c;
    }

    public void setNombre(String n){
     // TODO implementar operaciones
     nombre = n;
    }

    public void setFechaInicio(String fi){
       // TODO implementar operaciones
       fechaInicio = fi;
    }
	     
    public void setFechaFin(String ff){
       // TODO implementar operaciones
       fechaFin = ff;
    }
	    
}
