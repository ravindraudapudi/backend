package com.lucida.emembler.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucida.emembler.dao.PickListRepository;
import com.lucida.emembler.entity.Chapter;
import com.lucida.emembler.entity.FollowUp;
import com.lucida.emembler.entity.Opportunity;
import com.lucida.emembler.entity.PickList;
import com.lucida.emembler.exceptions.DaoException;
import com.lucida.emembler.responsedtos.Response;
import com.lucida.emembler.service.ConfigService;
import com.lucida.emembler.service.OpportunityService;

@RestController
@RequestMapping("/api")
public class OpportunityController {

	@Autowired
	private OpportunityService opportunityService;

	@Autowired
	private PickListRepository pickListRepository;

	@Autowired
	private ConfigService configService;

	private static final Logger log = LoggerFactory.getLogger(OpportunityController.class);

	/**
	 * To Add stage
	 * 
	 * @param opportunity
	 * @return
	 */
	@PostMapping("/saveStage")
	public ResponseEntity<Response> saveStage(@RequestBody PickList pickList) {
		try {
			if (pickListRepository.existsIfOrder(pickList.getOrderId())) {
				return getResponse("500", "", "Stage order already exist");
			} else {
				opportunityService.saveStage(pickList);
				return getResponse("201", "", "Stage added Succesfully");
			}
		} catch (DaoException e) {
			return getResponse("500", "Stage not added Succesfully", "");
		}
	}

	/**
	 * Get ALL stages
	 * 
	 * @return
	 */
	@GetMapping("/getStages")
	public List<PickList> getStages() {
		List<PickList> list = null;
		try {
			list = opportunityService.getStages();
		} catch (DaoException e) {
			log.debug("Unable to find the stages" , e);
		}
		return list;
	}

	/**
	 * To Update Opportunity
	 * 
	 * @param opportunity
	 * @return
	 */
	@PutMapping("/updateStage/{id}")
	public ResponseEntity<Response> updateStage(@PathVariable("id") int id, @RequestBody PickList pickList) {
		try {
			opportunityService.updateStage(id, pickList);
			return getResponse("200", "", "Stage updated Succesfully");
		} catch (DaoException e) {
			return getResponse("500", "Stage not updated Succesfully", "");
		}
	}

	/**
	 * To Update Opportunity
	 * 
	 * @param opportunity
	 * @return
	 */
	@DeleteMapping("/deleteStage/{id}")
	public ResponseEntity<Response> deleteStage(@PathVariable int id) {
		try {
			opportunityService.deleteStage(id);
			return getResponse("200", "", "Stage deleted Succesfully");
		} catch (DaoException e) {
			return getResponse("500", "Stage not deleted Succesfully", "");
		}
	}

	/**
	 * To Add Opportunity
	 * 
	 * @param opportunity
	 * @return
	 */
	@PostMapping("/saveOportunity")
	public ResponseEntity<Response> saveOpportunity(@RequestBody Opportunity opportunity) {
		try {
			opportunityService.saveOpportunity(opportunity);
			return getResponse("201", "", "Opportunity added Succesfully");
		} catch (DaoException e) {
			return getResponse("500", "Opportunity not added Succesfully", "");
		}
	}

	/**
	 * To Add Opportunity
	 * 
	 * @param opportunity
	 * @return
	 */
	@PutMapping("/updateOportunity")
	public ResponseEntity<Response> updateOpportunity(@RequestBody Opportunity opportunity) {
		try {
			opportunityService.updateOpportunity(opportunity);
			return getResponse("201", "", "Opportunity updated Succesfully");
		} catch (DaoException e) {
			return getResponse("500", "Opportunity not updated Succesfully", "");
		}
	}

	/**
	 * Get ALL Opportunities
	 * 
	 * @return
	 */
	@GetMapping("/getAllOpportunities/{userName}")
	public List<Opportunity> getOpportunities(@PathVariable("userName") String userName) {
		List<Opportunity> list = null;

		List<String> codes = new ArrayList<>();
		try {
			if (configService.getChaptersForUser(userName) != null) {
				for (Chapter c : configService.getChaptersForUser(userName)) {
					codes.add(c.getPrimaryGroupCode());
				}
			}
			codes.add("NAT");
			list = opportunityService.getOpportunitiesForUserName(codes);
		} catch (DaoException e) {
			log.debug("Unable to find all opportunities",e);
		}
		return list;
	}

	/**
	 * Get ALL Opportunities
	 * 
	 * @return
	 */
	@GetMapping("/getOpportunities/{chapterCode}")
	public List<Opportunity> getOpportunitiesForChapter(@PathVariable("chapterCode") String chapterCode) {
		List<Opportunity> list = null;
		try {
			list = opportunityService.getOpporuntiesForChapter(chapterCode);
		} catch (DaoException e) {
			log.debug("Unable to find opportunity",e);
		}
		return list;
	}

	/**
	 * Get ALL Opportunities
	 * 
	 * @return
	 */
	@GetMapping("/getAllOpportunities")
	public List<Opportunity> getAllOpportunities() {
		List<Opportunity> list = null;
		try {
			list = opportunityService.getOpporunties();
		} catch (DaoException e) {
			log.debug("Unable to find the all opportunities",e);
		}
		return list;
	}

	/**
	 * To Update Opportunity
	 * 
	 * @param opportunity
	 * @return
	 */
	@DeleteMapping("/deleteOpportunity/{id}")
	public ResponseEntity<Response> deleteOpportunity(@PathVariable int id) {
		try {
			opportunityService.deleteOpportunity(id);
			return getResponse("200", "", "Opportunity deleted Succesfully");
		} catch (DaoException e) {
			return getResponse("500", "Opportunity not deleted Succesfully", "");
		}
	}

	/**
	 * To Update Opportunity
	 * 
	 * @param opportunity
	 * @return
	 */
	@PutMapping("/addNotesToOpportunity/{id}")
	public ResponseEntity<Response> addNotesToOppoertunity(@PathVariable int id, @RequestBody FollowUp followUp) {
		try {
			String result = opportunityService.addNotesToOpportunity(id, followUp);
			if (result.equalsIgnoreCase("SUCCESS")) {
				return getResponse("200", "", "Notes addded Succesfully");
			} else {
				return getResponse("500", "Notes not added Succesfully", "");
			}

		} catch (DaoException e) {
			return getResponse("500", "Notes not added Succesfully", "");
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
