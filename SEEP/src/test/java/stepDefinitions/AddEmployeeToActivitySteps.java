package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Import of classes
import projectManagementSystem.Activity;
import projectManagementSystem.Employee;
import projectManagementSystem.Project;
import projectManagementSystem.ProjectManagementSystem;

import static junit.framework.TestCase.*;

public class AddEmployeeToActivitySteps {

    @Given("that there exists a project with name {string}")
    public void that_there_exists_a_project_with_name(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("that there exists an activity with name {string} in project {string}")
    public void that_there_exists_an_activity_with_name_in_project(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("that there exists a user named {string}")
    public void that_there_exists_a_user_named(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("{string} is logged in")
    public void is_logged_in(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("{string} is part of project {string}")
    public void is_part_of_project(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("{string} is part of activity {string}")
    public void is_part_of_activity(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("{string} adds {string} to activity {string}")
    public void adds_to_activity(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("{string} is assigned to activity {string}")
    public void is_assigned_to_activity(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("{string} is not part of project {string}")
    public void is_not_part_of_project(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("{string} is not part of activity {string}")
    public void is_not_part_of_activity(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
