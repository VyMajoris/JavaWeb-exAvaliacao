package br.com.fiap.map;

import java.io.Serializable;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Escola;

@ManagedBean
public class MarkersView implements Serializable {


	private static final long serialVersionUID = 6715992049291483761L;
	private MapModel simpleModel;

	@PostConstruct
	public void init() {
		simpleModel = new DefaultMapModel();
		GenericDao<Escola> escolaDao = new GenericDao<Escola>(Escola.class);
		for (Escola escola :  escolaDao.listar()) {
			String lat = null;
			String lng = null;

			if (escola.getLatLng() != null) {
				StringTokenizer st = new StringTokenizer(escola.getLatLng(), ",");
				while (st.hasMoreElements()) {
					lat = (String) st.nextElement();
					lng = (String) st.nextElement();
				}
				LatLng coord1 = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
				simpleModel.addOverlay(new Marker(coord1, escola.getNome()));
			}


		}
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}
}