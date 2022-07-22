package libClases;
import java.util.Scanner;
//import java.lang.Object;

public class Empresa implements Cloneable {
	
	private Cliente [] cli;
	private int ncli;
	
	public Empresa() {
		this.cli = new Cliente [10];
		this.ncli=0;
	}
	
	public int existe(String nif) {//Te devuelve la posicion del cliente, -1 si no existe
		int pos = -1, i = 0;
		boolean encontrado=false;
		while(i<ncli && !encontrado) {
			if(cli[i].getNif().equals(nif)) {
				encontrado = true;
				pos=i;
			}
			else {
				i++;
			}
		}
	return pos;
	}
	
	
	public int getN() {
		return this.ncli;
	}
	
	public void alta(Cliente c) {//Si el cliente no existe lo creamos y lo introducimos en el array sin pedirle contrato ya que el que te pasan por parametro ya tiene un contrato , si existe no se añade.
		if(cli.length == ncli) {//No hay espacio, hay que aumentar el tamaño de la tabla cli
			int incremento = 5;
			Cliente [] aux = new Cliente [cli.length + incremento];
			for(int i=0;i<ncli;i++) {
				aux[i] = cli[i];
			}
			cli = aux;
			aux = null;
		}
		if(existe(c.getNif()) == -1) {//Lo añadimos porque no se encuentra en la tabla
			cli[ncli] = c;
			ncli++;
		}	
	}
	
	public void alta() {
		String nif, nombre, Nac;
		Fecha fNac, fAl, fPerm;
		float minMes=0f, precioMin=0f;
		int tipoCli=0;
		Scanner n=new Scanner(System.in);
		
		System.out.println("DNI: "); nif=n.nextLine();
		if(existe(nif) != -1) {
			System.out.println("Ya existe un Cliente con ese dni: \n" + cli[existe(nif)]);
		}
		else {
		System.out.println("Nombre: "); nombre = n.nextLine();
		System.out.println("Fecha de Nacimiento: ");
		fNac = Fecha.pedirFecha();
		System.out.println("Fecha de Alta: "); 
		fAl = Fecha.pedirFecha();
		System.out.println("Minutos que habla al mes: "); minMes = n.nextFloat();
		System.out.println("Indique el tipo de cliente (1-Movil, 2-Tarifa Plana): "); tipoCli = n.nextInt();
		if(tipoCli == 1) {
			System.out.println("Precio por minuto: "); precioMin = n.nextFloat();
			System.out.println("Fecha fin permanencia: "); fPerm = Fecha.pedirFecha();
			Cliente c = new ClienteMovil(nif, nombre, fNac,  fAl, fPerm, minMes, precioMin);
			alta(c);
			}else if(tipoCli == 2){
				n.nextLine();//Hay que ponerlo para que no se salte a la siguiente linea y así puedas introducir la nacionalidad del cliente de este tipo de contrato
				System.out.println("Nacionalidad: "); Nac = n.nextLine();
				Cliente c = new ClienteTarifaPlana(nif, nombre, fNac, fAl, minMes, Nac);
				alta(c);
			}
			else {
				System.out.println("El numero introducido no se corresponde con ningun tipo de contrato");
			}
		}
		//n.close();//POR PRECAUCION MEJOR NO CERRARLO, PUEDE DAR PROBLEMAS
	}
	
	public void baja(String nif) {
		if(existe(nif) != -1) {	
			cli[existe(nif)] = cli[ncli-1];
			ncli--;
		}
		if(cli.length>(ncli*2)) {
			Cliente [] aux= new Cliente[cli.length-(cli.length-(ncli*2))];
			for(int i=0;i<ncli;i++) {
				aux[i] = cli[i];
			}
			cli = aux;
			aux = null;
		}
	}
	
	public void baja() {//Metodo equals para que compare el contenido y no el objeto
		Scanner n = new Scanner(System.in);
		String nif, resp;
		System.out.println("Introduce nif cliente a dar de baja: "); nif = n.nextLine(); 
		if(existe(nif) != -1) {
		System.out.println(cli[existe(nif)]);
		System.out.println("¿Seguro que desea eliminarlo (s/n)? "); resp = n.nextLine();
			if(resp.equals("s")) {
				baja(nif);
				System.out.println("El cliente con nif " + nif + " ha sido eliminado");
			}else if(resp.equals("n")){
				System.out.println("El cliente con nif " + nif + " no se elimina");
			}
		}
		else {
			System.out.println("No existe ningun cliente con ese DNI");
		}
	}
	
	public Object clone() {//PREGUNTAR PORQUE ASI SI Y COMO LO TENIA ANTES NO!!
		Empresa obj=null;
		try {
			obj=(Empresa)super.clone();
			obj.cli = cli.clone();
			for(int i=0;i<ncli;i++) {
				obj.cli[i] = (Cliente)cli[i].clone();
			}
		}
		catch(CloneNotSupportedException ex) {
			System.out.println("No se ha podido clonar, es posible que la clase no tenga implementada la interface cloneable");
		}
		return (Object)obj;
	}
	
	
	public String toString() {
		StringBuffer s = new StringBuffer();
		for(int i = 0;i<ncli;i++) {
			s.append(this.cli[i]);
		}
		return s.toString();
	}
	
	/*public String toString() {
		String s = "";
		for(int i=0;i<ncli;i++) {
			s=s+this.cli[i]+"\n";
		}
		return s;
	}*/
	
	public float factura() {
		float fglobal=0f;
			for(int i=0;i<ncli;i++) {
				if(cli[i] instanceof ClienteMovil) {
					fglobal=fglobal+((ClienteMovil)cli[i]).factura();
				}
				else if(cli[i] instanceof ClienteTarifaPlana){
					fglobal=fglobal+((ClienteTarifaPlana)cli[i]).factura();
				}
			}
		return fglobal;
	}
	
	public void descuento(int dto) {
		float preActual=0f;
		for(int i=0;i<ncli;i++) {
			if(cli[i] instanceof ClienteMovil) {
				preActual=((ClienteMovil)cli[i]).getPrecioMinuto();
				((ClienteMovil)cli[i]).setPrecioMinutos(preActual-(preActual*(dto/100.0f)));
			}
		}
	}
	
	/*public void descuento(int dto) {//OTRA FORMA DE HACERLO
		float des=((100-dto)/100.0f);
		for(int i=0;i<ncli;i++) {
			if(cli[i] instanceof ClienteMovil) {
				ClienteMovil c = (ClienteMovil)cli[i];
				c.setPrecioMinutos(c.getPrecioMinuto()*des);
			}
		}
	}*/
	
	public int nClienteMovil() {
		int cantidad = 0;
		for(int i=0;i<ncli;i++) {
			if(cli[i] instanceof ClienteMovil) {
				cantidad++;
			}
		}
		return cantidad;
	}
	
	public void ver() {//METODO NO NECESARIO HECHO POR MI PARA PROBAR COSAS
		for(int i=0; i<ncli;i++) {
			System.out.println(cli[i]);
		}
	}
	
	public void clienteGastoso() {//Mostrara el cliente de cada tipo con mayor factura
		float facturaMayorPlana = 0f;
		float facturaMayorMovil = 0f;
		for(int i=0;i<ncli;i++) {
			if(cli[i] instanceof ClienteTarifaPlana) {
				ClienteTarifaPlana c = (ClienteTarifaPlana) cli[i];
				if(facturaMayorPlana <= c.factura() ) {
					facturaMayorPlana = c.factura();
				}
			}
			else if(cli[i] instanceof ClienteMovil) {
				ClienteMovil c = (ClienteMovil) cli[i];
				if(facturaMayorMovil <= c.factura()) {
					facturaMayorMovil = c.factura();
				}
			}
		}
		System.out.println("Clientes Movil con mayor factura: ");
		for(int j=0;j<ncli;j++) {
			if(cli[j] instanceof ClienteTarifaPlana) {
			ClienteTarifaPlana c = (ClienteTarifaPlana) cli[j];
			if(c.factura() == facturaMayorPlana) {
				System.out.println(cli[j]);
				}
			}
		}
		System.out.println("Clientes Tarifa Plana con mayor factura: ");
		for(int k=0;k<ncli;k++) {
			if(cli[k] instanceof ClienteMovil) {
			ClienteMovil c = (ClienteMovil) cli[k];
			if(c.factura() == facturaMayorMovil) {
				System.out.println(cli[k]);
				}
			}
		}
	}
	
	public static void robaCliente(Empresa a, Empresa b) {//La empresa a roba clientes a b cuando hablen mas de los minutos que tienen contratados
		String nif = null;
		for(int i=0;i<b.ncli;i++) {
			if(b.cli[i] instanceof ClienteTarifaPlana) {
				ClienteTarifaPlana c = (ClienteTarifaPlana) b.cli[i];
				if(c.getMinutos() > c.getLimite()) {
					a.alta(b.cli[i]);
					nif = b.cli[i].getNif();
					b.baja(nif);
				}
			}
		}
	}
	
	public boolean equals(Object obj) {
		boolean devuelto = false;
		if(this == obj) {
			devuelto = true;
		}
		else if(obj == null) {
			devuelto = false;
		}
		else if(getClass() != obj.getClass()) {
			devuelto = false;
		}
		else {
			Empresa e = (Empresa) obj;
			if(this.ncli != e.ncli) {
				devuelto = false;
			}
			else {
				int i = 0;
				int j;
				int contador = 0;
				boolean encontrado;
			for(i=0;i<ncli;i++) {
				encontrado = false;
				j=0;
				while(j<e.ncli && encontrado == false) {
						if(this.cli[i].getNif().equals(e.cli[j].getNif()) == true) {
							encontrado = true;
							contador++;
						}
						else {
							j++;
						}
					}
				if(encontrado == false) {
					devuelto = false;
					}
				}
			if(contador == this.ncli) {
				devuelto = true;
				}
			}
		}
		return devuelto;
	}
	
	public int eliminaCliMenorFactura() {//Elimina clientes movil con factura menor a la media, devolviendo el total de eliminados
		int clientesEli = 0;
		int clientes = 0;
		float media = 0f;
		float totalf = 0f;
			for(int i=0;i<ncli;i++) {
				if(cli[i] instanceof ClienteMovil) {
					ClienteMovil c = (ClienteMovil) cli[i];
					totalf = totalf + c.factura();
					clientes++;
				}
			}
			media = totalf / clientes;
			for(int j=0;j<ncli;j++) {
				if(cli[j] instanceof ClienteMovil) {
					ClienteMovil c = (ClienteMovil) cli[j];
					if(c.factura() < media) {
						baja(cli[j].getNif());
						clientesEli++;
					}
				}
			}
		return clientesEli;
	}
	
	/*public void mostrarCliJoven() {//ALGO DEBE ESTAR OCURRIENDO CON LAS FECHAS
		Fecha f = null;
		for(int i=0;i<ncli;i++) {
				if(f == null || Fecha.mayor(cli[i].getFechaNac(), f) == false) {
				f = cli[i].getFechaNac();
				}
		}
		System.out.println("Clientes mas jovenes: ");
		System.out.println("f vale: " + f);
		for(int j=0;j<ncli;j++) {
			if(f.equals(cli[j].getFechaNac()) == true) {
				System.out.println(cli[j]);
			}
		}
	}*/
	
	public void eliminaCliTplana(String nif, int anio) {
		int i = 0;
		boolean encontrado = false;
		while(i<ncli && encontrado == false) {
			if(cli[i] instanceof ClienteTarifaPlana) {
				ClienteTarifaPlana c = (ClienteTarifaPlana) cli[i];
				if(c.getNif().equals(nif) && c.getFechaAlta().getAnio() == anio) {
					encontrado = true;
					baja(cli[i].getNif());
				}
				else {
					i++;
				}
			}
			else {
				i++;
			}
		}
	}
	
	public void eliminarPeoresTPNacionalidad(String nac) {
		
	}

	public static void main(String[] args) {//PROBANDO COSAS
		Fecha f1=new Fecha(29,2,2022), f2= new Fecha(28,1,2000), f3=new Fecha(29,2,2004);
		 Fecha fnac2 = new Fecha(27,06,1995);
		 Fecha fnac3 = new Fecha(12,06,2005);
		 Fecha fnac4 = new Fecha(12,10,1993);
		 Fecha fnac5 = new Fecha(22,01,2019);
		 Fecha fnac6 = new Fecha(12,12,1993), fnac7 = new Fecha(10,3,1900);
		 System.out.println("Fechas:" + f1 + ", " + f2 + ", " + f3 + ", " + fnac2 + ", " + fnac3 + ", " + fnac4 + ", " + fnac5 + ", " + fnac6 + ", " + fnac7);
		 
		Empresa p = new Empresa();
		Cliente c = new ClienteMovil("547B", "Luis Perez", f1, f1, 15.50f, 0.03f);
		Cliente m = new ClienteMovil("451E", "Miguel Angel", f2,  f3, 50.50f, 0.03f);
		Cliente e = new ClienteTarifaPlana("444R", "Pablo Sanchez", f1, f1, 340.30f, "Jamaica");
		Cliente b = new ClienteTarifaPlana("432X", "Celia Sanchez", fnac2, fnac5, 50.30f, "Francia");
		Cliente f = new ClienteTarifaPlana("112R", "Jose Estevez", fnac3, fnac6, 20.30f, "España");
		Cliente g = new ClienteTarifaPlana("121X", "Cristian Sanyo", fnac4, fnac7, 400.30f, "Argentina");
		p.alta(m);
		p.alta(b);
		p.alta(f);
		p.alta(g);
		p.alta(c);
		p.alta(e);
		System.out.println("---Empresa a ANTES---");
		p.ver();
		System.out.println("---DESPUES---");
	}
}
