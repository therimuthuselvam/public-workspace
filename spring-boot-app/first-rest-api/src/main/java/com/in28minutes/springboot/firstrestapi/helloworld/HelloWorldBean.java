package com.in28minutes.springboot.firstrestapi.helloworld;

public class HelloWorldBean {
  private String message;

  HelloWorldBean(String message) {
    this.message = message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
