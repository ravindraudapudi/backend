package com.lucida.emembler.responsedtos;

import java.util.List;

public class NationalDashboard {

	private List<DashboardDetails> totalActiveMembership;
	private List<DashboardDetails> paidMemebrship;
	
	public List<DashboardDetails> getTotalActiveMembership() {
		return totalActiveMembership;
	}

	public void setTotalActiveMembership(List<DashboardDetails> totalActiveMembership) {
		this.totalActiveMembership = totalActiveMembership;
	}

	public List<DashboardDetails> getPaidMemebrship() {
		return paidMemebrship;
	}

	public void setPaidMemebrship(List<DashboardDetails> paidMemebrship) {
		this.paidMemebrship = paidMemebrship;
	}
}
