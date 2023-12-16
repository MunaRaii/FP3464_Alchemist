package utility;

import model.Checkout;
import model.Parking;
import service.CheckoutService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckOutUtility extends InputUtility {
    private final static CheckoutService checkoutService = new CheckoutService();

    public static List<Integer> getRowAndCols(Parking parking) {
        List<String> availables = new ArrayList<>();
        for (int row = 0; row < parking.getTotalNoOfRows(); row++) {
            for (int col = 0; col < parking.getTotalNoOfCols(); col++) {
                if (isAvailable(row, col, parking.getId())) {
                    System.out.printf("%d%d\t", row, col);
                    availables.add(String.format("%d%d", row, col));
                } else {
                    System.out.print("O\t");
                }
            }
            System.out.println();
        }

        if (availables.isEmpty()) {
            System.out.println("No space available in this parking!!!");
            return new ArrayList<>();
        }

        while (true) {
            String choice = getString("Enter parking space:");
            if (availables.contains(choice)) {
                return Arrays.stream(choice.split("")).map(Integer::parseInt).toList();
            } else {
                System.out.println("Invalid parking space selection!!!");
            }
        }
    }

    public static Checkout getCheckoutInput() {
        Parking parking = ParkingUtility.selectParking();
        List<Integer> rowsNCols = getRowAndCols(parking);
        if (rowsNCols.size() < 2) {
            System.out.println("No parking available!!!!");
            return null;
        }
        String username = AccountUtility.loggedInUser.getUsername();
        return new Checkout(
                parking.getId(),
                username,
                rowsNCols.get(0),
                rowsNCols.get(1),
                new Timestamp(System.currentTimeMillis()),
                null,
                true,
                parking.getRatePerHour(),
                parking.getAdditionalFee(),
                parking.isElectric()
        );
    }

    public static void checkout() {
        String uniqueKey = getString("Provide your unique key:");
        if (uniqueKey.length() != 4) {
            System.out.println("Invalid unique key!!!");
        } else {
            boolean status = checkoutService.checkOut(uniqueKey);
            if (status) {
                System.out.println("You are successfully checked out!!!");
                System.out.println("Thank you !!!");
            } else {
                System.out.println("We could not found your key. Either you have already checked out or key is invalid!!!");
            }
        }
    }

    public static void checkoutMenu() {
        List<Integer> choices = List.of(1, 2);
        while (true) {
            System.out.println("1.Check In");
            System.out.println("2.Back");
            int choice = getInt("Select Option:");
            if (choices.contains(choice)) {
                if (choice == 1) {
                    Checkout checkout = getCheckoutInput();
                    if (checkout == null) {
                        System.out.println("Could not check in");
                        continue;
                    }
                    Checkout stored = checkoutService.checkIn(checkout);
                    if (null != stored) {
                        System.out.printf("\nyou successfully checked in. Your unique id is %s\n", stored.getUniqueKey());
                        return;
                    }
                } else if (choice == 2) {
                    return;
                }

            }
        }
    }


    public static boolean isAvailable(int row, int col, String parkingId) {
        return checkoutService.isAvailable(row, col, parkingId);
    }
}
