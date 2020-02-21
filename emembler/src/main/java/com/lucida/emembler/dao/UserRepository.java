package com.lucida.emembler.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.User;

/**
 * 
 * @author Ravindra
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select password from User where username =?1")
	String getPassword(String username);
	
	User findByUsername(String username);
	
	@Query("select count(u)>0 from User u where u.username =:username")
	public boolean existsIfUsername(@Param("username") String username);
	
	@Query("select count(u)>0 from User u where u.password =:password")
	public boolean existsIfPassword(@Param("password") String password);
	
	@Query("select count(u)>0 from User u where u.username =:username AND u.password =:password")
	public boolean existsPasswordForUser(@Param("username") String username,@Param("password") String password);
	
	@Query("SELECT u FROM User u WHERE u.username=:username")
	List<User> findByUniqueFields(@Param("username") String username);
}
