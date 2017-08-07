package rw.mentors.ifate.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 10:39:09 PM
 */
@Entity
@Table(name = "QUESTION")
public class Question extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(name = "CODE")
	private String code;
	@Column(name = "NAME")
	private String name;
	@Column(name = "QUESTION_TYPE")
	private String questionType;

	@OneToMany(mappedBy = "question")
	private List<DataDefinition> dataDefinitions;

	@OneToMany(mappedBy = "question")
	private List<Answer> answers;
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public List<DataDefinition> getDataDefinitions() {
		return dataDefinitions;
	}

	public void setDataDefinitions(List<DataDefinition> dataDefinitions) {
		this.dataDefinitions = dataDefinitions;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	

}
