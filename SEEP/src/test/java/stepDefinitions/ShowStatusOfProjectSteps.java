package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Class imports
import projectManagementSystem.*;

// Junit improts
import static org.junit.Assert.*;

public class ShowStatusOfProjectSteps {
    // fields
    //private ProjectHolder projectHolder;
    private ProjectHolder project;
    //private EmployeeHolder employeeHolder;
    private EmployeeHolder employee;
    private ErrorMessageHolder errorMessage;

    // Constructor
    public ShowStatusOfProjectSteps(EmployeeHolder employeeHolder, ProjectHolder projectHolder, ErrorMessageHolder errorMessage){
        //this.projectHolder = projectHolder;
        this.project = projectHolder;
        //this.employeeHolder = employeeHolder;
        this.employee = employeeHolder;
        this.errorMessage = errorMessage;
    }

    @When("{string} choose to see project status on {string}")
    public void choose_to_see_project_status_on(String user, String projectName) {
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        employee.setEmployee(project.getProject().getEmployeeWithName(user));
        try {
            project.getProject().showStatus(employee.getEmployee()); // skal i den funktion sætte en boolean statusShown til true
        }
        catch (Error e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("{string} is shown the project status connected to {string}")
    public void is_shown_the_project_status_connected_to(String user, String projectName) {
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        assertTrue(project.getProject().getStatusShown()); // tester om boolean showStatus er sat til true
    }

    @Given("{string} is not a project leader on {string}")
    public void is_not_a_project_leader_on(String user, String projectName) {
        ProjectManagementSystem.getProjectWithName(projectName).setProjectLeader(null);
    }
}
