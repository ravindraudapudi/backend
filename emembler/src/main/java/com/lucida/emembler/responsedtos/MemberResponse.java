package com.lucida.emembler.responsedtos;

import java.util.Date;

/**
 * Response entities for members data
 * 
 * @author Lucida
 *
 */
public class MemberResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String guid;
	private String company;
	private String emailBounced;
	private String alternateEmail;
	private Date dateProcessed;
	private String sponsershipCode;
	private String phone;
	private Date currentDate;
	private Date expiryDate;
	private Date memberSince;
	private Date renewalDate;
	private Date lastFollowUp;
	private String paymentType;
	private String city;
	private String country;
	private int status;
	private String professionalTitle;

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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmailBounced() {
		return emailBounced;
	}

	public void setEmailBounced(String emailBounced) {
		this.emailBounced = emailBounced;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public Date getDateProcessed() {
		return dateProcessed;
	}

	public void setDateProcessed(Date dateProcessed) {
		this.dateProcessed = dateProcessed;
	}

	public String getSponsershipCode() {
		return sponsershipCode;
	}

	public void setSponsershipCode(String sponsershipCode) {
		this.sponsershipCode = sponsershipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public Date getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(Date renewalDate) {
		this.renewalDate = renewalDate;
	}

	public Date getLastFollowUp() {
		return lastFollowUp;
	}

	public void setLastFollowUp(Date lastFollowUp) {
		this.lastFollowUp = lastFollowUp;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

}
