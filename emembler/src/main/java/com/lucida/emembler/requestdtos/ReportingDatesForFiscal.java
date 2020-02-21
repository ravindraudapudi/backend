package com.lucida.emembler.requestdtos;

import java.util.List;

import com.lucida.emembler.entity.CsvLog;

/**
 * Reporting dates
 * 
 * @author Ravindra
 *
 */
public class ReportingDatesForFiscal {

	private int fiscaYear;
	private List<CsvLog> csvLog;

	public int getFiscaYear() {
		return fiscaYear;
	}

	public void setFiscaYear(int fiscaYear) {
		this.fiscaYear = fiscaYear;
	}

	public List<CsvLog> getCsvLog() {
		return csvLog;
	}

	public void setCsvLog(List<CsvLog> csvLog) {
		this.csvLog = csvLog;
	}
}
