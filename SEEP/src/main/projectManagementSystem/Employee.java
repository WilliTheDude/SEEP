package projectManagementSystem;

import java.util.ArrayList;

public class Employee {
    // fields
    private int ID;
    private String name;
    private int age;
    private String gender;
    private boolean authorization;

    private boolean loggedIn = false;


    private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    // constructor
    public Employee(String name, int age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
        authorization = true;
    }

    // General methods
    public void createProject(String name, String description){
        /**
         * TODO: Add the project to the list in projectManagementSystem.
         *  * add the user to the project when it's created.
         */
    }



    public void addProject(Project p){
        projects.add(p);
    }

    // Getter
    public String getName() {
        return name;
    }
    public int getID() {return ID;}
    public boolean getAuthorization() { return authorization;}
    public void setLoggedIn(boolean loggedIn) {this.loggedIn = loggedIn;}
    public ArrayList<Project> getProjects() {
        return projects;
    }

    // Setter
    private void setProjectLeader(Project p, Employee e){
        if(p.getProjectLeader() != null){
            p.addAssignee(e);
            p.setProjectLeader(e);
            e.projects.add(p);
        }else{
            throw new IllegalArgumentException("main.Project already has a leader");
        }
    }
    void addEmployeeToCurrentActivity(Activity a, Employee e){
        a.addAssignee(this, e);
    }
    public void setID(int ID) {this.ID = ID;}
    public void addEmploayeeToProject(Employee employee2, Project project) {

    }
    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }
}
