package br.com.fiap.bean.session;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Admin;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Professor;

import br.com.fiap.entity.Usuario;

@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean implements Serializable{


	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	public Aluno getAluno() {
		return aluno;
	}

	private Professor professor;
	private GenericDao<Usuario> usuarioDao;
	private Session hSession;
	private String rm;
	private String senha;
	private Usuario usuario;
	HttpSession session;

	@PostConstruct
	public void init(){
		usuarioDao = new GenericDao<Usuario>(Usuario.class);
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		hSession = JpaUtil.getHibSession();

	}

	public String buscaUsuario(){
		String retorno = null;
		Usuario usuario = (Usuario) hSession.getNamedQuery("findUsuario")
				.setLong("rm", Long.parseLong(rm))
				.setString("senha", senha)
				.uniqueResult();

		if (usuario != null) {
			switch (usuario.getTipo()) {
			case ADMIN:
				retorno = loginAdmin((Admin) usuario);
				break;
			case ALUNO:
				retorno = loginAluno((Aluno) usuario);
				break;
			case PROFESSOR:
				retorno = loginProfessor((Professor) usuario);
				break;
			default:
				retorno = "index";
				break;
			}
		}
		else{
			FacesMessage msg = new FacesMessage("Usuário ou senha incorretos.");
			FacesContext.getCurrentInstance().addMessage("login", msg);
		}
		return retorno;


	}

	private String loginProfessor(Professor professor) {
		session.setAttribute("loginType", "professor");
		session.setAttribute("displayName", professor.getNome());
		session.setAttribute("idProfessor", professor.getId());
		return "/professor/professor-dashboard";
	}

	private String loginAluno(Aluno aluno) {
		session.setAttribute("loginType", "aluno");
		session.setAttribute("displayName", aluno.getNome());
		session.setAttribute("idAluno", aluno.getId());
		return "/aluno/aluno-dashboard";
	}

	private String loginAdmin(Admin admin) {
		session.setAttribute("loginType", "admin");
		session.setAttribute("displayName", "admin");
		return "/admin/admin-dashboard";
	}

	public String deslogar(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return "index?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
