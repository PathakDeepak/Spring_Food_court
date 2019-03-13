package com.example.taco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	  public PasswordEncoder encoder() {
		//StandardPasswordEncoder—Applies SHA-256 hashing encryption
	    return new StandardPasswordEncoder("53cr3t");
	}
	
	@Autowired
	  private UserDetailsService userDetailsService;
	
	@Override
	  protected void configure(AuthenticationManagerBuilder auth)
	      throws Exception {

		/*                               In-memory user store
		 * The in-memory user store is convenient for testing purposes or for very simple applications, 
		 * but it doesn’t allow for easy editing of users. If you need to add, remove, or change a user, 
		 * you’ll have to make the necessary changes and then rebuild and redeploy the application.
		 * 	*/
		
		/*
		 * auth .inMemoryAuthentication() .withUser("buzz") .password("infinity")
		 * .authorities("ROLE_USER") .and() .withUser("woody") .password("bullseye")
		 * .authorities("ROLE_USER");
		 */
		
		
		
		/*
		 * you want customers to be able to register with the application and manage their own user accounts. 
		 * That doesn’t fit with the limitations of the in-memory user store, 
		 * so let’s take a look at another option that allows for a database-backed user store.
		 * */
		
		
		
		
		/*                                            JDBC-based user store
		 * User information is often maintained in a relational database, and a JDBC-based user store seems appropriate. 
		 * */
		
		/*
		 * auth .jdbcAuthentication() .dataSource(dataSource);
		 */
	    
		
		
		/*                                              USER-DETAILS SERVICE
		 * custom user details service that reads user information via a JPA repository, 
		 * you just need a way to get users into the database in the first place
		 * 
		 * you simply make a call to the userDetailsService() method, passing in the UserDetailsService instance 
		 * that has been autowired into SecurityConfig.
		 * */
		auth
	      .userDetailsService(userDetailsService)
	      .passwordEncoder(encoder());
		
		
	  }
	
	
	/*
	 * You need to ensure that requests for /design and /orders are only available to authenticated users of "ROLE_USER"; 
	 * All other requests should be permitted for all users.
	 * 
	 * The order of these rules is important. Security rules declared first take precedence over those declared lower down. 
	 * If you were to swap the order of those two security rules, all requests would have permitAll() applied to them; 
	 * the rule for /design and /orders requests would have no effect.
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
	    .authorizeRequests()
	      .antMatchers("/design", "/orders")
	        .access("hasRole('ROLE_USER')")
	      .antMatchers("/", "/**").access("permitAll")
	      
	      
	      /*
	       * To replace the built-in login page, you first need to tell Spring Security what path your custom login page will be at. 
	       * That can be done by calling formLogin() on the HttpSecurity object passed into configure()
	       * 
	       * Notice that before you call formLogin(), you bridge this section of configuration and the previous section with a call to and().
	       * */
	      .and()
	      .formLogin()
	      .loginPage("/login")
	      
	      /*
	       * a successful login would take them to the root path
	       * but you can change that by specifying a default success page
	       * uncomment below line
	       * */
	      //.defaultSuccessUrl("/design")  
	      
	      
	      /*
	       * To enable logout, you simply need to call logout on the HttpSecurity object
	       * */
	      .and()
          .logout()
            .logoutSuccessUrl("/")
            
            
            .and()
            .csrf()
              .ignoringAntMatchers("/h2-console/**")
	    ;
	}

}
