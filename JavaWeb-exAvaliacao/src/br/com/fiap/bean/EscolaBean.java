package br.com.fiap.bean;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.behavior.Behavior;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.gmap.GMap;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Escola;

@ManagedBean
@ViewScoped
public class EscolaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3753237190019758230L;

	@PostConstruct
	public void init() {
		draggableModel = new DefaultMapModel();
		escola = new Escola();
		
	}
GMap gmap = new GMap();
	private MapModel draggableModel;
	private Escola escola;
	@Inject
	private GenericDao<Escola> escolaDao;

	private String latDisplay;
	private String lngDisplay;
	private Double lat;
	private Double lng;
	private Marker marker;
	private List<Escola> listEscola;
	private String endereco;
	private String centerGeoMap = "36.885233, 30.702323";
	private String centerEscolaIndividual = "36.885233, 30.702323";
	private String escolaNome;

	public void onGeocode(GeocodeEvent event) {
		draggableModel = new DefaultMapModel();
		List<GeocodeResult> results = event.getResults();


		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			setCenterGeoMap(center.getLat() + "," + center.getLng());
			

			GeocodeResult result = results.get(0);
			draggableModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
			System.out.println(result.getLatLng()+ result.getAddress());

			this.setLat(result.getLatLng().getLat());
			this.setLng(result.getLatLng().getLng());

			DecimalFormat df = new DecimalFormat("###.###");
			this.setLatDisplay("Lat: " + (df.format(result.getLatLng().getLat())));
			this.setLngDisplay("Lng: " + (df.format(result.getLatLng().getLng())));

			for(Marker premarker : draggableModel.getMarkers()) {
				premarker.setDraggable(true);

			}
		}
	}


	public Double getLat() {
		return lat;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	public Double getLng() {
		return lng;
	}


	public void setLng(Double lng) {
		this.lng = lng;
	}


	public void setDraggableModel(MapModel draggableModel) {
		this.draggableModel = draggableModel;
	}


	public void onMarkerDrag(MarkerDragEvent event) {
		marker = event.getMarker();
		System.out.println(+marker.getLatlng().getLat());

		DecimalFormat df = new DecimalFormat("###.###");

		this.setLat(marker.getLatlng().getLat());
		this.setLng(marker.getLatlng().getLng());

		this.setLatDisplay("Lat: " + (df.format(marker.getLatlng().getLat())));
		this.setLngDisplay("Lng: " + (df.format(marker.getLatlng().getLng())));
	}


	public String cadastrarEscola(){
		escolaDao = new  GenericDao<Escola>(Escola.class);
		System.out.println("teste");
		escola.setLat(lat);
		escola.setLng(lng);
		escolaDao.adicionar(escola);

		escola = new Escola();
		FacesMessage msg = new FacesMessage("Escola cadastrada!");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return centerGeoMap;
	}

	public void buscarGps(int id){
		System.out.println("IDDDDD"+id);
		Escola escola = escolaDao.buscar(id);
		draggableModel  = new DefaultMapModel();
		escolaDao = new GenericDao<Escola>(Escola.class);
		LatLng latLng = new LatLng(escola.getLat(), escola.getLng());
		centerEscolaIndividual = escola.getLat() + "," + escola.getLng();
		draggableModel.addOverlay(new Marker(latLng));
		System.out.println(escola.getNome());
		setEscolaNome(escola.getNome());

	}
	public void teste(){
		System.out.println("TESTE");
	}

	public void listarEscolas(){
		escolaDao = new  GenericDao<Escola>(Escola.class);
		listEscola =  escolaDao.listar();


	}




























	//getter setters

	public MapModel getDraggableModel() {
		return draggableModel;
	}


	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public void setCenterGeoMap(String centerGeoMap) {
		this.centerGeoMap = centerGeoMap;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



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
	public String getLatDisplay() {
		return latDisplay;
	}

	public void setLatDisplay(String latDisplay) {
		this.latDisplay = latDisplay;
	}

	public String getLngDisplay() {
		return lngDisplay;
	}

	public void setLngDisplay(String lngDisplay) {
		this.lngDisplay = lngDisplay;
	}




	public List<Escola> getListEscola() {
		return listEscola;
	}


	public void setListEscola(List<Escola> listEscola) {
		this.listEscola = listEscola;
	}


	public String getCenterEscolaIndividual() {
		return centerEscolaIndividual;
	}


	public void setCenterEscolaIndividual(String centerEscolaIndividual) {
		this.centerEscolaIndividual = centerEscolaIndividual;
	}


	public String getEscolaNome() {
		return escolaNome;
	}


	public void setEscolaNome(String escolaNome) {
		this.escolaNome = escolaNome;
	}


}