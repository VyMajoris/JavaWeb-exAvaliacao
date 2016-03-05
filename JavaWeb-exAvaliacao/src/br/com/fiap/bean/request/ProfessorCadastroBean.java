package br.com.fiap.bean.request;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Escola;
import br.com.fiap.entity.Professor;

@ManagedBean
@ViewScoped
public class ProfessorCadastroBean {

	private Professor professor;
	private GenericDao<Professor> professorDao;
	private GenericDao<Escola> escolaDao;

	private GenericDao<Disciplina> disciplinaDao;
	private List<Disciplina> listDisciplina;
	private Long idProf;

	public void init() throws IOException{
		System.out.println("Professor Bean init");
		professorDao = new  GenericDao<Professor>(Professor.class);
		disciplinaDao = new GenericDao<Disciplina>(Disciplina.class);
		escolaDao = new GenericDao<Escola>(Escola.class);
		listDisciplina = disciplinaDao.listar();
		if (idProf != null) {
			professor = professorDao.buscar(idProf);
		}else{
			professor = new Professor();
		}
		if (professor == null && idProf != null) {
			String message = "Bad request. Unknown user.";
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println(ec.getRequestContextPath());
			ec.invalidateSession();
			ec.redirect(ec.getRequestContextPath());
		}





	}

	public void cadastrarProfessor() throws ParseException{
		if (!escolaDao.listar().isEmpty()) {
			Format formatter = new SimpleDateFormat("ddMMyyyy");
			professor.setSenha(formatter.format(professor.getDataNasc()));
			professorDao.adicionar(professor);
			FacesMessage msg = new FacesMessage("Professor "+professor.getNome()+" cadastrado!");
			professor = new Professor();
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage("Cadastre uma escola primeiro!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public String atualizarProfessor(){
		if (!escolaDao.listar().isEmpty()) {
			professorDao.update(professor);
			professor = new Professor();
			FacesMessage msg = new FacesMessage("Professor Atualizado!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "/lista/lista-professor";
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
	public Long getIdProf() {
		return idProf;
	}

	public void setIdProf(Long idProf) {
		this.idProf = idProf;
	}



}
