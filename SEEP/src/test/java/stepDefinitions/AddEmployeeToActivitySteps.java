package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Import of classes
import projectManagementSystem.*;

// Junit import
import static org.junit.Assert.*;

public class AddEmployeeToActivitySteps {

    // Fields
    private EmployeeHolder employee;
    private EmployeeHolder employee2;
    private ProjectHolder project;
    private ActivityHolder activity;
    private ProjectManagementSystem projectManagementSystem;
    private ErrorMessageHolder errorMessageHolder;

    // Constructor
    public AddEmployeeToActivitySteps(EmployeeHolder employeeHolder, ProjectHolder projectHolder, ActivityHolder activityHolder,
                                      ProjectManagementSystem projectManagementSystem, ErrorMessageHolder errorMessageHolder){
        //this.employeeHolder = employeeHolder;
        this.employee = employeeHolder;
        //this.projectHolder = projectHolder;
        this.project = projectHolder;
        this.activity = activityHolder;
        this.projectManagementSystem = projectManagementSystem;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("that there exists a project with name {string}")
    public void that_there_exists_a_project_with_name(String projectName) {
        project.setProject(projectName,"string");
    }

    @Given("that there exists an activity with name {string} in project {string}")
    public void that_there_exists_an_activity_with_name_in_project(String activityName, String projectName) {
        project.setProject(projectManagementSystem.getProjectWithName(projectName));
        activity.setActivity(new Activity(activityName,"string", project.getProject()));
        project.getProject().addActivity(activity.getActivity());
    }

    @Given("that there exists a user named {string}")
    public void that_there_exists_a_user_named(String employeeName) {
        employee.setEmployee(employeeName,42,"Man");
    }

    @Given("{string} is logged in")
    public void is_logged_in(String employeeName) {
        employee.setEmployee(projectManagementSystem.getEmployeeWithName(employeeName));
        employee.getEmployee().setLoggedIn(true);
    }

    @Given("{string} is part of project {string}")
    public void is_part_of_project(String user, String projectName) {
        project.setProject(projectManagementSystem.getProjectWithName(projectName));
        employee.setEmployee(project.getProject().getEmployeeWithName(user));
        project.getProject().getAssignees().add(employee.getEmployee());
    }

    @Given("{string} is part of activity {string}")
    public void is_part_of_activity(String user, String activityName ) {
        employee.setEmployee(activity.getActivity().getEmployeeWithName(user));
        project.getProject().getActivitiesWithName(activityName).addAssignee(employee.getEmployee());
        activity.getActivity().getAssignees().add(employee.getEmployee());
    }

    @When("{string} adds {string} to activity {string}")
    public void adds_to_activity(String projectLeader, String user, String activityName) {
        employee.setEmployee(projectManagementSystem.getEmployeeWithName(projectLeader));
        employee2.setEmployee(projectManagementSystem.getEmployeeWithName(user));
        activity.setActivity(project.getProject().getActivitiesWithName(activityName));

        // TODO: change this to only one employee
        activity.getActivity().addAssignee(employee.getEmployee(), employee2.getEmployee());
    }

    @Then("{string} is assigned to activity {string}")
    public void is_assigned_to_activity(String user, String activityName) {
        employee.setEmployee(project.getProject().getEmployeeWithName(user));
        assertTrue(project.getProject().getActivitiesWithName(activityName).getAssigness().contains(employee.getEmployee()));
    }

    @Given("{string} is not part of project {string}")
    public void is_not_part_of_project(String user, String projectName) {
        employee.setEmployee(projectManagementSystem.getEmployeeWithName(user));
        projectManagementSystem.getProjectWithName(projectName).removeAssignee(employee.getEmployee());
    }

    @Given("{string} is not part of activity {string}")
    public void is_not_part_of_activity(String user, String activityName) {
        employee.setEmployee(projectManagementSystem.getEmployeeWithName(user));
        project.getProject().getActivityWithName(activityName).removeAssignee(employee.getEmployee());
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) {
        assertTrue(errorMessageHolder.getErrorMessage().equals(errorMessage));
    }

}
