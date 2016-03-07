package br.com.fiap.converter;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class SimpleEntityConverter implements Converter {  
  
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {  
        if (value != null) {  
            return this.getAttributesFrom(component).get(value);  
        }  
        return null;  
    }  
  
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
        if (value != null  
                && !"".equals(value)) {  
            Identifiable entity = (Identifiable) value;
            this.addAttribute(component, entity);  
            Long codigo = entity.getId();  
            if (codigo != null) {  
                return String.valueOf(codigo);  
            }  
        }  
  
        return (String) value;  
    }  

    protected void addAttribute(UIComponent component, Identifiable o) {  
        String key = o.getId().toString(); 
        System.out.println("ID CONVERTER = " +key);
        this.getAttributesFrom(component).put(key, o);  
    }  
  
    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
        return component.getAttributes();  
    }  
  
}