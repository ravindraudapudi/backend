package com.lucida.emembler.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.lucida.emembler.dao.UserRepository;
import com.lucida.emembler.entity.User;

@Component
public class EmailComponent {
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${spring.mail.host}")
	private String emailHost;
	
	@Value("${spring.mail.port}")
	private String port;
	
	@Value("${spring.mail.username}")
	private String userName;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(emailHost);
	    mailSender.setPort(Integer.parseInt(port));
	    mailSender.setUsername(userName);
	    mailSender.setPassword(password);
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}
}
