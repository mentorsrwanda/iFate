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
 * @Time 11:04:58 PM
 */
@Entity
@Table(name = "DATA_DEFINITION")
public class DataDefinition extends GenericDomain {
	private static final long serialVersionUID = 1L;
	@Column(name = "VALUE")
	private int value;
	@Column(name = "MEANING")
	private String meaning;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "QUESTION_UUID")
	private Question question;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
