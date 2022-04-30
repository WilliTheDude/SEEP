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
    @Given("{string} has an activity with name {string}")
    public void has_an_activity_with_name(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("actor has entered the info change state of {string}")
    public void actor_has_entered_the_info_change_state_of(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("actor sets the name of the activity to a valid name")
    public void actor_sets_the_name_of_the_activity_to_a_valid_name() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("actor sets planned time to a valid time")
    public void actor_sets_planned_time_to_a_valid_time() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("actor sets description")
    public void actor_sets_description() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("actor confirms changes")
    public void actor_confirms_changes() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the changes are made")
    public void the_changes_are_made() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("actor sets the name of the activity to an invalid name that is already used")
    public void actor_sets_the_name_of_the_activity_to_an_invalid_name_that_is_already_used() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("actor sets the name of the activity to blank")
    public void actor_sets_the_name_of_the_activity_to_blank() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("actor sets planned time to an invalid time with start time later than end time")
    public void actor_sets_planned_time_to_an_invalid_time_with_start_time_later_than_end_time() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
