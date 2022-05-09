package whiteBoxTests;

import io.cucumber.java.en_old.Ac;
import org.junit.Test;
import static org.junit.Assert.*;
import projectManagementSystem.*;

public class WhiteBoxTest_changeActivity {
    @Test
    public void testInputSetA() {
        var errorMessage = "";
        try {
            var project = new Project("Project 1", "This is the project");
            var a = new Activity("Activity 2", "This is our activity", project);
            project.addActivity(new Activity("Activity 1", "activity with name Activity 1", project));
            project.addActivity(a);
            a.setTempName("Activity 1");
            a.setTempDesc("this is  the better version");
            a.changeActivity();
        } catch(Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage, "This name is already used, please enter another");
    }

    @Test
    public void testInputSetB() {
        var errorMessage = "";
        try {
            var parent = new Project("Project 1", "This is a project");
            var a = new Activity("Activity 2", "This is our activity", parent);
            parent.addActivity(new Activity("Activity 1", "This is our first activity", parent));
            parent.addActivity(a);
            a.setTempDesc("This is the temporary Description");
            a.setTempName("");
            a.changeActivity();
        } catch(Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage, "Please enter a name");
    }

    @Test
    public void testInputSetC() {
        var errorMessage = "";
        var parent = new Project("Project 1", "This is our project");
        var a = new Activity("Activity 2", "This is our third activity", parent);
        parent.addActivity(new Activity("Activity 1", "This is our first activity", parent));
        a.setTempName("Activity 3");
        a.setTempDesc("This is our third activity");
        String name = "";
        try {
            a.changeActivity();
            name = a.getName();
        } catch(Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage, "");
        assertEquals("Activity 3", name);
    }
}
