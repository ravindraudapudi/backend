package com.lucida.emembler.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucida.emembler.entity.Member;
import com.lucida.emembler.requestdtos.MemberRequest;
import com.lucida.emembler.responsedtos.ChapterOverview;
import com.lucida.emembler.responsedtos.PaidSponseredDto;
import com.lucida.emembler.responsedtos.Response;
import com.lucida.emembler.responsedtos.SocialMediaResponse;
import com.lucida.emembler.responsedtos.TotalRevenueResponse;
import com.lucida.emembler.service.MemberService;
import com.lucida.emembler.service.TransactonService;

/**
 * 
 * @author Ravindra
 *	Membership Management Controller
 */
@RestController
@RequestMapping("/api")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TransactonService transactonService;

	/**
	 * 
	 * @param chapter
	 * @return return list of chapters
	 */
	@GetMapping(value = "/getMembersByChapter/{chapter}")
	public List<Member> getMembersByChapter(@PathVariable("chapter") String chapter) {
		return memberService.getMembrsForChapter(chapter);
	}

	/**
	 * 
	 * @param request Members request
	 * @return
	 */
	@PostMapping(value = "/saveMembers")
	public ResponseEntity<Response> saveMembers(@RequestBody MemberRequest request) {
		
		Response response = new Response();
		memberService.saveMembershipData(request);
		transactonService.evictAllCaches();
		response.setResponseStatus("200");
		response.setSuccessDescription("Distrubution file uploaded successfully");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * To get all paid and expired members for the current fiscal year monthly
	 * 
	 * @return paid members
	 */
	@GetMapping(value = "/getPaidExpiredMembers/{chapter}/{reportingDate}/{fiscalYear}")
	public List<PaidSponseredDto> getPaidExpiredMembers(
			@PathVariable("chapter") String chapter , 
			@PathVariable("reportingDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date reportingDate,
			@PathVariable("fiscalYear") int fiscalYear) {
		return memberService.getPaidExpiredMembers(chapter, reportingDate, fiscalYear);
	}

	/**
	 * Getting all members for the required chapter
	 * 
	 * @return List of paid and Sponsored Members
	 */
	@GetMapping(value = "/getpaidSponseredChart/{chapter}/{reportingDate}/{fiscalYear}")
	public List<PaidSponseredDto> getpaidSponseredChart(
			@PathVariable("chapter") String chapter,
			@PathVariable("reportingDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date reportingDate,
			@PathVariable("fiscalYear") int fiscalYear) {
		return memberService.findSponseredMembers(chapter, reportingDate,fiscalYear);
	}

	/**
	 * To get monthly Membership revenue
	 * 
	 * @return paid members
	 */
	@GetMapping(value = "/getMonthyMembershipRevenue/{chapter}/{reportingDate}/{fiscalYear}")
	public List<PaidSponseredDto> getMonthyMembershipRevenue(
			@PathVariable("chapter") String chapter,
			@PathVariable("reportingDate") String reportingDate,
			@PathVariable("fiscalYear") int fiscalYear) {
		return memberService.getMonthlyMembershipRevenue(chapter,reportingDate,fiscalYear);
	}

	/**
	 * To get Overview Details for chapter
	 * 
	 * @return Overview
	 */
	@GetMapping(value = "/getOverviewDetails/{chapter}/{reportingDate}/{fiscalYear}")
	public ChapterOverview getOverviewDetails(@PathVariable("chapter") String chapter,
			@PathVariable("reportingDate") String reportingDate,
			@PathVariable("fiscalYear") int fiscalYear) {
		return memberService.getOverviewDetails(chapter,reportingDate,fiscalYear);
	}

	/**
	 * To get monthly Membership revenue
	 * 
	 * @return paid members
	 */
	@GetMapping(value = "/getSocialMedia/{chapter}")
	public List<SocialMediaResponse> getSocialMediaData(@PathVariable("chapter") String chapter) {
		return memberService.getSocialMedia(chapter);
	}

	/**
	 * Getting all members for the required chapter
	 * 
	 * @return List of paid and Sponsored Members
	 */
	@GetMapping(value = "/getTotalRevenueChart/{chapter}/{fiscalYear}")
	public List<TotalRevenueResponse> getTotalRevenueChart(@PathVariable("chapter") String chapter,
			@PathVariable("fiscalYear") int fiscalYear) {
		return memberService.findTotalRevenue(chapter,fiscalYear);
	}

	/**
	 * Getting all members for the sponsorship code
	 * 
	 * @return List of members belong to chapter
	 */
	@GetMapping(value = "/getMembersForSponsorhip/{sponserhipCode}")
	public List<Member> getMembersForSponsorhipCode(@PathVariable("sponserhipCode") String sponserhipCode) {
		return memberService.getMembersForSponsorhipCode(sponserhipCode);
	}

	/**
	 * Getting Member details for api guid
	 * 
	 * @return List of members belong to chapter
	 */
	@GetMapping(value = "/getMemberDetailsForGuid/{apiGuid}")
	public Member getMemberDetailsForGuid(@PathVariable("apiGuid") String apiGuid) {
		return memberService.getMemberDetailsForApiGuid(apiGuid);
	}

}
