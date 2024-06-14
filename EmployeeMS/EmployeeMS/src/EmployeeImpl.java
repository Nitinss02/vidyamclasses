import java.sql.*;

public class EmployeeImpl implements EmployeeInterface {
    Connection con;

    @Override
    public void Add_employee(Employee emp) {
        con = DBConnection.createDBConnection();
        if (con == null) {
            System.out.println("Connection is not established");
        } else {
            String query = "insert into employee values(?,?,?,?,?,?)";
            try {
                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setInt(1, emp.getId());
                pstm.setString(2, emp.getName());
                pstm.setString(3, emp.getPosition());
                pstm.setString(4, emp.getDepartment());
                pstm.setDouble(5, emp.getSalary());
                pstm.setString(6, emp.getSupervisor());
                int cnt = pstm.executeUpdate();
                if (cnt != 0)
                    System.out.println("Employee Inserted Successfully !!!");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void remove_employee(int id) {
        con = DBConnection.createDBConnection();
        if (con == null) {
            System.out.println("Connection is not established");
        } else {
            String query = "delete into employee where id = ?";
            try {
                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setInt(0, id);
                int cnt = pstm.executeUpdate();

                if (cnt != 0)
                    System.out.println("Employee delete Successfully !!!");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update_employee(int id, String name) {
        con = DBConnection.createDBConnection();
        String query = "update employee set name=? where id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            pstm.setString(2, name);

            int count = pstm.executeUpdate();
            if (count != 0) {
                System.out.println("Employee Update Sucessfully !!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void change_department(int id, String Department) {
        con = DBConnection.createDBConnection();
        String query = "update employee set department=? where id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            pstm.setString(2, Department);

            int count = pstm.executeUpdate();
            if (count != 0) {
                System.out.println("Employee Update Sucessfully !!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DisplayInfo() {
        con = DBConnection.createDBConnection();
        String query = "select * from employee";
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                System.out.format("%d%s%s%s%d%s",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        result.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void promote(int id, String position) {
        con = DBConnection.createDBConnection();
        String query = "update employee set position=? where id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            pstm.setString(2, position);

            int count = pstm.executeUpdate();
            if (count != 0) {
                System.out.println("Employee Promote Sucessfully !!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demote(int id, String position) {
        con = DBConnection.createDBConnection();
        String query = "update employee set position=? where id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            pstm.setString(2, position);

            int count = pstm.executeUpdate();
            if (count != 0) {
                System.out.println("Employee Demote Sucessfully !!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
