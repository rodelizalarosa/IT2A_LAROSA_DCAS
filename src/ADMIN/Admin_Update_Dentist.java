
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Admin_Update_Dentist extends javax.swing.JFrame {

    private int dentistId;

    public Admin_Update_Dentist() {
        initComponents();   
    }

    public Admin_Update_Dentist(int selectedDentistId) {
        initComponents();
        dentistID.setEditable(false);
        loadDentist(selectedDentistId);
    }

    private void loadDentist(int selectedDentistId) {
            this.dentistId = selectedDentistId;

            String query = "SELECT d_fname, d_mname, d_lname, d_gender, specialization, d_contact FROM dentist WHERE dentist_id = ?";

            try (Connection conn = new ConnectDB().getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setInt(1, selectedDentistId);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String fname = rs.getString("d_fname");
                        String mname = rs.getString("d_mname");
                        String lname = rs.getString("d_lname");
                        String genderVal = rs.getString("d_gender");
                        String specializationVal = rs.getString("specialization");
                        String contact = rs.getString("d_contact");

                        dentistID.setText(String.valueOf(selectedDentistId));
                        firstName.setText(fname);
                        middleName.setText(mname != null ? mname : "");
                        lastName.setText(lname);
                        gender.setSelectedItem(genderVal);

                        // Add specialization to JComboBox if not present
                        boolean found = false;
                        for (int i = 0; i < specializationBox.getItemCount(); i++) {
                            if (specializationBox.getItemAt(i).equals(specializationVal)) {
                                found = true;
                                break;
                            }
                        }
                        if (!found && specializationVal != null && !specializationVal.trim().isEmpty()) {
                            specializationBox.addItem(specializationVal);
                        }
                        specializationBox.setSelectedItem(specializationVal);

                        phone.setText(contact);
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Dentist with ID " + selectedDentistId + " not found.",
                            "Dentist Not Found",
                            JOptionPane.WARNING_MESSAGE);
                    }
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                    "Error loading dentist data: " + e.getMessage(),
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
        saveDentist = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cancelPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dentistID = new javax.swing.JTextField();
        appointment4 = new javax.swing.JLabel();
        specilization = new javax.swing.JLabel();
        specializationBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Update Dentist Information");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 250, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Fill out the necessary doctor details to update.");
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
        account2.setText("DENTIST DETAILS");
        jPanel9.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 510, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fill out dentist details to update.");
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

        saveDentist.setBackground(new java.awt.Color(0, 51, 51));
        saveDentist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveDentistMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveDentistMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveDentistMouseExited(evt);
            }
        });
        saveDentist.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Save");
        saveDentist.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel2.add(saveDentist, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 90, 30));

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

        jPanel2.add(cancelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 90, 30));

        dentistID.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        dentistID.setForeground(new java.awt.Color(51, 51, 51));
        dentistID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dentistIDFocusLost(evt);
            }
        });
        dentistID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dentistIDMouseReleased(evt);
            }
        });
        dentistID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dentistIDActionPerformed(evt);
            }
        });
        jPanel2.add(dentistID, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 70, 30));

        appointment4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment4.setForeground(new java.awt.Color(51, 51, 51));
        appointment4.setText("Dentist ID");
        jPanel2.add(appointment4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 90, 30));

        specilization.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        specilization.setForeground(new java.awt.Color(51, 51, 51));
        specilization.setText("Specialization");
        jPanel2.add(specilization, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 160, 30));

        specializationBox.setBackground(new java.awt.Color(204, 204, 204));
        specializationBox.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        specializationBox.setForeground(new java.awt.Color(51, 51, 51));
        specializationBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Specialization", "General", "Orthodontist" }));
        specializationBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                specializationBoxFocusLost(evt);
            }
        });
        specializationBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specializationBoxActionPerformed(evt);
            }
        });
        jPanel2.add(specializationBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 250, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 560, 450));

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

    private void saveDentistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveDentistMouseClicked

       // Step 1: Validate all required fields
        if (firstName.getText().trim().isEmpty() || 
            lastName.getText().trim().isEmpty() || 
            phone.getText().trim().isEmpty() ||
            gender.getSelectedItem() == null || 
            specializationBox.getSelectedItem() == null ||
            gender.getSelectedItem().toString().equals("Select") || 
            specializationBox.getSelectedItem().toString().equals("Select Specialization")) {

            JOptionPane.showMessageDialog(this, "Please fill all required fields before proceeding.");
            return;
        }

        try {
            int dentistId = Integer.parseInt(dentistID.getText().trim()); // Read-only or hidden dentist ID field
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            // Step 2: Update the dentist record
            String updateQuery = "UPDATE dentist SET d_fname = ?, d_mname = ?, d_lname = ?, d_gender = ?, specialization = ?, d_contact = ? WHERE dentist_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);

            updateStmt.setString(1, firstName.getText().trim());
            updateStmt.setString(2, middleName.getText().trim().isEmpty() ? null : middleName.getText().trim()); // optional
            updateStmt.setString(3, lastName.getText().trim());
            updateStmt.setString(4, gender.getSelectedItem().toString());
            updateStmt.setString(5, specializationBox.getSelectedItem().toString().trim());
            updateStmt.setString(6, phone.getText().trim());
            updateStmt.setInt(7, dentistId);

            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Log the action
                Session sess = Session.getInstance(); // Make sure Session is implemented correctly
                sess.logEvent("UPDATED DENTIST DETAILS", "Admin updated dentist details.");

                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>✅ Dentist updated successfully!</b></html>",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>⚠ No changes detected.</b><br>The dentist might not exist or the data is unchanged.</html>",
                    "Update Notice",
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
                "<html><b>❌ An unexpected error occurred:</b><br>" + e.getMessage() + "</html>",
                "Application Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_saveDentistMouseClicked

    private void saveDentistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveDentistMouseEntered
        saveDentist.setBackground(Hover);
    }//GEN-LAST:event_saveDentistMouseEntered

    private void saveDentistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveDentistMouseExited
        saveDentist.setBackground(Nav);
    }//GEN-LAST:event_saveDentistMouseExited

    private void cancelPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseClicked
        Admin_Doctor_Internal app = new Admin_Doctor_Internal();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelPanelMouseClicked

    private void cancelPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseEntered
        cancelPanel.setBackground(Hover);
    }//GEN-LAST:event_cancelPanelMouseEntered

    private void cancelPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseExited
        cancelPanel.setBackground(Nav);
    }//GEN-LAST:event_cancelPanelMouseExited

    private void dentistIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dentistIDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistIDFocusLost

    private void dentistIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dentistIDMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistIDMouseReleased

    private void dentistIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dentistIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistIDActionPerformed

    private void specializationBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_specializationBoxFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_specializationBoxFocusLost

    private void specializationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specializationBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_specializationBoxActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_Update_Dentist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Dentist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Dentist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Dentist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Update_Dentist().setVisible(true);
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
    private javax.swing.JTextField dentistID;
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
    private javax.swing.JPanel saveDentist;
    private javax.swing.JComboBox<String> specializationBox;
    private javax.swing.JLabel specilization;
    // End of variables declaration//GEN-END:variables
}
