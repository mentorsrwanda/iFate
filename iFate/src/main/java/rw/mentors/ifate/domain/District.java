package rw.mentors.ifate.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
public class District extends GenericDomain{
	
	@Column(name ="DISTRICT",unique = true)
	private String name;
	
	@ManyToOne(cascade ={CascadeType.MERGE})
	@JoinColumn
	private Province province;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "district")
	private List<Sector> sectors;
	
	@OneToMany(mappedBy = "district")
	private List<Cases> cases;
	
	
	public List<Cases> getCases() {
		return cases;
	}

	public void setCases(List<Cases> cases) {
		this.cases = cases;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}
	
	
	

}
