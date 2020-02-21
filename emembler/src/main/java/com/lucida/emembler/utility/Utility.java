package com.lucida.emembler.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.lucida.emembler.entity.Chapter;
import com.lucida.emembler.requestdtos.MemberEmailDto;

public class Utility {

	/**
	 * Utility class to convert String to date conversion
	 * 
	 * @param input
	 * @return
	 */
	public static Date getStringToDate(String input) {

		final List<String> dateFormats = Arrays.asList("M/dd/yy", "M/dd/yyyy", "M/dd/yy h:mm", "M/dd/yy hh:mm",
				"M/d/yy h:mm", "MM/d/yy h:mm", "MM/dd/yyyy hh:mm", "MM/dd/yy hh:mm", "MM-dd-yy hh:mm", "MM-dd-yyyy",
				"MM-dd-yyyy hh:mm", "MM/dd/yyyy hh:mm");

		if (input != null) {
			for (String format : dateFormats) {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				try {
					Date dt = sdf.parse(input);
					return dt;
				} catch (ParseException e) {
					// intentionally empty
				}
			}
		}
		return null;
	}

	public static int getCurrentFiscalYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		if (month == 9 || month == 10 || month == 11) {
			return year + 1;
		} else {
			return year;
		}
	}

	public static Map<String, Date> getStartAndEndDate(int value) {

		Map<String, Date> map = new HashMap<>();
		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		Date dtStart = null;
		Date dtEnd = null;

		if (month == 9 || month == 10 || month == 11) {
			dtStart = new GregorianCalendar(year + value, Calendar.OCTOBER, 01).getTime();
			dtEnd = new GregorianCalendar(year + 1 + value, Calendar.SEPTEMBER, 30).getTime();
		} else {
			dtStart = new GregorianCalendar(year - 1 + value, Calendar.OCTOBER, 01).getTime();
			dtEnd = new GregorianCalendar(year + value, Calendar.SEPTEMBER, 30).getTime();
		}

		map.put("dtStart", dtStart);
		map.put("dtEnd", dtEnd);

		return map;
	}

//	public static String getPrimaryGroupCode(String chapterName) {
//
//		if (chapterName.equalsIgnoreCase("Professionals: Greater Atlanta"))
//			return "atl";
//		else if (chapterName.equalsIgnoreCase("Professionals: Houston")) {
//			return "hou";
//		} else if (chapterName.equalsIgnoreCase("Professionals: Charlotte")) {
//			return "char";
//		} else if (chapterName.equalsIgnoreCase("Professionals: North Texas")) {
//			return "ntx";
//		} else if (chapterName.equalsIgnoreCase("Professionals: Seattle")) {
//			return "sea";
//		} else if (chapterName.equalsIgnoreCase("Professionals: Los Angeles Metro")) {
//			return "la";
//		} else if (chapterName.equalsIgnoreCase("Professionals: New England")) {
//			return "ne";
//		} else if (chapterName.equalsIgnoreCase("Professionals: Philadelpia")) {
//			return "phl";
//		} else {
//			return "";
//		}
//	}

	public static Map<Integer, Date> getFiscalEndDates(int year) {
		Map<Integer, Date> map = new HashMap<Integer, Date>();
		map.put(0, new GregorianCalendar(year, Calendar.SEPTEMBER, 30).getTime());
		map.put(1, new GregorianCalendar(year - 1, Calendar.SEPTEMBER, 30).getTime());
		map.put(2, new GregorianCalendar(year - 2, Calendar.SEPTEMBER, 30).getTime());
		map.put(3, new GregorianCalendar(year + 1, Calendar.SEPTEMBER, 30).getTime());
		return map;
	}

	/**
	 * Sending an email to User
	 * 
	 * @return
	 */
	public static boolean sendEmail(String from, String password, String to, String subject, String content) {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {

			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(from));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setContent(content, "text/html");

			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setHeader("Content-ID", "<image>");

			multipart.addBodyPart(messageBodyPart);

			mimeMessage.setContent(content, "text/html");
			mimeMessage.setSubject(subject);

			Transport.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Sending an email to User
	 * 
	 * @return
	 */
	public static boolean sendEmailWIthCC(Chapter chapter, MemberEmailDto memberEmailDto, String email) {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(chapter.getChapterEmail(), chapter.getChapterPassWord());
			}
		});

		try {

			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(chapter.getChapterEmail()));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			for (String c : memberEmailDto.getBcc()) {
				mimeMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(c));
			}

			for (String c : memberEmailDto.getCc()) {
				mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(c));
			}

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setHeader("Content-ID", "<image>");
			mimeMessage.setContent(memberEmailDto.getMessage(), "text/html");
			mimeMessage.setSubject(memberEmailDto.getSubject());

			Transport.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Get fiscal year based on the date.
	 * 
	 * @param date
	 * @return
	 */
	public static int getFiscalYearFromDate(Date date) {

		Calendar cal = Calendar.getInstance();

		if (date != null) {
			cal.setTime(date);
		}

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		if (month == 9 || month == 10 || month == 11) {
			return year + 1;
		} else {
			return year;
		}
	}

	/**
	 * Get fiscal year start date and end dates for give fiscal year
	 * 
	 * @param year
	 * @return
	 */
	public static Map<String, Date> getStartAndEndDateForFiscalYear(int year) {

		Map<String, Date> map = new HashMap<>();

		Date dtStart = new GregorianCalendar(year - 1, Calendar.OCTOBER, 01).getTime();
		Date dtEnd = new GregorianCalendar(year, Calendar.SEPTEMBER, 30).getTime();

		map.put("dtStart", dtStart);
		map.put("dtEnd", dtEnd);

		return map;
	}

	/**
	 * Utility class to convert String to date conversion
	 * 
	 * @param input
	 * @return
	 */
	public static Date getStringToD(String input) {

		final List<String> dateFormats = Arrays.asList("yyyy-MM-dd");

		if (input != null) {
			for (String format : dateFormats) {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				try {
					Date dt = sdf.parse(input);
					return dt;
				} catch (ParseException e) {
					// intentionally empty
				}
			}
		}
		return null;
	}

	public static String getDateToString(Date date) {
		if(date != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		}else {
			return null;
		}
		
	}

}
