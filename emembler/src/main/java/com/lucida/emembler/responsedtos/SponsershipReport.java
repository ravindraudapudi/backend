package com.lucida.emembler.responsedtos;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SponsershipReport {

	private int id;
	private int sponsorId;
	private String sponsor;
	private double amount;
	private String type;
	private String invoiceNumber;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date invoiceDate;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date aggrementEndDate;

	private String partenershipYear;
	private String discountCode;
	private int issued;
	private int redeemed;
	private int unused;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date expirationDate;

	private String contractTerm;
	private String sponsorContact;
	private String relationWithNational;
	private String nationalBenefit;

	private String crm;
	private String sponserLevel;
	private String sponserContact;
	private String primaryGroupCode;

	@JsonFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getAggrementEndDate() {
		return aggrementEndDate;
	}

	public void setAggrementEndDate(Date aggrementEndDate) {
		this.aggrementEndDate = aggrementEndDate;
	}

	public String getPartenershipYear() {
		return partenershipYear;
	}

	public void setPartenershipYear(String partenershipYear) {
		this.partenershipYear = partenershipYear;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public int getIssued() {
		return issued;
	}

	public void setIssued(int issued) {
		this.issued = issued;
	}

	public int getRedeemed() {
		return redeemed;
	}

	public void setRedeemed(int redeemed) {
		this.redeemed = redeemed;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getContractTerm() {
		return contractTerm;
	}

	public void setContractTerm(String contractTerm) {
		this.contractTerm = contractTerm;
	}

	public int getUnused() {
		return unused;
	}

	public void setUnused(int unused) {
		this.unused = unused;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getSponserContact() {
		return sponserContact;
	}

	public void setSponserContact(String sponserContact) {
		this.sponserContact = sponserContact;
	}

	public String getRelationWithNational() {
		return relationWithNational;
	}

	public void setRelationWithNational(String relationWithNational) {
		this.relationWithNational = relationWithNational;
	}

	public String getSponsorContact() {
		return sponsorContact;
	}

	public void setSponsorContact(String sponsorContact) {
		this.sponsorContact = sponsorContact;
	}

	public String getNationalBenefit() {
		return nationalBenefit;
	}

	public void setNationalBenefit(String nationalBenefit) {
		this.nationalBenefit = nationalBenefit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSponserLevel() {
		return sponserLevel;
	}

	public void setSponserLevel(String sponserLevel) {
		this.sponserLevel = sponserLevel;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(int sponsorId) {
		this.sponsorId = sponsorId;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SponsershipReport [id=");
		builder.append(id);
		builder.append(", sponsor=");
		builder.append(sponsor);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", invoiceNumber=");
		builder.append(invoiceNumber);
		builder.append(", invoiceDate=");
		builder.append(invoiceDate);
		builder.append(", aggrementEndDate=");
		builder.append(aggrementEndDate);
		builder.append(", partenershipYear=");
		builder.append(partenershipYear);
		builder.append(", discountCode=");
		builder.append(discountCode);
		builder.append(", issued=");
		builder.append(issued);
		builder.append(", redeemed=");
		builder.append(redeemed);
		builder.append(", unused=");
		builder.append(unused);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
		builder.append(", contractTerm=");
		builder.append(contractTerm);
		builder.append(", sponsorContact=");
		builder.append(sponsorContact);
		builder.append(", relationWithNational=");
		builder.append(relationWithNational);
		builder.append(", nationalBenefit=");
		builder.append(nationalBenefit);
		builder.append(", crm=");
		builder.append(crm);
		builder.append(", sponserLevel=");
		builder.append(sponserLevel);
		builder.append(", sponserContact=");
		builder.append(sponserContact);
		builder.append("]");
		return builder.toString();
	}
}
