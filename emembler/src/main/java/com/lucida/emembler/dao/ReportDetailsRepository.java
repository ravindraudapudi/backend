package com.lucida.emembler.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.ReportDetails;

@Repository
public interface ReportDetailsRepository extends JpaRepository<ReportDetails, Integer> {

}
