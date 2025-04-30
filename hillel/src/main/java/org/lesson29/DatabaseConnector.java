package org.lesson29;

import java.sql.*;

public class DatabaseConnector {

    static String url = "jdbc:postgresql://localhost:5432/company";
    static String username = "postgres";
    static String password = "root";

    static Connection connection;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
