package com.lucida.emembler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "national_contact")
public class NationalContact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "full_name")
	private String fullName;  // contact name

	@Column(name = "role")
	private String role;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

//	@Column(name = "company")
//	private String company;
//
//	@Column(name = "company_role")
//	private String companyRole;

	@Column(name = "other")
	private String other;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

//	public String getCompany() {
//		return company;
//	}
//
//	public void setCompany(String company) {
//		this.company = company;
//	}
//
//	public String getCompanyRole() {
//		return companyRole;
//	}
//
//	public void setCompanyRole(String companyRole) {
//		this.companyRole = companyRole;
//	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
