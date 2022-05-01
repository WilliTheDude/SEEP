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
    Given the project has a project leader
    And there exist an activity
    When the project leader budgets time for the activity
    Then the activity time is updated
    And the time is added to the total time

  Scenario: Change budget time for an activity
    Given the project has a project leader
    And an activity with budgeted time
    When the project leader changes the budget time for the activity
    Then the activity time is updated
    And the time is added to the total time

  Scenario: Delete budget time
    Given the project has a project leader
    And an activity with budgeted
    When the project leader deletes the budgeted time
    Then the activity time is updated
    And the time is added to the total time

  Scenario: Get total budget time
    Given the project has a project leader
    And activities with budgeted time
    When the budget leader checks the total time
    Then the the total time is received

  Scenario: Can an employee budget time
    Given that there exist a user
    And there exist an activity
    When the user tires to budget time for the activity
    Then the error message "only the project manager can budget time for an activity" is given
    And the activity isn't updated
    And the time isn't added to the total time