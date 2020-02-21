package com.lucida.emembler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * To store the fiscal year information about chapters
 * 
 * @author Ravindra
 *
 */
@Entity
@Table(name = "fy_data")
public class FyData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fiscal_year")
	private int fiscalYear;

	@Column(name = "chapter_name")
	private String chapterName;

	@Column(name = "type")
	private String type;

	@Column(name = "primary_group_code")
	private String primaryGroupCode;

	@Column(name = "revenue_term")
	private String revenueTerm;

	/**
	 * last fiscal year data
	 */

	@Column(name = "total_active_memebership")
	private int totalActiveMembership;

	@Column(name = "paid_membership")
	private int paidMembership;

	@Column(name = "local_sponsered_memebrship")
	private int localSponseredMembership;

	@Column(name = "national_sponsored_membership")
	private int nationalSponseredMembership;

	@Column(name = "total_revenue")
	private double totalRevenue;

	/**
	 * Goal data
	 */

	@Column(name = "total_active_memebership_goal")
	private int totalActiveMembershipGoal;

	@Column(name = "paid_membership_goal")
	private int paidMembershipGoal;

	@Column(name = "local_sponsered_memebrship_goal")
	private int localSponseredMembershipGoal;

	@Column(name = "national_sponsored_membership_goal")
	private int nationalSponsoredMembershipGoal;

	@Column(name = "total_revenue_goal")
	private double totalRevenueGoal;

	@Column(name = "memebership_revenue_goal")
	private double membershipRevenueGoal;

	@Column(name = "registration_events_goal")
	private double registrationEventsGoal;

	@Column(name = "program_revenue_goal")
	private double programRevenueGoal;

	@Column(name = "expiration_rate_goal", columnDefinition = "Decimal(10,2) default '0.00'")
	private double expirationRateGoal;

	@Column(name = "other_revenue_goal", columnDefinition = "Decimal(10,2) default '0.00'")
	private double otherRevenueGoal;

	/*
	 * Profit and loss data
	 */

	@Column(name = "program_revenue")
	private double programRevenue;

	@Column(name = "membership_income")
	private double membershipIncome;

	@Column(name = "contribution_revenue")
	private double contributionRevenue;

	@Column(name = "registration_events")
	private double registrationEvents;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public int getTotalActiveMembership() {
		return totalActiveMembership;
	}

	public void setTotalActiveMembership(int totalActiveMembership) {
		this.totalActiveMembership = totalActiveMembership;
	}

	public int getPaidMembership() {
		return paidMembership;
	}

	public void setPaidMembership(int paidMembership) {
		this.paidMembership = paidMembership;
	}

	public int getLocalSponseredMembership() {
		return localSponseredMembership;
	}

	public void setLocalSponseredMembership(int localSponseredMembership) {
		this.localSponseredMembership = localSponseredMembership;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public int getTotalActiveMembershipGoal() {
		return totalActiveMembershipGoal;
	}

	public void setTotalActiveMembershipGoal(int totalActiveMembershipGoal) {
		this.totalActiveMembershipGoal = totalActiveMembershipGoal;
	}

	public int getPaidMembershipGoal() {
		return paidMembershipGoal;
	}

	public void setPaidMembershipGoal(int paidMembershipGoal) {
		this.paidMembershipGoal = paidMembershipGoal;
	}

	public int getLocalSponseredMembershipGoal() {
		return localSponseredMembershipGoal;
	}

	public void setLocalSponseredMembershipGoal(int localSponseredMembershipGoal) {
		this.localSponseredMembershipGoal = localSponseredMembershipGoal;
	}

	public double getTotalRevenueGoal() {
		return totalRevenueGoal;
	}

	public void setTotalRevenueGoal(double totalRevenueGoal) {
		this.totalRevenueGoal = totalRevenueGoal;
	}

	public double getMembershipRevenueGoal() {
		return membershipRevenueGoal;
	}

	public void setMembershipRevenueGoal(double membershipRevenueGoal) {
		this.membershipRevenueGoal = membershipRevenueGoal;
	}

	public double getRegistrationEventsGoal() {
		return registrationEventsGoal;
	}

	public void setRegistrationEventsGoal(double registrationEventsGoal) {
		this.registrationEventsGoal = registrationEventsGoal;
	}

	public double getProgramRevenueGoal() {
		return programRevenueGoal;
	}

	public void setProgramRevenueGoal(double programRevenueGoal) {
		this.programRevenueGoal = programRevenueGoal;
	}

	public double getProgramRevenue() {
		return programRevenue;
	}

	public void setProgramRevenue(double programRevenue) {
		this.programRevenue = programRevenue;
	}

	public double getMembershipIncome() {
		return membershipIncome;
	}

	public void setMembershipIncome(double membershipIncome) {
		this.membershipIncome = membershipIncome;
	}

	public double getContributionRevenue() {
		return contributionRevenue;
	}

	public void setContributionRevenue(double contributionRevenue) {
		this.contributionRevenue = contributionRevenue;
	}

	public double getRegistrationEvents() {
		return registrationEvents;
	}

	public void setRegistrationEvents(double registrationEvents) {
		this.registrationEvents = registrationEvents;
	}

	public int getNationalSponseredMembership() {
		return nationalSponseredMembership;
	}

	public void setNationalSponseredMembership(int nationalSponseredMembership) {
		this.nationalSponseredMembership = nationalSponseredMembership;
	}

	public int getNationalSponsoredMembershipGoal() {
		return nationalSponsoredMembershipGoal;
	}

	public void setNationalSponsoredMembershipGoal(int nationalSponsoredMembershipGoal) {
		this.nationalSponsoredMembershipGoal = nationalSponsoredMembershipGoal;
	}

	public String getRevenueTerm() {
		return revenueTerm;
	}

	public void setRevenueTerm(String revenueTerm) {
		this.revenueTerm = revenueTerm;
	}

	public double getOtherRevenueGoal() {
		return otherRevenueGoal;
	}

	public void setOtherRevenueGoal(double otherRevenueGoal) {
		this.otherRevenueGoal = otherRevenueGoal;
	}

	public double getExpirationRateGoal() {
		return expirationRateGoal;
	}

	public void setExpirationRateGoal(double expirationRateGoal) {
		this.expirationRateGoal = expirationRateGoal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FyData [id=");
		builder.append(id);
		builder.append(", fiscalYear=");
		builder.append(fiscalYear);
		builder.append(", chapterName=");
		builder.append(chapterName);
		builder.append(", type=");
		builder.append(type);
		builder.append(", primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", totalActiveMembership=");
		builder.append(totalActiveMembership);
		builder.append(", paidMembership=");
		builder.append(paidMembership);
		builder.append(", localSponseredMembership=");
		builder.append(localSponseredMembership);
		builder.append(", totalRevenue=");
		builder.append(totalRevenue);
		builder.append(", totalActiveMembershipGoal=");
		builder.append(totalActiveMembershipGoal);
		builder.append(", paidMembershipGoal=");
		builder.append(paidMembershipGoal);
		builder.append(", localSponseredMembershipGoal=");
		builder.append(localSponseredMembershipGoal);
		builder.append(", totalRevenueGoal=");
		builder.append(totalRevenueGoal);
		builder.append(", membershipRevenueGoal=");
		builder.append(membershipRevenueGoal);
		builder.append(", registrationEventsGoal=");
		builder.append(registrationEventsGoal);
		builder.append(", programRevenueGoal=");
		builder.append(programRevenueGoal);
		builder.append(", programRevenue=");
		builder.append(programRevenue);
		builder.append(", membershipIncome=");
		builder.append(membershipIncome);
		builder.append(", contributionRevenue=");
		builder.append(contributionRevenue);
		builder.append(", registrationEvents=");
		builder.append(registrationEvents);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FyData other = (FyData) obj;
		if(fiscalYear != other.fiscalYear) 
			return false;
		else 
			return true;
	}

}