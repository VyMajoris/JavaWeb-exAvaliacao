package br.com.fiap.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Disciplina;

@ManagedBean
@ViewScoped
public class DisciplinaListaBean {

	private List<Disciplina> listDisciplina;
	private GenericDao<Disciplina> disciplinaDao;


	private Long idDisciplinaremover;

	@PostConstruct
	public void init(){
		System.out.println("DisciplinaListBean init");
		disciplinaDao = new GenericDao<Disciplina>(Disciplina.class);
		setListDisciplina(disciplinaDao.listar());
	}


	





	public String remove(){
		System.out.println("REMOVE " +idDisciplinaremover);
		disciplinaDao.removeById(idDisciplinaremover);
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Disciplina Removido!"));
		listDisciplina = disciplinaDao.listar();
		return "lista-disciplina?faces-redirect=true";
		
	}

	public List<Disciplina> getListDisciplina() {
		return listDisciplina;
	}

	public void setListDisciplina(List<Disciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}
	public Long getIdDisciplinaremover() {
		return idDisciplinaremover;
	}




	public void setIdDisciplinaremover(Long idDisciplinaremover) {
		this.idDisciplinaremover = idDisciplinaremover;
	}


}
