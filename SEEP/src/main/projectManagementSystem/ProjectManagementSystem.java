package projectManagementSystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class ProjectManagementSystem {
    static private ArrayList<Project> projects = new ArrayList<Project>();
    static private ArrayList<Employee> employees = new ArrayList<Employee>();
    static private int projectCounter = 0;
    static private Calendar cal = Calendar.getInstance();
    static private Scanner scanner = new Scanner(System.in);;

    public static void main(String[] args) {
        System.out.println("test");
    }

    static public ArrayList<Project> getProjects(){
        return projects;
    }
    static public ArrayList<Employee> getEmployees() {return employees;}

    static public Project getProjectWithName(String projectName){
        Project returnProject = null;
        for (Project project: projects) {
            if (project.getName().equals(projectName)) {
                returnProject = project;
            }
        }
        return returnProject;
    }

    static public Employee getEmployeeWithName(String employeeName){
        Employee returnEmployee = null;
        for (Employee employee: employees) {
            if (employee.getName().equals(employeeName)){
                returnEmployee = employee;
            }
        }
        return returnEmployee;
    }

    // adds the project to the list.
    static public void addProjectToList(Project project) { projects.add(project); }
    static public void addEmployeeToList(Employee employee) {employees.add(employee);}

    public static void clearLists(){
        projects.clear();
        employees.clear();
        projectCounter = 0;
    }

}
