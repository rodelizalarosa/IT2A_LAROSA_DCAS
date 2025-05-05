
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class Admin_Doctor_Internal extends javax.swing.JInternalFrame {

    public Admin_Doctor_Internal() {
        initComponents();
        loadDoctors();
        
            //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        dentist.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        dentist.getTableHeader().setOpaque(false);
        dentist.getTableHeader().setBorder(null);
        dentist.getTableHeader().setBackground(new Color(51, 51, 255));
        dentist.getTableHeader().setForeground(new Color(0, 0, 0));
        dentist.setRowHeight(25);
        
        //table colum size
        dentist.getColumnModel().getColumn(0).setPreferredWidth(30);
        dentist.getColumnModel().getColumn(1).setPreferredWidth(30);
        dentist.getColumnModel().getColumn(2).setPreferredWidth(60);
        dentist.getColumnModel().getColumn(3).setPreferredWidth(60);
        dentist.getColumnModel().getColumn(4).setPreferredWidth(25);
        dentist.getColumnModel().getColumn(5).setPreferredWidth(60);
        dentist.getColumnModel().getColumn(6).setPreferredWidth(30);
        
        //FOR LIVE SEARCH LEZGOOO
        searchDentist.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchDentist();
            }
        });  
        
        filterDentist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchDentist();
            }
        });
        
        dentist.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = dentist.getSelectedRow();
                    if (selectedRow != -1) {
                        int dentistId = Integer.parseInt(dentist.getValueAt(selectedRow, 0).toString());
                        new Admin_Update_Dentist(dentistId).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(
                            null,
                            "<html><b>⚠ No row selected.</b><br>Please select a dentist before double-clicking.</html>",
                            "⚠ Selection Required",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
            }
        });
    }
    
    private void loadDoctors() { 
        ConnectDB connect = new ConnectDB();

        // Create an uneditable table model
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are uneditable
            }
        };

        model.addColumn("Dentist ID");
        model.addColumn("Has Account");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Gender");
        model.addColumn("Specialization");
        model.addColumn("Contact Number");

        try {
            Connection conn = connect.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

            String query = "SELECT dentist_id, user_id, d_fname, d_lname, d_gender, specialization, d_contact " +
                           "FROM dentist WHERE d_status != 'Archived' AND user_id != ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, 0); // Adjust based on your filtering needs
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("dentist_id"),
                    rs.getInt("user_id"),
                    rs.getString("d_fname"),
                    rs.getString("d_lname"),
                    rs.getString("d_gender"),
                    rs.getString("specialization"),
                    rs.getString("d_contact")
                });
            }

            dentist.setModel(model);
            model.fireTableDataChanged();

            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error loading doctors: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    public int getSelectedUserId() {
        int selectedRow = dentist.getSelectedRow(); // Get the selected row index

            if (selectedRow != -1) { // Check if a row is selected
                return Integer.parseInt(dentist.getValueAt(selectedRow, 0).toString()); // Get user_id from the first column
            }
        return -1; // Return -1 if no row is selected
    }
    
    private void searchDentist() {
        String keyword = searchDentist.getText().trim();
        String filter = filterDentist.getSelectedItem().toString(); // ComboBox: All, General, Orthodontist

        try {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            StringBuilder sql = new StringBuilder("SELECT dentist_id, user_id, d_fname, d_lname, d_gender, specialization, d_contact FROM dentist WHERE 1=1");

            if (!keyword.isEmpty()) {
                sql.append(" AND (d_fname LIKE ? OR d_lname LIKE ?)");
            }

            if (!filter.equals("All")) {
                sql.append(" AND specialization = ?");
            }

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (!keyword.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + keyword + "%");
                pstmt.setString(paramIndex++, "%" + keyword + "%");
            }

            if (!filter.equals("All")) {
                pstmt.setString(paramIndex++, filter);
            }

            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) dentist.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("dentist_id"),
                    rs.getInt("user_id"),
                    rs.getString("d_fname"),
                    rs.getString("d_lname"),
                    rs.getString("d_gender"),
                    rs.getString("specialization"),
                    rs.getString("d_contact")
                });
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Error searching dentists:</b><br>" + e.getMessage() + "</html>",
                "⚠ Error",
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
        doctor_header = new javax.swing.JPanel();
        doctor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        updatePanel = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        archivePanel = new javax.swing.JPanel();
        delete = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dentist = new javax.swing.JTable();
        refreshPanel = new javax.swing.JPanel();
        refresh = new javax.swing.JLabel();
        searchDentist = new javax.swing.JTextField();
        filterDentist = new javax.swing.JComboBox<>();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doctor_header.setBackground(new java.awt.Color(55, 162, 153));
        doctor_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        doctor_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doctor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        doctor.setForeground(new java.awt.Color(255, 255, 255));
        doctor.setText("Doctor Information");
        doctor_header.add(doctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 180, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Manage doctor accounts.");
        doctor_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, 50));

        jPanel1.add(doctor_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        delete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete.setText("ARCHIVE");
        archivePanel.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel3.add(archivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 50));

        dentist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        dentist.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(dentist);

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

        searchDentist.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        searchDentist.setForeground(new java.awt.Color(153, 153, 153));
        searchDentist.setText("Search");
        searchDentist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDentistActionPerformed(evt);
            }
        });
        jPanel1.add(searchDentist, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 230, 30));

        filterDentist.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filterDentist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "General", "Orthodontist" }));
        jPanel1.add(filterDentist, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updatePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseClicked

         int selectedDentistId = getSelectedUserId(); // Assuming this method returns dentist_id from table

        if (selectedDentistId != -1) {
            Admin_Update_Dentist updateForm = new Admin_Update_Dentist(selectedDentistId);
            updateForm.setVisible(true);
            this.dispose(); // Optional: close current window
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please select a dentist to edit.</b></html>",
                "🔍 No Dentist Selected",
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
         int selectedDentistId = getSelectedUserId(); // Assumes this method gets dentist_id from selected row

        if (selectedDentistId != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Are you sure you want to archive this dentist's information?</b><br>This action cannot be undone.</html>",
                "⚠ Confirm Archive",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    // Step 1: Fetch dentist details for logging
                    String fetchQuery = "SELECT d_fname, d_lname FROM dentist WHERE dentist_id = ?";
                    PreparedStatement fetchStmt = conn.prepareStatement(fetchQuery);
                    fetchStmt.setInt(1, selectedDentistId);
                    ResultSet rs = fetchStmt.executeQuery();

                    String dentistName = "";
                    if (rs.next()) {
                        dentistName = rs.getString("d_fname") + " " + rs.getString("d_lname");
                    }

                    // Step 2: Archive the dentist
                    String archiveQuery = "UPDATE dentist SET d_status = 'Archived' WHERE dentist_id = ?";
                    PreparedStatement archiveStmt = conn.prepareStatement(archiveQuery);
                    archiveStmt.setInt(1, selectedDentistId);

                    int rowsUpdated = archiveStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        // Step 3: Log archive event
                        Session sess = Session.getInstance();
                        sess.logEvent("ARCHIVED DENTIST", "Admin archived dentist: " + dentistName + " (ID: " + selectedDentistId + ")");

                        // Success message with HTML formatting
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>✅ Dentist archived successfully!</b><br>The dentist's status has been updated to 'Archived'.</html>",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        // Error message with HTML formatting
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>⚠️ Failed to archive dentist.</b><br>There was an issue updating the dentist's status.</html>",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }

                    rs.close();
                    fetchStmt.close();
                    archiveStmt.close();
                    conn.close();

                } catch (SQLException e) {
                    // Database error message with HTML formatting
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>❌ Database error:</b><br>" + e.getMessage() + "</html>",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            // FIXED: This block was incorrectly nested before
            JOptionPane.showMessageDialog(
                this,
                "<html><b>⚠️ Please select a dentist to archive.</b><br>Ensure a dentist is selected before archiving.</html>",
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
        loadDoctors();
    }//GEN-LAST:event_refreshPanelMouseClicked

    private void refreshPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseEntered
        refreshPanel.setBackground(hoverColor);
    }//GEN-LAST:event_refreshPanelMouseEntered

    private void refreshPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseExited
        refreshPanel.setBackground(navColor);
    }//GEN-LAST:event_refreshPanelMouseExited

    private void searchDentistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDentistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchDentistActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel archivePanel;
    private javax.swing.JLabel delete;
    private javax.swing.JTable dentist;
    private javax.swing.JLabel doctor;
    private javax.swing.JPanel doctor_header;
    private javax.swing.JLabel edit;
    private javax.swing.JComboBox<String> filterDentist;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel refresh;
    private javax.swing.JPanel refreshPanel;
    private javax.swing.JTextField searchDentist;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables
}
