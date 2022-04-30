Feature: Change info of activity
  Descriptions: An employee changes the information of an activity
  Background:
    Given that there exists a project with name "project1"
    And that there exists a user named "user1"
    And "user1" is logged in
    And "project1" has an activity with name "activity1"
    And actor has entered the info change state of "activity1"

  Scenario: The employee chooses to change the information of an activity on a project they are assigned to
    Given actor sets the name of the activity to a valid name
    And actor sets planned time to a valid time
    And actor sets description
    When actor confirms changes
    Then the changes are made

  Scenario: The employee changes the name to a name that is already the name of an existing activity
    Given actor sets the name of the activity to an invalid name that is already used
    When actor confirms changes
    Then the error message "This name is already used, please enter another" is given

  Scenario: The employee changes the name to blank
    Given actor sets the name of the activity to blank
    When actor confirms changes
    Then the error message "Please enter a name" is given

  Scenario: The employee changes the planned time to have an end date before the start date
    Given actor sets planned time to an invalid time with start time later than end time
    When actor confirms changes
    Then the error message "The entered times are not valid, please enter valid times" is given