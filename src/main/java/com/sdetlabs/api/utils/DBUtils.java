package com.sdetlabs.api.utils;

import com.sdetlabs.api.config.ConfigReader;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBUtils {

    // Helper method to get Database Connection
    private static Connection getConnection() throws SQLException {
        String url = ConfigReader.getInstance().getProperty("db.url");
        String user = ConfigReader.getInstance().getProperty("db.username");
        String pass = ConfigReader.getInstance().getProperty("db.password");

        // Establishes a connection to the Database
        return DriverManager.getConnection(url, user, pass);
    }

    // Architect Method: Executes a SELECT query and returns the first row as a Map (Key-Value)
    // Example: Map will have {"firstname": "Ayush", "totalprice": 1500}
    public static Map<String, String> getRowData(String query) {
        Map<String, String> rowMap = new HashMap<>();

        // Using "Try-With-Resources" to automatically close connections even if tests fail
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Getting Column Names (Metadata)
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // If a row is found, extract all columns into the Map
            if (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = rs.getString(i);
                    rowMap.put(columnName, columnValue);
                }
            } else {
                System.out.println("WARNING: No data found for query -> " + query);
            }

        } catch (SQLException e) {
            System.err.println("DATABASE ERROR: Failed to execute query.");
            e.printStackTrace();
        }

        return rowMap;
    }
}