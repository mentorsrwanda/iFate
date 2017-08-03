package rw.mentors.ifate.usecase;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@Component
@ManagedBean(eager = true,name = "access")
@RequestScoped
public class PageaccessUsecase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String access;

	public PageaccessUsecase() {
		access = "cases";
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String accessRequest(String page, String side) {
		String accesspage = "";
		access = side;
		
		System.out.println("The Page To be accessed will be :"+side);
		try {
			if(side.length() >0)
			{
				this.access = side;
			}else{
				this.access = "cases";
			}
		} catch (Exception e) {
			accesspage = "empty";
			e.printStackTrace();
		}

		return accesspage;
	}

}
