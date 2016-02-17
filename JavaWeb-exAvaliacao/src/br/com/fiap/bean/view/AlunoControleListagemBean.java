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
import br.com.fiap.entity.Professor;

@ManagedBean
@ViewScoped
public class AlunoControleListagemBean {

	
	private List<Disciplina> listDisciplina;
	private GenericDao<Aluno> alunoDao;
	private Long idDisciplina;
	private HttpSession session;
	private Disciplina disciplina;
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
		alunoDao = new GenericDao<Aluno>(Aluno.class);
		
		disciplina = (Disciplina) session.getAttribute("disciplina");
		
	}

	public void updateDisciplinaSession(Disciplina disciplina){
		session.setAttribute("disciplina", disciplina);

	}
	

	private void createHsession(){
		hSession = JpaUtil.getHibSession();

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



}
