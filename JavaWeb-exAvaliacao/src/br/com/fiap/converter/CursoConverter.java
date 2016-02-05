package br.com.fiap.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fiap.entity.Curso;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = Curso.class)
public class CursoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Curso) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Curso) {
        	Curso entity= (Curso) value;
            if (entity != null && entity instanceof Curso && entity.getIdCurso() != null) {
                uiComponent.getAttributes().put( entity.getIdCurso().toString(), entity);
                return entity.getIdCurso().toString();
            }
        }
        return "";
    }
}