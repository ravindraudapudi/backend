package com.lucida.emembler.requestdtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lucida Pojo class contains details of members data
 */
public class MemberDto {

	@JsonProperty("API_GUID")
	private String apiGuid;

	@JsonProperty("Registration_Date")
	private String registrationDate;

	@JsonProperty("Last_Updated")
	private String lastUpdated;

	@JsonProperty("Date_Membership_Expires")
	private String expiresOn;

	@JsonProperty("Member_Type_Code")
	private String membetTypeCode;

	@JsonProperty("Primary_Group_Code")
	private String primagGroupCode;

	@JsonProperty("First_Name")
	private String firstName;

	@JsonProperty("Middle_Name")
	private String middleName;

	@JsonProperty("Last_Name")
	private String lastName;

	@JsonProperty("Member_Name_Title")
	private String nameTitle;

	@JsonProperty("Email_Address")
	private String emailAddress;

	@JsonProperty("Email_Address_Alternate")
	private String emailaAddressAlternate;

	@JsonProperty("Email_Bounced")
	private String emailBounced;

	@JsonProperty("Home_City")
	private String homeCity;

	@JsonProperty("Home_Location")
	private String homeLocation;

	@JsonProperty("Home_State_Abbrev")
	private String homeStateAbbrev;

	@JsonProperty("Home_Postal_Code")
	private String homePostalCode;

	@JsonProperty("Home_Country")
	private String homeCountry;

	@JsonProperty("Home_Phone_Area_Code")
	private String homePhoneAreaCode;

	@JsonProperty("Home_Phone")
	private String homePhone;

	@JsonProperty("Mobile_Area_Code")
	private String mobilaAreaCode;

	@JsonProperty("Mobile")
	private String mobile;

	@JsonProperty("Employer_Name")
	private String employerName;

	@JsonProperty("Professional_Title")
	private String professionalTitle;

	@JsonProperty("Profession")
	private String profession;

	@JsonProperty("Employer_City")
	private String employerCity;

	@JsonProperty("Employer_Location")
	private String employerLocation;

	@JsonProperty("Employer_State_Abbrev")
	private String employerStateAbbrev;

	@JsonProperty("Employer_Postal_Code")
	private String employerPostalCode;

	@JsonProperty("Employer_Country")
	private String employerCountry;

	@JsonProperty("Employer_Phone_Area_Code")
	private String employerPhoneAreaCode;

	@JsonProperty("Employer_Phone")
	private String employerPhone;

	@JsonProperty("Date_Last_Renewed")
	private String lastRenewedOn;
	
	@JsonProperty("Gender")
	private String gender;
	
	@JsonProperty("Membership")
	private String membership;
	
	public String getApiGuid() {
		return apiGuid;
	}

	public void setApiGuid(String apiGuid) {
		this.apiGuid = apiGuid;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(String expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public String getMembetTypeCode() {
		return membetTypeCode;
	}

	public void setMembetTypeCode(String membetTypeCode) {
		this.membetTypeCode = membetTypeCode;
	}

	public String getPrimagGroupCode() {
		return primagGroupCode;
	}

	public void setPrimagGroupCode(String primagGroupCode) {
		this.primagGroupCode = primagGroupCode;
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

	public String getNameTitle() {
		return nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailaAddressAlternate() {
		return emailaAddressAlternate;
	}

	public void setEmailaAddressAlternate(String emailaAddressAlternate) {
		this.emailaAddressAlternate = emailaAddressAlternate;
	}

	public String getEmailBounced() {
		return emailBounced;
	}

	public void setEmailBounced(String emailBounced) {
		this.emailBounced = emailBounced;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getHomeLocation() {
		return homeLocation;
	}

	public void setHomeLocation(String homeLocation) {
		this.homeLocation = homeLocation;
	}

	public String getHomeStateAbbrev() {
		return homeStateAbbrev;
	}

	public void setHomeStateAbbrev(String homeStateAbbrev) {
		this.homeStateAbbrev = homeStateAbbrev;
	}

	public String getHomePostalCode() {
		return homePostalCode;
	}

	public void setHomePostalCode(String homePostalCode) {
		this.homePostalCode = homePostalCode;
	}

	public String getHomeCountry() {
		return homeCountry;
	}

	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}

	public String getHomePhoneAreaCode() {
		return homePhoneAreaCode;
	}

	public void setHomePhoneAreaCode(String homePhoneAreaCode) {
		this.homePhoneAreaCode = homePhoneAreaCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilaAreaCode() {
		return mobilaAreaCode;
	}

	public void setMobilaAreaCode(String mobilaAreaCode) {
		this.mobilaAreaCode = mobilaAreaCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEmployerCity() {
		return employerCity;
	}

	public void setEmployerCity(String employerCity) {
		this.employerCity = employerCity;
	}

	public String getEmployerLocation() {
		return employerLocation;
	}

	public void setEmployerLocation(String employerLocation) {
		this.employerLocation = employerLocation;
	}

	public String getEmployerStateAbbrev() {
		return employerStateAbbrev;
	}

	public void setEmployerStateAbbrev(String employerStateAbbrev) {
		this.employerStateAbbrev = employerStateAbbrev;
	}

	public String getEmployerPostalCode() {
		return employerPostalCode;
	}

	public void setEmployerPostalCode(String employerPostalCode) {
		this.employerPostalCode = employerPostalCode;
	}

	public String getEmployerCountry() {
		return employerCountry;
	}

	public void setEmployerCountry(String employerCountry) {
		this.employerCountry = employerCountry;
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

	public String getLastRenewedOn() {
		return lastRenewedOn;
	}

	public void setLastRenewedOn(String lastRenewedOn) {
		this.lastRenewedOn = lastRenewedOn;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberDto [apiGuid=");
		builder.append(apiGuid);
		builder.append(", registrationDate=");
		builder.append(registrationDate);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append(", expiresOn=");
		builder.append(expiresOn);
		builder.append(", membetTypeCode=");
		builder.append(membetTypeCode);
		builder.append(", primagGroupCode=");
		builder.append(primagGroupCode);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", nameTitle=");
		builder.append(nameTitle);
		builder.append(", emailAddress=");
		builder.append(emailAddress);
		builder.append(", emailaAddressAlternate=");
		builder.append(emailaAddressAlternate);
		builder.append(", emailBounced=");
		builder.append(emailBounced);
		builder.append(", homeCity=");
		builder.append(homeCity);
		builder.append(", homeLocation=");
		builder.append(homeLocation);
		builder.append(", homeStateAbbrev=");
		builder.append(homeStateAbbrev);
		builder.append(", homePostalCode=");
		builder.append(homePostalCode);
		builder.append(", homeCountry=");
		builder.append(homeCountry);
		builder.append(", homePhoneAreaCode=");
		builder.append(homePhoneAreaCode);
		builder.append(", homePhone=");
		builder.append(homePhone);
		builder.append(", mobilaAreaCode=");
		builder.append(mobilaAreaCode);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", employerName=");
		builder.append(employerName);
		builder.append(", professionalTitle=");
		builder.append(professionalTitle);
		builder.append(", profession=");
		builder.append(profession);
		builder.append(", employerCity=");
		builder.append(employerCity);
		builder.append(", employerLocation=");
		builder.append(employerLocation);
		builder.append(", employerStateAbbrev=");
		builder.append(employerStateAbbrev);
		builder.append(", employerPostalCode=");
		builder.append(employerPostalCode);
		builder.append(", employerCountry=");
		builder.append(employerCountry);
		builder.append(", employerPhoneAreaCode=");
		builder.append(employerPhoneAreaCode);
		builder.append(", employerPhone=");
		builder.append(employerPhone);
		builder.append(", lastRenewedOn=");
		builder.append(lastRenewedOn);
		builder.append(", membership=");
		builder.append(membership);
		builder.append("]");
		return builder.toString();
	}

}