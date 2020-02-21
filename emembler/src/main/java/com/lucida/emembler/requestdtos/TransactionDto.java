package com.lucida.emembler.requestdtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Transaction request Object
 */

public class TransactionDto {

	@JsonProperty("Transaction_ID")
	private long transaction_id;

	@JsonProperty("Payment_Type")
	private String paymentType;

	@JsonProperty("Date_Processed")
	private String dateProcessed;

	@JsonProperty("Amount")
	private int amount;

	@JsonProperty("Member_API_GUID")
	private String memberApiGuid;

	@JsonProperty("Promotional_Code")
	private String promotionalCode;

	@JsonProperty("Member_Primary_Group")
	private String memberPrimaryGroup;

	@JsonProperty("First_Name")
	private String firstName;

	@JsonProperty("Last_Name")
	private String lastName;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("Member_Type")
	private String memberType;

	@JsonProperty("Current_Membership_Exp_Date")
	private String expiryDate;

	@JsonProperty("Title")
	private String title;

	@JsonProperty("Organization")
	private String organization;

	@JsonProperty("Current_Membership")
	private String currentMembership;
	
	@JsonProperty("Current_Member_City")
	private String currentMemberCity;

	@JsonProperty("Current_Member_Country")
	private String currentMemberCountry;

	@JsonProperty("Date_Member_Signup")
	private String dateMemberSignup;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDateProcessed() {
		return dateProcessed;
	}

	public void setDateProcessed(String dateProcessed) {
		this.dateProcessed = dateProcessed;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMemberApiGuid() {
		return memberApiGuid;
	}

	public void setMemberApiGuid(String memberApiGuid) {
		this.memberApiGuid = memberApiGuid;
	}

	public String getPromotionalCode() {
		return promotionalCode;
	}

	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}

	public String getMemberPrimaryGroup() {
		return memberPrimaryGroup;
	}

	public void setMemberPrimaryGroup(String memberPrimaryGroup) {
		this.memberPrimaryGroup = memberPrimaryGroup;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCurrentMemberCity() {
		return currentMemberCity;
	}

	public void setCurrentMemberCity(String currentMemberCity) {
		this.currentMemberCity = currentMemberCity;
	}

	public String getCurrentMemberCountry() {
		return currentMemberCountry;
	}

	public void setCurrentMemberCountry(String currentMemberCountry) {
		this.currentMemberCountry = currentMemberCountry;
	}

	public String getDateMemberSignup() {
		return dateMemberSignup;
	}

	public void setDateMemberSignup(String dateMemberSignup) {
		this.dateMemberSignup = dateMemberSignup;
	}

	public String getCurrentMembership() {
		return currentMembership;
	}

	public void setCurrentMembership(String currentMembership) {
		this.currentMembership = currentMembership;
	}

	@Override
	public String toString() {
		return "TransactionDto [transaction_id=" + transaction_id + ", paymentType=" + paymentType + ", dateProcessed="
				+ dateProcessed + ", amount=" + amount + ", memberApiGuid=" + memberApiGuid + ", promotionalCode="
				+ promotionalCode + ", memberPrimaryGroup=" + memberPrimaryGroup + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", memberType=" + memberType + ", expiryDate="
				+ expiryDate + ", title=" + title + ", organization=" + organization + ", currentMemberCity="
				+ currentMemberCity + ", currentMemberCountry=" + currentMemberCountry + ", dateMemberSignup="
				+ dateMemberSignup + "]";
	}

}
