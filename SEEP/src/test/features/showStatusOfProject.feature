Feature: Show project status
  Descriptions: The project leader choose to see project status
  Background:
    Given that there exists a project with name "project1"
    And that there exists a user named "user1"
    And "user1" is logged in

  Scenario: The project leader choose to see status on a project, that they are assigned to
    Given "project1" has "user1" as project leader
    When user enters "project1"
    And "user1" choose to see project status on "project1"
    Then "user1" is shown the project status connected to "project1"

  Scenario: The project leader choose to see status on a project, that they are not assigned to
    Given "user1" is not a project leader on "project1"
    When "user1" choose to see project status on "project1"
    Then the error message "You are not project leader on this project and can therefore not see status" is given