package projectManagementSystem;

import io.cucumber.java.an.E;

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
        options.add("log in");
        options.add("close");
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
        ProjectManagementSystem.getProjectWithName("General").getActivityWithName("nr2").addAssignee(ProjectManagementSystem.getEmployeeWithName("Carl"));


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
            case "log out" -> logOut();
            case "add employee" -> addEmployee();
            case "budget time" -> budgetTime();
            case "show budgeted time" -> showBudgetedTime();
            case "show status" -> showStatus();
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
    private static void logOut(){
        options.clear();
        options.add("help");
        options.add("log in");
        options.add("close");
        ProjectManagementSystem.setLoggedInEmployee(null);
        currentProject = null;
        currentActivity = null;
        path.clear();
        System.out.println("You are now logged out");
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
        Project p;
        try {
            p = ProjectManagementSystem.getLoggedInEmployee().getProjectWithName(scanner.nextLine());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        removeOption("enter project");
        removeOption("enter helper activity");
        removeOption("create project");
        currentProject = p;
        path.add(currentProject.getName());
        if(currentProject.getProjectLeader() == ProjectManagementSystem.getLoggedInEmployee()){
            options.add("add employee");
            options.add("create activity");
            options.add("show status");
        }
        options.add("enter activity");
        if (currentProject.getProjectLeader()==null){
            options.add("set project leader");
            removeOption("enter activity");
        }

        printPath();
    }
    private static void enterHelperActivity(){
        System.out.println("Choose activity in project (write project on first line and activity on second line):");
        int i=1;
        for (Activity activity: ProjectManagementSystem.getLoggedInEmployee().getActivities()) {
            System.out.println(i + ": " + activity.getParent().getName() + "\\" + activity.getName());
            i++;
        }
        Project p;
        Activity a;
        try {
            p = ProjectManagementSystem.getProjectWithName(scanner.nextLine());
            a = ProjectManagementSystem.getLoggedInEmployee().getActivityWithName(scanner.nextLine(),p);
        } catch (Exception e){
            System.out.println("This is not a valid path");
            return;
        }
        currentProject = p;
        currentActivity = a;
        path.add(currentProject.getName());
        path.add(currentActivity.getName());
        printPath();
        removeOption("enter project");
        removeOption("enter helper activity");
        removeOption("create project");

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
            if (activity.getAssignees().contains(ProjectManagementSystem.getLoggedInEmployee()) || activity.getParent().getProjectLeader() == ProjectManagementSystem.getLoggedInEmployee()){
                System.out.print("          ------member");
            }
            System.out.println();
            i++;

        }
        Activity a;
        try {
            a = currentProject.getActivityWithName(scanner.nextLine());
            if (!a.getAssignees().contains(ProjectManagementSystem.getLoggedInEmployee()) && a.getParent().getProjectLeader() != ProjectManagementSystem.getLoggedInEmployee()){
                System.out.println("You are not assigned to this activity");
                return;
            }
        }catch(Exception e){
            System.out.println("No such activity exists in this project");
            return;
        }
        if (currentProject.getProjectLeader() != ProjectManagementSystem.getLoggedInEmployee()) {
            options.add("add employee");
        }

        removeOption("enter activity");
        removeOption("create activity");
        currentActivity = a;
        path.add(currentActivity.getName());
        if (currentProject.getProjectLeader() == ProjectManagementSystem.getLoggedInEmployee()) {
            removeOption("show status");
            options.add("change activity");
            options.add("budget time");
            options.add("show budgeted time");

        }
        printPath();
    }
    private static void changeActivity(){
        removeOption("change activity");
        System.out.println("Write new name of activity " + currentActivity.getName());
        String name=scanner.nextLine();
        currentActivity.setTempName(name);
        System.out.println("Write new description of activity " + name);
        currentActivity.setTempDesc(scanner.nextLine());
        currentActivity.changeActivity();
    }
    private static void setProjectLeader(){
        System.out.println("Write name of the project leader:");
        Employee employee;
        try {
            employee = ProjectManagementSystem.getEmployeeWithName(scanner.nextLine());
        }catch (Exception e){
            System.out.println("No employee with this name exists");
            return;
        }
        System.out.println("Project leader set to " + employee.getName());
        ProjectManagementSystem.getLoggedInEmployee().setProjectLeader(currentProject, employee);
        removeOption("set project leader");
        if(currentProject.getProjectLeader() == ProjectManagementSystem.getLoggedInEmployee()){
            options.add("add employee");
            options.add("create activity");
        }
        options.add("enter activity");

    }
    private static void addEmployee(){
        System.out.println("Write name of employee:");
        Employee employee;
        try {
            employee = ProjectManagementSystem.getEmployeeWithName(scanner.nextLine());
            if(currentActivity==null){
                currentProject.addAssignee(employee);
            }
            else {
                currentActivity.addAssignee(employee);
            }
        }catch (Exception e){
            System.out.println("No employee with this name exists");
        }
    }

    private static void budgetTime(){
        double time;
        System.out.println("Enter new time");
        try {
            time = Double.parseDouble(scanner.nextLine());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Budgeted time set to " + time);
        currentActivity.budgetTimeForActivity(time);
    }
    private static void showBudgetedTime(){
        System.out.println(currentActivity.getBudgetedTime());
    }

    private static void showStatus(){
        System.out.println(currentProject.getName() + ": " + currentProject.getDescription());
        System.out.println("Activities:");
        double totalTime = 0;
        for (Activity a : currentProject.getActivities()){
            System.out.println("  " + a.getName() + ": " + a.getDesc() );
            System.out.println("    time: " + a.getBudgetedTime());
            System.out.println("    assignees: " + a.getAssignees().size());
            totalTime+= a.getBudgetedTime();

        }
        System.out.println("Total time for project: " + totalTime);
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
        options.add("return to menu");
        options.add("log out");
        options.add("close");
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
