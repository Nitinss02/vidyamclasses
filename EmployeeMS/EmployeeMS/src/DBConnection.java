import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static Connection con;

    public static Connection createDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeedb?useSSL=false", "root", "Nitin@8085");
            con.createStatement();
        } catch (Exception e) {
            System.out.println("Error is occured when upload the data : ");
            e.printStackTrace();
        }
        return con;
    }
}
