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

  Scenario: Adding time to an activity
    Given there exist a project manager
    And a project with activities
    When the project leader select an activity
    And budget time for the chosen activity
    Then the time is budgeted for the activity
    And the activity is updated
    And the time is added to the total time.

  Scenario: Change budget time for an activity
    Given there exist a project manager
    And an activity with budgeted time
    When the project manager chooses the activity
    And changes the budget time
    Then the time is budgeted for the activity
    And the project is updated
    And the time is added to the total time

  Scenario: Delete budget time
    Given there exist a project manager
    And an activity with budget time
    When the project manager deletes the budget time
    Then the time is deleted
    And the activity is updated
    And the time is added to the total time

  Scenario: Get total budget time
    Given there exist a project manager
    And a project with activities with budget time
    When the project leader checks the total time
    Then he gets the total time

  Scenario: Can an employee budget time
    Given there exist an employee
    And a project with activities
    When the employee tries to budget time
    Then the error message "only the project manager can budget time" is given
    And the times isn't budget
    And the project isn't updated


    ##TODO: when deleting an activity, then the budget time is deleted as well
    #TODO: Can you change time on a closed activity