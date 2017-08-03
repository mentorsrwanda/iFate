package rw.mentors.ifate.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 2, 2017
 * @Time 11:26:57 PM
 */
@Entity
@Table(name = "CASES")
public class Cases extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAMES")
	private String names;
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "REPORTED_DATE")
	private Date reportedDate;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "COMMENT")
	private String comment;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "DOMAIN_UUID")
	private Domain domain;

	@OneToOne(mappedBy = "question")
	private Answer answer;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "LOCATION_UUID")
	private Location location;

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
