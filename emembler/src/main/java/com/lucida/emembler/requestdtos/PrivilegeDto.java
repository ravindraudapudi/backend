package com.lucida.emembler.requestdtos;

public class PrivilegeDto {
	
	private String role;
	private boolean isNational;
	private String[] privileges;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String[] getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String[] privileges) {
		this.privileges = privileges;
	}

	public boolean isNational() {
		return isNational;
	}

	public void setNational(boolean isNational) {
		this.isNational = isNational;
	}
	
	
	
}
