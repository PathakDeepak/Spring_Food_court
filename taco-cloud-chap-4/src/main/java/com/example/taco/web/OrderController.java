package com.example.taco.web;


import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.taco.Order;
import com.example.taco.User;
import com.example.taco.data.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
  
  private OrderRepository orderRepo;

  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }
  
  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal User user, 
      @ModelAttribute Order order) {
    if (order.getDeliveryName() == null) {
      order.setDeliveryName(user.getFullname());
    }
    if (order.getDeliveryStreet() == null) {
      order.setDeliveryStreet(user.getStreet());
    }
    if (order.getDeliveryCity() == null) {
      order.setDeliveryCity(user.getCity());
    }
    if (order.getDeliveryState() == null) {
      order.setDeliveryState(user.getState());
    }
    if (order.getDeliveryZip() == null) {
      order.setDeliveryZip(user.getZip());
    }
    
    return "orderForm";
  }

  
  /*
   * Here, the Order object sub- mitted in the form (which also happens to be the same Order object maintained in session) 
   * is saved via the save() method on the injected OrderRepository
   * 
   * Once the order is saved, you don’t need it hanging around in a session anymore. 
   * In fact, if you don’t clean it out, the order remains in session, including its associated tacos, and the next order will start with whatever 
   * tacos the old order contained. 
   * Therefore, the processOrder() method asks for a SessionStatus parameter and calls its setComplete() method to reset the session.
   * */
  @PostMapping
  public String processOrder(@Valid Order order, Errors errors,
      SessionStatus sessionStatus,
      @AuthenticationPrincipal User user) {
	  
    if (errors.hasErrors()) {
      return "orderForm";
    }
    
    order.setUser(user);
    
    orderRepo.save(order);
    sessionStatus.setComplete();
    
    return "redirect:/";
  }

}