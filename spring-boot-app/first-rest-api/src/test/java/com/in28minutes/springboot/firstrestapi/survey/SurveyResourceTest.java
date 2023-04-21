package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = SurveyResource.class)
@AutoConfigureMockMvc(addFilters = false)
public class SurveyResourceTest {
    @MockBean
    private SurveyService surveyService;

    @Autowired
    MockMvc mockMvc;

    private static String SPECIFIC_QUESTION_URL = "http://localhost:8080/surveys/Survey1/questions/Question1";
    private static String ALL_QUESTION_URL = "http://localhost:8080/surveys/Survey1/questions";

    @Test
    void retrieveSpecificSurveyQuestion_404Scenario() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println(mvcResult.getResponse().getStatus());
        assertEquals(404, mvcResult.getResponse().getStatus()); // I implemented it
    }

    @Test
    void retrieveSpecificSurveyQuestion_basicScenario() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL)
                .accept(MediaType.APPLICATION_JSON);
        Question question = new Question("Question1", "Most Popular Cloud Platform Today",
                Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        String expectedResponse = """
                {"id":"Question1",
                "description":"Most Popular Cloud Platform Today",
                "correctAnswer":"AWS",
                "options":["AWS","Azure","Google Cloud","Oracle Cloud"]}
                  """;
        when(surveyService.retrieveSpecificSurveyQuestion("Survey1", "Question1")).thenReturn(question);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println(mvcResult.getResponse().getStatus());
        JSONAssert.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString(), false);
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    // GET - /surveys/{surveyId}/questions
    // @GetMapping("/surveys/{surveyId}/questions")
    // public List<Question> retrieveAllSurveyQuestions(@PathVariable String
    // surveyId) {
    // List<Question> questions =
    // this.surveyService.retrieveAllSurveyQuestions(surveyId);

    // if (questions == null) {
    // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    // }
    // return questions;
    // }
    @Test
    public void retrieveAllSurveyQuestions_basicScenario() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ALL_QUESTION_URL);
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question1", "Most Popular Cloud Platform Today",
                Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS"));
        questions.add(new Question("Question2", "Fastest Growing Cloud Platform",
                Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud"));
        questions.add(new Question("Question3", "Most Popular DevOps Tool",
                Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes"));
        String expectedResponse = """
                  [
                    {
                        "id": "Question1",
                        "description": "Most Popular Cloud Platform Today",
                        "correctAnswer": "AWS",
                        "options": [
                            "AWS",
                            "Azure",
                            "Google Cloud",
                            "Oracle Cloud"
                        ]
                    },
                    {
                        "id": "Question2",
                        "description": "Fastest Growing Cloud Platform",
                        "correctAnswer": "Google Cloud",
                        "options": [
                            "AWS",
                            "Azure",
                            "Google Cloud",
                            "Oracle Cloud"
                        ]
                    },
                    {
                        "id": "Question3",
                        "description": "Most Popular DevOps Tool",
                        "correctAnswer": "Kubernetes",
                        "options": [
                            "Kubernetes",
                            "Docker",
                            "Terraform",
                            "Azure DevOps"
                        ]
                    }
                ]
                    """;
        when(surveyService.retrieveAllSurveyQuestions("Survey1")).thenReturn(questions);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        System.out.println(response.getStatus());
        assertEquals(200, response.getStatus());
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false);
    }

    @Test
    public void addNewSurveyQuestion_basicScenario() throws Exception {
        String requestBody = """
                {
                    "description": "Your favourite cloud platform",
                    "correctAnswer": "GCP",
                    "options": [
                        "AWS",
                        "Azure",
                        "Google Cloud",
                        "Oracle Cloud"
                    ]
                }
                    """;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(ALL_QUESTION_URL)
                .accept(MediaType.APPLICATION_JSON).content(requestBody).contentType(MediaType.APPLICATION_JSON);
        when(surveyService.addNewSurveyQuestion(any(), anyString())).thenReturn("SOME_ID");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String locationHeader = mvcResult.getResponse().getHeader("Location");
        System.out.println(mvcResult.getResponse().getStatus());
        System.out.println(locationHeader);
        assertEquals(201, mvcResult.getResponse().getStatus());
        assertTrue(locationHeader.contains("/surveys/Survey1/questions/SOME_ID"));
    }

}
