package com.lucida.emembler.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucida.emembler.dao.SponserRepository;
import com.lucida.emembler.entity.CsvLog;
import com.lucida.emembler.entity.Sponsor;
import com.lucida.emembler.exceptions.InvalidDataException;
import com.lucida.emembler.requestdtos.FiscalYearsList;
import com.lucida.emembler.requestdtos.ProfitAndLossRequest;
import com.lucida.emembler.requestdtos.ReportingDatesForFiscal;
import com.lucida.emembler.requestdtos.SocialMediaRequest;
import com.lucida.emembler.requestdtos.SponsershipRequest;
import com.lucida.emembler.requestdtos.TransactionRequest;
import com.lucida.emembler.responsedtos.Response;
import com.lucida.emembler.responsedtos.SponsershipReport;
import com.lucida.emembler.responsedtos.SponsorshipResponse;
import com.lucida.emembler.service.TransactonService;
import com.lucida.emembler.utility.Utility;

/**
 * @author Lucida Controller class for
 * 
 *         Controller class to save the Membership data
 */
@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	private TransactonService transactonService;

	@Autowired
	private SponserRepository sponserRepository;

	/**
	 * 
	 * @paramRequest for all Members
	 * @return Response Object
	 */
	@PostMapping(value = "/saveTransactions")
	public ResponseEntity<Response> saveTransactions(@RequestBody TransactionRequest request) {

		try {
			boolean uplaoded = transactonService.isDLUplaoded(Utility.getStringToDate(request.getReportedDate()));
			transactonService.evictAllCaches();
			if (uplaoded) {
				transactonService.saveTransaction(request);
				return getResponse("200", "", "Transaction file uploaded successfully");
			} else {
				return getResponse("500", "Please upload the Distribution file", "");
			}
		} catch (Exception e) {
			return getResponse("500", "Please upload the Distribution file", "");
		}
	}

	/**
	 * 
	 * @paramRequest for all Members
	 * @return Response Object
	 */
	@PostMapping(value = "/saveSponsers")
	public ResponseEntity<Response> saveSponsers(@RequestBody SponsershipRequest request) {
		try {
			transactonService.saveSponsers(request);
			transactonService.evictAllCaches();
			return getResponse("200", "", "Sponsership file uploaded successfully");
		} catch (Exception e) {
			return getResponse("500", "Unable to uplaod failed due to Service error", "");
		}
	}

	/**
	 * 
	 * @paramRequest for all Members
	 * @return Response Object
	 */
	@PostMapping(value = "/processProfitAndLoss")
	public ResponseEntity<Response> saveProfitAndLoss(@RequestBody ProfitAndLossRequest request) {
		try {
			transactonService.evictAllCaches();
			transactonService.updateProfitAndLoss(request);
			return getResponse("200", "", "P&L file uploaded successfully");
		} catch (Exception e) {
			return getResponse("500", "Incorrect file found please validate", "");
		}
	}

	/**
	 * 
	 * @paramRequest for all Members
	 * @return Response Object
	 */
	@PostMapping(value = "/processSocialMedia")
	public ResponseEntity<Response> saveSocialMedia(@RequestBody SocialMediaRequest request) {

		try {
			transactonService.evictAllCaches();
			transactonService.saveSocialMedia(request);
			return getResponse("200", "", "Social Media file uploaded successfully");
		} catch (Exception e) {
			return getResponse("500", "Social Media file not uploaded successfully", "");
		}
	}

	/**
	 * Get Chapter level sponserhip report details
	 * 
	 * @param chapterNameprofit
	 * @return
	 */
	@GetMapping(value = "/getSponserForChapter/{chapterName}")
	public List<SponsershipReport> getSponserForChapter(@PathVariable("chapterName") String chapterName) {
		return transactonService.getSponsers(chapterName);
	}

	/**
	 * Get Chapter level sponserhip report details
	 * 
	 * @param chapterName
	 * @return
	 */
	@GetMapping(value = "/getNationalSponsors/{userName}/{type}")
	public List<SponsershipReport> getNationalSponsors(@PathVariable("userName") String userName,
			@PathVariable("type") String type) {
		return transactonService.getNationalSponsers(userName, type);
	}

	/**
	 * 
	 * @paramRequest for all Members
	 * @return Response Object
	 */
	@PostMapping(value = "/updateSponsor")
	public ResponseEntity<SponsorshipResponse> updateSponsor(@RequestBody SponsershipReport sponsor) {

		try {
			SponsershipReport sponsershipReport = transactonService.updateSponsor(sponsor);
			SponsorshipResponse sponsorshipResponse = new SponsorshipResponse();
			sponsorshipResponse.setSponsershipReport(sponsershipReport);
			sponsorshipResponse.setSuccessDescription("Sponsor Updated Succesffully");
			sponsorshipResponse.setResponseStatus("200");
			return new ResponseEntity<SponsorshipResponse>(sponsorshipResponse, HttpStatus.OK);
		} catch (Exception e) {
			SponsorshipResponse sponsorshipResponse = new SponsorshipResponse();
			sponsorshipResponse.setSuccessDescription("Unable to update sponsor due to server error");
			sponsorshipResponse.setResponseStatus("500");
			return new ResponseEntity<SponsorshipResponse>(sponsorshipResponse, HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @paramRequest for all Members
	 * @return Response Object
	 */
	@PostMapping(value = "/saveSponsor")
	public ResponseEntity<SponsorshipResponse> saveSponsor(@RequestBody Sponsor sponsor) {
		
		SponsorshipResponse sponsorshipResponse = new SponsorshipResponse();

		try {
			List<Sponsor> sponsors = this.sponserRepository.findByDiscountCode(sponsor.getDiscountCode());
			if (sponsors.size() == 0 || sponsor.getDiscountCode().equalsIgnoreCase("NA")) {
				SponsershipReport sponsershipReport = transactonService.saveSponser(sponsor);
				sponsorshipResponse.setSponsershipReport(sponsershipReport);
				sponsorshipResponse.setSuccessDescription("Sponsor Added Succesffully");
				sponsorshipResponse.setResponseStatus("200");
				return new ResponseEntity<SponsorshipResponse>(sponsorshipResponse, HttpStatus.OK);
			} else {
				sponsorshipResponse.setErrorDescrition("Sponsor code already Exist");
				sponsorshipResponse.setResponseStatus("500");
				return new ResponseEntity<SponsorshipResponse>(sponsorshipResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sponsorshipResponse.setSuccessDescription("Unable to add sponsor due to server error");
			sponsorshipResponse.setResponseStatus("500");
			return new ResponseEntity<SponsorshipResponse>(sponsorshipResponse, HttpStatus.OK);
		}
	}

	/**
	 * Get Reporting Dates in descending order for reports
	 * 
	 * 
	 * @return List<Date>
	 */
	@GetMapping(value = "/getReportingDates")
	public FiscalYearsList getAllReportingDates() {
		return transactonService.getReportingDates();
	}

	/**
	 * Remove Board Member details
	 * 
	 * @param id
	 * @return
	 * @throws InvalidDataException
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/deleteSponsor/{id}")
	public ResponseEntity<Response> deleteSponsor(@PathVariable("id") int id) {
		try {
			transactonService.evictAllCaches();
			transactonService.deleteSponsor(id);
			return getResponse("200", "", "Sponsor has been removed");
		} catch (Exception e) {
			return getResponse("500", "Unable to remove Server due to server error", "");
		}
	}

	/**
	 * For common repsone
	 * 
	 * @param statusCode
	 * @param errorDescription
	 * @param successDescription
	 * @return
	 */
	private ResponseEntity<Response> getResponse(String statusCode, String errorDescription,
			String successDescription) {
		Response response = new Response();
		response.setSuccessDescription(successDescription);
		response.setErrorDescrition(errorDescription);
		response.setResponseStatus(statusCode);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
