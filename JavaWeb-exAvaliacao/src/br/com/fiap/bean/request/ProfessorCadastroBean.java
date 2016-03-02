package br.com.fiap.bean.request;

import java.text.ParseException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Escola;
import br.com.fiap.entity.Professor;

@ManagedBean
@RequestScoped
public class ProfessorCadastroBean {

	Professor professor;
	private GenericDao<Professor> professorDao;
	private GenericDao<Escola> escolaDao;
	private GenericDao<Disciplina> disciplinaDao;
	private List<Disciplina> listDisciplina;

	@PostConstruct
	public void init(){
		System.out.println("Professor Bean init");
		professor = new Professor();
		professorDao = new  GenericDao<Professor>(Professor.class);
		disciplinaDao = new GenericDao<Disciplina>(Disciplina.class);
		escolaDao = new GenericDao<Escola>(Escola.class);
		listDisciplina = disciplinaDao.listar();
	}
	private void generateRandomId() {
		if (professor.getId() == null) {
			Random rand = new Random();
			int randomNum = rand.nextInt((99999 - 11111) + 1) + 0;
			professor.setId(Long.parseLong(String.format("%05d", randomNum)));
		}
	}
	public void cadastrarProfessor() throws ParseException{

		if (!escolaDao.listar().isEmpty()) {


			if (professor.getCpf().isEmpty()) {
				FacesMessage msg = new FacesMessage("Insira um CPF!");
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}else{
				professor.setSenha(professor.getCpf());
				professorDao.adicionar(professor);
				FacesMessage msg = new FacesMessage("Professor "+professor.getNome()+"cadastrado!");
				professor = new Professor();
				
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}


		}else{
			FacesMessage msg = new FacesMessage("Cadastre uma escola primeiro!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public String atualizarProfessor(){
		if (!escolaDao.listar().isEmpty()) {


			if (professor.getCpf().isEmpty()) {
				FacesMessage msg = new FacesMessage("Insira um CPF!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "/cadastro/cadastro-professor";

			}else{
				professor.setSenha(professor.getCpf());
				professorDao.adicionar(professor);
				professor = new Professor();
				generateRandomId();
				FacesMessage msg = new FacesMessage("Professor Atualizado!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "/lista/lista-professor";

			}


		}else{
			FacesMessage msg = new FacesMessage("Cadastre uma escola primeiro!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "/cadastro/cadastro-professor";
		}

		
	}
	public List<Disciplina> getListDisciplina() {
		return listDisciplina;
	}
	public void setListDisciplina(List<Disciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}



}
