package com.example.taco;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

//@Data
public class Order {

	//@NotBlank--you only want to be sure that the user doesn’t leave any of the fields blank. Part of Hibernate Validator
  @NotBlank(message="Name is required")
  private String name;

  @NotBlank(message="Street is required")
  private String street;

  @NotBlank(message="City is required")
  private String city;

  @NotBlank(message="State is required")
  private String state;

  @NotBlank(message="Zip code is required")
  private String zip;

  
  //You need to not only ensure that the ccNumber property isn’t empty, but that it contains a value that could be a valid credit card number.
  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
           message="Must be formatted MM/YY")
  private String ccExpiration;

  //@Digits to ensure that the value con- tains exactly three numeric digits.
  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;
  
  public Order() {
	// TODO Auto-generated constructor stub
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
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

public String getCcNumber() {
	return ccNumber;
}

public void setCcNumber(String ccNumber) {
	this.ccNumber = ccNumber;
}

public String getCcExpiration() {
	return ccExpiration;
}

public void setCcExpiration(String ccExpiration) {
	this.ccExpiration = ccExpiration;
}

public String getCcCVV() {
	return ccCVV;
}

public void setCcCVV(String ccCVV) {
	this.ccCVV = ccCVV;
}

public Order(@NotBlank(message = "Name is required") String name,
		@NotBlank(message = "Street is required") String street, @NotBlank(message = "City is required") String city,
		@NotBlank(message = "State is required") String state, @NotBlank(message = "Zip code is required") String zip,
		@CreditCardNumber(message = "Not a valid credit card number") String ccNumber,
		@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY") String ccExpiration,
		@Digits(integer = 3, fraction = 0, message = "Invalid CVV") String ccCVV) {
	super();
	this.name = name;
	this.street = street;
	this.city = city;
	this.state = state;
	this.zip = zip;
	this.ccNumber = ccNumber;
	this.ccExpiration = ccExpiration;
	this.ccCVV = ccCVV;
}
  
  
  

}
//end::allButValidation[]
//end::all[]
