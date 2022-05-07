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

    @Then("the activity is saved to list of activities")
    public void the_activity_is_saved_to_list_of_activities() {
        /** Debuging?
           * for (Activity a: project.getProject().getActivities()){
                System.out.println(a.getName());
                System.out.println(activity.getActivity().getName());
            }
         */
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
        } catch (Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
