package br.com.fiap.bean.professor;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Professor;

@ManagedBean
@ViewScoped
public class ProfessorListaBean {

	private List<Professor> listProfessor;
	private GenericDao<Professor> professorDao;


	public Professor getProfessorRemover() {
		return professorRemover;
	}

	public void setProfessorRemover(Professor professorRemover) {
		this.professorRemover = professorRemover;
	}

	private Professor professorRemover;

	@PostConstruct
	public void init(){
		System.out.println("ProfessorListBean init");
		professorDao = new GenericDao<Professor>(Professor.class);
		setListProfessor(professorDao.listar());


		for (Professor pl : listProfessor) {
			for (Disciplina d : pl.getDisciplinas()) {
				System.out.println("Disciplina: " + d.getNome());

			}

		}
	}

	public String remove(){
		
		for (Disciplina disciplina : professorRemover.getDisciplinas()) {
			disciplina.setProfessor(null);
		}
		
		professorDao.removeById(professorRemover.getId());
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




}
