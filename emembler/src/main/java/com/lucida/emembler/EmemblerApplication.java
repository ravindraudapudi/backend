package com.lucida.emembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.lucida.emembler.service.ConfigService;
import com.lucida.emembler.service.UserService;

/**
 * To run spring boot application with default Settings in database.
 * 
 * @author Lucida.
 */
@SpringBootApplication
@EnableCaching
public class EmemblerApplication implements CommandLineRunner  {

	@Autowired
	private ConfigService configService;

	@Autowired
	private UserService userService;

	/**
	 * To run Spring application
	 * 
	 * @param args
	 */
	
	
	public static void main(String[] args) 
	{
		SpringApplication.run(EmemblerApplication.class, args);
	}

	/**
	 *  @author Lucida
	 */
	@Override
	public void run(String... arg0) throws Exception 
	{
		configService.initPrivilages();
		configService.initRolesAssignPrivilage();
		userService.initDefaultUser();
	}
}
