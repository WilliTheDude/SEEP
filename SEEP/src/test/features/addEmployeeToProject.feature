Feature: Add employee to project

  Background:
    Given that there exists a project with name "project1"
    And that there exists a user named "user1"
    And that there exists a user named "user2"

  Scenario: project leader adds employee to project
    Given "project1" has "user1" as project leader
    When "user1" adds "user2" to project "project1"
    Then "user2" is added to project "project1"


  Scenario: project leader isn't leader of project
    Given "project1" has no project leader
    When "user1" adds "user2" to project "project1"
    Then the error message "user isn't project leader of chosen project" is given
    And "user2" isn't added to project "project1"

