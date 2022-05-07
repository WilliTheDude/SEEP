package projectManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

// Embla
public class CLI {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> options = new ArrayList<>();
    private static ArrayList<String> path = new ArrayList<>();
    private static Project currentProject;
    private static Activity currentActivity;
    public CLI(){}
    public static void setup(){
        options.add("help");
        options.add("close");
        options.add("log in");
        help();

        ProjectManagementSystem.addEmployeeToList(new Employee("Allan", 40, "male" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Bodil", 42, "female" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Carl", 34, "male" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Dagmar", 44, "female" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Egon", 41, "male" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Freja", 35, "female" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Gorm", 53, "male" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Helga", 33, "female" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Ingolf", 63, "male" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Johanne", 67, "female" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Knud", 50, "male" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Laura", 53, "female" ));
        ProjectManagementSystem.addEmployeeToList(new Employee("Malik", 46, "male" ));

        ProjectManagementSystem.addProjectToList(new Project("General","Project with general tasks"));
        ProjectManagementSystem.addProjectToList(new Project("StormWeb","Create website with data of storms"));

        ProjectManagementSystem.getProjectWithName("General").addActivity(new Activity("MainActivity", "The main activity", ProjectManagementSystem.getProjectWithName("General") ));
        ProjectManagementSystem.getProjectWithName("General").addActivity(new Activity("nr2", "den anden", ProjectManagementSystem.getProjectWithName("General") ));

        ProjectManagementSystem.getProjectWithName("General").addAssignee(ProjectManagementSystem.getEmployeeWithName("Allan"));
        ProjectManagementSystem.getProjectWithName("General").addAssignee(ProjectManagementSystem.getEmployeeWithName("Bodil"));
        ProjectManagementSystem.getProjectWithName("General").addAssignee(ProjectManagementSystem.getEmployeeWithName("Carl"));
        ProjectManagementSystem.getProjectWithName("General").getActivityWithName("MainActivity").addAssignee(ProjectManagementSystem.getEmployeeWithName("Dagmar"));
        ProjectManagementSystem.getProjectWithName("General").getActivityWithName("MainActivity").addAssignee(ProjectManagementSystem.getEmployeeWithName("Allan"));


        ProjectManagementSystem.getProjectWithName("General").setProjectLeader(ProjectManagementSystem.getEmployeeWithName("Allan"));
        ProjectManagementSystem.getProjectWithName("StormWeb").setProjectLeader(ProjectManagementSystem.getEmployeeWithName("Bodil"));
    }
    public static void run(){
        while (scanner.hasNext()) {
            boolean correct = false;
            String in = scanner.nextLine();
            for (String s:options) {
                if (in.equals(s)){
                    correct = true;
                    runCommand(s);
                    break;
                }
            }
            if(!correct){
                System.out.println(in+" :Command not recognized, see help for available commands");
            }

        }
    }
    private static void runCommand(String s){
        switch (s) {
            case "help" -> help();
            case "close" -> System.exit(0);
            case "log in" -> logIn();
            case "enter project" -> enterProject();
            case "enter helper activity" -> enterHelperActivity();
            case "return to menu" -> returnToMenu();
            case "create activity" -> createActivity();
            case "enter activity" -> enterActivity();
            case "change activity" -> changeActivity();
            case "create project" -> createProject();
            case "set project leader" -> setProjectLeader();
        }
    }
    private static void logIn(){
        System.out.println("Write your name:");
        Employee empName = ProjectManagementSystem.getEmployeeWithName(scanner.nextLine());

        if(empName != null){
            ProjectManagementSystem.setLoggedInEmployee(empName);
            removeOption("log in");
            returnToMenu();
        }else{
            System.out.println("User doesn't exist");
            return;
        }
    }
    private static void removeOption(String s){
        for (int i = options.size()-1; i>=0; i--){
            if (options.get(i).equals(s)){
                options.remove(i);
            }
        }
    }
    private static void createProject(){
        removeOption("create project");
        removeOption("enter project");
        removeOption("enter helper activity");
        System.out.println("Write name of new project");
        String name = scanner.nextLine();
        System.out.println("Write description of project " + name);
        String desc = scanner.nextLine();

        currentProject = ProjectManagementSystem.getLoggedInEmployee().createProject(name, desc);
        path.add(currentProject.getName());
        printPath();
        options.add("set project leader");
    }
    private static void enterProject(){
        System.out.println("Choose project:");
        int i=1;
        for (Project project: ProjectManagementSystem.getLoggedInEmployee().getProjects()) {
            System.out.println(i + ": " + project.getName());
            i++;
        }
        Project p = ProjectManagementSystem.getProjectWithName(scanner.nextLine());
        if (!p.getAssignees().contains(ProjectManagementSystem.getLoggedInEmployee())){
            System.out.println("You are not assigned to this project");
            return;
        }
        removeOption("enter project");
        removeOption("enter helper activity");
        currentProject = p;
        path.add(currentProject.getName());
        if(currentProject.getProjectLeader() == ProjectManagementSystem.getLoggedInEmployee()){
            options.add("add employee");
            options.add("create activity");
        }
        options.add("enter activity");
        if (currentProject.getProjectLeader()==null){
            options.add("set project leader");
        }

        printPath();
    }
    private static void enterHelperActivity(){
        removeOption("enter project");
        removeOption("enter helper activity");

        System.out.println("Choose activity in project (write project on first line and activity on second line):");
        int i=1;
        for (Activity activity: ProjectManagementSystem.getLoggedInEmployee().getActivities()) { // listen er tom... hvorfor???
            System.out.println(i + ": " + activity.getParent().getName() + "\\" + activity.getName());
            i++;
        }

        currentProject = ProjectManagementSystem.getProjectWithName(scanner.nextLine());
        currentActivity = currentProject.getActivityWithName(scanner.nextLine());
        path.add(currentProject.getName());
        path.add(currentActivity.getName());
        printPath();

    }
    private static void createActivity(){
        removeOption("create activity");
        removeOption("enter activity");
        System.out.println("Write name of new activity");
        String name = scanner.nextLine();
        currentProject.setTempName(name);
        System.out.println("Write description of activity " + name);
        String desc = scanner.nextLine();
        currentProject.setTempDesc(desc);
        currentProject.createActivity(ProjectManagementSystem.getLoggedInEmployee());
        currentActivity = currentProject.getActivityWithName(name);
        path.add(currentActivity.getName());
        printPath();
    }
    private static void enterActivity(){
        System.out.println("Choose activity: ");
        int i=1;
        for (Activity activity: currentProject.getActivities()) {
            System.out.print(i + ": " + activity.getName());
            if (activity.getAssignees().contains(ProjectManagementSystem.getLoggedInEmployee())){
                System.out.print("          ------member");
            }
            System.out.println();
            i++;

        }
        Activity a = currentProject.getActivityWithName(scanner.nextLine());
        if (!a.getAssignees().contains(ProjectManagementSystem.getLoggedInEmployee())){
            System.out.println("You are not assigned to this activity");
            return;
        }
        removeOption("enter activity");
        removeOption("create activity");
        currentActivity = a;
        path.add(currentActivity.getName());
        if (currentProject.getProjectLeader() == ProjectManagementSystem.getLoggedInEmployee()) {
            options.add("change activity");
        }
        printPath();
    }
    private static void changeActivity(){
        removeOption("change activity");
        System.out.println("Write new name of new activity");
        currentActivity.setTempName(scanner.nextLine());
        System.out.println("Write new description of activity " + currentActivity.getName());
        currentActivity.setTempDesc(scanner.nextLine());
        currentActivity.changeActivity();

    }
    private static void setProjectLeader(){

    }

    private static void returnToMenu(){
        if (currentProject != null) {
            currentProject.setStatusShown(false);
            currentProject.setTempName(null);
            currentProject.setTempDesc(null);
        }
        if (currentActivity != null) {
            currentActivity.setTempName(null);
            currentActivity.setTempDesc(null);
        }
        currentProject = null;
        currentActivity = null;
        options.clear();
        options.add("help");
        options.add("close");
        options.add("return to menu");
        options.add("enter project");
        options.add("enter helper activity");
        options.add("create project");
        path.clear();
        path.add(ProjectManagementSystem.getLoggedInEmployee().getName());
        printPath();
    }
    private static void printPath(){
        for (String s: path) {
            System.out.print("\\"+s);
        }
        System.out.println();
    }
    private static void help(){
        printPath();
        System.out.println("Here are your options. Type the option you choose.");
        for (int i=0; i<options.size(); i++){
            System.out.println(i+1 + ": " + options.get(i));
        }
    }

}
