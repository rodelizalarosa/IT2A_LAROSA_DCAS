package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
    
        private static Connection connect;

        // Constructor to establish connection
        public ConnectDB() {
            try {
                if (connect == null || connect.isClosed()) {
                    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dcas_sys", "root", "");
                }
            } catch (SQLException ex) {
                System.out.println("Can't connect to database: " + ex.getMessage());
            }
        }

        public static Connection getConnection() {
            try {
                if (connect == null || connect.isClosed()) {
                    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dcas_sys", "root", "");
                }
            } catch (SQLException ex) {
                System.err.println("Connection error: " + ex.getMessage());
            }
            return connect;
        }

        // Insert data method
        public int insertData(String sql) {
            int result = 0;
            try (PreparedStatement pst = connect.prepareStatement(sql)) {
                pst.executeUpdate();
                System.out.println("Inserted successfully!");
                result = 1;
            } catch (SQLException ex) {
                System.err.println("Insert error: " + ex.getMessage());
            }
            return result;
        }

        // Get data method
        public ResultSet getData(String sql) throws SQLException {
            java.sql.Statement stmt = connect.createStatement();
            return stmt.executeQuery(sql);
        }

        // Optionally close the connection when needed (outside ConnectDB class)
        public void closeConnection() {
            try {
                if (connect != null && !connect.isClosed()) {
                    connect.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }
}
