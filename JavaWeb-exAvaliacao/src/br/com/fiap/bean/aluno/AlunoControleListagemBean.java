package br.com.fiap.bean.aluno;

import java.util.ArrayList;
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
import br.com.fiap.entity.Professor;
import br.com.fiap.entity.TipoNotaEnum;
import br.com.fiap.helpers.DisciplinaComNota;

@ManagedBean
@RequestScoped
public class AlunoControleListagemBean {


	private List<Disciplina> listDisciplina;
	private List<DisciplinaComNota> listDisciplinaComNota;

	private GenericDao<Aluno> alunoDao;
	private Long idDisciplina;
	private HttpSession session;
	private Disciplina disciplina;
	private Aluno aluno;
	private Nota notaP1;
	private Nota notaAtvd;
	private Nota notaP2;

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
		aluno = alunoDao.buscar((Long) session.getAttribute("rmAluno"));
		listDisciplina = aluno.getCurso().getDisciplinas();
		listDisciplinaComNota = new ArrayList<DisciplinaComNota>();
		prepNota();

	}

	public void prepNota(){
		DisciplinaComNota dcm;
		for (Disciplina disc : listDisciplina) {
			dcm = new DisciplinaComNota();
			dcm.setNome(disc.getNome());
			
			Query findNotaPorAlunoEDisciplina = JpaUtil.getHibSession().getNamedQuery("findNotaPorAlunoEDisciplina");
			findNotaPorAlunoEDisciplina.setLong("idAluno", (Long) aluno.getId());
			findNotaPorAlunoEDisciplina.setLong("idDisciplina",disc.getId());
			
			findNotaPorAlunoEDisciplina.setParameter("tipo", TipoNotaEnum.PROJETO_1);
			notaP1 = ((Nota) findNotaPorAlunoEDisciplina.uniqueResult());
			if (notaP1== null) {
				notaP1 = new Nota();
			}
			dcm.setNotaP1(notaP1);
			
			findNotaPorAlunoEDisciplina.setParameter("tipo", TipoNotaEnum.PROJETO_2);
			notaP2 = (Nota) findNotaPorAlunoEDisciplina.uniqueResult();
			if (notaP2== null) {
				notaP2 = new Nota();
			}
			dcm.setNotaP2(notaP2);
			
			findNotaPorAlunoEDisciplina.setParameter("tipo", TipoNotaEnum.ATIVIDADE_PRATICA);
			notaAtvd =(Nota) findNotaPorAlunoEDisciplina.uniqueResult();
			if (notaAtvd== null) {
				notaAtvd = new Nota();
			}
			dcm.setNotaAtvd(notaAtvd);
			listDisciplinaComNota.add(dcm);
		}
	}


	public void updateDisciplinaSession(Disciplina disciplina){
		session.setAttribute("disciplina", disciplina);
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
	public Nota getNotaP1() {
		return notaP1;
	}
	public void setNotaP1(Nota notaP1) {
		this.notaP1 = notaP1;
	}
	public Nota getNotaAtvd() {
		return notaAtvd;
	}
	public void setNotaAtvd(Nota notaAtvd) {
		this.notaAtvd = notaAtvd;
	}
	public Nota getNotaP2() {
		return notaP2;
	}
	public void setNotaP2(Nota notaP2) {
		this.notaP2 = notaP2;
	}
	public List<DisciplinaComNota> getListDisciplinaComNota() {
		return listDisciplinaComNota;
	}
	public void setListDisciplinaComNota(List<DisciplinaComNota> listDisciplinaComNota) {
		this.listDisciplinaComNota = listDisciplinaComNota;
	}


}
