package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// General imports
import java.util.ArrayList;

// Imports of classes
import static org.junit.Assert.*;
import projectManagementSystem.*;

public class AddProjectLeaderToProjectSteps {

    //fields
    private EmployeeHolder employee; //[0] is user 1, [1] is user 2
    private ProjectHolder project;

    // Constructor
    public AddProjectLeaderToProjectSteps(ProjectHolder projectHolder, EmployeeHolder employee){
        this.employee = employee;
        this.project = projectHolder;
    }

    @Given("that there exists a project")
    public void that_there_exists_a_project() {
        ProjectManagementSystem.addProjectToList(new Project("project1", "This is a project"));
        project.setProject(ProjectManagementSystem.getProjectWithName("project1"));
    }

    @Given("the project has no project leader")
    public void the_project_has_no_project_leader() {
        project.getProject().setProjectLeader(null);
    }

    @Given("user named {string} is part of the project")
    public void user_named_is_part_of_the_project(String user) {
        employee.setEmployee(ProjectManagementSystem.getEmployeeWithName(user));
        project.getProject().getAssignees().add(employee.getEmployee());
    }

    @When("{string} assigns {string} as project leader")
    public void assigns_as_project_leader(String user1, String user2) {
        employee.getEmployeeWithName(user1).setProjectLeader(project.getProject(), employee.getEmployeeWithName(user2));
    }

    @Then("{string} is project leader of the project")
    public void is_project_leader_of_the_project(String projectLeader) {
        assertEquals(project.getProject().getProjectLeader().getName(), projectLeader);
    }

    @Given("user named {string} isn't part of the project")
    public void user_named_isn_t_part_of_the_project(String user) {
        project.getProject().getAssignees().remove(employee.getEmployeeWithName(user));
    }

    @Then("{string} isn't project leader of the project")
    public void isn_t_project_leader_of_the_project(String projectLeader) {
        assertNotEquals(project.getProject().getProjectLeader().getName(),projectLeader);
    }

    @Given("the project has a project leader")
    public void the_project_has_a_project_leader() {
        project.getProject().setProjectLeader(new Employee("project leader",42,"male"));
    }


}
