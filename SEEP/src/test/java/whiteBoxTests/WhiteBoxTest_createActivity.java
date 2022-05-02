package whiteBoxTests;

import org.junit.Test;
import projectManagementSystem.Activity;
import projectManagementSystem.Employee;
import projectManagementSystem.Project;

import static org.junit.Assert.*;

public class WhiteBoxTest_createActivity {
    @Test
    public void testInputSetA(){
        var project = new Project("project1","This is a project");
        var e1 = new Employee("Bob",48,"male");
        project.setProjectLeader(e1);
        project.setTempName("activity1");
        project.setTempDesc("description");
        project.createActivity(e1);
        assertNotNull(project.getActivityWithName("activity1"));
    }

    @Test
    public void testInputSetB(){
        var errorMessage = "";
        try{
            var project = new Project("project1","This is a project");
            var e1 = new Employee("Bob",48,"male");
            project.setProjectLeader(e1);
            project.addActivity(new Activity("activity1","description",project));
            project.setTempName("activity1");
            project.setTempDesc("description");
            project.createActivity(e1);
        } catch(Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage,"This name is already used, please enter another");
    }

    @Test
    public void testInputSetC(){
        var errorMessage = "";
        try{
            var project = new Project("project1","This is a project");
            var e1 = new Employee("Bob",48,"male");
            project.setProjectLeader(e1);
            project.setTempName("");
            project.setTempDesc("description");
            project.createActivity(e1);
        } catch(Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage,"Please enter a name");
    }

    @Test
    public void testInputSetD(){
        var errorMessage = "";
        try{
            var project = new Project("project1","This is a project");
            var e1 = new Employee("Bob",48,"male");
            var e2 = new Employee("Clair", 28, "female");
            project.setProjectLeader(e1);
            project.setTempName("activity1");
            project.setTempDesc("description");
            project.createActivity(e2);
        } catch(Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage,"You do not have authority to create activities on this project");
    }
}
