package com.lucida.emembler.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Email Log history to track the email history
 * 
 * @author Ravindra
 *
 */
@Entity
@Table(name = "email_log")
public class EmailLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "mail_sent_On")
	private Date mailSentOn;

	@Column(name = "mail_content", columnDefinition = "LONGTEXT")
	private String mailContent;

	@Column(name = "subject")
	private String subject;

	@Column(name = "mail_sent_by")
	private String mailSentBy;

	@Column(name = "mail_sent_to")
	private String mailSentTo;

	public EmailLog() {

	}

	public EmailLog(Date mailSentOn, String mailContent, String subject, String mailSentBy, String mailSentTo) {
		this.mailSentOn = mailSentOn;
		this.mailContent = mailContent;
		this.subject = subject;
		this.mailSentBy = mailSentBy;
		this.mailSentTo = mailSentTo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getMailSentOn() {
		return mailSentOn;
	}

	public void setMailSentOn(Date mailSentOn) {
		this.mailSentOn = mailSentOn;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getMailSentTo() {
		return mailSentTo;
	}

	public void setMailSentTo(String mailSentTo) {
		this.mailSentTo = mailSentTo;
	}

	public String getMailSentBy() {
		return mailSentBy;
	}

	public void setMailSentBy(String mailSentBy) {
		this.mailSentBy = mailSentBy;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailLog [id=");
		builder.append(id);
		builder.append(", mailSentOn=");
		builder.append(mailSentOn);
		builder.append(", mailContent=");
		builder.append(mailContent);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", mailSentBy=");
		builder.append(mailSentBy);
		builder.append(", mailSentTo=");
		builder.append(mailSentTo);
		builder.append("]");
		return builder.toString();
	}
}
