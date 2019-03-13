package com.example.taco.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import com.example.taco.Ingredient;
import com.example.taco.Ingredient.Type;
import com.example.taco.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	private static final org.slf4j.Logger log =
		    org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);

@ModelAttribute
public void addIngredientsToModel(Model model) {
	
	
	
	//build a list of ingredients
	List<Ingredient> ingredients = Arrays.asList(
	  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
	  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
	  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
	  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
	  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
	  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
	  new Ingredient("CHED", "Cheddar", Type.CHEESE),
	  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
	  new Ingredient("SLSA", "Salsa", Type.SAUCE),
	  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
	);
	
	Type[] types = Ingredient.Type.values();
	for (Type type : types) {
	  model.addAttribute(type.toString().toLowerCase(),
	      filterByType(ingredients, type));
	}
}
	
  @GetMapping
  public String showDesignForm(Model model) {
    model.addAttribute("design", new Taco());
    return "design";
  }


/*
 * When the form is submitted, the fields in the form are bound to properties of a Taco object
 * 
 * For now, the processDesign() method does nothing with the Taco object. In fact, it doesn’t do much of anything at all.
 * you’ll add some per- sistence logic that will save the submitted Taco to a database.
 * 
 * "redirect:", indicating that this is a redirect view. More specifically, it indicates that after processDesign() completes,
 *  the user’s browser should be redirected to the relative path /order/current.
 *  
 *  
 *we need to revisit each of the controllers, specifying that validation should be performed when the forms are POSTed to their respective handler methods.
 *To validate a submitted Taco, you need to add the Java Bean Validation API’s @Valid annotation.
 *The @Valid annotation tells Spring MVC to perform validation on the submitted Taco object after it’s bound to the submitted form data 
 *and before the processDesign() method is called.
 */
  @PostMapping
  public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
    if (errors.hasErrors()) {
      return "design";
    }


    // Save the taco design...
    // We'll do this later
    log.info("Processing design: " + design);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}
