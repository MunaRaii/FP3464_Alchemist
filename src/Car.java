// Car.java
public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public void displayInfo() {
        System.out.println("Car - License Plate: " + getLicensePlate());
    }
}
