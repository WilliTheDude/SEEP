package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Imports of classes
import projectManagementSystem.Employee;
import projectManagementSystem.Project;
import projectManagementSystem.ProjectManagementSystem;

import static org.junit.Assert.*;

public class AddEmployeeToProjectSteps {
    @Given("{string} has {string} as project leader")
    public void has_as_project_leader(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("{string} adds {string} to project {string}")
    public void adds_to_project(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("{string} is added to project {string}")
    public void is_added_to_project(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("{string} has no project leader")
    public void has_no_project_leader(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("{string} isn't added to project {string}")
    public void isn_t_added_to_project(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
