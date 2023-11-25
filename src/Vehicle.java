// abstract class representing a generic Vehicle.
public abstract class Vehicle {
    // Private attribute to store the license plate of the vehicle.
    private String licensePlate;

   //Constructor for the Vehicle class.
    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    //Getter method to retrieve the license plate of the vehicle.
    public String getLicensePlate() {
        return licensePlate;
    }

    //display information used by subclass to display the licence plate number
    public abstract void displayInfo();

}
