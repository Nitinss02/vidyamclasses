
public class Employee {

    private int id;
    private String name;
    private String position;
    private String department;
    private double Salary;
    private String supervisor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public Employee(int id, String name, String position, String department,
            double salary, String supervisor) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        Salary = salary;
        this.supervisor = supervisor;

    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", position=" + position + ", department=" + department
                + ", Salary=" + Salary + ", supervisor=" + supervisor + "]";
    }

}