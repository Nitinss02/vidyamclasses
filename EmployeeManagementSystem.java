import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private String name;
    private int id;
    private String position;
    private String department;
    private double salary;
    private Employee supervisor;
    private List<Employee> subordinates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public void setPerformanceMetrics(Map<String, Double> performanceMetrics) {
        this.performanceMetrics = performanceMetrics;
    }

    private Map<String, Double> performanceMetrics;

    public Employee(String name, int id, String position, String department, double salary, Employee supervisor) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.supervisor = supervisor;
        this.subordinates = new ArrayList<>();
        this.performanceMetrics = new HashMap<>();
    }

    // Getters and setters for all attributes

    // Method to add a subordinate
    public void addSubordinate(Employee subordinate) {
        this.subordinates.add(subordinate);
    }

    // Method to update performance metrics
    public void updatePerformanceMetric(String metric, double value) {
        this.performanceMetrics.put(metric, value);
    }

    // Method to get performance metrics
    public Map<String, Double> getPerformanceMetrics() {
        return performanceMetrics;
    }
}

class Department {
    private String name;
    private int id;
    private Employee manager;
    List<Employee> employees;
    private double budget;
    private Map<String, Double> resources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Map<String, Double> getResources() {
        return resources;
    }

    public void setResources(Map<String, Double> resources) {
        this.resources = resources;
    }

    public Department(String name, int id, Employee manager) {
        this.name = name;
        this.id = id;
        this.manager = manager;
        this.employees = new ArrayList<>();
        this.budget = 0.0;
        this.resources = new HashMap<>();
    }

    // Getters and setters for all attributes

    // Method to add an employee
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    // Method to assign budget
    public void assignBudget(double budget) {
        this.budget = budget;
    }

    // Method to manage resources
    public void manageResource(String resource, double quantity) {
        this.resources.put(resource, quantity);
    }
}

class Hierarchy {
    Map<Integer, Employee> employees;

    public Hierarchy() {
        this.employees = new HashMap<>();
    }

    // Method to add an employee to the hierarchy
    public void addEmployee(Employee employee) {
        this.employees.put(employee.getId(), employee);
    }

    // Method to find the supervisor of an employee
    public Employee findSupervisor(int employeeId) {
        return this.employees.get(employeeId).getSupervisor();
    }

    // Method to find all subordinates of an employee
    public List<Employee> findSubordinates(int employeeId) {
        return this.employees.get(employeeId).getSubordinates();
    }

    // Method to update the hierarchy
    public void updateHierarchy() {
        // Implement logic to update the hierarchy
    }

    // Method to analyze the hierarchy structure
    public void analyzeStructure() {
        // Implement logic to analyze the hierarchy structure
    }
}

class PerformanceTracking {
    private Map<Integer, Map<String, Double>> performanceData;

    public PerformanceTracking() {
        this.performanceData = new HashMap<>();
    }

    // Method to track employee performance
    public void trackPerformance(int employeeId, String metric, double value) {
        if (!performanceData.containsKey(employeeId)) {
            performanceData.put(employeeId, new HashMap<>());
        }
        performanceData.get(employeeId).put(metric, value);
    }

    // Method to generate performance reports
    public Map<Integer, Map<String, Double>> generateReports() {
        return this.performanceData;
    }

    // Method to identify top performers
    public List<Employee> identifyTopPerformers(Hierarchy hierarchy) {
        List<Employee> topPerformers = new ArrayList<>();
        // Implement logic to identify top performers based on performance data
        return topPerformers;
    }
}

class Security {
    private Map<String, String> credentials;

    public Security() {
        this.credentials = new HashMap<>();
    }

    // Method to authenticate user
    public boolean authenticateUser(String username, String password) {
        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            return true;
        }
        return false;
    }

    // Method to enforce access control
    public boolean enforceAccessControl(String role, String functionality) {
        // Implement logic to enforce access control based on role and functionality
        return true;
    }

    // Method to encrypt data
    public String encryptData(String data) {
        // Implement logic to encrypt data
        return data;
    }

    // Method to change credentials
    public void changeCredentials(String username, String newPassword) {
        credentials.put(username, newPassword);
    }
}

public class EmployeeManagementSystem {
    private static Hierarchy hierarchy = new Hierarchy();
    private static List<Department> departments = new ArrayList<>();
    private static PerformanceTracking performanceTracking = new PerformanceTracking();
    private static Security security = new Security();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    employeeManagementMenu(scanner);
                    break;
                case 2:
                    departmentManagementMenu(scanner);
                    break;
                case 3:
                    performanceTrackingMenu(scanner);
                    break;
                case 4:
                    securitySettingsMenu(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // Main Menu
    private static void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Employee Management");
        System.out.println("2. Department Management");
        System.out.println("3. Performance Tracking");
        System.out.println("4. Security Settings");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Employee Management Menu
    private static void employeeManagementMenu(Scanner scanner) {
        System.out.println("Employee Management Menu:");
        System.out.println("1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Update Employee Information");
        System.out.println("4. Change Department");
        System.out.println("5. Promote Employee");
        System.out.println("6. Demote Employee");
        System.out.println("7. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addEmployee(scanner);
                break;
            case 2:
                removeEmployee(scanner);
                break;
            case 3:
                updateEmployeeInformation(scanner);
                break;
            case 4:
                changeDepartment(scanner);
                break;
            case 5:
                promoteEmployee(scanner);
                break;
            case 6:
                demoteEmployee(scanner);
                break;
            case 7:
                break; // Go back to main menu
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Department Management Menu
    private static void departmentManagementMenu(Scanner scanner) {
        System.out.println("Department Management Menu:");
        System.out.println("1. Create Department");
        System.out.println("2. Assign Employee to Department");
        System.out.println("3. Manage Department Budget");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createDepartment(scanner);
                break;
            case 2:
                assignEmployeeToDepartment(scanner);
                break;
            case 3:
                manageDepartmentBudget(scanner);
                break;
            case 4:
                break; // Go back to main menu
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Performance Tracking Menu
    private static void performanceTrackingMenu(Scanner scanner) {
        System.out.println("Performance Tracking Menu:");
        System.out.println("1. Track Employee Performance");
        System.out.println("2. Generate Performance Reports");
        System.out.println("3. Identify Top Performers");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                trackEmployeePerformance(scanner);
                break;
            case 2:
                generatePerformanceReports();
                break;
            case 3:
                identifyTopPerformers();
                break;
            case 4:
                break; // Go back to main menu
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Security Settings Menu
    private static void securitySettingsMenu(Scanner scanner) {
        System.out.println("Security Settings Menu:");
        System.out.println("1. Change Credentials");
        System.out.println("2. Log Out");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                changeCredentials(scanner);
                break;
            case 2:
                System.out.println("Logging out...");
                // Implement logout logic
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Employee Management Methods

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter employee position: ");
        String position = scanner.nextLine();

        System.out.print("Enter employee department: ");
        String department = scanner.nextLine();

        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter supervisor ID (or -1 if none): ");
        int supervisorId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee supervisor = null;
        if (supervisorId != -1) {
            supervisor = hierarchy.employees.get(supervisorId);
        }

        Employee newEmployee = new Employee(name, id, position, department, salary, supervisor);
        hierarchy.addEmployee(newEmployee);

        if (supervisor != null) {
            supervisor.addSubordinate(newEmployee);
        }

        System.out.println("Employee added successfully.");
    }

    private static void removeEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to remove: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employeeToRemove = hierarchy.employees.get(employeeId);
        if (employeeToRemove != null) {
            // Remove employee from hierarchy
            hierarchy.employees.remove(employeeId);

            // Remove employee from department
            for (Department department : departments) {
                department.employees.remove(employeeToRemove);
            }

            // Remove employee from subordinates of supervisor
            if (employeeToRemove.getSupervisor() != null) {
                employeeToRemove.getSupervisor().getSubordinates().remove(employeeToRemove);
            }

            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void updateEmployeeInformation(Scanner scanner) {
        System.out.print("Enter employee ID to update: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employeeToUpdate = hierarchy.employees.get(employeeId);
        if (employeeToUpdate != null) {
            System.out.println("What information would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Position");
            System.out.println("3. Department");
            System.out.println("4. Salary");
            System.out.println("5. Supervisor");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    employeeToUpdate.setName(newName);
                    break;
                case 2:
                    System.out.print("Enter new position: ");
                    String newPosition = scanner.nextLine();
                    employeeToUpdate.setPosition(newPosition);
                    break;
                case 3:
                    System.out.print("Enter new department: ");
                    String newDepartment = scanner.nextLine();
                    employeeToUpdate.setDepartment(newDepartment);
                    break;
                case 4:
                    System.out.print("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    employeeToUpdate.setSalary(newSalary);
                    break;
                case 5:
                    System.out.print("Enter new supervisor ID (or -1 if none): ");
                    int newSupervisorId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Employee newSupervisor = null;
                    if (newSupervisorId != -1) {
                        newSupervisor = hierarchy.employees.get(newSupervisorId);
                    }

                    employeeToUpdate.setSupervisor(newSupervisor);

                    if (newSupervisor != null) {
                        newSupervisor.addSubordinate(employeeToUpdate);
                    }

                    if (employeeToUpdate.getSupervisor() != null) {
                        employeeToUpdate.getSupervisor().getSubordinates().remove(employeeToUpdate);
                    }

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Employee information updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void changeDepartment(Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = hierarchy.employees.get(employeeId);
        if (employee != null) {
            System.out.println("Available departments:");
            for (int i = 0; i < departments.size(); i++) {
                System.out.println((i + 1) + ". " + departments.get(i).getName());
            }

            System.out.print("Enter the new department number: ");
            int departmentIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (departmentIndex >= 1 && departmentIndex <= departments.size()) {
                Department newDepartment = departments.get(departmentIndex - 1);

                // Remove employee from old department
                for (Department department : departments) {
                    if (department.getEmployees().contains(employee)) {
                        department.getEmployees().remove(employee);
                        break;
                    }
                }

                // Add employee to new department
                newDepartment.addEmployee(employee);
                employee.setDepartment(newDepartment.getName());
                System.out.println("Employee department changed successfully.");
            } else {
                System.out.println("Invalid department number.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void promoteEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to promote: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = hierarchy.employees.get(employeeId);
        if (employee != null) {
            System.out.print("Enter new position: ");
            String newPosition = scanner.nextLine();
            employee.setPosition(newPosition);
            System.out.println("Employee promoted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void demoteEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to demote: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = hierarchy.employees.get(employeeId);
        if (employee != null) {
            System.out.print("Enter new position: ");
            String newPosition = scanner.nextLine();
            employee.setPosition(newPosition);
            System.out.println("Employee demoted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Department Management Methods

    private static void createDepartment(Scanner scanner) {
        System.out.print("Enter department name: ");
        String name = scanner.nextLine();

        System.out.print("Enter department ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter department manager ID: ");
        int managerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee manager = hierarchy.employees.get(managerId);
        if (manager != null) {
            Department newDepartment = new Department(name, id, manager);
            departments.add(newDepartment);
            System.out.println("Department created successfully.");
        } else {
            System.out.println("Manager not found.");
        }
    }

    private static void assignEmployeeToDepartment(Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = hierarchy.employees.get(employeeId);
        if (employee != null) {
            System.out.println("Available departments:");
            for (int i = 0; i < departments.size(); i++) {
                System.out.println((i + 1) + ". " + departments.get(i).getName());
            }

            System.out.print("Enter the department number: ");
            int departmentIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (departmentIndex >= 1 && departmentIndex <= departments.size()) {
                Department department = departments.get(departmentIndex - 1);
                department.addEmployee(employee);
                employee.setDepartment(department.getName());
                System.out.println("Employee assigned to department successfully.");
            } else {
                System.out.println("Invalid department number.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void manageDepartmentBudget(Scanner scanner) {
        System.out.println("Available departments:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getName());
        }

        System.out.print("Enter the department number: ");
        int departmentIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (departmentIndex >= 1 && departmentIndex <= departments.size()) {
            Department department = departments.get(departmentIndex - 1);
            System.out.println("1. Assign Budget");
            System.out.println("2. Manage Resources");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter department budget: ");
                    double budget = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    department.assignBudget(budget);
                    System.out.println("Department budget assigned successfully.");
                    break;
                case 2:
                    System.out.print("Enter resource name: ");
                    String resourceName = scanner.nextLine();
                    System.out.print("Enter resource quantity: ");
                    double resourceQuantity = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    department.manageResource(resourceName, resourceQuantity);
                    System.out.println("Resource managed successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.println("Invalid department number.");
        }
    }

    // Performance Tracking Methods

    private static void trackEmployeePerformance(Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = hierarchy.employees.get(employeeId);
        if (employee != null) {
            System.out.print("Enter performance metric: ");
            String metric = scanner.nextLine();

            System.out.print("Enter performance value: ");
            double value = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            performanceTracking.trackPerformance(employeeId, metric, value);
            employee.updatePerformanceMetric(metric, value);
            System.out.println("Employee performance tracked successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void generatePerformanceReports() {
        Map<Integer, Map<String, Double>> reports = performanceTracking.generateReports();
        if (!reports.isEmpty()) {
            System.out.println("Performance Reports:");
            for (Map.Entry<Integer, Map<String, Double>> entry : reports.entrySet()) {
                System.out.println("Employee ID: " + entry.getKey());
                System.out.println("Metrics: " + entry.getValue());
            }
        } else {
            System.out.println("No performance data available.");
        }
    }

    private static void identifyTopPerformers() {
        List<Employee> topPerformers = performanceTracking.identifyTopPerformers(hierarchy);
        if (!topPerformers.isEmpty()) {
            System.out.println("Top Performers:");
            for (Employee employee : topPerformers) {
                System.out.println("Employee: " + employee.getName());
                System.out.println("Performance: " + employee.getPerformanceMetrics());
            }
        } else {
            System.out.println("No top performers found.");
        }
    }

    // Security Settings Methods

    private static void changeCredentials(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        security.changeCredentials(username, newPassword);
        System.out.println("Credentials changed successfully.");
    }
}
