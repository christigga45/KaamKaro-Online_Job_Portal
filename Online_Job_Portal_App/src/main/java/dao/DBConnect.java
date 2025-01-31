package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String DB_URL = DBInfo.DBUrl;
    private static final String DB_USER = DBInfo.DBUname;
    private static final String DB_PASSWORD = DBInfo.DBUpwd;

    private DBConnect() {
        // Private constructor to prevent instantiation
    }

    // Provide a fresh connection each time
    public static Connection getCon() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
