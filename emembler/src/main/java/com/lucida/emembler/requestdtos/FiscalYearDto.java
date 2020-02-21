package com.lucida.emembler.requestdtos;

public class FiscalYearDto {

	private String fiscalYear;

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getTotalActiveMembership() {
		return totalActiveMembership;
	}

	public void setTotalActiveMembership(String totalActiveMembership) {
		this.totalActiveMembership = totalActiveMembership;
	}

	public String getPaidMembership() {
		return paidMembership;
	}

	public void setPaidMembership(String paidMembership) {
		this.paidMembership = paidMembership;
	}

	public String getLocalSponseredMembership() {
		return localSponseredMembership;
	}

	public void setLocalSponseredMembership(String localSponseredMembership) {
		this.localSponseredMembership = localSponseredMembership;
	}

	public String getMembershipRevenue() {
		return membershipRevenue;
	}

	public void setMembershipRevenue(String membershipRevenue) {
		this.membershipRevenue = membershipRevenue;
	}

	private String totalActiveMembership;
	private String paidMembership;
	private String localSponseredMembership;
	private String membershipRevenue;

}
