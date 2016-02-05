package br.com.fiap.bean.request;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;

@ManagedBean
@RequestScoped
public class CursoCadastroBean {
	private List<Escola> listEscolaTotal;

	private GenericDao<Curso> cursoDao;
	private GenericDao<Escola> escolaDao;
	private Curso curso;
	private String dataInicio;
	private String dataTermino;

	@PostConstruct
	public void init() {
		System.out.println("Curso Cadastro Bean init");
		cursoDao = new  GenericDao<Curso>(Curso.class);
		escolaDao = new GenericDao<Escola>(Escola.class);
		listEscolaTotal = escolaDao.listar();
		curso = new Curso();
	}

	public void cadastrarCurso() throws ParseException{
		if (curso.getEscola() == null) {
			FacesMessage msg = new FacesMessage("Para cadastrar um curso � necess�rio selecionar uma escola!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{

			cursoDao.adicionar(curso);
			curso = new Curso();
			FacesMessage msg = new FacesMessage("Curso cadastrado!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public String atualizarCurso(){
		cursoDao.update(curso);
		FacesMessage msg = new FacesMessage("Curso " +curso.getNome()+ " Atualizado!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "lista-curso";
	}


	//getter e setter
	public String getDataTermino() {
		return dataTermino;
	}


	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}



	public String getDataInicio() {
		return dataInicio;
	}

	public List<Escola> getListEscolaTotal() {
		return listEscolaTotal;
	}

	public void setListEscolaTotal(List<Escola> listEscolaTotal) {
		this.listEscolaTotal = listEscolaTotal;
	}

	public Curso getCurso() {
		return curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

}
