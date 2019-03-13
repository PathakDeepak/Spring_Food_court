package com.example.taco.web;

/*
 * @ModelAttribute methods are invoked before the controller methods annotated with @RequestMapping are invoked. 
 * The logic behind the sequence is that, the model object has to be created before any processing starts inside the controller methods
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.taco.Ingredient;
import com.example.taco.Ingredient.Type;
import com.example.taco.Order;
import com.example.taco.Taco;
import com.example.taco.data.IngredientRepository;
import com.example.taco.data.TacoRepository;

@Controller
@RequestMapping("/design")

/*
 * The class-level @SessionAttributes annotation specifies any model
 * objects like the order attribute that should be kept in session and
 * available across multiple requests.
 * */
@SessionAttributes("order")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  private TacoRepository tacoRepo;


  @Autowired
  public DesignTacoController(
        IngredientRepository ingredientRepo,
        TacoRepository tacoRepo) {
    this.ingredientRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
  }

  
  /*
   *The @ModelAttribute is an annotation that binds a method parameter or method return value to a named model attribute and then exposes it to a web view.
   * */
  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "design")
  public Taco design() {
    return new Taco();
  }



  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }

    return "design";
  }

  @PostMapping
  public String processDesign(
      @Valid Taco taco, Errors errors,
      @ModelAttribute Order order) {
/*
 * The Order parameter is annotated with @ModelAttribute to indicate that its value 
 * should come from the model and that Spring MVC shouldn’t attempt to bind request 
 * parameters to it.
 * */
	  
	  
    if (errors.hasErrors()) {
      return "design";
    }

    /*
     * After checking for validation errors, processDesign() 
     * uses the injected Taco Repository to save the taco. 
     * It then adds the Taco object to the Order that’s kept in the session.
     * */
    Taco saved = tacoRepo.save(taco);
    order.addDesign(saved);

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