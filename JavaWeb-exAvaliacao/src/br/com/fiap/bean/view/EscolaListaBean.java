package br.com.fiap.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Escola;

@ManagedBean
@ViewScoped
public class EscolaListaBean {

	private List<Escola> listEscola;
	private GenericDao<Escola> escolaDao;


	private int idEscolaremover;

	@PostConstruct
	public void init(){
		System.out.println("EscolaListBean init");
		escolaDao = new GenericDao<Escola>(Escola.class);
		setListEscola(escolaDao.listar());
	}


	public int getIdEscolaremover() {
		return idEscolaremover;
	}

	public void setIdEscolaremover(int idEscolaremover) {
		this.idEscolaremover = idEscolaremover;
	}

	public String remove(){
		System.out.println("REMOVE " +idEscolaremover);
		escolaDao.removeById(idEscolaremover);
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Escola Removida!"));
		listEscola = escolaDao.listar();
		return "lista-escola?faces-redirect=true";
		
	}

	public List<Escola> getListEscola() {
		return listEscola;
	}

	public void setListEscola(List<Escola> listEscola) {
		this.listEscola = listEscola;
	}

}
