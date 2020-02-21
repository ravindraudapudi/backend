package com.lucida.emembler.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * To maintain file upload history
 * @author Admin
 *
 */
@Entity
@Table(name = "csv_log")
public class CsvLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "csv_doc_name")
	private String docName;

	@Column(name = "uploaded_by")
	private String uploadedBy;

	@Column(name = "uploaded_on")
	private Date uploadedOn;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "reporting_date")
	private Date reportingDate;

	@Column(name = "status")
	private String status;

	@Column(name = "description")
	private String description;
	
	@Column(name = "doc_type")
	private String docType;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public Date getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CsvLog [id=");
		builder.append(id);
		builder.append(", docName=");
		builder.append(docName);
		builder.append(", uploadedBy=");
		builder.append(uploadedBy);
		builder.append(", uploadedOn=");
		builder.append(uploadedOn);
		builder.append(", reportingDate=");
		builder.append(reportingDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", description=");
		builder.append(description);
		builder.append(", docType=");
		builder.append(docType);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reportingDate == null) ? 0 : reportingDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CsvLog other = (CsvLog) obj;
		if (reportingDate == null) {
			if (other.reportingDate != null)
				return false;
		} else if (!reportingDate.equals(other.reportingDate))
			return false;
		return true;
	}
}
