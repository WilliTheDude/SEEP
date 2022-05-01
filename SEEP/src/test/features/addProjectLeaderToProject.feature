Feature: Add project leader to project

  Background:
    Given that there exists a project
    And the project has no project leader
    And that there exists a user named "user1"
    And that there exists a user named "user2"

   ## Adding a project leader to an existing project (Main scenario)
  Scenario: add user2 as project leader
    Given user named "user1" is part of the project
    And user named "user2" is part of the project
    When "user1" assigns "user2" as project leader
    Then "user2" is project leader of the project

   ## user1 isn't part of project
  Scenario: user1 isn't part of project
    Given user named "user1" isn't part of the project
    And user named "user2" is part of the project
    When "user1" assigns "user2" as project leader
    Then "user2" isn't project leader of the project
    Then the error message "User needs to be part of project to assign project leaders" is given

   ## user2 isn't part of project
  Scenario: user2 isn't part of project
    Given user named "user1" is part of the project
    And user named "user2" isn't part of the project
    When "user1" assigns "user2" as project leader
    Then "user2" is project leader of the project


  ## Another project leader is assigned to a project with a project leader
  Scenario: Another project leader is assigned to a project that already has one assigned
    Given the project has a project leader
    And user named "user1" is part of the project
    And user named "user2" is part of the project
    When "user1" assigns "user2" as project leader
    Then the error message "There can only be one project leader" is given
    And "user2" isn't project leader of the project

