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
    public Employee(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        authorization = true;
        ProjectManagementSystem.addEmployeeToList(this);
    }

    // General methods
    public Project createProject(String name, String description) {
        if(!authorization) throw new IllegalArgumentException("You are unauthorised to create a project");
        try {
            Project project = new Project(name, description);
            project.getAssignees().add(this); // adds the employee to the
            return project;
        } catch(Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addProject(Project p) {
        projects.add(p);
    }

    // Getter
    public String getName() { return name; }
    public int getID() {
        return ID;
    }
    public boolean getAuthorization() {
        return authorization;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public ArrayList<Project> getProjects() {
        return projects;
    }
    public double getTotalProjectTime(Project p) {
        return p.getTotalBudgetedTime();
    }

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
    void addEmployeeToCurrentActivity(Activity a, Employee e) {
        a.addAssignee(this, e);
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void addEmployeeToProject(Employee employee2, Project project) {
        try {
            if (project.getProjectLeader().equals(this)) {
                project.getAssignees().add(employee2);
            } else {
                throw new IllegalArgumentException("user isn't project leader of chosen project");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("user isn't project leader of chosen project");
        }

    }
    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }
}
