package rw.mentors.ifate.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 10:15:03 PM
 */
@Entity
@Table(name = "DOMAIN")
public class Domain extends GenericDomain {

	private static final long serialVersionUID = 1L;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "domain")
	private List<Category> categories;

	@OneToMany(mappedBy = "domain")
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
