package br.com.fiap.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Nota;
import br.com.fiap.entity.NotaPK;
import br.com.fiap.entity.Professor;

@ManagedBean
@ViewScoped
public class ProfessorControleListagemBean {

	private GenericDao<Professor> professorDao;
	private List<Aluno> listAluno;
	private List<Disciplina> listDisciplina;
	private GenericDao<Aluno> alunoDao;
	private Professor professor;
	private Long idDisciplina;
	private HttpSession session;
	private Disciplina disciplina;
	private Aluno aluno;

	private Session hSession;

	public List<Disciplina> getListDisciplina() {
		return listDisciplina;
	}
	public void setListDisciplina(List<Disciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}
	@PostConstruct
	public void init(){
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		System.out.println("CONTROLE list BEAN");
		createHsession();
		professorDao = new GenericDao<Professor>(Professor.class);
		disciplina = (Disciplina) session.getAttribute("disciplina");

	}
	
	public void addNota(){
	
		
	}

	public void updateDisciplinaSession(Disciplina disciplina){
		session.setAttribute("disciplina", disciplina);

	}
	@SuppressWarnings("unchecked")
	public List<Aluno> queryAlunosPorProfessor(){
		Query queryAlunosPorProfessor = hSession.getNamedQuery("findAlunoPorProfessor");
		queryAlunosPorProfessor.setLong("rmProfessor", (Long) session.getAttribute("rmProfessor"));
		return listAluno = queryAlunosPorProfessor.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> queryAlunosPorDisciplina(){
		Query queryAlunosPorDisciplina = hSession.getNamedQuery("findAlunoPorDisciplina");
		Disciplina disciplina = (Disciplina) session.getAttribute("disciplina");
		this.disciplina = disciplina;
		queryAlunosPorDisciplina.setLong("idDisciplina", disciplina.getIdDisciplina());
		List<Aluno> listaluno = queryAlunosPorDisciplina.list();
		return this.listAluno = listaluno;
	}


	public void buscaProfessor(Long rmProfessor){
		System.out.println("PROFESSOR CONTROLE "+rmProfessor);
		professor = professorDao.buscar(rmProfessor);
		System.out.println("PROFESSOR CONTROLE "+professor);
	}
	private void createHsession(){
		hSession = JpaUtil.getHibSession();

	}












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
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}



}
