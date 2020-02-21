package com.lucida.emembler.utility;

import java.util.Date;
import java.util.concurrent.RecursiveAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lucida.emembler.dao.MemberRepository;
import com.lucida.emembler.dao.MemberVersionRepository;
import com.lucida.emembler.entity.Address;
import com.lucida.emembler.entity.Employer;
import com.lucida.emembler.entity.Member;
import com.lucida.emembler.entity.MemberOfficialDetails;
import com.lucida.emembler.entity.MembersVersionData;
import com.lucida.emembler.requestdtos.MemberDto;
import com.lucida.emembler.requestdtos.MemberRequest;

/**
 * 
 * @author Ravindra
 *	Upload Members Details
 */
public class Task extends RecursiveAction {

	private static final long serialVersionUID = -330590816004276095L;

	private static final Logger logger = LoggerFactory.getLogger(RecursiveAction.class);

	private MemberRequest memberRequest;
	private MemberRepository memberRepository;
	private MemberVersionRepository memberVersionRepository;
	private int first;
	private int last;

	public Task(MemberRequest memberRequest, MemberRepository memberRepository,
			MemberVersionRepository memberVersionRepository, int first, int last) {
		this.memberRequest = memberRequest;
		this.memberRepository = memberRepository;
		this.memberVersionRepository = memberVersionRepository;
		this.first = first;
		this.last = last;
	}

	@Override
	protected void compute() {

		if (last - first < 100) {
			updateMembershipData();
		} else {
			int middle = (last + first) / 2;
			Task t1 = new Task(memberRequest, memberRepository, memberVersionRepository, first, middle + 1);
			Task t2 = new Task(memberRequest, memberRepository, memberVersionRepository, middle + 1, last);
			invokeAll(t1, t2);
		}

	}

	/**
	 * Updating the Membership details in the Members and Distribution file
	 */
	public void updateMembershipData() {
		Date rDate = Utility.getStringToDate(this.memberRequest.getReportedDate());
		try {
			for (int i = first; i < last; i++) {
				MemberDto memberDto = memberRequest.getContent().get(i);
				Date expiryDate = Utility.getStringToDate(memberDto.getExpiresOn());
				if (memberDto.getApiGuid() != null) {
					if (memberVersionRepository.isExistByGuidAndReportingDate(memberDto.getApiGuid(), rDate)) {
						if (memberDto.getPrimagGroupCode() != null) {
							memberVersionRepository.updateVersionData(expiryDate,
									memberDto.getPrimagGroupCode().toUpperCase(), memberDto.getApiGuid(), rDate);
							memberVersionRepository.flush();
						}
					} else {
						MembersVersionData membersVersionData = getMemberVersionDataModeler(memberDto,
								this.memberRequest.getReportedDate());
						memberVersionRepository.save(membersVersionData);
						memberVersionRepository.flush();
					}
					Member member = DataModeler(memberDto);
					memberRepository.save(member);
					memberRepository.flush();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Modeling the membership data.
	 * 
	 * @param membershipData.
	 * @return Member.
	 */
	private Member DataModeler(MemberDto membershipData) {

		Member member = new Member();

		member.setApiGuid(membershipData.getApiGuid());
		if (membershipData.getPrimagGroupCode() != null) {
			member.setPrimaryGroupCode(membershipData.getPrimagGroupCode().toUpperCase());
		}

		member.setRegistrationDate(Utility.getStringToDate(membershipData.getRegistrationDate()));
		member.setLastRenewed(Utility.getStringToDate(membershipData.getLastRenewedOn()));
		member.setExpiryDate(Utility.getStringToDate(membershipData.getExpiresOn()));
		member.setLastUpdated(Utility.getStringToDate(membershipData.getLastUpdated()));

		member.setFirstName(membershipData.getFirstName());
		member.setMiddleName(membershipData.getMiddleName());
		member.setLastName(membershipData.getLastName());
		member.setNameTitle(membershipData.getNameTitle());
		member.setNamePrefix(membershipData.getProfessionalTitle());
		member.setEmailBounced(membershipData.getEmailBounced());
		member.setEmail(membershipData.getEmailAddress());
		member.setAlternateEmails(membershipData.getEmailaAddressAlternate());
		member.setMobile(membershipData.getMobile());
		member.setMemberType(membershipData.getMembetTypeCode());
		member.setMobileAreaCode(membershipData.getMobilaAreaCode());
		if (membershipData.getMembership() != null) {
			member.setMembership(membershipData.getMembership().toUpperCase());
		}

		/**
		 * Setting Home address details
		 */
		member.setHomeAddress(setHomeAddress(membershipData));

		/**
		 * Setting official details
		 */
		MemberOfficialDetails memberOfficialDetails = new MemberOfficialDetails();
		memberOfficialDetails.setProfession(membershipData.getProfession());
		memberOfficialDetails.setProfessionalTitle(membershipData.getProfessionalTitle());

		member.setOfficialDetails(memberOfficialDetails);

		/**
		 * Setting Employer details
		 */
		Employer employer = new Employer();
		employer.setName(membershipData.getEmployerName());
		employer.setOfficeAddress(getEmployerAddress(membershipData));

		member.setEmployer(employer);

		return member;
	}

	/**
	 * Setting Home address
	 * 
	 * @param membershipData
	 * @return
	 */
	private Address setHomeAddress(MemberDto membershipData) {
		/**
		 * Member Personal address
		 */

		Address personalAddress = new Address();

		personalAddress.setCity(membershipData.getHomeCity());
		personalAddress.setLocation(membershipData.getHomeLocation());
		personalAddress.setCountry(membershipData.getHomeCountry());
		personalAddress.setStateOrProvince(membershipData.getHomeStateAbbrev());
		personalAddress.setHomePostalCode(membershipData.getHomePostalCode());
		personalAddress.setHomePhoneAreaCode(membershipData.getHomePhoneAreaCode());

		return personalAddress;

	}

	/**
	 * Setting Employer address
	 * 
	 * @param membershipData
	 * @return
	 */
	private Address getEmployerAddress(MemberDto membershipData) {
		/**
		 * Member Personal address
		 */

		Address employerAddress = new Address();
		employerAddress.setCity(membershipData.getEmployerCity());
		employerAddress.setStateOrProvince(membershipData.getEmployerStateAbbrev());
		employerAddress.setHomePostalCode(membershipData.getEmployerPostalCode());
		employerAddress.setCountry(membershipData.getEmployerCountry());
		employerAddress.setEmployerPhoneAreaCode(membershipData.getEmployerPhoneAreaCode());
		employerAddress.setEmployerPhone(membershipData.getEmployerPhone());

		return employerAddress;

	}

	/**
	 * Modeling the Members version data
	 * 
	 * @param membershipData
	 * @param reportingDate
	 * @return
	 */
	private MembersVersionData getMemberVersionDataModeler(MemberDto membershipData, String reportingDate) {

		MembersVersionData membersVersionData = new MembersVersionData();

		if (membershipData.getMembership() != null) {
			membersVersionData.setMemebrshipType(membershipData.getMembership().toUpperCase());
		}
		membersVersionData.setExpiryDate(Utility.getStringToDate(membershipData.getExpiresOn()));
		membersVersionData.setGuid(membershipData.getApiGuid());
		membersVersionData.setReportingDate(Utility.getStringToDate(reportingDate));
		membersVersionData.setPrimaryGroupCode(membershipData.getPrimagGroupCode().toUpperCase());
		return membersVersionData;
	}

}
