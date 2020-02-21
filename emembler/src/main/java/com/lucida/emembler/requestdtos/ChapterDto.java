package com.lucida.emembler.requestdtos;

public class ChapterDto {

	private int id;
	private String chapterName;
	private String description;
	private String type;
	private String primaryGroupCode;
	private int totalActiveMembership;
	private int paidMembership;
	private int membershipRevenue;
	private int localSponseredMembership;
	private String associatedWith;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getMembershipRevenue() {
		return membershipRevenue;
	}

	public void setMembershipRevenue(int membershipRevenue) {
		this.membershipRevenue = membershipRevenue;
	}

	public int getLocalSponseredMembership() {
		return localSponseredMembership;
	}

	public void setLocalSponseredMembership(int localSponseredMembership) {
		this.localSponseredMembership = localSponseredMembership;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssociatedWith() {
		return associatedWith;
	}

	public void setAssociatedWith(String associatedWith) {
		this.associatedWith = associatedWith;
	}

	@Override
	public String toString() {
		return "ChapterDto [id=" + id + ", chapterName=" + chapterName + ", description=" + description + ", type="
				+ type + ", primaryGroupCode=" + primaryGroupCode + ", totalActiveMembership=" + totalActiveMembership
				+ ", paidMembership=" + paidMembership + ", membershipRevenue=" + membershipRevenue
				+ ", localSponseredMembership=" + localSponseredMembership + ", associatedWith=" + associatedWith + "]";
	}
}
