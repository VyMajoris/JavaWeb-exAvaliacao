package br.com.fiap.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Aluno;

@ManagedBean
@ViewScoped
public class AlunoListaBean {

	private List<Aluno> listAluno;
	private GenericDao<Aluno> alunoDao;


	private Long idAlunoremover;

	@PostConstruct
	public void init(){
		System.out.println("AlunoListBean init");
		alunoDao = new GenericDao<Aluno>(Aluno.class);
		setListAluno(alunoDao.listar());
	}




	public String remove(){
		System.out.println("REMOVE " +idAlunoremover);
		alunoDao.removeById(idAlunoremover);
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
	public Long getIdAlunoremover() {
		return idAlunoremover;
	}



	public void setIdAlunoremover(Long idAlunoremover) {
		this.idAlunoremover = idAlunoremover;
	}




}
