//William
package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
// Junit imports
import static org.junit.Assert.*;

// Class import
import projectManagementSystem.*;

public class BudgetTimeForActivitySteps {
    // Fields
    private final ActivityHolder activity;
    private final EmployeeHolder employee;
    private final ProjectHolder project;
    private ErrorMessageHolder errorMessageHolder;
    private double oldTime;
    private double totalTime;

    // Constructor
    public BudgetTimeForActivitySteps(ActivityHolder activity, EmployeeHolder employee, ProjectHolder project, ErrorMessageHolder errorMessageHolder) {
        this.activity = activity;
        this.employee = employee;
        this.project = project;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("there exist an activity")
    public void there_exist_an_activity() {
        activity.getActivity();
    }

    @When("the project leader budgets time for the activity")
    public void the_project_leader_budgets_time_for_the_activity() {
        double time = 2.4;
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().budgetTimeForActivity(time);
        }
        catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("an activity with budgeted time")
    public void an_activity_with_budgeted_time() {
        ProjectManagementSystem.setLoggedInEmployee(employee.getEmployee());
        try {
            activity.getActivity().budgetTimeForActivity(7.4);
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader changes the budget time for the activity")
    public void the_project_leader_changes_the_budget_time_for_the_activity() {
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().budgetTimeForActivity(1.4);
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @When("the project leader deletes the budgeted time")
    public void the_project_leader_deletes_the_budgeted_time() {
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().deleteBudgetedTimeForActivity();
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the time is added to the total time")
    public void the_time_is_added_to_the_total_time() {
        assertNotEquals(oldTime, activity.getActivity().getBudgetedTime());
    }

    @Given("the project has budgeted time")
    public void a_project_with_budgeted_time() {
        try {
            project.getProject().updateTotalBudgetedTimeForProject(7.6);
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader checks the total time")
    public void the_budget_leader_checks_the_total_time() {
        try {
            totalTime = project.getProject().getProjectLeader().getTotalProjectTime(project.getProject());
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the the total time is received")
    public void the_the_total_time_is_received() {
        assertEquals(totalTime, project.getProject().getProjectLeader().getTotalProjectTime(project.getProject()), 0.0);
    }

    @When("the user tires to budget time for the activity")
    public void the_user_tires_to_budget_time_for_the_activity() {
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().budgetTimeForActivity(7.2);
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the user tries to delete budgeted time for the activity")
    public void the_user_tries_to_delete_budgeted_time_for_the_activity() {
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().deleteBudgetedTimeForActivity();
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the user tires to update the budgeted time for a project")
    public void the_user_tires_to_update_the_budgeted_time_for_a_project() {
        oldTime = project.getProject().getTotalBudgetedTimeForProject();
        try {
            project.getProject().updateTotalBudgetedTimeForProject(7.6);
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project time isn't updated")
    public void the_project_time_isn_t_updated() {
      assertEquals(oldTime, project.getProject().getTotalBudgetedTimeForProject(), 0.0);
    }

    @Then("the activity isn't updated")
    public void the_activity_isn_t_updated() {
        assertEquals(oldTime, activity.getActivity().getBudgetedTime(), 0.0); // Should be the same time.
    }

    @Then("the time isn't added to the total time")
    public void the_time_isn_t_added_to_the_total_time() {
        assertEquals(totalTime, project.getProject().getTotalBudgetedTimeForProject(),0.0);
    }
}
