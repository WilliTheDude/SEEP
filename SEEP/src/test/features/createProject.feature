#William
Feature: Create a project
  Description: Users can create projects for different situations, and tasks
  Actor: User

## Create a project in general (Main scenario)
  Scenario: A user creates a new project
    Given that there exists a user named "Bob Bobsen"
    And the user has ID "bob"
    When the user creates a new project with name "bobBobsensProject"
    Then the project is created

  Scenario: A user creates a project only with a description
    Given that there exists a user named "Bob Bobsen"
    And the user has ID "bob"
    When the user creates a project with description "Hello World!"
    Then the error message "The project must have a name" is given


  ## Checks if the user becomes a part of the project upon creation
  Scenario: A project is created and the user is a part of the project
    Given that there exists a user named "Bob Bobsen"
    And the user has ID "bob"
    When the user creates a valid project
    Then the project is created
    Then the creating user is a part of the project

  ## Two projects that are given the same name
  Scenario: Two projects that are given the same name
    Given that there exist a user
    And that there exists a project with name "Project1"
    When the user creates a new project with name "Project2"
    Then the project is created
