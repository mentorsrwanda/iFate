package rw.mentors.ifate.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@SuppressWarnings("serial")
@Entity
public class Sector extends GenericDomain{
	
	
	@Column(name ="NAME",unique = true)
	private String name;
	
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn
	private District district;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public District getDistrict() {
		return district;
	}


	public void setDistrict(District district) {
		this.district = district;
	}
	
	
	
	

}
