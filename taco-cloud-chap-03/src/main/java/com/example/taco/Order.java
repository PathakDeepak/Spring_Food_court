package com.example.taco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity

/*
 * Without it, JPA would default to persisting the entities to a table named Order, but order is a reserved word in SQL and would cause problems.
 * */
@Table(name="Taco_Order")//This specifies that Order entities should be persisted to a table named Taco_Order in the database.
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  private Date placedAt;
  
//end::allButDetailProperties[]
  @NotBlank(message="Delivery name is required")
  private String deliveryName;
  
  @NotBlank(message="Street is required")
  private String deliveryStreet;
  
  @NotBlank(message="City is required")
  private String deliveryCity;
  
  @NotBlank(message="State is required")
  private String deliveryState;
  
  @NotBlank(message="Zip code is required")
  private String deliveryZip;

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;
  
  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
           message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;

  
  @ManyToMany(targetEntity=Taco.class)
  private List<Taco> tacos = new ArrayList<>();
  
  public void addDesign(Taco design) {
    this.tacos.add(design);
  }
  
  @PrePersist
  void placedAt() {
    this.placedAt = new Date();
  }
  
  public Order() {
	// TODO Auto-generated constructor stub
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Date getPlacedAt() {
	return placedAt;
}

public void setPlacedAt(Date placedAt) {
	this.placedAt = placedAt;
}

public String getDeliveryName() {
	return deliveryName;
}

public void setDeliveryName(String deliveryName) {
	this.deliveryName = deliveryName;
}

public String getDeliveryStreet() {
	return deliveryStreet;
}

public void setDeliveryStreet(String deliveryStreet) {
	this.deliveryStreet = deliveryStreet;
}

public String getDeliveryCity() {
	return deliveryCity;
}

public void setDeliveryCity(String deliveryCity) {
	this.deliveryCity = deliveryCity;
}

public String getDeliveryState() {
	return deliveryState;
}

public void setDeliveryState(String deliveryState) {
	this.deliveryState = deliveryState;
}

public String getDeliveryZip() {
	return deliveryZip;
}

public void setDeliveryZip(String deliveryZip) {
	this.deliveryZip = deliveryZip;
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

public List<Taco> getTacos() {
	return tacos;
}

public void setTacos(List<Taco> tacos) {
	this.tacos = tacos;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public Order(Long id, Date placedAt, @NotBlank(message = "Delivery name is required") String deliveryName,
		@NotBlank(message = "Street is required") String deliveryStreet,
		@NotBlank(message = "City is required") String deliveryCity,
		@NotBlank(message = "State is required") String deliveryState,
		@NotBlank(message = "Zip code is required") String deliveryZip,
		@CreditCardNumber(message = "Not a valid credit card number") String ccNumber,
		@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY") String ccExpiration,
		@Digits(integer = 3, fraction = 0, message = "Invalid CVV") String ccCVV, List<Taco> tacos) {
	super();
	this.id = id;
	this.placedAt = placedAt;
	this.deliveryName = deliveryName;
	this.deliveryStreet = deliveryStreet;
	this.deliveryCity = deliveryCity;
	this.deliveryState = deliveryState;
	this.deliveryZip = deliveryZip;
	this.ccNumber = ccNumber;
	this.ccExpiration = ccExpiration;
	this.ccCVV = ccCVV;
	this.tacos = tacos;
}
  
  
  
}