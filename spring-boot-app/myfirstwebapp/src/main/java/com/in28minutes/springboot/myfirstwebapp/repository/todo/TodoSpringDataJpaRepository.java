package com.in28minutes.springboot.myfirstwebapp.repository.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.myfirstwebapp.bean.todo.Todo;

@Repository
public interface TodoSpringDataJpaRepository extends JpaRepository<Todo, Integer> {

  public List<Todo> findByUsername(String username);

}
