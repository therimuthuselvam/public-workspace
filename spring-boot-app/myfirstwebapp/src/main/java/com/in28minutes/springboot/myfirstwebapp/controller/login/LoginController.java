package com.in28minutes.springboot.myfirstwebapp.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.springboot.myfirstwebapp.service.authentication.AuthenticationService;

@Controller
@SessionAttributes("name")
public class LoginController {

  // private Logger logger = LoggerFactory.getLogger(getClass());

  // private AuthenticationService authenticationService;

  // public LoginController(AuthenticationService authenticationService) {
  // super();
  // this.authenticationService = authenticationService;
  // }

  // @RequestMapping(value = "/login", method = RequestMethod.GET)
  // public String goToLoginPage() {
  // return "login";
  // }

  // @RequestMapping(value = "/login", method = RequestMethod.POST)
  // public String goToWelcomePage(@RequestParam String name, @RequestParam String
  // password, ModelMap model) {
  // try {
  // if (authenticationService.authenticate(name, password)) {
  // model.put("name", name);
  // return "welcome";
  // }
  // model.put("errorMessage", "Invalid Credentials!, Please try again.");
  // return "login";
  // } catch (Exception e) {
  // return e.toString();
  // }
  // }

  // @RequestMapping("/login2")
  // public String goToLoginPage2(@RequestParam String name, ModelMap model) {
  // model.put("name", name);
  // // System.out.println("The Request Param is " + name); // not recommended in
  // // prod code
  // logger.debug("Request param is {}", name);
  // logger.info("I want this printed at info level");
  // logger.warn("I want this printed at warn level");
  // return "login";
  // }

  // @RequestMapping("/login1")
  // public String goToLoginPage1() {
  // return "login";
  // }

}
