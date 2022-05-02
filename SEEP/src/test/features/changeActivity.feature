Feature: Change info of activity
  Descriptions: An employee changes the information of an activity
  Background:
    Given that there exists a project with name "project1"
    And that there exists a user named "user1"
    And "user1" is logged in
    And "project1" has an activity with name "activity1"
    And "project1" has an activity with name "activity2"

  Scenario: The employee chooses to change the information of an activity on a project they are assigned to
    When actor changes the name of the activity to "activity3"
    Then the changes are made

  Scenario: The employee changes the name to a name that is already the name of an existing activity
    When actor changes the name of the activity to "activity2"
    Then the error message "This name is already used, please enter another" is given

  Scenario: The employee changes the name to blank
    When actor changes the name of the activity to ""
    Then the error message "Please enter a name" is given

