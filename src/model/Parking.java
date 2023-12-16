package model;

import enums.ParkingType;
import utility.AppUtility;

public class Parking extends BaseModel{
    private ParkingType parkingType;
    private int totalNoOfRows;
    private int totalNoOfCols;
    private int totalSpaceAvailable;
    private double ratePerHour;

    private double additionalFee;

    private boolean isElectric = false;
    public Parking(){}
    public Parking(ParkingType parkingType, int totalNoOfRows, int totalNoOfCols, int totalSpaceAvailable, double ratePerHour, double additionalFee, boolean isElectric) {
        this.parkingType = parkingType;
        this.totalNoOfRows = totalNoOfRows;
        this.totalNoOfCols = totalNoOfCols;
        this.totalSpaceAvailable = totalSpaceAvailable;
        this.ratePerHour = ratePerHour;
        this.additionalFee = additionalFee;
        this.isElectric = isElectric;
    }

    public ParkingType getParkingType() {
        return parkingType;
    }

    public int getTotalNoOfRows() {
        return totalNoOfRows;
    }

    public int getTotalNoOfCols() {
        return totalNoOfCols;
    }

    public int getTotalSpaceAvailable() {
        return totalSpaceAvailable;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public double getAdditionalFee() {
        return additionalFee;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    public void setTotalNoOfRows(int totalNoOfRows) {
        this.totalNoOfRows = totalNoOfRows;
    }

    public void setTotalNoOfCols(int totalNoOfCols) {
        this.totalNoOfCols = totalNoOfCols;
    }

    public void setTotalSpaceAvailable(int totalSpaceAvailable) {
        this.totalSpaceAvailable = totalSpaceAvailable;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public void setAdditionalFee(double additionalFee) {
        this.additionalFee = additionalFee;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return AppUtility.formateString(
                null==parkingType?"":parkingType.toString(),
                Double.toString(ratePerHour),
                Double.toString(additionalFee),
                Boolean.toString(isElectric)
        );
    }

}
