package com.lucida.emembler.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.Role;

/**
 * 
 * @author Ravindra
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	/*
	 * @param role roleName
	 * 
	 *@return Role for the given role name
	 */
	Role findByRole(String role);
	
	@Query("select count(c)>0 from Role c where c.role = :roleName")
	public boolean existsIfRole(@Param("roleName") String roleName);


}
