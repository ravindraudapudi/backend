
package com.lucida.emembler.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.Opportunity;

@Repository
@Transactional
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer>{
	
	@Query("select op from Opportunity op order by op.sponsorName")
	public List<Opportunity> getAllOpportunities();
	
	@Query("select op from Opportunity op where op.primaryGroupCode in :primaryGroupCode order by op.sponsorName")
	public List<Opportunity> getOpportunitiesForUserName(@Param("primaryGroupCode") Collection<String> primaryGroupCode);
	
	
	@Query("select op from Opportunity op where op.primaryGroupCode = :primaryGroupCode order by op.sponsorName")
	public List<Opportunity> findByPrimaryGroupCode(@Param("primaryGroupCode") String primaryGroupCode);
	
}
