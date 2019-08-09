@dc.cloud.svc.seed
Feature: Fortellis API

  As a consumer of the Fortellis API resource
  I should use the methods provided to me

  Scenario Outline: Valid post call
    Given I give a valid request body <body>
    When I make a request to post a request
    Then I should get a response with HTTP status code 202
    Examples:
      |body                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
      |{"id": "00f6252b-5aeb-dde4-3c99-37c5bdc55de8", "entityId": "a1d3b2c4-d53d-e1f3-c122-1234567890ab", "requestStatus": "Accepted","date": "11/11/18","phoneNumber": "(555) 555-5555","address": "Test Address","name": "Test Mcgee","storeId": "S77777777","storeName": "Test Store Name","connectionId": "12341234-5678-1234-5678-901234567890","solutionId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa","developer": "Test Developer","subscriptionId": "a1b2c3d4-e5f6-a1b2-c3d4-e5f6a1b2c3d4","email": "something@something.something","solutionName": "Test Solution"} |

  Scenario Outline: Invalid post call
    Given I give an invalid request body <body>
    When I make a request to post a request
    Then I should get a response with HTTP status code 400
    And The response should contain the message Invalid Request
    Examples:
      |body                                                                                                                                                                                                                                                                                                                                                                                     |
      |{"requestStatus": "Accepted","date": "11/11/18","phoneNumber": "(555) 555-5555","address": "Test Address","name": "Test Mcgee","storeId": "S77777777","storeName": "Test Store Name","connectionId": "12341234-5678-1234-5678-901234567890","solutionId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa","developer": "Test Developer","subscriptionId": "a1b2c3d4-e5f6-a1b2-c3d4-e5f6a1b2c3d4"} |


  Scenario Outline: Valid patch call
    Given I give a valid request body <body>
    When I request to update a given request to a given status
    Then I should get a response with HTTP status code 202
    Examples:
      |body                                                                      |
      |{"id": "00f6252b-5aeb-dde4-3c99-37c5bdc55de8","requestStatus": "Declined"}|

  Scenario Outline: Invalid patch call
    Given I give an invalid request body <body>
    When I request to update a given request to a given status
    Then I should get a response with HTTP status code 400
    And The response should contain the message Invalid Request
    Examples:
      |body                                                                      |
      |{"id": "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","requestStatus": "Declined"}|

  Scenario Outline: Get call
    Given There are requests in the table
    When I make a get request
    Then I should get a response with HTTP status code 200
    And The response should contain the message <item>
    Examples:
      |item                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
      |{"date":{"s":"11/11/18","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"address":{"s":"Test Address","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"solutionName":{"s":"Test Solution","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"entityId":{"s":"a1d3b2c4-d53d-e1f3-c122-1234567890ab","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"storeId":{"s":"S77777777","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"phoneNumber":{"s":"(555) 555-5555","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"name":{"s":"Test Mcgee","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"connectionId":{"s":"12341234-5678-1234-5678-901234567890","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"storeName":{"s":"Test Store Name","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"developer":{"s":"Test Developer","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"solutionId":{"s":"aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"id":{"s":"00f6252b-5aeb-dde4-3c99-37c5bdc55de8","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"subscriptionId":{"s":"a1b2c3d4-e5f6-a1b2-c3d4-e5f6a1b2c3d4","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"email":{"s":"something@something.something","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null},"requestStatus":{"s":"Declined","n":null,"b":null,"m":null,"l":null,"ss":null,"ns":null,"bs":null,"null":null,"bool":null}}    |

  Scenario Outline: Delete call
    Given There are requests in the table
    When I make a request to delete a given request <requestId>
    Then I should get a response with HTTP status code 202
    Examples:
      |requestId                            |
      |00f6252b-5aeb-dde4-3c99-37c5bdc55de8 |