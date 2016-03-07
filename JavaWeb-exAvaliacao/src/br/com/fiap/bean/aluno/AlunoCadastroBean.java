package br.com.fiap.bean.aluno;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Query;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;

@ManagedBean
@ViewScoped
public class AlunoCadastroBean {

	private Aluno aluno;
	private GenericDao<Aluno> alunoDao;
	private GenericDao<Curso> cursoDao;
	private List<Curso> listCurso;
	private Long idAluno;


	public void init() throws IOException{
		alunoDao = new  GenericDao<Aluno>(Aluno.class);
		cursoDao = new GenericDao<Curso>(Curso.class);
		listCurso = cursoDao.listar();
		if (idAluno != null) {
			aluno = alunoDao.buscar(idAluno);
			System.out.println("22222");
		}else{
			aluno = new Aluno();
			System.out.println("11111");
		}
		if (aluno == null && idAluno != null) {
			System.out.println("333333");
			String message = "Bad request. Unknown user.";
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println(ec.getRequestContextPath());
			ec.invalidateSession();
			ec.redirect(ec.getRequestContextPath());
		}
	}

	public void cadastrarAluno() throws ParseException{
		Query findTotalAlunosPorCurso = JpaUtil.getHibSession().getNamedQuery("findTotalAlunosPorCurso");
		findTotalAlunosPorCurso.setLong("idCurso", aluno.getCurso().getId());
		findTotalAlunosPorCurso.uniqueResult();

		if (cursoDao.buscar(aluno.getCurso().getId()).getVagas() <= ((Long) findTotalAlunosPorCurso.uniqueResult())) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Este curso não possui mais vagas disponíveis!",null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			Format formatter = new SimpleDateFormat("ddMMyyyy");
			aluno.setSenha(formatter.format(aluno.getDataNasc()));
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
	

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}





}
