
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;



public class Admin_Update_Patient extends javax.swing.JFrame {


   private int patientId;

    public Admin_Update_Patient() {
        initComponents();   
    }

    public Admin_Update_Patient(int selectedPatientId) {
        initComponents();
        patientID.setEditable(false);
        loadPatient(selectedPatientId);
    }

    private void loadPatient(int selectedPatientId) {
        this.patientId = selectedPatientId;

        String query = "SELECT p_fname, p_mname, p_lname, p_gender, p_dob, p_contactNumber, p_email FROM patients WHERE patient_id = ?";

        try (Connection conn = new ConnectDB().getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, selectedPatientId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String fname = rs.getString("p_fname");
                    String mname = rs.getString("p_mname");
                    String lname = rs.getString("p_lname");
                    String genderVal = rs.getString("p_gender");
                    Date dob = rs.getDate("p_dob");
                    String phone = rs.getString("p_contactNumber");
                    String email = rs.getString("p_email");

                    patientID.setText(String.valueOf(selectedPatientId));
                    firstName.setText(fname);
                    middleName.setText(mname != null ? mname : "");
                    lastName.setText(lname);
                    gender.setSelectedItem(genderVal);
                    birth.setDate(dob);
                    this.phone.setText(phone);
                    emailAdd.setText(email != null ? email : "");
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Patient with ID " + selectedPatientId + " not found.",
                        "Patient Not Found",
                        JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading patient data: " + e.getMessage(),
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
        date1 = new javax.swing.JLabel();
        birth = new com.toedter.calendar.JDateChooser();
        middleName = new javax.swing.JTextField();
        appointment1 = new javax.swing.JLabel();
        savePatient = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cancelPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        appointment8 = new javax.swing.JLabel();
        emailAdd = new javax.swing.JTextField();
        patientID = new javax.swing.JTextField();
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
        appointment.setText("Update Patient Information");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 250, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Fill out the necessary patient details to update.");
        appointment_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 380, 50));

        jPanel1.add(appointment_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

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
        account2.setText("PATIENT DETAILS");
        jPanel9.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 590, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fill out patient details to update.");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 620, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 50));

        appointment2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment2.setForeground(new java.awt.Color(51, 51, 51));
        appointment2.setText("First Name");
        jPanel2.add(appointment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 90, 30));

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
        jPanel2.add(firstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 250, 30));

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
        jPanel2.add(lastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 250, 30));

        appointment3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment3.setForeground(new java.awt.Color(51, 51, 51));
        appointment3.setText("Last Name");
        jPanel2.add(appointment3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 160, 30));

        appointment7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment7.setForeground(new java.awt.Color(51, 51, 51));
        appointment7.setText("Phone Number");
        jPanel2.add(appointment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 160, 30));

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
        jPanel2.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 250, 30));

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
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 250, 30));

        appointment6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment6.setForeground(new java.awt.Color(51, 51, 51));
        appointment6.setText("Gender");
        jPanel2.add(appointment6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 160, 30));

        date1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        date1.setForeground(new java.awt.Color(51, 51, 51));
        date1.setText("Date of Birth");
        jPanel2.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 160, 30));

        birth.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 250, 30));

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
        jPanel2.add(middleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 250, 30));

        appointment1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment1.setForeground(new java.awt.Color(51, 51, 51));
        appointment1.setText("Middle Name");
        jPanel2.add(appointment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 130, 30));

        savePatient.setBackground(new java.awt.Color(0, 51, 51));
        savePatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savePatientMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                savePatientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                savePatientMouseExited(evt);
            }
        });
        savePatient.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Save");
        savePatient.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel2.add(savePatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, 90, 30));

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

        jPanel2.add(cancelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 90, 30));

        appointment8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment8.setForeground(new java.awt.Color(51, 51, 51));
        appointment8.setText("Email");
        jPanel2.add(appointment8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 160, 30));

        emailAdd.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        emailAdd.setForeground(new java.awt.Color(51, 51, 51));
        emailAdd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailAddFocusLost(evt);
            }
        });
        emailAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                emailAddMouseReleased(evt);
            }
        });
        emailAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailAddActionPerformed(evt);
            }
        });
        jPanel2.add(emailAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 250, 30));

        patientID.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        patientID.setForeground(new java.awt.Color(51, 51, 51));
        patientID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                patientIDFocusLost(evt);
            }
        });
        patientID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                patientIDMouseReleased(evt);
            }
        });
        patientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientIDActionPerformed(evt);
            }
        });
        jPanel2.add(patientID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 70, 30));

        appointment4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment4.setForeground(new java.awt.Color(51, 51, 51));
        appointment4.setText("Patient ID");
        jPanel2.add(appointment4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 90, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 650, 480));

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

    private void savePatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePatientMouseClicked
    
         // Step 1: Validate all fields
        if (firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty() ||
            phone.getText().trim().isEmpty() || birth.getDate() == null) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>⚠ Please complete all required fields.</b><br>First name, Last name, Phone, and Birthdate are mandatory.</html>",
                "Incomplete Form",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            int patientId = Integer.parseInt(patientID.getText().trim()); // Assuming you have a hidden text field storing patient ID
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            // Step 2: Update the patient record
            String updateQuery = "UPDATE patients SET p_fname = ?, p_mname = ?, p_lname = ?, p_gender = ?, p_dob = ?, p_contactNumber = ?, p_email = ? WHERE patient_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);

            updateStmt.setString(1, firstName.getText().trim());
            updateStmt.setString(2, middleName.getText().trim().isEmpty() ? null : middleName.getText().trim()); // optional
            updateStmt.setString(3, lastName.getText().trim());
            updateStmt.setString(4, (String) gender.getSelectedItem());
            updateStmt.setDate(5, new java.sql.Date(birth.getDate().getTime()));
            updateStmt.setString(6, phone.getText().trim());
            updateStmt.setString(7, emailAdd.getText().trim().isEmpty() ? null : emailAdd.getText().trim()); // optional
            updateStmt.setInt(8, patientId);

           int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Add this logging section
                Session sess = Session.getInstance();
                sess.logEvent("UPDATED PATIENT DETAILS", "Admin updated patient details.");
                
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>✅ Patient updated successfully!</b></html>",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );

                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>⚠ Patient not found or no changes made.</b></html>",
                        "No Update",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            updateStmt.close();
            conn.close();

            this.dispose(); // Close the form if needed

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "<html><b>❌ An unexpected error occurred:</b><br>" + e.getMessage() + "</html>",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_savePatientMouseClicked

    private void savePatientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePatientMouseEntered
        savePatient.setBackground(Hover);
    }//GEN-LAST:event_savePatientMouseEntered

    private void savePatientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePatientMouseExited
        savePatient.setBackground(Nav);
    }//GEN-LAST:event_savePatientMouseExited

    private void cancelPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseClicked
        Admin_Patient_Internal app = new Admin_Patient_Internal();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelPanelMouseClicked

    private void cancelPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseEntered
        cancelPanel.setBackground(Hover);
    }//GEN-LAST:event_cancelPanelMouseEntered

    private void cancelPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseExited
        cancelPanel.setBackground(Nav);
    }//GEN-LAST:event_cancelPanelMouseExited

    private void emailAddFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailAddFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_emailAddFocusLost

    private void emailAddMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailAddMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_emailAddMouseReleased

    private void emailAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailAddActionPerformed

    private void patientIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientIDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDFocusLost

    private void patientIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientIDMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDMouseReleased

    private void patientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_Update_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Update_Patient().setVisible(true);
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
    private javax.swing.JLabel appointment8;
    private javax.swing.JPanel appointment_header;
    private com.toedter.calendar.JDateChooser birth;
    private javax.swing.JPanel cancelPanel;
    private javax.swing.JLabel date1;
    private javax.swing.JTextField emailAdd;
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
    private javax.swing.JTextField patientID;
    private javax.swing.JTextField phone;
    private javax.swing.JPanel savePatient;
    // End of variables declaration//GEN-END:variables
}
