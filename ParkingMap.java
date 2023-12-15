public class ParkingMap {

    // Initializing ParkingMap with constants for EMPTY and OCCUPIED spaces
    public static final int EMPTY = 0;
    public static final int OCCUPIED = 1;
    private final int[][] parkingSpace;

    // Initializes a ParkingMap with default empty space
    public ParkingMap(int rows, int cols){
        this.parkingSpace = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                parkingSpace[i][j] = EMPTY; // default space empty
            }
        }
    }
}
