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
 * MemberOfficialDetails table (member_official_details) Created using
 * id, professionalTitle and profession
 */

@Entity
@Table(name="member_official_details")
public class MemberOfficialDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="professional_title")
	private String professionalTitle;
	
	@Column(name="profession")
	private String profession;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberOfficialDetails [id=");
		builder.append(id);
		builder.append(", professionalTitle=");
		builder.append(professionalTitle);
		builder.append(", profession=");
		builder.append(profession);
		builder.append("]");
		return builder.toString();
	}

}
