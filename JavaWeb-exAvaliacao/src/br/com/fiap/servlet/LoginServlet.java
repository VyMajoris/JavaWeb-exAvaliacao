package br.com.fiap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		String rm = request.getParameter("rm");
		String senha = request.getParameter("senha");
		String valid = "no";

		//se essa string tiver valor, é porque o usuário quer deslogar
		String logout = request.getParameter("logout");

		System.out.println(request.getParameter("userId"));
		System.out.println(request.getParameter("displayName"));

		//logar
		if (rm != null) {
			if (rm.equals("admin")&&senha.equals("admin")){
				logarAdmin(rm, senha);

			}else{
				logarUsuario(rm,senha);
			}

		}

		//deslogar
		if (logout != null) {
			HttpSession session = request.getSession(false);
			session.invalidate();
		}


	}
	private void logarAdmin(String userId, String displayName) {
		FacesContext context = FacesContext.getCurrentInstance();
		//Recupera a sessão do usuário... true -> cria uma sessão se não existe
		HttpSession session = (HttpSession)
				context.getExternalContext().getSession(true);
		//Adiciona informação na sessão -> (chave, valor)
		session.setAttribute("userId", userId);
		session.setAttribute("displayName", displayName);
	}
	private void logarUsuario(String userId, String displayName) {
		FacesContext context = FacesContext.getCurrentInstance();
		//Recupera a sessão do usuário... true -> cria uma sessão se não existe
		HttpSession session = (HttpSession)
				context.getExternalContext().getSession(true);
		//Adiciona informação na sessão -> (chave, valor)



		Session hSession = JpaUtil.getHibSession();

		Query query = hSession.getNamedQuery("findAluno");
		query.setInteger("rm", 1);
		query.setString("senha", "senha");
		List<Aluno> alunoList = query.list();
		for (Aluno aluno : alunoList) {
			System.out.println("List of Employees::" + aluno.getRm());
		}






		session.setAttribute("userId", userId);
		session.setAttribute("displayName", displayName);
	}


}
