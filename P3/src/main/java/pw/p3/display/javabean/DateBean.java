package pw.p3.display.javabean;

public class DateBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String inicio;
	private String fin;
	
	
	public String getInicio() {
		return inicio;
	}
	public void setInicior(String init) {
		this.inicio = init;
	}
	public String getFinal() {
		return fin;
	}
	public void setFinal(String fina) {
		this.fin = fina;
	}
}
