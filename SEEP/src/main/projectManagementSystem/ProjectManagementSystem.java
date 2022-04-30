package projectManagementSystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class ProjectManagementSystem {
    private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private int projectCounter = 0;
    private Calendar cal = Calendar.getInstance();
    private Scanner scanner = new Scanner(System.in);;

    public static void main(String[] args) {
        System.out.println("test");
    }

    public ArrayList<Project> getProjects(){
        return projects;
    }
    public ArrayList<Employee> getEmployees() {return employees;}

    public Project getProjectWithName(String projectName){
        Project returnProject = null;
        for (Project project: projects) {
            if(project.getName().equals(projectName)){
                returnProject = project;
            }
        }
        return returnProject;
    }

    public Employee getEmployeeWithName(String employeeName){
        Employee returnEmployee = null;
        for (Employee employee: employees) {
            if (employee.getName().equals(employeeName)){
                returnEmployee = employee;
            }
        }
        return returnEmployee;
    }

    // adds the project to the list.
    public void addProjectToList(Project project) { projects.add(project); }
}
