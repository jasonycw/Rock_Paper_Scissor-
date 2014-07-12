package util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author User
 */
public final class DBConnection {
    private static final String JDBC_CONNECTION = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad016_db";
    private static final String DB_USERNAME = "aiad016";
    private static final String DB_PASSWORD = "aiad016";

    private DBConnection() {

    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(JDBC_CONNECTION, DB_USERNAME, DB_PASSWORD);
    }
}
