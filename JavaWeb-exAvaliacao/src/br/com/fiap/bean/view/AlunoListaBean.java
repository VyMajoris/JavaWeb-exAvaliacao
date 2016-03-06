package br.com.fiap.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Nota;

@ManagedBean
@ViewScoped
public class AlunoListaBean {

	private List<Aluno> listAluno;
	private GenericDao<Aluno> alunoDao;
	private GenericDao<Nota> notaDao;
	private Aluno alunoRemover;

	@PostConstruct
	public void init(){
		System.out.println("AlunoListBean init");
		alunoDao = new GenericDao<Aluno>(Aluno.class);
		setListAluno(alunoDao.listar());
		notaDao = new GenericDao<Nota>(Nota.class);

	}





	public String remove(){
		List<Nota> listNota = JpaUtil.getHibSession().getNamedQuery("findNotaPorAluno").setLong("idAluno", alunoRemover.getId()).list();
		for (Nota nota : listNota) {
			notaDao.remover(nota);
		}
		
		alunoRemover.setCurso(null);
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
