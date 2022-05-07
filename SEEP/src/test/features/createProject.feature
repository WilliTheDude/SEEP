## The Scenarios that needs to be implemented
  ## Create a project in general (Main scenario)
  ## Creating a project with only a description
  ## Checks if the user becomes a part of the project upon creation
  ## Unauthorised user tries to create a project
  ## Adding a project leader to the project
  ## Another project leader is assigned to a project with a project leader
  ## Two projects that are given the same ID
  ## Two projects that are given the same name
  ## A project with no PL should be accessible for all users
  ## When a Pl i assigned to the project only assigned users have

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

  ## Unauthorised user tires to create a project
  Scenario: A unauthorised user tries to create a project
    Given that there exists a user named "Bob Bobsen"
    And the User is unauthorised to create a project
    When the user creates a valid project
    Then the error message "You are unauthorised to create a project" is given

  ## Two projects that are given the same name
  Scenario: Two projects that are given the same name
    Given that there exist a user
    And that there exists a project with name "Project1"
    When the user creates a new project with name "Project2"
    Then the project is created
