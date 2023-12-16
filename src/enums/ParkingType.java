package enums;

public enum ParkingType {
    CAR("Car"),
    BIKE("Bike");
    private String value;

    ParkingType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
