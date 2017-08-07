package rw.mentors.ifate.usecase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rw.mentors.ifate.domain.Answer;
import rw.mentors.ifate.domain.Cases;
import rw.mentors.ifate.domain.Category;
import rw.mentors.ifate.domain.District;
import rw.mentors.ifate.domain.Domain;
import rw.mentors.ifate.domain.EAgeGroup;
import rw.mentors.ifate.domain.Indicator;
import rw.mentors.ifate.domain.Province;
import rw.mentors.ifate.domain.Response;
import rw.mentors.ifate.service.AdminService;

@Component
@ManagedBean(eager = true)
@ViewScoped
public class RepportingBean implements Serializable {

	@Autowired
	private AdminService adminservice;

	private Domain domain;

	private String globalId;

	private String first;

	private Cases cases;

	private Category category;

	private Province province;

	private String provinceId;

	private String districtId;

	private List<Answer> answerlist;

	private List<Response> responseList;

	public RepportingBean() {
		province = new Province();
		provinceId = new String();
		districtId = new String();
		domain = new Domain();
		category = new Category();
		answerlist = new ArrayList<Answer>();
		globalId = new String();
		first = new String();
		cases = new Cases();
		responseList = new ArrayList<Response>();
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public Province getProvince() {
		return province;
	}

	public Cases getCases() {
		return cases;
	}

	public void setCases(Cases cases) {
		this.cases = cases;
	}

	public List<Response> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<Response> responseList) {
		this.responseList = responseList;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public List<Answer> getAnswerlist() {
		return answerlist;
	}

	public void setAnswerlist(List<Answer> answerlist) {
		this.answerlist = answerlist;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public void successMessages(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void errorMessages(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String downloadDomain(Domain domain) {
		this.domain = domain;
		return "repportingCategory";
	}

	public String downloadCategory(Category category) {
		this.category = category;
		return "RepportingForm";
	}

	public List<Indicator> closedIndicators() {
		List<Indicator> list = new ArrayList<Indicator>();
		for (Indicator ind : category.getIndicators()) {
			if (ind.getAnswers().isEmpty() == false) {
				if (list.contains(ind) == false) {
					list.add(ind);
				}
			}
		}
		return list;
	}

	public void addingtoList(Indicator indica) {
		try {
			Answer newAnswer = adminservice.findAnswer(globalId);
			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();
			String ip = httpServletRequest.getRemoteAddr();

			for (Answer answer : answerlist) {
				if (answer.getQuestion().getId().equals(newAnswer.getQuestion().getId())) {
					answerlist.remove(answer);
					break;
				}
			}
			// newAnswer.setIp(ip);
			newAnswer.setQuestion(newAnswer.getQuestion());
			answerlist.add(newAnswer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkopenAnswer(Indicator indi) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		String ip = httpServletRequest.getRemoteAddr();
		Indicator indica = indi;
		Response response = new Response();
		response.setComment(first);
		response.setIndicator(indica);
		response.setState(true);
		for (Response inresponse : responseList) {
			if (inresponse.getIndicator().getId().equals(response.getIndicator().getId())) {
				responseList.remove(inresponse);
				break;
			}
		}
		// response.setIp(ip);
		responseList.add(response);
		first = new String();
		indica = new Indicator();

	}

	public void recordResponse() {
		try {
			for (Answer answers : answerlist) {
				Response response = new Response();
				response.setAnswer(answers);
				response.setState(true);
				response.setIndicator(answers.getQuestion());
				responseList.add(response);
			}
			District district = adminservice.findDistrcit(districtId);
			cases.setDistrict(district);
			cases.setReportedDate(new Date());
			cases.setDomain(domain);
			adminservice.addCase(cases);
			for (Response resp : responseList) {
				resp.setCases(cases);
				adminservice.addResponse(resp);
			}
			successMessages("Response is well recorded : and ur case number is :" + cases.getId(), "");
		} catch (Exception e) {
			errorMessages(e.getMessage(), e.getMessage());
		}
		answerlist = new ArrayList<Answer>();
		responseList = new ArrayList<Response>();
		first = new String();
		globalId = new String();
		cases = new Cases();
        districtId = new String();
        
	}

	public List<District> provinceDistricts() {
		List<District> list = new ArrayList<District>();
		if (provinceId.length() > 0) {
			province = adminservice.findProvince(provinceId);
			list = province.getDistricts();
		} else {
			list = new ArrayList<District>();

		}
		return list;
	}

	public List<Province> provinces() {
		List<Province> list = new ArrayList<Province>();
		for (Province province : adminservice.allProvinces()) {
			if (list.contains(province) == false) {
				list.add(province);
			}

		}
		return list;
	}

	public List<EAgeGroup> agegroups() {
		List<EAgeGroup> ages = new ArrayList<EAgeGroup>();
		ages = Arrays.asList(EAgeGroup.values());
		return ages;
	}

}
