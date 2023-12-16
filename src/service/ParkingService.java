package service;

import enums.ParkingType;
import model.Parking;
import utility.DatabaseUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingService {
    /*public Parking create(Parking parking) {
        String query = "insert into parking(parking_type,total_rows,total_cols,total_space_available,rate_per_hour,additional_fee,is_electric) " +
                "values(?,?,?,?,?,?,?,?);";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setString(1, parking.getId());
            databaseUtility.getStatement().setString(1, parking.getParkingType().toString());
            databaseUtility.getStatement().setInt(2, parking.getTotalNoOfRows());
            databaseUtility.getStatement().setInt(3, parking.getTotalNoOfCols());
            databaseUtility.getStatement().setInt(4, parking.getTotalSpaceAvailable());
            databaseUtility.getStatement().setDouble(5, parking.getRatePerHour());
            databaseUtility.getStatement().setDouble(6, parking.getAdditionalFee());
            databaseUtility.getStatement().setBoolean(7, parking.isElectric());
            databaseUtility.getStatement().execute();
            return parking;
        } catch (Exception e) {
            System.out.println("Unable to create  new parking type!!!");
        }
        return null;
    }*/

    public List<Parking> getParkings() {
        String query = "SELECT * FROM PARKING";
        List<Parking> parkings = new ArrayList<>();
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            ResultSet result = databaseUtility.getStatement().executeQuery();
            while (result.next()) {
                Parking parking = new Parking();
                parking.setId(result.getString("id"));
                String ss=result.getString("parking_type");
                parking.setParkingType(ParkingType.valueOf(result.getString("parking_type")));
                parking.setTotalNoOfRows(result.getInt("total_rows"));
                parking.setTotalNoOfCols(result.getInt("total_cols"));
                parking.setTotalSpaceAvailable(result.getInt("total_space_available"));
                parking.setRatePerHour(result.getDouble("rate_per_hour"));
                parking.setAdditionalFee(result.getDouble("additional_fee"));
                parking.setElectric(result.getBoolean("is_electric"));
                parkings.add(parking);
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve information");
        }
        return parkings;
    }

    public Parking getparkingSpaceDetails(int parkingId) {
        String query = "SELECT * FROM parking WHERE id=?";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setInt(1, parkingId);
            ResultSet result = databaseUtility.getStatement().executeQuery();
            Parking parking = new Parking();
            while (result.next()) {
                parking.setId(result.getString("id"));
                parking.setParkingType(ParkingType.valueOf(result.getString("parking_type")));
                parking.setTotalNoOfRows(result.getInt("total_rows"));
                parking.setTotalNoOfCols(result.getInt("total_cols"));
                parking.setTotalSpaceAvailable(result.getInt("total_space_available"));
                parking.setRatePerHour(result.getDouble("rate_per_hour"));
                parking.setAdditionalFee(result.getDouble("additional_fee"));
                parking.setElectric(result.getBoolean("is_electric"));
            }
            return parking;
        } catch (Exception e) {
            System.out.println("Failed to retrieve information");
        }
        return null;
    }

    public Parking updateParkingInfo(Parking parking){
        String query = "UPDATE parking set rate_per_hour=?, additional_fee=? where id=?";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setDouble(1, parking.getRatePerHour());
            databaseUtility.getStatement().setDouble(2, parking.getAdditionalFee());
            databaseUtility.getStatement().setString(3, parking.getId());
            databaseUtility.getStatement().execute();
            return parking;
        } catch (Exception e) {
            System.out.println("Failed to update the information");
        }
        return null;
    }
}
