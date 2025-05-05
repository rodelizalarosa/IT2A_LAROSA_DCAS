
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Admin_Add_Patient extends javax.swing.JFrame {


    public Admin_Add_Patient() {
        initComponents();
       
    }
    
    public void setFieldsEditable(boolean editable) {
        firstName.setEditable(editable);
        lastName.setEditable(editable);
        phone.setEditable(editable);
        gender.setEnabled(editable);
        birth.setEnabled(editable);

        // Patient ID is always not editable
        middleName.setEditable(false);
    }
    
    public void clearForm() {
        middleName.setText("");
        middleName.setEnabled(false);  // still keep it disabled since there's no user bound
        firstName.setText("");
        lastName.setText("");
        gender.setSelectedIndex(0);  // reset to default option (usually "Select Gender" or similar)
        birth.setDate(null);
        phone.setText("");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Register a Patient");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 210, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Fill out the necessary patient details.");
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
        jLabel2.setText("Fill out patient details to register.");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 620, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 50));

        appointment2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment2.setForeground(new java.awt.Color(51, 51, 51));
        appointment2.setText("First Name");
        jPanel2.add(appointment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 90, 30));

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
        jPanel2.add(firstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 250, 30));

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
        jPanel2.add(lastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 250, 30));

        appointment3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment3.setForeground(new java.awt.Color(51, 51, 51));
        appointment3.setText("Last Name");
        jPanel2.add(appointment3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 160, 30));

        appointment7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment7.setForeground(new java.awt.Color(51, 51, 51));
        appointment7.setText("Phone Number");
        jPanel2.add(appointment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 160, 30));

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
        jPanel2.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 250, 30));

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
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 250, 30));

        appointment6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment6.setForeground(new java.awt.Color(51, 51, 51));
        appointment6.setText("Gender");
        jPanel2.add(appointment6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 160, 30));

        date1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        date1.setForeground(new java.awt.Color(51, 51, 51));
        date1.setText("Date of Birth");
        jPanel2.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 160, 30));

        birth.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 250, 30));

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
        jPanel2.add(middleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 250, 30));

        appointment1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment1.setForeground(new java.awt.Color(51, 51, 51));
        appointment1.setText("Middle Name");
        jPanel2.add(appointment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 130, 30));

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

        jPanel2.add(savePatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 90, 30));

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

        jPanel2.add(cancelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, 90, 30));

        appointment8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment8.setForeground(new java.awt.Color(51, 51, 51));
        appointment8.setText("Email");
        jPanel2.add(appointment8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 160, 30));

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
        jPanel2.add(emailAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 250, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 650, 470));

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

    private void middleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middleNameFocusLost

    }//GEN-LAST:event_middleNameFocusLost

    private void middleNameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_middleNameMouseReleased
      
    }//GEN-LAST:event_middleNameMouseReleased

    private void middleNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_middleNameActionPerformed

    private void cancelPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseEntered
        cancelPanel.setBackground(Hover);
    }//GEN-LAST:event_cancelPanelMouseEntered

    private void cancelPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseExited
         cancelPanel.setBackground(Nav);
    }//GEN-LAST:event_cancelPanelMouseExited

    private void cancelPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelPanelMouseClicked
        Admin_Patient_Internal app = new Admin_Patient_Internal();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelPanelMouseClicked

    private void savePatientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePatientMouseEntered
        savePatient.setBackground(Hover);
    }//GEN-LAST:event_savePatientMouseEntered

    private void savePatientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePatientMouseExited
        savePatient.setBackground(Nav);
    }//GEN-LAST:event_savePatientMouseExited

    private void savePatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePatientMouseClicked
         // Step 1: Validate required fields
        if (firstName.getText().trim().isEmpty() ||
            lastName.getText().trim().isEmpty() ||
            phone.getText().trim().isEmpty() ||
            birth.getDate() == null) {

            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please fill in all required fields.</b><br>First name, last name, contact number, and birth date are required.</html>",
                "⚠️ Missing Fields",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            // Step 2: Check for existing patient
            String checkQuery = "SELECT patient_id FROM patients WHERE p_fname = ? AND p_lname = ? AND p_dob = ? AND p_status = 'Active'";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, firstName.getText().trim());
            checkStmt.setString(2, lastName.getText().trim());
            java.sql.Date dob = new java.sql.Date(birth.getDate().getTime());
            checkStmt.setDate(3, dob);

            ResultSet rs = checkStmt.executeQuery();

            int patientId = -1;
            if (rs.next()) {
                // Existing patient
                patientId = rs.getInt("patient_id");
                Session.getInstance().setPatient(
                    patientId,
                    firstName.getText().trim(),
                    lastName.getText().trim(),
                    (String) gender.getSelectedItem(),
                    new SimpleDateFormat("yyyy-MM-dd").format(birth.getDate()),
                    phone.getText().trim()
                );

                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>Patient already exists.</b><br>You can now proceed to make an appointment.</html>",
                    "ℹ️ Existing Patient",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // Step 3: Insert new patient
                String insertQuery = "INSERT INTO patients (p_fname, p_mname, p_lname, p_gender, p_dob, p_contactNumber, p_email, p_status) VALUES (?, ?, ?, ?, ?, ?, ?, 'Active')";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);

                insertStmt.setString(1, firstName.getText().trim());
                insertStmt.setString(2, middleName.getText().trim().isEmpty() ? null : middleName.getText().trim());
                insertStmt.setString(3, lastName.getText().trim());
                insertStmt.setString(4, (String) gender.getSelectedItem());
                insertStmt.setDate(5, dob);
                insertStmt.setString(6, phone.getText().trim());
                insertStmt.setString(7, emailAdd.getText().trim().isEmpty() ? null : emailAdd.getText().trim());

                int inserted = insertStmt.executeUpdate();
                if (inserted > 0) {
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        patientId = generatedKeys.getInt(1);
                        Session.getInstance().setPatient(
                            patientId,
                            firstName.getText().trim(),
                            lastName.getText().trim(),
                            (String) gender.getSelectedItem(),
                            new SimpleDateFormat("yyyy-MM-dd").format(birth.getDate()),
                            phone.getText().trim()
                        );

                        Session.getInstance().logEvent("ADDED NEW PATIENT", "Admin added patient ID: " + patientId);
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>New patient added successfully!</b></html>",
                            "✅ Success",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                    generatedKeys.close();
                }
                insertStmt.close();
            }

            rs.close();
            checkStmt.close();
            conn.close();

            // Step 4: Ask to proceed with booking
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Proceed to booking?</b><br>Do you want to set an appointment for this patient now?</html>",
                "📅 Confirm Booking",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                new Admin_Appointment_Add().setVisible(true);
                this.dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "<html><b>❌ Error occurred:</b><br>" + e.getMessage() + "</html>",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_savePatientMouseClicked

    private void emailAddFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailAddFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_emailAddFocusLost

    private void emailAddMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailAddMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_emailAddMouseReleased

    private void emailAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailAddActionPerformed


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
            java.util.logging.Logger.getLogger(Admin_Add_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Add_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Add_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Add_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Add_Patient().setVisible(true);
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
    private javax.swing.JTextField phone;
    private javax.swing.JPanel savePatient;
    // End of variables declaration//GEN-END:variables
}
