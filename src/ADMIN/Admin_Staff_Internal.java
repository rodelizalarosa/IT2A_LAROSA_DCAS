
package ADMIN;

import Config.ConnectDB;
import java.awt.Color;
import java.awt.Font;
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
    }
    
    private void loadStaffs(){ 
        ConnectDB connect = new ConnectDB();

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

            String query = "SELECT staff_id, user_id, s_fname, s_lname, s_gender FROM staff"; // Corrected query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("staff_id"),
                    rs.getInt("user_id"),
                    rs.getString("s_fname"),
                    rs.getString("s_lname"),
                    rs.getString("s_gender"),
                });
            }

            staffs.setModel(model);
            model.fireTableDataChanged();

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error loading doctors: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public int getSelectedUserId() {
        int selectedRow = staffs.getSelectedRow(); // Get the selected row index

            if (selectedRow != -1) { // Check if a row is selected
                return Integer.parseInt(staffs.getValueAt(selectedRow, 0).toString()); // Get user_id from the first column
            }
        return -1; // Return -1 if no row is selected
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
        jScrollPane1 = new javax.swing.JScrollPane();
        staffs = new javax.swing.JTable();
        addPanel = new javax.swing.JPanel();
        add = new javax.swing.JLabel();
        editPanel = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        deletePanel = new javax.swing.JPanel();
        delete = new javax.swing.JLabel();
        refreshPanel = new javax.swing.JPanel();
        refresh = new javax.swing.JLabel();

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
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 40));

        staffs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        staffs.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(staffs);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 840, 380));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 860, 440));

        addPanel.setBackground(new java.awt.Color(0, 51, 51));
        addPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPanelMouseExited(evt);
            }
        });
        addPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add.setText("ADD");
        addPanel.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel1.add(addPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 110, -1));

        editPanel.setBackground(new java.awt.Color(0, 51, 51));
        editPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editPanelMouseExited(evt);
            }
        });
        editPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit.setText("UPDATE");
        editPanel.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel1.add(editPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 110, -1));

        deletePanel.setBackground(new java.awt.Color(0, 51, 51));
        deletePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deletePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deletePanelMouseExited(evt);
            }
        });
        deletePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete.setText("ARCHIVE");
        deletePanel.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel1.add(deletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 110, -1));

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

        jPanel1.add(refreshPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseClicked

        Admin_Add_User ad = new Admin_Add_User();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addPanelMouseClicked

    private void addPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseEntered
        addPanel.setBackground(hoverColor);
    }//GEN-LAST:event_addPanelMouseEntered

    private void addPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseExited
        addPanel.setBackground(navColor);
    }//GEN-LAST:event_addPanelMouseExited

    private void editPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPanelMouseClicked

        int selectedUserId = getSelectedUserId(); // Get selected user's ID

        if (selectedUserId != -1) {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String query = "SELECT u_username, u_email, u_role FROM users WHERE user_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, selectedUserId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Retrieve user details
                    String username = rs.getString("u_username");
                    String email = rs.getString("u_email");
                    String role = rs.getString("u_role");

                    //                    // Pass data to userUPDATE form (without password)
                    //                    Admin_Update_User updateForm = new Admin_Update_User(selectedUserId, username, email, role);
                    //                    Session.getInstance().getDesktopPane().add(updateForm);
                    //                    //updateForm.setUserData(selectedUserId, username, email, role);
                    //                    updateForm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.");
                }

                // Close resources
                rs.close();
                pstmt.close();
                conn.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a User to Edit.");
        }
    }//GEN-LAST:event_editPanelMouseClicked

    private void editPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPanelMouseEntered
        editPanel.setBackground(hoverColor);
    }//GEN-LAST:event_editPanelMouseEntered

    private void editPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPanelMouseExited
        editPanel.setBackground(navColor);
    }//GEN-LAST:event_editPanelMouseExited

    private void deletePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletePanelMouseClicked
        int selectedUserId = getSelectedUserId(); // Get the selected user's ID

        if (selectedUserId != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Do you want to delete the user information?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    ConnectDB db = new ConnectDB(); // Create an instance of dbConnector
                    Connection conn = db.getConnection(); // Get database connection

                    String query = "DELETE FROM users WHERE user_id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setInt(1, selectedUserId);

                    int rowsDeleted = pstmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "User Deleted Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to Delete User.");
                    }
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a User to Delete.");
        }
    }//GEN-LAST:event_deletePanelMouseClicked

    private void deletePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletePanelMouseEntered
        deletePanel.setBackground(hoverColor);
    }//GEN-LAST:event_deletePanelMouseEntered

    private void deletePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletePanelMouseExited
        deletePanel.setBackground(navColor);
    }//GEN-LAST:event_deletePanelMouseExited

    private void refreshPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseClicked
        loadStaffs();
    }//GEN-LAST:event_refreshPanelMouseClicked

    private void refreshPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseEntered
        refreshPanel.setBackground(hoverColor);
    }//GEN-LAST:event_refreshPanelMouseEntered

    private void refreshPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseExited
        refreshPanel.setBackground(navColor);
    }//GEN-LAST:event_refreshPanelMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JPanel addPanel;
    private javax.swing.JLabel delete;
    private javax.swing.JPanel deletePanel;
    private javax.swing.JLabel edit;
    private javax.swing.JPanel editPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel refresh;
    private javax.swing.JPanel refreshPanel;
    private javax.swing.JLabel staff;
    private javax.swing.JPanel staff_header;
    private javax.swing.JTable staffs;
    // End of variables declaration//GEN-END:variables
}
