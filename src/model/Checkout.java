package model;

import utility.AppUtility;

import java.sql.Timestamp;

public class Checkout extends BaseModel {
    private String parkingId;
    private String username;
    private int row;
    private int col;
    private Timestamp checkedIn;
    private Timestamp checkedOut;
    private boolean isActive = true;
    private String uniqueKey;
    private double perRate;
    private double additionalFee;
    private boolean isElectric;

    public Checkout() {
        uniqueKey = AppUtility.randomNumberStringGenerator();
    }

    public Checkout(
            String parkingId,
            String username,
            int row,
            int col,
            Timestamp checkedIn,
            Timestamp checkedOut,
            boolean isActive,
            double perRate,
            double additionalFee,
            boolean isElectric
    ) {
        this();
        this.parkingId = parkingId;
        this.username = username;
        this.row = row;
        this.col = col;
        this.checkedIn = checkedIn;
        this.checkedOut = checkedOut;
        this.isActive = isActive;
        this.perRate = perRate;
        this.additionalFee = additionalFee;
        this.isElectric = isElectric;
    }

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Timestamp getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Timestamp checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Timestamp getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(Timestamp checkedOut) {
        this.checkedOut = checkedOut;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public double getPerRate() {
        return perRate;
    }

    public double getAdditionalFee() {
        return additionalFee;
    }

    public void setPerRate(double perRate) {
        this.perRate = perRate;
    }

    public void setAdditionalFee(double additionalFee) {
        this.additionalFee = additionalFee;
    }

    private double totalHours() {
        return (getCheckedOut().getTime() - getCheckedIn().getTime()) / ((double) 1000 * 60 * 60);
    }

    private double additionalFee() {
        return isElectric ? additionalFee : 0;
    }

    private double totalFee() {
        return perRate * totalHours() + additionalFee();
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return String.format(
                """
                            Username:       %s
                            Checked In:     %s
                            Checked Out:    %s
                            Total Hours:    %.2f
                            Per Hour Rate:  %.2f
                            Addi. Fee:      %.2f
                            Total Fee:      %.2f               
                        """,
                username,
                getCheckedIn().toString(),
                getCheckedOut().toString(),
                totalHours(),
                perRate,
                additionalFee(),
                totalFee()
        );
    }
}
