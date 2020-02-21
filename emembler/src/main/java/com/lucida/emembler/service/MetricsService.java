package com.lucida.emembler.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lucida.emembler.dao.FyRepository;
import com.lucida.emembler.dao.LogRepository;
import com.lucida.emembler.dao.MemberRepository;
import com.lucida.emembler.entity.FyData;
import com.lucida.emembler.responsedtos.DetailsMatricsResponse;
import com.lucida.emembler.responsedtos.Header;
import com.lucida.emembler.responsedtos.MatricsContent;
import com.lucida.emembler.utility.Utility;

/**
 * 
 * @author Lucida Metrics Service
 */
@Service
@Transactional
public class MetricsService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private FyRepository fyRepository;

	@Autowired
	private LogRepository logRepository;

	private static final Logger logger = LoggerFactory.getLogger(MetricsService.class);

	/**
	 * To get the detailed metrics for the selected chapter
	 * 
	 * @param chapter
	 * @return
	 */
	@Cacheable("detailedMetrics")
	public DetailsMatricsResponse getDetailedMetrics(String chapter, Date reportDate, int fiscalYear) {

		DetailsMatricsResponse matricsResponse = null;

		try {

			matricsResponse = new DetailsMatricsResponse();
			List<MatricsContent> matricsContent = new ArrayList<>();

			matricsResponse.setHeaders(getHeaders(fiscalYear));

			FyData period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter, fiscalYear - 2);
			FyData period2 = fyRepository.findByFiscalYearAndPrimaryCode(chapter, fiscalYear - 1);
			FyData actual = fyRepository.findByFiscalYearAndPrimaryCode(chapter, fiscalYear);

			/**
			 * Total Active Members
			 */
			matricsContent.add(getTotalActiveMembersDetails(chapter, fiscalYear, period1, period2, reportDate));

			/**
			 * Total Paid Members
			 */
			matricsContent.add(getPaidMembersDetails(chapter, fiscalYear, actual, period2, period1, reportDate));

			/**
			 * Get Members revenue
			 */
			matricsContent.add(getExpirationRate(actual, chapter, reportDate, fiscalYear));

			FyData fyData = fyRepository.findByFiscalYearAndPrimaryCode(chapter, fiscalYear);

			if (fyData != null && period1 != null && period2 != null) {

				/**
				 * Memebrship revenue
				 */
				matricsContent.add(getMembershipRevenueDetails(fyData, period1, period2));

				/**
				 * Event registration
				 */

				matricsContent.add(getEventRegistrationDetails(fyData, period1, period2));

				/**
				 * Program Revenue
				 */

				matricsContent.add(getProgramRevenueDetails(fyData, period1, period2));

				/**
				 * other Revenue
				 */

				matricsContent.add(getOtherRevenueDetails(fyData, period1, period2));

				/**
				 * Total Revenue
				 */
				matricsContent.add(getTotalRevenueDetails(fyData, period1, period2));

				matricsResponse.setMatricsContent(matricsContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return matricsResponse;
	}

	/**
	 * Get total active for detailed metrics
	 * 
	 * @param chapter
	 * @param fisaclYear
	 * @return
	 */
	MatricsContent getTotalActiveMembersDetails(String chapter, int fisaclYear, FyData period1, FyData period2,
			Date reportDate) {

		/**
		 * To get total active members for current fiscal year
		 */
		Date period2EndDt = new GregorianCalendar(fisaclYear - 1, Calendar.SEPTEMBER, 30).getTime();

		MatricsContent totalActive = new MatricsContent();
		totalActive.setChapterName("Active Members");
		int activeActual = memberRepository.getActiveMembersCount(reportDate, reportDate, chapter);
		totalActive.setActual(activeActual);

		double activeGoal = 0;
		double activePercentage = 0;

		FyData fyData = fyRepository.findByFiscalYearAndPrimaryCode(chapter, fisaclYear);
		if (fyData != null) {
			activeGoal = fyData.getTotalActiveMembershipGoal();
		}
		totalActive.setGoal(activeGoal);

		/**
		 * To get Total active members for the last year
		 */

		if (fisaclYear > 2019) {
			Date periode3ndDt = new GregorianCalendar(fisaclYear - 2, Calendar.SEPTEMBER, 30).getTime();
			Date rDate = logRepository.getMaxReportingDate(periode3ndDt);
			totalActive.setPeriod1(memberRepository.getActiveMembersCount(rDate, periode3ndDt, chapter));
		} else {
			if (period1 != null) {
				totalActive.setPeriod1(period1.getTotalActiveMembership());
			}
		}

		/**
		 * To get Total active members for the last to last year
		 */
		Date date = logRepository.getMaxReportingDate(period2EndDt);
		totalActive.setPeriod2(memberRepository.getActiveMembersCount(date, period2EndDt, chapter));

		/**
		 * Percentage calculations
		 */

		if (activeGoal != 0) {
			activePercentage = Math.round((activeActual * 1.0) / activeGoal * 100);
		}

		totalActive.setPercentage(activePercentage);

		return totalActive;

	}

	/**
	 * Get Paid for detailed metrics
	 * 
	 * @param chapter
	 * @param fisaclYear
	 * @return
	 */
	MatricsContent getPaidMembersDetails(String chapter, int fisaclYear, FyData actual, FyData period22,
			FyData period11, Date reportDate) {

		MatricsContent paid = new MatricsContent();

		int actualPaid = memberRepository.findCreditCardMemebrsCount(reportDate, reportDate, chapter);
		actualPaid += memberRepository.getLocalSponsorMembershipByChapter(reportDate, reportDate, chapter);

		paid.setActual(actualPaid);

		double goalPaid = 0;
		double paidPercentage = 0;
		double period2 = 0;
		double period1 = 0;

		FyData fyData = fyRepository.findByFiscalYearAndPrimaryCode(chapter, fisaclYear);

		if (fyData != null) {
			goalPaid = fyData.getPaidMembershipGoal() + fyData.getLocalSponseredMembershipGoal();
		}
		paid.setGoal(goalPaid);

		if ((fisaclYear - 1) < 2018) {
			if (period22 != null) {
				paid.setPeriod2(period22.getPaidMembership() + period22.getLocalSponseredMembership());
			}

		} else {
			Date period2EndDt = new GregorianCalendar(fisaclYear - 1, Calendar.SEPTEMBER, 30).getTime();
			Date rDate = logRepository.getMaxReportingDate(period2EndDt);
			period2 += memberRepository.findCreditCardMemebrsCount(rDate, rDate, chapter);
			period2 += memberRepository.getLocalSponsorMembershipByChapter(rDate, rDate, chapter);
			paid.setPeriod2(period2);
		}

		if ((fisaclYear - 2) < 2018) {
			if (period11 != null) {
				paid.setPeriod1(period11.getPaidMembership() + period11.getLocalSponseredMembership());
			}
		} else {
			Date period3EndDt = new GregorianCalendar(fisaclYear - 2, Calendar.SEPTEMBER, 30).getTime();
			Date rDate = logRepository.getMaxReportingDate(period3EndDt);
			period1 += memberRepository.findCreditCardMemebrsCount(rDate, rDate, chapter);
			period1 += memberRepository.getLocalSponsorMembershipByChapter(rDate, rDate, chapter);
			paid.setPeriod1(period1);
		}

		if (goalPaid != 0) {
			paidPercentage = Math.round((actualPaid * 1.0) / goalPaid * 100);
		}

		paid.setPercentage(paidPercentage);
		paid.setChapterName("Member-Paid + Local Sponsor-Paid Memberships");
		return paid;

	}

	private MatricsContent getMembershipRevenueDetails(FyData fyData, FyData fyDataPeriod1, FyData fyDataPeriod2) {

		double membershipRevenueActual = 0;
		double membershipRevenueGoal = 0;
		double membershipRevenuePercentage = 0;

		MatricsContent membershipRevenue = new MatricsContent();
		membershipRevenue.setChapterName("Membership Revenue");

		membershipRevenueActual = fyData.getMembershipIncome();
		membershipRevenue.setActual(Math.round(membershipRevenueActual));
		membershipRevenueGoal = fyData.getMembershipRevenueGoal();

		membershipRevenue.setPeriod1(Math.round(fyDataPeriod1.getMembershipIncome()));

		membershipRevenue.setPeriod2(Math.round(fyDataPeriod2.getMembershipIncome()));

		if (membershipRevenueGoal != 0) {
			membershipRevenuePercentage = Math.round((membershipRevenueActual * 1.0) / membershipRevenueGoal * 100);
		}
		membershipRevenue.setGoal(membershipRevenueGoal);
		membershipRevenue.setPercentage(membershipRevenuePercentage);

		return membershipRevenue;
	}

	/**
	 * To get Event registration details
	 * 
	 * @param fyData
	 * @param fyDataPeriod1
	 * @param fyDataPeriod2
	 * @return
	 */
	private MatricsContent getEventRegistrationDetails(FyData fyData, FyData fyDataPeriod1, FyData fyDataPeriod2) {

		double eventRegistrationActual = 0;
		double eventRegistrationGoal = 0;
		double eventRegistrationPercentage = 0;

		MatricsContent eventRegistration = new MatricsContent();

		eventRegistrationActual = fyData.getRegistrationEvents();
		eventRegistration.setActual(Math.round(eventRegistrationActual));
		eventRegistrationGoal = fyData.getRegistrationEventsGoal();

		if (fyDataPeriod1 != null) {
			eventRegistration.setPeriod1(Math.round(fyDataPeriod1.getRegistrationEvents()));
		}

		if (fyDataPeriod2 != null) {
			eventRegistration.setPeriod2(Math.round(fyDataPeriod2.getRegistrationEvents()));
		}

		if (eventRegistrationGoal != 0) {
			eventRegistrationPercentage = Math.round((eventRegistrationActual * 1.0) / eventRegistrationGoal * 100);
		}

		eventRegistration.setGoal(eventRegistrationGoal);
		eventRegistration.setPercentage(eventRegistrationPercentage);
		eventRegistration.setChapterName("Event Registration");

		return eventRegistration;

	}

	/**
	 * TO set the Program revenue details
	 * 
	 * @param fyData
	 * @param fyDataPeriod1
	 * @param fyDataPeriod2
	 * @return
	 */
	private MatricsContent getProgramRevenueDetails(FyData fyData, FyData fyDataPeriod1, FyData fyDataPeriod2) {
		double programRevenueActual = 0;
		double programRevenueGoal = 0;
		double programRevenuePercentage = 0;

		MatricsContent programRevenue = new MatricsContent();

		programRevenueActual = fyData.getProgramRevenue();
		programRevenue.setActual(Math.round(programRevenueActual));
		programRevenueGoal = fyData.getProgramRevenueGoal();
		programRevenue.setGoal(programRevenueGoal);
		programRevenue.setPeriod1(Math.round(fyDataPeriod1.getProgramRevenue()));
		programRevenue.setPeriod2(Math.round(fyDataPeriod2.getProgramRevenue()));

		if (programRevenueGoal != 0) {
			programRevenuePercentage = Math.round((programRevenueActual * 1.0) / programRevenueGoal * 100);
		}

		programRevenue.setPercentage(programRevenuePercentage);
		programRevenue.setChapterName("Sponsorship & Program Revenue");

		return programRevenue;

	}

	/**
	 * TO set the Program revenue details
	 * 
	 * @param fyData
	 * @param fyDataPeriod1
	 * @param fyDataPeriod2
	 * @return
	 */
	private MatricsContent getOtherRevenueDetails(FyData fyData, FyData fyDataPeriod1, FyData fyDataPeriod2) {
		double otherActual = 0;
		double otherGoal = 0;
		double otherPercentage = 0;

		MatricsContent otherRevenue = new MatricsContent();

		otherActual = fyData.getContributionRevenue();
		otherRevenue.setActual(Math.round(otherActual));
		otherGoal = fyData.getOtherRevenueGoal();
		otherRevenue.setGoal(otherGoal);
		otherRevenue.setPeriod1(Math.round(fyDataPeriod1.getContributionRevenue()));
		otherRevenue.setPeriod2(Math.round(fyDataPeriod2.getContributionRevenue()));

		if (otherGoal != 0) {
			otherPercentage = Math.round((otherActual * 1.0) / otherGoal * 100);
		}

		otherRevenue.setPercentage(otherPercentage);
		otherRevenue.setChapterName("Other (Includes Contribution Revenue + In-Kind Sponsorships)");

		return otherRevenue;

	}

	/**
	 * TO get the total revenue details
	 * 
	 * @param fyData
	 * @param fyDataPeriod1
	 * @param fyDataPeriod2
	 * @return
	 */
	private MatricsContent getTotalRevenueDetails(FyData fyData, FyData fyDataPeriod1, FyData fyDataPeriod2) {
		MatricsContent totalRevenue = new MatricsContent();
		double atcual = 0;
		double totalReveneueGoal = 0;
		double revenuePercentage = 0;

		atcual = fyData.getTotalRevenue();
		totalRevenue.setActual(Math.round(atcual));
		totalReveneueGoal = fyData.getTotalRevenueGoal();
		totalRevenue.setGoal(totalReveneueGoal);
		totalRevenue.setPeriod1(Math.round(fyDataPeriod1.getTotalRevenue()));
		totalRevenue.setPeriod2(Math.round(fyDataPeriod2.getTotalRevenue()));
		totalRevenue.setChapterName("Total Revenue");

		if (totalReveneueGoal != 0) {
			revenuePercentage = Math.round((atcual * 1.0) / totalReveneueGoal * 100);
		}
		totalRevenue.setPercentage(revenuePercentage);

		return totalRevenue;
	}

	/**
	 * TO get Get Expiration rate
	 * 
	 * @param chapter
	 * @param rDate
	 * @return
	 */
	private MatricsContent getExpirationRate(FyData fyData, String chapter, Date rDate, int fiscalYear) {

		MatricsContent expiration = new MatricsContent();

		/*
		 * Expiration rate for current fiscal year
		 */
		Date reportDate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));

		int paidMembeForLastFiscal = memberRepository.findCreditCardMemebrsCount(reportDate,
				Utility.getFiscalEndDates(fiscalYear).get(1), chapter);

		Date fiscalStartDate = new GregorianCalendar(fiscalYear - 1, Calendar.OCTOBER, 01).getTime();
		// Date fiscalEndDate = new GregorianCalendar(fiscalYear, Calendar.SEPTEMBER,
		// 30).getTime();
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
			if (paidMembeForLastFiscal != 0) {
				expirationRate = (expiredMembers * 1.0 / paidMembeForLastFiscal) * 100;
			}
		}
		double expirationGoal = fyData.getExpirationRateGoal();

		double acheived = 0.0;
		if (expirationGoal != 0) {
			acheived = (expirationRate * 1.0 / expirationGoal) * 100;
		}

		expiration.setGoal(fyData.getExpirationRateGoal());
		expiration.setActual(expirationRate);
		expiration.setPercentage(acheived);
		expiration.setChapterName("Expiration Rate");

		/**
		 * Expiration rate for previous fiscal year details
		 */

		double period2ExpirationRate = 0;
		Date startD = new GregorianCalendar(fiscalYear - 2, Calendar.OCTOBER, 01).getTime();
		Date endD = new GregorianCalendar(fiscalYear - 1, Calendar.SEPTEMBER, 30).getTime();

		double period2expiredMembers = memberRepository.getExpiringMembersCountForFiscal(startD, endD, chapter);

		if ((fiscalYear - 1) <= 2018) {
			int paidmembers = 0;
			FyData fyData1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter, (fiscalYear - 2));

			if (fyData1 != null) {
				paidmembers = fyData1.getPaidMembership();
			}
			if (paidmembers != 0) {
				period2ExpirationRate = (period2expiredMembers * 1.0 / paidmembers) * 100;
			}
		} else {
			Date period2EndDate = new GregorianCalendar((fiscalYear - 2), Calendar.SEPTEMBER, 30).getTime();
			Date date = logRepository.getMaxReportingDate(period2EndDate);
			int period2PaidMembers = memberRepository.findCreditCardMemebrsCount(date, date, chapter);
			if (period2PaidMembers != 0) {
				period2ExpirationRate = (period2expiredMembers * 1.0 / period2PaidMembers) * 100;
			}
		}

		expiration.setPeriod2(period2ExpirationRate);

		/**
		 * Expiration rate for previous to previous fiscal year details
		 */
		double period1ExpirationRate = 0;
		Date period1startD = new GregorianCalendar((fiscalYear - 3), Calendar.OCTOBER, 01).getTime();
		Date period1endD = new GregorianCalendar((fiscalYear - 2), Calendar.SEPTEMBER, 30).getTime();

		double period1expiredMembers = memberRepository.getExpiringMembersCountForFiscal(period1startD, period1endD,
				chapter);

		if ((fiscalYear - 2) <= 2018) {
			int paidmembers = 0;
			FyData fyData2 = fyRepository.findByFiscalYearAndPrimaryCode(chapter, (fiscalYear - 3));
			if (fyData2 != null) {
				paidmembers = fyData2.getPaidMembership();
			}
			if (paidmembers != 0) {
				period1ExpirationRate = (period1expiredMembers * 1.0 / paidmembers) * 100;
			}
		} else {
			Date period1EndDate = new GregorianCalendar((fiscalYear - 3), Calendar.SEPTEMBER, 30).getTime();
			Date date = logRepository.getMaxReportingDate(period1EndDate);
			int period1PaidMembers = memberRepository.findCreditCardMemebrsCount(date, period1EndDate, chapter);

			if (period1expiredMembers != 0) {
				period1ExpirationRate = (period1expiredMembers * 1.0 / period1PaidMembers) * 100;
			}
		}
		expiration.setPeriod1(period1ExpirationRate);
		return expiration;
	}

	/**
	 * To set the the Detailed Matrics headers dynamically
	 * 
	 * @param fiscalYear
	 * @return
	 */

	private Header getHeaders(int fiscalYear) {

		int period1 = fiscalYear - 2;
		int period2 = fiscalYear - 1;
		String actual = " Actual";

		Header header = new Header();
		header.setChapterName("");
		header.setActual("FY " + fiscalYear + actual);
		header.setGoal("FY " + fiscalYear + " Goal");
		header.setPeriod1("FY " + period1 + actual);
		header.setPeriod2("FY " + period2 + actual);
		header.setPercentage("Achieved (%) ");

		return header;
	}

}
