package com.lucida.emembler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucida.emembler.dao.UserRepository;
import com.lucida.emembler.entity.Privilege;
import com.lucida.emembler.entity.User;

/**
 * @author Lucida.
 *  Security service to secure application
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Default implementation class for retrieval of secure user data.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);

		if (user == null | !user.isStatus() ) {
			throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Privilege privilege: user.getRole().getPrivilages()) {
			authorities.add(new SimpleGrantedAuthority(privilege.getPrivilege()));	
		}
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), authorities);

		return userDetails;
	}
}
