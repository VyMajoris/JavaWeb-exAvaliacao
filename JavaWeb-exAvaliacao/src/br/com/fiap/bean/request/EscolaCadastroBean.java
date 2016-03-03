package br.com.fiap.bean.request;
import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Escola;
import br.com.fiap.latlng.AddressConverter;
import br.com.fiap.latlng.GoogleResponse;
import br.com.fiap.latlng.Result;

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
		try {
			getLatLng(escola.getEndereco());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		escolaDao.adicionar(escola);
		FacesMessage msg = new FacesMessage("Escola "+escola.getNome()+" cadastrada!");
		this.escola = new Escola();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	private void getLatLng(String endereco) throws IOException {
		GoogleResponse res = new AddressConverter().convertToLatLong(endereco);
		if(res.getStatus().equals("OK")){
			Result result = res.getResults()[0];
				System.out.println("Lattitude of address is :"  +result.getGeometry().getLocation().getLat());
				System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());
				System.out.println("Location is " + result.getGeometry().getLocation_type());
				escola.setLatLong(result.getGeometry().getLocation().getLat()+","+result.getGeometry().getLocation().getLng());
		}

	}

	public String atualizarEscola(){
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