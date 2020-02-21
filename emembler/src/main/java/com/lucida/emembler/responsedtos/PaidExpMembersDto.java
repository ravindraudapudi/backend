package com.lucida.emembler.responsedtos;

import java.util.List;

import com.lucida.emembler.utility.MembershipFYData;

public class PaidExpMembersDto {

	private String key;
	private List<MembershipFYData> values;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<MembershipFYData> getValues() {
		return values;
	}

	public void setValues(List<MembershipFYData> values) {
		this.values = values;
	}

}
