
package Config;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
    
            public static Connection connect;

            public ConnectDB(){
                try{
                    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dcas_sys", "root", "");
                   
                }catch(SQLException ex){
                        System.out.println("Can't connect to database: "+ex.getMessage());
                }
            }
      
            public static Connection getConnection() {
                return connect;
            }
            
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

            public ResultSet getData(String sql) throws SQLException {
               java.sql.Statement stmt = connect.createStatement();
               return stmt.executeQuery(sql);
           }

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
            
            // -- Logs
}
