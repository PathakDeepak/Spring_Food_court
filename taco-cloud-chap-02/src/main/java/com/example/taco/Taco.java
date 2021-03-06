package com.example.taco;

import java.util.List;
// end::allButValidation[]
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
// tag::allButValidation[]
import lombok.Data;

@Data
public class Taco {

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;
  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<String> ingredients;
  
  public Taco() {
	// TODO Auto-generated constructor stub
}
  
public Taco(@NotNull @Size(min = 5, message = "Name must be at least 5 characters long") String name,
		@Size(min = 1, message = "You must choose at least 1 ingredient") List<String> ingredients) {
	super();
	this.name = name;
	this.ingredients = ingredients;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<String> getIngredients() {
	return ingredients;
}

public void setIngredients(List<String> ingredients) {
	this.ingredients = ingredients;
}
  
  

}