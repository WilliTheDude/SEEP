package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// General imports
import java.util.ArrayList;

// Import of the classes
import projectManagementSystem.*;

public class ChangeActivitySteps {
    // Fields
    //private ProjectHolder projectHolder;
    //private EmployeeHolder employeeHolder;
    //private ActivityHolder activityHolder;
    private ProjectHolder project;
    private EmployeeHolder employee;
    private ActivityHolder activity;
    private ProjectManagementSystem projectManagementSystem;

    // Constructor
    public ChangeActivitySteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder,
                               ProjectManagementSystem projectManagementSystem){
        //this.projectHolder = projectHolder;
        this.project=projectHolder;
        //this.employeeHolder = employeeHolder;
        this.employee = employeeHolder;
        //this.activityHolder = activityHolder;
        this.activity = activityHolder;
        this.projectManagementSystem = projectManagementSystem;
        //this.employees = employees;
    }
    @Given("actor sets the name of the activity to a valid name")
    public void actor_sets_the_name_of_the_activity_to_a_valid_name() {
        activity.getActivity().setTempName(activity.getActivity().getName());
    }

    @Given("actor sets planned time to a valid time")
    public void actor_sets_planned_time_to_a_valid_time() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("actor sets description")
    public void actor_sets_description() {
        activity.getActivity().setTempName(activity.getActivity().getDesc());
    }

    @When("actor confirms changes")
    public void actor_confirms_changes() {
        activity.getActivity().setChangingActivity(false);
    }

    @Then("the changes are made")
    public void the_changes_are_made() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
        // TODO: 14-04-2022
    }

    @Given("actor sets the name of the activity to an invalid name that is already used")
    public void actor_sets_the_name_of_the_activity_to_an_invalid_name_that_is_already_used() {
        project.getProject().setTempName("activity1");
    }

    @Given("actor sets the name of the activity to blank")
    public void actor_sets_the_name_of_the_activity_to_blank() {
        project.getProject().setTempName("");
    }

    @Given("actor sets planned time to an invalid time with start time later than end time")
    public void actor_sets_planned_time_to_an_invalid_time_with_start_time_later_than_end_time() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("{string} has an activity with name {string}")
    public void hasAnActivityWithName(String projectName, String activityName) {
        project.setProject(projectManagementSystem.getProjectWithName(projectName));
        project.getProject().setCreatingActivity(true);
        project.getProject().setTempName(activityName);
        project.getProject().setCreatingActivity(false);
        activity.setActivity(project.getProject().getActivityWithName(activityName));
    }

    @Given("actor has entered the info change state of {string}")
    public void actorHasEnteredTheInfoChangeStateOf(String arg0) {
        activity.getActivity().setChangingActivity(true);
    }
}
