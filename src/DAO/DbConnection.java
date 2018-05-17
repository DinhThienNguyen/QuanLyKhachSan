package DAO;

import java.sql.*;

public class DbConnection {
    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Hotel.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
