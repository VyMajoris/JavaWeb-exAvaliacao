package br.com.fiap.bean.request;
import java.io.IOException;
import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Escola;
import br.com.fiap.latlng.AddressConverter;
import br.com.fiap.latlng.GoogleResponse;
import br.com.fiap.latlng.Result;


@ManagedBean
@ViewScoped
public class EscolaCadastroBean implements Serializable {

	private static final long serialVersionUID = -3753237190019758230L;
	private GenericDao<Escola> escolaDao;
	private Escola escola;
	private Long idEscola;

	public void init() throws IOException {
		System.out.println("EscolaCadastroBean init");
		escolaDao = new  GenericDao<Escola>(Escola.class);
		if (idEscola != null) {
			escola = escolaDao.buscar(idEscola);
		}else{
			escola = new Escola();
		}

		if (escola == null && idEscola != null) {
			String message = "Bad request. Unknown user.";
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println(ec.getRequestContextPath());
			ec.invalidateSession();
			ec.redirect(ec.getRequestContextPath());
		}
	}

	public void cadastrarEscola(){
		try {
			escola.setLatLng(getLatLng(escola.getEndereco()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		escolaDao.adicionar(escola);
		FacesMessage msg = new FacesMessage("Escola "+escola.getNome()+" cadastrada!");
		this.escola = new Escola();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	private String getLatLng(String endereco) throws IOException {
		GoogleResponse res = new AddressConverter().convertToLatLong(endereco);
		String latLng = null;
		if(res.getStatus().equals("OK")){
			Result result = res.getResults()[0];
			System.out.println(result.getFormatted_address());
			System.out.println(result.getGeometry().getLocation_type());
			latLng = result.getGeometry().getLocation().getLat()+","+result.getGeometry().getLocation().getLng();
		}
		return latLng;

	}

	public String atualizarEscola() throws IOException{
		escola.setLatLng(getLatLng(escola.getEndereco()));
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

	public Long getIdEscola() {
		return idEscola;
	}

	public void setIdEscola(Long idEscola) {
		this.idEscola = idEscola;
	}



}