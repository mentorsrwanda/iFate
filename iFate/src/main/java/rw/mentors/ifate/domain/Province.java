package rw.mentors.ifate.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
public class Province extends GenericDomain{

	@Column(name ="PROVINCE",unique = true)
	private String name;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "province")
	private List<District> districts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}
	
	
	
	
}
