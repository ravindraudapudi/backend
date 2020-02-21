package com.lucida.emembler.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucida.emembler.responsedtos.DetailsMatricsResponse;
import com.lucida.emembler.service.MetricsService;

/**
 * @author Lucida Detailed metrics service
 * 
 */
@RestController
@RequestMapping("/api")
public class DetailMetricsController {

	@Autowired
	private MetricsService metricsService;

	/**
	 * Retrieve all total active membership data
	 * 
	 * 
	 * @return
	 */
	@GetMapping(value = "/getDetailedMetrics/{chapter}/{rDate}/{fiscalYear}")
	public DetailsMatricsResponse getDetailedMetrics(
			@PathVariable("chapter") String chapter,
			@PathVariable("rDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reportingDate,
			@PathVariable("fiscalYear") int fiscalYear) {
		return metricsService.getDetailedMetrics(chapter, reportingDate,fiscalYear);
	}
}
