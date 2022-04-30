package setup;

// Junit imports
import org.junit.runner.RunWith;

// Cucumber implementations
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "summary"
        , glue = "stepDefinitions"
        , features = "src/test/features"
        //, publish = false
)

public class RunCucumberTest {
    // This class will be empty
}
