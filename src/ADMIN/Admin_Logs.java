
package ADMIN;

import Config.ConnectDB;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;


public class Admin_Logs extends javax.swing.JInternalFrame {

    public Admin_Logs() {
        initComponents();
        loadLogs();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        tableLogs.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        tableLogs.getTableHeader().setOpaque(false);
        tableLogs.getTableHeader().setBorder(null);
        tableLogs.getTableHeader().setBackground(new Color(51, 51, 255));
        tableLogs.getTableHeader().setForeground(new Color(0, 0, 0));
        tableLogs.setRowHeight(25);
        
        //table colum size
        tableLogs.getColumnModel().getColumn(0).setPreferredWidth(10);
        tableLogs.getColumnModel().getColumn(1).setPreferredWidth(10);
        tableLogs.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableLogs.getColumnModel().getColumn(3).setPreferredWidth(150);
        tableLogs.getColumnModel().getColumn(4).setPreferredWidth(70);
    }
    
    private void loadLogs() {
        ConnectDB connect = new ConnectDB();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Log ID");
        model.addColumn("User ID");
        model.addColumn("Event");
        model.addColumn("Description");
        model.addColumn("Timestamp");

        try {
            Connection conn = connect.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

           String query = "SELECT l.log_id, " +
               "CONCAT(u.user_id, ' - ', u.u_role) AS user_info, " +
               "l.log_event, l.log_description, l.log_timestamp " +
               "FROM logs l " +
               "JOIN users u ON l.user_id = u.user_id " +
               "ORDER BY l.log_timestamp DESC";


            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("log_id"),          // Log ID
                    rs.getInt("user_id"),         // User ID
                    rs.getString("log_event"),    // Log event
                    rs.getString("log_description"), // Description
                    rs.getTimestamp("log_timestamp") // Timestamp
                });
            }

            tableLogs.setModel(model);
            model.fireTableDataChanged();

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error loading logs: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    public int getSelectedUserId() {
        int selectedRow = tableLogs.getSelectedRow(); // Get the selected row index

            if (selectedRow != -1) { // Check if a row is selected
                return Integer.parseInt(tableLogs.getValueAt(selectedRow, 0).toString()); // Get user_id from the first column
            }
        return -1; // Return -1 if no row is selected
    }
    
    public void logEvent(int userId, String event, String description) {
   
        ConnectDB dbc = new ConnectDB();
        PreparedStatement pstmt = null;
        
        try {


            String sql = "INSERT INTO logs (user_id, log_event, log_description, log_timestamp) VALUES (?, ?, ?, ?)";
            pstmt = dbc.connect.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, event);
            pstmt.setString(3, description);
            pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }
    
    public void autoRefreshLogs() {
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLogs(); // Refresh logs every 5 seconds
            }
        });

        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logs_header = new javax.swing.JPanel();
        logs = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLogs = new javax.swing.JTable();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logs_header.setBackground(new java.awt.Color(55, 162, 153));
        logs_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logs.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        logs.setForeground(new java.awt.Color(255, 255, 255));
        logs.setText("System Logs");
        logs_header.add(logs, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 150, 50));

        jPanel1.add(logs_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tableLogs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableLogs.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableLogs);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 820, 440));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logs;
    private javax.swing.JPanel logs_header;
    private javax.swing.JTable tableLogs;
    // End of variables declaration//GEN-END:variables
}
