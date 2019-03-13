package com.example.taco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.taco.User;
import com.example.taco.data.UserRepository;


/*
 * Spring’s stereotype annotations that flag it for inclusion in Spring’s component scanning, 
 * so there’s no need to explicitly declare this class as a bean. Spring will automatically discover it and instantiate it as a bean.
 * */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepo;

	@Autowired
	  public UserRepositoryUserDetailsService(UserRepository userRepo) {
	    this.userRepo = userRepo;
	  }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByUsername(username);
		
		if (user != null) {
		      return user;
		    }
		throw new UsernameNotFoundException(
                "User '" + username + "' not found");
	}

}
