package com.lucida.emembler.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lucida.emembler.config.MailSenderFactoryImpl;
import com.lucida.emembler.dao.ChapterRepository;
import com.lucida.emembler.dao.EmailLogRepository;
import com.lucida.emembler.dao.ReportDetailsRepository;
import com.lucida.emembler.dao.RoleRepository;
import com.lucida.emembler.dao.UserRepository;
import com.lucida.emembler.entity.Chapter;
import com.lucida.emembler.entity.EmailLog;
import com.lucida.emembler.entity.ReportDetails;
import com.lucida.emembler.entity.User;
import com.lucida.emembler.exceptions.DaoException;
import com.lucida.emembler.exceptions.InvalidDataException;
import com.lucida.emembler.requestdtos.MemberEmailDto;
import com.lucida.emembler.requestdtos.PasswordResetDto;
import com.lucida.emembler.requestdtos.StatusDto;
import com.lucida.emembler.utility.Utility;

/**
 * 
 * @author Ravindra Service class for user operations
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ChapterRepository chapterRepository;

	@Autowired
	private ReportDetailsRepository reportDetailsRepository;

	@Autowired
	private EmailLogRepository emailLogRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	/*
	 * Initial Default User Creation
	 */
	public void initDefaultUser() {
		try {
			List<User> users = userRepository.findAll();
			if (users.size() == 0) {
				User user1 = new User();
				user1.setFirstName("Pratima ");
				user1.setMiddleName("superAdmin");
				user1.setLastName("Rao");
				user1.setUsername("admin@emembler.com");
				user1.setPassword("Welcome@123");
				user1.setDesignation("Vise president");
				user1.setCreatedOn(new Date());
				user1.setCreatedOn(new Date());
				Set<Chapter> userChapter = new HashSet<>();
				user1.setRole(roleRepository.findByRole("SuperAdmin"));
				user1.setChapters(userChapter);

				userRepository.save(user1);
				userRepository.flush();
			}

			if (reportDetailsRepository.findAll().size() == 0) {
				ReportDetails reportDetails = new ReportDetails();
				reportDetails.setEmailId("ascendtest@ascendleadership.org");
				reportDetails.setPassword("Ascend@1");
				reportDetailsRepository.save(reportDetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param chapter
	 * @return Chapter
	 */
	@Cacheable("getChapter")
	public Chapter getChapter(String chapter) {
		return chapterRepository.findByChapterName(chapter);
	}

	/**
	 * Saving user to database
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		userRepository.save(user);
		userRepository.flush();
	}

	/**
	 * 
	 * @param id
	 * @param user
	 */
	public void updateUser(int id, User user) {
		Optional<User> userTemp = userRepository.findById(id);
		if (userTemp.isPresent()) {
			userRepository.save(user);
		}
		userRepository.flush();
	}

	/**
	 * Getting List of users from database.
	 * 
	 * @return User list
	 */
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Getting user data from database.
	 * 
	 * @param id
	 * @return User
	 */
	public User getUser(int id) {
		return userRepository.findById(id).orElse(null);
	}

	/**
	 * deleting user from database
	 * 
	 * @param id
	 */
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	/**
	 * 
	 * @param passwordDto
	 * @throws InvalidDataException
	 */
	public boolean updatePassword(PasswordResetDto passwordDto) throws InvalidDataException {

		User user = userRepository.findByUsername(passwordDto.getUsername());
		if (user.getPassword().equals(passwordDto.getOldPassword())) {
			user.setPassword(passwordDto.getNewPassword());
		} else {
			throw new InvalidDataException("Invalid password");
		}
		userRepository.flush();
		return true;
	}

	/**
	 * unique check for username
	 * 
	 * @param id
	 * @param username
	 * @throws InvalidDataException
	 */
	public void isUnique(int id, String username) throws InvalidDataException {

		List<User> users = this.userRepository.findByUniqueFields(username);

		if (users != null) {
			for (User u : users) {
				if (u.getUsername().equals(username) && u.getId() != id) {
					throw new InvalidDataException("Duplicate usernameusername Id - " + username);
				}
			}
		}
	}

	/**
	 * Recover password Service
	 * 
	 * @param to
	 * @param subject
	 */
	public void sendSimpleMessage(String to, String subject) {

		try {

			Optional<ReportDetails> rDetails = reportDetailsRepository.findById(1);
			ReportDetails reportDetails = rDetails.get();
			User user = userRepository.findByUsername(to);

			String content = "<div class='container'>" + "        <div class='row'>"
					+ "            <div style='line-height:20px;'>"
					+ "                <strong style='font-size: 14px;font-weight: 400;color:black;font-weight: bold'>Hello"
					+ "                   User,</strong>" + "            </div></br>"
					+ "            <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: 400;color:black;'> A request for"
					+ "                    your password was received." + "                </strong>"
					+ "            </div></br>" + "            <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: 400;color:black;'>"
					+ "                    Your password for the eMembler Reporting Tool is " + user.getPassword()
					+ " </strong>" + "            </div>" + "            </br>"
					+ "            <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: 400; color:black;'>"
					+ "                    change your password please click on the top right icon after logging in. </strong> </div></br>"
					+ "           <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: bold;'></strong>Thank you for using"
					+ "                eMembler Reporting Tool." + "          </div>" + "            </br>"
					+ "            <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: bold;'>eMembler Support Team</strong>"
					+ "            </div>" + "            </br>" + "        </div>" + "    </div>";

			Utility.sendEmail(reportDetails.getEmailId(), reportDetails.getPassword(), to, subject, content);

			logger.info("Mail has been been sent to the user", user.getUsername());

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Reset password Service
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 */
	public void sendMessage(PasswordResetDto passwordResetDto, String subject) {

		try {

			Optional<ReportDetails> rDetails = reportDetailsRepository.findById(1);
			ReportDetails reportDetails = rDetails.get();

			User user = userRepository.findByUsername(passwordResetDto.getUsername());

			String content = "<div class='container'>" + "        <div class='row'>"
					+ "            <div style='line-height:20px;'>"
					+ "                <strong style='font-size: 14px;font-weight: 400;color:black;font-weight: bold'>Hello"
					+ "                   User,</strong>" + "            </div></br>"
					+ "            <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: 400;color:black;'> You have reset your password for the eMembler Reporting Tool to "
					+ "                   " + user.getPassword() + " " + "                </strong>"
					+ "            </div></br>" + "            <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: 400;color:black;'>"
					+ "                    If you did not reset your password, please contact support@emembler.com. </strong>"
					+ "            </div>" + "            </br>" + "            <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: 400; color:black;'>"
					+ "                   Thank you for using eMembler Reporting Tool.  </strong> </div></br>"
					+ "            </br>" + "            <div style='line-height:20px'>"
					+ "                <strong style='font-size: 14px;font-weight: bold;'> eMembler Support Team</strong>"
					+ "            </div>" + "            </br>" + "        </div>" + "    </div>";

			Utility.sendEmail(reportDetails.getEmailId(), reportDetails.getPassword(), passwordResetDto.getUsername(),
					subject, content);

			logger.info("Mail has been been sent to the user sendResetPasswordMessage() : ", user.getUsername());

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @param user
	 */
	public void updateUserStatus(int id, StatusDto user) {
		Optional<User> userStatus = userRepository.findById(id);
		if (userStatus.isPresent()) {
			User updatedUser = userStatus.get();
			updatedUser.setStatus(user.isStatus());
		}
		userRepository.flush();
	}

	/**
	 * Duplicate check for user Id
	 * 
	 * @param username
	 * @throws InvalidDataException
	 */
	public void isDuplicate(String username) throws InvalidDataException {

		List<User> users = this.userRepository.findByUniqueFields(username);

		if (users != null) {
			for (User u : users) {
				if (u.getUsername().equals(username)) {
					throw new InvalidDataException("Duplicate username - " + username);
				}
			}
		}
	}

	/**
	 * Sending mail to members
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 * @param sentBy
	 */
	public void sendMessageToMembers(MemberEmailDto memberEmailDto) throws DaoException {

		try {

			Chapter chapter = chapterRepository.findByPrimaryGroupCode(memberEmailDto.getFromChapter());

			for (String email : memberEmailDto.getTo()) {

				/**
				 * Send mail to member
				 */
				Utility.sendEmailWIthCC(chapter, memberEmailDto, email);

				/**
				 * Log record
				 */

				logMailRecord(memberEmailDto, email);

				logger.info("Mail has been been sent to the members() : ");
			}
		} catch (Exception e) {
			logger.info("Unable to send mail to members " + e);
			throw new DaoException(e);
		}
	}

	public boolean isExistEmail(String email) {
		return this.userRepository.existsIfUsername(email);
	}

	public boolean isExistPassword(String password) {
		return this.userRepository.existsIfPassword(password);
	}

	public boolean isPasswordExistForUser(String user, String password) {
		return this.userRepository.existsPasswordForUser(user, password);
	}

	/**
	 * Maintain audit records for mail
	 * 
	 * @param memberEmailDto
	 * @param email
	 */
	public void logMailRecord(MemberEmailDto memberEmailDto, String email) {
		EmailLog emailLog = new EmailLog(new Date(), memberEmailDto.getMessage(), memberEmailDto.getSubject(),
				memberEmailDto.getSentBy(), email);
		emailLogRepository.save(emailLog);
		emailLogRepository.flush();

	}

}
