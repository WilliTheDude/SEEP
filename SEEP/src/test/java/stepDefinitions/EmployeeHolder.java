package stepDefinitions;
// General imports

import java.util.ArrayList;

// Import of classes
import projectManagementSystem.*;

/*
 * This class holds all employees used for a test.
 *
 * - Helene
 */
public class EmployeeHolder {

    // fields
    private Employee employee;
    private ArrayList<Employee> employees = new ArrayList<>();

    // Constructor
    public EmployeeHolder() {

    }

    // Setter
    public void setEmployee(String name, int age, String gender) {
        employee = new Employee(name, age, gender);
        employees.add(employee);
    }

    public void setEmployee(Employee e) {
        employee = e;
        if (!employees.contains(e)) {
            employees.add(e);
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployeeWithName(String name) {
        Employee setE = null;
        for (Employee e : employees) {
            if (e.getName().equals(name)) {
                setE = e;
            }
        }
        return setE;
    }

    // Getters
    public Employee getEmployee() {
        if (employee == null) {
            employee = exampleEmployee();
        }
        return employee;
    }

    // General functions
    private Employee exampleEmployee() {
        return new Employee("Flemming", 46, "male");
    }
}
