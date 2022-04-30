package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Import of classes
import org.junit.Ignore;
import projectManagementSystem.*;

// import of Junit
import static org.junit.Assert.*;

public class CreateProjectSteps {
    // Field
    //private ProjectHolder projectHolder;
    private ProjectHolder project;
    //private EmployeeHolder employeeHolder;
    private EmployeeHolder employee;
    private ProjectManagementSystem projectManagementSystem;

    // Constructor
    public CreateProjectSteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder, ProjectManagementSystem projectManagementSystem) {
        //this.projectHolder = projectHolder;
        this.project = projectHolder;
        //this.employeeHolder = employeeHolder;
        this.employee = employeeHolder;
        this.projectManagementSystem = projectManagementSystem;
    }

    @Given("the user has ID {int}")
    public void the_user_has_id(Integer ID) {
        employee.getEmployee().setID(ID);
    }

    @When("the user creates a new project with name {string}")
    public void the_user_creates_a_new_project_with_name(String projectName) {
        employee.getEmployee().createProject(projectName, "This is a project");
        project.setProject(projectManagementSystem.getProjectWithName(projectName));
    }

    @Then("the project is created")
    public void the_project_is_created() {
        assertTrue(projectManagementSystem.getProjects().contains(project.getProject()));
    }

    @When("the user creates a project with description {string}")
    public void the_user_creates_a_project_with_description(String description) {
        employee.getEmployee().createProject("project 1", description);
    }

    @Then("the project isn't created")
    public void the_project_isn_t_created() {
        assertFalse(projectManagementSystem.getProjects().contains(project.getProject()));
    }

    @When("the user creates a valid project")
    public void the_user_creates_a_valid_project() {
        employee.getEmployee().createProject("project1", "This is a project");
        project.setProject(projectManagementSystem.getProjectWithName("project1"));
    }

    @Then("the creating user is a part of the project")
    public void the_creating_user_is_a_part_of_the_project() {
        assertTrue(employee.getEmployee().getProjects().contains(project.getProject()));
    }

    @Given("that there exist a user")
    public void that_there_exist_a_user() {
        employee.setEmployee("Bo", 42,"Man");
    }

    @Given("the User is unauthorised to create a project")
    public void the_user_is_unauthorised_to_create_a_project() {
        employee.getEmployee().setAuthorization(false);
    }


    @Ignore
    @When("the user tries to access the project")
    public void the_user_tries_to_access_the_project() {
        throw new io.cucumber.java.PendingException();
    }

    @Ignore
    @Then("the user is granted access to the project")
    public void the_user_is_granted_access_to_the_project() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}