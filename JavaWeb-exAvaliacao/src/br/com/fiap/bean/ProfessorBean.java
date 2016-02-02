package br.com.fiap.bean;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;

import br.com.fiap.entity.Disciplina;

import br.com.fiap.entity.Professor;
import br.com.fiap.helpers.FormatadorData;

@ManagedBean
public class ProfessorBean {

	Professor professor;
	private List<Professor> listProfessor;
	private GenericDao<Professor> professorDao;
	private GenericDao<Disciplina> disciplinaDao;
	private Professor professorTemp;
	private String dataNascimento;
	private List<Disciplina> listDisciplinaTotal;
	private List<String> listDisciplinaIdSelecionadas;
	private boolean professorUpdate = false;


	@PostConstruct
	public void init(){
		System.out.println("Professor Bean init");
		professor = new Professor();

		professorDao = new  GenericDao<Professor>(Professor.class);
		disciplinaDao = new GenericDao<Disciplina>(Disciplina.class);
		listDisciplinaTotal = disciplinaDao.listar();
	}




	public void cadastrarProfessor() throws ParseException{
		professor.setDataNasc(FormatadorData.formatarDate(dataNascimento, "yyyy-MM-dd", "dd/MM/yyyy"));


		for (String disciplinaId : listDisciplinaIdSelecionadas) {
			System.out.println("Disciplina ID: +"+ disciplinaId);
			//add lista discplina prof
		}

		professorDao.adicionar(professor);
		professor = new Professor();
		FacesMessage msg = new FacesMessage("Professor cadastrado!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


	public void prepRemoverProfessor(Professor professor){
		professorTemp=professor;
	}

	public String removerProfessor(){
		System.out.println("Remover: " + professorTemp.getNome());
		professorDao.removeById(professorTemp.getRmProfessor());
		FacesMessage msg = new FacesMessage("Professor Removido!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		listProfessor = professorDao.listar();
		return "lista-professor?faces-redirect=true";
	}

	public String prepAtualizarProfessor(Professor professor){
		System.out.println("professor"+professor.getNome());
		this.professor = professor;
		professorUpdate = true;
		return "cadastro-professor";
	}
	public String atualizarProfessor(){
		professorDao.update(professor);
		professorUpdate = false;
		return "lista-professor";
	}


	public List<Professor> getListProfessor() {
		return listProfessor;
	}




	public void setListProfessor(List<Professor> listProfessor) {
		this.listProfessor = listProfessor;
	}




	public Professor getProfessorTemp() {
		return professorTemp;
	}




	public void setProfessorTemp(Professor professorTemp) {
		this.professorTemp = professorTemp;
	}




	public String getDataNascimento() {
		return dataNascimento;
	}




	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}




	public List<Disciplina> getListDisciplinaTotal() {
		return listDisciplinaTotal;
	}




	public void setListDisciplinaTotal(List<Disciplina> listDisciplinaTotal) {
		this.listDisciplinaTotal = listDisciplinaTotal;
	}




	public List<String> getListDisciplinaIdSelecionadas() {
		return listDisciplinaIdSelecionadas;
	}




	public void setListDisciplinaIdSelecionadas(List<String> listDisciplinaIdSelecionadas) {
		this.listDisciplinaIdSelecionadas = listDisciplinaIdSelecionadas;
	}




	public boolean isProfessorUpdate() {
		return professorUpdate;
	}




	public void setProfessorUpdate(boolean professorUpdate) {
		this.professorUpdate = professorUpdate;
	}




	public List<Disciplina> listarDisciplinas(){
		return disciplinaDao.listar();
	}


	public List<Professor> listarProfessores(){
		return professorDao.listar();
	}





	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}



}
