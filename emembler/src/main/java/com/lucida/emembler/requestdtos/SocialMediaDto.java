package com.lucida.emembler.requestdtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SocialMediaDto {

	@JsonProperty("Chapter")
	private String primaryGroupCode;

	@JsonProperty("Monthly_Date")
	private String monthlyDate;

	@JsonProperty("Distribution_List")
	private int distributionList;

	@JsonProperty("Facebook_Page")
	private int facebookPage;

	@JsonProperty("LinkedIn_Page")
	private int linkedInPage;

	@JsonProperty("Instagram_Page")
	private int instagramPage;
	
	@JsonProperty("LinkedIn_group")
	private int linkedInGroup;
	
	@JsonProperty("Twitter")
	private int twitter;

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public String getMonthlyDate() {
		return monthlyDate;
	}

	public void setMonthlyDate(String monthlyDate) {
		this.monthlyDate = monthlyDate;
	}

	public int getDistributionList() {
		return distributionList;
	}

	public void setDistributionList(int distributionList) {
		this.distributionList = distributionList;
	}

	public int getFacebookPage() {
		return facebookPage;
	}

	public void setFacebookPage(int facebookPage) {
		this.facebookPage = facebookPage;
	}

	public int getLinkedInPage() {
		return linkedInPage;
	}

	public void setLinkedInPage(int linkedInPage) {
		this.linkedInPage = linkedInPage;
	}

	public int getInstagramPage() {
		return instagramPage;
	}

	public void setInstagramPage(int instagramPage) {
		this.instagramPage = instagramPage;
	}

	public int getLinkedInGroup() {
		return linkedInGroup;
	}

	public void setLinkedInGroup(int linkedInGroup) {
		this.linkedInGroup = linkedInGroup;
	}

	public int getTwitter() {
		return twitter;
	}

	public void setTwitter(int twitter) {
		this.twitter = twitter;
	}
	
	
}
