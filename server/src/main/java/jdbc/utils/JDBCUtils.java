package jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    public static Connection getNewConnection() throws SQLException {
        final String dbURL = "jdbc:postgresql://localhost:5435/cards";
        final String userName = "postgres";
        final String password = "123456";

        Connection connection = DriverManager.getConnection(dbURL, userName, password);
        if (connection.isValid(1)) {
            System.out.println("Connection successful");
        }
        return connection;
    }
}