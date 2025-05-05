
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class Admin_Staff_Internal extends javax.swing.JInternalFrame {

    public Admin_Staff_Internal() {
        initComponents();
        
         loadStaffs();
        
            //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        staffs.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        staffs.getTableHeader().setOpaque(false);
        staffs.getTableHeader().setBorder(null);
        staffs.getTableHeader().setBackground(new Color(51, 51, 255));
        staffs.getTableHeader().setForeground(new Color(0, 0, 0));
        staffs.setRowHeight(25);
        
        //table colum size
        staffs.getColumnModel().getColumn(0).setPreferredWidth(30);
        staffs.getColumnModel().getColumn(1).setPreferredWidth(30);
        staffs.getColumnModel().getColumn(2).setPreferredWidth(60);
        staffs.getColumnModel().getColumn(3).setPreferredWidth(60);
        staffs.getColumnModel().getColumn(4).setPreferredWidth(60);
        
        
          //FOR LIVE SEARCH LEZGOOO
        searchStaff.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchStaff();
            }
        });  
        
        staffs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = staffs.getSelectedRow();
                    if (selectedRow != -1) {
                        int staffId = Integer.parseInt(staffs.getValueAt(selectedRow, 0).toString());
                        new Admin_Update_Staff(staffId).setVisible(true);
                    } else {
                         JOptionPane.showMessageDialog(
                            null,
                            "<html><b>No staff member selected.</b><br>Please select a staff member before proceeding.</html>",
                            "⚠ Selection Required",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
            }
        });
    }
    
    private void loadStaffs(){ 
        ConnectDB connect = new ConnectDB();
        int currentUserId = Session.getInstance().getUserId(); // Correct

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Staff ID");
        model.addColumn("Has Account");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Gender");

        try {
            Connection conn = connect.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

            String query = "SELECT staff_id, user_id, s_fname, s_lname, s_gender " +
                           "FROM staff WHERE s_status != 'Archived' AND user_id != ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, currentUserId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("staff_id"),
                    rs.getInt("user_id"),
                    rs.getString("s_fname"),
                    rs.getString("s_lname"),
                    rs.getString("s_gender")
                });
            }

            staffs.setModel(model);
            model.fireTableDataChanged();

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error loading staff: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    public int getSelectedStaffId() {
        int selectedRow = staffs.getSelectedRow(); // Get the selected row index

        if (selectedRow != -1) { // Check if a row is selected
            return Integer.parseInt(staffs.getValueAt(selectedRow, 0).toString()); // Get staff_id from the first column
        }
        return -1; // Return -1 if no row is selected
    }

    private void searchStaff() {
        String keyword = searchStaff.getText().trim(); // JTextField for live search

        try {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            StringBuilder sql = new StringBuilder("SELECT staff_id, user_id, s_fname, s_lname, s_gender FROM staff");

            if (!keyword.isEmpty()) {
                sql.append(" WHERE s_fname LIKE ? OR s_lname LIKE ?");
            }

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            if (!keyword.isEmpty()) {
                pstmt.setString(1, "%" + keyword + "%");
                pstmt.setString(2, "%" + keyword + "%");
            }

            ResultSet rs = pstmt.executeQuery();

            // Clear table
            DefaultTableModel model = (DefaultTableModel) staffs.getModel(); // assuming JTable name is staffTable
            model.setRowCount(0);

            // Populate table
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("staff_id"),
                    rs.getInt("user_id"),
                    rs.getString("s_fname"),
                    rs.getString("s_lname"),
                    rs.getString("s_gender")
                });
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>⚠ An error occurred while searching staff.</b><br>" +
                e.getMessage() + "</html>",
                "Search Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    
    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        staff_header = new javax.swing.JPanel();
        staff = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        archivePanel = new javax.swing.JPanel();
        archive = new javax.swing.JLabel();
        updatePanel = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        staffs = new javax.swing.JTable();
        refreshPanel = new javax.swing.JPanel();
        refresh = new javax.swing.JLabel();
        searchStaff = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        staff_header.setBackground(new java.awt.Color(55, 162, 153));
        staff_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        staff_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        staff.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        staff.setForeground(new java.awt.Color(255, 255, 255));
        staff.setText("Staff Information");
        staff_header.add(staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 180, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Manage admin accounts");
        staff_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 190, 50));

        jPanel1.add(staff_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archivePanel.setBackground(new java.awt.Color(0, 51, 51));
        archivePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archivePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                archivePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                archivePanelMouseExited(evt);
            }
        });
        archivePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archive.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        archive.setForeground(new java.awt.Color(255, 255, 255));
        archive.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        archive.setText("ARCHIVE");
        archivePanel.add(archive, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel3.add(archivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, -1));

        updatePanel.setBackground(new java.awt.Color(0, 51, 51));
        updatePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePanelMouseExited(evt);
            }
        });
        updatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit.setText("UPDATE");
        updatePanel.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel3.add(updatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, -1));
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, -30, -1, -1));
        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, -40, 80, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 50));

        staffs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        staffs.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(staffs);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 840, 380));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 860, 450));

        refreshPanel.setBackground(new java.awt.Color(0, 51, 51));
        refreshPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshPanelMouseExited(evt);
            }
        });
        refreshPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh (2).png"))); // NOI18N
        refreshPanel.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 30));

        jPanel1.add(refreshPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 60, -1));

        searchStaff.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        searchStaff.setForeground(new java.awt.Color(153, 153, 153));
        searchStaff.setText("Search");
        searchStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStaffActionPerformed(evt);
            }
        });
        jPanel1.add(searchStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 230, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updatePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseClicked

        int selectedStaffId = getSelectedStaffId(); // Get selected staff ID

        if (selectedStaffId != -1) {
            Admin_Update_Staff updateForm = new Admin_Update_Staff(selectedStaffId);
            updateForm.setVisible(true);
            this.dispose(); // Optional: close current window
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please select a staff member to edit.</b></html>",
                "🔍 No Staff Selected",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_updatePanelMouseClicked

    private void updatePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseEntered
        updatePanel.setBackground(hoverColor);
    }//GEN-LAST:event_updatePanelMouseEntered

    private void updatePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseExited
        updatePanel.setBackground(navColor);
    }//GEN-LAST:event_updatePanelMouseExited

    private void archivePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivePanelMouseClicked
        int selectedStaffId = getSelectedStaffId(); // Implement this to return selected staff_id

        if (selectedStaffId != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to archive this staff's information?",
                "Confirm Archive",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    ConnectDB db = new ConnectDB();
                    try (Connection conn = db.getConnection()) {

                        // Step 1: Fetch staff details for logging
                        String fetchQuery = "SELECT s_fname, s_lname FROM staff WHERE staff_id = ?";
                        String staffName = "";

                        try (PreparedStatement fetchStmt = conn.prepareStatement(fetchQuery)) {
                            fetchStmt.setInt(1, selectedStaffId);
                            try (ResultSet rs = fetchStmt.executeQuery()) {
                                if (rs.next()) {
                                    staffName = rs.getString("s_fname") + " " + rs.getString("s_lname");
                                }
                            }
                        }

                        // Step 2: Archive the staff
                        String archiveQuery = "UPDATE staff SET s_status = 'Archived' WHERE staff_id = ?";
                        try (PreparedStatement archiveStmt = conn.prepareStatement(archiveQuery)) {
                            archiveStmt.setInt(1, selectedStaffId);
                            int rowsUpdated = archiveStmt.executeUpdate();

                            if (rowsUpdated > 0) {
                                // Step 3: Log archive event
                                Session sess = Session.getInstance();
                                sess.logEvent("ARCHIVED STAFF", "Admin archived staff: " + staffName + " (ID: " + selectedStaffId + ")");

                                JOptionPane.showMessageDialog(
                                    this,
                                    "<html><b>Staff archived successfully!</b></html>",
                                    "Archive Success",
                                    JOptionPane.INFORMATION_MESSAGE
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                    this,
                                    "<html><b>Failed to archive staff.</b><br>Please try again or check database connection.</html>",
                                    "Archive Failed",
                                    JOptionPane.WARNING_MESSAGE
                                );
                            }
                        }
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>Database Error:</b><br>" + e.getMessage() + "</html>",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>No Selection</b><br>Please select a staff member to archive.</html>",
                "Selection Required",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_archivePanelMouseClicked

    private void archivePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivePanelMouseEntered
        archivePanel.setBackground(hoverColor);
    }//GEN-LAST:event_archivePanelMouseEntered

    private void archivePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivePanelMouseExited
        archivePanel.setBackground(navColor);
    }//GEN-LAST:event_archivePanelMouseExited

    private void refreshPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseClicked
        loadStaffs();
    }//GEN-LAST:event_refreshPanelMouseClicked

    private void refreshPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseEntered
        refreshPanel.setBackground(hoverColor);
    }//GEN-LAST:event_refreshPanelMouseEntered

    private void refreshPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseExited
        refreshPanel.setBackground(navColor);
    }//GEN-LAST:event_refreshPanelMouseExited

    private void searchStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStaffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchStaffActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel archive;
    private javax.swing.JPanel archivePanel;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel refresh;
    private javax.swing.JPanel refreshPanel;
    private javax.swing.JTextField searchStaff;
    private javax.swing.JLabel staff;
    private javax.swing.JPanel staff_header;
    private javax.swing.JTable staffs;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables
}
