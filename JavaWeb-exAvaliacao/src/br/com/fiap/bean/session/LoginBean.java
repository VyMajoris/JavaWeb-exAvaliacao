package br.com.fiap.bean.session;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.primefaces.context.RequestContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Admin;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Professor;

import br.com.fiap.entity.Usuario;
import br.com.fiap.helpers.NumericValidator;

@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean implements Serializable{


	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	public Aluno getAluno() {
		return aluno;
	}

	private Professor professor;
	private static GenericDao<Usuario> usuarioDao;
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


	public boolean isNumericInputValid (String input){
		return NumericValidator.isNumeric(input); 
	}

	
	
	
	
	public String buscaUsuario(){
		Admin admin = new Admin();
		admin.setSenha("admin");
		usuarioDao.adicionar(admin);
		
		System.out.println(isNumericInputValid(rm));
		Usuario usuario = null;
		String retorno = "index";
		System.out.println(rm +" a " + senha);
		
		if (isNumericInputValid(rm)) {
			usuario = (Usuario) hSession.getNamedQuery("findUsuario")
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
			FacesContext.getCurrentInstance().addMessage("xssssss", errorMessage);
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
		return "/admin/admin-dashboard?faces-redirect=true";
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
