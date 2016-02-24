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
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;

@ManagedBean
@RequestScoped
public class AlunoCadastroBean {

	Aluno aluno;
	private GenericDao<Aluno> alunoDao;
	private GenericDao<Curso> cursoDao;
	private List<Curso> listCurso;

	@PostConstruct
	public void init(){
		System.out.println("Aluno Bean init");
		aluno = new Aluno();
		generateRandomId();
		alunoDao = new  GenericDao<Aluno>(Aluno.class);
		cursoDao = new GenericDao<Curso>(Curso.class);
		listCurso = cursoDao.listar();
	}
	private void generateRandomId() {
		if (aluno.getId() == null) {
			Random rand = new Random();
			int randomNum = rand.nextInt((99999 - 11111) + 1) + 0;
			aluno.setId(Long.parseLong(String.format("%05d", randomNum)));
		}
	}
	public void cadastrarAluno() throws ParseException{
		if (!cursoDao.listar().isEmpty()) {
			if (aluno.getCurso() != null) {

				if (aluno.getCpf().isEmpty()) {
					FacesMessage msg = new FacesMessage("Insira um CPF!");
					FacesContext.getCurrentInstance().addMessage(null, msg);

				}else{
					aluno.setSenha(aluno.getCpf());
					alunoDao.adicionar(aluno);
					FacesMessage msg = new FacesMessage("Aluno "+aluno.getNome()+" cadastrado!");
					aluno = new Aluno();
					generateRandomId();
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}

			}else{
				FacesMessage msg = new FacesMessage("Escolha um curso primeiro!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			FacesMessage msg = new FacesMessage("Cadastre um curso primeiro!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public String atualizarAluno(){
		if (aluno.getCurso() != null) {

			if (aluno.getCpf().isEmpty()) {
				FacesMessage msg = new FacesMessage("Insira um CPF!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "/cadastro/cadastro-aluno";

			}else{
				aluno.setSenha(aluno.getCpf());
				alunoDao.update(aluno);
				generateRandomId();
				FacesMessage msg = new FacesMessage("Aluno Atualizado!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "/lista/lista-aluno";
			}

		}else{
			FacesMessage msg = new FacesMessage("Escolha um curso primeiro!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "/cadastro/cadastro-aluno";
		}


	}
	public List<Curso> getListCurso() {
		return listCurso;
	}
	public void setListCurso(List<Curso> listCurso) {
		this.listCurso = listCurso;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}



}
