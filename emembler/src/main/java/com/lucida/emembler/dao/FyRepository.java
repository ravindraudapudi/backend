package com.lucida.emembler.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucida.emembler.entity.FyData;

@Repository
public interface FyRepository extends JpaRepository<FyData, Integer> {

	Optional<FyData> findById(int id);

	@Query("SELECT fy FROM FyData fy WHERE fy.fiscalYear = :fiscalYear")
	List<FyData> findByFiscalYear(@Param("fiscalYear") int year);

	@Query("SELECT fy FROM FyData fy WHERE fy.primaryGroupCode = :primaryGroupCode")
	List<FyData> findByPrimaryGroupCode(@Param("primaryGroupCode") String primaryGroupCode);

	@Query("SELECT fy FROM FyData fy WHERE fy.chapterName = :chapterName and fy.fiscalYear = :fiscalYear")
	FyData findByFiscalYearAndChapter(@Param("chapterName") String chapterName, @Param("fiscalYear") int fiscalYear);

	@Query("SELECT fy FROM FyData fy WHERE fy.primaryGroupCode = :primaryGroupCode and fy.fiscalYear = :fiscalYear")
	FyData findByFiscalYearAndPrimaryCode(@Param("primaryGroupCode") String primaryGroupCode,
			@Param("fiscalYear") int fiscalYear);

	@Query("select count(fy)>0 from FyData fy WHERE fy.primaryGroupCode = :primaryGroupCode and fy.fiscalYear = :fiscalYear")
	public boolean existIfFiscalYearAndPrimaryCode(@Param("primaryGroupCode") String primaryGroupCode,
			@Param("fiscalYear") int fiscalYear);
	
	@Query(value = "select distinct fiscal_year from fy_data;", nativeQuery = true)
	public List<Integer> findUniqueFiscalYears();
	
	@Modifying
	@Transactional
	public void deleteByprimaryGroupCode(String primaryGroupCode);

	/**
	 * Update Fiscal year data
	 * 
	 * @param expiryDate
	 * @param apiGuid
	 */
	@Modifying(clearAutomatically = true)
	@Query("update FyData fy set " + " fy.totalActiveMembership = :totalActiveMembership, "
			+ " fy.paidMembership = :paidMembership, " + " fy.localSponseredMembership = :localSponseredMembership, "
			+ " fy.nationalSponseredMembership = :nationalSponseredMembership, " + " fy.totalRevenue = :totalRevenue "
			+ " where fy.id = :id")
	public void updateFyData(@Param("totalActiveMembership") int totalActiveMembership,
			@Param("paidMembership") int paidMembership,
			@Param("localSponseredMembership") int localSponseredMembership,
			@Param("nationalSponseredMembership") int nationalSponseredMembership,
			@Param("totalRevenue") double totalRevenue, @Param("id") int id);

	/**
	 * Update Fiscal year Goal data
	 * 
	 * @param expiryDate
	 * @param apiGuid
	 */
	@Modifying(clearAutomatically = true)
	@Query("update FyData fy set " + " fy.totalActiveMembershipGoal = :totalActiveMembershipGoal, "
			+ " fy.paidMembershipGoal = :paidMembershipGoal, "
			+ " fy.localSponseredMembershipGoal = :localSponseredMembershipGoal, "
			+ " fy.nationalSponsoredMembershipGoal = :nationalSponsoredMembershipGoal, "
			+ " fy.totalRevenueGoal = :totalRevenueGoal, " + " fy.membershipRevenueGoal = :membershipRevenueGoal, "
			+ " fy.programRevenueGoal = :programRevenueGoal, " 
			+ " fy.registrationEventsGoal = :registrationEventsGoal, "
			+ " fy.expirationRateGoal = :expirationRateGoal, "
			+ " fy.otherRevenueGoal = :otherRevenueGoal "
			+ " where fy.id = :id")
	public void updateFiscalYearGoal(@Param("totalActiveMembershipGoal") int totalActiveMembershipGoal,
			@Param("paidMembershipGoal") int paidMembershipGoal,
			@Param("localSponseredMembershipGoal") int localSponseredMembershipGoal,
			@Param("nationalSponsoredMembershipGoal") int nationalSponsoredMembershipGoal,
			@Param("totalRevenueGoal") double totalRevenueGoal,
			@Param("membershipRevenueGoal") double membershipRevenueGoal,
			@Param("programRevenueGoal") double programRevenueGoal,
			@Param("registrationEventsGoal") double registrationEventsGoal,
			@Param("expirationRateGoal") double expirationRateGoal,
			@Param("otherRevenueGoal") double otherRevenueGoal,
			@Param("id") int id);

	/**
	 * Update Fiscal year Profit and loss data
	 * 
	 * @param expiryDate
	 * @param apiGuid
	 */
	@Modifying(clearAutomatically = true)
	@Query("update FyData fy set " + 
			  " fy.totalRevenue = :totalRevenue, " 
			+ " fy.programRevenue = :programRevenue, "
			+ " fy.membershipIncome = :membershipIncome, " 
			+ " fy.contributionRevenue = :contributionRevenue, "
			+ " fy.registrationEvents = :registrationEvents, "
			+ " fy.revenueTerm = :revenueTerm " 
//			+ " fy.kindDonations = :lifeTimeMembership, "
//			+ " fy.lifeTimeMembership = :kindDonations "
			+ " where fy.id = :id")
	public void updateFiscalYearProfitLoss(
			@Param("totalRevenue") double totalRevenue,
			@Param("programRevenue") double programRevenue, 
			@Param("membershipIncome") double membershipIncome,
			@Param("contributionRevenue") double contributionRevenue,
			@Param("registrationEvents") double registrationEvents,
			@Param("revenueTerm") String revnueTerm,
//			@Param("lifeTimeMembership") double lifeTimeMembership,
//			@Param("kindDonations") double kindDonations,
			@Param("id") int id);

	/**
	 * Update Fiscal year Profit and loss data
	 * 
	 * @param expiryDate
	 * @param apiGuid
	 */
	@Modifying(clearAutomatically = true)
	@Query("update FyData fy set " + " fy.chapterName = :chapterName, " + " fy.type = :type,"
			+ " fy.primaryGroupCode = :primaryGroupCode " + " where fy.id = :id")
	public void updateChpaterDetails(@Param("chapterName") String chapterName, @Param("type") String type,
			@Param("primaryGroupCode") String primaryGroupCode, @Param("id") int id);
}