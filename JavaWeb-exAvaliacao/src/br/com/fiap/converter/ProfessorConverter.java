package br.com.fiap.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fiap.entity.Professor;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Professor) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Professor) {
        	Professor entity= (Professor) value;
            if (entity != null && entity instanceof Professor && entity.getRmProfessor() != null) {
                uiComponent.getAttributes().put( entity.getRmProfessor().toString(), entity);
                return entity.getRmProfessor().toString();
            }
        }
        return "";
    }
}