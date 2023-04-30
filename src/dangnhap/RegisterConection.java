package dangnhap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class RegisterConection {
    static final String DB_URL = "jdbc:mysql://localhost:3306/dangnhap";
    static final String USER = "root";
    static final String PASS = "trantrantrantran";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Connecting to a selected database...");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
            System.out.println("Connected database successfully...");
            Statement stmt = conn.createStatement();
            // đưa ra lựa chọn
            int choose = 0;
            System.out.println("[1]. Log in");
            System.out.println("[2]. Sign up");
            choose = sc.nextInt();
            // đăng nhập
            if (choose == 1) {
                boolean exists = false;
                String name = "";
                while (!exists) {
                    System.out.println("Log in name:");
                    name = sc.next();
                    String query = "SELECT user_name FROM username WHERE user_name = '" + name + "'";
                    ResultSet rs = stmt.executeQuery(query);
                    exists = rs.next();
                    if (!exists) {
                        System.out.println("Username does not exist. Please re-enter");
                    }
                }
                boolean ok = false;
                while (!ok) {
                    System.out.println("Password:");
                    String pass = sc.next();
                    String query = "SELECT user_name FROM username WHERE user_name = '" + name + "' AND user_password = '" + pass + "'";
                    ResultSet rs = stmt.executeQuery(query);
                    ok = rs.next();
                    if (!ok) {
                        System.out.println("Incorrect password. Please re-enter");
                    }
                }
                System.out.println("Logged in successfully...");
            }
            
            // đăng ký xong.
            if (choose == 2) {
                boolean exists = true;
                String name = "";
                while (exists) {
                    System.out.println("Log in name:");
                    name = sc.next();
                    String ten = "Select user_name from username where user_name = " + "'" + name + "'";
                    ResultSet rs = stmt.executeQuery(ten);
                    exists = rs.next();
                    if (exists) {
                        System.out.println("Username already exists, please re-enter");
                    }
                }
                boolean ok = false;
                while (!ok) {
                    System.out.println("Password:");
                    String pass = sc.next();
                    if (!pass.matches("^.{8,}$")) {
                        System.out.println("Password must contain at least 8 characters. Please re-enter");
                    } else if (!pass.matches(".*[A-Z].*")) {
                        System.out.println("The password is missing a capital character. Please re-enter");
                    } else if (!pass.matches(".*[0-9].*")) {
                        System.out.println("Password is missing numeric characters. Please re-enter");
                    } else if (!pass.matches(".*[^a-zA-Z0-9].*")) {
                        System.out.println("Password is missing special characters. Please re-enter");
                    } else {
                        ok = true;
                        String query = "INSERT INTO username(user_name, user_password) VALUES ('" + name + "','" + pass
                                + "')";
                        stmt.executeUpdate(query);
                        System.out.println("Regiser successful...");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connected database failed...");
        }

    }
}
