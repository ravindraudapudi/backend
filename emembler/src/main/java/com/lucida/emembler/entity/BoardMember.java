package com.lucida.emembler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  Board member details
 * @author Ravindra
 *
 */
@Entity
@Table(name = "board_member")
public class BoardMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "api_guid")
	private String apiGuid;
	
	@Column(name = "primary_Group_Code")
	private String primaryGroupCode;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "memeber_role")
	private String memberRole;

	@Column(name = "phone")
	private String phone;

	@Column(name = "alternat_email")
	private String alternateEmail;

	@Column(name = "organisation")
	private String organisation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApiGuid() {
		return apiGuid;
	}

	public void setApiGuid(String apiGuid) {
		this.apiGuid = apiGuid;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
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

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardMember [id=");
		builder.append(id);
		builder.append(", apiGuid=");
		builder.append(apiGuid);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", memberRole=");
		builder.append(memberRole);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", alternatEmail=");
		builder.append(alternateEmail);
		builder.append(", organisation=");
		builder.append(organisation);
		builder.append("]");
		return builder.toString();
	}

}
