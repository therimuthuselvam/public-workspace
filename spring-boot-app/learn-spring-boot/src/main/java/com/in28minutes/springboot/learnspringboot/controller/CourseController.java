package com.in28minutes.springboot.learnspringboot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.learnspringboot.service.Course;

@RestController
public class CourseController {

  @RequestMapping("/courses")
  public List<Course> retriveAllCourses() {
    return Arrays.asList(
        new Course(1, "Learn AWS", "in28minutes"),
        new Course(2, "Learn Azure", "in28minutes"),
        new Course(3, "Learn Devops", "in28minutes"));
  }

}
