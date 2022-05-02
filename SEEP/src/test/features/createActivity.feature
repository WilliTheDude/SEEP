Feature: Create activity
  Descriptions: A project leader creates a new activity for a project
  Background:
    Given that there exists a project with name "project1"
    And that there exists a user named "user1"
    And "project1" has "user1" as project leader
    And "user1" is logged in

  Scenario: The project leader creates an activity with a valid name and no other info
    When actor creates activity with name "activity1" and description "This is an activity"
    Then the activity is saved to list of activities

  Scenario: The project leader creates an activity with a valid name and description
    When actor creates activity with name "activity1" and description ""
    Then the activity is saved to list of activities

  Scenario: The project leader creates an activity with an invalid name that is already used
    Given there exist an activity with name "activity1"
    When actor creates activity with name "activity1" and description "This is an activity"
    Then the error message "This name is already used, please enter another" is given

  Scenario: The project leader creates an activity with a blank name
    When actor creates activity with name "" and description "This is an activity"
    Then the error message "Please enter a name" is given

  Scenario: The actor is not project leader on the project they are trying to create an activity on
    Given actor is not project leader on this project
    When actor creates activity with name "activity1" and description "This is an activity"
    Then the error message "You do not have authority to create activities on this project" is given