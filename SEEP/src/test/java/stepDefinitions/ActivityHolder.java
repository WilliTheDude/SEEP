package stepDefinitions;

// Class imports
import projectManagementSystem.*;

public class ActivityHolder {
    // smtn. needs to be done to get this to hold an activity, but I am unsure on how it will be connected to a project.
    // TODO: Find ud af hvordan denne skal oprettes

    // Fields
    private Activity activity;

    // Constructor
    public ActivityHolder(ProjectManagementSystem scheduleApp){
    }

    // General functions
    private Activity exampleActivity(){
        return new Activity("exampleActivity", "", new Project());
    }


    // Setter
    public void setActivity(String name, String desc, Project p){
        activity = new Activity(name, desc, p);
    }

    public void setActivity(Activity a){
        activity = a;
    }

    // Getter
    public Activity getActivity(){
        if (activity == null){
            activity = exampleActivity();
        }
        return activity;
    }
}
