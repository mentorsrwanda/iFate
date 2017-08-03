package rw.mentors.ifate.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 3, 2017
 * @Time 9:34:42 PM
 */
@Entity
@Table(name = "LOCATION")
public class Location extends GenericDomain {

	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	@OneToOne(mappedBy="location")
	private Cases cases;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Cases getCases() {
		return cases;
	}
	public void setCases(Cases cases) {
		this.cases = cases;
	}
	
}
