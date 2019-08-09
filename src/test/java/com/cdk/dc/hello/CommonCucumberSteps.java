package com.cdk.dc.hello;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class CommonCucumberSteps {

  @Autowired
  private World world;

  /**
   * check the status code.
   * @param statusCode int
   */
  @Then("I should get a response with HTTP status code (\\d+)")
  public void shouldGetResponseWithHttpStatusCode(int statusCode) {
    final ResponseEntity response = world.getResponse();
    assertThat(response.getStatusCodeValue(), is(statusCode));
  }

  /**
   * compare the response message.
   * @param message String
   */
  @And("The response should contain the message (.*)")
  public void theResponseShouldContainTheMessage(String message) {
    final ResponseEntity response = world.getResponse();
    assertThat(Objects.requireNonNull(response.getBody()).toString(), containsString(message));
  }
}
