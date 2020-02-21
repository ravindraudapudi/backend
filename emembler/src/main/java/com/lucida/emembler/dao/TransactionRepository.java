package com.lucida.emembler.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucida.emembler.entity.Transaction;

/**
 * 
 * @author
 *
 */
@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Transaction findByMemberApiGuid(String MemberApiGuid);
	
	@Query(value = " select * from transaction where " + 
			" transaction_id = (Select max(transaction_id) from transaction where member_api_guid = :memberApiGuid ); ", nativeQuery = true)
	public Transaction findMaxTransactionForGuid(@Param("memberApiGuid") String memberApiGuid);

	@Query("select t from Transaction t where t.transactionId = :transactionId")
	public Transaction findByTransactionId(@Param("transactionId") long transactionId);
	
	@Query("select count(t)>0 from Transaction t where t.transactionId = :transactionId")
	public boolean ifTransactionExist(@Param("transactionId") long transactionId);
	
	@Query( value = "select * from transaction where member_api_guid =:member_api_guid order by transaction_id desc limit 1;", nativeQuery = true)
	public Transaction findLatestTransactionForMember(@Param("member_api_guid") long member_api_guid);
	
	
	@Query( value = "select * from transaction where member_api_guid =:member_api_guid order by transaction_id desc limit 1;", nativeQuery = true)
	public Transaction findLatestCodeRecord(@Param("member_api_guid") String member_api_guid);
	


}