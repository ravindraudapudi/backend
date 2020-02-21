package com.lucida.emembler.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.SocialMedia;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Integer> {

	@Query(value= "select * from social_media where MONTH(monthly_date) = :month "
			+ " AND YEAR(monthly_date) = :year "
			+ "AND primary_group_code = :primaryGroupCode", nativeQuery=true)
	public List<SocialMedia> findByMonthlyDtAndCode(@Param("month") int month,
			@Param("year") int year,
			@Param("primaryGroupCode") String primaryGroupCode);
	
	@Query(value= "select count(*)>0 from social_media where MONTH(monthly_date) = :month "
			+ " AND YEAR(monthly_date) = :year "
			+ "AND primary_group_code = :primaryGroupCode", nativeQuery=true)
	public boolean ifMonthlyMediaExist(@Param("month") int month,
			@Param("year") int year,
			@Param("primaryGroupCode") String primaryGroupCode);
	
	@Query(value = " select * from social_media where "
			+ " primary_group_code = :primaryGroupCode order by monthly_date", nativeQuery=true)
	public List<SocialMedia> findForFiscalYear(@Param("primaryGroupCode") String primaryGroupCode);
}
