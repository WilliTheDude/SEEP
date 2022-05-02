Feature: White Box Test
  Description: White box tests for functions.

  Scenario: createActivity A
    Given that there exists a project with name "project1"
    And that there exists a user named "user1"
    And "project1" has "user1" as project leader
    And "user1" is logged in