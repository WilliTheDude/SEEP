## Scenarios to be implemente
  # Budgeting time for an activity
  # Change budget time
  # Delete budget time
  # Get total amount of budget time
  # Can an employee budget time for an activity
  # When deleting an activity does the time gets deleted as well
  # Can you change the time on a closed activity

Feature: Budgeting time for activities
  Description: The project manager can budget time for the different activities so the
  project manager has an overview of the time the project is going to take.
  Actor: Project manager

  Scenario: Budget time for an activity
    Given that there exists a project with name "project1"
    And that there exists a user named "project leader"
    And "project leader" is logged in
    And "project1" has "project leader" as project leader
    And that there exists an activity with name "activity1" in project "project1"
    When the project leader budgets time for the activity
    Then the time is added to the total time

  Scenario: Change budget time for an activity
    Given that there exists a project with name "project1"
    And that there exists a user named "project leader"
    And "project leader" is logged in
    And "project1" has "project leader" as project leader
    And that there exists an activity with name "activity2" in project "project1"
    And an activity with budgeted time
    When the project leader changes the budget time for the activity
    Then the time is added to the total time

  Scenario: Delete budget time
    Given that there exists a project with name "project1"
    And that there exists a user named "project leader"
    And "project leader" is logged in
    And "project1" has "project leader" as project leader
    And that there exists an activity with name "activity2" in project "project1"
    And an activity with budgeted time
    When the project leader deletes the budgeted time
    Then the time is added to the total time

  Scenario: Get total budget time for project
    Given that there exists a project with name "project1"
    And that there exists a user named "project leader"
    And "project leader" is logged in
    And "project1" has "project leader" as project leader
    And the project has budgeted time
    When the project leader checks the total time
    Then the the total time is received

  Scenario: Can an employee budget time
    Given that there exists a user named "Bob bobsen"
    And that there exists a user named "project leader"
    And "Bob bobsen" is logged in
    And that there exists a project with name "project1"
    And "project1" has "project leader" as project leader
    And that there exists an activity with name "activity2" in project "project1"
    When the user tires to budget time for the activity
    Then the error message "It's only the project leader that can budget time for activities" is given
    And the activity isn't updated
    And the time isn't added to the total time

  Scenario: Can an employee delete budget time
    Given that there exists a user named "Bob bobsen"
    And that there exists a user named "project leader"
    And "Bob bobsen" is logged in
    And that there exists a project with name "project1"
    And "project1" has "project leader" as project leader
    And that there exists an activity with name "activity2" in project "project1"
    When the user tries to delete budgeted time for the activity
    Then the error message "It's only the project leader that can delete budgeted time for activities" is given
    And the activity isn't updated
    And the time isn't added to the total time

    Scenario: A user tries to update the budgeted for a project
      Given that there exists a user named "Bob Bobsen"
      And that there exists a user named "Jens Jensen"
      And "Bob Bobsen" is logged in
      And that there exists a project with name "project1"
      And "project1" has "Jens Jensen" as project leader
      When the user tires to update the budgeted time for a project
      Then the error message "It's only the project leader there can update budgeted time for a project" is given
      And the project time isn't updated