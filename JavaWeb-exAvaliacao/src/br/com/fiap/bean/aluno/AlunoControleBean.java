package br.com.fiap.bean.aluno;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Disciplina;

@ManagedBean
@RequestScoped
public class AlunoControleBean {

	
	private List<Aluno> listAluno;
	private List<Disciplina> listDisciplina;
	private GenericDao<Aluno> alunoDao;
	private Aluno aluno;
	HttpSession session;
	private List<Curso> listaCurso;

	public List<Disciplina> getListDisciplina() {
		return listDisciplina;
	}
	public void setListDisciplina(List<Disciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}
	@PostConstruct
	public void init(){
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		alunoDao = new GenericDao<Aluno>(Aluno.class);
		aluno = alunoDao.buscar( (Long) session.getAttribute("idAluno"));
	}	
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getListAluno() {
		return listAluno;
	}
	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = listAluno;
	}
	public GenericDao<Aluno> getAlunoDao() {
		return alunoDao;
	}
	public void setAlunoDao(GenericDao<Aluno> alunoDao) {
		this.alunoDao = alunoDao;
	}
	public List<Curso> getListaCurso() {
		return listaCurso;
	}
	public List<Curso> setListaCurso(List<Curso> listaCurso) {
		this.listaCurso = listaCurso;
		return listaCurso;
	}



}
