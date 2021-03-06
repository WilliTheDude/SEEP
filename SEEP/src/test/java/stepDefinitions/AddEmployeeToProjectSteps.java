//Tore
package stepDefinitions;

// Cucumber imports
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Imports of classes;
import projectManagementSystem.*;

import static org.junit.Assert.*;

public class AddEmployeeToProjectSteps {

    // Fields
    private ProjectHolder project;
    private EmployeeHolder employee;
    private ErrorMessageHolder errorMessageHolder;

    // Constructor
    public AddEmployeeToProjectSteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder, ErrorMessageHolder errorMessageHolder){
        this.employee = employeeHolder;
        this.project = projectHolder;
        this.errorMessageHolder = errorMessageHolder;
    }

    @After
    public void clearLists(){
        ProjectManagementSystem.clearLists();
    }


    @Given("{string} has {string} as project leader")
    public void has_as_project_leader(String projectName, String user) {
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        employee.setEmployee(ProjectManagementSystem.getEmployeeWithName(user));
        project.getProject().setProjectLeader(employee.getEmployee());
    }

    @Given("{string} has no project leader")
    public void project_has_no_project_leader(String projectName){
        ProjectManagementSystem.getProjectWithName(projectName).setProjectLeader(null);
    }

    @When("{string} adds {string} to project {string}")
    public void add_to_project(String projectLeader, String user, String projectName){
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        try {
            employee.getEmployeeWithName(projectLeader).addEmployeeToProject(employee.getEmployeeWithName(user),project.getProject());
        }catch (Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }

    }

    @Then("{string} is added to project {string}")
    public void is_part_of_project(String employeeName, String projectName){
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        employee.setEmployee(ProjectManagementSystem.getEmployeeWithName(employeeName));
        assertTrue(project.getProject().getAssignees().contains(employee.getEmployee()));
    }

    @Then("{string} isn't added to project {string}")
    public void isn_t_part_of_project(String employeeName, String projectName){
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        employee.setEmployee(ProjectManagementSystem.getEmployeeWithName(employeeName));
        assertFalse(project.getProject().getAssignees().contains(employee.getEmployee()));
    }
}
