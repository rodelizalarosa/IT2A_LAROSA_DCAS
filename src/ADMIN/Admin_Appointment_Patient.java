
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


public class Admin_Appointment_Patient extends javax.swing.JFrame {


    public Admin_Appointment_Patient() {
        initComponents();
        
        Session session = Session.getInstance();

        if (session.getPatientId() != -1) {
            // Patient selected -> fill fields and make them not editable
            patientID.setText(String.valueOf(session.getPatientId()));
            firstName.setText(session.getPatientFirstName());
            lastName.setText(session.getPatientLastName());
            gender.setSelectedItem(session.getPatientGender());
            phone.setText(session.getPatientContact());
            
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // your DOB format
                java.util.Date date = sdf.parse(session.getPatientDob());
                birth.setDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Make fields non-editable
            firstName.setEditable(false);
            lastName.setEditable(false);
            gender.setEnabled(false);
            birth.setEnabled(false);
            phone.setEditable(false);

        } else {
            // No patient selected -> allow admin to fill the details
            patientID.setEditable(false); // Still not editable
            firstName.setEditable(true);
            lastName.setEditable(true);
            gender.setEnabled(true);
            birth.setEnabled(true);
            phone.setEditable(true);
        }
        
        generateNextPatientId();
    }
    
    private void generateNextPatientId() {
        try {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            String query = "SELECT MAX(patient_id) AS max_id FROM patients";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                int nextId = maxId + 1;
                patientID.setText(String.valueOf(nextId));
            } else {
                // No patients yet, start from 1
                patientID.setText("1");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void setFieldsEditable(boolean editable) {
        firstName.setEditable(editable);
        lastName.setEditable(editable);
        phone.setEditable(editable);
        gender.setEnabled(editable);
        birth.setEnabled(editable);

        // Patient ID is always not editable
        patientID.setEditable(false);
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
        patientID = new javax.swing.JTextField();
        appointment1 = new javax.swing.JLabel();
        proceedPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cancelPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Appointment Setup");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 210, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Fill out patient and appointment details.");
        appointment_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 380, 50));

        jPanel1.add(appointment_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 860, 50));

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
        jLabel2.setText("Fill out patient details to proceed.");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 620, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 50));

        appointment2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment2.setForeground(new java.awt.Color(51, 51, 51));
        appointment2.setText("First Name");
        jPanel2.add(appointment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 90, 30));

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
        jPanel2.add(firstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 250, 30));

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
        jPanel2.add(lastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 250, 30));

        appointment3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment3.setForeground(new java.awt.Color(51, 51, 51));
        appointment3.setText("Last Name");
        jPanel2.add(appointment3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 160, 30));

        appointment7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment7.setForeground(new java.awt.Color(51, 51, 51));
        appointment7.setText("Phone Number");
        jPanel2.add(appointment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 160, 30));

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
        jPanel2.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 250, 30));

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
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 250, 30));

        appointment6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment6.setForeground(new java.awt.Color(51, 51, 51));
        appointment6.setText("Gender");
        jPanel2.add(appointment6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 160, 30));

        date1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        date1.setForeground(new java.awt.Color(51, 51, 51));
        date1.setText("Date of Birth");
        jPanel2.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 160, 30));

        birth.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 250, 30));

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
        jPanel2.add(patientID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 90, 30));

        appointment1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment1.setForeground(new java.awt.Color(51, 51, 51));
        appointment1.setText("Patient ID:");
        jPanel2.add(appointment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 130, 30));

        proceedPanel.setBackground(new java.awt.Color(0, 51, 51));
        proceedPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                proceedPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                proceedPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                proceedPanelMouseExited(evt);
            }
        });
        proceedPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Proceed");
        proceedPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel2.add(proceedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 90, 30));

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

        jPanel2.add(cancelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 90, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 650, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 560));

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

    private void patientIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientIDFocusLost

    }//GEN-LAST:event_patientIDFocusLost

    private void patientIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientIDMouseReleased
        //        username.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12), new java.awt.Color(0, 0, 0)));
        //        errorUsername.setText("");
    }//GEN-LAST:event_patientIDMouseReleased

    private void patientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDActionPerformed

    private void cancelPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseEntered
        cancelPanel.setBackground(Hover);
    }//GEN-LAST:event_cancelPanelMouseEntered

    private void cancelPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseExited
         cancelPanel.setBackground(Nav);
    }//GEN-LAST:event_cancelPanelMouseExited

    private void cancelPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseClicked
        Admin_Appointment app = new Admin_Appointment();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelPanelMouseClicked

    private void proceedPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proceedPanelMouseEntered
        proceedPanel.setBackground(Hover);
    }//GEN-LAST:event_proceedPanelMouseEntered

    private void proceedPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proceedPanelMouseExited
        proceedPanel.setBackground(Nav);
    }//GEN-LAST:event_proceedPanelMouseExited

    private void proceedPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proceedPanelMouseClicked
        // Step 1: Validate all fields
        if (firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty() ||
            phone.getText().trim().isEmpty() || birth.getDate() == null) {
            JOptionPane.showMessageDialog(this, "⚠ Please fill all fields before proceeding.");
            return;
        }

        try {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            // Step 2: Check duplicate patients (same fname, lname, birthdate)
            String checkQuery = "SELECT patient_id FROM patients WHERE p_fname = ? AND p_lname = ? AND p_dob = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, firstName.getText().trim());
            checkStmt.setString(2, lastName.getText().trim());

            java.sql.Date dob = new java.sql.Date(birth.getDate().getTime());
            checkStmt.setDate(3, dob);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Patient already exists
                int existingPatientId = rs.getInt("patient_id");

                // Save patient info into session
                Session.getInstance().setPatient(existingPatientId,
                    firstName.getText().trim(),
                    lastName.getText().trim(),
                    (String) gender.getSelectedItem(),
                    new SimpleDateFormat("yyyy-MM-dd").format(birth.getDate()),
                    phone.getText().trim()
                );

                JOptionPane.showMessageDialog(this, "✅ Patient already exists. Proceeding to Appointment Form...");
            } else {
                // Step 3: Insert new patient
                String insertQuery = "INSERT INTO patients (p_fname, p_lname, p_contactNumber, p_gender, p_dob) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);

                insertStmt.setString(1, firstName.getText().trim());
                insertStmt.setString(2, lastName.getText().trim());
                insertStmt.setString(3, phone.getText().trim());
                insertStmt.setString(4, (String) gender.getSelectedItem());
                insertStmt.setDate(5, dob);

                int inserted = insertStmt.executeUpdate();

                if (inserted > 0) {
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int newPatientId = generatedKeys.getInt(1);

                        // Save new patient info into session
                        Session.getInstance().setPatient(newPatientId,
                            firstName.getText().trim(),
                            lastName.getText().trim(),
                            (String) gender.getSelectedItem(),
                            new SimpleDateFormat("yyyy-MM-dd").format(birth.getDate()),
                            phone.getText().trim()
                        );

                        JOptionPane.showMessageDialog(this, "✅ New patient added. Proceeding to Appointment Form...");
                    }
                }
            }

            // Step 4: Open the Appointment Form
            Admin_Appointment_Add appointmentForm = new Admin_Appointment_Add();
            appointmentForm.setVisible(true);

            this.dispose(); // Close the patient form

            rs.close();
            checkStmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage());
        }
    }//GEN-LAST:event_proceedPanelMouseClicked


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
            java.util.logging.Logger.getLogger(Admin_Appointment_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Appointment_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Appointment_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Appointment_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Appointment_Patient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account2;
    private javax.swing.JLabel appointment;
    private javax.swing.JLabel appointment1;
    private javax.swing.JLabel appointment2;
    private javax.swing.JLabel appointment3;
    private javax.swing.JLabel appointment6;
    private javax.swing.JLabel appointment7;
    private javax.swing.JPanel appointment_header;
    private com.toedter.calendar.JDateChooser birth;
    private javax.swing.JPanel cancelPanel;
    private javax.swing.JLabel date1;
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
    private javax.swing.JTextField patientID;
    private javax.swing.JTextField phone;
    private javax.swing.JPanel proceedPanel;
    // End of variables declaration//GEN-END:variables
}
