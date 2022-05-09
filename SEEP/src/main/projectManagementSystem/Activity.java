package projectManagementSystem;

import java.util.ArrayList;

/*
* The function of this class is to store and handle the information of an Activity.
*
* Class responsible: Helene
 */

public class Activity {
    // Fields
    private final String ID;
    private String description;
    private String name;
    private String tempDesc;
    private String tempName;
    private double budgetedTime;
    private ArrayList<Employee> assignees = new ArrayList<Employee>();
    private final Project parent;

    // Constructor
    public Activity(String name, String desc, Project p) {
        this.parent = p;
        this.name = name;
        this.description = desc;

        // TODO code the method for id generation
        this.ID = Integer.toString(parent.getActivities().size());
        this.budgetedTime = 0;
    }

    // General functions

    /*
     * changeActivity()
     *
     * Checks if the information stored in temporary holders, tempName and tempDesc, is valid.
     * If they are ok, the name and description of the Activity is changed.
     *
     * - Helene
     */
    public void changeActivity() {
        if (parent.getActivitiesWithName(tempName) != null && name != tempName) {
            throw new IllegalArgumentException("This name is already used, please enter another");
        } else if (tempName == "") {
            throw new IllegalArgumentException("Please enter a name");
        }

        this.name = tempName;
        this.description = tempDesc;
        tempName = null;
        tempDesc = null;
    }

    public void addAssignee(Employee employee1, Employee employee2) {
        if (assignees.contains(employee2)) {
            return;
        }
        if (assignees.contains(employee1)) {
            assignees.add(employee2);
            employee2.addEmployeeToActivity(this);
        }
        else throw new IllegalArgumentException("You don't have permission to add others to this activity");
    }

    public void addAssignee(Employee employee) {
        if (assignees.contains(employee)) return;
        assignees.add(employee);
        employee.addEmployeeToActivity(this);
    }

    public void removeAssignee(Employee employee) {
        assignees.remove(employee);
    }

    public void budgetTimeForActivity(double time) {
        try {
            if (parent.getProjectLeader().equals(ProjectManagementSystem.getLoggedInEmployee())) {
                budgetedTime = time;
                parent.updateTotalBudgetedTimeForProject(budgetedTime);
            } else throw new IllegalArgumentException("It's only the project leader that can budget time for activities");
        } catch (Exception e) {
            throw new IllegalArgumentException("It's only the project leader that can budget time for activities");
        }
    }

    public void deleteBudgetedTimeForActivity() {
        try {
            if (parent.getProjectLeader().equals(ProjectManagementSystem.getLoggedInEmployee())) {
                parent.removeTimeFormTotalBudgetTime(budgetedTime); // Removes the current total budgeted time from project total time
                budgetedTime = 0; // updates the budgeted time on the activity.
            } else throw new IllegalArgumentException("It's only the project leader that can delete budgeted time for activities");
        } catch (Exception e) {
            throw new IllegalArgumentException("It's only the project leader that can delete budgeted time for activities");
        }
    }


    // Getter
    public ArrayList<Employee> getAssignees() { return assignees; }
    public String getDesc() { return description; }
    public String getName() { return name; }
    public double getBudgetedTime() { return budgetedTime; }
    public Project getParent() { return parent; }

    // Setter
    public void setTempName(String n) { tempName = n; }
    public void setTempDesc(String n) { tempDesc = n;}

}
