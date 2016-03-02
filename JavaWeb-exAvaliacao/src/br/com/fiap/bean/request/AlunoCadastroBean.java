package br.com.fiap.bean.request;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Query;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;

@ManagedBean
@RequestScoped
public class AlunoCadastroBean implements DatabaseListener {

	Aluno aluno;
	private GenericDao<Aluno> alunoDao;
	private GenericDao<Curso> cursoDao;
	private List<Curso> listCurso;

	@PostConstruct
	public void init(){
		System.out.println("Aluno Bean init");
		aluno = new Aluno();
		alunoDao = new  GenericDao<Aluno>(Aluno.class);
		cursoDao = new GenericDao<Curso>(Curso.class);
		listCurso = cursoDao.listar();
	}

	public void cadastrarAluno() throws ParseException{
		Format formatter = new SimpleDateFormat("ddMMyyyy");
		aluno.setSenha(formatter.format(aluno.getDataNasc()));

		Query findTotalAlunosPorCurso = JpaUtil.getHibSession().getNamedQuery("findTotalAlunosPorCurso");
		findTotalAlunosPorCurso.setLong("idCurso", aluno.getCurso().getId());
		findTotalAlunosPorCurso.uniqueResult();

		if (cursoDao.buscar(aluno.getCurso().getId()).getVagas() < (Long) findTotalAlunosPorCurso.uniqueResult()) {
			FacesMessage msg = new FacesMessage("Este curso não possui mais vagas disponíveis!: "+aluno.getRm());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			aluno = alunoDao.adicionar(aluno);
			FacesMessage msg = new FacesMessage("Aluno Cadastrado. Registro de Matrícula: "+aluno.getRm());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			aluno = new Aluno();
		}
	}


	public String atualizarAluno(){

		aluno.setSenha(aluno.getCpf());
		alunoDao.update(aluno);
		FacesMessage msg = new FacesMessage("Aluno Atualizado!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "/lista/lista-aluno";

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
	@Override
	public void onSave() {
		// TODO Auto-generated method stub

	}



}
