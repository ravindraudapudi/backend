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

@Entity
@Table(name = "members_version_data")
public class MembersVersionData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "reporting_date")
	private Date reportingDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiry_date")
	private Date expiryDate;

	@Column(name = "guid")
	private String guid;
	
	@Column(name = "primary_group_code")
	private String primaryGroupCode;
	
	@Column(name = "transaction_id")
	private long transactionId;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_processed")
	private Date dateProcessed;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "promotional_Code")
	private String promotionalCode;

	@Column(name = "membership_type")
	private String memebrshipType;

	@Column(name = "amount")
	private int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getDateProcessed() {
		return dateProcessed;
	}

	public void setDateProcessed(Date dateProcessed) {
		this.dateProcessed = dateProcessed;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPromotionalCode() {
		return promotionalCode;
	}

	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}

	public String getMemebrshipType() {
		return memebrshipType;
	}

	public void setMemebrshipType(String memebrshipType) {
		this.memebrshipType = memebrshipType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MembersVersionData [id=");
		builder.append(id);
		builder.append(", reportingDate=");
		builder.append(reportingDate);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
		builder.append(", guid=");
		builder.append(guid);
		builder.append(", primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append(", dateProcessed=");
		builder.append(dateProcessed);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", promotionalCode=");
		builder.append(promotionalCode);
		builder.append(", memebrshipType=");
		builder.append(memebrshipType);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
	
	
}
