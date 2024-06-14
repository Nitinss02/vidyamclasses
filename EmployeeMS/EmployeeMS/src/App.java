import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int n = 0;
        EmployeeImpl eImpl = new EmployeeImpl();
        Scanner sobj = new Scanner(System.in);

        do {
            System.out.println("Main Menu : \n");
            System.out.println(
                    "1. Employee Management \n2. Deparment Management \n3. performnance Traking\n4. Security Setting\n5. Exit");
            int choice = sobj.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Employee management Menu :\n");
                    System.out.println(
                            "1.Add Employee\n2.Remove Employee \n3.Update Employee Information\n4.Change Deparment\n5.promote Employee\n6.Demote Employee\n7.Disply Employee\n8.Back to Main Menu");
                    System.out.println("Enter Your Choice");
                    int choice1 = sobj.nextInt();

                    switch (choice1) {
                        case 1:
                            System.out.println("Add Employee");
                            Employee emp = new Employee();
                            System.out.println("Enter Id : ");
                            int id = sobj.nextInt();

                            System.out.println("Enter the name : ");
                            String name = sobj.next();

                            System.out.println("Enter Your Position : ");
                            String position = sobj.next();

                            System.out.println("Enter Your Department : ");
                            String department = sobj.next();

                            System.out.println("Enter Your Salary : ");
                            Double Salary = sobj.nextDouble();

                            System.out.println("Enter your Supervisour name : ");
                            String supervisor = sobj.next();

                            emp.setId(id);
                            emp.setName(name);
                            emp.setPosition(position);
                            emp.setDepartment(department);
                            emp.setSalary(Salary);
                            emp.setSupervisor(supervisor);

                            eImpl.Add_employee(emp);
                            break;
                        case 2:
                            System.out.println("Remove Employee\n");
                            System.out.println("Enter the id for delete");
                            int rId = sobj.nextInt();
                            eImpl.remove_employee(rId);
                            break;
                        case 3:
                            System.out.println("Update Employee");
                            System.out.println("Enter the id for update");
                            int Uid = sobj.nextInt();
                            System.out.println("Enter the name for update");
                            String Newname = sobj.next();
                            eImpl.update_employee(Uid, Newname);
                            break;
                        case 4:
                            System.out.println("Change Deparment");
                            System.out.println("Enter the id to change the Deparment");
                            int Cid = sobj.nextInt();
                            System.out.println("Enter the new Department");
                            String nDepart = sobj.next();
                            eImpl.change_department(Cid, nDepart);
                            break;
                        case 5:
                            System.out.println("Promot Employee");
                            System.out.println("Enter the id to change the position");
                            int pid = sobj.nextInt();
                            System.out.println("Enter the new position");
                            String npos = sobj.next();
                            eImpl.promote(pid, npos);
                            break;
                        case 6:
                            System.out.println("Demote Employee");
                            System.out.println("Promot Employee");
                            System.out.println("Enter the id to change the position");
                            int did = sobj.nextInt();
                            System.out.println("Enter the new position");
                            String dpos = sobj.next();
                            eImpl.demote(did, dpos);
                            break;
                        case 7:
                            System.out.println("Employee Information");
                            eImpl.DisplayInfo();
                            break;
                        case 8:
                            System.out.println("Back to Main Menu");
                            break;

                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Deparment managemet Menu : \n");
                    System.out.println(
                            "1.Create Deparment\n2.Assign employee to Department \n3.manage Deparment Budget\n4.Back to Main Menu");
                    System.out.println("Enter Your Choice");
                    int choice2 = sobj.nextInt();
                    switch (choice2) {
                        case 1:
                            System.out.println("Create Deparment\n");
                            break;
                        case 2:
                            System.out.println("Assign Employee to Department");
                            break;
                        case 3:
                            System.out.println("Manage Deparment");
                            break;
                        case 4:
                            System.out.println("Back to Main Menu");
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Performace Traking Menu");
                    System.out.println(
                            "1.Track Employee Performance \n2.Generate Employee Report \n3.Identify Top Perfromers\n4.Back to Main Menu");
                    System.out.println("Enter Your Choice");
                    int choice3 = sobj.nextInt();
                    switch (choice3) {
                        case 1:
                            System.out.println(" Employee Performance");

                            break;
                        case 2:
                            System.out.println("Employee Report");
                            break;
                        case 3:
                            System.out.println("Top Performer");
                            break;
                        case 4:
                            System.out.println("Back to Main Menu");
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Security Setting Menu : \n");
                    System.out.println("1.Change Credentials \n2.LogOut");
                    System.out.println("Enter Your Choice");
                    int Choice4 = sobj.nextInt();
                    switch (Choice4) {
                        case 1:
                            System.out.println("Change Credential");
                            break;
                        case 2:
                            System.out.println("Log Out");

                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Thank You....❤️");
                    return;
                default:
                    System.out.println("Invalid Input");
            }
        } while (n == 0);
    }
}
