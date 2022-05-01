package projectManagementSystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class ProjectManagementSystem {

    private static ArrayList<Project> projects = new ArrayList<Project>();
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private static int projectCounter = 0;
    private static Calendar cal = Calendar.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> options = new ArrayList<>();
    private static Employee loggedInEmployee;
    private static Project currentProject;
    private static Activity currentActivity;
    private static ArrayList<String> path = new ArrayList<>();

    public static void main(String[] args) {
        setup();
        run();
    }
    private static void setup(){
        options.add("help");
        options.add("close");
        options.add("log in");
        help();

        employees.add(new Employee("Allan", 40, "male" ));
        employees.add(new Employee("Bodil", 42, "female" ));
        employees.add(new Employee("Carl", 34, "male" ));
        employees.add(new Employee("Dagmar", 44, "female" ));
        employees.add(new Employee("Egon", 41, "male" ));
        employees.add(new Employee("Freja", 35, "female" ));
        employees.add(new Employee("Gorm", 53, "male" ));
        employees.add(new Employee("Helga", 33, "female" ));
        employees.add(new Employee("Ingolf", 63, "male" ));
        employees.add(new Employee("Johanne", 67, "female" ));
        employees.add(new Employee("Knud", 50, "male" ));
        employees.add(new Employee("Laura", 53, "female" ));
        employees.add(new Employee("Malik", 46, "male" ));

        projects.add(new Project("General","Project with general tasks"));
        projects.add(new Project("StormWeb","Create website with data of storms"));

        getProjectWithName("General").addAssignee(getEmployeeWithName("Allan"));
        getProjectWithName("General").addAssignee(getEmployeeWithName("Bodil"));
        getProjectWithName("General").addAssignee(getEmployeeWithName("Carl"));

        getProjectWithName("General").setProjectLeader(getEmployeeWithName("Allan"));
        getProjectWithName("StormWeb").setProjectLeader(getEmployeeWithName("Bodil"));

        getProjectWithName("General").addActivity(new Activity("MainActivity", "The main activity", getProjectWithName("General") ));
    }

    public static void run(){
        while (scanner.hasNext()) {
            String in = scanner.nextLine();
            for (String s:options) {
                if (in.equals(s)){
                    runCommand(s);
                    break;
                }
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


        }
    }
    private static void logIn(){
        System.out.println("Write your name:");
        loggedInEmployee = getEmployeeWithName(scanner.next());
        removeOption("log in");
        // her skal indsættes options med de ting man kan gøre og der skal laves funktioner til det
        returnToMenu();

    }

    private static void removeOption(String s){
        for (int i = options.size()-1; i>=0; i--){
            if (options.get(i).equals(s)){
                options.remove(i);
            }
        }
    }

    private static void enterProject(){
        removeOption("enter project");
        removeOption("enter helper activity");
        System.out.println("Choose project:");
        int i=1;
        for (Project project: loggedInEmployee.getProjects()) {
            System.out.println(i + ": " + project.getName());
            options.add(project.getName());
        }
        currentProject = getProjectWithName(scanner.nextLine());
        path.add(currentProject.getName());
        for (Project project: loggedInEmployee.getProjects()) {
            removeOption(project.getName());
        }
        options.add("return to menu");
        printPath();
    }
    private static void enterHelperActivity(){
        removeOption("enter project");
        removeOption("enter helper activity");
    }
    private static void returnToMenu(){
        currentProject = null;
        currentActivity = null;
        options.clear();
        options.add("help");
        options.add("close");
        options.add("enter project");
        options.add("enter helper activity");
        path.clear();
        path.add(loggedInEmployee.getName());
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

    static public ArrayList<Project> getProjects(){
        return projects;
    }
    static public ArrayList<Employee> getEmployees() {return employees;}

    public static Project getProjectWithName(String projectName){
        Project returnProject = null;
        for (Project project: projects) {
            if (project.getName().equals(projectName)) {
                returnProject = project;
            }
        }
        return returnProject;
    }

    public static Employee getEmployeeWithName(String employeeName){
        Employee returnEmployee = null;
        for (Employee employee: employees) {
            if (employee.getName().equals(employeeName)){
                returnEmployee = employee;
            }
        }
        return returnEmployee;
    }

    // adds the project to the list.
    static public void addProjectToList(Project project) { projects.add(project); }
    static public void addEmployeeToList(Employee employee) {employees.add(employee);}

    public static void clearLists(){
        projects.clear();
        employees.clear();
        projectCounter = 0;
    }

}
