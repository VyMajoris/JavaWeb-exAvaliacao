package br.com.fiap.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Professor;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable{

	protected String teste = "teste;";
	protected List<Aluno> alunoList;
	List<Professor> professorList;
	Session hSession;

	protected String rm;
	protected String senha;

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	public LoginBean() {
		// TODO Auto-generated constructor stub
	}

	public String logar(){
		System.out.println("logafr");
		System.out.println("RM===" +rm +"senha===="+senha);

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		//Admin
		if (rm.equals("admin")&&senha.equals("admin")) {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
					}
				}else{
					//usuario não cadastrado
				}
			}


		}


		return "true";

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

	private void queryAluno(){
		Query queryAluno = hSession.getNamedQuery("findAluno");
		queryAluno.setInteger("rm",  Integer.parseInt(rm));
		queryAluno.setString("senha", senha);
		alunoList = queryAluno.list();
	}

	private void queryProfessor(){
		Query queryProfessor = hSession.getNamedQuery("findProfessor");
		queryProfessor.setInteger("rm", Integer.parseInt(rm));
		queryProfessor.setString("senha", senha);
		professorList = queryProfessor.list();
	}

	private void createHsession(){
		hSession = JpaUtil.getHibSession();
	}


	public void deslogar(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
	}
}
