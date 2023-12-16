package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtility implements AutoCloseable {
    public static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/authentication";
    public static final String DB_PASSWORD = "Thisis2023)";
    public static final String DB_USERNAME = "root";
    private final PreparedStatement statement;
    private final Connection connection;
    private final String query;

    public DatabaseUtility(String query) throws SQLException {
        connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME,
                DB_PASSWORD);
        this.query = query;
        statement = connection.prepareStatement(query);
    }

    @Override
    public void close() throws Exception {
        if (null != connection) connection.close();
        if (null != statement) statement.close();
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getQuery() {
        return query;
    }
}
