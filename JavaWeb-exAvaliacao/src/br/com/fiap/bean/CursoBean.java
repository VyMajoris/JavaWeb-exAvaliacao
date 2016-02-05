package br.com.fiap.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;
import br.com.fiap.helpers.FormatadorData;

@ManagedBean
@ViewScoped

//NÃO USAR
public class CursoBean {
	private List<Escola> listEscolaTotal;
	private List<String> listEscolaIdSelecionadas;
	private List<Curso> listCurso;
	private GenericDao<Curso> cursoDao;
	private GenericDao<Escola> escolaDao;
	private Curso curso;
	private Curso cursoTemp;
	private String dataInicio;
	private String dataTermino;
	private boolean cursoUpdate;



	@PostConstruct
	public void init() {
		System.out.println("Curso Bean init");
		curso = new Curso();

		cursoDao = new  GenericDao<Curso>(Curso.class);
		escolaDao = new GenericDao<Escola>(Escola.class);
		listEscolaTotal = escolaDao.listar();
	}


	public void cadastrarCurso() throws ParseException{

		if (listEscolaIdSelecionadas.isEmpty()) {
			FacesMessage msg = new FacesMessage("Para cadastrar um curso é necessário selecionar uma escola!");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}else{
			for (String escolaId : listEscolaIdSelecionadas) {
				System.out.println("Escola ID: +"+ escolaId);
				curso.setEscola(escolaDao.buscar(Integer.parseInt(escolaId)));
			}

			cursoDao.adicionar(curso);
			dataInicio = "";
			dataTermino = "";
			listEscolaIdSelecionadas.clear();
			curso = new Curso();

			FacesMessage msg = new FacesMessage("Curso cadastrado!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}



	public void prepRemoverCurso(Curso curso){
		cursoTemp=curso;
	}

	public String removerCurso(){
		System.out.println("Remover: " + curso.getNome());
		cursoDao.removeById(curso.getIdCurso());
		FacesMessage msg = new FacesMessage("Curso "+curso.getNome()+" Removido!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		listCurso = cursoDao.listar();
		return "lista-curso?faces-redirect=true";
	}

	public String prepAtualizarCurso(Curso curso){
		System.out.println("curso"+curso.getNome());
		this.curso = curso;
		cursoUpdate = true;
		return "cadastro-curso";
	}
	public String atualizarCurso(){
		cursoDao.update(curso);
		cursoUpdate = false;
		FacesMessage msg = new FacesMessage("Curso " +curso.getNome()+ " Atualizado!");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "lista-curso";
	}



	public List<Escola> listarEscolas(){
		return escolaDao.listar();
	}


	public void listarCursos(){

		listCurso =  cursoDao.listar();
	}


















	//Getter and Setters;
	public List<Escola> getListEscolaTotal() {
		return listEscolaTotal;
	}
	public void setListEscolaTotal(List<Escola> listEscolaTotal) {
		this.listEscolaTotal = listEscolaTotal;
	}

	public List<String> getListEscolaIdSelecionadas() {
		return listEscolaIdSelecionadas;
	}


	public void setListEscolaIdSelecionadas(List<String> listEscolaIdSelecionadas) {
		this.listEscolaIdSelecionadas = listEscolaIdSelecionadas;
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
	public String getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}


	public boolean isCursoUpdate() {
		return cursoUpdate;
	}


	public void setCursoUpdate(boolean cursoUpdate) {
		this.cursoUpdate = cursoUpdate;
	}



}
