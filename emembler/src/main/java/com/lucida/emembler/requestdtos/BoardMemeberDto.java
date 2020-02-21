package com.lucida.emembler.requestdtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Lucida Membership board request object
 */
public class BoardMemeberDto {

	private int id;
	private String firstName;
	private String lastName;
	private String apiGuid;
	private String email;
	private String memberRole;
	private String phone;
	private String alternateEmail;
	private String status;
	private String organisation;
	private String membershipType;
	private String employerName;
	private String middleName;
	private Date memberSignup;
	private String emailBounced;
	private String gender;
	private String homePhoneAreaCode;
	private String mobileAreaCode;
	private String mobile;
	private String professionalTitle;
	private String employerPhoneAreaCode;
	private String employerPhone;
	
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date expiryDate;
	
	private String primaryGroupCode;
	private double amount;
	private String paymentType;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date processedDate;
	
	private String sponsor;
	private String promotionalCode;
	
	

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getMemberSignup() {
		return memberSignup;
	}

	public void setMemberSignup(Date memberSignup) {
		this.memberSignup = memberSignup;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}



	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public String getPromotionalCode() {
		return promotionalCode;
	}

	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getApiGuid() {
		return apiGuid;
	}

	public void setApiGuid(String apiGuid) {
		this.apiGuid = apiGuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getEmailBounced() {
		return emailBounced;
	}

	public void setEmailBounced(String emailBounced) {
		this.emailBounced = emailBounced;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomePhoneAreaCode() {
		return homePhoneAreaCode;
	}

	public void setHomePhoneAreaCode(String homePhoneAreaCode) {
		this.homePhoneAreaCode = homePhoneAreaCode;
	}

	public String getMobileAreaCode() {
		return mobileAreaCode;
	}

	public void setMobileAreaCode(String mobileAreaCode) {
		this.mobileAreaCode = mobileAreaCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

	public String getEmployerPhoneAreaCode() {
		return employerPhoneAreaCode;
	}

	public void setEmployerPhoneAreaCode(String employerPhoneAreaCode) {
		this.employerPhoneAreaCode = employerPhoneAreaCode;
	}

	public String getEmployerPhone() {
		return employerPhone;
	}

	public void setEmployerPhone(String employerPhone) {
		this.employerPhone = employerPhone;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
}
