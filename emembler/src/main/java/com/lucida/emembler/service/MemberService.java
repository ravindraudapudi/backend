package com.lucida.emembler.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lucida.emembler.dao.FyRepository;
import com.lucida.emembler.dao.LogRepository;
import com.lucida.emembler.dao.MemberRepository;
import com.lucida.emembler.dao.MemberVersionRepository;
import com.lucida.emembler.dao.SocialMediaRepository;
import com.lucida.emembler.dao.TransactionRepository;
import com.lucida.emembler.entity.CsvLog;
import com.lucida.emembler.entity.FyData;
import com.lucida.emembler.entity.Member;
import com.lucida.emembler.entity.MembersVersionData;
import com.lucida.emembler.entity.SocialMedia;
import com.lucida.emembler.entity.Transaction;
import com.lucida.emembler.exceptions.InvalidDataException;
import com.lucida.emembler.requestdtos.MemberRequest;
import com.lucida.emembler.responsedtos.ChapterOverview;
import com.lucida.emembler.responsedtos.FiscalMembership;
import com.lucida.emembler.responsedtos.Media;
import com.lucida.emembler.responsedtos.PaidSponseredDto;
import com.lucida.emembler.responsedtos.Revenue;
import com.lucida.emembler.responsedtos.SocialMediaResponse;
import com.lucida.emembler.responsedtos.TotalRevenueResponse;
import com.lucida.emembler.utility.Task;
import com.lucida.emembler.utility.Utility;

/**
 * 
 * @author Lucida Service class to update Members into database.
 */
@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberVersionRepository memberVersionRepository;

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private SocialMediaRepository socialMediaRepository;

	@Autowired
	private ForkJoinPool forkJoinPool;

	@Autowired
	private FyRepository fyRepository;

	private List<Member> otherMembers = new ArrayList<>();

	@Autowired
	private TransactionRepository transactionRepository; 
	/**
	 * Saving membership Details
	 * 
	 * @param memberships
	 * @throws InvalidDataException
	 */
	public void saveMembershipData(MemberRequest request) {

		Task task = new Task(request, memberRepository, memberVersionRepository, 0, request.getContent().size());
		forkJoinPool.execute(task);

		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", forkJoinPool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", forkJoinPool.getActiveThreadCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());

		/**
		 * Updating the file upload status in audit table
		 */
		updateMembershipLog(request);
	}

	/**
	 * Getting members For chapter
	 * 
	 * @param groupCode
	 * @return
	 */
	@Cacheable("getMembrsForChapter")
	public List<Member> getMembrsForChapter(String groupCode) {
		return memberRepository.findByPrimaryGroupCode(groupCode);
	}

	
	/**
	 * To get local Sponsored Members
	 * 
	 * @param type
	 * @param primGroupCode
	 * @param reportDate
	 * @return
	 */
	PaidSponseredDto getLocalSponser(String type, String primGroupCode, Date reportDate, int fiscalYear) {

		PaidSponseredDto activePaid = new PaidSponseredDto();
		List<FiscalMembership> members = new ArrayList<>();

		for (int i = (fiscalYear - 2); i <= fiscalYear; i++) {

			List<Member> memberList = null;
			Date date = null;

			FiscalMembership fiscalMembership = new FiscalMembership();

			if (fiscalYear == i) {
				memberList = memberRepository.getLocalSponsorMembers(reportDate, reportDate, primGroupCode);
				List<Member> latest = new ArrayList<>();
				for(Member member : memberList) {
					Transaction tr = transactionRepository.findLatestCodeRecord(member.getApiGuid());
					member.setPromotionalCode(tr.getSponsershipCode());
					member.setDiscountCode(tr.getSponsershipCode());
					latest.add(member);
				}
				otherMembers.addAll(latest);
				fiscalMembership.setList(latest);
				fiscalMembership.setName("FY " + i);
				fiscalMembership.setValue(memberList.size());

			} else if ((fiscalYear - 1) == i) {
				Date lastFiscalEndDate = new GregorianCalendar((fiscalYear - 1), Calendar.SEPTEMBER, 30).getTime();
				date = logRepository.getMaxReportingDate(lastFiscalEndDate);
				memberList = memberRepository.getLocalSponsorMembers(date, date, primGroupCode);
				
				List<Member> latest = new ArrayList<>();
				for(Member member : memberList) {
					Transaction tr = transactionRepository.findLatestCodeRecord(member.getApiGuid());
					member.setPromotionalCode(tr.getSponsershipCode());
					member.setDiscountCode(tr.getSponsershipCode());
					latest.add(member);
				}

				fiscalMembership.setList(latest);
				fiscalMembership.setName("FY " + i);
				fiscalMembership.setValue(memberList.size());

			} else {
				Date lastToLastFiscalEndDate = new GregorianCalendar((fiscalYear - 2), Calendar.SEPTEMBER, 30)
						.getTime();
				date = logRepository.getMaxReportingDate(lastToLastFiscalEndDate);

				if (date != null) {
					memberList = memberRepository.getLocalSponsorMembers(date, date, primGroupCode);

					fiscalMembership.setList(memberList);
					fiscalMembership.setName("FY " + i);
					fiscalMembership.setValue(memberList.size());
				} else {
					fiscalMembership.setName("FY " + i);
				}
			}
			members.add(fiscalMembership);
		}

		activePaid.setKey(type);
		activePaid.setValues(members);
		activePaid.setFiscalYear(fiscalYear);
		return activePaid;
	}

	
	/**
	 * National sponsored members details
	 * 
	 * @param type
	 * @param primGroupCode
	 * @param reportDate
	 * @return
	 */
	PaidSponseredDto getNationalSponser(String type, String primGroupCode, Date reportDate, int fiscalYear) {

		PaidSponseredDto activePaid = new PaidSponseredDto();
		List<FiscalMembership> members = new ArrayList<>();

		for (int i = (fiscalYear - 2); i <= fiscalYear; i++) {

			List<Member> memberList = null;
			Date date = null;

			FiscalMembership fiscalMembership = new FiscalMembership();

			if (fiscalYear == i) {
				memberList = memberRepository.getNationalSponsorMembers(reportDate, reportDate, primGroupCode);
				
				List<Member> latest = new ArrayList<>();
				for(Member member : memberList) {
					Transaction tr = transactionRepository.findLatestCodeRecord(member.getApiGuid());
					member.setPromotionalCode(tr.getSponsershipCode());
					member.setDiscountCode(tr.getSponsershipCode());
					latest.add(member);
				}
				
				otherMembers.addAll(memberList);

				fiscalMembership.setList(memberList);
				fiscalMembership.setName("FY " + i);
				fiscalMembership.setValue(memberList.size());

			} else if ((fiscalYear - 1) == i) {
				Date lastFiscalEndDate = new GregorianCalendar((fiscalYear - 1), Calendar.SEPTEMBER, 30).getTime();
				date = logRepository.getMaxReportingDate(lastFiscalEndDate);
				memberList = memberRepository.getNationalSponsorMembers(date, date, primGroupCode);
				
				List<Member> latest = new ArrayList<>();
				for(Member member : memberList) {
					Transaction tr = transactionRepository.findLatestCodeRecord(member.getApiGuid());
					member.setPromotionalCode(tr.getSponsershipCode());
					member.setDiscountCode(tr.getSponsershipCode());
					latest.add(member);
				}
				
				fiscalMembership.setList(latest);
				fiscalMembership.setName("FY " + i);
				fiscalMembership.setValue(memberList.size());

			} else {
				Date lastToLastFiscalEndDate = new GregorianCalendar((fiscalYear - 2), Calendar.SEPTEMBER, 30)
						.getTime();
				date = logRepository.getMaxReportingDate(lastToLastFiscalEndDate);

				if (date != null) {
					memberList = memberRepository.getNationalSponsorMembers(date, date, primGroupCode);

					fiscalMembership.setList(memberList);
					fiscalMembership.setName("FY " + i);
					fiscalMembership.setValue(memberList.size());
				} else {
					fiscalMembership.setName("FY " + i);
				}
			}
			members.add(fiscalMembership);
		}

		activePaid.setKey(type);
		activePaid.setValues(members);
		activePaid.setFiscalYear(fiscalYear);
		return activePaid;

	}

	
	/**
	 * get paid members  
	 * @param type
	 * @param primGroupCode
	 * @param reportDate
	 * @param fiscalYear
	 * @return
	 */
	PaidSponseredDto getPaidMembers(String type, String primGroupCode, Date reportDate, int fiscalYear) {
		PaidSponseredDto activePaid = new PaidSponseredDto();
		List<FiscalMembership> members = new ArrayList<>();
		for (int i = (fiscalYear - 2); i <= fiscalYear; i++) {
			List<Member> memberList = null;
			Date date = null;
			FiscalMembership fiscalMembership = new FiscalMembership();
			if (fiscalYear == i) {
				memberList = memberRepository.findCreditCardMemebrs(reportDate, reportDate, primGroupCode);
				otherMembers.addAll(memberList);
				fiscalMembership.setList(memberList);
				fiscalMembership.setName("FY " + i);
				fiscalMembership.setValue(memberList.size());
			} else if ((fiscalYear - 1) == i) {
				Date lastFiscalEndDate = new GregorianCalendar((fiscalYear - 1), Calendar.SEPTEMBER, 30).getTime();
				date = logRepository.getMaxReportingDate(lastFiscalEndDate);
				memberList = memberRepository.findCreditCardMemebrs(date, date, primGroupCode);
				fiscalMembership.setList(memberList);
				fiscalMembership.setName("FY " + i);
				fiscalMembership.setValue(memberList.size());
			} else {
				Date lastToLastFiscalEndDate = new GregorianCalendar((fiscalYear - 2), Calendar.SEPTEMBER, 30)
						.getTime();
				date = logRepository.getMaxReportingDate(lastToLastFiscalEndDate);
				if (date != null) {
					memberList = memberRepository.findCreditCardMemebrs(date, date, primGroupCode);
					fiscalMembership.setList(memberList);
					fiscalMembership.setName("FY " + i);
					fiscalMembership.setValue(memberList.size());
				} else {
					fiscalMembership.setName("FY " + i);
				}
			}
			members.add(fiscalMembership);
		}
		activePaid.setKey(type);
		activePaid.setValues(members);
		activePaid.setFiscalYear(fiscalYear);
		return activePaid;
	}

	/**
	 * Get other members
	 * 
	 * @param type
	 * @param primGroupCode
	 * @param reportDate
	 * @return
	 */
	PaidSponseredDto getOtherMembers(String type, String primGroupCode, Date reportDate, int fiscalYear) {
		PaidSponseredDto activePaid = new PaidSponseredDto();
		List<FiscalMembership> members = new ArrayList<>();
		for (int i = (fiscalYear - 2); i <= fiscalYear; i++) {
			List<Member> memberList = new ArrayList<>();
			FiscalMembership fiscalMembership = new FiscalMembership();
			if (fiscalYear == i) {
				List<Member> list = memberRepository.getTotalActiveMembersDetails(reportDate, reportDate,
						primGroupCode);
				list.removeAll(otherMembers);
				Set<Member> set = new HashSet<>(list);
				for (Member member : set) {
					MembersVersionData mvd = memberVersionRepository.findMaxTransactionForGuid(member.getApiGuid());
					if (mvd != null) {
						member.setPaymentType(mvd.getPaymentType());
						member.setDateProcessed(mvd.getDateProcessed());
						member.setPromotionalCode(mvd.getPromotionalCode());
						memberList.add(member);
					} else {
						memberList.add(member);
					}
				}
				fiscalMembership.setList(memberList);
				fiscalMembership.setName("FY " + i);
				fiscalMembership.setValue(memberList.size());
			} else if ((fiscalYear - 1) == i) {
				Date lastFiscalEndDate = new GregorianCalendar((fiscalYear - 1), Calendar.SEPTEMBER, 30).getTime();
				Date date = logRepository.getMaxReportingDate(lastFiscalEndDate);
				memberList = memberRepository.findOtherMembers(date, date, primGroupCode);
				fiscalMembership.setList(memberList);
				fiscalMembership.setName("FY " + i);
				fiscalMembership.setValue(memberList.size());
			} else {
				Date lastFiscalEndDate = new GregorianCalendar((fiscalYear - 2), Calendar.SEPTEMBER, 30).getTime();
				Date date = logRepository.getMaxReportingDate(lastFiscalEndDate);
				memberList = memberRepository.findOtherMembers(date, date, primGroupCode);
				if (date != null) {
					fiscalMembership.setList(memberList);
					fiscalMembership.setName("FY " + i);
					fiscalMembership.setValue(memberList.size());
				} else {
					fiscalMembership.setName("FY " + i);
				}
			}
			members.add(fiscalMembership);
		}
		activePaid.setKey(type);
		activePaid.setValues(members);
		activePaid.setFiscalYear(fiscalYear);
		return activePaid;
	}

	/**
	 * 
	 * @param type
	 * @param primGroupCode
	 * @param memberType
	 * @return Memebrship monthly paid and expired memebrship data
	 */
	@Cacheable("getPaidExpiredMembers")
	public List<PaidSponseredDto> getPaidExpiredMembers(String primGroupCode, Date reportingDate, int fiscalYear) {

		List<PaidSponseredDto> result = new ArrayList<>();
		PaidSponseredDto expiredDto = new PaidSponseredDto();
		List<FiscalMembership> expired = new ArrayList<>();
		for (int i = 10; i <= 12; i++) {
			FiscalMembership fiscalMembership = new FiscalMembership();
			List<Member> members = memberRepository.findMonthlyPaidExpiredMembers((fiscalYear - 1), i, reportingDate,
					primGroupCode);
			fiscalMembership.setList(members);
			fiscalMembership.setName(theMonth(i));
			fiscalMembership.setValue(members.size());
			expired.add(fiscalMembership);
		}
		for (int i = 1; i < 10; i++) {
			FiscalMembership fiscalMembership = new FiscalMembership();
			List<Member> members = memberRepository.findMonthlyPaidExpiredMembers(fiscalYear, i, reportingDate,
					primGroupCode);
			fiscalMembership.setList(members);
			fiscalMembership.setName(theMonth(i));
			fiscalMembership.setValue(members.size());
			expired.add(fiscalMembership);
		}
		expiredDto.setKey("Expired");
		expiredDto.setValues(expired);
		expiredDto.setFiscalYear(fiscalYear);
		PaidSponseredDto paidDto = new PaidSponseredDto();
		List<FiscalMembership> paidActive = new ArrayList<>();
		for (int i = 10; i <= 12; i++) {
			FiscalMembership fiscalMembership = new FiscalMembership();
			List<Member> members = memberRepository.findMonthlyPaidActiveMembers((fiscalYear - 1), i, reportingDate,
					primGroupCode);
			List<Member> list = new ArrayList<>();
			for (Member member : members) {
				member.setExpiryDate(memberRepository.getExpiryDate(member.getApiGuid()));
				list.add(member);
			}
			fiscalMembership.setList(list);
			fiscalMembership.setName(theMonth(i));
			fiscalMembership.setValue(members.size());
			paidActive.add(fiscalMembership);
		}

		for (int i = 1; i < 10; i++) {
			FiscalMembership fiscalMembership = new FiscalMembership();
			List<Member> members = memberRepository.findMonthlyPaidActiveMembers(fiscalYear, i, reportingDate,
					primGroupCode);
			List<Member> list = new ArrayList<>();
			for (Member member : members) {
				member.setExpiryDate(memberRepository.getExpiryDate(member.getApiGuid()));
				list.add(member);
			}
			fiscalMembership.setList(list);
			fiscalMembership.setName(theMonth(i));
			fiscalMembership.setValue(members.size());
			paidActive.add(fiscalMembership);
		}
		paidDto.setKey("Paid");
		paidDto.setValues(paidActive);
		paidDto.setFiscalYear(fiscalYear);
		result.add(paidDto);
		result.add(expiredDto);
		return result;
	}

	/**
	 * To update the MemberRequest CSV log data
	 * 
	 * @param request
	 */

	private void updateMembershipLog(MemberRequest request) {
	
		CsvLog csvLog = new CsvLog();
		csvLog.setDocName(request.getFileName());
		csvLog.setStatus("SUCCESS");
		csvLog.setUploadedBy(request.getUploadedBy());
		csvLog.setUploadedOn(new Date());
		csvLog.setReportingDate(Utility.getStringToDate(request.getReportedDate()));
		csvLog.setDocType(request.getType());

		logRepository.save(csvLog);
	}

	public static String theMonth(int month) {
		String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		return monthNames[month - 1];
	}

	/**
	 * Paid Sponsored Chart
	 * 
	 * @param primGroupCode
	 * @return
	 */
	@Cacheable("findSponseredMembers")
	public List<PaidSponseredDto> findSponseredMembers(String primGroupCode, Date reportingDate, int fiscalYear) {
		List<PaidSponseredDto> paidSponsered = new ArrayList<>();
		paidSponsered.add(getPaidMembers("Member Paid", primGroupCode, reportingDate, fiscalYear));
		paidSponsered.add(getLocalSponser("Local Sponsor-Paid", primGroupCode, reportingDate, fiscalYear));
		paidSponsered.add(getNationalSponser("National Sponsor-Paid", primGroupCode, reportingDate, fiscalYear));
		paidSponsered.add(getOtherMembers("Other", primGroupCode, reportingDate, fiscalYear));
		return paidSponsered;
	}

	/**
	 * 
	 * @param type
	 * @param primGroupCode
	 * @param memberType
	 * @return Monthly Membership revenue
	 */
	@Cacheable("getMonthlyMembershipRevenue")
	public List<PaidSponseredDto> getMonthlyMembershipRevenue(String primGroupCode, String reportingDate,
			int fiscalYear) {
		List<PaidSponseredDto> result = new ArrayList<>();
		int count1 = 0;
		Date rDate = Utility.getStringToDate(reportingDate);
		PaidSponseredDto expiredDto = new PaidSponseredDto();
		List<FiscalMembership> expired = new ArrayList<>();
		Date lastRdate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));
		for (int i = 10; i <= 12; i++) {
			int creditCardPayment = 0, squarePayment = 0;
			FiscalMembership fiscalMembership = new FiscalMembership();
			Integer mRevenue = memberRepository.findMonthlyMemebrshipRevenue(fiscalYear - 1, i, rDate, primGroupCode);
			if (mRevenue != null) {
				creditCardPayment = mRevenue;
			}
			fiscalMembership.setValue(creditCardPayment + squarePayment);
			fiscalMembership.setName(theMonth(i));
			fiscalMembership.setMonth(count1++);
			expired.add(fiscalMembership);
		}

		for (int i = 1; i < 10; i++) {
			int creditCardPayment = 0, squarePayment = 0;
			FiscalMembership fiscalMembership = new FiscalMembership();
			fiscalMembership.setMonth(count1++);
			fiscalMembership.setName(theMonth(i));
			Integer monthlyRevenue = memberRepository.findMonthlyMemebrshipRevenue(fiscalYear, i, rDate, primGroupCode);
			if (monthlyRevenue != null) {
				creditCardPayment = monthlyRevenue;
			}
			fiscalMembership.setValue(creditCardPayment + squarePayment);
			expired.add(fiscalMembership);
		}
		expiredDto.setKey("FY " + fiscalYear);
		expiredDto.setValues(expired);
		expiredDto.setFiscalYear(fiscalYear);
		PaidSponseredDto paidDto = new PaidSponseredDto();
		List<FiscalMembership> paidActive = new ArrayList<>();
		int count2 = 0;
		for (int i = 10; i <= 12; i++) {
			int creditCardPayment = 0, squarePayment = 0;
			FiscalMembership fiscalMembership = new FiscalMembership();
			Integer currentMrevenue = memberRepository.findMonthlyMemebrshipRevenue(fiscalYear - 2, i, lastRdate,
					primGroupCode);
			if (currentMrevenue != null) {
				creditCardPayment = currentMrevenue;
			}
			fiscalMembership.setValue(creditCardPayment + squarePayment);
			fiscalMembership.setMonth(count2++);
			fiscalMembership.setName(theMonth(i));
			paidActive.add(fiscalMembership);
		}
		for (int i = 1; i < 10; i++) {
			int creditCardPayment = 0, squarePayment = 0;
			FiscalMembership fiscalMembership = new FiscalMembership();
			Integer currentMonthRevenue = memberRepository.findMonthlyMemebrshipRevenue((fiscalYear - 1), i, lastRdate,
					primGroupCode);
			if (currentMonthRevenue != null) {
				creditCardPayment = currentMonthRevenue;
			}
			fiscalMembership.setValue(creditCardPayment + squarePayment);
			fiscalMembership.setName(theMonth(i));
			fiscalMembership.setMonth(count2++);
			paidActive.add(fiscalMembership);
		}

		paidDto.setKey(" FY " + (fiscalYear - 1));
		paidDto.setFiscalYear(fiscalYear);
		paidDto.setValues(paidActive);
		paidDto.setFiscalYear(fiscalYear);
		result.add(paidDto);
		result.add(expiredDto);
		return result;
	}

	/**
	 * Overview details for the
	 * 
	 * @param chapter
	 * @return
	 */
	@Cacheable("getOverviewDetails")
	public ChapterOverview getOverviewDetails(String chapter, String reportingDate, int fiscalYear) {

		Date rDate = Utility.getStringToD(reportingDate);
		ChapterOverview chapterOverview = new ChapterOverview();
		/**
		 * Setting total revenue for current fiscal year
		 */

		double totalRevenue = 0;
		FyData fiscalDetails = fyRepository.findByFiscalYearAndPrimaryCode(chapter, fiscalYear);
		if (fiscalDetails != null) {
			totalRevenue = fiscalDetails.getTotalRevenue();
		}
		chapterOverview.setTotalRevenue(totalRevenue);

		/**
		 * Calculation for paid and local sponsored members for the current fiscal year
		 */

		int paidMembers = memberRepository.findCreditCardMemebrsCount(rDate, rDate, chapter);
		int localSponsered = memberRepository.getLocalSponsorMembershipByChapter(rDate, rDate, chapter);
		chapterOverview.setPaidSponsored(paidMembers + localSponsered);

		/**
		 * Calculation for the the Memberships
		 */

		int goal = 0;
		if (fiscalDetails != null) {
			goal = fiscalDetails.getPaidMembershipGoal() + fiscalDetails.getLocalSponseredMembershipGoal();
		}
		int actual = paidMembers + localSponsered;

		/*
		 * Membership goal
		 */

		double percentage = 0;
		if (goal != 0) {
			percentage = (actual * 1.0 / goal) * 100;
		}
		chapterOverview.setMembershipGoal(percentage);
		chapterOverview.setMembershipGoalValue(goal);

		/**
		 * Calculation for the the Revenue
		 */

		double revenueGoal = 0;
		if (fiscalDetails != null) {
			revenueGoal = fiscalDetails.getTotalRevenueGoal();
		}
		double revenuePercentage = 0;
		if (revenueGoal != 0) {
			revenuePercentage = (totalRevenue / revenueGoal) * 100;
		}
		chapterOverview.setRevenueGoal(revenuePercentage);
		chapterOverview.setRevenueGoalValue(revenueGoal);

		/**
		 * Calculation for Expiration rate
		 */
		Date fiscalStartDate = new GregorianCalendar(fiscalYear - 1, Calendar.OCTOBER, 01).getTime();
		double expirationRate = 0;
		double expiredMembers = memberRepository.getExpiringMembersCountForFiscal(fiscalStartDate, rDate, chapter);

		if (fiscalYear == 2018) {
			int paidmembers = 0;
			FyData fydata = fyRepository.findByFiscalYearAndPrimaryCode(chapter, (fiscalYear - 1));
			if (fydata != null) {
				paidmembers = fydata.getPaidMembership();
			}
			if (paidmembers != 0) {
				expirationRate = (expiredMembers * 1.0 / paidmembers) * 100;
			}
		} else {
			Date reportDate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));
			int paidMembeForLastFiscal = memberRepository.findCreditCardMemebrsCount(reportDate,
					Utility.getFiscalEndDates(fiscalYear).get(1), chapter);

			if (paidMembeForLastFiscal != 0) {
				expirationRate = (expiredMembers * 1.0 / paidMembeForLastFiscal) * 100;
			}
		}
		chapterOverview.setExpirationRate(expirationRate);

		return chapterOverview;
	}

	/**
	 * Paid Total revenue Chart
	 * 
	 * @param primGroupCode
	 * @return
	 */
	@Cacheable("findTotalRevenue")
	public List<TotalRevenueResponse> findTotalRevenue(String primGroupCode, int fiscalYear) {
		List<TotalRevenueResponse> totalRevenue = new ArrayList<>();
		totalRevenue.add(getFiscalYearRevenue("Membership", primGroupCode, fiscalYear));
		totalRevenue.add(getFiscalYearRevenue("Events", primGroupCode, fiscalYear));
		totalRevenue.add(getFiscalYearRevenue("Program", primGroupCode, fiscalYear));
		totalRevenue.add(getFiscalYearRevenue("Other (Includes Contribution Revenue + In-Kind Sponsorships)",
				primGroupCode, fiscalYear));
		return totalRevenue;
	}

	/**
	 * To find the total Revenue details from P&L
	 * 
	 * @param type
	 * @param primGroupCode
	 * @return
	 */
	TotalRevenueResponse getFiscalYearRevenue(String type, String primGroupCode, int fiscalYear) {

		TotalRevenueResponse totalRevenueResponse = new TotalRevenueResponse();
		List<Revenue> values = new ArrayList<>();

		for (int i = -2; i <= 0; i++) {
			int year = fiscalYear;
			year = year + i;
			FyData fy = fyRepository.findByFiscalYearAndPrimaryCode(primGroupCode, year);
			if (year == fiscalYear && fy != null) {
				totalRevenueResponse.setRevenueTerm(fy.getRevenueTerm());
			}
			if (fy != null) {
				Revenue revenue = new Revenue();
				if (type.equalsIgnoreCase("Membership")) {
					revenue.setFiscalYear(year);
					revenue.setRevenue(fy.getMembershipIncome());
				} else if (type.equalsIgnoreCase("Events")) {
					revenue.setFiscalYear(year);
					revenue.setRevenue(fy.getRegistrationEvents());
				} else if (type.equalsIgnoreCase("Program")) {
					revenue.setFiscalYear(year);
					revenue.setRevenue(fy.getProgramRevenue());
				} else {
					revenue.setFiscalYear(year);
					revenue.setRevenue(fy.getContributionRevenue());
				}
				values.add(revenue);
			}
		}

		totalRevenueResponse.setKey(type);
		totalRevenueResponse.setValues(values);
		return totalRevenueResponse;
	}

	/**
	 * Getting Social Media details
	 * 
	 * @param primGroupCode
	 * @return
	 */

	@Cacheable("getSocialMedia")
	public List<SocialMediaResponse> getSocialMedia(String primGroupCode) {

		List<SocialMediaResponse> socialMediaResponse = new ArrayList<>();
		socialMediaResponse.add(getMedia("LinkedIn Page Followers", primGroupCode));
		socialMediaResponse.add(getMedia("Distribution List of Professional Group", primGroupCode));
		socialMediaResponse.add(getMedia("Facebook Page Likes", primGroupCode));
		socialMediaResponse.add(getMedia("Instagram Followers", primGroupCode));
		socialMediaResponse.add(getMedia("LinkedIn Group Members", primGroupCode));
		socialMediaResponse.add(getMedia("Twitter Followers", primGroupCode));
		return socialMediaResponse;

	}

	/**
	 * to find the total Revenue details from P&L
	 * 
	 * @param type
	 * @param primGroupCode
	 * @return
	 */
	SocialMediaResponse getMedia(String type, String primGroupCode) {

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		Date startDt = new GregorianCalendar(year - 1, month, 28).getTime();
		Date endDt = new GregorianCalendar(year, month, 28).getTime();
		SocialMediaResponse socialMediaResponse = new SocialMediaResponse();
		List<Media> values = new ArrayList<>();
		List<SocialMedia> mediaList = socialMediaRepository.findForFiscalYear(primGroupCode);
		for (SocialMedia socialMedia : mediaList) {
			Media media = new Media();
			if (type.equalsIgnoreCase("LinkedIn Page Followers")) {
				if (socialMedia.getMonthlyDate().after(startDt) && socialMedia.getMonthlyDate().before(endDt)) {
					media.setMonth(theMonth(socialMedia.getMonthlyDate().getMonth() + 1));
					media.setValue(socialMedia.getLinkedInPage());
				}
			} else if (type.equalsIgnoreCase("Distribution List of Professional Group")) {
				if (socialMedia.getMonthlyDate().after(startDt) && socialMedia.getMonthlyDate().before(endDt)) {
					media.setMonth(theMonth(socialMedia.getMonthlyDate().getMonth() + 1));
					media.setValue(socialMedia.getDistributionList());
				}
			} else if (type.equalsIgnoreCase("Facebook Page Likes")) {
				if (socialMedia.getMonthlyDate().after(startDt) && socialMedia.getMonthlyDate().before(endDt)) {
					media.setMonth(theMonth(socialMedia.getMonthlyDate().getMonth() + 1));
					media.setValue(socialMedia.getFacebookPage());
				}
			} else if (type.equalsIgnoreCase("Instagram Followers")) {
				if (socialMedia.getMonthlyDate().after(startDt) && socialMedia.getMonthlyDate().before(endDt)) {
					media.setMonth(theMonth(socialMedia.getMonthlyDate().getMonth() + 1));
					media.setValue(socialMedia.getInstagramPage());
				}
			} else if (type.equalsIgnoreCase("Twitter Followers")) {
				if (socialMedia.getMonthlyDate().after(startDt) && socialMedia.getMonthlyDate().before(endDt)) {
					media.setMonth(theMonth(socialMedia.getMonthlyDate().getMonth() + 1));
					media.setValue(socialMedia.getTwitter());
				}
			} else {
				if (socialMedia.getMonthlyDate().after(startDt) && socialMedia.getMonthlyDate().before(endDt)) {
					media.setMonth(theMonth(socialMedia.getMonthlyDate().getMonth() + 1));
					media.setValue(socialMedia.getLinkedInGroup());
				}
			}
			if (media.getMonth() != null) {
				values.add(media);
			}

		}
		socialMediaResponse.setKey(type);
		socialMediaResponse.setValues(values);
		return socialMediaResponse;
	}

	/**
	 * To get Members for the redeemed Sponsorship code
	 * 
	 * @param sponserhipCode
	 * @return
	 */

	@Cacheable("getMembersForSponsorhipCode")
	public List<Member> getMembersForSponsorhipCode(String sponserhipCode) {
		return memberRepository.getMembersForSponsorhipCode(sponserhipCode);
	}

	/**
	 * To get Members details for api guid
	 * 
	 * @param sponserhipCode
	 * @return
	 */
	public Member getMemberDetailsForApiGuid(String apiGuid) {
		return memberRepository.findByApiGuid(apiGuid);
	}
}
