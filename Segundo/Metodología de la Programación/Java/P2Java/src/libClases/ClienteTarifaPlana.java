package libClases;

public class ClienteTarifaPlana extends Cliente {
	private String nacionalidad;
	private static int minutosTP=300;
	private static float precioTP=20f;
	private static float excesominTP=0.15f;
	private float minHablados;
	
	public ClienteTarifaPlana(String nif, String nom, Fecha fnac, Fecha fAlta, float minH , String nac) {
		super(nif, nom, fnac, fAlta);
		this.minHablados=minH;
		this.nacionalidad=nac;
	}
	
	public ClienteTarifaPlana(String nif, String nom, Fecha fnac, float minH, String nac) {
		super(nif, nom, fnac);
		this.minHablados=minH;
		this.nacionalidad=nac;
	}
	
	public ClienteTarifaPlana(ClienteTarifaPlana c) {
		super(c);
		this.minHablados=c.minHablados;
		this.nacionalidad=c.nacionalidad;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
	
	public void setNacionalidad(String nac) {
		this.nacionalidad=nac;
	}
	
	public float getMinutos() {
		return this.minHablados;
	}
	
	public void setMinutos(float mH) {
		this.minHablados=mH;
	}
	
	public static int getLimite() {
		return minutosTP;
	}
	
	public static void setLimite(int l) {
		minutosTP=l;
	}
	
	public static float getTarifa() {
		return precioTP;
	}
	
	public static void setTarifa(int minTP, float preTP) {
		minutosTP=minTP;
		precioTP=preTP;
	}
	
	public static float getExceso() {
		return excesominTP;
	}
	
	public static void setExceso(float e) {
		excesominTP=e;
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
		}else {
			ClienteTarifaPlana cli = (ClienteTarifaPlana) obj;
			if(obj instanceof ClienteTarifaPlana && this.getNif()==cli.getNif()) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public Object clone () {
		Object obj=null;
		obj=new ClienteTarifaPlana(this);
		return obj;
	}
	
	public float factura() {
		float precio=0;
		if(minHablados<=minutosTP) {
			precio=precioTP;
		}else {
		precio=(precioTP+((minHablados-minutosTP)*excesominTP));
		}
		return precio;
	}
	
	public void ver() {
		System.out.println(this.getNif() + " " + this.getFechaNac() + ": " + this.getNombre() + " ("
				+ this.getCodCliente() + " - " + this.getFechaAlta() + ") " + this.getNacionalidad()
				+ " [" + getLimite() + " por " + getTarifa() + "] " + getMinutos() + " --> "
				+ this.factura());
	}
	
	public String toString() {
		return this.getNif() + " " + this.getFechaNac() + ": " + this.getNombre() + " ("
				+ this.getCodCliente() + " - " + this.getFechaAlta() + ") " + this.getNacionalidad()
				+ " [" + getLimite() + " por " + getTarifa() + "] " + getMinutos() + " --> "
				+ this.factura();
	}

	public static void main(String[] args) {

	}

}
