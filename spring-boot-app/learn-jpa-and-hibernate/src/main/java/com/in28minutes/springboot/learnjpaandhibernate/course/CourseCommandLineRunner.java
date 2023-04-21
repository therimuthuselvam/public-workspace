package com.in28minutes.springboot.learnjpaandhibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learnjpaandhibernate.course.jdbc.CourseJdbcRepository;
import com.in28minutes.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;

@Component // remove the comment to run this class
public class CourseCommandLineRunner implements CommandLineRunner {

    // <---Spring Data Jpa--->
    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1, "Learn AWS SpringDataJpa!", "in28minutes"));
        repository.save(new Course(2, "Learn Azure SpringDataJpa!", "in28minutes"));
        repository.save(new Course(3, "Learn Devops SpringDataJpa!", "in28minutes"));
        repository.save(new Course(4, "Learn Full Stack SpringDataJpa!", "in28minutes"));

        System.out.println("------------------");
        repository.deleteById(1l);

        System.out.println("------------------");
        System.out.println(repository.findById(2l));
        System.out.println(repository.findById(3l));
        System.out.println(repository.findById(4l));

        System.out.println("------------------");
        System.out.println(repository.findAll());
        System.out.println(repository.count());

        System.out.println("------------------");
        System.out.println(repository.findByAuthor("in28minutes"));
        System.out.println(repository.findByAuthor(""));

        System.out.println("------------------");
        System.out.println(repository.findByName("Learn Devops SpringDataJpa!"));
        System.out.println(repository.findByName("Learn Full Stack SpringDataJpa!"));

    }

    // <---JPA--->
    // @Autowired
    // private CourseJpaRepository repository;

    // @Override
    // public void run(String... args) throws Exception {

    // repository.insert(new Course(1, "Learn AWS - JPA", "in28minutes"));
    // repository.insert(new Course(2, "Learn Azure - JPA", "in28minutes"));
    // repository.insert(new Course(3, "Learn Devops - JPA", "in28minutes"));

    // repository.deleteById(1l);

    // System.out.println(repository.findById(2l));
    // System.out.println(repository.findById(3l));
    // }

    // <---JDBC--->
    // @Autowired
    // private CourseJdbcRepository repository;

    // @Override
    // public void run(String... args) throws Exception {
    // repository.insert(new Course(1, "Learn AWS - JDBC", "in28minutes"));
    // repository.insert(new Course(2, "Learn Azure JDBC", "in28minutes"));
    // repository.insert(new Course(3, "Learn Devops - JDBC", "in28minutes"));

    // repository.deleteById(1l);

    // System.out.println(repository.findById(2l));
    // System.out.println(repository.findById(3l));
    // }

}
