package rw.mentors.ifate.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 3, 2017
 * @Time 1:32:39 AM
 */
@Entity
@Table(name = "ANSWER")
public class Answer extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(name = "OPEN_ANSWER")
	private String openAnswer;
	
	@Column(name = "CLOSED_ANSWER")
	private int closedAnswer;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "QUESTION_UUID")
	private Indicator question;
	
	
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "CASE_UUID")
	private Cases cases;
	
	@Transient
	private String ip;
	
	
	
    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOpenAnswer() {
		return openAnswer;
	}

	public void setOpenAnswer(String openAnswer) {
		this.openAnswer = openAnswer;
	}

	public int getClosedAnswer() {
		return closedAnswer;
	}

	public void setClosedAnswer(int closedAnswer) {
		this.closedAnswer = closedAnswer;
	}

	public Indicator getQuestion() {
		return question;
	}

	public void setQuestion(Indicator question) {
		this.question = question;
	}

	public Cases getCases() {
		return cases;
	}

	public void setCases(Cases cases) {
		this.cases = cases;
	}

	
	
	

}
