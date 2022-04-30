package setup;

// Junit imports
import static org.junit.Assert.assertTrue;

// Cucumber imports
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionTest {
    @When("I do nothing")
    public void i_do_nothing(){}

    @Then("everything is okay")
    public void everything_is_okay(){
        assertTrue(true);
    }

}
