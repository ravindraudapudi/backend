package com.lucida.emembler.requestdtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Reporting dates 
 * @author Ravindra
 *
 */
public class FiscalYearsList {

	private int currentFiscalYear;
	private List<ReportingDatesForFiscal> reportingDatesForFiscal = new ArrayList<>() ;

	public int getCurrentFiscalYear() {
		return currentFiscalYear;
	}

	public void setCurrentFiscalYear(int currentFiscalYear) {
		this.currentFiscalYear = currentFiscalYear;
	}

	public List<ReportingDatesForFiscal> getReportingDatesForFiscal() {
		return reportingDatesForFiscal;
	}

	public void setReportingDatesForFiscal(List<ReportingDatesForFiscal> reportingDatesForFiscal) {
		this.reportingDatesForFiscal = reportingDatesForFiscal;
	}

}
