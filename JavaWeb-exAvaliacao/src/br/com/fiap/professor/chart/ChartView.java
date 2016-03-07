package br.com.fiap.professor.chart;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Disciplina;
import br.com.fiap.entity.Nota;
import br.com.fiap.entity.Professor;
import br.com.fiap.entity.TipoNotaEnum;

import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@ViewScoped
public class ChartView implements Serializable {
	private static final long serialVersionUID = -4672395420994836221L;
	private BarChartModel barModel;
	private HorizontalBarChartModel horizontalBarModel;
	private GenericDao<Professor> professorDao;
	private GenericDao<Nota> notaDao;
	private Professor professor;
	private HttpSession session;

	@PostConstruct
	public void init() {
		System.out.println("Chart");

		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		professorDao = new GenericDao<Professor>(Professor.class);
		notaDao = new GenericDao<Nota>(Nota.class);
		professor = professorDao.buscar((Long) session.getAttribute("rmProfessor"));
		createBarModel();
	}

	private Double calculaMedia(List<Nota> listNota){
		Double media = 0D;
		int qtdNotas = 0;
		for (Nota nota : listNota) {
			if (nota.getValor() != null) {
				media += nota.getValor();
				qtdNotas++;
			}
		} 
		if (media!=null ){
			media = media/qtdNotas;
			System.out.println("MEDIA = "+media);
		}
		return media;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		for (TipoNotaEnum tipo : TipoNotaEnum.values()) {
			ChartSeries tipoChart = new ChartSeries();
			tipoChart.setLabel(tipo.toString());
			Query findNotaPorDisciplinaETipo = JpaUtil.getHibSession().getNamedQuery("findNotaPorDisciplinaETipo");
			findNotaPorDisciplinaETipo.setParameter("tipo", tipo);
			for(Disciplina disc : professor.getDisciplinas()){
				findNotaPorDisciplinaETipo.setParameter("idDisciplina", disc.getId());
				tipoChart.set(disc.getNome(), calculaMedia(findNotaPorDisciplinaETipo.list()));
			}
			model.addSeries(tipoChart);
			model.setBarWidth(45);
			model.setAnimate(true);
			model.setSeriesColors("1ABC9C, 34495E, 4D90FE");
			model.setMouseoverHighlight(true);
			model.setBarMargin(45);
			model.setShowPointLabels(true);
			model.setExtender("notaExtender");
		}
		return model;
	}


	private void createBarModel() {
		barModel = initBarModel();
		barModel.setTitle("Média de notas dos meus alunos por disciplina");
		barModel.setLegendPosition("ne");
		
		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Disciplinas");
		
		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Nota");
		yAxis.setMin(0);
		yAxis.setMax(12);
	}




	public GenericDao<Professor> getProfessorDao() {
		return professorDao;
	}

	public void setProfessorDao(GenericDao<Professor> professorDao) {
		this.professorDao = professorDao;
	}

	public GenericDao<Nota> getNotaDao() {
		return notaDao;
	}

	public void setNotaDao(GenericDao<Nota> notaDao) {
		this.notaDao = notaDao;
	}
	public BarChartModel getBarModel() {
		return barModel;
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	public Professor getProfessor() {
		return professor;
	}



	public void setProfessor(Professor professor) {
		this.professor = professor;
	}



}