package com.lucida.emembler.config;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailSenderFactoryImpl {

	public JavaMailSender getSender(final String email, final String password) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(mailProperties());
		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername(email);
		mailSender.setPassword(password);
		mailSender.setPort(587);
		return mailSender;
	}

	private Properties mailProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.starttls.required", true);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.connectiontimeout", 5000);
		properties.put("mail.smtp.timeout", 5000);
		properties.put("mail.smtp.writetimeout", 5000);
		return properties;
	}
}