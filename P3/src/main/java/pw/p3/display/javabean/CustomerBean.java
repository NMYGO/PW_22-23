package pw.p3.display.javabean;

/**
 * 
 * Clase que implementa un bean de cliente
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class CustomerBean implements java.io.Serializable {

	/* Atributos */
	
	private static final long serialVersionUID = 1L;
	
	private String nombreUser;
	private Integer antiguedadUser;
	private String correoUser = "";
	private String passwordUser = "";
	private Boolean adminUser;
	
	/* Getters y setters */
	
	public String getNombreUser() {
		return nombreUser;
	}
	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}
	public Integer getAntiguedadUser() {
		return antiguedadUser;
	}
	public void setAntiguedadUser(Integer antiguedadUser) {
		this.antiguedadUser = antiguedadUser;
	}
	public String getCorreoUser() {
		return correoUser;
	}
	public void setCorreoUser(String emailUser) {
		this.correoUser = emailUser;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	public Boolean getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(Boolean adminUser) {
		this.adminUser = adminUser;
	}
}
