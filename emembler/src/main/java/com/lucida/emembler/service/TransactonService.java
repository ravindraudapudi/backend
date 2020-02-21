package com.lucida.emembler.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lucida.emembler.dao.FyRepository;
import com.lucida.emembler.dao.LogRepository;
import com.lucida.emembler.dao.MemberRepository;
import com.lucida.emembler.dao.MemberVersionRepository;
import com.lucida.emembler.dao.SocialMediaRepository;
import com.lucida.emembler.dao.SponserRepository;
import com.lucida.emembler.dao.TransactionRepository;
import com.lucida.emembler.entity.Chapter;
import com.lucida.emembler.entity.CsvLog;
import com.lucida.emembler.entity.FyData;
import com.lucida.emembler.entity.Member;
import com.lucida.emembler.entity.MembersVersionData;
import com.lucida.emembler.entity.SocialMedia;
import com.lucida.emembler.entity.Sponsor;
import com.lucida.emembler.entity.Transaction;
import com.lucida.emembler.exceptions.InvalidDataException;
import com.lucida.emembler.requestdtos.FiscalYearsList;
import com.lucida.emembler.requestdtos.ProfitAndLossRequest;
import com.lucida.emembler.requestdtos.ProfitAndLosssDto;
import com.lucida.emembler.requestdtos.ReportingDatesForFiscal;
import com.lucida.emembler.requestdtos.SocialMediaDto;
import com.lucida.emembler.requestdtos.SocialMediaRequest;
import com.lucida.emembler.requestdtos.SponserDto;
import com.lucida.emembler.requestdtos.SponsershipRequest;
import com.lucida.emembler.requestdtos.TransactionDto;
import com.lucida.emembler.requestdtos.TransactionRequest;
import com.lucida.emembler.responsedtos.SponsershipReport;
import com.lucida.emembler.utility.Utility;

/**
 * @author Lucida
 * 
 *         Service class to update Transaction for members
 */
@Service
@Transactional
public class TransactonService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private MemberVersionRepository memberVersionRepository;

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private SponserRepository sponserRepository;

	@Autowired
	private ConfigService configService;

	@Autowired
	private SocialMediaRepository socialMediaRepository;

	@Autowired
	private FyRepository fyRepository;

	/**
	 * Update Sponsosrhip master data into the database.
	 * 
	 * @param Sponsership
	 */
	public void saveSponsers(SponsershipRequest request) {

		for (SponserDto sponserDto : request.getContent()) {

			try {

				if (sponserDto.getType() != null && !sponserRepository.existsIfSponsorId(sponserDto.getSponsorId(),
						sponserDto.getType().toUpperCase())) {

					Sponsor s = new Sponsor();
					s.setSponsor(sponserDto.getSponsor());
					s.setSponsorId(sponserDto.getSponsorId());

					if (!StringUtils.isEmpty(sponserDto.getType())) {
						s.setType(sponserDto.getType().toUpperCase());
					}

					if (!StringUtils.isEmpty(sponserDto.getPrimaryGroupCode())) {
						s.setPrimaryGroupCode(sponserDto.getPrimaryGroupCode().toUpperCase());
					}

					if (!StringUtils.isEmpty(sponserDto.getPromotionalCode())
							&& sponserDto.getPromotionalCode().contains("(")) {
						s.setDiscountCode(sponserDto.getPromotionalCode()
								.substring(0, sponserDto.getPromotionalCode().indexOf("(") - 1).trim().toUpperCase());
					} else if (!StringUtils.isEmpty(sponserDto.getPromotionalCode())) {
						s.setDiscountCode(sponserDto.getPromotionalCode().trim().toUpperCase());
					} else {
						s.setDiscountCode("NA");
					}

					if (sponserDto.getSponserLevel() != null) {
						s.setSponserLevel(sponserDto.getSponserLevel().toUpperCase());
					}

					s.setInvoiceDate(Utility.getStringToDate(sponserDto.getInvoiceDate()));
					s.setInvoiceNumber(sponserDto.getInvoiceNumber());

					if (!StringUtils.isEmpty(sponserDto.getChapterAmount()) && sponserDto.getChapterAmount() != null) {
						String value = sponserDto.getChapterAmount().split("\\.", 2)[0].replaceAll("[^0-9|-]", "");
						if (!StringUtils.isEmpty(value)) {
							s.setAmount(Double.parseDouble(value));
						} else {
							s.setAmount(0);
						}
					}

					s.setExpirationDate(Utility.getStringToDate(sponserDto.getExpirationDate()));
					s.setPartenershipYear(sponserDto.getPartenershipYear());
					s.setAggrementEndDate(Utility.getStringToDate(sponserDto.getPartnershipEndDate()));
					s.setPaymentDate(Utility.getStringToDate(sponserDto.getPaymentDate()));

					int issued = 0;
					if (!StringUtils.isEmpty(sponserDto.getChapterAssigned())) {
						String assigned = sponserDto.getChapterAssigned().replaceAll("[^0-9]", "");
						boolean isNumber = isNumeric(assigned);
						if (isNumber) {
							issued += Integer.parseInt(assigned);
						}

					}

					if (!StringUtils.isEmpty(sponserDto.getNationalAssigned())) {
						String assigned = sponserDto.getNationalAssigned().replaceAll("[^0-9]", "");
						boolean isNumber = isNumeric(assigned);
						if (isNumber) {
							issued += Integer.parseInt(assigned);
						}
					}

					s.setAssigned(issued);
					s.setCrm(sponserDto.getCrm());
					s.setSponserContact(sponserDto.getSponserContact());
					s.setRelationWithNational(sponserDto.getRelationWithNational());
					s.setInvoiceTerms(sponserDto.getInvoiceTerms());

					if (sponserDto.getNationalBenefits() != null) {
						s.setNationalBenefits(sponserDto.getNationalBenefits().toUpperCase().trim());
					}

					sponserRepository.save(s);

				} else {

					if (sponserDto.getType() != null) {
						Sponsor sponsor = sponserRepository.findBySponsorIdAndType(sponserDto.getSponsorId(),
								sponserDto.getType());

						Sponsor s = new Sponsor();
						s.setId(sponsor.getId());
						s.setSponsorId(sponsor.getSponsorId());
						s.setSponsor(sponserDto.getSponsor());
						s.setSponsorId(sponserDto.getSponsorId());

						if (!StringUtils.isEmpty(sponserDto.getType())) {
							s.setType(sponserDto.getType().toUpperCase());
						}

						if (!StringUtils.isEmpty(sponserDto.getPrimaryGroupCode())) {
							s.setPrimaryGroupCode(sponserDto.getPrimaryGroupCode().toUpperCase());
						}

						if (!StringUtils.isEmpty(sponserDto.getPromotionalCode())
								&& sponserDto.getPromotionalCode().contains("(")) {
							s.setDiscountCode(sponserDto.getPromotionalCode()
									.substring(0, sponserDto.getPromotionalCode().indexOf("(") - 1).trim()
									.toUpperCase());
						} else if (!StringUtils.isEmpty(sponserDto.getPromotionalCode())) {
							s.setDiscountCode(sponserDto.getPromotionalCode().trim().toUpperCase());
						} else {
							s.setDiscountCode("NA");
						}

						if (sponserDto.getSponserLevel() != null) {
							s.setSponserLevel(sponserDto.getSponserLevel().toUpperCase());
						}

						s.setInvoiceDate(Utility.getStringToDate(sponserDto.getInvoiceDate()));
						s.setInvoiceNumber(sponserDto.getInvoiceNumber());
						s.setPaymentDate(Utility.getStringToDate(sponserDto.getPaymentDate()));

						if (!StringUtils.isEmpty(sponserDto.getChapterAmount())
								&& sponserDto.getChapterAmount() != null) {
							String value = sponserDto.getChapterAmount().split("\\.", 2)[0].replaceAll("[^0-9|-]", "");
							if (!StringUtils.isEmpty(value)) {
								s.setAmount(Double.parseDouble(value));
							} else {
								s.setAmount(0);
							}
						}

						s.setExpirationDate(Utility.getStringToDate(sponserDto.getExpirationDate()));
						s.setPartenershipYear(sponserDto.getPartenershipYear());
						s.setAggrementEndDate(Utility.getStringToDate(sponserDto.getPartnershipEndDate()));

						int issued = 0;
						if (!StringUtils.isEmpty(sponserDto.getChapterAssigned())) {
							String assigned = sponserDto.getChapterAssigned().replaceAll("[^0-9]", "");
							boolean isNumber = isNumeric(assigned);
							if (isNumber) {
								issued += Integer.parseInt(assigned);
							}

						}

						if (!StringUtils.isEmpty(sponserDto.getNationalAssigned())) {
							String assigned = sponserDto.getNationalAssigned().replaceAll("[^0-9]", "");
							boolean isNumber = isNumeric(assigned);
							if (isNumber) {
								issued += Integer.parseInt(assigned);
							}
						}

						s.setAssigned(issued);
						s.setCrm(sponserDto.getCrm());
						s.setSponserContact(sponserDto.getSponserContact());
						s.setRelationWithNational(sponserDto.getRelationWithNational());
						s.setInvoiceTerms(sponserDto.getInvoiceTerms());

						if (sponserDto.getNationalBenefits() != null) {
							s.setNationalBenefits(sponserDto.getNationalBenefits().toUpperCase().trim());
						}

						sponserRepository.save(s);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * Update the log after sponsorship data upload.
		 */
		updateSponsershipLog(request);

	}

	public boolean isNumeric(String s) {
		return java.util.regex.Pattern.matches("\\d+", s);
	}

	/**
	 * Transaction Processing
	 * 
	 * @param Transaction
	 */
	public void saveTransaction(TransactionRequest request) throws InvalidDataException {

		try {

			for (TransactionDto transaction : request.getContent()) {

				Transaction tr = transactionRepository.findByTransactionId(transaction.getTransaction_id());
				String promotionalCode = "";
				if (!transactionRepository.ifTransactionExist(transaction.getTransaction_id())) {

					Transaction t = new Transaction();
					t.setTransactionId(transaction.getTransaction_id());
					t.setAmount(transaction.getAmount());
					t.setExpiryDate(Utility.getStringToDate(transaction.getExpiryDate()));
					t.setDateProcessed(Utility.getStringToDate(transaction.getDateProcessed()));
					if (transaction.getPaymentType() != null) {
						t.setPaymentType(transaction.getPaymentType().toUpperCase());
					}

					if (transaction.getMemberPrimaryGroup() != null) {
						t.setPrimaryGroupCode(transaction.getMemberPrimaryGroup().toUpperCase());
					}

					t.setMemberApiGuid(transaction.getMemberApiGuid());
					t.setMemberType(transaction.getMemberType());

					if (transaction.getCurrentMembership() != null) {
						t.setCurrentMembership(transaction.getCurrentMembership().toUpperCase());
					}

					if (!StringUtils.isEmpty(transaction.getPromotionalCode())
							&& transaction.getPromotionalCode().contains("(")) {
						promotionalCode = transaction.getPromotionalCode()
								.substring(0, transaction.getPromotionalCode().indexOf("(") - 1).trim().toUpperCase();
						t.setSponsershipCode(promotionalCode);
					} else if (!StringUtils.isEmpty(transaction.getPromotionalCode())) {
						promotionalCode = transaction.getPromotionalCode().trim().toUpperCase();
						t.setSponsershipCode(promotionalCode);
					} else {
						promotionalCode = "Not Applicable";
						t.setSponsershipCode(promotionalCode);
					}

					transactionRepository.save(t);
					transactionRepository.flush();

				} else {

					Transaction t = new Transaction();
					t.setId(tr.getId());
					t.setTransactionId(transaction.getTransaction_id());
					t.setAmount(transaction.getAmount());
					t.setExpiryDate(Utility.getStringToDate(transaction.getExpiryDate()));
					t.setDateProcessed(Utility.getStringToDate(transaction.getDateProcessed()));
					if (transaction.getPaymentType() != null) {
						t.setPaymentType(transaction.getPaymentType().toUpperCase());
					}

					if (transaction.getMemberPrimaryGroup() != null) {
						t.setPrimaryGroupCode(transaction.getMemberPrimaryGroup().toUpperCase());
					}

					t.setMemberApiGuid(transaction.getMemberApiGuid());
					t.setMemberType(transaction.getMemberType());

					if (transaction.getCurrentMembership() != null) {
						t.setCurrentMembership(transaction.getCurrentMembership().toUpperCase());
					}

					if (!StringUtils.isEmpty(transaction.getPromotionalCode())
							&& transaction.getPromotionalCode().contains("(")) {
						promotionalCode = transaction.getPromotionalCode()
								.substring(0, transaction.getPromotionalCode().indexOf("(") - 1).trim().toUpperCase();
						t.setSponsershipCode(promotionalCode);
					} else if (!StringUtils.isEmpty(transaction.getPromotionalCode())) {
						promotionalCode = transaction.getPromotionalCode().trim().toUpperCase();
						t.setSponsershipCode(promotionalCode);
					} else {
						promotionalCode = "Not Applicable";
						t.setSponsershipCode(promotionalCode);
					}

					transactionRepository.save(t);
					transactionRepository.flush();

				}

				/**
				 * 
				 */

				Date rDate = Utility.getStringToDate(request.getReportedDate());

				/*
				 * Getting the guid for the reporting date
				 */
				MembersVersionData mvd = memberVersionRepository
						.findByGuidAndTransactionId(transaction.getMemberApiGuid(), transaction.getTransaction_id());

				MembersVersionData mvdByRd = memberVersionRepository
						.findByGuidAndReportingDate(transaction.getMemberApiGuid(), rDate);

				boolean isMemberExist = memberVersionRepository
						.isExistByGuidAndReportingDate(transaction.getMemberApiGuid(), rDate);

				if (isMemberExist && mvd == null && (mvdByRd.getTransactionId() == 0
						|| transaction.getTransaction_id() > mvdByRd.getTransactionId())) {

					memberVersionRepository.updateTransactionVersionData(
							Utility.getStringToDate(transaction.getDateProcessed()), transaction.getTransaction_id(),
							promotionalCode, transaction.getPaymentType().toUpperCase(), transaction.getAmount(),
							transaction.getMemberApiGuid(), rDate);
					memberVersionRepository.flush();
				} else if (isMemberExist && mvd != null && mvd.getTransactionId() == transaction.getTransaction_id()) {
					memberVersionRepository.updatePastTransaction(
							Utility.getStringToDate(transaction.getDateProcessed()), promotionalCode,
							transaction.getPaymentType().toUpperCase(), transaction.getAmount(),
							transaction.getTransaction_id());
					memberVersionRepository.flush();
				} else if (isMemberExist && mvd != null && transaction.getTransaction_id() > mvd.getTransactionId()) {
					memberVersionRepository.updateTransactionVersionData(
							Utility.getStringToDate(transaction.getDateProcessed()), transaction.getTransaction_id(),
							promotionalCode, transaction.getPaymentType().toUpperCase(), transaction.getAmount(),
							transaction.getMemberApiGuid(), rDate);
					memberVersionRepository.flush();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * To update the CSV log data
		 * 
		 * @param request
		 */

		updateCsvLog(request);
	}

	/**
	 * Social Media data Processing
	 * 
	 * @param Social
	 *            Media data
	 * 
	 */
	public void saveSocialMedia(SocialMediaRequest request) {

		try {

			for (SocialMediaDto socialMediaDto : request.getContent()) {

				SocialMedia socialMedia = new SocialMedia();
				Date monthlyDate = Utility.getStringToDate(socialMediaDto.getMonthlyDate());
				final Calendar c = Calendar.getInstance();

				if (monthlyDate != null) {
					c.setTime(monthlyDate);
				}

				List<SocialMedia> sm = socialMediaRepository.findByMonthlyDtAndCode(c.get(Calendar.MONTH) + 1,
						(c.get(Calendar.YEAR)), socialMediaDto.getPrimaryGroupCode());

				if (sm.size() == 0) {
					BeanUtils.copyProperties(socialMediaDto, socialMedia);
					socialMedia.setMonthlyDate(Utility.getStringToDate(socialMediaDto.getMonthlyDate()));

					socialMediaRepository.save(socialMedia);
					socialMediaRepository.flush();

				} else {

					for (SocialMedia updated : sm) {
						BeanUtils.copyProperties(socialMediaDto, socialMedia);

						socialMedia.setMonthlyDate(Utility.getStringToDate(socialMediaDto.getMonthlyDate()));
						socialMedia.setId(updated.getId());

						socialMediaRepository.save(socialMedia);
						socialMediaRepository.flush();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Update in the log repository
		 */
		updateCsvLog(request);
	}

	/**
	 * Update profit and loss sheets
	 * 
	 * @param request
	 */
	public void updateProfitAndLoss(ProfitAndLossRequest req) {

		ProfitAndLosssDto request = req.getContent();

		FyData fyData = fyRepository.findByFiscalYearAndPrimaryCode(request.getChapterName(), request.getFiscalYear());

		double lifeTimeMembership = 0;
		if (!StringUtils.isEmpty(request.getLifeTimeMembership())) {
			lifeTimeMembership = Double.valueOf(request.getLifeTimeMembership());
		}

		double kindDonations = 0;
		if (!StringUtils.isEmpty(request.getKindDonations())) {
			kindDonations = Double.valueOf(request.getKindDonations());
		}

		double totalRevenue = 0;
		if (!StringUtils.isEmpty(request.getTotalRevenue())) {
			totalRevenue = Double.valueOf(request.getTotalRevenue());
		}

		double contributionRevenue = 0;
		if (!StringUtils.isEmpty(request.getContributionRevenue())) {
			contributionRevenue = Double.valueOf(request.getContributionRevenue()) + kindDonations;
		}

		double programRevenue = 0;
		if (!StringUtils.isEmpty(request.getProgramRevenue())) {
			programRevenue = Double.valueOf(request.getProgramRevenue());
		}

		double membershipIncome = 0;
		if (!StringUtils.isEmpty(request.getMembershipIncome())) {
			membershipIncome = Double.valueOf(request.getMembershipIncome()) + lifeTimeMembership;
		}

		double registrationEvent = 0;
		if (!StringUtils.isEmpty(request.getRegistrationEvent())) {
			registrationEvent = Double.valueOf(request.getRegistrationEvent());
		}

		String revenueTerm = "";

		if (request != null && request.getRevenueTerm() != null) {
			revenueTerm = request.getRevenueTerm();
		}

		fyRepository.updateFiscalYearProfitLoss(totalRevenue, programRevenue, membershipIncome, contributionRevenue,
				registrationEvent, revenueTerm, fyData.getId());

		updatePLCsvLog(req);

	}

	/**
	 * Update Transaction log request
	 * 
	 * @param request
	 */
	private void updatePLCsvLog(ProfitAndLossRequest request) {
		CsvLog csvLog = new CsvLog();
		csvLog.setDocName(request.getFileName());
		csvLog.setStatus("SUCCESS");
		csvLog.setUploadedBy(request.getUploadedBy());
		csvLog.setUploadedOn(new Date());
		csvLog.setDocType(request.getType());
		logRepository.save(csvLog);
		logRepository.flush();

	}

	/**
	 * TO get primary group code from
	 * 
	 * @param chapterName
	 * @return
	 */
	private String getPrimaryGroupCode(String chapterName) {

		if (chapterName.contains("Atlanta"))
			return "atl";
		else if (chapterName.contains("Houston")) {
			return "hou";
		} else if (chapterName.contains("Charlotte")) {
			return "char";
		} else if (chapterName.contains("Texas")) {
			return "ntx";
		} else if (chapterName.contains("Seattle")) {
			return "sea";
		} else if (chapterName.contains("Angeles")) {
			return "la";
		} else if (chapterName.contains("England")) {
			return "ne";
		} else if (chapterName.contains("Philadelphia")) {
			return "phl";
		} else if (chapterName.contains("Bentley")) {
			return "Bentley";
		} else if (chapterName.contains("Brandeis")) {
			return "Brandeis";
		} else if (chapterName.contains("Drexel")) {
			return "Drexel";
		} else if (chapterName.contains("Emory")) {
			return "Emory";
		} else if (chapterName.contains("Georgia")) {
			return "gsu";
		} else if (chapterName.contains("Seattle University")) {
			return "SeattleU";
		} else if (chapterName.contains("Southern")) {
			return "usc";
		} else if (chapterName.contains("Temple")) {
			return "temple";
		} else if (chapterName.contains("University of Texas")) {
			return "utd";
		} else if (chapterName.contains("Washington")) {
			return "uWash";
		} else if (chapterName.contains("Penn")) {
			return "Penstate";
		} else if (chapterName.contains("Southern Methodist")) {
			return "smu";
		} else if (chapterName.contains("Masachusetts")) {
			return "Mass";
		} else {
			return "NA";
		}
	}

	/**
	 * 
	 * @return Get Sponser for the chapters
	 */
	@Cacheable("getSponsers")
	public List<SponsershipReport> getSponsers(String primarygroupcode) {

		List<Sponsor> sponsers = sponserRepository.getSponsersForChapter("SPONSOR", primarygroupcode);
		List<SponsershipReport> report = new ArrayList<SponsershipReport>();

		for (Sponsor sponser : sponsers) {

			SponsershipReport sr = new SponsershipReport();
			sr.setId(sponser.getId());
			sr.setSponsor(sponser.getSponsor());
			sr.setAmount(sponser.getAmount());
			sr.setInvoiceNumber(sponser.getInvoiceNumber());
			sr.setInvoiceDate(sponser.getInvoiceDate());
			sr.setAggrementEndDate(sponser.getAggrementEndDate());
			sr.setPartenershipYear(sponser.getPartenershipYear());
			sr.setDiscountCode(sponser.getDiscountCode());
			sr.setIssued(sponser.getAssigned());
			sr.setCrm(sponser.getCrm());
			sr.setSponserLevel(sponser.getSponserLevel());
			sr.setContractTerm(sponser.getSponserContact());
			sr.setType(sponser.getType());
			int issued = sponser.getAssigned();
			List<Member> memebrs = memberRepository.getMembersForSponsorhipCode(sponser.getDiscountCode());
			sr.setRedeemed(memebrs.size());
			int unused = issued - memebrs.size();
			sr.setUnused(unused);
			sr.setCrm(sponser.getCrm());
			sr.setPrimaryGroupCode(sponser.getPrimaryGroupCode());
			sr.setSponserContact(sponser.getSponserContact());
			sr.setRelationWithNational(sponser.getRelationWithNational());
			sr.setExpirationDate(sponser.getExpirationDate());
			sr.setContractTerm(sponser.getInvoiceTerms());
			sr.setSponsorId(sponser.getSponsorId());

			if (sponser.getNationalBenefits() != null) {
				sr.setNationalBenefit(sponser.getNationalBenefits().trim());
			}
			report.add(sr);
		}
		return report;
	}

	/**
	 * Update Transaction log request
	 * 
	 * @param request
	 */
	private void updateCsvLog(TransactionRequest request) {
		CsvLog csvLog = new CsvLog();
		csvLog.setDocName(request.getFileName());
		csvLog.setStatus("SUCCESS");
		csvLog.setUploadedBy(request.getUploadedBy());
		csvLog.setUploadedOn(new Date());
		csvLog.setReportingDate(Utility.getStringToDate(request.getReportedDate()));
		csvLog.setDocType(request.getType());
		logRepository.save(csvLog);
		logRepository.flush();

	}

	/**
	 * To update the Sponsorship CSV log data
	 * 
	 * @param request
	 */

	private void updateSponsershipLog(SponsershipRequest request) {
		CsvLog csvLog = new CsvLog();
		csvLog.setDocName(request.getFileName());
		csvLog.setStatus("SUCCESS");
		csvLog.setUploadedBy(request.getUploadedBy());
		csvLog.setUploadedOn(new Date());
		csvLog.setDocType(request.getType());
		logRepository.save(csvLog);
	}

	/**
	 * To update the SocialMediaRequest CSV log data
	 * 
	 * @param request
	 */
	private void updateCsvLog(SocialMediaRequest request) {
		CsvLog csvLog = new CsvLog();
		csvLog.setDocName(request.getFileName());
		csvLog.setStatus("SUCCESS");
		csvLog.setUploadedBy(request.getUploadedBy());
		csvLog.setUploadedOn(new Date());
		csvLog.setDocType(request.getType());
		logRepository.save(csvLog);
	}

	/**
	 * To get the reporting details
	 * 
	 * @return
	 */
	@Cacheable("getReportingDates")
	public FiscalYearsList getReportingDates() {

		Date date = logRepository.getMaxReportDate();
		FiscalYearsList fiscalYearsList = new FiscalYearsList();
		for (Integer fiscalYear : fyRepository.findUniqueFiscalYears()) {
			ReportingDatesForFiscal reportingDatesForFiscal = new ReportingDatesForFiscal();
			Map<String, Date> reportDates = Utility.getStartAndEndDateForFiscalYear(fiscalYear);

			Set<CsvLog> csvLog = this.logRepository.getReportingDatesforfiscalyear(reportDates.get("dtStart"),
					reportDates.get("dtEnd"));
			if(!csvLog.isEmpty() ) {
				reportingDatesForFiscal.setFiscaYear(fiscalYear);
				reportingDatesForFiscal.setCsvLog(new ArrayList<>(csvLog));
				fiscalYearsList.getReportingDatesForFiscal().add(reportingDatesForFiscal);
			}
		}
		fiscalYearsList.setCurrentFiscalYear(Utility.getFiscalYearFromDate(date));
		return fiscalYearsList;
	}

	public boolean isDLUplaoded(Date reportingDate) {
		return this.logRepository.isDLUploaded(reportingDate);
	}

	public void deleteSponsor(int id) {
		this.sponserRepository.deleteById(id);
	}

	/**
	 * 
	 * @param sponsor
	 */
	public SponsershipReport updateSponsor(SponsershipReport sponsor) {

		this.sponserRepository.updateSponsor(sponsor.getSponsor(), sponsor.getAmount(), sponsor.getInvoiceNumber(),
				sponsor.getInvoiceDate(), sponsor.getAggrementEndDate(), sponsor.getPartenershipYear(),
				sponsor.getDiscountCode(), sponsor.getRelationWithNational(), sponsor.getCrm(),
				sponsor.getSponserContact(), sponsor.getSponserLevel(), sponsor.getExpirationDate(),
				sponsor.getContractTerm(), sponsor.getIssued(), sponsor.getPrimaryGroupCode().toUpperCase(),
				sponsor.getNationalBenefit().toUpperCase(), sponsor.getType().toUpperCase(), sponsor.getPaymentDate(),
				sponsor.getId());
		
		
		SponsershipReport sr = new SponsershipReport();
		sr.setId(sponsor.getId());
		sr.setSponsor(sponsor.getSponsor());
		sr.setAmount(sponsor.getAmount());
		sr.setInvoiceNumber(sponsor.getInvoiceNumber());
		sr.setInvoiceDate(sponsor.getInvoiceDate());
		sr.setAggrementEndDate(sponsor.getAggrementEndDate());
		sr.setPartenershipYear(sponsor.getPartenershipYear());
		sr.setDiscountCode(sponsor.getDiscountCode());
		sr.setIssued(sponsor.getIssued());
		sr.setCrm(sponsor.getCrm());
		sr.setSponserLevel(sponsor.getSponserLevel());
		sr.setContractTerm(sponsor.getSponserContact());
		sr.setType(sponsor.getType());
		int issued = sponsor.getIssued();
		List<Member> memebrs = memberRepository.getMembersForSponsorhipCode(sponsor.getDiscountCode());
		sr.setRedeemed(memebrs.size());
		int unused = issued - memebrs.size();
		sr.setUnused(unused);
		sr.setCrm(sponsor.getCrm());
		sr.setPrimaryGroupCode(sponsor.getPrimaryGroupCode());
		sr.setSponserContact(sponsor.getSponserContact());
		sr.setRelationWithNational(sponsor.getRelationWithNational());
		sr.setExpirationDate(sponsor.getExpirationDate());
		sr.setContractTerm(sponsor.getContractTerm());
		sr.setSponsorId(sponsor.getSponsorId());

		if (sponsor.getNationalBenefit() != null) {
			sr.setNationalBenefit(sponsor.getNationalBenefit().trim());
		}
		this.sponserRepository.flush();
		
		return sr;
	}

	/**
	 * 
	 * @param sponsor
	 */
	public SponsershipReport saveSponser(Sponsor sponsor) {
		sponsor.setSponsorId(sponserRepository.getMaxSponsorId() + 1);
		this.sponserRepository.save(sponsor);
		
		SponsershipReport sr = new SponsershipReport();
		sr.setId(sponsor.getId());
		sr.setSponsor(sponsor.getSponsor());
		sr.setAmount(sponsor.getAmount());
		sr.setInvoiceNumber(sponsor.getInvoiceNumber());
		sr.setInvoiceDate(sponsor.getInvoiceDate());
		sr.setAggrementEndDate(sponsor.getAggrementEndDate());
		sr.setPartenershipYear(sponsor.getPartenershipYear());
		sr.setDiscountCode(sponsor.getDiscountCode());
		sr.setIssued(sponsor.getAssigned());
		sr.setCrm(sponsor.getCrm());
		sr.setSponserLevel(sponsor.getSponserLevel());
		sr.setContractTerm(sponsor.getSponserContact());
		sr.setType(sponsor.getType());
		int issued = sponsor.getAssigned();
		List<Member> memebrs = memberRepository.getMembersForSponsorhipCode(sponsor.getDiscountCode());
		sr.setRedeemed(memebrs.size());
		int unused = issued - memebrs.size();
		sr.setUnused(unused);
		sr.setCrm(sponsor.getCrm());
		sr.setPrimaryGroupCode(sponsor.getPrimaryGroupCode());
		sr.setSponserContact(sponsor.getSponserContact());
		sr.setRelationWithNational(sponsor.getRelationWithNational());
		sr.setExpirationDate(sponsor.getExpirationDate());
		sr.setContractTerm(sponsor.getInvoiceTerms());
		sr.setSponsorId(sponsor.getSponsorId());

		if (sponsor.getNationalBenefits() != null) {
			sr.setNationalBenefit(sponsor.getNationalBenefits().trim());
		}
		this.sponserRepository.flush();
		return sr;

	}

	/**
	 * 
	 * @return Get Sponser for the chapters
	 */
	@Cacheable("getNationalSponsers")
	public List<SponsershipReport> getNationalSponsers(String username, String type) {

		List<String> codes = new ArrayList<String>();

		for (Chapter c : configService.getChaptersForUser(username)) {
			codes.add(c.getPrimaryGroupCode());
		}
		codes.add("NAT");

		List<Sponsor> sponsers = sponserRepository.findByType(type, codes);

		List<SponsershipReport> report = new ArrayList<SponsershipReport>();

		for (Sponsor sponser : sponsers) {

			SponsershipReport sr = new SponsershipReport();
			sr.setId(sponser.getId());
			sr.setSponsor(sponser.getSponsor());
			sr.setAmount(sponser.getAmount());
			sr.setInvoiceNumber(sponser.getInvoiceNumber());
			sr.setInvoiceDate(sponser.getInvoiceDate());
			sr.setAggrementEndDate(sponser.getAggrementEndDate());
			sr.setPartenershipYear(sponser.getPartenershipYear());
			sr.setDiscountCode(sponser.getDiscountCode());
			sr.setIssued(sponser.getAssigned());
			sr.setCrm(sponser.getCrm());
			sr.setSponserLevel(sponser.getSponserLevel());
			sr.setContractTerm(sponser.getSponserContact());
			sr.setType(sponser.getType());
			int issued = sponser.getAssigned();
			List<Member> memebrs = memberRepository.getMembersForSponsorhipCode(sponser.getDiscountCode());
			sr.setRedeemed(memebrs.size());
			int unused = issued - memebrs.size();
			sr.setUnused(unused);
			sr.setCrm(sponser.getCrm());
			sr.setPrimaryGroupCode(sponser.getPrimaryGroupCode());
			sr.setSponserContact(sponser.getSponserContact());
			sr.setRelationWithNational(sponser.getRelationWithNational());
			sr.setExpirationDate(sponser.getExpirationDate());
			sr.setContractTerm(sponser.getInvoiceTerms());
			sr.setSponsorId(sponser.getSponsorId());
			sr.setPaymentDate(sponser.getPaymentDate());

			if (sponser.getNationalBenefits() != null) {
				sr.setNationalBenefit(sponser.getNationalBenefits().trim());
			}
			report.add(sr);
		}
		return report;
	}

	public void evictAllCaches() {
		cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
}
