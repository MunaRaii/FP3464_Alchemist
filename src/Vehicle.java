public enum Vehicle {
    CAR(10), BIKE(7.5);

    private final double price;

    Vehicle(double price){
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
