package br.com.fiap.bean.request;
import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;




import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Escola;

@ManagedBean
@RequestScoped
public class EscolaCadastroBean implements Serializable {

	private static final long serialVersionUID = -3753237190019758230L;
	private GenericDao<Escola> escolaDao;
	private Escola escola;

	

	@PostConstruct
	public void init() {
		System.out.println("EscolaCadastroBean init");
		escolaDao = new  GenericDao<Escola>(Escola.class);
		escola = new Escola();
	}

	public void cadastrarEscola(){
		System.out.println("CADASTRAR ESCOLA "+ escola.getNome());
		escolaDao.adicionar(escola);
		FacesMessage msg = new FacesMessage("Escola "+escola.getNome()+" cadastrada!");
		this.escola = new Escola();
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	
	public String atualizarEscola(){
		System.out.println("ATUALIZAR ESCOLA: "+ escola.getNome());
		escolaDao.update(escola);
		FacesMessage msg = new FacesMessage("Escola "+escola.getNome()+" Atualizada!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "/lista/lista-escola";

	}




	//getter setters
	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public GenericDao<Escola> getEscolaDao() {
		return escolaDao;
	}

	public void setEscolaDao(GenericDao<Escola> escolaDao) {
		this.escolaDao = escolaDao;
	}



}