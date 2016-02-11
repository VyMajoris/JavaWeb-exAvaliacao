package Testing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Professor;

@ManagedBean
@ViewScoped
public class ProfessorQueryTestBean {
	private List<Aluno> listAluno;
	private GenericDao<Aluno> alunoDao;
	private Professor professor;
	
	private Session hSession;
	@PostConstruct
	public void init(){
		createHsession();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		professor = (Professor) session.getAttribute("professor");
		queryAlunosPorProfessor();
		System.out.println(professor.getRmProfessor());
	}
	
	
	@SuppressWarnings("unchecked")
	private void queryAlunosPorProfessor(){
		Query queryAlunosPorProfessor = hSession.getNamedQuery("findAlunoPorProfessor");
		queryAlunosPorProfessor.setLong("rmProfessor", professor.getRmProfessor());
		setListAluno(queryAlunosPorProfessor.list());
	}

	private void createHsession(){
		hSession = JpaUtil.getHibSession();
		
	}
	
	public List<Aluno> getListAluno() {
		return listAluno;
	}


	public void setListAluno(List<Aluno> listAluno) {
		this.listAluno = listAluno;
	}

}
