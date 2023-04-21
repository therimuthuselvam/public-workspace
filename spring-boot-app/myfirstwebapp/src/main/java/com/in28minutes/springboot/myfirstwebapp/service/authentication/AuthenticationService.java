package com.in28minutes.springboot.myfirstwebapp.service.authentication;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  public boolean authenticate(String name, String password) {
    try {
      return name.equals("in28minutes") && password.equals("dummy");
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

}
