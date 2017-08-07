package rw.mentors.ifate.usecase;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.bouncycastle.asn1.ocsp.ResponderID;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rw.mentors.ifate.domain.Answer;
import rw.mentors.ifate.domain.Category;
import rw.mentors.ifate.domain.Domain;
import rw.mentors.ifate.domain.EQuestionType;
import rw.mentors.ifate.domain.Indicator;
import rw.mentors.ifate.service.AdminService;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 3, 2017
 * @Time 2:59:41 AM
 */
@Component
@ManagedBean(eager = true)
@ViewScoped
public class AdministratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminService adminService;

	private Domain domain;

	private Category category;

	private Indicator indicator;

	private Answer answer;

	public AdministratorBean() {
		domain = new Domain();
		category = new Category();
		indicator = new Indicator();
		answer = new Answer();
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public List<Domain> allDomain() {
		return adminService.allDomain();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
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

	public void warningMessages(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void errorMessages(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void downloadDomain(Domain domain) {
		this.domain = domain;
	}

	public String fetchDomain(Domain domain) {
		this.domain = domain;
		return "DomainCategories";
	}

	public String fetchIndicator(Indicator indicator) {
		this.indicator = indicator;
		return "IndicatorAnswers";
	}

	public void addDomain() {
		try {

			if (domain.getName().length() > 0) {
				if (domain.getDescription().length() > 0) {
					String msg = adminService.addDomain(domain);
					successMessages("Success", msg);
					domain = new Domain();
					allDomain();
					RequestContext.getCurrentInstance().execute("PF(newdomain).hide()");
				} else {
					errorMessages("PLease provide dname", "");
				}
			} else {
				errorMessages("Please provide descirpiton", "");
			}

		} catch (Exception e) {
			errorMessages("Error:", e.getMessage());
		}
	}

	public void deleteDomain() {
		try {
			String sms = adminService.deleteDomain(domain);
			successMessages(sms, sms);
			domain = new Domain();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recordCategory() {
		try {
			String code = "";
			if (domain.getCategories().isEmpty() == false) {
				code = domain.getCategories().get(domain.getCategories().size() - 1).getCode();
			}
			category.setCode(generatingcode(code, this.domain));
			category.setDomain(domain);
			category.setState(true);
			String sms = adminService.addcategory(this.domain, this.category);
			successMessages(sms, sms);
			category = new Category();
			domain = adminService.findDomain(this.domain.getId());
		} catch (Exception e) {
			e.printStackTrace();
			errorMessages(e.getMessage(), e.getMessage());
		}
	}

	public String fetchCategory(Category category) {
		this.category = category;
		return "CategoryQuestions";
	}

	public String generatingcode(String code, Domain domain) {
		String alphabets[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		String newValue = "";
		if (code.length() > 0) {
			code = code.replace(".", ",");
			String value[] = code.split(",");

			if (value[1].equalsIgnoreCase("Z") == false) {
				for (int i = 0; i < alphabets.length; i++) {
					if (value[1].equals(alphabets[i])) {
						value[1] = alphabets[i + 1];
						break;
					}
				}
			} else {
				value[0] = (Integer.parseInt(value[0]) + 1) + "";
				value[1] = alphabets[0];
			}
			newValue = value[0] + "." + value[1];
		} else {
			int position = 0;
			for (int i = 0; i < allDomain().size(); i++) {
				if (allDomain().get(i).getId().equals(domain.getId())) {
					position = i + 1;
					break;
				}
			}
			newValue = String.valueOf(position) + ".A";
		}

		return newValue;
	}

	public EQuestionType[] types() {
		return EQuestionType.values();
	}

	public List<EQuestionType> alltypes() {
		List<EQuestionType> list = Arrays.asList(types());
		return list;
	}

	public void recordIndicator() {
		try {
			indicator.setState(true);
			String sms = adminService.addIndicator(this.indicator, this.category);
			indicator = new Indicator();
			category = adminService.findCategory(this.category.getId());
			successMessages(sms, sms);

		} catch (Exception e) {
			e.printStackTrace();
			errorMessages(e.getMessage(), e.getMessage());
		}
	}

	public void recordAnswer() {
		try {
			answer.setQuestion(this.indicator);
			String sms = adminService.answer(answer, this.indicator);
			successMessages(sms, sms);
			answer = new Answer();
			indicator = adminService.findIndicator(this.indicator.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
