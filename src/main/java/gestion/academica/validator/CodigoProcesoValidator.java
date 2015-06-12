package gestion.academica.validator;

import gestion.academica.modelo.Proceso;
import gestion.academica.servicio.ProcesoServicio;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
@FacesValidator("codigoValidator")
public class CodigoProcesoValidator implements Validator {
	
	@EJB
	private ProcesoServicio procesoServicio;
	
	public void validate(FacesContext fc, UIComponent c, Object o) throws ValidatorException {
        if (o == null) {
            FacesMessage msg = new FacesMessage("Este campo no puede estar vacío");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
        String codigoProceso = (String)o;
        Proceso proceso = procesoServicio.obtenerPorCodigoProceso(codigoProceso);
         
        if (proceso != null) {
            FacesMessage msg = new FacesMessage("El código de proceso ya existe");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
         
    }

}
