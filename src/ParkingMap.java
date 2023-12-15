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
}
