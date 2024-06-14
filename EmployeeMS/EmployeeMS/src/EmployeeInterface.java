
public interface EmployeeInterface {
    public void Add_employee(Employee emp);

    public void remove_employee(int id);

    public void update_employee(int id, String name);

    public void change_department(int id, String department);

    public void DisplayInfo();

    public void promote(int id, String position);

    public void demote(int id, String position);
}
