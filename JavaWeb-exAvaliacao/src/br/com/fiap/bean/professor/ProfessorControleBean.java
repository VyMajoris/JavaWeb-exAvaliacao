package br.com.fiap.bean.professor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Professor;

@ManagedBean
@RequestScoped
public class ProfessorControleBean {

	private GenericDao<Professor> professorDao;
	private List<Aluno> listAluno;
	private List<Disciplina> listDisciplina;
	private GenericDao<Aluno> alunoDao;
	private Professor professor;
	private HttpSession session;
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
		professorDao = new GenericDao<Professor>(Professor.class);
		professor = professorDao.buscar( (Long) session.getAttribute("rmProfessor"));
	}

	public List<Curso> queryfindCursoPorProfessor(){
		Query findCursoPorProfessor =  JpaUtil.getHibSession().getNamedQuery("findCursoPorProfessor");
		findCursoPorProfessor.setLong("rmProfessor", (Long) session.getAttribute("rmProfessor"));
		List<Curso> listacurso = findCursoPorProfessor.list();
		return this.setListaCurso(listacurso);
	}

	public List<Disciplina> disciplinaPorCursoPorProfessor(Long idCurso){
		List<Disciplina> listDisciplina = new ArrayList<Disciplina>();
		for (Disciplina disc : professor.getDisciplinas()) {
				if (idCurso == disc.getCurso().getId()) {
					listDisciplina.add(disc);
				}
		}
		return listDisciplina;
	}


	
	//
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
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