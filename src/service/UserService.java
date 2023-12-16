package service;

import model.User;
import utility.AccountUtility;
import utility.DatabaseUtility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public User createUser(User user) {
        String query = "insert into user_details values(?,?, ?, ?,?) ";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setString(1, user.getId());
            databaseUtility.getStatement().setString(2, user.getEmail());
            databaseUtility.getStatement().setString(3, user.getUsername());
            databaseUtility.getStatement().setString(4, user.getPassword());
            databaseUtility.getStatement().setBoolean(5, user.isAdmin());
            databaseUtility.getStatement().execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User login(String username, String password) {
        String query = "SELECT * FROM user_details where username = ? AND password = ?";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setString(1, username);
            databaseUtility.getStatement().setString(2, password);
            ResultSet result = databaseUtility.getStatement().executeQuery();
            while (result.next()) {
                String email = result.getString("email");
                String dbUsername = result.getString("username");
                String dbPassword = result.getString("password");
                boolean isAdmin = result.getBoolean("is_admin");
                User user = new User(email, dbUsername, dbPassword,isAdmin);
                AccountUtility.loggedInUser = user;
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
