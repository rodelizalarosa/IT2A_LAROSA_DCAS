
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Admin_Update_Staff extends javax.swing.JFrame {

    private int staffId;

    public Admin_Update_Staff() {
        initComponents();   
    }

    public Admin_Update_Staff(int selectedStaffId) {
        initComponents();
        staffID.setEditable(false);
        loadStaff(selectedStaffId);
    }
    
    private void loadStaff(int selectedStaffId) {
        this.staffId = selectedStaffId;

        String query = "SELECT s_fname, s_mname, s_lname, s_gender, s_contact FROM staff WHERE staff_id = ?";

        try (Connection conn = new ConnectDB().getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, selectedStaffId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String fname = rs.getString("s_fname");
                    String mname = rs.getString("s_mname");
                    String lname = rs.getString("s_lname");
                    String genderVal = rs.getString("s_gender");
                    String contact = rs.getString("s_contact");

                    staffID.setText(String.valueOf(selectedStaffId));
                    firstName.setText(fname);
                    middleName.setText(mname != null ? mname : "");
                    lastName.setText(lname);
                    gender.setSelectedItem(genderVal);
                    phone.setText(contact);
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Staff with ID " + selectedStaffId + " not found.",
                        "Staff Not Found",
                        JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading staff data: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    
    Color Hover = new Color (55,162,153);
    Color Nav = new Color (0,51,51);


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        appointment_header = new javax.swing.JPanel();
        appointment = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        account2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        appointment2 = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        lastName = new javax.swing.JTextField();
        appointment3 = new javax.swing.JLabel();
        appointment7 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        gender = new javax.swing.JComboBox<>();
        appointment6 = new javax.swing.JLabel();
        middleName = new javax.swing.JTextField();
        appointment1 = new javax.swing.JLabel();
        saveStaff = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cancelPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        staffID = new javax.swing.JTextField();
        appointment4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Update Staff Information");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 250, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Fill out the necessary staff details to update.");
        appointment_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 380, 50));

        jPanel1.add(appointment_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 850, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(55, 162, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        account2.setForeground(new java.awt.Color(255, 255, 255));
        account2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account2.setText("STAFF DETAILS");
        jPanel9.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 510, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fill out staff details to update.");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 530, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 50));

        appointment2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment2.setForeground(new java.awt.Color(51, 51, 51));
        appointment2.setText("First Name");
        jPanel2.add(appointment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 90, 30));

        firstName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        firstName.setForeground(new java.awt.Color(51, 51, 51));
        firstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstNameFocusLost(evt);
            }
        });
        firstName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                firstNameMouseReleased(evt);
            }
        });
        firstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameActionPerformed(evt);
            }
        });
        jPanel2.add(firstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 250, 30));

        lastName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        lastName.setForeground(new java.awt.Color(51, 51, 51));
        lastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameFocusLost(evt);
            }
        });
        lastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lastNameMouseReleased(evt);
            }
        });
        lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameActionPerformed(evt);
            }
        });
        jPanel2.add(lastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 250, 30));

        appointment3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment3.setForeground(new java.awt.Color(51, 51, 51));
        appointment3.setText("Last Name");
        jPanel2.add(appointment3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 160, 30));

        appointment7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment7.setForeground(new java.awt.Color(51, 51, 51));
        appointment7.setText("Phone Number");
        jPanel2.add(appointment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 160, 30));

        phone.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        phone.setForeground(new java.awt.Color(51, 51, 51));
        phone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                phoneFocusLost(evt);
            }
        });
        phone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                phoneMouseReleased(evt);
            }
        });
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        jPanel2.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 250, 30));

        gender.setBackground(new java.awt.Color(204, 204, 204));
        gender.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        gender.setForeground(new java.awt.Color(51, 51, 51));
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female" }));
        gender.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                genderFocusLost(evt);
            }
        });
        gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderActionPerformed(evt);
            }
        });
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 250, 30));

        appointment6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment6.setForeground(new java.awt.Color(51, 51, 51));
        appointment6.setText("Gender");
        jPanel2.add(appointment6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 160, 30));

        middleName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        middleName.setForeground(new java.awt.Color(51, 51, 51));
        middleName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                middleNameFocusLost(evt);
            }
        });
        middleName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                middleNameMouseReleased(evt);
            }
        });
        middleName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middleNameActionPerformed(evt);
            }
        });
        jPanel2.add(middleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 250, 30));

        appointment1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment1.setForeground(new java.awt.Color(51, 51, 51));
        appointment1.setText("Middle Name");
        jPanel2.add(appointment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 130, 30));

        saveStaff.setBackground(new java.awt.Color(0, 51, 51));
        saveStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveStaffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveStaffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveStaffMouseExited(evt);
            }
        });
        saveStaff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Save");
        saveStaff.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel2.add(saveStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 90, 30));

        cancelPanel.setBackground(new java.awt.Color(0, 51, 51));
        cancelPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelPanelMouseExited(evt);
            }
        });
        cancelPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cancel");
        cancelPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel2.add(cancelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 90, 30));

        staffID.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        staffID.setForeground(new java.awt.Color(51, 51, 51));
        staffID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                staffIDFocusLost(evt);
            }
        });
        staffID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                staffIDMouseReleased(evt);
            }
        });
        staffID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffIDActionPerformed(evt);
            }
        });
        jPanel2.add(staffID, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 70, 30));

        appointment4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment4.setForeground(new java.awt.Color(51, 51, 51));
        appointment4.setText("Staff ID");
        jPanel2.add(appointment4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 90, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 560, 410));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void firstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFocusLost

    private void firstNameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstNameMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameMouseReleased

    private void firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameActionPerformed

    private void lastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameFocusLost

    private void lastNameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameMouseReleased

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameActionPerformed

    private void phoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneFocusLost

    private void phoneMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phoneMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneMouseReleased

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void genderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genderFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_genderFocusLost

    private void genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderActionPerformed

    private void middleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middleNameFocusLost

    }//GEN-LAST:event_middleNameFocusLost

    private void middleNameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_middleNameMouseReleased

    }//GEN-LAST:event_middleNameMouseReleased

    private void middleNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_middleNameActionPerformed

    private void saveStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveStaffMouseClicked

        // Step 1: Validate all required fields including gender
        if (firstName.getText().trim().isEmpty() || 
            lastName.getText().trim().isEmpty() || 
            phone.getText().trim().isEmpty() || 
            gender.getSelectedItem() == null || 
            gender.getSelectedItem().toString().equals("Select")) {

            JOptionPane.showMessageDialog(
                this,
                "<html><b>⚠ Please fill all required fields</b><br>before proceeding.</html>",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }


        try {
            int staffId = Integer.parseInt(staffID.getText().trim()); // Assuming you have a hidden text field storing staff_id
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            // Step 2: Update the staff record
            String updateQuery = "UPDATE staff SET s_fname = ?, s_mname = ?, s_lname = ?, s_gender = ?, s_contact = ? WHERE staff_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);

            updateStmt.setString(1, firstName.getText().trim());
            updateStmt.setString(2, middleName.getText().trim().isEmpty() ? null : middleName.getText().trim()); // optional
            updateStmt.setString(3, lastName.getText().trim());
            updateStmt.setString(4, (String) gender.getSelectedItem());
            updateStmt.setString(5, phone.getText().trim());
            updateStmt.setInt(6, staffId);

            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Log the action
                Session sess = Session.getInstance();
                sess.logEvent("UPDATED STAFF DETAILS", "Admin updated staff details.");

                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>✅ Staff updated successfully!</b></html>",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>⚠️ Staff not found or no changes made.</b></html>",
                    "Update Failed",
                    JOptionPane.WARNING_MESSAGE
                );
            }

            updateStmt.close();
            conn.close();
            this.dispose(); // Close the form

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "<html><b>❌ Error:</b> " + e.getMessage() + "</html>",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_saveStaffMouseClicked

    private void saveStaffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveStaffMouseEntered
        saveStaff.setBackground(Hover);
    }//GEN-LAST:event_saveStaffMouseEntered

    private void saveStaffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveStaffMouseExited
        saveStaff.setBackground(Nav);
    }//GEN-LAST:event_saveStaffMouseExited

    private void cancelPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseClicked
        Admin_Staff_Internal app = new Admin_Staff_Internal();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelPanelMouseClicked

    private void cancelPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseEntered
        cancelPanel.setBackground(Hover);
    }//GEN-LAST:event_cancelPanelMouseEntered

    private void cancelPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseExited
        cancelPanel.setBackground(Nav);
    }//GEN-LAST:event_cancelPanelMouseExited

    private void staffIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staffIDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_staffIDFocusLost

    private void staffIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffIDMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_staffIDMouseReleased

    private void staffIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffIDActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Update_Staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account2;
    private javax.swing.JLabel appointment;
    private javax.swing.JLabel appointment1;
    private javax.swing.JLabel appointment2;
    private javax.swing.JLabel appointment3;
    private javax.swing.JLabel appointment4;
    private javax.swing.JLabel appointment6;
    private javax.swing.JLabel appointment7;
    private javax.swing.JPanel appointment_header;
    private javax.swing.JPanel cancelPanel;
    private javax.swing.JTextField firstName;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField lastName;
    private javax.swing.JTextField middleName;
    private javax.swing.JTextField phone;
    private javax.swing.JPanel saveStaff;
    private javax.swing.JTextField staffID;
    // End of variables declaration//GEN-END:variables
}
