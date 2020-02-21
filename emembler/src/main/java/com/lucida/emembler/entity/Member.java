package com.lucida.emembler.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * To store Member details
 * 
 * @author Admin
 *
 */
@Entity
@Table(name = "members")
public class Member {

	@Id
	@Column(name = "api_guid")
	private String apiGuid;

	@Column(name = "primary_group_code")
	private String primaryGroupCode;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "name_title")
	private String nameTitle;

	@Column(name = "name_prefix")
	private String namePrefix;

	@Column(name = "email")
	private String email;

	@Column(name = "alternate_emails")
	private String alternateEmails;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "mobile_area_code")
	private String mobileAreaCode;

	@Column(name = "emailBounced")
	private String emailBounced;

	@Column(name = "gender")
	private String gender;

	@JsonFormat(pattern = "MM/dd/yyyy")
	@Column(name = "expiry_date")
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@JsonFormat(pattern = "MM/dd/yyyy")
	@Column(name = "date_processed")
	private Date dateProcessed;

	@JsonFormat(pattern = "MM/dd/yyyy")
	@Column(name = "registration_Date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	@JsonFormat(pattern = "MM/dd/yyyy")
	@Column(name = "last_renewed")
	@Temporal(TemporalType.DATE)
	private Date lastRenewed;

	@Column(name = "last_Updated")
	@Temporal(TemporalType.DATE)
	private Date lastUpdated;

	@Column(name = "tenancy_id")
	private int tenancyId;

	@Column(name = "member_type")
	private String memberType;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "amount")
	private double amount;

	@Column(name = "sponsership_code")
	private String sponsershipCode;

	@Column(name = "membership")
	private String membership;

	@Column(name = "sponsor", insertable = false, nullable = true, updatable = false)
	private String sponsor;

	@Column(name = "discount_code", length = 3000)
	private String discountCode;

	@Column(name = "promotional_Code")
	private String promotionalCode;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "home_address")
	private Address homeAddress;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "employer")
	private Employer employer;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "personal_info")
	private MemberPersonalInfo personalInfo;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "official_details")
	private MemberOfficialDetails officialDetails;

	public String getApiGuid() {
		return apiGuid;
	}

	public void setApiGuid(String apiGuid) {
		this.apiGuid = apiGuid;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNameTitle() {
		return nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlternateEmails() {
		return alternateEmails;
	}

	public void setAlternateEmails(String alternateEmails) {
		this.alternateEmails = alternateEmails;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobileAreaCode() {
		return mobileAreaCode;
	}

	public void setMobileAreaCode(String mobileAreaCode) {
		this.mobileAreaCode = mobileAreaCode;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getLastRenewed() {
		return lastRenewed;
	}

	public void setLastRenewed(Date lastRenewed) {
		this.lastRenewed = lastRenewed;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getTenancyId() {
		return tenancyId;
	}

	public void setTenancyId(int tenancyId) {
		this.tenancyId = tenancyId;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSponsershipCode() {
		return sponsershipCode;
	}

	public void setSponsershipCode(String sponsershipCode) {
		this.sponsershipCode = sponsershipCode;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getPromotionalCode() {
		return promotionalCode;
	}

	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public MemberPersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(MemberPersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public MemberOfficialDetails getOfficialDetails() {
		return officialDetails;
	}

	public void setOfficialDetails(MemberOfficialDetails officialDetails) {
		this.officialDetails = officialDetails;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [apiGuid=");
		builder.append(apiGuid);
		builder.append(", primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", nameTitle=");
		builder.append(nameTitle);
		builder.append(", namePrefix=");
		builder.append(namePrefix);
		builder.append(", email=");
		builder.append(email);
		builder.append(", alternateEmails=");
		builder.append(alternateEmails);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", mobileAreaCode=");
		builder.append(mobileAreaCode);
		builder.append(", emailBounced=");
		builder.append(emailBounced);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
		builder.append(", dateProcessed=");
		builder.append(dateProcessed);
		builder.append(", registrationDate=");
		builder.append(registrationDate);
		builder.append(", lastRenewed=");
		builder.append(lastRenewed);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append(", tenancyId=");
		builder.append(tenancyId);
		builder.append(", memberType=");
		builder.append(memberType);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", sponsershipCode=");
		builder.append(sponsershipCode);
		builder.append(", membership=");
		builder.append(membership);
		builder.append(", sponsor=");
		builder.append(sponsor);
		builder.append(", discountCode=");
		builder.append(discountCode);
		builder.append(", promotionalCode=");
		builder.append(promotionalCode);
		builder.append(", homeAddress=");
		builder.append(homeAddress);
		builder.append(", employer=");
		builder.append(employer);
		builder.append(", personalInfo=");
		builder.append(personalInfo);
		builder.append(", officialDetails=");
		builder.append(officialDetails);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		Member other = (Member) obj;
		if (apiGuid.equals(other.apiGuid))
			return true;
		return false;
	}
}
