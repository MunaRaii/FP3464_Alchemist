package utility;

import model.Parking;
import service.ParkingService;

import java.util.List;

public class ParkingUtility extends InputUtility {

    private final static ParkingService parkingService = new ParkingService();

    public static void printHeader() {
        System.out.println(
                AppUtility.formateString(
                        "SN",
                        "Parking Type",
                        "Rate Per Hour",
                        "Additional Fee",
                        "Is Electric"
                )
        );

    }

    public static void displayParking(List<Parking> parkings) {
        for (int i = 0; i < parkings.size(); i++) {
            Parking parking = parkings.get(i);
            System.out.println(AppUtility.formateString(Integer.toString(i + 1), parking.toString()));

        }
    }

    public static void displayParking() {
        displayParking(parkingService.getParkings());
    }

    public static Parking selectParking(List<Parking> parkings) {
        while (true) {
            int choice = getInt("Select from SN:");
            if (choice < 1 || choice > parkings.size()) {
                System.out.println("Invalid selection");
            } else {
                return parkings.get(choice - 1);
            }
        }
    }

    public static Parking selectParking() {
        List<Parking> parkings = parkingService.getParkings();
        printHeader();
        displayParking(parkings);
        return selectParking(parkings);
    }

    public static void updateParkingFee() {
        Parking parking = selectParking();
        double perHourRate = getDouble(String.format("Per hour rate(%.2f):", parking.getRatePerHour()));
        double additional = 0;
        if (!parking.isElectric()) {
            additional = getDouble(String.format("Additional Fee(%.2f):", parking.getAdditionalFee()));
            if (additional > 0) {
                parking.setAdditionalFee(additional);
            }
        }
        if (perHourRate > 0) {
            parking.setRatePerHour(perHourRate);
        }
        Parking updatedParking = parkingService.updateParkingInfo(parking);
        if (updatedParking != null) {
            System.out.println("Success fully updated!!!");
        } else {
            System.out.println("Unable to update!!!");
        }

    }

}
