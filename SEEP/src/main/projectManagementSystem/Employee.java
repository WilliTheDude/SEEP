package projectManagementSystem;

import java.util.ArrayList;

// Tore
public class Employee {

    // Fields
    private int age;
    private boolean loggedIn = false;
    private String ID;
    private String name;
    private String gender;
    private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    // constructor
    public Employee(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ID = generateID(name);
        ProjectManagementSystem.addEmployeeToList(this);
    }

    // General methods
    public Project createProject(String name, String description) {
        try {
            Project project = new Project(name, description);
            project.getAssignees().add(this); // adds the employee to the assignees of the project
            projects.add(project);
            return project;
        } catch(Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private String generateID(String name) {
        String[] nameSceptered = name.toLowerCase().split(" ");
        int numNames = nameSceptered.length;
        if (nameSceptered.length >= 3) return "" + nameSceptered[0].charAt(1) + nameSceptered[1].charAt(0) + nameSceptered[numNames].charAt(0);
        else if (nameSceptered.length == 2) return nameSceptered[0].substring(0,2) + nameSceptered[1].charAt(0);
        return null;
    }

    // Getter
    public String getName() { return name; }
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
    public ArrayList<Project> getProjects() { return projects; }
    public double getTotalProjectTime(Project p) { return p.getTotalBudgetedTimeForProject();}
    public ArrayList<Activity> getActivities() { return activities; }

    public Project getProjectWithName(String name){
        for (Project p : projects) {
            if(p.getName().equals(name)) return p;
        }
        throw new IllegalArgumentException("No such project is assigned to this employee");
    }

    public Activity getActivityWithName(String name, Project p){
        for(Activity a : activities){
            if(a.getParent() == p && a.getName().equals(name)) return a;
        }
        throw new IllegalArgumentException("Has no activity with name " + name);
    }

    public void setProjectLeader(Project p, Employee e) {
        if (p.getProjectLeader() == null && p.getAssignees().contains(this)) {
            p.setProjectLeader(e);
            if (this != e) {
                p.addAssignee(e);
            }
        } else if (p.getProjectLeader() != null) {
            throw new IllegalArgumentException("There can only be one project leader");
        } else if (!p.getAssignees().contains(this)) {
            throw new IllegalArgumentException("User needs to be part of project to assign project leaders");
        }
    }

    // Setter
    public void setID(String ID) { this.ID = ID; }
    public void addEmployeeToProject(Employee employee2, Project project) {
        try {
            if (project.getProjectLeader().equals(this)) {
                project.getAssignees().add(employee2);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("user isn't project leader of chosen project");
        }

    }
    public void addEmployeeToActivity(Activity activity){
        activities.add(activity);
    }
}
