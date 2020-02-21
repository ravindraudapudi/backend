/**
 * 
 */
package com.lucida.emembler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ravindra 
 * 		Address table (address) created using id, firstLine,
 *         secondLine, thirdLine, city, stateOrProvince, zip, country, landMark
 *         and validated
 */

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_line")
	private String firstLine;

	@Column(name = "second_line")
	private String secondLine;

	@Column(name = "third_line")
	private String thirdLine;

	@Column(name = "city")
	private String city;

	@Column(name = "location")
	private String location;

	@Column(name = "state_or_province")
	private String stateOrProvince;

	@Column(name = "home_postal_code")
	private String homePostalCode;

	@Column(name = "country")
	private String country;

	@Column(name = "landmark")
	private String landMark;

	@Column(name = "validated")
	private String validated;
	
	@Column(name = "home_phone_area_code")
	private String homePhoneAreaCode;
	
	@Column(name = "employer_phone_area_code")
	private String employerPhoneAreaCode;
	
	@Column(name = "employer_Phone")
	private String employerPhone;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getThirdLine() {
		return thirdLine;
	}

	public void setThirdLine(String thirdLine) {
		this.thirdLine = thirdLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getHomePostalCode() {
		return homePostalCode;
	}

	public void setHomePostalCode(String homePostalCode) {
		this.homePostalCode = homePostalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String isValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getValidated() {
		return validated;
	}

	public String getHomePhoneAreaCode() {
		return homePhoneAreaCode;
	}

	public void setHomePhoneAreaCode(String homePhoneAreaCode) {
		this.homePhoneAreaCode = homePhoneAreaCode;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [id=");
		builder.append(id);
		builder.append(", firstLine=");
		builder.append(firstLine);
		builder.append(", secondLine=");
		builder.append(secondLine);
		builder.append(", thirdLine=");
		builder.append(thirdLine);
		builder.append(", city=");
		builder.append(city);
		builder.append(", location=");
		builder.append(location);
		builder.append(", stateOrProvince=");
		builder.append(stateOrProvince);
		builder.append(", homePostalCode=");
		builder.append(homePostalCode);
		builder.append(", country=");
		builder.append(country);
		builder.append(", landMark=");
		builder.append(landMark);
		builder.append(", validated=");
		builder.append(validated);
		builder.append("]");
		return builder.toString();
	}
}
