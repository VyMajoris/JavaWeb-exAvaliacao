package br.com.fiap.bean.view;

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
public class CursoListaBean {

	private List<Curso> listCurso;
	private GenericDao<Curso> cursoDao;
	private int idCursoRemover;



	@PostConstruct
	public void init(){
		cursoDao = new  GenericDao<Curso>(Curso.class);

		listCurso = cursoDao.listar();

	}
	public String remove(){
		System.out.println("REMOVE " +idCursoRemover);
		cursoDao.removeById(idCursoRemover);
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Curso Removido!"));
		listCurso = cursoDao.listar();
		return "lista-curso?faces-redirect=true";

	}
	
	
	
	public int getIdCursoRemover() {
		return idCursoRemover;
	}
	public void setIdCursoRemover(int idCursoRemover) {
		this.idCursoRemover = idCursoRemover;
	}
	public List<Curso> getListCurso() {
		return listCurso;
	}
	public void setListCurso(List<Curso> listCurso) {
		this.listCurso = listCurso;
	}

}
