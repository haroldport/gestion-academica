package gestion.academica.beans;

import gestion.academica.modelo.Proveedor;
import gestion.academica.servicio.ProveedorServicio;
import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class LoguinIncopBean extends Utilitario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProveedorServicio proveedorServicio;
	
	private Proveedor proveedorLogueado;
	private String ruc;
	private String username;
	private String clave;
	
	@PostConstruct
	public void iniciar() {
		try {
			iniciarVariables();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void iniciarVariables(){
		setProveedorLogueado(new Proveedor());
		setRuc("");
		setUsername("");
		setClave("");
	}
	
	public String verRegistroProveedores(){
		return "/faces/paginas/procesos/registroProveedores.xhtml?faces-redirect=true";
	}
	
	public String ingresoPortal(){
		proveedorLogueado = proveedorServicio.obtenerPorRucUserYClave(ruc, username, clave);
		if(proveedorLogueado != null){
			return "/faces/paginas/procesos/principalProveedores.xhtml?faces-redirect=true";
		}
		return "";
	}
	
	public String regresarLogin(){
		iniciarVariables();
		return "/faces/paginas/procesos/principal.xhtml?faces-redirect=true";
	}

	public Proveedor getProveedorLogueado() {
		return proveedorLogueado;
	}

	public void setProveedorLogueado(Proveedor proveedorLogueado) {
		this.proveedorLogueado = proveedorLogueado;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
