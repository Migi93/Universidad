package libClases;

import java.util.Scanner;
import java.lang.Object;

public final class Fecha  implements Cloneable, Proceso {/*Debe ser mutable, es decir, se pueden modificar los atributos privados de la clase
	  1.Declaramos la clase como final(constante) para prevenir que se puedan definir subclases para esta clase, e impedir que cualquiera pueda llamar al metodo clone desde dentro de la clase.
	  2.Le ponemos implements Cloneable debido a que la clase nuestro proposito es indicar al metodo clone de java.lang.Object que hemos dado permiso a la clase para permitir que los objetos 
	  instanciados a partir de esta puedan ser clonados.
	  3.Tambien le ponemos implements Proceso ya que es una interfaz que hemos de crear y en java si creamos una interfaz y se la ponemos a una clase, estamos obligados a que esa clase tenga los metodos 
	  de esa interfaz en la clase que decidamos poner el implements Proceso*/
	private int dia, mes, anio;
 
	public Fecha(int d, int m, int a) {
		this.setFecha(d,m,a);
	}
	
	public Fecha(Fecha f1) {
		this.setFecha(f1.getDia(), f1.getMes(), f1.getAnio());
	}
	
	public void setFecha(int d, int m, int a) {
		int diaMes[]= {0,31,28,31,30,31,30,31,31,30,31,30,31};
		
		this.dia=d;
		this.mes=m;
		this.anio=a;
		
		if(m<1){
			m=1;
			this.mes=m;
		}
		if(m>12){
			m=12;
			this.mes=m;
		}
		if(d<1){
			d=1;
			this.dia=d;
		}
		if(this.bisiesto()==true){
			diaMes[2]=29;
		}
		if(d>diaMes[m]){
			d=diaMes[m];
			this.dia=d;
		}
	}
	
	public boolean bisiesto() {
		boolean bisiesto=false;
		if((this.anio%400)==0) {//Si el año es divisible entre 400 siempre es bisiesto y por tanto tiene 366 dias
			bisiesto=true;
		}
		else if((this.anio%4)==0 && (this.anio%100)!=0) {//Si el año es divisible entre 4 y no entre 100, tambien es bisiesto y tiene 366 dias
			bisiesto=true;
		}
		return bisiesto;
	}
	
	public int getDia() {
		 return this.dia;
	}
	 
	public int getMes() {
		return this.mes;
	}
	
	public int getAnio() {
		return this.anio;
	}
	
	public void setDia(int d) {
		this.dia=d;
	}
	
	public void setMes(int m) {
		this.mes=m;
	}

	public void setAnio(int a) {
		this.anio=a;
	}
	
	public static boolean mayor(Fecha f1, Fecha f2) {//Devuelve true si la Fecha f1 es mayor que la f2, en caso contratio devuelve false
		/*if(f1.getAnio()>f2.getAnio()) {
			return true;
		}
		else if(f1.getAnio()==f2.getAnio() && f1.getMes()>f2.getMes()) {
			return true;
		}
		else if(f1.getAnio()==f2.getAnio() && f1.getMes()==f2.getMes() && f1.getDia()>f2.getDia()) {
			return true;
		}	
		else {
			return false;
		}	*/
		if (f1.anio*10000+f1.mes*100+f1.dia>f2.anio*10000+f2.mes*100+f2.dia)
			return true;
			else
			return false;
	}
	
	public static Fecha pedirFecha() {//El método es static debido a que en el main llama a este metodo con el nombre de la clase, es decir, Fecha.pedirFecha ///CORREGIR (El error esta en que tenemos que crear una condicional de si el año es bisiesto en este metodo, porque el constructor me lo corrige al llamar al setfecha)
		boolean fechacorrecta=false;
		int d, m, a;
		Fecha f=null;
		do {
			Scanner n = new Scanner(System.in);
		System.out.print("Introduce Fecha (dd/mm/aaaa): "); d = n.nextInt(); m = n.nextInt(); a = n.nextInt();
		f=new Fecha(d, m, a);
			if((a%400!=0 && d==29 && m==2)) {
				System.out.println("Fecha no valida");
			}
			else if(a%4!=0 && a%100==0 && d==29 && m==2) {
				System.out.println("Fecha no valida");
			}
			else {
				fechacorrecta=true;
				//n.close();//NO PUEDO CERRAR LA CLASE Scanner YA QUE ESTE METODO SE USARA EN OTRA CLASE Y AL CERRARLA NOS DA ERROR EN EL MAIN YA QUE SI LA CERRAMOS NO SE PUEDE LEER LO QUE INTRODUZCAMOS EN LA CLASE Empresa.
			}
		} while(fechacorrecta!=true);
			return f;
	}
	
	public String toString() {
		String s;
		s="0";
		if(this.dia<10 && this.mes<10) {
			return  s + this.dia + "/" + s + this.mes + "/" + this.anio;
		}
		else if(this.dia<10) {
			return s + this.dia + "/" + this.mes + "/" + this.anio;
		}
		else if(this.mes<10) {
			return  this.dia + "/" + s + this.mes + "/" + this.anio;
		}
		else
			return  this.dia + "/" + this.mes + "/" +this.anio;
	}
	
	public Fecha diaSig() {
		Fecha f= (Fecha) clone();//Casting
		//Fecha f= new Fecha(this.dia, this.mes, this.anio);//Otra forma de hacerlo
		//Fecha f=new Fecha(this);//Otra forma de hacerlo
		boolean bisiesto=false;
		if((this.anio%400)==0) {//Si el año es divisible entre 400 siempre es bisiesto y por tanto tiene 366 dias
			bisiesto=true;
		}
		else if((this.anio%4)==0 && (this.anio%100)!=0) {//Si el año es divisible entre 4 y no entre 100, tambien es bisiesto y tiene 366 dias
			bisiesto=true;
		}
		if((this.dia==31 && this.mes!=12) || (this.dia==30 && (this.mes==4 || this.mes==6 || this.mes==9 || this.mes==11)) || (this.mes==2 && bisiesto==false)) {
			f.dia=1;
			f.mes++;
		}
		else if(this.dia==31 && this.mes==12) {
			f.dia=1;
			f.mes=1;
			f.anio++;
		}
		else {
			f.dia++;
		}
		return f;
	}
	
	public Object clone() {//Metodo que sirve para crear y devolver una copia de un objeto ///IMPLEMENTAR
		Object obj=null;
		try {
			obj=super.clone();/*Lo que hacemos en este caso es llamar al metodo clone de la clase Object que tiene ya de por si implementada Java, lo cual lo que hace es
			 esto es una copia binaria de los atributos(dia, mes y anio) de ese objeto*/
		}
		catch(CloneNotSupportedException ex) {//En el caso de que se produzca la excepcion CloneNotSupportedException(Excepción que se lanza cuando intentamos hacer un clone de un objeto cuya clase no tiene implementado el interface Cloneable) se lanzara el catch con el siguiente mensaje
			System.out.println("No se ha podido clonar, es posible que la clase no tenga implementada la interface cloneable");
		}
		return obj;
	}
	
	public boolean equals(Object obj) {//Compara dos objetos y devuelve true si son iguales
		/*if(this==obj) {//Si los apuntan a la misma direccion de memoria, son iguales
			return true;
		}
		else if(obj==null) {//Si el objeto obj no apunta nada, no pueden ser igual, por tanto devolvera false
			return false;
		}
		else if(getClass()!=obj.getClass()) {//Si la clase obj es distinta a esta clase, no pueden ser iguales, por tanto tambien devolvemos false
			return false;
		}
		else {//Por ultimo hacemos un casting explicito (convertimos el objeto obj a tipo Fecha para poder compararlo)
			Fecha f = (Fecha) obj;//casting explicito
			if(this.dia==f.dia && this.mes==f.mes && this.anio==f.anio) {//Despues del casting, podemos comparar ambos objetos (El de esta clase y el objeto que usamos para comparar) y ver si son o no iguales
			return true;
			}
			else
			return false;
		}*/
		if (this == obj) return true; //si apuntan al mismo sitio son iguales
		if (obj == null) return false;
		if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
		return false; // si los 2 no son de la misma clase no son iguales
		Fecha c = (Fecha) obj;
		return (dia==c.dia && mes==c.mes && anio==c.anio);

	}
	
	public void ver() {
		if(this.dia<10 && this.mes<10) {
			System.out.println("0" + this.dia + "/0" + this.mes + "/" + this.anio);
		}
		else if(this.dia<10 && this.mes>=10) {
			System.out.println("0" + this.dia + "/" + this.mes + "/" + this.anio);
		}
		else if(this.dia>10 && this.mes<10) {
			System.out.println(this.dia + "/0" + this.mes + "/" + this.anio);
		}
		else {	
		System.out.println(this.dia + "/" + this.mes + "/" + this.anio);
		}
	}

	public static void main(String[] args) {    
		 Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1), f3 = new Fecha(29,2,2004);
		 final Fecha f4=new Fecha(05,12,2023); //es constante la referencia f4
		 System.out.print("Fechas: " + f1.toString() + ", "+f2+ ", " +f3+ ", " +f4+ "\n");
		 f1=new Fecha(31,12,2016); //31/12/2016
		 f4.setFecha(28, 2, 2008);             //pero no es constante el objeto al que apunta
		 System.out.println(f1 +" "+ f2.toString() +" " + f3 +" "+ f4+" "+ f1);
		 f1=new Fecha(f4.getDia()-10, f4.getMes(), f4.getAnio()-7); //f1=18/02/2001
		 f3=Fecha.pedirFecha(); //pide una fecha por teclado
		 if (f3.bisiesto() && Fecha.mayor(f2,f1))
		 System.out.print("El " + f3.getAnio() + " fue bisiesto, " + f1 + ", " + f3);
	}
}

