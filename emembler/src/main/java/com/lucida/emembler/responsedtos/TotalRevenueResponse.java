package com.lucida.emembler.responsedtos;

import java.util.List;

public class TotalRevenueResponse {

	private String key;

	private List<Revenue> values;
	private String revenueTerm;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Revenue> getValues() {
		return values;
	}

	public void setValues(List<Revenue> values) {
		this.values = values;
	}

	public String getRevenueTerm() {
		return revenueTerm;
	}

	public void setRevenueTerm(String revenueTerm) {
		this.revenueTerm = revenueTerm;
	}
	
	
}
