package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// General imports
import java.util.ArrayList;

// Import of classes
import projectManagementSystem.*;

import static org.junit.Assert.assertTrue;

public class CreateActivitySteps {
    private ProjectHolder project;
    private EmployeeHolder employee;
    private ActivityHolder activity;


    public CreateActivitySteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder){
        this.project = projectHolder;
        this.employee = employeeHolder;
        this.activity = activityHolder;
    }

    @Given("actor has entered the create activity state for {string}")
    public void actor_has_entered_the_create_activity_state_for(String string) {
        project.getProject().setCreatingActivity(true);
    }

    @When("actor confirms creation")
    public void actor_confirms_creation() {
        project.getProject().setCreatingActivity(false);
    }

    @Then("the activity is saved to list of activities")
    public void the_activity_is_saved_to_list_of_activities() {
        assertTrue(project.getProject().getActivities().contains(activity.getActivity()));
    }


    @Given("actor is not project leader on this project")
    public void actor_is_not_project_leader_on_this_project() {
        project.getProject().setProjectLeader(new Employee("Dan",35,"male"));
    }
}
