Feature: Create activity
  Descriptions: A project leader creates a new activity for a project
  Background:
    Given that there exists a project with name "project1"
    And that there exists a user named "user1"
    And "project1" has "user1" as project leader
    And "user1" is logged in
    And actor has entered the create activity state for "project1"

  Scenario: The project leader creates an activity with a valid name and no other info
    Given actor sets the name of the activity to a valid name
    When actor confirms creation
    Then the activity is saved to list of activities

  Scenario: The project leader creates an activity with a valid name, time and description
    Given actor sets the name of the activity to a valid name
    And actor sets planned time to a valid time
    And actor sets description
    When actor confirms creation
    Then the activity is saved to list of activities

  Scenario: The project leader creates an activity with an invalid name
    Given actor sets the name of the activity to an invalid name that is already used
    When actor confirms creation
    Then the error message "This name is already used, please enter another" is given

  Scenario: The project leader creates an activity with a blank name
    Given actor sets the name of the activity to blank
    When actor confirms creation
    Then the error message "Please enter a name" is given

  Scenario: The project leader creates an activity with invalid planned time
    Given actor sets planned time to an invalid time
    When actor confirms changes
    Then the error message "The entered times are not valid, please enter valid times" is given

  Scenario: The actor is not project leader on the project they are trying to create an activity on
    Given actor is not project leader on this project
    When actor tries to create an activity
    Then the error message "You do not have authority to create activitieson this project" is given