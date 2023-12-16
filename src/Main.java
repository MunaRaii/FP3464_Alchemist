import model.User;
import service.UserService;
import utility.*;

import java.util.List;

public class Main {
    private static UserService userService = new UserService();

    static void displayAdminMenu() {
        List<Integer> options = List.of(1, 2);
        while (true) {
            System.out.println("1. Update Parking");
            System.out.println("2. List Parking");
            System.out.println("3. Log out");
            int choice = InputUtility.getInt("Select Option:");
            if (options.contains(choice)) {
                if (choice == 1) {
                    ParkingUtility.updateParkingFee();
                } else if (choice == 2) {
                    ParkingUtility.displayParking();
                } else if (choice == 3) {
                    AccountUtility.loggedInUser = null;
                    return;
                }
            }
        }
    }

    static int displayMenu() {
        while (true) {
            List<Integer> options = List.of(0, 1, 2, 3);
            System.out.println("1. Register");
            System.out.println("2. Log in");
            System.out.println("3. Check Out");
            System.out.println("0. Exit");
            int choice = InputUtility.getInt("Select your choice: ");
            if (options.contains(choice)) return choice;
            System.out.println("Invalid option selection ");
        }

    }

    public static void main(String[] args) {
        while (true) {
            try {
                int choice = displayMenu();
                if (choice == 1) {
                    User user = UserInput.getUser();
                    User dbSavedUser = userService.createUser(user);
                    if (null != dbSavedUser) {
                        System.out.println("User created successfully.");
                    } else {
                        System.out.println("Could not create the user.");
                    }
                } else if (choice == 2) {
                    String username = InputUtility.getString("Username:");
                    String password = InputUtility.getString("Password:");
                    User user = userService.login(username, password);
                    if (null != user) {
                        System.out.println("User logged in successfully!");
                        if (user.isAdmin()) {
                            displayAdminMenu();
                            return;
                        } else {
                            CheckOutUtility.checkoutMenu();
                        }
                    } else System.out.println("User could not be logged in. Please try again...");
                } else if (choice == 3) {
                    CheckOutUtility.checkout();
                } else if (choice == 0) {
                    System.out.println("Thank you for using our app.");
                    break;
                }
            } catch (Exception ex) {
                System.out.println("An error occurred.");
            }
        }
    }
}