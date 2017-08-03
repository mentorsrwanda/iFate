package rw.mentors.ifate.usecase;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rw.mentors.ifate.domain.Domain;
import rw.mentors.ifate.service.AdminService;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 3, 2017
 * @Time 2:59:41 AM
 */
@Component
@ManagedBean
@ViewScoped
public class AdminBean {
	
	@Autowired
	private AdminService adminService;
	private Domain domain = new Domain();

	public void addDomain() {
		try {
			String msg = adminService.addDomain(domain);
			successMessages("Success", msg);
		} catch (Exception e) {
			errorMessages("Error:", e.getMessage());
		}
	}

	private List<Domain> all = new ArrayList<Domain>();
	@PostConstruct
	public void init(){
		all = allDomain();
	}
	public List<Domain> allDomain() {
		return adminService.allDomain();
	}
	
	

	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public List<Domain> getAll() {
		return all;
	}
	public void setAll(List<Domain> all) {
		this.all = all;
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

}
