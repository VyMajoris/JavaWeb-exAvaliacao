package br.com.fiap.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Professor;

@ManagedBean
@ViewScoped
public class ProfessorListaBean {

	private List<Professor> listProfessor;
	private GenericDao<Professor> professorDao;


	private Long idProfessorremover;

	@PostConstruct
	public void init(){
		System.out.println("ProfessorListBean init");
		professorDao = new GenericDao<Professor>(Professor.class);
		setListProfessor(professorDao.listar());
	}




	public String remove(){
		System.out.println("REMOVE " +idProfessorremover);
		professorDao.removeById(idProfessorremover);
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Professor Removido!"));
		listProfessor = professorDao.listar();
		return "lista-professor?faces-redirect=true";

	}

	public List<Professor> getListProfessor() {
		return listProfessor;
	}

	public void setListProfessor(List<Professor> listProfessor) {
		this.listProfessor = listProfessor;
	}
	public Long getIdProfessorremover() {
		return idProfessorremover;
	}



	public void setIdProfessorremover(Long idProfessorremover) {
		this.idProfessorremover = idProfessorremover;
	}




}
