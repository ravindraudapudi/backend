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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * To store the Sponsorship details
 * 
 * @author Ravindra
 *
 */
@Entity
@Table(name = "sponsor")
public class Sponsor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "sponsor_id")
	private int sponsorId;

	@Column(name = "type")
	private String type;

	@Column(name = "sponsor", length = 3000)
	@JsonProperty("sponsor")
	private String sponsor;

	@Column(name = "amount", columnDefinition = "Decimal(10,2) default '0.00'")
	@JsonProperty("amount")
	private double amount;

	@Column(name = "invoiceNumber")
	@JsonProperty("invoiceNumber")
	private String invoiceNumber;

	@Column(name = "invoiceDate")
	@JsonFormat(pattern = "MM/dd/yyyy")
	@JsonProperty("invoiceDate")
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;

	@Column(name = "aggrement_end_date")
	@JsonFormat(pattern = "MM/dd/yyyy")
	@JsonProperty("aggrementEndDate")
	@Temporal(TemporalType.DATE)
	private Date aggrementEndDate;

	@Column(name = "partenershipYear")
	@JsonProperty("partenershipYear")
	private String partenershipYear;

	@Column(name = "discount_code", length = 3000)
	@JsonProperty("discountCode")
	private String discountCode;

	@Column(name = "relationWithNational")
	@JsonProperty("relationWithNational")
	private String relationWithNational;

	@Column(name = "crm")
	@JsonProperty("crm")
	private String crm;

	@Column(name = "sponser_contact")
	@JsonProperty("sponserContact")
	private String sponserContact;

	@Column(name = "contract_endDate")
	private Date contractEndDate;

	@Column(name = "sponser_Level")
	@JsonProperty("sponserLevel")
	private String sponserLevel;

	@Column(name = "primary_group_code")
	@JsonProperty("primaryGroupCode")
	private String primaryGroupCode;

	@Column(name = "assigned")
	@JsonProperty("issued")
	private int assigned;

	@Column(name = "redeemed")
	private int redeemed;

	@Column(name = "expiration_date")
	@JsonFormat(pattern = "MM/dd/yyyy")
	@JsonProperty("expirationDate")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@Column(name = "invoice_term", columnDefinition = "LONGTEXT")
	@JsonProperty("contractTerm")
	private String invoiceTerms;

	@Column(name = "national_benefits")
	@JsonProperty("nationalBenefit")
	private String nationalBenefits;

	@Column(name = "paymentDate")
	@JsonFormat(pattern = "MM/dd/yyyy")
	@JsonProperty("paymentDate")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
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

	public int getAssigned() {
		return assigned;
	}

	public void setAssigned(int assigned) {
		this.assigned = assigned;
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

	public String getInvoiceTerms() {
		return invoiceTerms;
	}

	public void setInvoiceTerms(String invoiceTerms) {
		this.invoiceTerms = invoiceTerms;
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

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
