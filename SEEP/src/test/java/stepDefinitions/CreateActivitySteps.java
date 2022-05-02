package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// General imports
import java.util.ArrayList;

// Import of classes
import projectManagementSystem.*;

import static org.junit.Assert.*;

public class CreateActivitySteps {
    private ProjectHolder project;
    private EmployeeHolder employee;
    private ActivityHolder activity;
    private ErrorMessageHolder errorMessageHolder;


    public CreateActivitySteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder, ActivityHolder activityHolder, ErrorMessageHolder errorMessageHolder){
        this.project = projectHolder;
        this.employee = employeeHolder;
        this.activity = activityHolder;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("there exist an activity with name {string}")
    public void there_exist_an_activity_with_name(String name) {
        activity.setActivity(name,"",project.getProject());
        project.getProject().createTestActivity(name, "");
    }
/*
    @Given("actor has entered the create activity state for {string}")
    public void actor_has_entered_the_create_activity_state_for(String string) {
        project.getProject().setCreatingActivity(true);
    }

 */
/*
    @Given("actor sets the name of the activity to {string}")
    public void actor_sets_the_name_of_the_activity_to_a_valid_name(String name) {
        project.getProject().setTempName(name);
    }
*/
/*    @Given("actor sets description")
    public void actor_sets_description() {
        project.getProject().setTempDesc("This is a test activity.");
    }
*/
/*    @When("actor confirms creation")
    public void actor_confirms_creation() {
        try{
            project.getProject().setCreatingActivity(false);
            activity.setActivity(project.getProject().getTempName(), project.getProject().getTempDesc(),project.getProject());
            project.getProject().createActivity(employee.getEmployee());
        } catch (Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }*/

    @Then("the activity is saved to list of activities")
    public void the_activity_is_saved_to_list_of_activities() {
        for (Activity a: project.getProject().getActivities()){
            System.out.println(a.getName());
            System.out.println(activity.getActivity().getName());
        }
        assertTrue(project.getProject().getActivities().contains(activity.getActivity()));
    }


    @Given("actor is not project leader on this project")
    public void actor_is_not_project_leader_on_this_project() {
        project.getProject().setProjectLeader(new Employee("Dan",35,"male"));
    }

    @When("actor creates activity with name {string} and description {string}")
    public void actorCreatesActivityWithNameAndDescription(String name, String desc) {
        project.getProject().setTempName(name);
        project.getProject().setTempDesc(desc);
        try{
            project.getProject().createActivity(employee.getEmployee());
            activity.setActivity(project.getProject().getActivityWithName(name));
            System.out.println("Hi");
        } catch (Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
            System.out.println(errorMessageHolder.getErrorMessage());
        }
    }
}
