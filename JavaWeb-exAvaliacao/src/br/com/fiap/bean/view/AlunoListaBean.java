package br.com.fiap.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Query;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Disciplina;

@ManagedBean
@ViewScoped
public class AlunoListaBean {

	private List<Aluno> listAluno;
	private GenericDao<Aluno> alunoDao;
	private Aluno alunoRemover;

	@PostConstruct
	public void init(){
		System.out.println("AlunoListBean init");
		alunoDao = new GenericDao<Aluno>(Aluno.class);
		setListAluno(alunoDao.listar());
	}




	public String remove(){
		alunoRemover.setCurso(null);


		Query deleteNotaPorAlunoEDisciplina = JpaUtil.getHibSession().getNamedQuery("deleteNotaPorAlunoEDisciplina");
		deleteNotaPorAlunoEDisciplina.setLong("idAluno", (Long) alunoRemover.getId());
		
		for (Disciplina disc : alunoRemover.getCurso().getDisciplinas()) {
			
			deleteNotaPorAlunoEDisciplina.setLong("idDisciplina", disc.getId());
			System.out.println("DISCIPLINAD ELETE"+ disc.getNome());
			deleteNotaPorAlunoEDisciplina.executeUpdate();
		}
		
		
		



		alunoDao.removeById(alunoRemover.getId());
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Aluno Removido!"));
		listAluno = alunoDao.listar();
		return "lista-aluno?faces-redirect=true";

	}

	public List<Aluno> getListAluno() {
		return listAluno;
	}

	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = listAluno;
	}



	public Aluno getAlunoRemover() {
		return alunoRemover;
	}




	public void setAlunoRemover(Aluno alunoRemover) {
		this.alunoRemover = alunoRemover;
	}




}
