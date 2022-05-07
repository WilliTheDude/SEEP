package projectManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

// Tore
public class Employee {

    // Fields
    /**
     * TODO:
     *  * when the program is done see which of the fields we should make local
     */
    private int age;
    private boolean authorization;
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
        authorization = true;
        ProjectManagementSystem.addEmployeeToList(this);
    }

    // General methods
    public Project createProject(String name, String description) {
        if(!authorization) throw new IllegalArgumentException("You are unauthorised to create a project");
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

    //TODO: do we use this method?? ( public void addProject(Project p) { projects.add(p); } )

    // Getter
    public String getName() { return name; }
    /** TODO: do we use these method for anything in the program:
            * public int getID() { return ID; }
            * public boolean getAuthorization() { return authorization; }
     */
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
    public ArrayList<Project> getProjects() { return projects; }
    public double getTotalProjectTime(Project p) { return p.getTotalBudgetedTimeForProject();}
    public ArrayList<Activity> getActivities() { return activities; }

    // Setter
    public void setProjectLeader(Project p, Employee e) {
        if (p.getProjectLeader() == null && p.getAssignees().contains(this)) {
            p.addAssignee(e);
            p.setProjectLeader(e);
            e.projects.add(p);
        } else if (p.getProjectLeader() != null) {
            throw new IllegalArgumentException("There can only be one project leader");
        } else if (!p.getAssignees().contains(this)) {
            throw new IllegalArgumentException("User needs to be part of project to assign project leaders");
        }
    }

    /** TODO: do we use this method for anything in the program:
            * void addEmployeeToCurrentActivity(Activity a, Employee e) { a.addAssignee(this, e); } )
     */

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
    public void setAuthorization(boolean authorization) { this.authorization = authorization; }
    public void addEmployeeToActivity(Activity activity){
        activities.add(activity);
    }
}
