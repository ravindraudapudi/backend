package com.lucida.emembler.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucida.emembler.entity.Chapter;
/**
 *  Repository class for Chapters
 * @author Lucida
 *
 */
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

	Chapter findByChapterName(String chapterName);
	
	Chapter findByPrimaryGroupCode(String code);
	
	@Query("select c from Chapter c where c.type = 'Professional'")
	public List<Chapter> getProfessionalChapters();

	Optional<Chapter> findById(int id);
	
	@Query("select count(c)>0 from Chapter c where c.chapterName = :chapterName")
	public boolean existsIfChapterName(@Param("chapterName") String chapterName);
	
	@Query("select count(c)>0 from Chapter c where c.primaryGroupCode = :primaryGroupCode")
	public boolean existsIfPrimaryGroupCode(@Param("primaryGroupCode") String primaryGroupCode);
	
	@Query(value ="select * from chapter where primary_group_code not in (:primaryGroupCode)", nativeQuery = true)
	public List<Chapter> chapters(@Param("primaryGroupCode") String primaryGroupCode);
	
	@Modifying(clearAutomatically = true)
	@Query("update Chapter chapter set"
			+ " chapter.chapterName = :chapterName , "
			+ " chapter.primaryGroupCode = :primaryGroupCode, "
			+ " chapter.type = :type, "
			+ " chapter.associatedWith = :associatedWith, "
			+ " chapter.chapterEmail = :chapterEmail, "
			+ " chapter.chapterPassWord = :chapterPassWord, "
			+ " chapter.signature = :signature "
			+ "  where chapter.id = :id")
	int updateChapter(@Param("chapterName") String chapterName,  
			@Param("primaryGroupCode") String primaryGroupCode, 
			@Param("type") String type, 
			@Param("associatedWith") String associatedWith, 
			@Param("chapterEmail") String chapterEmail,
			@Param("chapterPassWord") String chapterPassWord,
			@Param("signature") String signature,
			@Param("id") int id);
}