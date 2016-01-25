package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.DefaultMapModel;

import br.com.fiap.entity.Escola;

@ManagedBean
@SessionScoped
public class CursoBean {
	
	
	@PostConstruct
	public void init() {
		System.out.println("Curso Bean init");

	}

}
