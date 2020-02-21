package com.lucida.emembler.responsedtos;

public class SponsorshipResponse extends Response {

	public SponsorshipResponse() {
		super();
	}

	private SponsershipReport sponsershipReport;

	public SponsershipReport getSponsershipReport() {
		return sponsershipReport;
	}

	public void setSponsershipReport(SponsershipReport sponsershipReport) {
		this.sponsershipReport = sponsershipReport;
	}

}
