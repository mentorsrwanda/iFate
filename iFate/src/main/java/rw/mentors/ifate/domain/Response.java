package rw.mentors.ifate.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Response extends GenericDomain{
	
	private String comment;
	@OneToOne(cascade ={CascadeType.MERGE})
	@JoinColumn
	private Indicator indicator;
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn
	private Answer answer;
	@OneToOne
	@JoinColumn
	private Cases cases;
	@Transient
	private String ip;
	
	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Cases getCases() {
		return cases;
	}
	public void setCases(Cases cases) {
		this.cases = cases;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Indicator getIndicator() {
		return indicator;
	}
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	
	

}
