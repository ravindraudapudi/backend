package com.lucida.emembler.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Integer> { 
	
}
