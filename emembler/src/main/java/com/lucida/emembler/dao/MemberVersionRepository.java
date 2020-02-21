package com.lucida.emembler.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucida.emembler.entity.MembersVersionData;

@Repository
@Transactional
public interface MemberVersionRepository extends JpaRepository<MembersVersionData, Long> {

	@Query("select count(mvd)>0 from MembersVersionData mvd WHERE mvd.guid = :guid and mvd.reportingDate = :reportingDate")
	public boolean  isExistByGuidAndReportingDate(@Param("guid") String guid,
			@Param("reportingDate") Date reportingDate);
	
	@Query(value = " select * from members_version_data where transaction_id = (select MAX(transaction_id) from members_version_data MVD where MVD.guid = :guid ) and transaction_id != 0 ; ", nativeQuery = true)
	public MembersVersionData  findMaxTransactionForGuid(@Param("guid") String guid);
	
	@Query("select mvd from MembersVersionData mvd WHERE mvd.guid = :guid and mvd.reportingDate = :reportingDate ")
	public MembersVersionData  findByGuidAndReportingDate(@Param("guid") String guid,
			@Param("reportingDate") Date reportingDate);
	
	@Query("SELECT mvd FROM MembersVersionData mvd WHERE mvd.guid = :guid and mvd.transactionId = :transactionId ")
	public MembersVersionData findByGuidAndTransactionId(@Param("guid") String guid,@Param("transactionId") long transactionId);

	@Modifying(clearAutomatically = true)
	@Query("update MembersVersionData mvd set"
			+ "  mvd.expiryDate = :expiryDate, "
			+ "  mvd.primaryGroupCode = :primaryGroupCode  "
			+ "  where mvd.guid = :guid "
			+ "  AND mvd.reportingDate = :reportingDate")
	int updateVersionData(@Param("expiryDate") Date expiryDate, 
			@Param("primaryGroupCode") String primaryGroupCode, 
			@Param("guid") String guid,
			@Param("reportingDate") Date reportingDate);
	
	@Modifying(clearAutomatically = true)
	@Query("update MembersVersionData mvd set"
			+ "  mvd.dateProcessed = :dateProcessed,  "
			+ "  mvd.transactionId = :transactionId, "
			+ "  mvd.promotionalCode = :promotionalCode,  "
			+ "  mvd.paymentType = :paymentType,  "
			+ "  mvd.amount = :amount "
			+ "  where mvd.guid = :guid "
			+ "  AND mvd.reportingDate = :reportingDate")
	int updateTransactionVersionData(
			@Param("dateProcessed") Date dateProcessed, 
			@Param("transactionId") long transactionId,
			@Param("promotionalCode") String promotionalCode,
			@Param("paymentType") String paymentType,
			@Param("amount") int amount,
			@Param("guid") String guid,
			@Param("reportingDate") Date reportingDate);
	
	
	@Modifying(clearAutomatically = true)
	@Query("update MembersVersionData mvd set"
			+ "  mvd.dateProcessed = :dateProcessed,  "
			+ "  mvd.promotionalCode = :promotionalCode,  "
			+ "  mvd.paymentType = :paymentType,  "
			+ "  mvd.amount = :amount "
			+ "  WHERE mvd.transactionId = :transactionId")
	int updatePastTransaction(
			@Param("dateProcessed") Date dateProcessed,
			@Param("promotionalCode") String promotionalCode,
			@Param("paymentType") String paymentType,
			@Param("amount") int amount,
			@Param("transactionId") long transactionId);	
}
