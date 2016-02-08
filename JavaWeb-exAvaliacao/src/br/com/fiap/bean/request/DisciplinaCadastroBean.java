package br.com.fiap.bean.request;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Disciplina;

@ManagedBean
@RequestScoped
public class DisciplinaCadastroBean {

	Disciplina disciplina;
	private GenericDao<Disciplina> disciplinaDao;
	private GenericDao<Curso> cursoDao;
	private List<Curso> listCurso;


	@PostConstruct
	public void init(){
		System.out.println("Disciplina Bean init");
		disciplina = new Disciplina();
		disciplinaDao = new GenericDao<Disciplina>(Disciplina.class);
		cursoDao = new GenericDao<Curso>(Curso.class);
		listCurso = cursoDao.listar();
		
	}
	public void cadastrarDisciplina() throws ParseException{
		
		
		System.out.println("PROFESSOR: "+ disciplina.getProfessor());
			disciplinaDao.adicionar(disciplina);
			disciplina = new Disciplina();
			FacesMessage msg = new FacesMessage("Disciplina cadastrada!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public String atualizarDisciplina(){
		disciplinaDao.update(disciplina);
		FacesMessage msg = new FacesMessage("Disciplina atualizado!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	
		return "lista-disciplina";
	}
	
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Collection<Curso> getListCurso() {
		return listCurso;
	}
	public void setListCurso(List<Curso> listCurso) {
		this.listCurso = listCurso;
	}



}
