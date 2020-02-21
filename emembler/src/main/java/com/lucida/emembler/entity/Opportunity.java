package com.lucida.emembler.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "opportunity")
public class Opportunity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "status")
	private String status;

	@Column(name = "sponsor_name", length = 500)
	private String sponsorName;

	@Column(name = "primary_group_code")
	private String primaryGroupCode;

	@Column(name = "existing_sponsor")
	private String existingSponsor;

	@Column(name = "relation_with_nationals")
	private String relationWithNationals;

	@Column(name = "national_convention_sponsor")
	private String nationalConventionSponsor;

	@Column(name = "owner")
	private String owner;

	@Column(name = "national_benefits")
	private String nationalBenefits;

	@Column(name = "created_on")
	@Temporal(TemporalType.DATE)
	private Date createdOn;

	@Column(name = "winning_percentage")
	private int winningPercentage;

	@Column(name = "potentional_close_date")
	@Temporal(TemporalType.DATE)
	private Date potentionalCoseDate;

	@Column(name = "proposed_amount")
	private int proposedAmount;

	@Column(name = "description")
	private String description;

	@Column(name = "proposed_contract_terms",  columnDefinition = "LONGTEXT")
	private String proposedContractTerms;

	@Column(name = "membership_proposed")
	private String membershipProposed;

	@Column(name = "tags")
	private String tags;

	@Column(name = "invoice")
	private String invoice;

	@ManyToOne
	@JoinColumn(name= "stage_id")
	private PickList stages;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "national_contacts")
	private List<NationalContact> nationalContacts;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "chapter_contacts")
	private List<ChapterContact> chapterContacts;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sponsor_contacts")
	private List<SponsorContact> sponsorContacts;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "follow_ups")
	private List<FollowUp> followUps;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getPrimaryGroupCode() {
		return primaryGroupCode;
	}

	public void setPrimaryGroupCode(String primaryGroupCode) {
		this.primaryGroupCode = primaryGroupCode;
	}

	public String getExistingSponsor() {
		return existingSponsor;
	}

	public void setExistingSponsor(String existingSponsor) {
		this.existingSponsor = existingSponsor;
	}

	public String getRelationWithNationals() {
		return relationWithNationals;
	}

	public void setRelationWithNationals(String relationWithNationals) {
		this.relationWithNationals = relationWithNationals;
	}

	public String getNationalConventionSponsor() {
		return nationalConventionSponsor;
	}

	public void setNationalConventionSponsor(String nationalConventionSponsor) {
		this.nationalConventionSponsor = nationalConventionSponsor;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getNationalBenefits() {
		return nationalBenefits;
	}

	public void setNationalBenefits(String nationalBenefits) {
		this.nationalBenefits = nationalBenefits;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getWinningPercentage() {
		return winningPercentage;
	}

	public void setWinningPercentage(int winningPercentage) {
		this.winningPercentage = winningPercentage;
	}

	public Date getPotentionalCoseDate() {
		return potentionalCoseDate;
	}

	public void setPotentionalCoseDate(Date potentionalCoseDate) {
		this.potentionalCoseDate = potentionalCoseDate;
	}

	public int getProposedAmount() {
		return proposedAmount;
	}

	public void setProposedAmount(int proposedAmount) {
		this.proposedAmount = proposedAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProposedContractTerms() {
		return proposedContractTerms;
	}

	public void setProposedContractTerms(String proposedContractTerms) {
		this.proposedContractTerms = proposedContractTerms;
	}

	public String getMembershipProposed() {
		return membershipProposed;
	}

	public void setMembershipProposed(String membershipProposed) {
		this.membershipProposed = membershipProposed;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public PickList getStages() {
		return stages;
	}

	public void setStages(PickList stages) {
		this.stages = stages;
	}

	public List<NationalContact> getNationalContacts() {
		return nationalContacts;
	}

	public void setNationalContacts(List<NationalContact> nationalContacts) {
		this.nationalContacts = nationalContacts;
	}

	public List<ChapterContact> getChapterContacts() {
		return chapterContacts;
	}

	public void setChapterContacts(List<ChapterContact> chapterContacts) {
		this.chapterContacts = chapterContacts;
	}

	public List<SponsorContact> getSponsorContacts() {
		return sponsorContacts;
	}

	public void setSponsorContacts(List<SponsorContact> sponsorContacts) {
		this.sponsorContacts = sponsorContacts;
	}

	public List<FollowUp> getFollowUps() {
		return followUps;
	}

	public void setFollowUps(List<FollowUp> followUps) {
		this.followUps = followUps;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Opportunity [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sponsorName=");
		builder.append(sponsorName);
		builder.append(", primaryGroupCode=");
		builder.append(primaryGroupCode);
		builder.append(", existingSponsor=");
		builder.append(existingSponsor);
		builder.append(", relationWithNationals=");
		builder.append(relationWithNationals);
		builder.append(", nationalConventionSponsor=");
		builder.append(nationalConventionSponsor);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", nationalBenefits=");
		builder.append(nationalBenefits);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", winningPercentage=");
		builder.append(winningPercentage);
		builder.append(", potentionalCoseDate=");
		builder.append(potentionalCoseDate);
		builder.append(", proposedAmount=");
		builder.append(proposedAmount);
		builder.append(", description=");
		builder.append(description);
		builder.append(", proposedContractTerms=");
		builder.append(proposedContractTerms);
		builder.append(", membershipProposed=");
		builder.append(membershipProposed);
		builder.append(", tags=");
		builder.append(tags);
		builder.append(", invoice=");
		builder.append(invoice);
		builder.append(", stages=");
		builder.append(stages);
		builder.append(", nationalContacts=");
		builder.append(nationalContacts);
		builder.append(", chapterContacts=");
		builder.append(chapterContacts);
		builder.append(", sponsorContacts=");
		builder.append(sponsorContacts);
		builder.append(", followUps=");
		builder.append(followUps);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chapterContacts == null) ? 0 : chapterContacts.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((existingSponsor == null) ? 0 : existingSponsor.hashCode());
		result = prime * result + ((followUps == null) ? 0 : followUps.hashCode());
		result = prime * result + id;
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((membershipProposed == null) ? 0 : membershipProposed.hashCode());
		result = prime * result + ((nationalBenefits == null) ? 0 : nationalBenefits.hashCode());
		result = prime * result + ((nationalContacts == null) ? 0 : nationalContacts.hashCode());
		result = prime * result + ((nationalConventionSponsor == null) ? 0 : nationalConventionSponsor.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((potentionalCoseDate == null) ? 0 : potentionalCoseDate.hashCode());
		result = prime * result + ((primaryGroupCode == null) ? 0 : primaryGroupCode.hashCode());
		result = prime * result + proposedAmount;
		result = prime * result + ((proposedContractTerms == null) ? 0 : proposedContractTerms.hashCode());
		result = prime * result + ((relationWithNationals == null) ? 0 : relationWithNationals.hashCode());
		result = prime * result + ((sponsorContacts == null) ? 0 : sponsorContacts.hashCode());
		result = prime * result + ((sponsorName == null) ? 0 : sponsorName.hashCode());
		result = prime * result + ((stages == null) ? 0 : stages.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + winningPercentage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opportunity other = (Opportunity) obj;
		if (chapterContacts == null) {
			if (other.chapterContacts != null)
				return false;
		} else if (!chapterContacts.equals(other.chapterContacts))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (existingSponsor == null) {
			if (other.existingSponsor != null)
				return false;
		} else if (!existingSponsor.equals(other.existingSponsor))
			return false;
		if (followUps == null) {
			if (other.followUps != null)
				return false;
		} else if (!followUps.equals(other.followUps))
			return false;
		if (id != other.id)
			return false;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		if (membershipProposed == null) {
			if (other.membershipProposed != null)
				return false;
		} else if (!membershipProposed.equals(other.membershipProposed))
			return false;
		if (nationalBenefits == null) {
			if (other.nationalBenefits != null)
				return false;
		} else if (!nationalBenefits.equals(other.nationalBenefits))
			return false;
		if (nationalContacts == null) {
			if (other.nationalContacts != null)
				return false;
		} else if (!nationalContacts.equals(other.nationalContacts))
			return false;
		if (nationalConventionSponsor == null) {
			if (other.nationalConventionSponsor != null)
				return false;
		} else if (!nationalConventionSponsor.equals(other.nationalConventionSponsor))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (potentionalCoseDate == null) {
			if (other.potentionalCoseDate != null)
				return false;
		} else if (!potentionalCoseDate.equals(other.potentionalCoseDate))
			return false;
		if (primaryGroupCode == null) {
			if (other.primaryGroupCode != null)
				return false;
		} else if (!primaryGroupCode.equals(other.primaryGroupCode))
			return false;
		if (proposedAmount != other.proposedAmount)
			return false;
		if (proposedContractTerms == null) {
			if (other.proposedContractTerms != null)
				return false;
		} else if (!proposedContractTerms.equals(other.proposedContractTerms))
			return false;
		if (relationWithNationals == null) {
			if (other.relationWithNationals != null)
				return false;
		} else if (!relationWithNationals.equals(other.relationWithNationals))
			return false;
		if (sponsorContacts == null) {
			if (other.sponsorContacts != null)
				return false;
		} else if (!sponsorContacts.equals(other.sponsorContacts))
			return false;
		if (sponsorName == null) {
			if (other.sponsorName != null)
				return false;
		} else if (!sponsorName.equals(other.sponsorName))
			return false;
		if (stages == null) {
			if (other.stages != null)
				return false;
		} else if (!stages.equals(other.stages))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (winningPercentage != other.winningPercentage)
			return false;
		return true;
	}

}
