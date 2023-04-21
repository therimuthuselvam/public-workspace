package com.in28minutes.springboot.firstrestapi.survey;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.tools.DocumentationTool.Location;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SurveyResource {

  private SurveyService surveyService;

  SurveyResource(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @GetMapping("/surveys")
  public List<Survey> retrieveAllSurveys() {
    return this.surveyService.retrieveAllSurveys();
  }

  @GetMapping("/surveys/{surveyId}")
  public Survey retrieveSurveyById(@PathVariable String surveyId) {
    Survey survey = this.surveyService.retrieveSurveyById(surveyId);
    if (survey == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return survey;
  }

  @GetMapping("/surveys/{surveyId}/questions")
  public List<Question> retrieveAllSurveyQuestions(@PathVariable String surveyId) {
    List<Question> questions = this.surveyService.retrieveAllSurveyQuestions(surveyId);

    if (questions == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return questions;
  }

  @GetMapping("/surveys/{surveyId}/questions/{questionId}")
  public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
    Question question = this.surveyService.retrieveSpecificSurveyQuestion(surveyId, questionId);

    if (question == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return question;
  }

  @PostMapping("/surveys/{surveyId}/questions")
  public ResponseEntity<Object> addNewSurveyQuestion(@RequestBody Question question, @PathVariable String surveyId) {
    String questionId = this.surveyService.addNewSurveyQuestion(question, surveyId);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").buildAndExpand(questionId)
        .toUri(); // location is a header
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/surveys/{surveyId}/questions/{questionId}")
  public ResponseEntity<Object> deleteSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
    String deletedQuestionId = this.surveyService.deleteSurveyQuestion(surveyId, questionId);
    if (deletedQuestionId == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/surveys/{surveyId}/questions/{questionId}")
  public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId,
      @RequestBody Question question) {
    this.surveyService.updateSurveyQuestion(surveyId, questionId, question);
    return ResponseEntity.noContent().build();
  }

}
