package stepDefinitions;

// Class imports
import projectManagementSystem.*;

//General imports

public class ProjectHolder {
    private Project project;
    private ProjectManagementSystem scheduleApp;

    // Constructor
    public ProjectHolder (ProjectManagementSystem scheduleApp){
        //this.scheduleApp = scheduleApp;
    }

    // Function for getting and instantiating the project
    public Project getProject() {
        if(project == null) {
            project = exampleProject();
        }
        return project;
    }

    // Creates an example/a dummy project
    private Project exampleProject() {
        String description = "This is a dummy project for doing the testing";
        return new Project("project1", description);
    }

    public void setProject(Project p){
        project = p;
    }

    public void setProject(String name, String description){
        project = new Project(name, description);
    }

    // Create a project with at specific name
    public void setProject(String name) {
        String description = "A dummy project with a specific name";
        project = new Project(name, description);
    }

    // Create a project with at specific number
    public void createProjectWithSpecificNumber(int number) {
        String description = "A project with a specific number";
        project = new Project("project1", description);
    }

    // Create a project wih specific description
    public void createProjectWithSpecificDescription(String description) {
        project = new Project("project1",description);
    }
}
