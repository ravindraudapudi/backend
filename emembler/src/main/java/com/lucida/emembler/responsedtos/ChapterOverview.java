package com.lucida.emembler.responsedtos;

/**
 * Chapter Overview details
 * 
 * @author Ravindra
 *
 */
public class ChapterOverview {

	private double totalRevenue;
	private int paidSponsored;
	private double expirationRate;
	private double MembershipGoal;
	private double RevenueGoal;
	private double MembershipGoalValue;
	private double RevenueGoalValue;

	public int getPaidSponsored() {
		return paidSponsored;
	}

	public void setPaidSponsored(int paidSponsored) {
		this.paidSponsored = paidSponsored;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public double getExpirationRate() {
		return expirationRate;
	}

	public void setExpirationRate(double expirationRate) {
		this.expirationRate = expirationRate;
	}

	public double getMembershipGoal() {
		return MembershipGoal;
	}

	public void setMembershipGoal(double membershipGoal) {
		MembershipGoal = membershipGoal;
	}

	public double getRevenueGoal() {
		return RevenueGoal;
	}

	public void setRevenueGoal(double d) {
		RevenueGoal = d;
	}

	public double getMembershipGoalValue() {
		return MembershipGoalValue;
	}

	public void setMembershipGoalValue(double membershipGoalValue) {
		MembershipGoalValue = membershipGoalValue;
	}

	public double getRevenueGoalValue() {
		return RevenueGoalValue;
	}

	public void setRevenueGoalValue(double revenueGoalValue) {
		RevenueGoalValue = revenueGoalValue;
	}

}
