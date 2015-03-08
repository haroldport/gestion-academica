package gestion.academica.beans;

import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class RegistroPortalBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean skip;
	private Integer opcion;
	private static final Logger LOGGER = Logger.getLogger(RegistroPortalBean.class.getName());
	
	@PostConstruct
	public void iniciar() {
		try {
			opcion = 0;
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}
	
	public String verRegistroProveedores(){
		return "/faces/paginas/procesos/registroProveedores.xhtml?faces-redirect=true";
	}
	
	public String onFlowProcess(FlowEvent event) {		
        if(skip) {
            skip = false;
            return "paso8";
        }
        else {
        	if(opcion == null){
        		opcion = 0;
        	}
        	if(event.getOldStep().equals("paso1") && (opcion != 1)){
        		RequestContext.getCurrentInstance().execute("PF('dlgRegresar').show();");
        		skip = false;
        		return "paso1";
        	}else{
        		return event.getNewStep();
        	}            
        }
    }
	
	public String regresarLogin(){
		return "/faces/paginas/procesos/principal.xhtml?faces-redirect=true";
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Integer getOpcion() {
		return opcion;
	}

	public void setOpcion(Integer opcion) {
		this.opcion = opcion;
	}
	
}
