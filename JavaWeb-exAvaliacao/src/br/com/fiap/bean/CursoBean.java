package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.map.DefaultMapModel;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;

@ManagedBean
@SessionScoped
public class CursoBean {
	private List<Escola> listEscolaTotal;
	private List<Escola> listEscolaSelecionadas;
	private List<Curso> listCurso;
	private GenericDao<Curso> cursoDao;
	private GenericDao<Escola> escolaDao;
	private Curso curso;
	private Curso cursoTemp;


	@PostConstruct
	public void init() {
		System.out.println("Curso Bean init");
		curso = new Curso();
		cursoDao = new  GenericDao<Curso>(Curso.class);
		escolaDao = new GenericDao<Escola>(Escola.class);

	}
	public void cadastrarCurso(){
		cursoDao.adicionar(curso);
		curso = new Curso();
		curso.setListEscola(listEscolaSelecionadas);
		FacesMessage msg = new FacesMessage("Curso cadastrado!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Escola> listarEscolas(){
		return escolaDao.listar();
	}

	public List<Curso> listarCursos(){
		return cursoDao.listar();
	}


	public void addEscolaLista(Escola escola){
		listEscolaSelecionadas.add(escola);
	}

	public void prepRemoverCurso(Curso curso){
		cursoTemp=curso;
	}

	public String removerCurso(){
		System.out.println("Remover: " + curso.getNome());
		cursoDao.removeById(curso.getCursoId());
		FacesMessage msg = new FacesMessage("Curso Removido!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		listCurso = cursoDao.listar();
		return "lista-curso?faces-redirect=true";
	}

	public String prepAtualizarCurso(Curso curso){
		System.out.println("curso"+curso.getDescricao());
		this.curso = curso;
		return "cadastro-curso";
	}
	public String atualizarCurso(){
		cursoDao.update(curso);
		return "lista-curso";
	}


	//Getter and Setters;
	public List<Escola> getListEscolaTotal() {
		return listEscolaTotal;
	}
	public void setListEscolaTotal(List<Escola> listEscolaTotal) {
		this.listEscolaTotal = listEscolaTotal;
	}
	public List<Escola> getListEscolaSelecionadas() {
		return listEscolaSelecionadas;
	}
	public void setListEscolaSelecionadas(List<Escola> listEscolaSelecionadas) {
		this.listEscolaSelecionadas = listEscolaSelecionadas;
	}
	public List<Curso> getListCurso() {
		return listCurso;
	}
	public void setListCurso(List<Curso> listCurso) {
		this.listCurso = listCurso;
	}
	public GenericDao<Curso> getCursoDao() {
		return cursoDao;
	}
	public void setCursoDao(GenericDao<Curso> cursoDao) {
		this.cursoDao = cursoDao;
	}
	public GenericDao<Escola> getEscolaDao() {
		return escolaDao;
	}
	public void setEscolaDao(GenericDao<Escola> escolaDao) {
		this.escolaDao = escolaDao;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Curso getCursoTemp() {
		return cursoTemp;
	}
	public void setCursoTemp(Curso cursoTemp) {
		this.cursoTemp = cursoTemp;
	}



}
