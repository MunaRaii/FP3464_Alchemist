// Bike.java
public class Bike extends Vehicle {
    public Bike(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public void displayInfo() {
        System.out.println("Bike - License Plate: " + getLicensePlate());
    }
}
