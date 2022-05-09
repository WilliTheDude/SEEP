package projectManagementSystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class ProjectManagementSystem {
    private static ArrayList<Project> projects = new ArrayList<Project>();
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private static Calendar calendar = Calendar.getInstance();
    private static Employee loggedInEmployee;

    public static void main(String[] args) {
        CLI.setup();
        CLI.run();
    }


    // Getters
    public static ArrayList<Project> getProjects(){
        return projects;
    }
    public static Employee getLoggedInEmployee() {return loggedInEmployee;}
    public static Calendar getCalendar() {return calendar;}
    public static Project getProjectWithName(String projectName){
        Project returnProject = null;
        for (Project project: projects) {
            if (project.getName().equals(projectName)) {
                returnProject = project;
            }
        }
        return returnProject;
    }
    public static Employee getEmployeeWithName(String employeeName){
        Employee returnEmployee = null;
        for (Employee employee: employees) {
            if (employee.getName().equals(employeeName)){
                return employee;
            }
        }
        throw new IllegalArgumentException("no employee with chosen name exists");
    }
    static public void addProjectToList(Project project) { projects.add(project); }
    static public void addEmployeeToList(Employee employee) {employees.add(employee);}
    public static void clearLists(){
        projects.clear();
        employees.clear();
        loggedInEmployee = null;
    }
    public static void setLoggedInEmployee(Employee loggedInEmployee) {ProjectManagementSystem.loggedInEmployee = loggedInEmployee;}

}
