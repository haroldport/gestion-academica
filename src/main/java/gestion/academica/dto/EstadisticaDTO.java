package gestion.academica.dto;

public class EstadisticaDTO {
	
	private int numeroRegistro;
	private String nombreCampo;
	private String mensaje;
	
	public EstadisticaDTO(){}
	
	public EstadisticaDTO(int numeroRegistro, String nombreCampo, String mensaje) {
		this.numeroRegistro = numeroRegistro;
		this.nombreCampo = nombreCampo;
		this.mensaje = mensaje;
	}
	
	public int getNumeroRegistro() {
		return numeroRegistro;
	}
	public void setNumeroRegistro(int numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	public String getNombreCampo() {
		return nombreCampo;
	}
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "EstadisticaDTO [numeroRegistro=" + numeroRegistro
				+ ", nombreCampo=" + nombreCampo + ", mensaje=" + mensaje + "]";
	}
	
}
