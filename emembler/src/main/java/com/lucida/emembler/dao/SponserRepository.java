package com.lucida.emembler.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.Sponsor;

@Repository
public interface SponserRepository extends JpaRepository<Sponsor, Integer> {
	
	@Query(value = "select max(sponsor_id) from sponsor ;", nativeQuery = true)
	public Integer getMaxSponsorId();
	
	@Query("select count(s)>0 from Sponsor s where s.discountCode = :discountCode")
	public boolean existsIfSponsershipCode(@Param("discountCode") String discountCode);
	
	@Query("select s from Sponsor s where s.discountCode = :discountCode")
	public List<Sponsor> findByDiscountCode(@Param("discountCode") String discountCode);
	
	@Query("select s from Sponsor s where s.type = :type and s.primaryGroupCode in :primaryGroupCode order by s.sponsor")
	public List<Sponsor> findByType(@Param("type") String type,@Param("primaryGroupCode") Collection<String> primaryGroupCode);
	
	List<Sponsor> findByPrimaryGroupCode(String groupCode); 
	
	@Query("select count(s)>0 from Sponsor s where s.sponsorId = :sponsorId AND type = :type")
	public boolean existsIfSponsorId(@Param("sponsorId") int sponsorId , @Param("type") String type);
	
	@Query("select s from Sponsor s where s.sponsorId = :sponsorId AND type = :type")
	public Sponsor findBySponsorIdAndType(@Param("sponsorId") int sponsorId, @Param("type") String type);
	
	
	@Query(value = "SELECT s "
			+ " FROM Sponsor s WHERE s.invoiceNumber = :invoiceNumber "
			+ " AND s.primaryGroupCode = :primaryGroupCode "
			+ " AND s.sponsor =  :sponsor ")
	List<Sponsor> findBydiscountCodeAndInvoice( 
			@Param("invoiceNumber") String invoiceNumber,
			@Param("primaryGroupCode") String primaryGroupCode,			
			@Param("sponsor") String sponsor);
	
	@Query(value = "SELECT s "
			+ "FROM Sponsor s WHERE s.type = :type "
			+ " AND s.primaryGroupCode = :primGroupCode order by s.sponsor")
	List<Sponsor> getSponsersForChapter( @Param("type") String type,
			@Param("primGroupCode") String primGroupCode);
	
	@Query(value = "SELECT sum(s.amount) "
			+ "FROM Sponsor s WHERE s.type = :type "
			+ " AND s.primaryGroupCode = :primGroupCode ")
	Integer getSumofMemberTransaction( @Param("type") String type,
			@Param("primGroupCode") String primGroupCode);
	
	
	/**
	 * Update Fiscal year Profit and loss data
	 * 
	 * @param expiryDate
	 * @param apiGuid
	 */
	@Modifying(clearAutomatically = true)
	@Query("update Sponsor s set " + 
			" s.sponsor = :sponsor, " +
			" s.amount = :amount, " + 
			" s.invoiceNumber = :invoiceNumber, " + 
			" s.invoiceDate = :invoiceDate, " + 
			" s.aggrementEndDate = :aggrementEndDate, " + 
			" s.partenershipYear = :partenershipYear, " + 
			" s.discountCode = :discountCode, " + 
			" s.relationWithNational = :relationWithNational, "+
			" s.crm = :crm, " +
			" s.sponserContact = :sponserContact, " +
			" s.sponserLevel = :sponserLevel, " +
			" s.expirationDate = :expirationDate, "+
			" s.invoiceTerms = :invoiceTerms, " +
			" s.assigned = :assigned, "+
			" s.primaryGroupCode = :primaryGroupCode, "+
			" s.nationalBenefits = :nationalBenefits, "+
			" s.type = :type, " +
			" s.paymentDate = :paymentDate " +
		    " where s.id = :id")
	public void updateSponsor(
			@Param("sponsor") String sponsor, 
			@Param("amount") double amount,
			@Param("invoiceNumber") String invoiceNumber,
			@Param("invoiceDate") Date invoiceDate,
			@Param("aggrementEndDate") Date aggrementEndDate,
			@Param("partenershipYear") String partenershipYear,
			@Param("discountCode") String discountCode,
			@Param("relationWithNational") String relationWithNational,
			@Param("crm") String crm,
			@Param("sponserContact") String sponserContact,
			@Param("sponserLevel") String sponserLevel,
			@Param("expirationDate") Date expirationDate,
			@Param("invoiceTerms") String invoiceTerms,
			@Param("assigned") int assigned,
			@Param("primaryGroupCode") String primaryGroupCode,
			@Param("nationalBenefits") String nationalBenefits,
			@Param("type") String type,
			@Param("paymentDate") Date paymentDate,
			@Param("id") int id);
}
