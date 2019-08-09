package com.cdk.dc.hello;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class FortellisAPIResourceSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private World world;

    private String requestBody;


    @When("I request to update a given request to a given status")
    public void updateRequest() throws URISyntaxException { ;
        ResponseEntity<String> response = restTemplate
                .exchange(new URI("/admin/update-request"), HttpMethod.PATCH, new HttpEntity<>(this.requestBody), String.class);
        saveResponse(response);
    }


    @Given("I give a valid request body (.*)")
    public void validRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    @When("I make a request to post a request")
    public void postRequest() throws URISyntaxException {
        ResponseEntity<String> response = restTemplate
                .exchange(new URI("/client/add-request"), HttpMethod.POST, new HttpEntity<>(this.requestBody), String.class);
        saveResponse(response);
    }

    @Given("There are requests in the table")
    public void thereAreRequests() {}

    @When("I make a get request")
    public void makeGetRequest() throws URISyntaxException {
        ResponseEntity<String> response = restTemplate.exchange(new URI("/admin/requests"), HttpMethod.GET, null, String.class);
        saveResponse(response);
    }

    @When("I make a request to delete a given request (.*)")
    public void deleteRequest(String requestId) throws URISyntaxException {
        JSONObject jsonRequestBody = new JSONObject();
        jsonRequestBody.put("id", requestId);
        ResponseEntity<String> response = restTemplate.exchange(new URI("/test/remove-request"), HttpMethod.DELETE, new HttpEntity<>(jsonRequestBody.toJSONString()), String.class);
        saveResponse(response);
    }

    /**
     * Save the response to the world.
     *
     * @param responseEntity response
     */
    private void saveResponse(final ResponseEntity responseEntity) {
        world.setResponse(responseEntity);
    }

    @Given("I give an invalid request body (.*)")
    public void invalidRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
