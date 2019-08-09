package com.cdk.dc.hello;

import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration // Don't ask
public class HomeResourceSteps {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private World world;

  /**
   * request greeting.
   */
  @When("I request the base url")
  public void requestGreeting() {
    // output
    ResponseEntity<String> response = restTemplate
        .getForEntity("http://localhost:" + this.port + "/admin/", String.class);

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
