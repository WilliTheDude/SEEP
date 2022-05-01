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
    //private ProjectHolder projectHolder;
    private ProjectHolder project;
    //private EmployeeHolder employeeHolder;
    private EmployeeHolder employee;
    private EmployeeHolder employee2;

    // Constructor
    public AddEmployeeToProjectSteps(ProjectHolder projectHolder, EmployeeHolder employeeHolder){
        //this.employeeHolder = employeeHolder;
        this.employee = employeeHolder;
        //this.projectHolder = projectHolder;
        this.project = projectHolder;
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
        //employee.getEmployee().g  brug liste employees???
        employee.setEmployee(ProjectManagementSystem.getEmployeeWithName(projectLeader));
        employee2.setEmployee(ProjectManagementSystem.getEmployeeWithName(user));
        project.setProject(ProjectManagementSystem.getProjectWithName(projectName));
        employee2.getEmployee().addEmployeeToProject(employee2.getEmployee(),project.getProject());
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
