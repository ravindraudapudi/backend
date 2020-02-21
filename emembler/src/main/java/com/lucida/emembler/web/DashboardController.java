package com.lucida.emembler.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucida.emembler.entity.Member;
import com.lucida.emembler.responsedtos.DashboardResponse;
import com.lucida.emembler.service.DashboardService;

/**
 * @author Lucida National dashboard service
 * 
 */
@RestController
@RequestMapping("/api")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	/**
	 * Retrieve all total active membership data
	 * 
	 * @return
	 */
	@GetMapping(value = "/getTotalActiveMembers/{fiscalYear}/{rDate}/{username}")
	public DashboardResponse getTotalActiveMembership(@PathVariable("fiscalYear") int fiscalYear,
			@PathVariable("rDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reportingDate, @PathVariable("username") String username) {
		return dashboardService.getTotalActiveMembership(fiscalYear, reportingDate,username);
	}

	/**
	 * Retrieve Paid Members count for the fiscal year
	 * 
	 * @return
	 */
	@GetMapping(value = "/getPaidMembers/{fiscalYear}/{rDate}/{username}")
	public DashboardResponse getPaidMembers(@PathVariable("fiscalYear") int fiscalYear,
			@PathVariable("rDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reportingDate,@PathVariable("username") String username) {
		return dashboardService.getPaidMembers(fiscalYear, reportingDate,username);
	}

	/**
	 * Retrieve Local sponsored Members count
	 * 
	 * @return
	 */
	@GetMapping(value = "/getLocalSponsored/{fiscalYear}/{rDate}/{username}")
	public DashboardResponse getLocalSponsored(@PathVariable("fiscalYear") int fiscalYear,
			@PathVariable("rDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reportingDate,@PathVariable("username") String username) {
		return dashboardService.getLocalSponsered(fiscalYear, reportingDate,username);
	}

	/**
	 * Retrieve Local sponsored Members count
	 * 
	 * @return
	 */
	@GetMapping(value = "/getNationalSponsored/{fiscalYear}/{rDate}/{username}")
	public DashboardResponse getNationalSponsored(@PathVariable("fiscalYear") int fiscalYear,
			@PathVariable("rDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reportingDate,@PathVariable("username") String username) {
		return dashboardService.getNationalSponsered(fiscalYear, reportingDate,username);
	}

	/**
	 * Retrieve Total revenue for the chapters
	 * 
	 * @return
	 */
	@GetMapping(value = "/getTotalRevenue/{fiscalYear}/{username}")
	public DashboardResponse getTotalRevenue(@PathVariable("fiscalYear") int fiscalYear,@PathVariable("username") String username) {
		return dashboardService.getTotalRevenue(fiscalYear,username);
	}

	/**
	 * 
	 * @param chapter
	 * @param reportingDate
	 * @return
	 */
	@GetMapping(value = "/getTotalActiveMembersDetails/{chapter}/{rDate}")
	public List<Member> getTotalActiveMembersDetails(@PathVariable("chapter") String chapter,
			@PathVariable("rDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reportingDate) {
		return dashboardService.getTotalActiveMembersDetails(chapter, reportingDate);
	}

}