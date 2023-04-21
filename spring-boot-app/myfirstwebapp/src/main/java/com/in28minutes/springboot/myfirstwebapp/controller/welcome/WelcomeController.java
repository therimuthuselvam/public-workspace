package com.in28minutes.springboot.myfirstwebapp.controller.welcome;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

  // @RequestMapping(value = "/", method = RequestMethod.GET)
  // public String goToLoginPage(ModelMap model) {
  // model.put("name", "in28Minutes");
  // return "welcome";
  // }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String goToLoginPage(ModelMap model) {
    model.put("name", getLoggedinUsername());
    return "welcome";
  }

  private String getLoggedinUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}
