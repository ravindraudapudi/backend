package com.lucida.emembler.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.BoardMember;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Integer>  {
	
	BoardMember findByApiGuid(String apiGuid);
	
	@Query("select count(b)>0 from BoardMember b where b.apiGuid = :apiGuid")
	public boolean existsIfGuid(@Param("apiGuid") String apiGuid);
	
	@Query("select count(b)>0 from BoardMember b where b.email = :email")
	public boolean existsIfEmail(@Param("email") String email);
	
	@Query("select b from BoardMember b where b.primaryGroupCode = :primaryGroupCode")
	List<BoardMember>findByprimaryGroupCode(@Param("primaryGroupCode") String primaryGroupCode);
	
}
