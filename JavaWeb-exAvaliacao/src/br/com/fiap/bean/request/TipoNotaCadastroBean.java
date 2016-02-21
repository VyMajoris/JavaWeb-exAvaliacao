package br.com.fiap.bean.request;

import java.text.ParseException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.TipoNota;

@ManagedBean
@RequestScoped
public class TipoNotaCadastroBean {

	private TipoNota tipoNota;
	private GenericDao<TipoNota> tipoNotaDao;

	@PostConstruct
	public void init() {
		this.tipoNota = new TipoNota();
		this.tipoNotaDao = new  GenericDao<TipoNota>(TipoNota.class);
	}

	public TipoNota getTipoNota() {
		return tipoNota;
	}

	public void setTipoNota(TipoNota tipoNota) {
		this.tipoNota = tipoNota;
	}

	public void cadastrar() throws ParseException {

		this.tipoNotaDao.adicionar(this.tipoNota);
		FacesMessage msg = new FacesMessage("Tipo de Nota " + tipoNota.getDescricao() + "cadastrado!");
		this.tipoNota = new TipoNota();
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

}
