package br.com.fiap.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fiap.entity.Disciplina;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = Disciplina.class)
public class DisciplinaConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Disciplina) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Disciplina) {
        	Disciplina entity= (Disciplina) value;
            if (entity != null && entity instanceof Disciplina && entity.getIdDisciplina() != null) {
                uiComponent.getAttributes().put( entity.getIdDisciplina().toString(), entity);
                return entity.getIdDisciplina().toString();
            }
        }
        return "";
    }
}