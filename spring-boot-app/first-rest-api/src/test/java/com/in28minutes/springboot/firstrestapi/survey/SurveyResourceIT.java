package com.in28minutes.springboot.firstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Base64;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {

  private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
  private static String GENERIC_QUESTION_URL = "/surveys/Survey1/questions";
  private static String ALL_SURVEYS_URL = "/surveys";
  private static String SPECIFIC_SURVEYS_URL = "/surveys/Survey1";

  @Autowired
  private TestRestTemplate template;

  String str = """
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
          }
      """;

  @Test
  public void retrieveSpecificSurveyQuestion_basicScenario() throws JSONException {
    HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
    HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> responseEntity = template.exchange(SPECIFIC_QUESTION_URL, HttpMethod.GET, httpEntity,
        String.class);

    // ResponseEntity<String> responseEntity =
    // template.getForEntity(SPECIFIC_QUESTION_URL, String.class); //enable this
    // and comment above, if there is no spring security.

    // System.out.println(responseEntity.getBody());
    // below is the output of the above
    // {"id":"Question1","description":"Most Popular Cloud Platform
    // Today","correctAnswer":"AWS","options":["AWS","Azure","Google
    // Cloud","Oracle Cloud"]}

    String expectedResponse = """
        {
          "id":"Question1",
          "description":"Most Popular Cloud Platform Today",
          "correctAnswer":"AWS"
        }
        """;
    // assertEquals(expectedResponse.trim(), responseEntity.getBody());
    // status -.headers -> body
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);

    System.out.println(responseEntity.getHeaders());
    // below is the output of the above
    // [Content-Type:"application/json", Transfer-Encoding:"chunked",
    // Date:"Sun, 02 Apr 2023 09:48:52 GMT", Keep-Alive:"timeout=60",
    // Connection:"keep-alive"]

  }

  @Test
  public void retrieveAllSurveyQuestions_basicScenario() throws JSONException {
    HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
    HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> responseEntity = template.exchange(GENERIC_QUESTION_URL, HttpMethod.GET, httpEntity,
        String.class);
    // ResponseEntity<String> responseEntity =
    // template.getForEntity(GENERIC_QUESTION_URL, String.class); //enable this
    // and comment above, if there is no spring security.

    // String expectedResponse = """
    // [
    // {
    // "id": "Question1",
    // "description": "Most Popular Cloud Platform Today",
    // "correctAnswer": "AWS",
    // "options": [
    // "AWS",
    // "Azure",
    // "Google Cloud",
    // "Oracle Cloud"
    // ]
    // },
    // {
    // "id": "Question2",
    // "description": "Fastest Growing Cloud Platform",
    // "correctAnswer": "Google Cloud",
    // "options": [
    // "AWS",
    // "Azure",
    // "Google Cloud",
    // "Oracle Cloud"
    // ]
    // },
    // {
    // "id": "Question3",
    // "description": "Most Popular DevOps Tool",
    // "correctAnswer": "Kubernetes",
    // "options": [
    // "Kubernetes",
    // "Docker",
    // "Terraform",
    // "Azure DevOps"
    // ]
    // }
    // ]

    // """; Since this is very big, we need to check only the important part, hence
    // I'm rewriting the expected response as below
    String expectedResponse = """
          [
            {
                "id": "Question1"
            },
            {
                "id": "Question2"
            },
            {
                "id": "Question3"
            }
        ]

            """;
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
  }

  @Test
  public void retrieveAllSurveys_basicScenario() throws JSONException {
    HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
    HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> responseEntity = template.exchange(ALL_SURVEYS_URL, HttpMethod.GET, httpEntity,
        String.class);
    // ResponseEntity<String> responseEntity =
    // template.getForEntity(ALL_SURVEYS_URL, String.class); //enable this
    // and comment above, if there is no spring security.
    String expectedResponse = """
          [
            {
                "id": "Survey1",
                "title": "My Favorite Survey",
                "description": "Description of the Survey",
                "questions": [
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
            }
        ]

            """;
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
  }

  @Test
  public void retrieveSurveyById_basicScenario() throws JSONException {
    HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
    HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
    ResponseEntity<String> responseEntity = template.exchange(SPECIFIC_SURVEYS_URL, HttpMethod.GET, httpEntity,
        String.class);
    // ResponseEntity<String> responseEntity =
    // template.getForEntity(SPECIFIC_SURVEYS_URL, String.class); //enable this
    // and comment above, if there is no spring security.
    String expectedResponse = """
                {
                  "id": "Survey1",
                  "title": "My Favorite Survey",
                  "description": "Description of the Survey"
        } """;
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
  }

  @Test
  public void addNewSurveyQuestion_basicScenario() {
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

    // HttpHeaders headers = new HttpHeaders();
    // headers.add("Content-Type", "application/json");
    // headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ="); // enable this or
    // below, both should not be enabled at a time
    // headers.add("Authorization", "Basic " + performBasicAuthEncoding("admin",
    // "password"));
    HttpHeaders headers = createHttpContentTypeAndAuthorizationHeaders();
    HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
    ResponseEntity<String> responseEntity = template.exchange(GENERIC_QUESTION_URL, HttpMethod.POST, httpEntity,
        String.class);
    System.out.println(responseEntity.getHeaders());
    // [Location:"http://localhost:64554/surveys/Survey1/questions/2322896121",
    // Content-Length:"0", Date:"Tue, 04 Apr 2023 20:16:08 GMT",
    // Keep-Alive:"timeout=60", Connection:"keep-alive"]
    System.out.println(responseEntity.getBody());
    // null
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    String locationHeader = responseEntity.getHeaders().get("Location").get(0);
    assertTrue(locationHeader.contains("/surveys/Survey1/questions/"));
    // Since creating new questions impacting other tests we need to delete the
    // question, once it has been checked above.
    ResponseEntity<String> responseEntityDelete = template.exchange(locationHeader, HttpMethod.DELETE, httpEntity,
        String.class);
    assertTrue(responseEntityDelete.getStatusCode().is2xxSuccessful());
    // template.delete(locationHeader); //enable this
    // and comment above, if there is no spring security.
  }

  private HttpHeaders createHttpContentTypeAndAuthorizationHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    // headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
    headers.add("Authorization", "Basic " + performBasicAuthEncoding("admin", "password"));
    return headers;
  }

  String performBasicAuthEncoding(String user, String password) {
    String combined = user + ":" + password;
    byte[] encodedBytes = Base64.getEncoder().encode(combined.getBytes());
    return new String(encodedBytes);
  }
}
