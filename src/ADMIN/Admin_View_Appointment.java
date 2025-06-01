
package ADMIN;

import AUTHENTICATION.EmailSender;
import Config.ConnectDB;
import Config.Session;
import PRINT.AppointmentSlip;
import PRINT.Decline_Reason;
import PRINT.GenerateSlip;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class Admin_View_Appointment extends javax.swing.JFrame {

    public Admin_View_Appointment() {
        initComponents();
        loadSessionData(); // Custom method to prefill fields from session
    }

    private void loadSessionData() {
      Session session = Session.getInstance();
        if (session.getPatientId() == 0 || session.getAppointmentId() == 0) {
            System.out.println("Session is empty or not set.");
            return;
        }

        // Gray background and border for IDs
        Color grayBg = new Color(240, 240, 240);
        Border lightBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

        // patientID
        patientID.setText(String.valueOf(session.getPatientId()));
        patientID.setEditable(false);
        patientID.setBackground(grayBg);
        patientID.setBorder(lightBorder);

        // appointmentID
        appointmentID.setText(String.valueOf(session.getAppointmentId()));
        appointmentID.setEditable(false);
        appointmentID.setBackground(grayBg);
        appointmentID.setBorder(lightBorder);

        // Other fields (editable false, keep default styling)
        fullName.setText(session.getPatientFirstName() + " " + session.getPatientLastName());
        fullName.setEditable(false);

        gender.setText(session.getPatientGender());
        gender.setEditable(false);

        phone.setText(session.getPatientContact());
        phone.setEditable(false);

        services.setText(session.getServiceNameList());
        services.setEditable(false);

        time.setText(session.getAppointmentTime());
        time.setEditable(false);

        date.setText(session.getAppointmentDate());
        date.setEditable(false);

        dentist.setText(session.getDentistFullName());
        dentist.setEditable(false);

        notes.setText(session.getAppointmentNotes());
        notes.setEditable(false);
    }
    
    

    
    Color Hover = new Color (55,162,153);
    Color Nav = new Color (0,51,51);


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        appointment_header = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        appointment1 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        account2 = new javax.swing.JLabel();
        appointment = new javax.swing.JLabel();
        patientID = new javax.swing.JTextField();
        appointment2 = new javax.swing.JLabel();
        fullName = new javax.swing.JTextField();
        appointmentID = new javax.swing.JTextField();
        appointment3 = new javax.swing.JLabel();
        gender = new javax.swing.JTextField();
        appointment5 = new javax.swing.JLabel();
        time = new javax.swing.JTextField();
        appointment6 = new javax.swing.JLabel();
        dentist = new javax.swing.JTextField();
        appointment7 = new javax.swing.JLabel();
        appointment8 = new javax.swing.JLabel();
        appointment9 = new javax.swing.JLabel();
        appointment10 = new javax.swing.JLabel();
        appointment11 = new javax.swing.JLabel();
        appointment12 = new javax.swing.JLabel();
        appointment13 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        notes = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        services = new javax.swing.JTextArea();
        approvePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        declinePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        generateSlip = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        updatePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Manage the appointment status of an appointment.");
        appointment_header.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 320, 30));

        appointment1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment1.setForeground(new java.awt.Color(255, 255, 255));
        appointment1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appointment1.setText("View an Appointment");
        appointment_header.add(appointment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 270, 30));

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
        });
        appointment_header.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel1.add(appointment_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(55, 162, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        account2.setForeground(new java.awt.Color(255, 255, 255));
        account2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account2.setText("APPOINTMENT DETAILS");
        jPanel9.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 730, 40));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 40));

        appointment.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment.setForeground(new java.awt.Color(51, 51, 51));
        appointment.setText("Patient ID:");
        jPanel2.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 130, 30));

        patientID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        patientID.setForeground(new java.awt.Color(51, 51, 51));
        patientID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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
        jPanel2.add(patientID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 100, 30));

        appointment2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment2.setForeground(new java.awt.Color(51, 51, 51));
        appointment2.setText("Full Name");
        jPanel2.add(appointment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 160, 30));

        fullName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        fullName.setForeground(new java.awt.Color(51, 51, 51));
        fullName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fullNameFocusLost(evt);
            }
        });
        fullName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fullNameMouseReleased(evt);
            }
        });
        fullName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullNameActionPerformed(evt);
            }
        });
        jPanel2.add(fullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 250, 30));

        appointmentID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointmentID.setForeground(new java.awt.Color(51, 51, 51));
        appointmentID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        appointmentID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                appointmentIDFocusLost(evt);
            }
        });
        appointmentID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                appointmentIDMouseReleased(evt);
            }
        });
        appointmentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentIDActionPerformed(evt);
            }
        });
        jPanel2.add(appointmentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 100, 30));

        appointment3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment3.setForeground(new java.awt.Color(51, 51, 51));
        appointment3.setText("Appointment ID:");
        jPanel2.add(appointment3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 160, 30));

        gender.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        gender.setForeground(new java.awt.Color(51, 51, 51));
        gender.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                genderFocusLost(evt);
            }
        });
        gender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                genderMouseReleased(evt);
            }
        });
        gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderActionPerformed(evt);
            }
        });
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 250, 30));

        appointment5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment5.setForeground(new java.awt.Color(51, 51, 51));
        appointment5.setText("Preferred Time");
        jPanel2.add(appointment5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 160, 30));

        time.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        time.setForeground(new java.awt.Color(51, 51, 51));
        time.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                timeFocusLost(evt);
            }
        });
        time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                timeMouseReleased(evt);
            }
        });
        time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeActionPerformed(evt);
            }
        });
        jPanel2.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 200, 30));

        appointment6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment6.setForeground(new java.awt.Color(51, 51, 51));
        appointment6.setText("Preferred Date");
        jPanel2.add(appointment6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 160, 30));

        dentist.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        dentist.setForeground(new java.awt.Color(51, 51, 51));
        dentist.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dentistFocusLost(evt);
            }
        });
        dentist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dentistMouseReleased(evt);
            }
        });
        dentist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dentistActionPerformed(evt);
            }
        });
        jPanel2.add(dentist, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 200, 30));

        appointment7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment7.setForeground(new java.awt.Color(51, 51, 51));
        appointment7.setText("Gender");
        jPanel2.add(appointment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 160, 30));

        appointment8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment8.setForeground(new java.awt.Color(51, 51, 51));
        appointment8.setText("Dental Services");
        jPanel2.add(appointment8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 160, 30));

        appointment9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment9.setForeground(new java.awt.Color(51, 51, 51));
        appointment9.setText("Preferred Dentist");
        jPanel2.add(appointment9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 160, 30));

        appointment10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment10.setForeground(new java.awt.Color(51, 51, 51));
        appointment10.setText("Preferred Date");
        jPanel2.add(appointment10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 160, 30));

        appointment11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment11.setForeground(new java.awt.Color(51, 51, 51));
        appointment11.setText("or Notes");
        jPanel2.add(appointment11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 160, 30));

        appointment12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment12.setForeground(new java.awt.Color(51, 51, 51));
        appointment12.setText("Special Requests");
        jPanel2.add(appointment12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, 160, 30));

        appointment13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment13.setForeground(new java.awt.Color(51, 51, 51));
        appointment13.setText("Phone Number");
        jPanel2.add(appointment13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 160, 30));

        date.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        date.setForeground(new java.awt.Color(51, 51, 51));
        date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateFocusLost(evt);
            }
        });
        date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dateMouseReleased(evt);
            }
        });
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });
        jPanel2.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 200, 30));

        phone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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
        jPanel2.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 250, 30));

        notes.setColumns(20);
        notes.setRows(5);
        notes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setViewportView(notes);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 200, -1));

        services.setColumns(20);
        services.setRows(5);
        services.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane2.setViewportView(services);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 250, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 790, 370));

        approvePanel.setBackground(new java.awt.Color(0, 51, 51));
        approvePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                approvePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                approvePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                approvePanelMouseExited(evt);
            }
        });
        approvePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("APPROVE");
        approvePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel1.add(approvePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 90, 30));

        declinePanel.setBackground(new java.awt.Color(0, 51, 51));
        declinePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                declinePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                declinePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                declinePanelMouseExited(evt);
            }
        });
        declinePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DECLINE");
        declinePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel1.add(declinePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 90, 30));

        generateSlip.setBackground(new java.awt.Color(0, 51, 51));
        generateSlip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generateSlipMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                generateSlipMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                generateSlipMouseExited(evt);
            }
        });
        generateSlip.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("GENERATE SLIP");
        generateSlip.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 120, 30));

        jPanel1.add(generateSlip, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 140, 30));

        updatePanel.setBackground(new java.awt.Color(0, 51, 51));
        updatePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePanelMouseExited(evt);
            }
        });
        updatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("UPDATE");
        updatePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel1.add(updatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void patientIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientIDFocusLost

    }//GEN-LAST:event_patientIDFocusLost

    private void patientIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientIDMouseReleased
        
    }//GEN-LAST:event_patientIDMouseReleased

    private void patientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDActionPerformed

    private void fullNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fullNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_fullNameFocusLost

    private void fullNameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullNameMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fullNameMouseReleased

    private void fullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullNameActionPerformed

    private void appointmentIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_appointmentIDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentIDFocusLost

    private void appointmentIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentIDMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentIDMouseReleased

    private void appointmentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentIDActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Admin_Appointment app = new Admin_Appointment();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered

    }//GEN-LAST:event_backMouseEntered

    private void generateSlipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateSlipMouseClicked
        int appointmentId = Session.getInstance().getAppointmentId();

        if (appointmentId != -1) {
           try {
               Connection conn = new ConnectDB().getConnection();

               String statusQuery = "SELECT a_status FROM appointments WHERE appointment_id = ?";
               PreparedStatement statusStmt = conn.prepareStatement(statusQuery);
               statusStmt.setInt(1, appointmentId);
               ResultSet rs = statusStmt.executeQuery();

               if (rs.next()) {
                   String status = rs.getString("a_status");

                   if ("Confirmed".equalsIgnoreCase(status)) {
                       new AppointmentSlip(appointmentId).setVisible(true); // ✅ Correct
                   } else {
                       JOptionPane.showMessageDialog(
                           this,
                           "<html><b>Cannot generate appointment slip.</b><br>Status is currently <b>" + status + "</b>.</html>",
                           "❌ Invalid Status",
                           JOptionPane.WARNING_MESSAGE
                       );
                   }
               } else {
                   JOptionPane.showMessageDialog(
                       this,
                       "<html><b>Appointment not found.</b></html>",
                       "❌ Error",
                       JOptionPane.ERROR_MESSAGE
                   );
               }

                rs.close();
                statusStmt.close();
                conn.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>Database error:</b><br>" + e.getMessage() + "</html>",
                    "❗ SQL Error",
                    JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Appointment ID not found.</b><br>Unable to generate appointment slip.</html>",
                "❌ Missing Data",
                JOptionPane.WARNING_MESSAGE
            );
        }

//                    int appointmentId = Session.getInstance().getAppointmentId();
//
//                       if (appointmentId != -1) {
//                           // Check the appointment status (optional)
//                           try (Connection conn = new ConnectDB().getConnection()) {
//                               String statusQuery = "SELECT a_status FROM appointments WHERE appointment_id = ?";
//                               try (PreparedStatement statusStmt = conn.prepareStatement(statusQuery)) {
//                                   statusStmt.setInt(1, appointmentId);
//                                   try (ResultSet rs = statusStmt.executeQuery()) {
//                                       if (rs.next()) {
//                                           String status = rs.getString("a_status");
//
//                                           // Only allow slip generation for confirmed appointments
//                                           if ("Confirmed".equalsIgnoreCase(status)) {
//                                                GenerateSlip slipFrame = new GenerateSlip(appointmentId);
//                                                slipFrame.setVisible(true);
//                                           } else {
//                                               // Using HTML formatting in JOptionPane
//                                               JOptionPane.showMessageDialog(this,
//                                                   "<html><b>Cannot generate appointment slip.</b><br>" +
//                                                   "The appointment status is currently: <span style='color:red;'>" + status + "</span></html>",
//                                                   "❌ Invalid Status", JOptionPane.WARNING_MESSAGE);
//                                           }
//                                       }
//                                   }
//                               }
//
//                           } catch (SQLException e) {
//                               // Using HTML formatting in JOptionPane for errors
//                               JOptionPane.showMessageDialog(this,
//                                   "<html><b>Error checking appointment status:</b><br>" + e.getMessage() + "</html>",
//                                   "❌ Error", JOptionPane.ERROR_MESSAGE);
//                           }
//                       } else {
//                           // Using HTML formatting for missing appointment ID
//                           JOptionPane.showMessageDialog(this,
//                               "<html><b>Appointment ID not found.</b><br>Unable to generate appointment slip.</html>",
//                               "❌ Missing Data", JOptionPane.WARNING_MESSAGE);
//                       }
    }//GEN-LAST:event_generateSlipMouseClicked

    private void approvePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approvePanelMouseEntered
        approvePanel.setBackground(Hover);
    }//GEN-LAST:event_approvePanelMouseEntered

    private void approvePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approvePanelMouseExited
        approvePanel.setBackground(Nav);
    }//GEN-LAST:event_approvePanelMouseExited

    private void declinePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declinePanelMouseEntered
        declinePanel.setBackground(Hover);
    }//GEN-LAST:event_declinePanelMouseEntered

    private void declinePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declinePanelMouseExited
        declinePanel.setBackground(Nav);
    }//GEN-LAST:event_declinePanelMouseExited

    private void updatePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseEntered
        updatePanel.setBackground(Hover);
    }//GEN-LAST:event_updatePanelMouseEntered

    private void updatePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseExited
        updatePanel.setBackground(Nav);
    }//GEN-LAST:event_updatePanelMouseExited

    private void generateSlipMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateSlipMouseEntered
        generateSlip.setBackground(Hover);
    }//GEN-LAST:event_generateSlipMouseEntered

    private void generateSlipMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateSlipMouseExited
        generateSlip.setBackground(Nav);
    }//GEN-LAST:event_generateSlipMouseExited

    private void genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderActionPerformed

    private void genderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genderMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_genderMouseReleased

    private void genderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genderFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_genderFocusLost

    private void timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeActionPerformed

    private void timeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_timeMouseReleased

    private void timeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFocusLost

    private void dentistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dentistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistActionPerformed

    private void dentistMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dentistMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistMouseReleased

    private void dentistFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dentistFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistFocusLost

    private void dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFocusLost

    private void dateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dateMouseReleased

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void phoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneFocusLost

    private void phoneMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phoneMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneMouseReleased

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void approvePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approvePanelMouseClicked
        int appointmentId = Session.getInstance().getAppointmentId();

        if (appointmentId != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Are you sure you want to approve this appointment?</b><br>Status will be updated to <b>Confirmed</b>.</html>",
                "✅ Confirm Approval",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = new ConnectDB().getConnection();

                    // Check current appointment status first
                    String checkStatusQuery = "SELECT a_status FROM appointments WHERE appointment_id = ?";
                    PreparedStatement checkStmt = conn.prepareStatement(checkStatusQuery);
                    checkStmt.setInt(1, appointmentId);
                    ResultSet statusRs = checkStmt.executeQuery();

                    if (statusRs.next()) {
                        String currentStatus = statusRs.getString("a_status");

                        if (!"Pending".equalsIgnoreCase(currentStatus)) {
                            JOptionPane.showMessageDialog(
                                this,
                                "<html><b>Cannot approve this appointment.</b><br>Status is already <b>" + currentStatus + "</b>.</html>",
                                "❌ Approval Blocked",
                                JOptionPane.WARNING_MESSAGE
                            );
                            statusRs.close();
                            checkStmt.close();
                            conn.close();
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Appointment not found.</b></html>",
                            "❌ Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                        statusRs.close();
                        checkStmt.close();
                        conn.close();
                        return;
                    }

                    statusRs.close();
                    checkStmt.close();

                    // Proceed with update
                    String updateQuery = "UPDATE appointments SET a_status = 'Confirmed' WHERE appointment_id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                    pstmt.setInt(1, appointmentId);

                    int rowsUpdated = pstmt.executeUpdate();
                    pstmt.close();

                    if (rowsUpdated > 0) {
                        // Get appointment and patient info
String infoQuery = "SELECT u.u_email, CONCAT(p.p_fname, ' ', p.p_lname) AS full_name, a.pref_date AS a_date, a.pref_time AS a_time " +
                   "FROM appointments a " +
                   "JOIN patients p ON a.patient_id = p.patient_id " +
                   "JOIN users u ON p.user_id = u.user_id " +
                   "WHERE a.appointment_id = ?";
                        PreparedStatement infoStmt = conn.prepareStatement(infoQuery);
                        infoStmt.setInt(1, appointmentId);
                        ResultSet rs = infoStmt.executeQuery();

                        if (rs.next()) {
                            String toEmail = rs.getString("u_email");
                            String patientName = rs.getString("full_name");
                            String appointmentDate = rs.getString("a_date");
                            String appointmentTime = rs.getString("a_time");

                            try {
                                EmailSender.sendAppointmentApproval(toEmail, patientName, appointmentDate, appointmentTime);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(
                                    this,
                                    "<html><b>Appointment approved, but failed to send email:</b><br>" + e.getMessage() + "</html>",
                                    "✉️ Email Error",
                                    JOptionPane.WARNING_MESSAGE
                                );
                            }
                        }

                        rs.close();
                        infoStmt.close();

                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Appointment approved successfully!</b><br>Status is now <b>Confirmed</b>.</html>",
                            "✅ Approved",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Approval failed.</b><br>Unable to update appointment.</html>",
                            "Update Failed",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }

                    conn.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>Database error:</b><br>" + e.getMessage() + "</html>",
                        "❗ SQL Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Appointment ID not found.</b><br>Unable to proceed with approval.</html>",
                "Missing ID",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_approvePanelMouseClicked

private void declinePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_declinePanelMouseClicked
        int appointmentId = Session.getInstance().getAppointmentId();

        if (appointmentId != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Are you sure you want to decline this appointment?</b><br>You will be asked to provide a reason.</html>",
                "Confirm Decline",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = new ConnectDB().getConnection();

                    // 🔍 First check current status
                    String statusQuery = "SELECT a_status FROM appointments WHERE appointment_id = ?";
                    PreparedStatement statusStmt = conn.prepareStatement(statusQuery);
                    statusStmt.setInt(1, appointmentId);
                    ResultSet statusRs = statusStmt.executeQuery();

                    if (statusRs.next()) {
                        String currentStatus = statusRs.getString("a_status");

                        if (!"Pending".equalsIgnoreCase(currentStatus)) {
                            JOptionPane.showMessageDialog(
                                this,
                                "<html><b>Cannot decline this appointment.</b><br>Status is <b>" + currentStatus + "</b>. Only pending appointments can be declined.</html>",
                                "❌ Decline Blocked",
                                JOptionPane.WARNING_MESSAGE
                            );
                            statusRs.close();
                            statusStmt.close();
                            conn.close();
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "<html><b>Appointment not found.</b></html>", "❌ Error", JOptionPane.ERROR_MESSAGE);
                        statusRs.close();
                        statusStmt.close();
                        conn.close();
                        return;
                    }

                    statusRs.close();
                    statusStmt.close();

                    // 🔄 Update appointment status to "Cancelled"
                    String updateQuery = "UPDATE appointments SET a_status = ? WHERE appointment_id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setString(1, "Cancelled");
                    updateStmt.setInt(2, appointmentId);
                    updateStmt.executeUpdate();
                    updateStmt.close();

                    // 👤 Fetch patient name
                    String fetchQuery = "SELECT p.p_fname, p.p_lname FROM appointments a JOIN patients p ON a.patient_id = p.patient_id WHERE a.appointment_id = ?";
                    PreparedStatement fetchStmt = conn.prepareStatement(fetchQuery);
                    fetchStmt.setInt(1, appointmentId);
                    ResultSet rs = fetchStmt.executeQuery();

                    if (rs.next()) {
                        String fullName = rs.getString("p_fname") + " " + rs.getString("p_lname");

                        // Pass data to Decline_Reason panel
                        Decline_Reason declineReasonPanel = new Decline_Reason(String.valueOf(appointmentId), fullName);
                        declineReasonPanel.setVisible(true);
                        this.setVisible(false);
                    }

                    rs.close();
                    fetchStmt.close();
                    conn.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "<html><b>Error:</b><br>" + e.getMessage() + "</html>", "❗ Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "<html><b>Appointment ID not found.</b></html>", "⚠️ Missing ID", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_declinePanelMouseClicked

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
            java.util.logging.Logger.getLogger(Admin_View_Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_View_Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_View_Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_View_Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_View_Appointment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account2;
    private javax.swing.JLabel appointment;
    private javax.swing.JLabel appointment1;
    private javax.swing.JLabel appointment10;
    private javax.swing.JLabel appointment11;
    private javax.swing.JLabel appointment12;
    private javax.swing.JLabel appointment13;
    private javax.swing.JLabel appointment2;
    private javax.swing.JLabel appointment3;
    private javax.swing.JLabel appointment5;
    private javax.swing.JLabel appointment6;
    private javax.swing.JLabel appointment7;
    private javax.swing.JLabel appointment8;
    private javax.swing.JLabel appointment9;
    private javax.swing.JTextField appointmentID;
    private javax.swing.JPanel appointment_header;
    private javax.swing.JPanel approvePanel;
    private javax.swing.JLabel back;
    private javax.swing.JTextField date;
    private javax.swing.JPanel declinePanel;
    private javax.swing.JTextField dentist;
    private javax.swing.JTextField fullName;
    private javax.swing.JTextField gender;
    private javax.swing.JPanel generateSlip;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea notes;
    private javax.swing.JTextField patientID;
    private javax.swing.JTextField phone;
    private javax.swing.JTextArea services;
    private javax.swing.JTextField time;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables
}
