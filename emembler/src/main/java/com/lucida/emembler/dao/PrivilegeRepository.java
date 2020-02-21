package com.lucida.emembler.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

	Privilege findByPrivilege(String privilegeName);

	@Query(value = "select * from privilege order by privilege;", nativeQuery = true)
	List<Privilege> orderPrivielgesByName();

}
