package br.com.fiap.bean.request;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Professor;
import br.com.fiap.entity.Curso;


@ManagedBean
@ViewScoped
public class DisciplinaCadastroBean {

	private Disciplina disciplina;
	private GenericDao<Disciplina> disciplinaDao;
	private GenericDao<Curso> cursoDao;
	private GenericDao<Professor> profDao;
	private List<Curso> listCurso;
	private List<Professor> listProf;
	private Long idDisciplina;

	public List<Professor> getListProf() {
		return listProf;
	}
	public void setListProf(List<Professor> listProf) {
		this.listProf = listProf;
	}

	public void init() throws IOException{
		System.out.println("Disciplina Bean init");
		disciplinaDao = new GenericDao<Disciplina>(Disciplina.class);
		cursoDao = new GenericDao<Curso>(Curso.class);
		profDao = new GenericDao<Professor>(Professor.class);
		listCurso = cursoDao.listar();
		listProf = profDao.listar();

		if (idDisciplina != null) {
			disciplina = disciplinaDao.buscar(idDisciplina);
		}else{
			disciplina = new Disciplina();
		}
		if (disciplina == null && idDisciplina != null) {
			String message = "Bad request. Unknown user.";
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println(ec.getRequestContextPath());
			ec.invalidateSession();
			ec.redirect(ec.getRequestContextPath());
		}


	}
	public void cadastrarDisciplina() throws ParseException{
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
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}



}
