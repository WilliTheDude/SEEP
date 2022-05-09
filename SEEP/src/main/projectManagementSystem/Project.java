package projectManagementSystem;

import java.util.ArrayList;
import java.util.Calendar;

// William
public class Project {
    private final ArrayList<Employee> assignees = new ArrayList<Employee>();
    private final ArrayList<Activity> activities = new ArrayList<Activity>();
    private int ID;
    private static int projectNum = 1;
    private String name;
    private String description;
    private String tempName;
    private String tempDesc;
    private double totalBudgetedTime;
    private Employee projectLeader;
    private boolean statusShown;

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

    /*
     * createActivity()
     *
     * Checks if the information stored in temporary holders, tempName and tempDesc, is valid.
     * If they are ok and the actor is projectLeader, the Activity is created.
     *
     * - Helene
     */
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
    public void updateTotalBudgetedTimeForProject(double time){
        try {
            if (this.getProjectLeader().equals(ProjectManagementSystem.getLoggedInEmployee())) totalBudgetedTime += time;
            else throw new IllegalArgumentException("It's only the project leader there can update budgeted time for a project");
        } catch(Exception e) {
            throw new IllegalArgumentException("It's only the project leader there can update budgeted time for a project");
        }
    }
    public void removeTimeFormTotalBudgetTime(double time) {totalBudgetedTime -= time;}
    private int generateID() {
        try {
            if (ProjectManagementSystem.getProjects().size() == 0)
                return Integer.parseInt((ProjectManagementSystem.getCalendar().get(Calendar.YEAR) + "").substring(2, 4) + (String.format("%03d", Project.projectNum++)));
            for (Project p : ProjectManagementSystem.getProjects()) {
                if (this.ID != p.getID()) return Integer.parseInt((ProjectManagementSystem.getCalendar().get(Calendar.YEAR) + "").substring(2, 4) + (String.format("%03d", Project.projectNum++)));
                else throw new IllegalArgumentException("The ID of two projects may not be the same");
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("The ID of two projects may not be the same");
        }
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
    public String getDescription() { return description;}
    public ArrayList<Activity> getActivities() { return activities;}
    public ArrayList<Employee> getAssignees(){ return assignees;}
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
    public double getTotalBudgetedTimeForProject(){return totalBudgetedTime;}
    public boolean getStatusShown() { return statusShown;}

    // Setter
    public void setTempName(String n){tempName = n;}
    public void setTempDesc(String n){tempDesc = n;}
    public void setDescription(String description) { this.description = description;}
    public void setProjectLeader(Employee projectLeader) { this.projectLeader = projectLeader; }
    public void showStatus(Employee employee){
        if (employee != projectLeader){
            throw new IllegalArgumentException("You are not project leader on this project and can therefore not see status");
        }
        else {
            System.out.println(CLI.currentProject.getName() + ": " + CLI.currentProject.getDescription());
            System.out.println("Activities:");
            double totalTime = 0;
            for (Activity a : CLI.currentProject.getActivities()){
                System.out.println("  " + a.getName() + ": " + a.getDesc() );
                System.out.println("    time: " + a.getBudgetedTime());
                System.out.println("    assignees: " + a.getAssignees().size());
                totalTime+= a.getBudgetedTime();

            }
            System.out.println("Total time for project: " + totalTime);
            setStatusShown(true); // Only used for tests
        }
    }
    public void setStatusShown(Boolean b) { statusShown=b;}
}
