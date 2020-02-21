package com.lucida.emembler.requestdtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SponserDto {

	@JsonProperty("Sponsorship_Id")
	private int sponsorId;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Sponsor")
	private String sponsor;

	@JsonProperty("Source")
	private String sponserLevel;

	@JsonProperty("Primary_Group_Code")
	private String primaryGroupCode;

	@JsonProperty("Accounting_Year")
	private String partenershipYear;

	@JsonProperty("Invoice_Number")
	private String invoiceNumber;

	@JsonProperty("Invoice_Date")
	private String invoiceDate;

	@JsonProperty("Agreement_End_Date")
	private String partnershipEndDate;

	@JsonProperty("Amount")
	private String chapterAmount;

	@JsonProperty("Contract_Terms")
	private String invoiceTerms;

	@JsonProperty("Promotional_Code")
	private String promotionalCode;

	@JsonProperty("National Memberships")
	private String nationalAssigned;

	@JsonProperty("Memberships_Issued")
	private String chapterAssigned;

	@JsonProperty("Expiration_Date_of_Code")
	private String expirationDate;

	@JsonProperty("Relationship_With_National?")
	private String relationWithNational;

	@JsonProperty("Chapter_Contact")
	private String crm;

	@JsonProperty("Sponsor_Contact")
	private String sponserContact;

	@JsonProperty("Contract Start Date")
	private Date contractStartDate;

	@JsonProperty("Redeemed")
	private int redeemed;

	@JsonProperty("Unused")
	private int unused;

	@JsonProperty("National_Benefit?")
	private String nationalBenefits;

	@JsonProperty("paymentDate")
	private String paymentDate;

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
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

	public String getPartenershipYear() {
		return partenershipYear;
	}

	public void setPartenershipYear(String partenershipYear) {
		this.partenershipYear = partenershipYear;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPartnershipEndDate() {
		return partnershipEndDate;
	}

	public void setPartnershipEndDate(String partnershipEndDate) {
		this.partnershipEndDate = partnershipEndDate;
	}

	public String getChapterAmount() {
		return chapterAmount;
	}

	public void setChapterAmount(String chapterAmount) {
		this.chapterAmount = chapterAmount;
	}

	public String getInvoiceTerms() {
		return invoiceTerms;
	}

	public void setInvoiceTerms(String invoiceTerms) {
		this.invoiceTerms = invoiceTerms;
	}

	public String getPromotionalCode() {
		return promotionalCode;
	}

	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}

	public String getNationalAssigned() {
		return nationalAssigned;
	}

	public void setNationalAssigned(String nationalAssigned) {
		this.nationalAssigned = nationalAssigned;
	}

	public String getChapterAssigned() {
		return chapterAssigned;
	}

	public void setChapterAssigned(String chapterAssigned) {
		this.chapterAssigned = chapterAssigned;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getRelationWithNational() {
		return relationWithNational;
	}

	public void setRelationWithNational(String relationWithNational) {
		this.relationWithNational = relationWithNational;
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

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public int getRedeemed() {
		return redeemed;
	}

	public void setRedeemed(int redeemed) {
		this.redeemed = redeemed;
	}

	public int getUnused() {
		return unused;
	}

	public void setUnused(int unused) {
		this.unused = unused;
	}

	public String getNationalBenefits() {
		return nationalBenefits;
	}

	public void setNationalBenefits(String nationalBenefits) {
		this.nationalBenefits = nationalBenefits;
	}

	public int getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(int sponsorId) {
		this.sponsorId = sponsorId;
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SponserDto [sponsorId=");
		builder.append(sponsorId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", sponsor=");
		builder.append(sponsor);
		builder.append(", sponserLevel=");
		builder.append(sponserLevel);
		builder.append(", primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", partenershipYear=");
		builder.append(partenershipYear);
		builder.append(", invoiceNumber=");
		builder.append(invoiceNumber);
		builder.append(", invoiceDate=");
		builder.append(invoiceDate);
		builder.append(", partnershipEndDate=");
		builder.append(partnershipEndDate);
		builder.append(", chapterAmount=");
		builder.append(chapterAmount);
		builder.append(", invoiceTerms=");
		builder.append(invoiceTerms);
		builder.append(", promotionalCode=");
		builder.append(promotionalCode);
		builder.append(", nationalAssigned=");
		builder.append(nationalAssigned);
		builder.append(", chapterAssigned=");
		builder.append(chapterAssigned);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
		builder.append(", relationWithNational=");
		builder.append(relationWithNational);
		builder.append(", crm=");
		builder.append(crm);
		builder.append(", sponserContact=");
		builder.append(sponserContact);
		builder.append(", contractStartDate=");
		builder.append(contractStartDate);
		builder.append(", redeemed=");
		builder.append(redeemed);
		builder.append(", unused=");
		builder.append(unused);
		builder.append(", nationalBenefits=");
		builder.append(nationalBenefits);
		builder.append("]");
		return builder.toString();
	}

}
