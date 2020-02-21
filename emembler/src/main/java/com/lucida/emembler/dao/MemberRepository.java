package com.lucida.emembler.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucida.emembler.entity.Member;

/**
 * 
 * @author Lucida
 *
 */
@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findByApiGuid(String apiGuid);
	
	@Query("select m.expiryDate from Member m where m.apiGuid = :api_guid")
	public Date getExpiryDate(@Param("api_guid") String api_guid);

	/**
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getMembersForChapter(:primaryGroupCode )}", nativeQuery = true)
	List<Member> findByPrimaryGroupCode(@Param("primaryGroupCode") String primaryGroupCode);

	/**
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getTotalActiveMembershipByChapter(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	int getActiveMembersCount(@Param("reportDate") Date reportDate, @Param("fiscalEndDate") Date fiscalEndDate,
			@Param("primaryGroupCode") String primaryGroupCode);

	/**
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param primaryGroupCode
	 * @return Paid members count for the fiscal year
	 */
	@Query(value = "{call getPaidMembershipByChapter(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	int findCreditCardMemebrsCount(@Param("reportDate") Date reportDate, @Param("fiscalEndDate") Date fiscalEndDate,
			@Param("primaryGroupCode") String primaryGroupCode);

	/**
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param sponserLevel
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getLocalSponsorMembershipByChapter(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	int getLocalSponsorMembershipByChapter(@Param("reportDate") Date reportDate,
			@Param("fiscalEndDate") Date fiscalEndDatel,
			@Param("primaryGroupCode") String primaryGroupCode);
	
	
	/**
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param sponserLevel
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getNationalSponsorMembershipByChapter(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	int getNationalSponsorMembershipByChapter(@Param("reportDate") Date reportDate,
			@Param("fiscalEndDate") Date fiscalEndDate,@Param("primaryGroupCode") String primaryGroupCode);
	
	
	
	/**
	 * Credit card Members
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getPaidMembers(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	List<Member> findCreditCardMemebrs(@Param("reportDate") Date reportDate, @Param("fiscalEndDate") Date fiscalEndDate,
			@Param("primaryGroupCode") String primaryGroupCode);


	/**
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param sponserLevel
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getLocalSponsorMembers(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	List<Member> getLocalSponsorMembers(@Param("reportDate") Date reportDate,
			@Param("fiscalEndDate") Date fiscalEndDate,@Param("primaryGroupCode") String primaryGroupCode);

	
	/**
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param sponserLevel
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getNationalSponsorMembers(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	List<Member> getNationalSponsorMembers(@Param("reportDate") Date reportDate,
			@Param("fiscalEndDate") Date fiscalEndDate, @Param("primaryGroupCode") String primaryGroupCode);

	
	
	/**
	 * To get other Members
	 * 
	 * @param sponser
	 * @param primary_group_code
	 * @return
	 */
	@Query(value = "{call getOtherMembers(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	List<Member> findOtherMembers(@Param("reportDate") Date reportDate, @Param("fiscalEndDate") Date fiscalEndDate,
			@Param("primaryGroupCode") String primaryGroupCode);

	
	/**
	 * Monthly paid active members
	 * 
	 * @param month
	 * @param year
	 * @param primGroupCode
	 * @param memberType
	 * @return
	 */
	

	
	@Query(value = "{call getMonthlyPaidActiveMembers(:year, :month, :reportDate, :primaryGroupCode )}", nativeQuery = true)
	List<Member> findMonthlyPaidActiveMembers(@Param("year") int year, @Param("month") int month,@Param("reportDate") Date reportDate,@Param("primaryGroupCode") String primaryGroupCode);
	
	/**
	 * Monthly expired paid members
	 * 
	 * @param month
	 * @param year
	 * @param primGroupCode
	 * @param memberType
	 * @return
	 */
	@Query(value = "{call getMonthlyPaidExpiredMembers(:year, :month,:reportDate, :primaryGroupCode )}", nativeQuery = true)
	List<Member> findMonthlyPaidExpiredMembers(@Param("year") int year, @Param("month") int month,
			@Param("reportDate") Date reportDate, @Param("primaryGroupCode") String primaryGroupCode);

	
	/**
	 * To get Monthly Membership Revenue
	 * 
	 * @param sponser
	 * @param primary_group_code
	 * 
	 */
	@Query(value = "{call getMonthlyMembershipRevenue(:year, :month,:reportDate, :primaryGroupCode )}", nativeQuery = true)
	Integer findMonthlyMemebrshipRevenue(@Param("year") int year, @Param("month") int month,
			@Param("reportDate") Date reportDate, @Param("primaryGroupCode") String primaryGroupCode);
		
	/**
	 * To get Expired Members count for fiscal year
	 * 
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getExpiredMembersInCurrentFy(:startDate,:reportDate, :primaryGroupCode)}",  nativeQuery = true)
	public int getExpiringMembersCountForFiscal(@Param("startDate") Date startDate,
			@Param("reportDate") Date reportDate, @Param("primaryGroupCode") String primaryGroupCode);

	/**
	 * To get Members for the redeemed code
	 * 
	 * @param sponser
	 * @param primary_group_code
	 * @return
	 */
	@Query(value = "{call getMembersForSponsorshipCode(:sponsosrhipCode)}",  nativeQuery = true)
	List<Member> getMembersForSponsorhipCode(@Param("sponsosrhipCode") String sponsosrhipCode);
	
	/**
	 * Check Whether the Member exist
	 * 
	 * @param apiGuid
	 * @return
	 */
	@Query("select count(m)>0 from Member m where m.apiGuid = :apiGuid")
	public boolean existsIfApiGuid(@Param("apiGuid") String apiGuid);

	/**
	 * Retrieve Member status
	 * 
	 * @param apiGuid
	 * @return
	 */
	@Query("select count(m)>0 from Member m where m.apiGuid = :apiGuid " 
	 + "and m.expiryDate >= CURRENT_DATE")
	public boolean isMemebrActive(@Param("apiGuid") String apiGuid);
	
	
	/**
	 * 
	 * @param reportDate
	 * @param fiscalEndDate
	 * @param primaryGroupCode
	 * @return
	 */
	@Query(value = "{call getTotalActiveMembers(:reportDate, :fiscalEndDate, :primaryGroupCode )}", nativeQuery = true)
	List<Member> getTotalActiveMembersDetails(@Param("reportDate") Date reportDate, @Param("fiscalEndDate") Date fiscalEndDate,
			@Param("primaryGroupCode") String primaryGroupCode);
	
}