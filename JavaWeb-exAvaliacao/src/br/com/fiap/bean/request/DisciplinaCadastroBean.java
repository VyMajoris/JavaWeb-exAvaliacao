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
import br.com.fiap.entity.Professor;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Disciplina;

@ManagedBean
@RequestScoped
public class DisciplinaCadastroBean {

	Disciplina disciplina;
	private GenericDao<Disciplina> disciplinaDao;
	private GenericDao<Curso> cursoDao;
	private GenericDao<Professor> profDao;
	private List<Curso> listCurso;
	private List<Professor> listProf;


	public List<Professor> getListProf() {
		return listProf;
	}
	public void setListProf(List<Professor> listProf) {
		this.listProf = listProf;
	}
	@PostConstruct
	public void init(){
		System.out.println("Disciplina Bean init");
		disciplina = new Disciplina();
		disciplinaDao = new GenericDao<Disciplina>(Disciplina.class);
		cursoDao = new GenericDao<Curso>(Curso.class);
		profDao = new GenericDao<Professor>(Professor.class);
		listCurso = cursoDao.listar();
		listProf = profDao.listar();
		
	}
	public void cadastrarDisciplina() throws ParseException{
		
		
		System.out.println("PROFESSOR: "+ disciplina.getProfessor());
		 
			disciplinaDao.adicionar(disciplina);
			
			FacesMessage msg = new FacesMessage("Disciplina "+disciplina.getNome()+" cadastrada!");
			disciplina = new Disciplina();
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public String atualizarDisciplina(){
		disciplinaDao.update(disciplina);
		FacesMessage msg = new FacesMessage("Disciplina atualizada!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	
		return "/lista/lista-disciplina";
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
