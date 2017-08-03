package rw.mentors.ifate.usecase;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

@Component
@ManagedBean(eager = true, name = "access")
@SessionScoped
public class PageaccessUsecase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String access;

	public PageaccessUsecase() {
		access = new String();
		if (access == "" || access.isEmpty()) {
			access = "cases";
		}
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String accessRequest(String page, String side) {
		access = side;

		System.out.println("The Page To be accessed will be :" + side);
		try {
			if (side.length() > 0) {
				this.access = side;
			} else {
				this.access = "cases";
			}
		} catch (Exception e) {
			page = "empty";
			e.printStackTrace();
		}

		return page;
	}

}
