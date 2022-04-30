package projectManagementSystem;

import java.util.ArrayList;

public class Project {
    private int ID;  // Tænkte at denne skulle være en int for at gøre det nemmer for os selv
    private String name;
    private String description;
    private Employee projectLeader;
    private ArrayList<Employee> assignees = new ArrayList<Employee>();
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private boolean creatingActivity = false;
    private String tempName;
    private String tempDesc;
    private boolean statusShown;

    // Constructor
    public Project(int year, int nr, String name, String description) {
        ID = Integer.parseInt(year + "" + nr);
        this.name = name;
        this.description = description;
        ProjectManagementSystem.addProjectToList(this);
    }

    public Project(){}

    public boolean getStatusShown() {
        return statusShown;
    }

    // General methods
    public void createActivity(){
        while (creatingActivity) {
            // Venter på at få variable og confirm trykkes på - confirm ændrer creatingActivity til false.
        }

        // TODO: Tjek at ting er korrekte.

        Activity activity = new Activity(tempName, tempDesc, this);
        activities.add(activity);
        tempName = null;
        tempDesc = null;
    }

    public void addAssignee(Employee e) {assignees.add(e);}
    public void addActivity(Activity activity){ activities.add(activity); }

    public boolean userHaveAccessesToProject() {
        /**
         * TODO:
         * 	* Check if a user is assigned to this project
         * 	* if the user is assigned to the project grant them accesses
         */
        return true;
    }

    // Getter
    public Activity getActivityWithName(String name){
        Activity returnActivity = null;
        for (Activity a: activities){
            if (a.getName().equals(name)){
                returnActivity = a;
            }
        }
        return returnActivity;
    }

    public String getTempDesc(){return tempDesc;}
    public String getTempName(){return tempName;}
    public ArrayList<Activity> getActivities(){
        return activities;
    }
    public ArrayList<Employee> getAssignees(){
        return assignees;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public int getID() {
        return ID;
    }
    public Employee getProjectLeader() {
        return projectLeader;
    }

    public Activity getActivitiesWithName(String activityName){
        Activity returnActivity = null;
        for (Activity activity: activities) {
            if (activity.getName().equals(activityName)){
                returnActivity = activity;
            }
        }
        return returnActivity;
    }

    public Employee getEmployeeWithName(String name){
        Employee returnEmployee = null;
        for (Employee employee: assignees) {
            if(employee.getName().equals(name)){
                returnEmployee = employee;
            }
        }
        return returnEmployee;
    }

    // Setter
    public void setTempName(String n){tempName = n;}
    public void setTempDesc(String n){tempDesc = n;}
    public void setCreatingActivity(Boolean b){creatingActivity = b;}
    public void setDescription(String description) {
        this.description = description;
    }
    public void setProjectLeader(Employee projectLeader) {
        this.projectLeader = projectLeader;
    }

    public void setName(String name) {
        for (Project p : ProjectManagementSystem.getProjects()) {
            if (!p.getName().equals(this.name)) {

            }
        }
    }


    public void showStatus(Employee employee){

    }
}