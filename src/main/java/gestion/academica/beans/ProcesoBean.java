package gestion.academica.beans;

import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class ProcesoBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean skip;
	
	public String onFlowProcess(FlowEvent event) {		
        if(skip) {
            skip = false;
            return "paso8";
        }
        else {
        	return event.getNewStep();
        }
    }

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	

}
