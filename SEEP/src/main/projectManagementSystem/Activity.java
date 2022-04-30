package projectManagementSystem;

import java.util.ArrayList;

public class Activity {
    // Fields
    private String ID;
    private String description;
    private int timetracking;
    private String name;
    private ArrayList<Employee> assignees = new ArrayList<Employee>();
    private Project parent;
    private boolean changingActivity = false;
    private String tempName;
    private String tempDesc;

    public Activity(String name, String desc, Project p){
        this.parent = p;
        this.name = name;
        this.description = desc;
        this.ID = Integer.toString(parent.getActivities().size());
    }

    public void changeActivity(){
        while (changingActivity){
            // Vent på confirm.
        }

        // TODO: Tjek at ting er korrekte.

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

    public ArrayList<Employee> getAssigness() {
        return assignees;
    }

    public void addAssignee(Employee employee1, Employee employee2){
        assignees.add(employee2);
    }

    public String getName(){return name;}
    public void setName(String n){name = n;}

    public String getDesc(){return description;}
    public void setDesc(String d){description = d;}

    public String getID(){return ID;}

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


    public String getTempDesc(){return tempDesc;}
    public String getTempName(){return tempName;}
    public void setChangingActivity(Boolean b){changingActivity = b;}

    public void setTempName(String n){tempName = n;}
    public void setTempDesc(String n){tempDesc = n;}
}
