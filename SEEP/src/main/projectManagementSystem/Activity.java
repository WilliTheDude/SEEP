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

    /*
     * TODO: find ud af hvad ideen var bag denne method
     *  * og hvor den skal bruges
     *  * // public String status(){
     *         // Do things with this string
     *         String returnString = "";
     *         return returnString;
     *     }
     */
    public void addAssignee(Employee employee1, Employee employee2) {
        if (assignees.contains(employee2)) {
            return;
        }
        if (assignees.contains(employee1)) {
            assignees.add(employee2);
        }
        else throw new IllegalArgumentException("You have no permission to add others to this activity");
    }

    public void addAssignee(Employee employee) {
        if (assignees.contains(employee)) return;
        assignees.add(employee);
    }

    public void removeAssignee(Employee employee) {
        assignees.remove(employee);
    }

    public void budgetTimeForActivity(double time) {
        try {
            if (parent.getProjectLeader().equals(ProjectManagementSystem.getLoggedInEmployee())) {
                budgetedTime = time;
                parent.updateTotalBudgetedTime(budgetedTime);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("It's only the project leader that can budget time for activities");
        }
    }

    public void deleteBudgetedTimeForActivity() {
        if (parent.getProjectLeader().equals(ProjectManagementSystem.getLoggedInEmployee())) {
            parent.removeTimeFormTotalBudgetTime(budgetedTime); // Removes the current total budgeted time from project total time
            budgetedTime = 0; // updates the budgeted time on the activity.
        }
    }


    // Getter
    /**
     TODO find the tests where this method is used and correct them so the method is covered:
        * // public Employee getEmployeeWithName(String name) {
            Employee returnEmployee = null;
            for (Employee employee : assignees) {
                if (employee.getName().equals(name)) {
                    returnEmployee = employee;
                }
            }
            return returnEmployee;
        }
     */

    public ArrayList<Employee> getAssignees() {
        return assignees;
    }

    /**
     * TODO Find out if we want to use these in the code
        * // public String getTempDesc() { return tempDesc;}
        * // public String getTempName() { return tempName;}
     */
    public String getDesc() { return description; }
    public String getName() { return name; }
    public double getBudgetedTime() { return budgetedTime; }

    // Setter
    /**
     * TODO find these setters in the tests and the code and then fix it.
     *     //public void setChangingActivity(Boolean b) {changingActivity = b;}
     *     //public void setDesc(String d) { description = d; }
     */

    public void setTempName(String n) { tempName = n; }
    public void setTempDesc(String n) { tempDesc = n;}
    //public void setName(String n) { name = n; }
}
