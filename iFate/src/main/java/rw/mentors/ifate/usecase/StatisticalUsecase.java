package rw.mentors.ifate.usecase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import rw.mentors.ifate.domain.Answer;
import rw.mentors.ifate.domain.Category;
import rw.mentors.ifate.domain.Domain;
import rw.mentors.ifate.domain.Indicator;
import rw.mentors.ifate.domain.Response;
import rw.mentors.ifate.service.AdminService;

@Component
@ManagedBean(eager = true)
@ViewScoped
public class StatisticalUsecase implements Serializable {

	@Autowired
	private AdminService adminservice;

	private String domainId;

	private Domain domain;

	private Category category;

	private String choosenIndicator;

	private String closedQuestion;

	private Map<String, List<Map.Entry<String, Integer>>> map;

	private List<Indicator> open;

	private List<Map.Entry<String, List<Response>>> openindicators;

	private HashMap<String, List<Response>> responses;

	private List<Map.Entry<String, Integer>> datas;

	private BarChartModel barModel;

	private List<Map.Entry<String, List<Map.Entry<String, Integer>>>> frequencies;

	private boolean choosen;

	private SimpleDateFormat sdf;

	public StatisticalUsecase() {
		responses = new HashMap<String, List<Response>>();
		open = new ArrayList<Indicator>();
		domain = new Domain();
		choosenIndicator = new String();
		domainId = new String();
		category = new Category();
		openindicators = new ArrayList<Map.Entry<String, List<Response>>>();
		datas = new ArrayList<Map.Entry<String, Integer>>();
		choosen = false;
		map = new HashMap<String, List<Map.Entry<String, Integer>>>();
		frequencies = new ArrayList<Map.Entry<String, List<Map.Entry<String, Integer>>>>();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		closedQuestion = new String();
	}

	public String getClosedQuestion() {
		return closedQuestion;
	}

	public void setClosedQuestion(String closedQuestion) {
		this.closedQuestion = closedQuestion;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getChoosenIndicator() {
		return choosenIndicator;
	}

	public void setChoosenIndicator(String choosenIndicator) {
		this.choosenIndicator = choosenIndicator;
	}

	public List<Map.Entry<String, List<Response>>> getOpenindicators() {
		return openindicators;
	}

	public void setOpenindicators(List<Map.Entry<String, List<Response>>> openindicators) {
		this.openindicators = openindicators;
	}

	public boolean isChoosen() {
		return choosen;
	}

	public void setChoosen(boolean choosen) {
		this.choosen = choosen;
	}

	public List<Map.Entry<String, Integer>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map.Entry<String, Integer>> datas) {
		this.datas = datas;
	}

	public List<Map.Entry<String, List<Map.Entry<String, Integer>>>> getFrequencies() {
		return frequencies;
	}

	public void setFrequencies(List<Map.Entry<String, List<Map.Entry<String, Integer>>>> frequencies) {
		this.frequencies = frequencies;
	}

	public Map<String, List<Map.Entry<String, Integer>>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<Map.Entry<String, Integer>>> map) {
		this.map = map;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public HashMap<String, List<Response>> getResponses() {
		return responses;
	}

	public void setResponses(HashMap<String, List<Response>> responses) {
		this.responses = responses;
	}

	public List<Category> categories() {
		List<Category> list = new ArrayList<Category>();
		if (domainId.length() > 0) {
			domain = adminservice.findDomain(domainId);
			list = domain.getCategories();
		} else {
			list = new ArrayList<Category>();
		}
		return list;
	}
	
	private void testingValue(){
		allOccurences();
		createBarModel();
		
	}
	
	public void dwonload()
	{
		testingValue();
	}

	public void filleMap(Category categorie) {
		choosen = false;
		frequencies = new ArrayList<Map.Entry<String, List<Map.Entry<String, Integer>>>>();
		datas = new ArrayList<Map.Entry<String, Integer>>();
		map = new HashMap<String, List<Map.Entry<String, Integer>>>();
		open = new ArrayList<Indicator>();
		List<Indicator> list = new ArrayList<Indicator>();

		for (Indicator indicator : categorie.getIndicators()) {

			if (indicator.getAnswers().isEmpty() == false) {
				list.add(indicator);
			} else {
				open.add(indicator);
			}

		}

		Set<Map.Entry<String, Integer>> freq;
		for (Indicator indicator : list) {
			HashMap<String, Integer> mm = new HashMap<String, Integer>();
			for (Answer answer : indicator.getAnswers()) {

				int i = 0;
				for (Response response : adminservice.allResponses()) {
					if (response.getAnswer() != null) {
						if (response.getAnswer().getId().equals(answer.getId())) {
							i++;
						} else {
							i = i;
						}
					}
				}
				mm.put(answer.getOpenAnswer(), i);

			}
			freq = mm.entrySet();
			datas = new ArrayList<Map.Entry<String, Integer>>(freq);
			map.put(indicator.getName(), datas);
		}
		Set<Map.Entry<String, List<Map.Entry<String, Integer>>>> frequesncySet = map.entrySet();
		frequencies = new ArrayList<Map.Entry<String, List<Map.Entry<String, Integer>>>>(frequesncySet);
		fillOpenIndicator();
		choosen = true;
	}

	public void fillOpenIndicator() {
		responses = new HashMap<String, List<Response>>();
		for (Indicator indicator : open) {
			List<Response> list = new ArrayList<Response>();
			for (Response response : adminservice.allResponses()) {
				if (response.getIndicator().getId().equals(indicator.getId())) {
					list.add(response);
				}
			}
			responses.put(indicator.getName(), list);
		}
		Set<Map.Entry<String, List<Response>>> frequesncySet = responses.entrySet();
		openindicators = new ArrayList<Map.Entry<String, List<Response>>>(frequesncySet);
	}

	public List<Response> responseByIndicator() {
		List<Response> list = new ArrayList<Response>();
		if (choosenIndicator.length() > 0) {
			if (responses.containsKey(choosenIndicator)) {
				list = responses.get(choosenIndicator);
			}
		} else {
			list = new ArrayList<Response>();
		}
		return list;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		ChartSeries boys = new ChartSeries();
		boys.setLabel("Indicators");
		if (map.containsKey(closedQuestion)) {
			for (Map.Entry<String, Integer> values : map.get(closedQuestion)) {
				boys.set(values.getKey(), values.getValue());
			}

		}

		model.addSeries(boys);
		return model;
	}

	public int allOccurences() {
		int valeuer = 0;
		if (closedQuestion.length() > 0) {
			for (Map.Entry<String, Integer> hh : map.get(closedQuestion)) {
				valeuer += hh.getValue();
			}
		}
		return valeuer;
	}

	private void createBarModel() {

		barModel = initBarModel();
		barModel.setTitle("Number of Occurences");
		barModel.setAnimate(true);
		barModel.setLegendPosition("ne");
		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Values");
		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel(" Occurences");
		yAxis.setMin(0);
		yAxis.setMax(allOccurences());
	}
	
	

}
