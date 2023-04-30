import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysqljdbc";
    static final String USER = "root";
    static final String PASS = "trantrantrantran";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Connecting to a selected database...");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
            Statement stmt = conn.createStatement();
            System.out.println("Connected database successfully...");
        
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connected database failed...");
        }

    }

}
