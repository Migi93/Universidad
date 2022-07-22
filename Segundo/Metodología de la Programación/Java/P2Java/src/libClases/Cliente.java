package libClases;

 public class Cliente implements Cloneable, Proceso{
	 private static int contador=1;
	 private static final Fecha fechaPorDefecto=new Fecha(1, 1, 2018);
	 
	 private final String nif; //dni del cliente (letra incluida) (NO puede cambiar)
	 private final int codCliente; //codigo único (y fijo) generado por la aplicación
	 private String nombre; //nombre completo del cliente (SI se puede modificar)
	 private final Fecha fechaNac; //fecha nacimiento del cliente (NO se puede cambiar)
	 private final Fecha fechaAlta; //fecha de alta del cliente (SI se puede modificar)
	 
	 public Cliente (String NIF, String nom, Fecha fNac, Fecha fAlta) { 
		 this.codCliente=contador++;
		 this.nif=NIF;
		 this.nombre=nom;
		 this.fechaNac=(Fecha) fNac.clone();
		 this.fechaAlta=(Fecha) fAlta.clone();
	 }
	 
	 public Cliente (String NIF, String nom, Fecha fNac) {
		 this.codCliente=contador++;
		 this.nif=NIF;
		 this.nombre=nom;
		 // Fecha f=(Fecha) fNac.clone(); Esto equivale a la linea de abajo
		 this.fechaNac=(Fecha) fNac.clone();
		 // Fecha f=(Fecha) fechaPorDefecto.clone();
		 this.fechaAlta=(Fecha) fechaPorDefecto.clone();
	 }
	 
	 public Cliente (Cliente c) {
		 this.codCliente=contador++;
		 this.nif=c.nif;
		 this.nombre=c.nombre;
		 this.fechaNac=(Fecha) c.fechaNac.clone();
		 this.fechaAlta=(Fecha) c.fechaAlta.clone();
	 }
	 
	 public Fecha getFechaAlta() {
		 //return new Fecha(this.fechaAlta); //Debe devolver una copia ya que la clase Fecha es mutable
		 return (Fecha) this.fechaAlta.clone();
	 }
	 
	 public static Fecha getFechaPorDefecto() {
		 return (Fecha) fechaPorDefecto.clone();
	 }
	 
	public static void setFechaPorDefecto(Fecha f) {//¿Porque no podemos poner this.fechaPorDefecto?
		 fechaPorDefecto.setFecha(f.getDia(), f.getMes(), f.getAnio());
	 }
	 
	 public void setFechaAlta(Fecha f) {
		 this.fechaAlta.setFecha(f.getDia(), f.getMes(), f.getAnio());//No se podria hacer this.fechaAlta=f, hay que hacerlo así ya que fechaAlta es final
	 }
	 
	 public Fecha getFechaNac() {
		 return new Fecha(this.fechaNac);
	 }
	 
	 public String getNombre() {
		 return this.nombre;
	 }
	 
	 public void setNombre(String nom) {
		 this.nombre=nom;
	 }
	 
	 public String getNif() {
		 return this.nif;
	 }
	 
	 public int getCodCliente() {
		 return this.codCliente;
	 }
	 
	 public String toString() {//Devuelve una cadena con la información del cliente 
		 return this.nif + " " + this.fechaNac + ": " + this.nombre + " (" + this.codCliente + " - " + this.fechaAlta + ")";
	 }
	 
	 public void ver() {
		 System.out.println(this.nif + " " + this.fechaNac + ": " + this.nombre + " (" + this.codCliente + " - " + this.fechaAlta + ")");
	 }
	 
	 public Object clone() {//ERROR, NO AVANZA EL CONTADOR porque java llama al constructor de copia y al hacer una copia binaria de los datos el contador no avanza por tanto tenemos que crear un objeto nuevo para que asi avance el contador
		 Object obj=null;
			 obj=new Cliente(this);
		 return obj;
	 }
	 
	 public boolean equals(Object obj) {
		 if(obj==this) {
			 return true;
		 }
		 else if(obj==null) {
			 return false;
		 }
		 else if(getClass()!=obj.getClass()) {
			 return false;
		 }
		 else {
			 Cliente c=(Cliente) obj;
			 if(this.nif==c.nif && obj instanceof Cliente) {
				 return true;
		 }
			 else {
				 return false;
			 }
		 }
	 }
	
	public static void main(String[] args) {
	}

}
