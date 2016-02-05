package br.com.fiap.bean;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Escola;
import br.com.fiap.helpers.FormatadorData;

@ManagedBean
@ViewScoped
//NÃO USAR
public class DisciplinaBean {


	private Disciplina disciplina;

	private GenericDao<Curso> cursoDao;
	private GenericDao<Disciplina> disciplinaDao;
	private List<Curso> listCursoTotal;
	private List<String> listCursoIdSelecionados;
	private Disciplina disciplinaTemp;
	boolean disciplinaUpdate;
	private List<Disciplina> listDisciplina;

	@PostConstruct
	public void init(){
		setDisciplina(new Disciplina());
		cursoDao = new  GenericDao<Curso>(Curso.class);
		setDisciplinaDao(new GenericDao<Disciplina>(Disciplina.class));

	}
	public void cadastrarDisciplina() throws ParseException{

		if (listCursoIdSelecionados.isEmpty()) {
			FacesMessage msg = new FacesMessage("Para cadastrar uma disciplina é necessário selecionar um curso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}else{
			for (String escolaId : listCursoIdSelecionados) {

				//relacionamento curso-disciplina
			}

			disciplinaDao.adicionar(disciplina);

			listCursoIdSelecionados.clear();
			disciplina = new Disciplina();

			FacesMessage msg = new FacesMessage("Disciplina cadastrada!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}


	}

	public void prepRemoverDisciplina(Disciplina disciplina){
		disciplinaTemp=disciplina;
	}

	public String removerDisciplina(){
		System.out.println("Remover: " + disciplina.getNome());
		disciplinaDao.removeById(disciplina.getIdDisciplina());
		FacesMessage msg = new FacesMessage("Disciplina "+disciplina.getNome()+" Removido!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		listDisciplina = disciplinaDao.listar();
		return "lista-disciplina?faces-redirect=true";
	}

	public String prepAtualizarDisciplina(Disciplina disciplina){
		System.out.println("disciplina"+disciplina.getNome());
		this.disciplina = disciplina;
		disciplinaUpdate = true;
		return "cadastro-disciplina";
	}
	public String atualizarDisciplina(){
		disciplinaDao.update(disciplina);
		disciplinaUpdate = false;
		FacesMessage msg = new FacesMessage("Disciplina " +disciplina.getNome()+ " Atualizada!");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "lista-disciplina";
	}



	public List<Curso> listarCursos(){
		return cursoDao.listar();
	}


	public List<Disciplina> listarDisciplinas(){

		return listDisciplina =  disciplinaDao.listar();
	}






	public List<Curso> getListCursoTotal() {
		return listCursoTotal;
	}
	public void setListCursoTotal(List<Curso> listCursoTotal) {
		this.listCursoTotal = listCursoTotal;
	}

	public boolean isDisciplinaUpdate() {
		return disciplinaUpdate;
	}
	public void setDisciplinaUpdate(boolean disciplinaUpdate) {
		this.disciplinaUpdate = disciplinaUpdate;
	}

	public GenericDao<Disciplina> getDisciplinaDao() {
		return disciplinaDao;
	}
	public void setDisciplinaDao(GenericDao<Disciplina> disciplinaDao) {
		this.disciplinaDao = disciplinaDao;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Disciplina getDisciplinaTemp() {
		return disciplinaTemp;
	}
	public void setDisciplinaTemp(Disciplina disciplinaTemp) {
		this.disciplinaTemp = disciplinaTemp;
	}
	public List<String> getListCursoIdSelecionados() {
		return listCursoIdSelecionados;
	}
	public void setListCursoIdSelecionados(List<String> listCursoIdSelecionados) {
		this.listCursoIdSelecionados = listCursoIdSelecionados;
	}
	public List<Disciplina> getListDisciplina() {
		return listDisciplina;
	}
	public void setListDisciplina(List<Disciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}




}
