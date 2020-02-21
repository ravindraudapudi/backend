package com.lucida.emembler.requestdtos;

public class ProfitAndLosssDto {

	private String chapterName;
	private String registrationEvent;
	private String revenueTerm;
	private String programRevenue;
	private String membershipIncome;
	private String contributionRevenue;
	private String lifeTimeMembership;
	private String kindDonations;
	private int fiscalYear;
	private String totalRevenue;
	
	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getRegistrationEvent() {
		return registrationEvent;
	}

	public void setRegistrationEvent(String registrationEvent) {
		this.registrationEvent = registrationEvent;
	}

	public String getProgramRevenue() {
		return programRevenue;
	}

	public void setProgramRevenue(String programRevenue) {
		this.programRevenue = programRevenue;
	}

	public String getMembershipIncome() {
		return membershipIncome;
	}

	public void setMembershipIncome(String membershipIncome) {
		this.membershipIncome = membershipIncome;
	}

	public String getContributionRevenue() {
		return contributionRevenue;
	}

	public void setContributionRevenue(String contributionRevenue) {
		this.contributionRevenue = contributionRevenue;
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public String getRevenueTerm() {
		return revenueTerm;
	}

	public void setRevenueTerm(String revenueTerm) {
		this.revenueTerm = revenueTerm;
	}
	
	

	public String getLifeTimeMembership() {
		return lifeTimeMembership;
	}

	public void setLifeTimeMembership(String lifeTimeMembership) {
		this.lifeTimeMembership = lifeTimeMembership;
	}

	public String getKindDonations() {
		return kindDonations;
	}

	public void setKindDonations(String kindDonations) {
		this.kindDonations = kindDonations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProfitAndLosssDto [chapterName=");
		builder.append(chapterName);
		builder.append(", registrationEvent=");
		builder.append(registrationEvent);
		builder.append(", programRevenue=");
		builder.append(programRevenue);
		builder.append(", membershipIncome=");
		builder.append(membershipIncome);
		builder.append(", contributionRevenue=");
		builder.append(contributionRevenue);
		builder.append(", fiscalYear=");
		builder.append(fiscalYear);
		builder.append("]");
		return builder.toString();
	}
}
