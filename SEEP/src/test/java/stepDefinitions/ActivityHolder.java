package stepDefinitions;

// Class imports
import projectManagementSystem.*;

/*
 * This class holds an activity used for tests.
 *
 * - Helene
 */

public class ActivityHolder {
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
