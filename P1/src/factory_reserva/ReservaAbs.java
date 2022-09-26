package factory_reserva;
import java.util.Date;

public abstract class ReservaAbs {
	
	protected int usuario;
	protected Date fecha;
	protected int duracion;
	protected int pista;
	protected int precio;
	protected int descuento;
	
	
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int user) {
		this.usuario=user;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date date) {
		this.fecha=date;
	}
	
	public int getDur() {
		return duracion;
	}
	public void setDur(int dura) {
		this.duracion=dura;
	}
	
	public int getPista() {
		return pista;
	}
	public void setPista(int pist) {
		this.pista=pist;
	}
	
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int prec) {
		this.precio=prec;
	}
	
	public int getDesc() {
		return descuento;
	}
	public void setDesc(int desc) {
		this.descuento=desc;
	}
}
