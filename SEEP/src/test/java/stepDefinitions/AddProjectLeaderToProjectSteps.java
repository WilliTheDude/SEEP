package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// General imports
import java.util.ArrayList;

// Imports of classes
import org.junit.Ignore;
import projectManagementSystem.*;

public class AddProjectLeaderToProjectSteps {

    //fields
    //private ProjectHolder projectHolder;
    private ProjectHolder project;
    //private EmployeeHolder employeeHolder;
    private EmployeeHolder toBeProjectLeader;
    private EmployeeHolder partOfProject;

    // Constructor
    public AddProjectLeaderToProjectSteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder){
        //this.projectHolder = projectHolder;
        this.project = projectHolder;
        //this.employeeHolder = employeeHolder;
        toBeProjectLeader = employeeHolder;
    }

    @Ignore
    @Given("that there exists a project")
    public void that_there_exists_a_project() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @Given("the project has no project leader")
    public void the_project_has_no_project_leader() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @Given("user named {string} is part of the project")
    public void user_named_is_part_of_the_project(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @When("{string} assigns {string} as project leader")
    public void assigns_as_project_leader(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @Then("{string} is project leader of the project")
    public void is_project_leader_of_the_project(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @Given("user named {string} isn't part of the project")
    public void user_named_isn_t_part_of_the_project(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @Then("{string} isn't project leader of the project")
    public void isn_t_project_leader_of_the_project(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @Given("the project has a project leader")
    public void the_project_has_a_project_leader() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @When("{string} correctly assigns {string} as project leader")
    public void correctly_assigns_as_project_leader(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
