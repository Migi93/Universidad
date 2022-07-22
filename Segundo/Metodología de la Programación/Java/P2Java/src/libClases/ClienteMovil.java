package libClases;

public class ClienteMovil extends Cliente {
	private float preciominuto;
	private float minutoshablados;
	private Fecha fpermanencia;
	
	public ClienteMovil(String nif, String nom, Fecha fnac, Fecha fAlta, Fecha perm,  float minh, float prem) {
		super(nif, nom, fnac, fAlta);//En java llamamos asi al constructor de la clase padre para que inicialice los atributos correspondientes
		this.preciominuto=prem;
		this.minutoshablados=minh;
		this.fpermanencia=(Fecha) perm.clone();
	}
	
	public ClienteMovil(String nif, String nom, Fecha fnac, Fecha falta, float minh, float prem) {
		super(nif, nom, fnac, falta);
		this.preciominuto=prem;
		this.minutoshablados=minh;
		this.fpermanencia=new Fecha(falta.getDia(), falta.getMes(), falta.getAnio()+1);
	}
	
	public ClienteMovil(String nif, String nom, Fecha fnac, float minh, float prem) {
		super(nif, nom, fnac, getFechaPorDefecto());
		this.preciominuto=prem;
		this.minutoshablados=minh;
		this.fpermanencia=getFechaPorDefecto();
		this.fpermanencia.setAnio(this.fpermanencia.getAnio()+1);
	}
	
	public ClienteMovil(ClienteMovil c) {
		super(c);
		this.minutoshablados=c.minutoshablados;
		this.preciominuto=c.preciominuto;
		this.fpermanencia=(Fecha)c.fpermanencia.clone();
	}
	
	public Fecha getFPermanencia() {//Como fecha es mutable, hay que devolver una copia, no el original
		return (Fecha) this.fpermanencia.clone();
		//return new Fecha(fpermanencia);
	}
	
	public void setFPermanencia(Fecha f) {
		this.fpermanencia=(Fecha) f.clone();
	}
	
	public float getPrecioMinuto() {
		return this.preciominuto;
	}
	
	public void setPrecioMinutos(float p) {
		this.preciominuto=p;
	}
	
	public float getMinutosHablados() {
		return this.minutoshablados;
	}
	
	public void setMinutosHablados(float m) {
		this.minutoshablados=m;
	}
	
	public float factura() {
		float precio=0;
		precio=this.minutoshablados*this.preciominuto;
		return precio;
	}
	
	public String toString() {
		return this.getNif() + " " + this.getFechaNac() + ": " + this.getNombre() + " (" + this.getCodCliente() + " - " 
			 + this.getFechaAlta() + ") " + this.getFPermanencia() + " " + this.getMinutosHablados() + " x " + this.getPrecioMinuto()
			 + " --> " + this.factura();
	}
	
	/*public boolean equals(Object obj) {
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
			if(obj instanceof ClienteMovil && this.getNif()==((ClienteMovil) obj).getNif()) {
				return true;
			}
			else {
				return false;
			}
		}
	}*/
	
	public boolean equals(Object obj) {//Otra forma de implementar el equals
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
			ClienteMovil cli = (ClienteMovil) obj;
			if(obj instanceof ClienteMovil && cli.getNif()==this.getNif() ){
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public Object clone() {
		Object obj=null;
			obj=new ClienteMovil(this);
			return obj;
	}

	public static void main(String[] args) {
		
	}
}
