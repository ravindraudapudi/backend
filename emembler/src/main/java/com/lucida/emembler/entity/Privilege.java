package com.lucida.emembler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "privilege")
	private String privilege;

	@Column(name = "isNational")
	private String isNational;

	public String getIsNational() {
		return isNational;
	}

	public void setIsNational(String isNational) {
		this.isNational = isNational;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Privilege [id=");
		builder.append(id);
		builder.append(", privilege=");
		builder.append(privilege);
		builder.append(", isNational=");
		builder.append(isNational);
		builder.append("]");
		return builder.toString();
	}
}
