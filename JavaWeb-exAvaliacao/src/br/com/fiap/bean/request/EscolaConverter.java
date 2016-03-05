package br.com.fiap.bean.request;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Escola;

@ManagedBean
@FacesConverter(forClass=Escola.class)
public class EscolaConverter implements Converter {

	GenericDao<Escola> escolaDao = new GenericDao<Escola>(Escola.class);

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		  try {
	           return escolaDao.buscar(Long.valueOf(arg2));
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
		return arg2;
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

    // ...
}