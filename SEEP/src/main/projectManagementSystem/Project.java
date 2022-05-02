package projectManagementSystem;

import io.cucumber.java.bs.A;

import java.util.ArrayList;
import java.util.Scanner;

public class Project {
    private ArrayList<Employee> assignees = new ArrayList<Employee>();
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private int ID;
    private String name;
    private String description;
    private String tempName;
    private String tempDesc;
    private double totalBudgetedTime;
    private Employee projectLeader;
    private boolean creatingActivity = false;
    private boolean statusShown = false;

    // Constructor
    public Project(String name, String description) {
        ID = 0; // noget
        this.name = name;
        this.description = description;
        ProjectManagementSystem.addProjectToList(this);
        totalBudgetedTime = 0;
    }

    public Project(){}

    public boolean getStatusShown() {
        return statusShown;
    }
    public void setStatusShown(Boolean b) {
        statusShown=b;
    }


    //Method kun til test
    public void createTestActivity(String name, String desc){
        this.activities.add(new Activity(name,desc,this));
    }


    // General methods
    public void createActivity(Employee e){
        // Check if inputs are viable and employee is project leader.
        if (this.getActivityWithName(tempName)!=null){
            throw new IllegalArgumentException("This name is already used, please enter another");
        }else if (tempName == ""){
            throw new IllegalArgumentException("Please enter a name");
        }else if (projectLeader!=e){
            throw new IllegalArgumentException("You do not have authority to create activities on this project");
        }

        // Create activity and add to activity list.
        Activity activity = new Activity(tempName, tempDesc, this);
        activities.add(activity);
        tempName = null;
        tempDesc = null;
    }

    public void addAssignee(Employee e) {
        assignees.add(e);
        e.getProjects().add(this);
    }
    public void removeAssignee(Employee e) {assignees.remove(e);}
    public void addActivity(Activity activity){ activities.add(activity); }
    public boolean userHaveAccessesToProject() {
        /**
         * TODO:
         * 	* Check if a user is assigned to this project
         * 	* if the user is assigned to the project grant them accesses
         */
        return true;
    }
    public void updateTotalBudgetedTime(double time){totalBudgetedTime = time;}


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
    public double getTotalBudgetedTime(){return totalBudgetedTime;}


    // Setter
    public void setTempName(String n){tempName = n;}
    public void setTempDesc(String n){tempDesc = n;}
    public void setCreatingActivity(Boolean b){creatingActivity = b;}
    public void setDescription(String description) {
        this.description = description;
    }
    public void setProjectLeader(Employee projectLeader) {
        this.projectLeader = projectLeader;

    } //only for tests

    public void setName(String name) {
        for (Project p : ProjectManagementSystem.getProjects()) {
            if (!p.getName().equals(this.name)) {

            }
        }
    }
    public void showStatus(Employee employee){
        if (employee != projectLeader){
            throw new IllegalArgumentException("You are not project leader on this project and can therefore not see status");
        }
        else {
            //do the thing
            setStatusShown(true);
        }
    }
}
