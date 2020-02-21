package com.lucida.emembler.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.CsvLog;

/**
 * 
 * @author Ravindra
 *
 */
@Repository
public interface LogRepository extends JpaRepository<CsvLog, Integer> {
	
	@Query(value = "select * from csv_log " 
			+ " where doc_type in ('TL','MD') "
			+ " ORDER BY uploaded_on DESC LIMIT 1; ", nativeQuery = true)
	public CsvLog getLastUploadedDetails();
	
	@Query(value = " SELECT * from csv_log  where doc_type in ('MD') and "
			+ " reporting_date is not null ORDER BY reporting_date DESC ; ", nativeQuery = true)
	public List<CsvLog> getReportingDates();
	
	@Query(value = " SELECT MAX(reporting_date) from csv_log  where doc_type in ('MD') and "
			+ " reporting_date is not null and DATE(reporting_date) <= :fiscalEndDate ; ", nativeQuery = true)
	public Date getMaxReportingDate(@Param("fiscalEndDate") Date fiscalEndDate);
	
	@Query("select count(cl)>0 from CsvLog cl where cl.docType in ('MD') AND reportingDate = :reportingDate")
	public boolean isDLUploaded(@Param("reportingDate") Date reportingDate);
	
	
	@Query(value = " SELECT MAX(reporting_date) from csv_log  where doc_type in ('MD') and "
			+ " reporting_date is not null", nativeQuery = true)
	public Date getMaxReportDate();
	
	
	@Query(value = " SELECT * from csv_log  where doc_type in ('MD') and "
			+ " reporting_date is not null and reporting_date between :startDt "
			+ " and :endDt ORDER BY reporting_date DESC ; ", nativeQuery = true)
	public Set<CsvLog> getReportingDatesforfiscalyear(@Param("startDt") Date startDt,@Param("endDt") Date endDt);
	
}


