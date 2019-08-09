package com.cdk.dc.hello;

import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

public class HealthCheckSteps {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private World world;

  private ResponseEntity<String> response; // output

  /**
   * test healthcheck end point.
   */
  @When("^I request the healthcheck url$")
  public void requestTheHealthcheckUrl() {
    response = restTemplate
        .getForEntity("http://localhost:" + this.port + "/health", String.class);

    saveResponse(response);
  }

  /**
   * test info url.
   */
  @When("^I request the info url$")
  public void  requestInfoUrl() {
    response = restTemplate
        .getForEntity("http://localhost:" + this.port + "/info", String.class);

    saveResponse(response);
  }

  /**
   * Save the response to the world.
   * @param responseEntity response
   */
  private void saveResponse(final ResponseEntity responseEntity) {
    world.setResponse(responseEntity);
  }

}
