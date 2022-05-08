package whiteBoxTests;

import org.junit.Test;
import projectManagementSystem.Activity;
import projectManagementSystem.Employee;
import projectManagementSystem.Project;

import static org.junit.Assert.*;

public class WhiteBoxTest_addAssignee {
    @Test
    public void testInputSetA(){
        var project = new Project("project1","This is a project");
        var a = new Activity("activity", "description", project);
        var e1 = new Employee("Alice",32,"female");
        var e2 = new Employee("Steve",48,"male");
        var errorMessage = "";
        try {
            a.addAssignee(e1,e2);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage,"You don't have permission to add others to this activity");
        assertFalse(a.getAssignees().contains(e2));
    }

    @Test
    public void testInputSetB(){
        var project = new Project("project1","This is a project");
        var a = new Activity("activity", "description", project);
        var e1 = new Employee("Alice",32,"female");
        var e2 = new Employee("Steve",48,"male");
        a.getAssignees().add(e1);
        a.addAssignee(e1,e2);
        assertTrue(a.getAssignees().contains(e2));
    }

    @Test
    public void testInputSetC(){
        var project = new Project("project1","This is a project");
        var a = new Activity("activity", "description", project);
        var e1 = new Employee("Alice",32,"female");
        var e2 = new Employee("Steve",48,"male");
        a.getAssignees().add(e2);
        a.addAssignee(e1,e2);
        assertEquals(a.getAssignees().size(), 1);
    }

}
