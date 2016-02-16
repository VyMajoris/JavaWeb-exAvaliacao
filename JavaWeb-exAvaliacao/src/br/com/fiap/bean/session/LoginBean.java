package br.com.fiap.bean.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Professor;

@ManagedBean
@RequestScoped
// >>>OK<<<
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String teste = "teste;";
	private List<Aluno> alunoList = new ArrayList<Aluno>();
	private List<Professor> professorList = new ArrayList<Professor>();
	private Session hSession;

	private String rm;
	private String senha;

	public String getTeste() {
		return teste;
	}

	public void teste() {
		System.out.println("../professor/controle-professor?faces-redirect=true");
	}

	public LoginBean() {
		// TODO Auto-generated constructor stub
	}

	
	
	public String logar() throws IOException{
		System.out.println("RM===" +rm +"senha===="+senha);

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		//Admin
		if (rm.equals("admin")&&senha.equals("admin")) {

			session.setAttribute("loginType", "admin");
			session.setAttribute("displayName", "admin");	
		}else{
			//pega a session do hibernate para o named query
			createHsession();

			//chama a named query do aluno
			queryAluno();
			if (!alunoList.isEmpty()) {
				session.setAttribute("loginType", "aluno");
				//LOGIN ALUNO
				for (Aluno a : alunoList) {
					System.out.println("RM DO ALUNO" + a.getRmAluno());
					session.setAttribute("displayName", a.getNome());
					session.setAttribute("rmAluno", a.getRmAluno());

				}
			}else{
				//não existe aluno com esse RM e senha, vamos se existe professores com esta combinação
				//chama a named query do professor
				queryProfessor();
				if(!professorList.isEmpty()){
					session.setAttribute("loginType", "professor");
					//LOGIN PROFESSOR
					for (Professor p : professorList) {
						System.out.println("RM DO PROFESSOR" + p.getRmProfessor());
						session.setAttribute("displayName", p.getNome());
						session.setAttribute("rmProfessor", p.getRmProfessor());
					}
					System.out.println("returnaaaaaaa");
					return "/professor/controle-professor";
				}else{
					//usuario não cadastrado
				}
			}
		}
		return rm;
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


	@SuppressWarnings("unchecked")
	private void queryAluno(){
		Query queryAluno = hSession.getNamedQuery("findAluno");
		queryAluno.setInteger("rmAluno",  Integer.parseInt(rm));
		queryAluno.setString("senha", senha);
		alunoList = queryAluno.list();
	}


	@SuppressWarnings("unchecked")
	private void queryProfessor(){
		Query queryProfessor = hSession.getNamedQuery("findProfessor");
		queryProfessor.setInteger("rmProfessor", Integer.parseInt(rm));
		queryProfessor.setString("senha", senha);
		professorList = queryProfessor.list();
	}

	private void createHsession(){
		hSession = JpaUtil.getHibSession();
	}


	public String deslogar(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return "index?faces-redirect=true";
	}
}
