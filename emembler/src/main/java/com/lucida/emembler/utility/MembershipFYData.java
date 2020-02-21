package com.lucida.emembler.utility;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class MembershipFYData {
	
	private String name;
	private Long value;

	public MembershipFYData(String monthYr, Long cnt) {
		this.name = monthYr;
		this.value = cnt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return this.value;
	}

	public void setValue(Long val) {
		this.value = val;
	}
}
