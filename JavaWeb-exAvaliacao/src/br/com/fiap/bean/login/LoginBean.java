package br.com.fiap.bean.login;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Admin;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Professor;
import br.com.fiap.entity.Usuario;
import br.com.fiap.validator.NumericValidator;

@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	public Aluno getAluno() {
		return aluno;
	}
	private Professor professor;
	private String rm;
	private String senha;
	private Usuario usuario;
	HttpSession session;

	@PostConstruct
	public void init(){
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	}
	public boolean isNumericInputValid (String input){
		return NumericValidator.isNumeric(input); 
	}

	public String buscaUsuario(){
		Usuario usuario = null;
		String retorno = null;
		if (isNumericInputValid(rm)) {
			usuario = (Usuario) JpaUtil.getHibSession().getNamedQuery("findUsuario")
					.setLong("rm", Long.parseLong(rm))
					.setString("senha", senha)
					.uniqueResult();
		}
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
			FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Error summary", "Error detail");
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			RequestContext.getCurrentInstance().execute(" $.bootstrapGrowl('RM e/ou Senha invalida', "
					+ "{type: 'danger',"
					+ "align: 'center',"
					+ "width: 'auto', "
					+ "allow_dismiss: false"
					+ "});");
		}
		return retorno;
	}

	private String loginProfessor(Professor professor) {
		session.setAttribute("loginType", "professor");
		session.setAttribute("displayName", professor.getNome());
		session.setAttribute("rmProfessor", professor.getId());
		return "/professor/professor-dashboard?faces-redirect=true";
	}

	private String loginAluno(Aluno aluno) {
		session.setAttribute("loginType", "aluno");
		session.setAttribute("displayName", aluno.getNome());
		session.setAttribute("rmAluno", aluno.getId());
		return "/aluno/aluno-disciplinaPorCurso?faces-redirect=true";
	}

	private String loginAdmin(Admin admin) {
		session.setAttribute("loginType", "admin");
		session.setAttribute("displayName", "admin");
		return "/index?faces-redirect=true";
	}

	public void deslogar() throws IOException{
		session.invalidate();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath());
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
