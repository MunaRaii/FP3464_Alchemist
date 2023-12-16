package service;

import model.Checkout;
import utility.DatabaseUtility;

import java.sql.ResultSet;
import java.sql.Timestamp;

public class CheckoutService {

    public Checkout checkIn(Checkout checkout) {
        String query = "INSERT INTO CHECKOUT VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setString(1, checkout.getId());
            databaseUtility.getStatement().setString(2, checkout.getParkingId());
            databaseUtility.getStatement().setString(3, checkout.getUsername());
            databaseUtility.getStatement().setInt(4, checkout.getRow());
            databaseUtility.getStatement().setInt(5, checkout.getCol());
            databaseUtility.getStatement().setTimestamp(6, checkout.getCheckedIn());
            databaseUtility.getStatement().setTimestamp(7, null);
            databaseUtility.getStatement().setBoolean(8, true);
            databaseUtility.getStatement().setString(9, checkout.getUniqueKey());
            databaseUtility.getStatement().setDouble(10, checkout.getPerRate());
            databaseUtility.getStatement().setDouble(11, checkout.getAdditionalFee());
            databaseUtility.getStatement().setBoolean(12, checkout.isElectric());
            databaseUtility.getStatement().execute();
            return checkout;
        } catch (Exception e) {
            System.out.println("Unable to create  new parking type!!!");
        }
        return null;
    }

    public boolean checkOut(String uniqueKey) {
        if (!checkIfUniqueKeyExist(uniqueKey)) {
            return false;
        }
        String query = "UPDATE CHECKOUT SET is_active=?, checked_out=? WHERE unique_key=?;";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setBoolean(1, false);
            databaseUtility.getStatement().setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            databaseUtility.getStatement().setString(3, uniqueKey);
            databaseUtility.getStatement().execute();
            Checkout checkout = getCheckoutByUniqueId(uniqueKey);
            System.out.println(checkout);
            return true;
        } catch (Exception e) {
            System.out.println("Database error!!! We could not check out!!!");
        }
        return false;
    }

    public Checkout getCheckoutByUniqueId(String uniqueKey) {
        String query = "select * from checkout where unique_key=?;";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setString(1, uniqueKey);
            ResultSet resultSet = databaseUtility.getStatement().executeQuery();
            Checkout checkout = new Checkout();
            while (resultSet.next()) {
                checkout.setId(resultSet.getString("id"));
                checkout.setParkingId(resultSet.getString("parking_id"));
                checkout.setUsername(resultSet.getString("username"));
                checkout.setRow(resultSet.getInt("row"));
                checkout.setCol(resultSet.getInt("col"));
                checkout.setCheckedIn(resultSet.getTimestamp("checked_in"));
                checkout.setCheckedOut(resultSet.getTimestamp("checked_out"));
                checkout.setActive(resultSet.getBoolean("is_active"));
                checkout.setPerRate(resultSet.getDouble("per_rate"));
                checkout.setAdditionalFee(resultSet.getDouble("additional"));
                checkout.setElectric(resultSet.getBoolean("is_electric"));
            }
            return checkout;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkIfUniqueKeyExist(String uniqueKey) {
        String query = "select count(*) from checkout where unique_key=? AND is_active=true;";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setString(1, uniqueKey);
            ResultSet resultSet = databaseUtility.getStatement().executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean isAvailable(int row, int col, String parkingId) {
        String query = "select count(*) from checkout where `row`=? AND col=? AND parking_id=? AND is_active=true;";
        try (DatabaseUtility databaseUtility = new DatabaseUtility(query)) {
            databaseUtility.getStatement().setInt(1, row);
            databaseUtility.getStatement().setInt(2, col);
            databaseUtility.getStatement().setString(3, parkingId);
            ResultSet resultSet = databaseUtility.getStatement().executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return !(count > 0);
        } catch (Exception e) {
            System.out.println("Unable to create  new parking type!!!");
        }
        return false;
    }
}
