package br.com.fiap.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fiap.entity.Escola;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = Escola.class)
public class EscolaConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Escola) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Escola) {
        	Escola entity= (Escola) value;
            if (entity != null && entity instanceof Escola && entity.getIdEscola() != null) {
                uiComponent.getAttributes().put( entity.getIdEscola().toString(), entity);
                return entity.getIdEscola().toString();
            }
        }
        return "";
    }
}