package com.example.taco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  private Date createdAt;

  
  /*
   * To declare the relationship between a Taco and its associated Ingredient list, you annotate ingredients with @ManyToMany. 
   * A Taco can have many Ingredient objects, and an Ingredient can be a part of many Tacos.
   * */
  @ManyToMany(targetEntity=Ingredient.class)
  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<Ingredient> ingredients = new ArrayList<>();
  
  
  /*
   *  You’ll use this to set the createdAt property to the current date and time before Taco is persisted.
   * */
  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
  
  public Taco() {
	// TODO Auto-generated constructor stub
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public List<Ingredient> getIngredients() {
	return ingredients;
}

public void setIngredients(List<Ingredient> ingredients) {
	this.ingredients = ingredients;
}

public Taco(Long id, @NotNull @Size(min = 5, message = "Name must be at least 5 characters long") String name,
		Date createdAt,
		@Size(min = 1, message = "You must choose at least 1 ingredient") List<Ingredient> ingredients) {
	super();
	this.id = id;
	this.name = name;
	this.createdAt = createdAt;
	this.ingredients = ingredients;
}
  

  
}