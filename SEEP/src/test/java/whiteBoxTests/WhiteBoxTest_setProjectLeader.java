package whiteBoxTests;

import org.junit.Test;
import projectManagementSystem.Employee;
import projectManagementSystem.Project;

import static org.junit.Assert.*;

public class WhiteBoxTest_setProjectLeader {
    @Test
    public void TestInputSetA(){
        var p = new Project("project1", "desc");
        var e1 = new Employee("e1",1,"male");
        var e2 = new Employee("e2",2,"female");
        p.setProjectLeader(null);
        p.getAssignees().add(e1);
        e1.setProjectLeader(p,e2);
        assertSame(p.getProjectLeader(), e2);
        assertTrue(p.getAssignees().contains(e2));
    }

    @Test
    public void TestInputSetB(){
        var p = new Project("project1", "desc");
        var e1 = new Employee("e1",1,"male");
        p.setProjectLeader(null);
        p.getAssignees().add(e1);
        e1.setProjectLeader(p,e1);
        assertSame(p.getProjectLeader(), e1);
        assertTrue(p.getAssignees().contains(e1));
    }

    @Test
    public void TestInputSetC(){
        var errorMessage = "";
        var p = new Project("project1", "desc");
        var e1 = new Employee("e1",1,"male");
        var e2 = new Employee("e2",2,"female");
        p.setProjectLeader(new Employee("project leader",23,"female"));
        try {
            p.getAssignees().add(e1);
            e1.setProjectLeader(p,e2);
        }catch (Exception e){
            errorMessage = e.getMessage();
        }
        assertNotSame(p.getProjectLeader(), e2);
        assertEquals("There can only be one project leader", errorMessage);
        assertFalse(p.getAssignees().contains(e2));
    }

    @Test
    public void TestInputSetD(){
        var errorMessage = "";
        var p = new Project("project1", "desc");
        var e1 = new Employee("e1",1,"male");
        var e2 = new Employee("e2",2,"female");
        try {
            p.setProjectLeader(null);
            e1.setProjectLeader(p,e2);
        }catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertNotSame(p.getProjectLeader(), e2);
        assertEquals(errorMessage, "User needs to be part of project to assign project leaders");
        assertFalse(p.getAssignees().contains(e2));
    }
}
