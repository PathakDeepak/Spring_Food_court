package com.example.taco.web;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import com.example.taco.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
  
	private static final org.slf4j.Logger log =
		    org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
	
	
	/*
	 *Once you have a way to persist taco creations to a database in later,
	 *you’ll revisit this method and modify it to populate the model with a list of Taco objects to be placed in the order. 
	 */
  @GetMapping("/current")
  public String orderForm(Model model) {
    model.addAttribute("order", new Order());
    return "orderForm";
  }
  
  
  
  /*
   * Here given an Order object whose properties are bound to the submitted form fields. 
   * Order, much like Taco, is a fairly straightforward class that carries order information.
   * */
  @PostMapping
  public String processOrder(@Valid Order order, Errors errors) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    
    log.info("Order submitted: " + order);
    return "redirect:/";
  }
  
}
