package projectManagementSystem;

import java.util.ArrayList;
import java.util.jar.JarOutputStream;

public class Activity {
    private String ID;
    private String description;
    private String name;
    private String tempDesc;
    private String tempName;
    private double budgetedTime;
    private boolean changingActivity = false;
    private ArrayList<Employee> assignees = new ArrayList<Employee>();
    private Project parent;

    // Constructor
    public Activity(String name, String desc, Project p){
        this.parent = p;
        this.name = name;
        this.description = desc;
        this.ID = Integer.toString(parent.getActivities().size());
        this.budgetedTime = 0;
    }

    // General functions
    public void changeActivity(){
        // TODO: Få input
        // TODO: Tjek at inputs er korrekte.

        // Tjek at variable er korrekte:
        if (parent.getActivitiesWithName(tempName)!=null && name != tempName){
            throw new IllegalArgumentException("This name is already used, please enter another");
        }else if (tempName == ""){
            throw new IllegalArgumentException("Please enter a name");
        }

        this.name = tempName;
        this.description = tempDesc;
        tempName = null;
        tempDesc = null;
    }
    public String status(){
        String returnString = "";
        //DO THING TO STRING

        return returnString;
    }

    public void addAssignee(Employee employee1, Employee employee2){
        assignees.add(employee2);
    }
    public void addAssignee(Employee employee) {
        assignees.add(employee);
    } // Special for test only
    public void removeAssignee(Employee employee){
        assignees.remove(employee);
    }
    public void budgetTimeForActivity(double time) {
        try {
            if (parent.getProjectLeader().equals(ProjectManagementSystem.getLoggedInEmployee())) {
                budgetedTime = time;
                parent.updateTotalBudgetedTime(budgetedTime);
            } else {
                throw new IllegalArgumentException("It's only the project leader that can budget time for activities");
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("It's only the project leader that can budget time for activities");
        }
    }
    public void deleteBudgetedTimeForActivity() {
        if(parent.getProjectLeader().equals(ProjectManagementSystem.getLoggedInEmployee())){
            parent.removeTimeFormTotalBudgetTime(budgetedTime); // Removes the current total budgeted time from project total time
            budgetedTime = 0; // updates the budgeted time on the activity.
        } else {
            throw new IllegalArgumentException("It's only the project leader that can delete budgeted time for an " +
                    "activity");
        }
    }


    // Getter
    public Employee getEmployeeWithName(String name){
        Employee returnEmployee = null;
        for (Employee employee: assignees) {
            if(employee.getName().equals(name)){
                returnEmployee = employee;
            }
        }
        return returnEmployee;
    }

    public ArrayList<Employee> getAssignees(){
        return assignees;
    }
    public String getID(){return ID;}
    public String getTempDesc(){return tempDesc;}
    public String getTempName(){return tempName;}
    public String getDesc(){return description;}
    public String getName(){return name;}
    public double getBudgetedTime(){return budgetedTime;}

    // Setter
    public void setChangingActivity(Boolean b){changingActivity = b;}
    public void setTempName(String n){tempName = n;}
    public void setTempDesc(String n){tempDesc = n;}
    public void setDesc(String d){description = d;}
    public void setName(String n){name = n;}
}
