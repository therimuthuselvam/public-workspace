package com.in28minutes.springboot.firstrestapi.survey;

import java.util.List;

public class Question {
  private String id;
  private String description;
  private String correctAnswer;
  private List<String> options;

  // public Question() {
  // }

  Question(String id, String description, List<String> options, String correctAnswer) {
    this.id = id;
    this.description = description;
    this.correctAnswer = correctAnswer;
    this.options = options;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(String correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  @Override
  public String toString() {
    return "Question [id=" + id + ", description=" + description + ", correctAnswer=" + correctAnswer + ", options="
        + options + "]";
  }

}
