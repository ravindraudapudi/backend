package com.lucida.emembler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lucida.emembler.dao.BoardMemberRepository;
import com.lucida.emembler.dao.ChapterRepository;
import com.lucida.emembler.dao.FyRepository;
import com.lucida.emembler.dao.LogRepository;
import com.lucida.emembler.dao.PrivilegeRepository;
import com.lucida.emembler.dao.RoleRepository;
import com.lucida.emembler.dao.UserRepository;
import com.lucida.emembler.entity.BoardMember;
import com.lucida.emembler.entity.Chapter;
import com.lucida.emembler.entity.CsvLog;
import com.lucida.emembler.entity.FyData;
import com.lucida.emembler.entity.Privilege;
import com.lucida.emembler.entity.Role;
import com.lucida.emembler.entity.User;
import com.lucida.emembler.exceptions.InvalidDataException;
import com.lucida.emembler.requestdtos.EditChapter;
import com.lucida.emembler.requestdtos.PrivilegeDto;
import com.lucida.emembler.web.UserController;

/**
 * 
 * @author Lucida Configuration Service to set up, chapter, goals, fiscal year,
 *         board member and administrator mail id
 * 
 */
@Service
@Transactional
public class ConfigService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TransactonService transactonService;

	@Autowired
	private ChapterRepository chapterRepository;

	@Autowired
	private BoardMemberRepository boardMemberRepository;

	@Autowired
	private FyRepository fyRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LogRepository logRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Initial Default Role Create
	 */
	public void initPrivilages() {

		try {

			Map<String, String> privilages = new HashMap<String, String>();
			
			// User management 
			
			privilages.put("National View", "NATIONAL");
			privilages.put("Chapter View", "CHAPTER");
			privilages.put("Allow User Creation", "NATIONAL");
			privilages.put("Set up Roles and Privileges", "NATIONAL");
			
			
			// Configs
			
			privilages.put("Allow Chapter Creation", "NATIONAL");
			privilages.put("Set up Pipeline stages", "CHAPTER");
			
			
			// Opportunity
			
			privilages.put("View  National Opportunities", "NATIONAL");
			privilages.put("View Chapter Opportunity", "CHAPTER");
			privilages.put("Manage Chapter Opportunity", "CHAPTER");
			privilages.put("Manage National Opportunities", "NATIONAL");
			
			
			
			privilages.put("Delete Chapter Opportunity", "CHAPTER");
			privilages.put("Delete National Opportunity", "NATIONAL");
			
			//privilages.put("Manage Opportunity", "CHAPTER");
			
			
			// privilages.put("Allow Upload files", "NATIONAL");   
			// privilages.put("Export Charts and Reports", "CHAPTER");
			//privilages.put("Allow User to Set a Fy Data", "NATIONAL");
		//	privilages.put("Visualise", "CHAPTER");
		//	privilages.put("Export Charts and Reports", "CHAPTER");
		//	privilages.put("Allow User to Send an Email", "CHAPTER");
		//	privilages.put("Allow User to Set a Goal", "NATIONAL");
		
		
			
		//	privilages.put("View Board Members", "CHAPTER");
		//	privilages.put("Manage Board Members", "CHAPTER");
			
//			privilages.put("View Opportunities", "CHAPTER");
//			privilages.put("Manage Opportunities", "CHAPTER");
			
		//	privilages.put("View Sponsors", "CHAPTER"); 
		//	privilages.put("Manage Sponsors", "CHAPTER");
			
		//	privilages.put("View Overview", "CHAPTER");
		//	privilages.put("View Memberships", "CHAPTER");
		//	privilages.put("View Detail Metrics", "CHAPTER");
		//	privilages.put("View Social Media", "CHAPTER");
		//	privilages.put("View Total active members", "NATIONAL");
	    //	privilages.put("View Paid members", "NATIONAL");
		//	privilages.put("View National sponsored members", "NATIONAL");
		//	privilages.put("View Local sponsored members", "NATIONAL");
		//	privilages.put("View Total revenue", "CHAPTER");
			
			
			if (privilegeRepository.findAll().size() == 0) {
				for (Map.Entry<String, String> entry : privilages.entrySet()) {
					Privilege privilege = new Privilege();
					privilege.setPrivilege(entry.getKey());
					privilege.setIsNational(entry.getValue());
					privilegeRepository.save(privilege);
				}
				privilegeRepository.flush();
			}
		} catch (Exception e) {
			logger.debug("Exception occured while setting the default roles and previlages", e);
		}
	}

	/**
	 * Initial Default Role Create
	 */
	public void initRolesAssignPrivilage() {

		try {

			List<Privilege> list = privilegeRepository.findAll();

			if (roleRepository.findAll().size() == 0) {

				Role r = new Role();
				r.setRole("SuperAdmin");
				r.setPrivilages(list);
				roleRepository.save(r);
				roleRepository.flush();
			}
		} catch (Exception e) {
			logger.debug("Exception occured while setting the default roles and previlages", e);
		}
	}

	/**
	 * to get list of Roles
	 * 
	 * @return List of Roles
	 */
	@Cacheable("getRoles")
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	/**
	 * Add a chapter data
	 * 
	 * @param chapter
	 */
	public void saveChapter(Chapter chapter) {

		try {

			if (chapter.getAssociatedWith() != null) {
				chapter.setAssociatedWith(chapter.getAssociatedWith().toUpperCase());
			}

			if (chapter.getPrimaryGroupCode() != null) {
				chapter.setPrimaryGroupCode(chapter.getPrimaryGroupCode().toUpperCase());
			}

			if (chapter.getType() != null) {
				chapter.setType(chapter.getType().toUpperCase());
			}

			chapterRepository.save(chapter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * delete a chapter
	 * 
	 * @param id
	 */
	public void deleteChapter(int id) {
		Optional<Chapter> c = chapterRepository.findById(id);
		if (c.isPresent()) {
			Chapter chapter = c.get();
			for (User user : chapter.getUsers()) {
				user.getChapters().remove(chapter);
			}
			chapterRepository.deleteById(id);
			this.chapterRepository.flush();

			this.fyRepository.deleteByprimaryGroupCode(chapter.getPrimaryGroupCode());
			this.fyRepository.flush();
		}

	}

	/**
	 * 
	 * @param id
	 * @param user
	 */
	public void updateUser(int id, Chapter chapter) {
		Optional<Chapter> chapterTemp = chapterRepository.findById(id);
		if (chapterTemp.isPresent()) {
			chapterRepository.save(chapter);
		}
		chapterRepository.flush();
	}

	/**
	 * 
	 * @return list of Chapters
	 */
	@Cacheable("getAllChapters")
	public List<Chapter> getAllChapters() {
		return chapterRepository.findAll();
	}

	/**
	 * 
	 * @return list of Chapters
	 */
	@Cacheable("getChapter")
	public List<Chapter> getChapter() {
		return chapterRepository.getProfessionalChapters();
	}

	/**
	 * 
	 * @return list of Chapters
	 */
	@Cacheable("getChaptersForUser")
	public Set<Chapter> getChaptersForUser(String username) {
		if (userRepository.findByUsername(username) != null) {
			return userRepository.findByUsername(username).getChapters();
		} else {
			return null;
		}
	}

	/**
	 * Saving board membership data into database
	 * 
	 * @param memeberships
	 * @throws ServiceException
	 * @throws InvalidDataException
	 */
	public void saveBoardMember(BoardMember boardMember) {
		boardMemberRepository.save(boardMember);
	}

	@Cacheable("getBoardMemebrs")
	public List<BoardMember> getBoardMemebrs() {
		return boardMemberRepository.findAll();
	}

	/**
	 * deleting user from database
	 * 
	 * @param id
	 */
	public void deleteBoardMember(int id) {
		boardMemberRepository.deleteById(id);
	}

	/**
	 * deleting user from database
	 * 
	 * @param id
	 */
	public void updateBoardMember(BoardMember boardMember) {
		boardMemberRepository.save(boardMember);
	}

	/**
	 * Add a Fiscal year data
	 * 
	 * @param fiscalYearDto
	 */
	public void updateFiscalYear(int id, FyData fyData) {
		transactonService.evictAllCaches();
		fyRepository.updateFyData(fyData.getTotalActiveMembership(), fyData.getPaidMembership(),
				fyData.getLocalSponseredMembership(), fyData.getNationalSponseredMembership(), fyData.getTotalRevenue(),
				id);
	}

	/**
	 * Add a Fiscal year data
	 * 
	 * @param fiscalYearDto
	 */
	public void updateFiscalYearGoal(int id, FyData fyData) {
		transactonService.evictAllCaches();
		fyRepository.updateFiscalYearGoal(fyData.getTotalActiveMembershipGoal(), fyData.getPaidMembershipGoal(),
				fyData.getLocalSponseredMembershipGoal(), fyData.getNationalSponsoredMembershipGoal(),
				fyData.getTotalRevenueGoal(), fyData.getMembershipRevenueGoal(), fyData.getProgramRevenueGoal(),
				fyData.getRegistrationEventsGoal(), fyData.getExpirationRateGoal(), fyData.getOtherRevenueGoal(), id);
	}

	/**
	 * deleting user
	 * 
	 * @param id
	 */
	public void deleteFyData(int id) {
		fyRepository.deleteById(id);
	}

	/**
	 * To get fiscal years for a given user
	 * 
	 * @param year
	 * @param username
	 * @return
	 */
	@Cacheable("getFiscalYearData")
	public List<FyData> getFiscalYearData(int year, String username) {
		List<FyData> fiscalYears = new ArrayList<FyData>();
		User user = userRepository.findByUsername(username);
		if (user != null) {
			for (Chapter chapter : user.getChapters()) {
				FyData fyData = fyRepository.findByFiscalYearAndPrimaryCode(chapter.getPrimaryGroupCode(), year);
				fiscalYears.add(fyData);
			}
		}
		return fiscalYears;
	}

	/**
	 * To get fiscal years for a given user
	 * 
	 * @param year
	 * @param username
	 * @return
	 */
	@Cacheable("getFiscalYearData")
	public List<FyData> getFiscalYearData(int year) {
		return fyRepository.findByFiscalYear(year);
	}

	/**
	 * To get list of privileges
	 * 
	 * @return
	 */

	public List<Privilege> getPrevilages() {
		return privilegeRepository.orderPrivielgesByName();
	}

	/**
	 * TO get privileges for a role
	 * 
	 * @param id
	 * @return
	 */
	public List<Privilege> getPrevilagesForRole(String id) {
		Role role = roleRepository.findByRole(id);
		List<Privilege> list = null;
		if (role != null) {
			list = role.getPrivilages();
		}
		return list;
	}

	/**
	 * Add a Role data
	 * 
	 * @param Role
	 */
	public void addRole(Role role) throws InvalidDataException {
		Privilege privilege = privilegeRepository.findByPrivilege("Visualise");
		List<Privilege> previlages = new ArrayList<Privilege>();
		previlages.add(privilege);
		role.setPrivilages(previlages);
		roleRepository.save(role);

	}

	/**
	 * Update a privileges to each chapter
	 * 
	 * @param chapter
	 */
	public void updateRole(String roleName, int id) throws InvalidDataException {
		Optional<Role> role = roleRepository.findById(id);
		if (role.isPresent()) {
			Role r = role.get();
			r.setRole(roleName);
			roleRepository.save(r);
		}
		roleRepository.flush();
	}

	/**
	 * Add a chapter data
	 * 
	 * @param chapter
	 */
	public void addPrevilage(Privilege privilage) throws InvalidDataException {
		privilegeRepository.save(privilage);
	}

	/**
	 * Update a privileges to each chapter
	 * 
	 * @param chapter
	 */
	public void updatePrivilages(PrivilegeDto privilegeDto) throws InvalidDataException {
		List<Privilege> privilages = new ArrayList<Privilege>();

		for (String value : privilegeDto.getPrivileges()) {
			Privilege privilege = privilegeRepository.findByPrivilege(value);
			privilages.add(privilege);
		}

		Role role = roleRepository.findByRole(privilegeDto.getRole());
		role.setPrivilages(privilages);
		roleRepository.save(role);
		roleRepository.flush();

	}

	/**
	 * update chapter details
	 * 
	 * @param chapter
	 */
	public void editChapter(int id, EditChapter editChapter) throws InvalidDataException {
		transactonService.evictAllCaches();
		chapterRepository.updateChapter(editChapter.getChapterName(), editChapter.getPrimaryGroupCode(),
				editChapter.getType(), editChapter.getAssociatedWith(), editChapter.getChapterEmail(),
				editChapter.getChapterPassWord(),editChapter.getSignature(), id);
		
		chapterRepository.flush();

		Optional<Chapter> chapterTemp = chapterRepository.findById(id);

		if (chapterTemp.isPresent()) {
			Chapter chapter = chapterTemp.get();
			for (FyData fyData : chapter.getFyData())
				fyRepository.updateChpaterDetails(editChapter.getChapterName(), editChapter.getType(),
						editChapter.getPrimaryGroupCode(), fyData.getId());
			fyRepository.flush();
		}
	}

	/**
	 * Duplicate check for user Id
	 * 
	 * @param username
	 * @throws InvalidDataException
	 */
	public boolean isDuplicateChapter(String chapter) throws InvalidDataException {
		return chapterRepository.existsIfChapterName(chapter);
	}

	/**
	 * Duplicate check for user Id
	 * 
	 * @param username
	 * @throws InvalidDataException
	 */
	public boolean isDuplicatePrimaryCode(String code) throws InvalidDataException {
		return chapterRepository.existsIfPrimaryGroupCode(code);

	}

	/**
	 * Get Last Uploaded details
	 * 
	 * @return
	 */
	@Cacheable("getLastUploadedDetails")
	public CsvLog getLastUploadedDetails() {
		return logRepository.getLastUploadedDetails();
	}
}
