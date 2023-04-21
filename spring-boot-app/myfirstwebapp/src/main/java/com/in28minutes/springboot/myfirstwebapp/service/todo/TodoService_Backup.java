package com.in28minutes.springboot.myfirstwebapp.service.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.in28minutes.springboot.myfirstwebapp.bean.todo.Todo;

import jakarta.validation.Valid;

@Service
public class TodoService_Backup {

  private static List<Todo> todos = new ArrayList<>();
  private static int todosCount = 0;

  static {
    todos.add(new Todo(++todosCount, "in28minutes", "Get AWS Certified", LocalDate.now().plusYears(1), false));
    todos.add(new Todo(++todosCount, "in28minutes", "Learn Devops", LocalDate.now().plusYears(2), false));
    todos.add(
        new Todo(++todosCount, "in28minutes", "Learn Full Stack Development", LocalDate.now().plusYears(3), false));
  }

  // public List<Todo> findByUsername(String username) {
  // return todos;
  // }

  public List<Todo> findByUsername(String username) {
    Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
    return todos.stream().filter(predicate).toList();
  }

  public void addTodo(String userName, String description, LocalDate targeDate, boolean done) {

    Todo todo = new Todo(++todosCount, userName, description, targeDate, done);
    todos.add(todo);

  }

  public void deleteById(int id) {
    Predicate<? super Todo> predicate = todo -> todo.getId() == id;
    todos.removeIf(predicate);
  }

  public Todo findById(int id) {
    Predicate<? super Todo> predicate = todo -> todo.getId() == id;
    Todo todo = todos.stream().filter(predicate).findFirst().get();
    return todo;
  }

  public void updateTodo(@Valid Todo todo) {
    deleteById(todo.getId());
    todos.add(todo);
  }

}
