package net.biomodels.jummp;

import net.biomodels.jummp.entities.security.Role;
import net.biomodels.jummp.entities.security.User;
import net.biomodels.jummp.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringSecurityDemoAppApplication {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoAppApplication.class, args);
	}
	
	@PostConstruct
	protected void init() {
		
		List<Role> authorityList = new ArrayList<Role>();
		authorityList.add(createAuthority("USER","User role"));
		authorityList.add(createAuthority("ADMIN","Admin role"));
		User user = new User();
		user.setId(1L);
		user.setUsername("hello");
		user.setEmail("hello@test.com");
		user.setPassword(passwordEncoder.encode("hello@123"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);
		System.out.println(user);
		userDetailsRepository.save(user);
	}
	
	private Role createAuthority(final String roleCode, final String roleDescription) {
		Role authority = new Role();
		authority.setAuthority(roleCode);
//		authority.setRoleDescription(roleDescription);
		return authority;
	}
}
