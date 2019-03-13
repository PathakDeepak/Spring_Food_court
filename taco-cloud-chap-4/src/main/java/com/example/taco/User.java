package com.example.taco;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/*
 * Implementations of UserDetails will provide some essential user information to the framework, 
 * such as what authorities are granted to the user and whether the user’s account is enabled or not.
 * */

@Entity
public class User implements UserDetails{
	
	  private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private  String username;
	  private  String password;
	  private  String fullname;
	  private  String street;
	  private  String city;
	  private  String state;
	  private  String zip;
	  private  String phoneNumber;
	  
	  public User() {
		// TODO Auto-generated constructor stub
	}
	  

	public User(String username, String password, String fullname, String street, String city, String state,
			String zip, String phoneNumber) {
		super();
		
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}



	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	/*
	 * The getAuthorities() method should return a collection of authorities granted to the user. 
	 * The various is___Expired() methods return a boolean to indicate whether or not the user’s account is enabled or expired.
	 * 
	 * For your User entity, the getAuthorities() method simply returns a collection indicating that all users will have 
	 * been granted ROLE_USER authority.
	 * */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
