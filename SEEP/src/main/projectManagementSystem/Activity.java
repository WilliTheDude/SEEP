package projectManagementSystem;

import java.util.ArrayList;

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
        if (assignees.contains(employee2)) return;
        if (assignees.contains(employee1)) assignees.add(employee2);
        else throw new IllegalArgumentException("You have no permission to add others to this activity");
    }
    public void addAssignee(Employee employee) {
        if (assignees.contains(employee)) return;
        assignees.add(employee);
    } // Special for test only
    public void removeAssignee(Employee employee){
        assignees.remove(employee);
    }

    public void budgetTimeForActivity(double time) {
        /**
         * TODO:
         *  * make it budget the time for the activity
         *  * When the time is budgeted then it updates the activity
         *  * And then it also updates the total time on the project.
         */
    }

    public void deleteBudgetedTimeForActivity() {
        budgetedTime = 0;
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
