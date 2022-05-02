package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// General imports
import java.util.ArrayList;

// Import of the classes
import static org.junit.Assert.*;
import projectManagementSystem.*;

public class ChangeActivitySteps {
    // Fields
    private ProjectHolder project;
    private EmployeeHolder employee;
    private ActivityHolder activity;
    private String oldName;
    private String oldDesc;

    // Constructor
    public ChangeActivitySteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder){
        this.project = projectHolder;
        this.employee = employeeHolder;
        this.activity = activityHolder;
    }

    @Given("actor sets new description")
    public void actor_sets_description() {
        activity.getActivity().setTempDesc("This is a description.");
    }

    @When("actor confirms changes")
    public void actor_confirms_changes() {
        oldName = activity.getActivity().getName();
        oldDesc = activity.getActivity().getDesc();
        activity.getActivity().setChangingActivity(false);
    }

    @Then("the changes are made")
    public void the_changes_are_made() {
        assertNotSame(oldName, activity.getActivity().getName());
        assertNotSame(oldDesc, activity.getActivity().getDesc());
    }

    @Given("{string} has an activity with name {string}")
    public void hasAnActivityWithName(String projectName, String activityName) {
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        project.getProject().setCreatingActivity(true);
        project.getProject().setTempName(activityName);
        project.getProject().setCreatingActivity(false);
        activity.setActivity(project.getProject().getActivityWithName(activityName));
    }

    @Given("actor has entered the info change state of {string}")
    public void actorHasEnteredTheInfoChangeStateOf(String arg0) {
        activity.getActivity().setChangingActivity(true);
    }

    @Given("actor sets the new name of the activity to {string}")
    public void actorSetsTheNewNameOfTheActivityTo(String name) {
        activity.getActivity().setTempName(name);
    }
}
