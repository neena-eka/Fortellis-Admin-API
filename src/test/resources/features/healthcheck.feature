@dc.cloud.svc.seed
Feature: Get health check

  Requesting the healthcheck endpoint
  I should be able to "status up"

  Scenario: get positive healthcheck
    When I request the healthcheck url
    Then I should get a response with HTTP status code 200
    And The response should contain the message {"status":"UP"}

  Scenario: Check info
    When I request the info url
    Then I should get a response with HTTP status code 200
    And The response should contain the message description
    And The response should contain the message name
