package net.biomodels.jummp.services;

import net.biomodels.jummp.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.biomodels.jummp.entities.security.User;


@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
    UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDetailsRepository.findByUsername(username);
		
		if(null == user) {
			throw new UsernameNotFoundException("User Not Found with username " + username);
		}
		return user;
	}

}
