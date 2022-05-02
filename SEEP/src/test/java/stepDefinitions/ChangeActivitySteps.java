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
    private ErrorMessageHolder errorMessageHolder;


    // Constructor
    public ChangeActivitySteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder, ErrorMessageHolder errorMessageHolder){
        this.project = projectHolder;
        this.employee = employeeHolder;
        this.activity = activityHolder;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Then("the changes are made")
    public void the_changes_are_made() {
        assertTrue(!oldName.equals(activity.getActivity().getName())||!oldDesc.equals(activity.getActivity().getDesc()));
    }

    @Given("{string} has an activity with name {string}")
    public void hasAnActivityWithName(String projectName, String activityName) {
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        project.getProject().createTestActivity(activityName, "");
        activity.setActivity(project.getProject().getActivityWithName(activityName));
    }
    
    @When("actor changes the name of the activity to {string}")
    public void actorChangesTheNameOfTheActivityTo(String name) {
        try{
            oldName = activity.getActivity().getName();
            oldDesc = activity.getActivity().getDesc();
            activity.getActivity().setTempName(name);
            activity.getActivity().changeActivity();
        } catch (Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
