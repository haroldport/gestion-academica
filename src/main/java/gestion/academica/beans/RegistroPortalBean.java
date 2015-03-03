package gestion.academica.beans;

import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class RegistroPortalBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String verRegistroProveedores(){
		return "/faces/paginas/procesos/registroProveedores.xhtml?faces-redirect=true";
	}
	

}
