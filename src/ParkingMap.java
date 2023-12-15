import java.util.Scanner;

public class ParkingMap {
    public static final int EMPTY = 0;
    public static final int OCCUPIED = 1;
    private final int[][] parkingSpace;

    public ParkingMap(int rows, int cols){
        this.parkingSpace = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                parkingSpace[i][j] = EMPTY; // default space empty
            }
        }
    }

    public void welcomeUsers()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to our parking app, please choose the vehicle type you want to park: " +
                "\n1)Car\n2)Bike");
        int option = scan.nextInt();
        if (option == 1) {

        } else if (option == 2) {

        } else {
            System.out.println("Please choose valid option.");
        }
    }

    //method to display the parking map
    public void displayParkingMap(){
        for (int[] emptySlot : parkingSpace) {
            for (int slots : emptySlot) {
                System.out.println(
                        slots == EMPTY ? "Empty" : "Occupied"
                );
            }
        }
    }

    // method to check if a parking space is available
    public boolean availableSpace(int row, int col){
        return parkingSpace[row][col] == EMPTY;
    }

    // method to book parking space
    public void bookSpace(int row, int col){
        if(availableSpace(row, col)){
            parkingSpace[row][col] = OCCUPIED;
            System.out.println("Parking space booked.");
        } else {
            System.out.println("Selected parking space is already occupied.");
        }
    }

    // method to free up a parking space
    public void freeSpace(int row, int col){
        if(parkingSpace[row][col] == OCCUPIED){
            parkingSpace[row][col] = EMPTY;
            System.out.println("Thank you for parking at our place. See you again!");
        } else {
            System.out.println("Selected parking space is already empty.");
        }
    }


}
