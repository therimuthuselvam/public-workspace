package com.in28minutes.springboot.firstrestapi.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController
public class HelloWorldResource {

  @GetMapping("/hello-world")
  // @ResponseBody
  public String helloWorld() {
    return "Hello World!";
  }

  @GetMapping("/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello World!");
  }

  @GetMapping("/hello-world-path-param/{name}")
  public HelloWorldBean helloWorldPathParam(@PathVariable String name) {
    return new HelloWorldBean("Hello World! " + name);
  }

  @GetMapping("/hello-world-path-param/{name}/message/{message}")
  public HelloWorldBean helloWorldMultiplePathParam(@PathVariable String name, @PathVariable String message) {
    return new HelloWorldBean("Hello World! " + name + " " + message);
  }
}
