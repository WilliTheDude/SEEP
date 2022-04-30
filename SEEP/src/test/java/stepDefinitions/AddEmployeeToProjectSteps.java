package stepDefinitions;

// Cucumber imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Imports of classes;
import projectManagementSystem.*;

import static org.junit.Assert.*;

public class AddEmployeeToProjectSteps {

    // Fields
    //private ProjectHolder projectHolder;
    private ProjectHolder project;
    //private EmployeeHolder employeeHolder;
    private EmployeeHolder employee;
    private EmployeeHolder employee2;
    private ProjectManagementSystem projectManagementSystem;

    // Constructor
    public AddEmployeeToProjectSteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder, ProjectManagementSystem projectManagementSystem){
        //this.employeeHolder = employeeHolder;
        this.employee = employeeHolder;
        //this.projectHolder = projectHolder;
        this.project = projectHolder;
        this.projectManagementSystem = projectManagementSystem;
    }



    @Given("{string} has {string} as project leader")
    public void has_as_project_leader(String projectName, String user) {
        project.setProject(projectManagementSystem.getProjectWithName(projectName));
        employee.setEmployee(projectManagementSystem.getEmployeeWithName(user));
        project.getProject().setProjectLeader(employee.getEmployee());
    }

    @Given("{string} has no project leader")
    public void project_has_no_project_leader(String projectName){
        projectManagementSystem.getProjectWithName(projectName).setProjectLeader(null);
    }

    @When("{string} adds {string} to project {string}")
    public void add_to_project(String projectLeader, String user, String projectName){
        //employee.getEmployee().g  brug liste employees???
        employee.setEmployee(projectManagementSystem.getEmployeeWithName(projectLeader));
        employee2.setEmployee(projectManagementSystem.getEmployeeWithName(user));
        project.setProject(projectManagementSystem.getProjectWithName(projectName));
        employee2.getEmployee().addEmploayeeToProject(employee2.getEmployee(),project.getProject());
    }

    @Then("{string} is added to project {string}")
    public void is_part_of_project(String employeeName, String projectName){
        project.setProject(projectManagementSystem.getProjectWithName(projectName));
        employee.setEmployee(projectManagementSystem.getEmployeeWithName(employeeName));
        assertTrue(project.getProject().getAssignees().contains(employee.getEmployee()));
    }

    @Then("{string} isn't added to project {string}")
    public void isn_t_part_of_project(String employeeName, String projectName){
        project.setProject(projectManagementSystem.getProjectWithName(projectName));
        employee.setEmployee(projectManagementSystem.getEmployeeWithName(employeeName));
        assertFalse(project.getProject().getAssignees().contains(employee.getEmployee()));
    }
}
