package stepDefinitions;
// General imports
import java.util.ArrayList;

// Import of classes
import projectManagementSystem.*;

public class EmployeeHolder {

    // fields
    private ProjectManagementSystem scheduleApp;
    private Employee employee;
    private ArrayList<Employee> employees = new ArrayList<>();

    // Constructor
    public EmployeeHolder(ProjectManagementSystem scheduleApp){
        this.scheduleApp = scheduleApp;
    }

    // Setter
    public void setEmployee(String name, int age, String gender){
        employee = new Employee(name,age,gender);
        employees.add(employee);
    }

    public void setEmployee(Employee e){
        employee = e;
        if (!employees.contains(employee)){
            employees.add(employee);
        }
    }

    // Getters
    public Employee getEmployee(){
        if (employee == null){
            employee = exampleEmployee();
        }
        return employee;
    }

    // General functions
    private Employee exampleEmployee(){
        return new Employee("Flemming", 46, "male");
    }
}
