package rw.mentors.ifate.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author NIYOMWUNGERI
 * @Date Aug 3, 2017
 * @Time 1:32:58 AM
 */
@Entity
@Table(name = "OBSERVATION")
public class Observation extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "RECORDING_DATE")
	private Date recordingDate;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "CASE_UUID")
	private Cases cases;
	@JoinColumn(name = "STATUS")
	private String status;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "USER_UUID")
	private User user;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}

	public Cases getCases() {
		return cases;
	}

	public void setCases(Cases cases) {
		this.cases = cases;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
