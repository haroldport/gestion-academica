package gestion.academica.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("fechaValidator")
public class FechaPublicacionValidator implements Validator {
	
	public void validate(FacesContext fc, UIComponent c, Object o) throws ValidatorException {
        if (o == null) {
            FacesMessage msg = new FacesMessage("Este campo no puede estar vacío");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
        Date fechaPublicacion = (Date)o;
         
        if (fechaPublicacion.before(new Date())) {
            FacesMessage msg = new FacesMessage("La fecha de publicación debe ser posterior a la fecha actual");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
         
    }

}
