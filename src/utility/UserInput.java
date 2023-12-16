package utility;

import model.User;

public class UserInput extends InputUtility {
    public static User getUser() {
        String email = getString("Email:");
        String username = getString("Username:");
        String password = getString("Password:");
        /*String checkAdmin = getString("Is Admin(Y/N)?");
        boolean isAdmin = checkAdmin.equalsIgnoreCase("y");*/
        return new User(email, username, password, false);
    }
}
