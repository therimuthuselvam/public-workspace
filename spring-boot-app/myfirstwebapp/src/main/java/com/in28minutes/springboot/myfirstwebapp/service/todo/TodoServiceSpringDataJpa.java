package com.in28minutes.springboot.myfirstwebapp.service.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.myfirstwebapp.bean.todo.Todo;
import com.in28minutes.springboot.myfirstwebapp.repository.todo.TodoSpringDataJpaRepository;

import jakarta.validation.Valid;

@Service
public class TodoServiceSpringDataJpa {

  @Autowired
  TodoSpringDataJpaRepository todoSpringDataJpaRepository;

  private static List<Todo> todos = new ArrayList<>();
  private static int todosCount = 0;

  public List<Todo> findByUsername(String username) {
    List<Todo> todos = todoSpringDataJpaRepository.findByUsername(username);
    return todos;
  }

  public void addTodo(Todo todo) {
    todoSpringDataJpaRepository.save(todo);
  }

  public void deleteById(int id) {
    todoSpringDataJpaRepository.deleteById(id);
  }

  public Todo findById(int id) {
    Todo todo = todoSpringDataJpaRepository.findById(id).get();
    return todo;
  }

  public void updateTodo(@Valid Todo todo) {
    todoSpringDataJpaRepository.save(todo);
  }

}
