package rw.mentors.ifate.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 10:59:23 PM
 */
@Entity
@Table(name="DEMOGRAPHIC")
public class Demographic extends Question{

	private static final long serialVersionUID = 1L;
	
	private String range;
	
	private String descirption;

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getDescirption() {
		return descirption;
	}

	public void setDescirption(String descirption) {
		this.descirption = descirption;
	}
	
	
	
	

}
