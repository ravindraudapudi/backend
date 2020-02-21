package com.lucida.emembler.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucida.emembler.dao.ChapterRepository;
import com.lucida.emembler.dao.RoleRepository;
import com.lucida.emembler.dao.UserRepository;
import com.lucida.emembler.entity.Chapter;
import com.lucida.emembler.entity.Role;
import com.lucida.emembler.entity.User;
import com.lucida.emembler.exceptions.InvalidDataException;
import com.lucida.emembler.requestdtos.ForgotPasswordDto;
import com.lucida.emembler.requestdtos.MemberEmailDto;
import com.lucida.emembler.requestdtos.PasswordResetDto;
import com.lucida.emembler.requestdtos.StatusDto;
import com.lucida.emembler.requestdtos.UserDto;
import com.lucida.emembler.responsedtos.Response;
import com.lucida.emembler.service.TransactonService;
import com.lucida.emembler.service.UserService;

/**
 * 
 * @author Lucida User management Module
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ChapterRepository chapterRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactonService transactonService;

	/**
	 * To add User
	 * 
	 * @param userDto
	 * @return
	 */
	@PostMapping(value = "/api/addUser")
	public ResponseEntity<Response> addUser(@RequestBody UserDto userDto) {

		try {
			User user = new User();
			userService.isDuplicate(userDto.getUsername());
            Role role = roleRepository.findByRole(userDto.getRoles());
			user.setFirstName(userDto.getFirstName());
			user.setMiddleName(userDto.getMiddleName());
			user.setLastName(userDto.getLastName());
			user.setUsername(userDto.getUsername());
			user.setCreatedOn(new Date());
			user.setPassword(userDto.getPassword());
			user.setDesignation(userDto.getDesignation());
			user.setUpdatedDate(new Date());
			user.setRole(role);

			for (String chapter : userDto.getChapters()) {
				Chapter c = userService.getChapter(chapter);
				user.getChapters().add(c);
			}
			userService.addUser(user);
			transactonService.evictAllCaches();
			return getResponse("200", "", "User added Successfully");
		} catch (Exception e) {
			return getResponse("500", "User not added Successfully", "");
		}
	}

	/**
	 * Getting user details for the user
	 * 
	 * @param userId
	 * @return
	 */

	@GetMapping(value = "/api/getUserRole/{userId}")
	public User getRoleForUser(@PathVariable("userId") String userId) {
		return userRepository.findByUsername(userId);
	}

	/**
	 *  To get all users
	 * @return
	 */
	@GetMapping(value = "/api/getUsers")
	public List<UserDto> getAllUsers() {

		List<UserDto> users = new ArrayList<UserDto>();
		for (User user : userService.getAllUsers()) {
			int j = 0;
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFirstName(user.getFirstName());
			userDto.setMiddleName(user.getMiddleName());
			userDto.setLastName(user.getLastName());
			userDto.setUsername(user.getUsername());
			userDto.setPassword(user.getPassword());
			userDto.setStatus(user.isStatus());

			if (user.getRole() != null) {
				userDto.setRoles(user.getRole().getRole());
			}
			userDto.setDesignation(user.getDesignation());
			String chapters[] = new String[user.getChapters().size()];

			for (Chapter chapter : user.getChapters()) {
				String chapterName = chapter.getChapterName();
				chapters[j++] = chapterName;
			}
			userDto.setChapters(chapters);

			users.add(userDto);
		}
		return users;
	}

	/**
	 * 
	 * @param id
	 * @param userDto
	 * @return ResponseEntity
	 * @throws InvalidDataException
	 */
	@PutMapping(value = "/api/updateUser/{id}")
	public ResponseEntity<Response> updateUser(@PathVariable("id") int id, @RequestBody UserDto userDto) {

		User user = new User();
		try {

			BeanUtils.copyProperties(userDto, user);
			user.setId(id);
			user.setUpdatedDate(new Date());
			user.setCreatedBy(userDto.getFirstName() + " " + userDto.getLastName());
			user.setUpdatedBy(userDto.getFirstName() + " " + userDto.getLastName());
			user.setUpdatedDate(new Date());

			userService.isUnique(id, user.getUsername());

			Role role = roleRepository.findByRole(userDto.getRoles());

			Set<Chapter> chapters = new HashSet<>();

			for (String chapter : userDto.getChapters()) {
				chapters.add(chapterRepository.findByChapterName(chapter));
			}
			transactonService.evictAllCaches();
			user.setChapters(chapters);
			user.setRole(role);
			userService.updateUser(id, user);
			return getResponse("200", "", "User updated succesfully.");

		} catch (Exception e) {
			return getResponse("500", "User not updated succesfully.", "");
		}
	}

	/**
	 * Update User status
	 * @param id
	 * @param statusDto
	 * @return
	 */

	@PutMapping(value = "/api/updateUserStatus/{id}")
	public ResponseEntity<Response> updateUserStatus(@PathVariable("id") int id, @RequestBody StatusDto statusDto) {

		try {
			userService.updateUserStatus(id, statusDto);
			return getResponse("200", "", "User updated succesfully.");
		} catch (Exception e) {
			return getResponse("200", "", "User updated succesfully.");
		}
	}

	/**
	 * to recover password
	 * @param request
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/recoverPassword")
	public ResponseEntity<Response> recoverPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {

		try {
			if (userRepository.existsIfUsername(forgotPasswordDto.getUsername())) {
				userService.sendSimpleMessage(forgotPasswordDto.getUsername(), "Your eMembler Password");
				return getResponse("200", "", "The password has been sent to your eMail");
			} else {
				return getResponse("500", "You''re not registered eMembler User, please contact adminstrato", "");
			}
		} catch (Exception e) {
			return getResponse("500", "Unable to reset recover password due to service error", "");
		}
	}

	/**
	 * To reset password
	 * @param passwordDto
	 * @return Response
	 */
	@PostMapping(value = "/api/users/reset_password")
	public ResponseEntity<Response> restPassword(@RequestBody PasswordResetDto passwordDto) {
		try {
			boolean result = userService.updatePassword(passwordDto);
			if (result) {
				userService.sendMessage(passwordDto, "Your eMembler Password is Reset");
			}
			return getResponse("200", "", "You're password has been reset and sent your email");
		} catch (Exception e) {
			return getResponse("500", "You're password has not been reset ", "");
		}
	}

	/**
	 * TO send mail to members
	 * @param memberEmailDto
	 * @return
	 */
	@PostMapping(value = "/api/users/emailToMembers")
	public ResponseEntity<Response> sendEmailToMembers(@RequestBody MemberEmailDto memberEmailDto) {
		try {
			userService.sendMessageToMembers(memberEmailDto);
			return getResponse("200", "", "Mail has been sent succesfully ");
		} catch (Exception e) {
			return getResponse("500", "", "Unable to send a mail, Please contact eMembler Support team");
		}
	}
	/**
	 * To Validate the user details 
	 * @param userDto
	 * @return
	 */
	@PostMapping(value = "/validateCredentials")
	public ResponseEntity<Response> validateCredentials(@RequestBody UserDto userDto){
		try {
			if (!userService.isExistEmail(userDto.getUsername())) {
				return getResponse("500", "Invalid Username", "");
			} else if (!userService.isExistPassword(userDto.getPassword())) {
				return getResponse("500", "Invalid password", "");
			} else if (!userService.isPasswordExistForUser(userDto.getUsername(), userDto.getPassword())) {
				return getResponse("500", "Invalid password", "");
			} else {
				return getResponse("200", "", "valid User");
			}
		} catch (Exception e) {
			return getResponse("500", "Unable to validate", "");
		}
	}
	
	/**
	 * For common Response 
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
