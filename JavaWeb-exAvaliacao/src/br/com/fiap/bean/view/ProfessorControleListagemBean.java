package br.com.fiap.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Nota;
import br.com.fiap.entity.NotaPK;
import br.com.fiap.entity.Professor;

import br.com.fiap.entity.TipoNotaEnum;

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
	private Nota notaP1;
	private Nota notaAtvd;
	private Nota notaP2;
	private GenericDao<Nota> notaDao;


	@PostConstruct
	public void init(){
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		System.out.println("CONTROLE list BEAN");
		professorDao = new GenericDao<Professor>(Professor.class);
		disciplina = (Disciplina) session.getAttribute("disciplina");
		notaDao = new GenericDao<Nota>(Nota.class);

	}

	public void addNota(){
		System.out.println("Add nota");
		NotaPK notaPK = new NotaPK();
		notaPK.setAluno(aluno);
		notaPK.setDisciplina(disciplina);
		notaPK.setTipo(TipoNotaEnum.PROJETO_1);
		notaP1.setNotapk(notaPK);
		notaDao.saveOrUpdate(notaP1);

		notaPK = new NotaPK();
		notaPK.setAluno(aluno);
		notaPK.setDisciplina(disciplina);
		notaPK.setTipo(TipoNotaEnum.ATIVIDADE_PRATICA);
		notaAtvd.setNotapk(notaPK);
		notaDao.saveOrUpdate(notaAtvd);

		notaPK = new NotaPK();
		notaPK.setAluno(aluno);
		notaPK.setDisciplina(disciplina);
		notaPK.setTipo(TipoNotaEnum.PROJETO_2);
		notaP2.setNotapk(notaPK);
		notaDao.update(notaP2);
	}

	public void prepNota(Aluno aluno){
		System.out.println("Prep nota");
		System.out.println(aluno.getNome());
		Query findNotaPorAlunoEDisciplina = JpaUtil.getHibSession().getNamedQuery("findNotaPorAlunoEDisciplina");
		findNotaPorAlunoEDisciplina.setLong("idAluno", (Long) aluno.getId());
		findNotaPorAlunoEDisciplina.setLong("idDisciplina", disciplina.getId());

		findNotaPorAlunoEDisciplina.setParameter("tipo", TipoNotaEnum.PROJETO_1);
		if ((Nota) findNotaPorAlunoEDisciplina.uniqueResult() != null ) {
			notaP1 = (Nota) findNotaPorAlunoEDisciplina.uniqueResult();
		}else{
			notaP1 = new Nota();
		}

		findNotaPorAlunoEDisciplina.setParameter("tipo", TipoNotaEnum.PROJETO_2);
		if ((Nota) findNotaPorAlunoEDisciplina.uniqueResult()  != null ) {
			notaP2 = (Nota) findNotaPorAlunoEDisciplina.uniqueResult();
		}else{
			notaP2 = new Nota();
		}

		findNotaPorAlunoEDisciplina.setParameter("tipo", TipoNotaEnum.ATIVIDADE_PRATICA);
		if ((Nota) findNotaPorAlunoEDisciplina.uniqueResult()  != null ) {
			notaAtvd = (Nota) findNotaPorAlunoEDisciplina.uniqueResult();
		}else{
			notaAtvd = new Nota();
		}
	}

	public void updateDisciplinaSession(Disciplina disciplina){
		session.setAttribute("disciplina", disciplina);
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> queryAlunosPorProfessor(){
		Query queryAlunosPorProfessor = JpaUtil.getHibSession().getNamedQuery("findAlunoPorProfessor");
		queryAlunosPorProfessor.setLong("rmProfessor", (Long) session.getAttribute("rmProfessor"));
		return listAluno = queryAlunosPorProfessor.list();
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> queryAlunosPorDisciplina(){
		Query queryAlunosPorDisciplina = JpaUtil.getHibSession().getNamedQuery("findAlunoPorDisciplina");
		Disciplina disciplina = (Disciplina) session.getAttribute("disciplina");
		this.disciplina = disciplina;
		queryAlunosPorDisciplina.setLong("idDisciplina", disciplina.getId());
		List<Aluno> listaluno = queryAlunosPorDisciplina.list();
		return this.listAluno = listaluno;
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
	public Nota getNotaAtvd() {
		return notaAtvd;
	}
	public void setNotaAtvd(Nota notaAtvd) {
		this.notaAtvd = notaAtvd;
	}
	public Nota getNotaP1() {
		return notaP1;
	}
	public void setNotaP1(Nota notaP1) {
		this.notaP1 = notaP1;
	}
	public Nota getNotaP2() {
		return notaP2;
	}
	public void setNotaP2(Nota notaP2) {
		this.notaP2 = notaP2;
	}
	public GenericDao<Nota> getNotaDao() {
		return notaDao;
	}
	public void setNotaDao(GenericDao<Nota> notaDao) {
		this.notaDao = notaDao;
	}
	public GenericDao<Professor> getProfessorDao() {
		return professorDao;
	}
	public void setProfessorDao(GenericDao<Professor> professorDao) {
		this.professorDao = professorDao;
	}
	public List<Disciplina> getListDisciplina() {
		return listDisciplina;
	}
	public void setListDisciplina(List<Disciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}


}
