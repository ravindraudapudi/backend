package com.lucida.emembler.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * SOcial Media details
 * @author Admin
 *
 */
@Entity
@Table(name = "social_media")
public class SocialMedia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="primary_group_code")
	private String primaryGroupCode;
	
	@Column(name="monthly_date")	
	@Temporal(TemporalType.DATE)
	private Date monthlyDate;
	
	@Column(name="distribution_list")
	private int distributionList;
	
	@Column(name="facebook_page")
	private int facebookPage;
	
	@Column(name="linkedIn_page")
	private int linkedInPage;
	
	@Column(name="instagram_page")
	private int instagramPage;
	
	@Column(name="linkedIn_group")
	private int linkedInGroup;
	
	@Column(name="twitter")
	private int twitter;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public Date getMonthlyDate() {
		return monthlyDate;
	}

	public void setMonthlyDate(Date monthlyDate) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SocialMedia [id=");
		builder.append(id);
		builder.append(", primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", monthlyDate=");
		builder.append(monthlyDate);
		builder.append(", distributionList=");
		builder.append(distributionList);
		builder.append(", facebookPage=");
		builder.append(facebookPage);
		builder.append(", linkedInPage=");
		builder.append(linkedInPage);
		builder.append(", instagramPage=");
		builder.append(instagramPage);
		builder.append(", linkedInGroup=");
		builder.append(linkedInGroup);
		builder.append(", twitter=");
		builder.append(twitter);
		builder.append("]");
		return builder.toString();
	}
}
