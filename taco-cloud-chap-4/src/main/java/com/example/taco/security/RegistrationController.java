package com.example.taco.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.taco.data.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public RegistrationController(
		      UserRepository userRepo, PasswordEncoder passwordEncoder) {
		    this.userRepo = userRepo;
		    this.passwordEncoder = passwordEncoder;
		  }
	
	
	@GetMapping
	  public String registerForm() {
	    return "registration";
	  }
	
	
	/*
	 * The RegistrationForm object given to process- Registration() is bound to the request data 
	 * */
	@PostMapping
	  public String processRegistration(RegistrationForm form) {
	    userRepo.save(form.toUser(passwordEncoder));
	    return "redirect:/login";
	  }

}
