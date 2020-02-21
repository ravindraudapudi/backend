package com.lucida.emembler.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucida.emembler.dao.BoardMemberRepository;
import com.lucida.emembler.dao.ChapterRepository;
import com.lucida.emembler.dao.MemberRepository;
import com.lucida.emembler.dao.RoleRepository;
import com.lucida.emembler.dao.SponserRepository;
import com.lucida.emembler.dao.TransactionRepository;
import com.lucida.emembler.entity.BoardMember;
import com.lucida.emembler.entity.Chapter;
import com.lucida.emembler.entity.CsvLog;
import com.lucida.emembler.entity.FyData;
import com.lucida.emembler.entity.Member;
import com.lucida.emembler.entity.Privilege;
import com.lucida.emembler.entity.Role;
import com.lucida.emembler.entity.Sponsor;
import com.lucida.emembler.entity.Transaction;
import com.lucida.emembler.exceptions.InvalidDataException;
import com.lucida.emembler.requestdtos.BoardMemeberDto;
import com.lucida.emembler.requestdtos.EditChapter;
import com.lucida.emembler.requestdtos.PrivilegeDto;
import com.lucida.emembler.responsedtos.Response;
import com.lucida.emembler.service.ConfigService;
import com.lucida.emembler.service.TransactonService;

/**
 * @author Ravindra Controller Class to add chapter, goal, board members and
 *         fiscal year data.
 * 
 */
@RestController
@RequestMapping("/api")
public class ConfigController {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private TransactonService transactonService;

	@Autowired
	private ChapterRepository chapterRepository;

	@Autowired
	private BoardMemberRepository boardMemberRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private SponserRepository sponserRepository;

	/**
	 * To add chapter
	 * 
	 * @param chapterDto
	 * @return Response
	 */
	@PostMapping(value = "/saveChapter")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<Response> saveChapter(@RequestBody Chapter chapter) {

		try {
			if (chapterRepository.existsIfChapterName(chapter.getChapterName())) {
				return getResponse("500", "Chapter already exist", "");
			} else if (chapterRepository.existsIfPrimaryGroupCode(chapter.getPrimaryGroupCode())) {
				return getResponse("500", "Primary group code already exist", "");
			} else {
				addChapter(chapter);
				return getResponse("201", "", "Chapter Added Succesfully");
			}
		} catch (Exception e) {
			return getResponse("500", "Unable to add chapter due to service error", "");
		}
	}

	/**
	 * @get Get for all Chapters
	 * @return Response Object
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getChapters")
	public List<Chapter> getChapter() {
		return configService.getAllChapters();
	}

	/**
	 * @get Request for all Chapters
	 * @return Response Object
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getChaptersForUser/{username}")
	public Set<Chapter> getChaptersForUser(@PathVariable("username") String username) {
		return configService.getChaptersForUser(username);
	}

	/**
	 * 
	 * @param BoardMemeberDto
	 * @return Response
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/saveBoardMember")
	public ResponseEntity<Response> saveBoardMember(@RequestBody BoardMember boardMember) {

		try {
			configService.saveBoardMember(boardMember);
			return getResponse("201", "", "Board Member added succesfully");
		} catch (Exception e) {
			return getResponse("500", "Board Member not added succesfully", "");
		}
	}

	/**
	 * Get board members details
	 * 
	 * @return Email log data list
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getBoardMembers/{chapter}")
	public List<BoardMemeberDto> getBoardMembers(@PathVariable("chapter") String chapter) {
		List<BoardMember> boardMembers = boardMemberRepository.findByprimaryGroupCode(chapter);
		List<BoardMemeberDto> members = new ArrayList<BoardMemeberDto>();
		if (boardMembers != null) {
			for (BoardMember boardMember : boardMembers) {
				BoardMemeberDto boardMemeberDto = new BoardMemeberDto();
				boardMemeberDto.setId(boardMember.getId());
				boardMemeberDto.setFirstName(boardMember.getFirstName());
				boardMemeberDto.setLastName(boardMember.getLastName());
				boardMemeberDto.setApiGuid(boardMember.getApiGuid());
				boardMemeberDto.setMemberRole(boardMember.getMemberRole());
				boardMemeberDto.setOrganisation(boardMember.getOrganisation());
				boardMemeberDto.setPhone(boardMember.getPhone());
				boardMemeberDto.setEmail(boardMember.getEmail());
				boardMemeberDto.setAlternateEmail(boardMember.getAlternateEmail());

				if (memberRepository.existsIfApiGuid(boardMember.getApiGuid())) {
					Member member = memberRepository.findByApiGuid(boardMember.getApiGuid());
					boardMemeberDto.setStatus("");
					boardMemeberDto.setMembershipType(member.getMembership());
					boardMemeberDto.setExpiryDate(member.getExpiryDate());
					boardMemeberDto.setPrimaryGroupCode(member.getPrimaryGroupCode());
					boardMemeberDto.setMiddleName(member.getMiddleName());
					boardMemeberDto.setMemberSignup(member.getRegistrationDate());
					boardMemeberDto.setEmailBounced(member.getEmailBounced());
					boardMemeberDto.setGender(member.getGender());
					boardMemeberDto.setMobileAreaCode(member.getMobileAreaCode());
					boardMemeberDto.setMobile(member.getMobile());

				} else {
					boardMemeberDto.setStatus("Not A Member");
				}

				Transaction tr = transactionRepository.findMaxTransactionForGuid(boardMember.getApiGuid());

				if (tr != null) {
					boardMemeberDto.setPaymentType(tr.getPaymentType());
					boardMemeberDto.setAmount(tr.getAmount());
					boardMemeberDto.setProcessedDate(tr.getDateProcessed());
					boardMemeberDto.setPromotionalCode(tr.getSponsershipCode());

					List<Sponsor> s = sponserRepository.findByDiscountCode(tr.getSponsershipCode());

					for (Sponsor sponsor : s) {
						boardMemeberDto.setSponsor(sponsor.getSponsor());
					}
				}
				members.add(boardMemeberDto);
			}
		}
		return members;
	}

	/**
	 * Remove Board Member details
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/updateBoardMember")
	public ResponseEntity<Response> updateBoardMember(@RequestBody BoardMember boardMember) {
		try {
			configService.updateBoardMember(boardMember);
			return getResponse("200", "", "Board members has been updated");
		} catch (Exception e) {
			return getResponse("500", "Board members has not updated", "");
		}
	}


	/**
	 * Remove Board Member details
	 * 
	 * @param id
	 * @return
	 * @throws InvalidDataException
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/deleteBoardMember/{id}")
	public ResponseEntity<Response> deleteBoardMember(@PathVariable("id") int id) {
		try {
			transactonService.evictAllCaches();
			configService.deleteBoardMember(id);
			return getResponse("200", "", "Board members has been removed");
		} catch (Exception e) {
			return getResponse("500", "Board members has not removed", "");
		}
	}

	/**
	 * Updating fiscal year data
	 * 
	 * @param id
	 * @param fyData
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/updateFiscalYear/{id}")
	public ResponseEntity<Response> updateFiscalYear(@PathVariable("id") int id, @RequestBody FyData fyData) {

		try {

			configService.updateFiscalYear(id, fyData);
			transactonService.evictAllCaches();
			return getResponse("201", "", "Fiscal year data updated Succesfully");
		} catch (Exception e) {
			return getResponse("500", "Fiscal year data not updated Succesfully", "");
		}

	}

	/**
	 * Updating fiscal year data
	 * 
	 * @param id
	 * @param fyData
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/updateFiscalYearGoal/{id}")
	public ResponseEntity<Response> updateFiscalYearGoal(@PathVariable("id") int id, @RequestBody FyData fyData) {

		try {
			configService.updateFiscalYearGoal(id, fyData);
			transactonService.evictAllCaches();
			return getResponse("201", "", "Fiscal year details updated Succesfully");
		} catch (Exception e) {
			return getResponse("500", "Fiscal year not details updated Succesfully", "");
		}
	}

	/**
	 * Get Fiscal year details
	 * 
	 * @param fiscalYear
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getFiscalYears/{fiscalYear}/{username}")
	public List<FyData> getFiscalYears(@PathVariable("fiscalYear") int fiscalYear,
			@PathVariable("username") String username) {
		return configService.getFiscalYearData(fiscalYear, username);
	}

	/**
	 * Get Fiscal year details
	 * 
	 * @param fiscalYear
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getAllFiscalYears/{fiscalYear}")
	public List<FyData> getAllFiscalYears(@PathVariable("fiscalYear") int fiscalYear) {
		return configService.getFiscalYearData(fiscalYear);
	}

	/**
	 * Get All the roles
	 * 
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getRoles")
	public List<Role> getRole() {
		return configService.getRoles();
	}

	/**
	 * Get all the privilages
	 * 
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getPrevilages")
	public List<Privilege> getPrivilages() {
		return configService.getPrevilages();
	}

	/**
	 * To add chapter
	 * 
	 * @param chapterDto
	 * @return Response
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/addRole")
	public ResponseEntity<Response> addRole(@RequestBody Role role) {

		try {

			transactonService.evictAllCaches();
			if (roleRepository.existsIfRole(role.getRole())) {
				return getResponse("500", "Role already exist", "");
			} else {
				configService.addRole(role);
				transactonService.evictAllCaches();
				return getResponse("201", "", "Role added successfully.!");
			}
		} catch (Exception e) {
			return getResponse("500", "Role not added successfully.!", "");
		}
	}

	/**
	 * Update Privileges for a role
	 * 
	 * @param privilegeDto
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/updateRole/{roleName}/{id}")
	public ResponseEntity<Response> UpdateRole(@PathVariable("roleName") String roleName, @PathVariable("id") int id) {

		try {
			transactonService.evictAllCaches();
			configService.updateRole(roleName, id);
			return getResponse("200", "", "Privilage updated successfully.!");

		} catch (Exception e) {
			return getResponse("500", "Privilage not updated successfully.!", "");
		}
	}

	/**
	 * Update Privileges for a role
	 * 
	 * @param privilegeDto
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/updatePrivilages")
	public ResponseEntity<Response> updatePrivilages(@RequestBody PrivilegeDto privilegeDto) {

		try {
			transactonService.evictAllCaches();
			configService.updatePrivilages(privilegeDto);
			return getResponse("200", "", "Privilage updated successfully.!");
		} catch (Exception e) {
			return getResponse("500", "Privilage not updated successfully.!", "");
		}
	}

	/**
	 * To edit a chapter
	 * 
	 * @param id
	 * @param editChapter
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/editChapter/{id}")
	public ResponseEntity<Response> editChapter(@PathVariable int id, @RequestBody EditChapter editChapter) {

		try {
			configService.editChapter(id, editChapter);
			transactonService.evictAllCaches();
			return getResponse("200", "", "Chapter updated successfully.!");
		} catch (Exception e) {
			return getResponse("500", "Unable to update the chapter", "");
		}
	}

	/**
	 * @get Request for all Privileges for the role
	 * @param id
	 * @return
	 * @throws InvalidDataException
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getPrivilagesforRole/{id}")
	public List<Privilege> getPrivilagesForRole(@PathVariable("id") String id) {
		return configService.getPrevilagesForRole(id);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getReportedDate")
	public CsvLog getReportedDate() {
		return configService.getLastUploadedDetails();
	}

	/**
	 * Add Chapters with last three years fiscal year data
	 * 
	 * @param chapter
	 * @throws InvalidDataException
	 */
	public void addChapter(Chapter chapter) {
		List<FyData> list = fiscalYearSetUp(chapter);
		chapter.setFyData(list);
		configService.saveChapter(chapter);
		transactonService.evictAllCaches();
	}

	/**
	 * To set default fiscal years in order to set goals, fiscal year and profit and
	 * loss statement
	 * 
	 * @param chapter
	 * @return
	 */
	private List<FyData> fiscalYearSetUp(Chapter chapter) {

		List<FyData> fyList = new ArrayList<FyData>();

		if (chapter.getType().equalsIgnoreCase("PROFESSIONAL")) {
			for (int i = 2016; i <= 2024; i++) {
				FyData fyData = new FyData();
				fyData.setFiscalYear(i);
				fyData.setPrimaryGroupCode(chapter.getPrimaryGroupCode());
				fyData.setChapterName(chapter.getChapterName());
				fyData.setType(chapter.getType().toUpperCase());
				fyList.add(fyData);
			}
		}
		return fyList;
	}

	/**
	 * Remove Board Member details
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(value = "/deleteChapter/{id}")
	public ResponseEntity<Response> deleteChapter(@PathVariable("id") int id) {
		try {
			configService.deleteChapter(id);
			transactonService.evictAllCaches();
			return getResponse("200", "", "Chapter has been removed");
		} catch (Exception e) {
			return getResponse("500", "Unable to delete the chapter", "");
		}
	}
	
	/**
	 * 
	 * @param statusCode
	 * @param errorDescription
	 * @param successDescription
	 * @return
	 */
	private ResponseEntity<Response> getResponse(String statusCode, String errorDescription,
			String successDescription) {
		Response response = new Response();
		response.setSuccessDescription(successDescription);
		response.setErrorDescrition(errorDescription);
		response.setResponseStatus(statusCode);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
