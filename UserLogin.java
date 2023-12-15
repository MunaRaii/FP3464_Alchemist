import java.sql.*;
import java.util.Scanner;

public class UserLogin {
    public static UserLogin loggedInUser;
    private final String username;
    private final String password;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    static void checkLoginUsers()
    {

        // instance of ParkingMap class
        ParkingMap map = new ParkingMap(3, 4);
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scan.next();

        System.out.print("Enter your password: ");
        String password = scan.next();

        UserLogin userLog = new UserLogin(username, password);

        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication","root",
                "Thisis2023)")
        ) {
            String query = "SELECT * FROM user_details where username = ? AND password = ?";

            PreparedStatement myStmt = con.prepareStatement(query);

            myStmt.setString(1, userLog.getUsername());
            myStmt.setString(2, userLog.getPassword());

            ResultSet result = myStmt.executeQuery();
            if(result.next()) {
                map.welcomeUsers();
            }
            else {
                System.out.println("Username or password didn't match or not registered. Please try again: ");
                checkLoginUsers();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args)
    {
        checkLoginUsers();
    }
}
