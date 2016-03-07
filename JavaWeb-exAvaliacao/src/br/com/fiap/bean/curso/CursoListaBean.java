package br.com.fiap.bean.curso;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Disciplina;

@ManagedBean
@ViewScoped
public class CursoListaBean {

	private List<Curso> listCurso;
	private GenericDao<Curso> cursoDao;
	private Curso cursoRemover;

	@PostConstruct
	public void init(){
		cursoDao = new  GenericDao<Curso>(Curso.class);
		listCurso = cursoDao.listar();

	}
	public String remove(){
		if (cursoRemover.getEscola() !=null) {
			cursoRemover.getEscola().getCursos().remove(cursoRemover);
			cursoRemover.setEscola(null);
		}
		for (Disciplina disciplina : cursoRemover.getDisciplinas()) {
			disciplina.setCurso(null);
		}
		
		cursoDao.removeById(cursoRemover.getId());
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Curso Removido!"));
		listCurso = cursoDao.listar();
		return "lista-curso?faces-redirect=true";
	}

	public Curso getCursoRemover() {
		return cursoRemover;
	}
	public void setCursoRemover(Curso cursoRemover) {
		this.cursoRemover = cursoRemover;
	}
	public List<Curso> getListCurso() {
		return listCurso;
	}
	public void setListCurso(List<Curso> listCurso) {
		this.listCurso = listCurso;
	}

}
