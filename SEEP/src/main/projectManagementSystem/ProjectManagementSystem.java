package projectManagementSystem;

import java.util.ArrayList;
import java.util.Calendar;

public class ProjectManagementSystem {
    private static ArrayList<Project> projects = new ArrayList<Project>();
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private int projectCounter = 0;
    Calendar cal = Calendar.getInstance();

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public static ArrayList<Project> getProjects(){
        return projects;
    }
    public static ArrayList<Employee> getEmployees() {return employees;}

    public static Project getProjectWithName(String projectName){
        Project returnProject = null;
        for (Project project: projects) {
            if(project.getName().equals(projectName)){
                returnProject = project;
            }
        }
        return returnProject;
    }

    public static Employee getEmployeeWithName(String employeeName){
        Employee returnEmployee = null;
        for (Employee employee: employees) {
            if (employee.getName().equals(employeeName)){
                returnEmployee = employee;
            }
        }
        return returnEmployee;
    }




    // adds the project to the list.
    public static void addProjectToList(Project project) { projects.add(project); }
}
