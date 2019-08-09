@dc.cloud.svc.seed
Feature: Get root resource

  As a consumer of the / resource
  I should be able to get hello world

  Scenario: Get hello world at base url
    When I request the base url
    Then I should get a response with HTTP status code 200
    And The response should contain the message Hi
