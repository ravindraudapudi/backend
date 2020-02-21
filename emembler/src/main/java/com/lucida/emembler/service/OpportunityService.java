package com.lucida.emembler.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucida.emembler.dao.OpportunityRepository;
import com.lucida.emembler.dao.PickListRepository;
import com.lucida.emembler.entity.FollowUp;
import com.lucida.emembler.entity.Opportunity;
import com.lucida.emembler.entity.PickList;
import com.lucida.emembler.exceptions.DaoException;

@Service
@Transactional
public class OpportunityService {

	@Autowired
	private PickListRepository pickListRepository;

	@Autowired
	private OpportunityRepository opportunityRepository;

	/**
	 * To add Opportunity
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public void saveStage(PickList pickList) throws DaoException {
		pickListRepository.saveAndFlush(pickList);
		
	}

	/**
	 * To Retrive all Opportunities
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public List<PickList> getStages() throws DaoException {
		return pickListRepository.getStagesByOrder();
	}

	/**
	 * To add Opportunity
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public void deleteStage(int id) throws DaoException {
		pickListRepository.deleteById(id);
		pickListRepository.flush();
	}

	/**
	 * To add Opportunity
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public void updateStage(int id, PickList pickList) throws DaoException {
		pickListRepository.saveAndFlush(pickList);
	}

	/**
	 * To add Opportunity
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public void saveOpportunity(Opportunity opportunity) throws DaoException {
		opportunityRepository.save(opportunity);
		opportunityRepository.flush();
	}
	
	/**
	 * To add Opportunity
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public void updateOpportunity(Opportunity opportunity) throws DaoException {
		opportunityRepository.save(opportunity);
		opportunityRepository.flush();
	}

	/**
	 * To Retrive all Opportunities
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public List<Opportunity> getOpportunitiesForUserName(Collection<String> primaryGroupCode) throws DaoException {
		return opportunityRepository.getOpportunitiesForUserName(primaryGroupCode);
	}

	/**
	 * To retrive chapter opportunities.
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public List<Opportunity> getOpporuntiesForChapter(String chapterCode) throws DaoException {
		return opportunityRepository.findByPrimaryGroupCode(chapterCode);
	}
	
	/**
	 * To retrive chapter opportunities.
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public List<Opportunity> getOpporunties() throws DaoException {
		return opportunityRepository.getAllOpportunities();
	}


	/**
	 * To add Opportunity
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public void deleteOpportunity(int id) throws DaoException {
		opportunityRepository.deleteById(id);
		opportunityRepository.flush();
	}

	/**
	 * To add Opportunity
	 * 
	 * @param opportunity
	 * @throws DaoException
	 */
	public String addNotesToOpportunity(int id, FollowUp followUp) throws DaoException {

		Optional<Opportunity> opp = opportunityRepository.findById(id);

		if (opp.isPresent()) {
			Opportunity opportunity = opp.get();
			opportunity.setStages(followUp.getStages());
			opportunity.getFollowUps().add(followUp);
			opportunityRepository.save(opportunity);
			opportunityRepository.flush();
			return "SUCCESS";
		} else {
			opportunityRepository.flush();
			return "FAILURE";
		}

	}

}
