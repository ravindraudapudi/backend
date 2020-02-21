/**
 * 
 */
package com.lucida.emembler.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ravindra
 * MemberPersonalInfo table(member_personal_info) Created using
 * id, maidenName, spouseName, spouseEmail, dateOfBirth and martialStatus 
 */
@Entity
@Table(name="member_personal_info")
public class MemberPersonalInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="maiden_name")
	private String maidenName;
	
	@Column(name="spouse_name")
	private String spouseName;
	
	@Column(name="spouse_email")
	private String spouseEmail;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="martial_status")
	private boolean martialStatus;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseEmail() {
		return spouseEmail;
	}

	public void setSpouseEmail(String spouseEmail) {
		this.spouseEmail = spouseEmail;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(boolean martialStatus) {
		this.martialStatus = martialStatus;
	}
}
