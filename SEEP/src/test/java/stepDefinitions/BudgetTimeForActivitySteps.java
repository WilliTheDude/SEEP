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
    ErrorMessageHolder errorMessageHolder;
    double oldTime;
    double totalTime;
    Employee pl;

    // Constructor
    public BudgetTimeForActivitySteps(ActivityHolder activity, EmployeeHolder employee, ProjectHolder project, ErrorMessageHolder errorMessageHolder) {
        this.activity = activity;
        this.employee = employee;
        this.project = project;
        this.errorMessageHolder = errorMessageHolder;
    }

    // Check
    @Given("there exist an activity")
    public void there_exist_an_activity() {
        activity.getActivity();
    }

    // Cehck
    @When("the project leader budgets time for the activity")
    public void the_project_leader_budgets_time_for_the_activity() {
        ProjectManagementSystem.setLoggedInEmployee(employee.getEmployee());
        double time = 2.4;
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().budgetTimeForActivity(time);
        }
        catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Check
    @Given("an activity with budgeted time")
    public void an_activity_with_budgeted_time() {
        ProjectManagementSystem.setLoggedInEmployee(employee.getEmployee());
        try {
            activity.getActivity().budgetTimeForActivity(7.4);
            System.out.println(activity.getActivity().getBudgetedTime());
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Check
    @When("the project leader changes the budget time for the activity")
    public void the_project_leader_changes_the_budget_time_for_the_activity() {
        ProjectManagementSystem.setLoggedInEmployee(employee.getEmployee()); // sets the project leader
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().budgetTimeForActivity(1.4);
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    // Check
    @When("the project leader deletes the budgeted time")
    public void the_project_leader_deletes_the_budgeted_time() {
        ProjectManagementSystem.setLoggedInEmployee(employee.getEmployee());
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().deleteBudgetedTimeForActivity();
            System.out.println(oldTime + " " + activity.getActivity().getBudgetedTime());
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    // Check
    @Then("the time is added to the total time")
    public void the_time_is_added_to_the_total_time() {
        assertNotEquals(oldTime, activity.getActivity().getBudgetedTime());
    }

    @Given("the project has budgeted time")
    public void a_project_with_budgeted_time() {
        ProjectManagementSystem.setLoggedInEmployee(employee.getEmployee());
        try {
            project.getProject().updateTotalBudgetedTime(7.6);
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader checks the total time")
    public void the_budget_leader_checks_the_total_time() {
        ProjectManagementSystem.setLoggedInEmployee(employee.getEmployee());
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
        ProjectManagementSystem.setLoggedInEmployee(employee.getEmployee());
        oldTime = activity.getActivity().getBudgetedTime();
        try {
            activity.getActivity().budgetTimeForActivity(7.2);
        } catch(Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity isn't updated")
    public void the_activity_isn_t_updated() {
        assertEquals(oldTime, activity.getActivity().getBudgetedTime(), 0.0); // Should be the same time.
    }

    @Then("the time isn't added to the total time")
    public void the_time_isn_t_added_to_the_total_time() {
        assertEquals(totalTime, project.getProject().getTotalBudgetedTime(),0.0);
    }
}
