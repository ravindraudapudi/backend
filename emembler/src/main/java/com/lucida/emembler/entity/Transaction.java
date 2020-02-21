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
 *  To maintain the transaction history
 * @author Ravindra
 *
 */
@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "transaction_id")
	private long transactionId;

	@Column(name = "amount")
	private double amount;

	@Column(name = "organization")
	private String organization;

	@Column(name = "member_Api_Guid")
	private String memberApiGuid;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "expiry_date")
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@Column(name = "Date_processed")
	@Temporal(TemporalType.DATE)
	private Date dateProcessed;

	@Column(name = "primary_group_code")
	private String primaryGroupCode;

	@Column(name = "sponsership_code")
	private String sponsershipCode;
	
	@Column(name = "member_type")
	private String memberType;
	
	@Column(name = "current_membership")
	private String currentMembership;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getMemberApiGuid() {
		return memberApiGuid;
	}

	public void setMemberApiGuid(String memberApiGuid) {
		this.memberApiGuid = memberApiGuid;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getDateProcessed() {
		return dateProcessed;
	}

	public void setDateProcessed(Date dateProcessed) {
		this.dateProcessed = dateProcessed;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public String getSponsershipCode() {
		return sponsershipCode;
	}

	public void setSponsershipCode(String sponsershipCode) {
		this.sponsershipCode = sponsershipCode;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getCurrentMembership() {
		return currentMembership;
	}

	public void setCurrentMembership(String currentMembership) {
		this.currentMembership = currentMembership;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionId=");
		builder.append(transactionId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", organization=");
		builder.append(organization);
		builder.append(", memberApiGuid=");
		builder.append(memberApiGuid);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
		builder.append(", dateProcessed=");
		builder.append(dateProcessed);
		builder.append(", primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", sponsershipCode=");
		builder.append(sponsershipCode);
		builder.append("]");
		return builder.toString();
	}

}
