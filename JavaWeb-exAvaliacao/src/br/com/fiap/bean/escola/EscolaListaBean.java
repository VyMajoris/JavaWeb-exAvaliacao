package br.com.fiap.bean.escola;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;

@ManagedBean
@ViewScoped
public class EscolaListaBean {

	private List<Escola> listEscola;
	private GenericDao<Escola> escolaDao;
	private Escola escolaremover;

	@PostConstruct
	public void init(){
		System.out.println("EscolaListBean init");
		escolaDao = new GenericDao<Escola>(Escola.class);
		setListEscola(escolaDao.listar());
	}




	

	public String remove(){
		System.out.println("REMOVE " +escolaremover.getNome());
		
		for (Curso curso : escolaremover.getCursos()) {
			curso.setEscola(null);
		}
		
		escolaremover.getCursos().clear();
		escolaDao.remover(escolaremover);
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Escola Removida!"));
		listEscola = escolaDao.listar();
		return "lista-escola?faces-redirect=true";
		
	}

	public List<Escola> getListEscola() {
		return listEscola;
	}

	public void setListEscola(List<Escola> listEscola) {
		this.listEscola = listEscola;
	}




	public Escola getEscolaremover() {
		return escolaremover;
	}




	public void setEscolaremover(Escola escolaremover) {
		this.escolaremover = escolaremover;
	}

}
