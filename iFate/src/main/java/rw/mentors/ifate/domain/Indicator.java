package rw.mentors.ifate.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 10:53:19 PM
 */
@Entity
@Table(name = "INDICATOR")
public class Indicator extends Question {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "CATEGORY_UUID")
	private Category category;
	@Column(name = "INDICATOR_NAME")
	private String indicatorName;

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
