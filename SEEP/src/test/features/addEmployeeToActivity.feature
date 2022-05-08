Feature: Add employee to an activity
  Descriptions: An employee adds another employee to an activity
  Background:
    Given that there exists a project with name "project1"
    And that there exists an activity with name "activity1" in project "project1"
    And that there exists a user named "user1"
    And that there exists a user named "user2"
    And "user1" is logged in

  Scenario: An employee from this project and this activity adds another employee from this project to this activity
    Given "user1" is part of project "project1"
    And "user1" is part of activity "activity1"
    And "user2" is part of project "project1"
    When "user1" adds "user2" to activity "activity1"
    Then "user2" is assigned to activity "activity1"

  Scenario: The other employee is not assigned to this project
    Given "user1" is part of project "project1"
    And "user1" is part of activity "activity1"
    And "user2" is not part of project "project1"
    When "user1" adds "user2" to activity "activity1"
    Then "user2" is assigned to activity "activity1"

  Scenario: The employee is not assigned to this activity
    Given "user1" is part of project "project1"
    And "user1" is not part of activity "activity1"
    And "user2" is part of project "project1"
    When "user1" adds "user2" to activity "activity1"
    Then the error message "You don't have permission to add others to this activity" is given

  Scenario: The employee is not assigned to this project
    Given "user1" is not part of project "project1"
    And "user1" is part of activity "activity1"
    And "user2" is part of project "project1"
    When "user1" adds "user2" to activity "activity1"
    Then "user2" is assigned to activity "activity1"

