package projectManagementSystem;

import java.util.ArrayList;
import java.util.Calendar;

public class Project {
    private ArrayList<Employee> assignees = new ArrayList<Employee>();
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private int ID;
    private static int projectNum = 1;
    private String name;
    private String description;
    private String tempName;
    private String tempDesc;
    private double totalBudgetedTime;
    private Employee projectLeader;
    private boolean statusShown;

    // Default constructor
    //TODO see if it's testing that needs to be done here or if there already exists a test that is wrong
    public Project(){}

    // parameterised Constructor
    public Project(String name, String description) {
        if (name == null) { throw new IllegalArgumentException("The project must have a name");}
        else {
            this.name = name;
            this.ID = generateID();
            this.description = description;
            this.totalBudgetedTime = 0;
            ProjectManagementSystem.addProjectToList(this);
        }
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

    /*
    public boolean userHaveAccessesToProject() {
        /**
         * TODO:
         * 	* Check if a user is assigned to this project
         * 	* if the user is assigned to the project grant them accesses

        return true;
    }*/

    public void updateTotalBudgetedTime(double time){
        try {
            if (this.getProjectLeader().equals(ProjectManagementSystem.getLoggedInEmployee())) totalBudgetedTime += time;
        } catch(Exception e) {
            throw new IllegalArgumentException("It's only the project leader there can see the total time of the project");
        }
    }
    public void removeTimeFormTotalBudgetTime(double time) {totalBudgetedTime -= time;}

    //
    private int generateID() {
        try {
            if (ProjectManagementSystem.getProjects().size() == 0)
                return Integer.parseInt((ProjectManagementSystem.getCalendar().get(Calendar.YEAR) + "").substring(2, 4) + (String.format("%03d", Project.projectNum++)));
            for (Project p : ProjectManagementSystem.getProjects()) {
                if (this.ID != p.getID()) return Integer.parseInt((ProjectManagementSystem.getCalendar().get(Calendar.YEAR) + "").substring(2, 4) + (String.format("%03d", Project.projectNum++)));
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("The ID of two projects may not be the same");
        }
        //TODO: fix this return in the test
        return -1;
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
    // public String getTempDesc(){return tempDesc;}
    // public String getTempName(){return tempName;}
    public ArrayList<Activity> getActivities() { return activities;}
    public ArrayList<Employee> getAssignees(){ return assignees;}
    public String getDescription() { return description;} //TODO find were this code is used and fix the tests
    public String getName() { return name; }
    public int getID() { return ID; }
    public Employee getProjectLeader() { return projectLeader;}
    public Activity getActivitiesWithName(String activityName){
        Activity returnActivity = null;
        for (Activity activity: activities) {
            if (activity.getName().equals(activityName)){
                returnActivity = activity;
            }
        }
        return returnActivity;
    }

    /** TODO: find where this method should be used and then check if it's relevant to have it.
            * public Employee getEmployeeWithName(String name){
                Employee returnEmployee = null;
                for (Employee employee: assignees) {
                    if(employee.getName().equals(name)){
                        returnEmployee = employee;
                    }
                }
                return returnEmployee;
    }*/

    public double getTotalBudgetedTime(){return totalBudgetedTime;}
    public boolean getStatusShown() { return statusShown;}

    // Setter
    public void setTempName(String n){tempName = n;}
    public void setTempDesc(String n){tempDesc = n;}
    public void setDescription(String description) { this.description = description;}
    public void setProjectLeader(Employee projectLeader) {
        this.projectLeader = projectLeader;

    }
    //only for tests
    /** TODO find where we use this and if we use it then change the code or the test
            * public void setName(String name) {
                for (Project p : ProjectManagementSystem.getProjects()) {
                    if (!p.getName().equals(this.name)) {
                    }
                }
            }
     */
    public void showStatus(Employee employee){
        if (employee != projectLeader){
            throw new IllegalArgumentException("You are not project leader on this project and can therefore not see status");
        }
        else {
            setStatusShown(true);
        }
    }
    public void setStatusShown(Boolean b) { statusShown=b;}
}
