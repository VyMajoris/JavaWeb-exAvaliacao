package br.com.fiap.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("br.com.fiap.helpers.NotaValidator")
public class NotaValidator implements Validator{

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Double nota = null;
		try {
			nota = (Double) value;
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,(String) component.getAttributes().get("label"), "Insira somente números!");
			throw new ValidatorException(msg);
		}

		if (nota != null && (nota <0 || nota >10)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,(String) component.getAttributes().get("label"), "A nota deve ser entre 0 e 10");
			throw new ValidatorException(msg);

		}
	}
}