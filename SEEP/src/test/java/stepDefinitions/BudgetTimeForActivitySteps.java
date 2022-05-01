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

    // Field
    ActivityHolder activity;
    EmployeeHolder employee;
    ProjectHolder project;
    ProjectManagementSystem projectManagementSystem;
    ErrorMessageHolder errorMessageHolder;
    double oldTime;
    double totalTime;
    Employee pl;

    // Constructor
    public BudgetTimeForActivitySteps(ActivityHolder activity, EmployeeHolder employee, ProjectHolder project,
                                      ProjectManagementSystem projectManagementSystem, ErrorMessageHolder errorMessageHolder) {
        this.activity = activity;
        this.employee = employee;
        this.project = project;
        this.projectManagementSystem = projectManagementSystem;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("there exist an activity")
    public void there_exist_an_activity() {
        activity.getActivity();
    }

    @When("the project leader budgets time for the activity")
    public void the_project_leader_budgets_time_for_the_activity() {
        double time = 2.4;
        oldTime = project.getProject().getTotalBudgetedTime();
        activity.getActivity().budgetTimeForActivity(time);
    }

    @Given("an activity with budgeted time")
    public void an_activity_with_budgeted_time() {
        oldTime = project.getProject().getTotalBudgetedTime();
        activity.getActivity().budgetTimeForActivity(7.4);
    }

    @When("the project leader changes the budget time for the activity")
    public void the_project_leader_changes_the_budget_time_for_the_activity() {
        oldTime = project.getProject().getTotalBudgetedTime();
        activity.getActivity().budgetTimeForActivity(1.4);
    }

    @When("the project leader deletes the budgeted time")
    public void the_project_leader_deletes_the_budgeted_time() {
        oldTime = project.getProject().getTotalBudgetedTime();
        activity.getActivity().deleteBudgetedTimeForActivity();
    }

    @Then("the time is added to the total time")
    public void the_time_is_added_to_the_total_time() {
        assertNotEquals(oldTime, project.getProject().getTotalBudgetedTime());
    }

    @Given("the project has budgeted time")
    public void a_project_with_budgeted_time() {
        project.getProject().updateTotalBudgetedTime(7.6);
    }

    @When("the project leader checks the total time")
    public void the_budget_leader_checks_the_total_time() {
        totalTime = project.getProject().getProjectLeader().getTotalProjectTime(project.getProject());
    }

    @Then("the the total time is received")
    public void the_the_total_time_is_received() {
        assertEquals(totalTime, project.getProject().getProjectLeader().getTotalProjectTime(project.getProject()));
    }

    @When("the user tires to budget time for the activity")
    public void the_user_tires_to_budget_time_for_the_activity() {
        try {
            employee.getEmployee().getTotalProjectTime(project.getProject());
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the activity isn't updated")
    public void the_activity_isn_t_updated() {
        assertEquals(oldTime, activity.getActivity().getBudgetedTime()); // Should be the same time.
    }

    @Then("the time isn't added to the total time")
    public void the_time_isn_t_added_to_the_total_time() {
        assertEquals(totalTime, project.getProject().getTotalBudgetedTime());
    }
}
