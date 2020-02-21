package com.lucida.emembler.responsedtos;

import java.util.List;

public class PaidSponseredDto {

	private String key;
	private int fiscalYear;
	private List<FiscalMembership> values;

	public String getKey() {
		return key;
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<FiscalMembership> getValues() {
		return values;
	}

	public void setValues(List<FiscalMembership> values) {
		this.values = values;
	}

}
