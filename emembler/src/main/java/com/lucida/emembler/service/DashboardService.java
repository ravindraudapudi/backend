package com.lucida.emembler.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lucida.emembler.dao.FyRepository;
import com.lucida.emembler.dao.LogRepository;
import com.lucida.emembler.dao.MemberRepository;
import com.lucida.emembler.entity.Chapter;
import com.lucida.emembler.entity.Member;
import com.lucida.emembler.responsedtos.ChapterContent;
import com.lucida.emembler.responsedtos.DashboardResponse;
import com.lucida.emembler.responsedtos.Header;
import com.lucida.emembler.utility.Utility;

/**
 * Dashboard Service class
 * 
 * @author Ravindra
 *
 */
@Service
@Transactional
public class DashboardService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private FyRepository fyRepository;

	@Autowired
	private ConfigService configService;

	@Autowired
	private LogRepository logRepository;

	private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);

	/**
	 * Get Local Sponsored members count
	 * 
	 * @param fiscalYear
	 * @return
	 */
	@Cacheable("totalActive")
	public DashboardResponse getTotalActiveMembership(int fiscalYear, Date reportingDate,String username) {

		DashboardResponse dashboardResponse = null;

		try {

			dashboardResponse = new DashboardResponse();

			/*
			 * Setting Headers for the tabular format
			 */
			dashboardResponse.setHeaders(getHeaders(fiscalYear));

			/*
			 * Setting tabular content
			 */
			List<ChapterContent> chapterList = new ArrayList<ChapterContent>();

			/*
			 * Getting all the chapters from the configuration settings
			 */
			//List<Chapter> chapters = configService.getChapter();
			
			Set<Chapter> chapters  = configService.getChaptersForUser(username);

			double totalActual = 0, totalGoal = 0, totalPeriod1 = 0, totalPeriod2 = 0, totalpercentage = 0;

			ChapterContent total = new ChapterContent();

			for (Chapter chapter : chapters) {

				/*
				 * Setting chapter tabular data content
				 */

				ChapterContent chapterContent = new ChapterContent();

				/**
				 * Setting chapter Name
				 */
				chapterContent.setChapterName(chapter.getChapterName());
				chapterContent.setPrimaryGroupCode(chapter.getPrimaryGroupCode());
				int period1 = 0;
				int period2 = 0;
				int goal = 0;
				int actual = 0;

				goal = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear)
						.getTotalActiveMembershipGoal();

				if (fiscalYear == 2018) {

					period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
							.getTotalActiveMembership();
					period2 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 1)
							.getTotalActiveMembership();

					actual = memberRepository.getActiveMembersCount(
							getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(0)),
							getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(0)),
							chapter.getPrimaryGroupCode());

				} else if (fiscalYear == 2019) {
					
					period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
							.getTotalActiveMembership();

					Date date = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));

					period2 = memberRepository.getActiveMembersCount(date, Utility.getFiscalEndDates(fiscalYear).get(1),
							chapter.getPrimaryGroupCode());
					actual = memberRepository.getActiveMembersCount(reportingDate, reportingDate,
							chapter.getPrimaryGroupCode());

				} else {

					Date period1Rdate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(2));
					
					period1 = memberRepository.getActiveMembersCount(period1Rdate,
							period1Rdate, chapter.getPrimaryGroupCode());
					
					Date period2RDate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));
					
					period2 = memberRepository.getActiveMembersCount(period2RDate,
							period2RDate, chapter.getPrimaryGroupCode());
					
					actual = memberRepository.getActiveMembersCount(reportingDate,
							reportingDate, chapter.getPrimaryGroupCode());

				}

				chapterContent.setActual(actual);
				chapterContent.setPeriod1(period1);
				chapterContent.setPeriod2(period2);
				chapterContent.setGoal(goal);

				/**
				 * Achievable calculation
				 */

				double percentage = 0;

				if (goal != 0) {
					percentage = Math.round((actual * 1.0) / goal * 100);
				}

				chapterContent.setPercentage(percentage);
				chapterList.add(chapterContent);

				/**
				 * , Adding all the details
				 */
				totalActual += actual;
				totalGoal += goal;

				totalPeriod1 += period1;
				totalPeriod2 += period2;

			}

			total.setActual(totalActual);
			total.setChapterName("Total");
			total.setGoal(totalGoal);
			total.setPeriod1(totalPeriod1);
			total.setPeriod2(totalPeriod2);

			totalpercentage = 0;

			if (totalGoal != 0) {
				totalpercentage = Math.round((totalActual * 1.0) / totalGoal * 100);
			}
			total.setPercentage(totalpercentage);

			chapterList.add(total);

			dashboardResponse.setChapterContent(chapterList);

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return dashboardResponse;
	}

	/**
	 * Get Local Sponsored members count
	 * 
	 * @param fiscalYear
	 * @return
	 */
	@Cacheable("paid")
	public DashboardResponse getPaidMembers(int fiscalYear, Date reportingDate,String userName) {

		DashboardResponse dashboardResponse = null;

		try {

			dashboardResponse = new DashboardResponse();

			/*
			 * Setting Headers for the tabular format
			 */
			dashboardResponse.setHeaders(getHeaders(fiscalYear));

			/*
			 * Setting tabular content
			 */
			List<ChapterContent> chapterList = new ArrayList<ChapterContent>();

			/*
			 * Getting all the chapters from the configuration settings
			 */
			//List<Chapter> chapters = configService.getChapter();
			
			Set<Chapter>  chapters = configService.getChaptersForUser(userName);

			double totalActual = 0, totalGoal = 0, totalPeriod1 = 0, totalPeriod2 = 0, totalpercentage = 0;

			ChapterContent total = new ChapterContent();

			for (Chapter chapter : chapters) {

				/*
				 * Setting chapter tabular data content
				 */

				ChapterContent chapterContent = new ChapterContent();

				/**
				 * Setting chapter Name
				 */
				chapterContent.setChapterName(chapter.getChapterName());
				chapterContent.setPrimaryGroupCode(chapter.getPrimaryGroupCode());

				int period1 = 0;
				int period2 = 0;
				int actual = 0;
				int goal = 0;

				goal = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear)
						.getPaidMembershipGoal();

				if (fiscalYear == 2018) {

					period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
							.getPaidMembership();
					period2 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 1)
							.getPaidMembership();
					Date date = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(0));
					actual = memberRepository.findCreditCardMemebrsCount(date, date, chapter.getPrimaryGroupCode());

				} else if (fiscalYear == 2019) {

					period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
							.getPaidMembership();
					Date date = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));
					period2 = memberRepository.findCreditCardMemebrsCount(date,
							Utility.getFiscalEndDates(fiscalYear).get(1), chapter.getPrimaryGroupCode());
					actual = memberRepository.findCreditCardMemebrsCount(reportingDate, reportingDate,
							chapter.getPrimaryGroupCode());

				} else {
					
					Date period1Rdate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(2));
					period1 = memberRepository.findCreditCardMemebrsCount(period1Rdate,
							period1Rdate, chapter.getPrimaryGroupCode());
					
					Date period2Rdate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));
					period2 = memberRepository.findCreditCardMemebrsCount(period2Rdate,
							period2Rdate, chapter.getPrimaryGroupCode());
					actual = memberRepository.findCreditCardMemebrsCount(reportingDate,
							reportingDate, chapter.getPrimaryGroupCode());
				}

				chapterContent.setActual(actual);
				chapterContent.setPeriod1(period1);
				chapterContent.setPeriod2(period2);
				chapterContent.setGoal(goal);

				/**
				 * Achievable calculation
				 */

				double percentage = 0;

				if (goal != 0) {
					percentage = Math.round((actual * 1.0) / goal * 100);
				}

				chapterContent.setPercentage(percentage);
				chapterList.add(chapterContent);

				/**
				 * , Adding all the details
				 */
				totalActual += actual;
				totalGoal += goal;

				totalPeriod1 += period1;
				totalPeriod2 += period2;

			}

			total.setActual(totalActual);
			total.setChapterName("Total");
			total.setGoal(totalGoal);
			total.setPeriod1(totalPeriod1);
			total.setPeriod2(totalPeriod2);

			totalpercentage = 0;

			if (totalGoal != 0) {
				totalpercentage = Math.round((totalActual * 1.0) / totalGoal * 100);
			}
			total.setPercentage(totalpercentage);

			chapterList.add(total);

			dashboardResponse.setChapterContent(chapterList);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return dashboardResponse;

	}

	/**
	 * TO get Local Sponsored members calculations
	 * 
	 */
	@Cacheable("localSponsored")
	public DashboardResponse getLocalSponsered(int fiscalYear, Date reportingDate,String userName) {

		DashboardResponse dashboardResponse = null;

		try {

			dashboardResponse = new DashboardResponse();

			/*
			 * Setting Headers for the tabular format
			 */
			dashboardResponse.setHeaders(getHeaders(fiscalYear));

			/*
			 * Setting tabular content
			 */
			List<ChapterContent> chapterList = new ArrayList<ChapterContent>();

			/*
			 * Getting all the chapters from the configuration settings
			 */
			//List<Chapter> chapters = configService.getChapter();
			
			Set<Chapter>  chapters = configService.getChaptersForUser(userName);

			double totalActual = 0, totalGoal = 0, totalPeriod1 = 0, totalPeriod2 = 0, totalpercentage = 0;

			ChapterContent total = new ChapterContent();

			for (Chapter chapter : chapters) {

				/*
				 * Setting chapter tabular data content
				 */

				ChapterContent chapterContent = new ChapterContent();

				/**
				 * Setting chapter Name
				 */
				chapterContent.setChapterName(chapter.getChapterName());
				chapterContent.setPrimaryGroupCode(chapter.getPrimaryGroupCode());

				int period1 = 0;
				int period2 = 0;
				int actual = 0;
				int goal = 0;

				goal = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear)
						.getLocalSponseredMembershipGoal();

				if (fiscalYear == 2018) {

					period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
							.getLocalSponseredMembership();
					period2 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 1)
							.getLocalSponseredMembership();

					Date date = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(0));
					actual = memberRepository.getLocalSponsorMembershipByChapter(date, date,
							chapter.getPrimaryGroupCode());

				} else if (fiscalYear == 2019) {

					period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
							.getLocalSponseredMembership();
					Date date = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));
					period2 = memberRepository.getLocalSponsorMembershipByChapter(date,
							Utility.getFiscalEndDates(fiscalYear).get(1), chapter.getPrimaryGroupCode());
					actual = memberRepository.getLocalSponsorMembershipByChapter(reportingDate, reportingDate,
							chapter.getPrimaryGroupCode());

				} else {

					Date period1Rdate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(2));
					
					period1 = memberRepository.getLocalSponsorMembershipByChapter(period1Rdate,
							period1Rdate, chapter.getPrimaryGroupCode());
					
					Date period2Rdate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));
					period2 = memberRepository.getLocalSponsorMembershipByChapter(period2Rdate,
							period2Rdate, chapter.getPrimaryGroupCode());
					actual = memberRepository.getLocalSponsorMembershipByChapter(reportingDate,
							reportingDate, chapter.getPrimaryGroupCode());
				}

				chapterContent.setActual(actual);
				chapterContent.setPeriod1(period1);
				chapterContent.setPeriod2(period2);
				chapterContent.setGoal(goal);

				/**
				 * Achievable calculation
				 */

				double percentage = 0;

				if (goal != 0) {
					percentage = Math.round((actual * 1.0) / goal * 100);
				}

				chapterContent.setPercentage(percentage);
				chapterList.add(chapterContent);

				/**
				 * , Adding all the details
				 */
				totalActual += actual;
				totalGoal += goal;

				totalPeriod1 += period1;
				totalPeriod2 += period2;

			}

			total.setActual(totalActual);
			total.setChapterName("Total");
			total.setGoal(totalGoal);
			total.setPeriod1(totalPeriod1);
			total.setPeriod2(totalPeriod2);

			totalpercentage = 0;

			if (totalGoal != 0) {
				totalpercentage = Math.round((totalActual * 1.0) / totalGoal * 100);
			}
			total.setPercentage(totalpercentage);

			chapterList.add(total);

			dashboardResponse.setChapterContent(chapterList);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return dashboardResponse;
	}

	/**
	 * To get National Sponsored members calculations
	 * 
	 * @param fiscalYear
	 * @return
	 */
	@Cacheable("NationalSponsored")
	public DashboardResponse getNationalSponsered(int fiscalYear, Date reportingDate, String userName) {

		DashboardResponse dashboardResponse = null;

		try {

			dashboardResponse = new DashboardResponse();

			/*
			 * Setting Headers for the tabular format
			 */
			dashboardResponse.setHeaders(getHeaders(fiscalYear));

			/*
			 * Setting tabular content
			 */
			List<ChapterContent> chapterList = new ArrayList<ChapterContent>();

			/*
			 * Getting all the chapters from the configuration settings
			 */
			// List<Chapter> chapters = configService.getChapter();
			
			Set<Chapter>  chapters = configService.getChaptersForUser(userName);

			double totalActual = 0, totalGoal = 0, totalPeriod1 = 0, totalPeriod2 = 0, totalpercentage = 0;

			ChapterContent total = new ChapterContent();

			for (Chapter chapter : chapters) {

				/*
				 * Setting chapter tabular data content
				 */

				ChapterContent chapterContent = new ChapterContent();

				/**
				 * Setting chapter Name
				 */
				chapterContent.setChapterName(chapter.getChapterName());
				chapterContent.setPrimaryGroupCode(chapter.getPrimaryGroupCode());

				int period1 = 0;
				int period2 = 0;
				int actual = 0;
				int goal = 0;

				goal = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear)
						.getNationalSponsoredMembershipGoal();

				if (fiscalYear == 2018) {

					period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
							.getNationalSponseredMembership();
					period2 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 1)
							.getNationalSponseredMembership();
					Date date = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(0));
					actual = memberRepository.getNationalSponsorMembershipByChapter(date, date,
							chapter.getPrimaryGroupCode());

				} else if (fiscalYear == 2019) {

					period1 = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
							.getLocalSponseredMembership();
					Date date = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(1));
					period2 = memberRepository.getNationalSponsorMembershipByChapter(date,
							Utility.getFiscalEndDates(fiscalYear).get(1), chapter.getPrimaryGroupCode());
					actual = memberRepository.getNationalSponsorMembershipByChapter(reportingDate, reportingDate,
							chapter.getPrimaryGroupCode());

				} else {

					Date period1Rdate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(2));
					
					period1 = memberRepository.getNationalSponsorMembershipByChapter(period1Rdate,
							period1Rdate, chapter.getPrimaryGroupCode());
					
					Date period2Rdate = logRepository.getMaxReportingDate(Utility.getFiscalEndDates(fiscalYear).get(2));
					period2 = memberRepository.getNationalSponsorMembershipByChapter(period2Rdate,
							period2Rdate, chapter.getPrimaryGroupCode());
					actual = memberRepository.getNationalSponsorMembershipByChapter(reportingDate,
							reportingDate, chapter.getPrimaryGroupCode());
				}

				chapterContent.setActual(actual);
				chapterContent.setPeriod1(period1);
				chapterContent.setPeriod2(period2);
				chapterContent.setGoal(goal);

				/**
				 * Achievable calculation
				 */

				double percentage = 0;

				if (goal != 0) {
					percentage = Math.round((actual * 1.0) / goal * 100);
				}

				chapterContent.setPercentage(percentage);
				chapterList.add(chapterContent);

				/**
				 * , Adding all the details
				 */
				totalActual += actual;
				totalGoal += goal;

				totalPeriod1 += period1;
				totalPeriod2 += period2;

			}

			total.setActual(totalActual);
			total.setChapterName("Total");
			total.setGoal(totalGoal);
			total.setPeriod1(totalPeriod1);
			total.setPeriod2(totalPeriod2);

			totalpercentage = 0;

			if (totalGoal != 0) {
				totalpercentage = Math.round((totalActual * 1.0) / totalGoal * 100);
			}
			total.setPercentage(totalpercentage);

			chapterList.add(total);

			dashboardResponse.setChapterContent(chapterList);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return dashboardResponse;
	}

	/**
	 * Get Total revenue for the all chapters
	 * 
	 * @param fiscalYear
	 * @return
	 */
	@Cacheable("totalRevenue")
	public DashboardResponse getTotalRevenue(int fiscalYear, String userName) {

		DashboardResponse dashboardResponse = new DashboardResponse();
		try {

			// dashboardResponse = new DashboardResponse();
			/*
			 * Setting Headers for the tabular format
			 */
			int period1 = fiscalYear - 2;
			int period2 = fiscalYear - 1;

			Header header = new Header();
			header.setChapterName("Chapter");
			header.setActual("FY " + fiscalYear + " Actual ");
			header.setGoal("FY " + fiscalYear + " Goal ");
			header.setPeriod1("FY " + period1 + " Actual ");
			header.setPeriod2("FY " + period2 + " Actual ");
			header.setPercentage("Achieved in (%) ");

			dashboardResponse.setHeaders(header);

			/*
			 * Setting tabular content
			 */
			List<ChapterContent> chapterList = new ArrayList<ChapterContent>();

			/*
			 * Getting all the chapters from the configuration settings
			 */
			//List<Chapter> chapters = configService.getChapter();
			
			Set<Chapter>  chapters = configService.getChaptersForUser(userName);

			double totalActual = 0, totalGoal = 0, totalPeriod1 = 0, totalPeriod2 = 0, totalpercentage = 0;

			ChapterContent total = new ChapterContent();

			for (Chapter chapter : chapters) {

				/*
				 * Setting chapter tabular data content
				 */

				ChapterContent chapterContent = new ChapterContent();

				chapterContent.setChapterName(chapter.getChapterName());
				chapterContent.setPrimaryGroupCode(chapter.getPrimaryGroupCode());

				double revenuePeriod1 = 0;
				double revenuePeriod2 = 0;
				double actual = 0;
				double goal = 0;

				goal = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear)
						.getTotalRevenueGoal();

				revenuePeriod1 = fyRepository
						.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 2)
						.getTotalRevenue();

				revenuePeriod2 = fyRepository
						.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear - 1)
						.getTotalRevenue();

				actual = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), fiscalYear)
						.getTotalRevenue();

				chapterContent.setActual(Math.round(actual));
				chapterContent.setGoal(Math.round(goal));
				chapterContent.setPeriod1(Math.round(revenuePeriod1));
				chapterContent.setPeriod2(Math.round(revenuePeriod2));

				double percentage = 0;
				if (goal != 0) {
					percentage = Math.round(((actual * 1.0) / goal) * 100);
				}

				chapterContent.setPercentage(percentage);
				chapterList.add(chapterContent);

				/**
				 * , Adding all the details
				 */

				totalActual += actual;
				totalGoal += goal;
				totalPeriod1 += revenuePeriod1;
				totalPeriod2 += revenuePeriod2;
			}

			total.setActual(Math.round(totalActual));
			total.setChapterName("Total");
			total.setGoal(totalGoal);
			total.setPeriod1(Math.round(totalPeriod1));
			total.setPeriod2(Math.round(totalPeriod2));

			if (totalGoal != 0) {
				totalpercentage = Math.round((totalActual * 1.0) / totalGoal * 100);
			}
			total.setPercentage(totalpercentage);

			chapterList.add(total);

			dashboardResponse.setChapterContent(chapterList);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return dashboardResponse;
	}

	@Cacheable("getTotalActiveMembersDetails")
	public List<Member> getTotalActiveMembersDetails(String chapter, Date reportingDate) {
		List<Member> members = memberRepository.getTotalActiveMembersDetails(reportingDate, reportingDate, chapter);
		return members;
	}

	/**
	 * To set the the National dashboard headers dynamically
	 * 
	 * @param fiscalYear
	 * @return
	 */

	private Header getHeaders(int fiscalYear) {

		int period1 = fiscalYear - 2;
		int period2 = fiscalYear - 1;

		Header header = new Header();
		header.setChapterName("Chapter");
		header.setActual("FY " + fiscalYear + " Actual");
		header.setGoal("FY " + fiscalYear + " Goal");
		header.setPeriod1("FY " + period1 + " Actual");
		header.setPeriod2("FY " + period2 + " Actual");
		header.setPercentage("Achieved in (%) ");

		return header;
	}

	private Date getMaxReportingDate(Date date) {
		return logRepository.getMaxReportingDate(date);
	}

}
