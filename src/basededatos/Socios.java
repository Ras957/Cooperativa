package basededatos;

public class Socios {

	private int codigoSocio;
	private String nombreSocio;
	private String apellidosSocio;
	private String direccionSocio;
	private String emailSocio;
	private String telefonoSocio;
	
	
	
	
	public Socios(int codigoSocio, String nombreSocio, String apellidosSocio,
			String direccionSocio, String emailSocio, String telefonoSocio) {
		super();
		this.codigoSocio = codigoSocio;
		this.nombreSocio = nombreSocio;
		this.apellidosSocio = apellidosSocio;
		this.direccionSocio = direccionSocio;
		this.emailSocio = emailSocio;
		this.telefonoSocio = telefonoSocio;
	}
	
	public int getCodigoSocio() {
		return codigoSocio;
	}
	public void setCodigoSocio(int codigoSocio) {
		this.codigoSocio = codigoSocio;
	}
	public String getNombreSocio() {
		return nombreSocio;
	}
	public void setNombreSocio(String nombreSocio) {
		this.nombreSocio = nombreSocio;
	}
	public String getApellidosSocio() {
		return apellidosSocio;
	}
	public void setApellidosSocio(String apellidosSocio) {
		this.apellidosSocio = apellidosSocio;
	}
	public String getDireccionSocio() {
		return direccionSocio;
	}
	public void setDireccionSocio(String direccionSocio) {
		this.direccionSocio = direccionSocio;
	}
	public String getEmailSocio() {
		return emailSocio;
	}
	public void setEmailSocio(String emailSocio) {
		this.emailSocio = emailSocio;
	}
	public String getTelefonoSocio() {
		return telefonoSocio;
	}
	public void setTelefonoSocio(String telefonoSocio) {
		this.telefonoSocio = telefonoSocio;
	}
}
