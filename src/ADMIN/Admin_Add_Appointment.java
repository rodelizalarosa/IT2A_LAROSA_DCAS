
package ADMIN;

import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Add_Appointment extends javax.swing.JInternalFrame {


    public Admin_Add_Appointment() {
        initComponents();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        appointment_header = new javax.swing.JPanel();
        appointment = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        account2 = new javax.swing.JLabel();
        appointment1 = new javax.swing.JLabel();
        patientID = new javax.swing.JTextField();
        appointment2 = new javax.swing.JLabel();
        fullName = new javax.swing.JTextField();
        appointment4 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        appointment5 = new javax.swing.JLabel();
        time = new javax.swing.JTextField();
        appointment6 = new javax.swing.JLabel();
        time1 = new javax.swing.JTextField();
        appointment7 = new javax.swing.JLabel();
        category = new javax.swing.JComboBox<>();
        appointment8 = new javax.swing.JLabel();
        services = new javax.swing.JComboBox<>();
        appointment9 = new javax.swing.JLabel();
        appointment10 = new javax.swing.JLabel();
        time3 = new javax.swing.JTextField();
        appointment11 = new javax.swing.JLabel();
        notes = new javax.swing.JTextField();
        appointment12 = new javax.swing.JLabel();
        services1 = new javax.swing.JComboBox<>();
        appointment3 = new javax.swing.JLabel();
        fullName1 = new javax.swing.JTextField();
        bookPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Add Appointment");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 50));

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

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Manage and add an appointment for existing patient account.");
        appointment_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 380, 50));

        jPanel1.add(appointment_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

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

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 40));

        appointment1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment1.setForeground(new java.awt.Color(51, 51, 51));
        appointment1.setText("Patient ID:");
        jPanel2.add(appointment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 130, 30));

        patientID.setBackground(new java.awt.Color(204, 204, 204));
        patientID.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        patientID.setForeground(new java.awt.Color(255, 255, 255));
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
        appointment2.setText("First Name");
        jPanel2.add(appointment2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 90, 30));

        fullName.setBackground(new java.awt.Color(204, 204, 204));
        fullName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        fullName.setForeground(new java.awt.Color(255, 255, 255));
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

        appointment4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment4.setForeground(new java.awt.Color(51, 51, 51));
        appointment4.setText("Category");
        jPanel2.add(appointment4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 160, 30));

        phone.setBackground(new java.awt.Color(204, 204, 204));
        phone.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        phone.setForeground(new java.awt.Color(255, 255, 255));
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

        appointment5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment5.setForeground(new java.awt.Color(51, 51, 51));
        appointment5.setText("Preferred Time");
        jPanel2.add(appointment5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 160, 30));

        time.setBackground(new java.awt.Color(204, 204, 204));
        time.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
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
        jPanel2.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 220, 30));

        appointment6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment6.setForeground(new java.awt.Color(51, 51, 51));
        appointment6.setText("Preferred Date");
        jPanel2.add(appointment6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 160, 30));

        time1.setBackground(new java.awt.Color(204, 204, 204));
        time1.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        time1.setForeground(new java.awt.Color(255, 255, 255));
        time1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                time1FocusLost(evt);
            }
        });
        time1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                time1MouseReleased(evt);
            }
        });
        time1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time1ActionPerformed(evt);
            }
        });
        jPanel2.add(time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 220, 30));

        appointment7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment7.setForeground(new java.awt.Color(51, 51, 51));
        appointment7.setText("Phone Number");
        jPanel2.add(appointment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 160, 30));

        category.setBackground(new java.awt.Color(204, 204, 204));
        category.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        category.setForeground(new java.awt.Color(204, 204, 204));
        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category", "General", "Orthodontics", " " }));
        category.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                categoryFocusLost(evt);
            }
        });
        jPanel2.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 250, 30));

        appointment8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment8.setForeground(new java.awt.Color(51, 51, 51));
        appointment8.setText("Dental Services");
        jPanel2.add(appointment8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 160, 30));

        services.setBackground(new java.awt.Color(204, 204, 204));
        services.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        services.setForeground(new java.awt.Color(204, 204, 204));
        services.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Dentist" }));
        services.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                servicesFocusLost(evt);
            }
        });
        jPanel2.add(services, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 220, 30));

        appointment9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment9.setForeground(new java.awt.Color(51, 51, 51));
        appointment9.setText("Preferred Dentist");
        jPanel2.add(appointment9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 160, 30));

        appointment10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment10.setForeground(new java.awt.Color(51, 51, 51));
        appointment10.setText("Preferred Date");
        jPanel2.add(appointment10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 160, 30));

        time3.setBackground(new java.awt.Color(204, 204, 204));
        time3.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        time3.setForeground(new java.awt.Color(255, 255, 255));
        time3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                time3FocusLost(evt);
            }
        });
        time3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                time3MouseReleased(evt);
            }
        });
        time3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time3ActionPerformed(evt);
            }
        });
        jPanel2.add(time3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 180, 30));

        appointment11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment11.setForeground(new java.awt.Color(51, 51, 51));
        appointment11.setText("or Notes");
        jPanel2.add(appointment11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 160, 30));

        notes.setBackground(new java.awt.Color(204, 204, 204));
        notes.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        notes.setForeground(new java.awt.Color(255, 255, 255));
        notes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                notesFocusLost(evt);
            }
        });
        notes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                notesMouseReleased(evt);
            }
        });
        notes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notesActionPerformed(evt);
            }
        });
        jPanel2.add(notes, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 220, 80));

        appointment12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment12.setForeground(new java.awt.Color(51, 51, 51));
        appointment12.setText("Special Requests");
        jPanel2.add(appointment12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, 160, 30));

        services1.setBackground(new java.awt.Color(204, 204, 204));
        services1.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        services1.setForeground(new java.awt.Color(204, 204, 204));
        services1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Dental Services", "Consultation", "Cleaning", "Tooth Extraction", "Root Canal", "Wisdom Tooth Removal", "Braces", "Retainers" }));
        services1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                services1FocusLost(evt);
            }
        });
        jPanel2.add(services1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 250, 30));

        appointment3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment3.setForeground(new java.awt.Color(51, 51, 51));
        appointment3.setText("Last Name");
        jPanel2.add(appointment3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 160, 30));

        fullName1.setBackground(new java.awt.Color(204, 204, 204));
        fullName1.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        fullName1.setForeground(new java.awt.Color(255, 255, 255));
        fullName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fullName1FocusLost(evt);
            }
        });
        fullName1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fullName1MouseReleased(evt);
            }
        });
        fullName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullName1ActionPerformed(evt);
            }
        });
        jPanel2.add(fullName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 250, 30));

        bookPanel.setBackground(new java.awt.Color(0, 51, 51));
        bookPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Book an Appointment");
        bookPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 30));

        jPanel2.add(bookPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 220, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 810, 400));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Admin_Appointment app = new Admin_Appointment();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
     
    }//GEN-LAST:event_backMouseEntered

    private void patientIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientIDFocusLost

    }//GEN-LAST:event_patientIDFocusLost

    private void patientIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientIDMouseReleased
        //        username.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12), new java.awt.Color(0, 0, 0)));
        //        errorUsername.setText("");
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

    private void phoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneFocusLost

    private void phoneMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phoneMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneMouseReleased

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void timeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFocusLost

    private void timeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_timeMouseReleased

    private void timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeActionPerformed

    private void time1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_time1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_time1FocusLost

    private void time1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_time1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_time1MouseReleased

    private void time1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_time1ActionPerformed

    private void categoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_categoryFocusLost

    }//GEN-LAST:event_categoryFocusLost

    private void servicesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_servicesFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_servicesFocusLost

    private void time3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_time3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_time3FocusLost

    private void time3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_time3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_time3MouseReleased

    private void time3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_time3ActionPerformed

    private void notesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notesFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_notesFocusLost

    private void notesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notesMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_notesMouseReleased

    private void notesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notesActionPerformed

    private void services1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_services1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_services1FocusLost

    private void fullName1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fullName1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_fullName1FocusLost

    private void fullName1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullName1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fullName1MouseReleased

    private void fullName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullName1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account2;
    private javax.swing.JLabel appointment;
    private javax.swing.JLabel appointment1;
    private javax.swing.JLabel appointment10;
    private javax.swing.JLabel appointment11;
    private javax.swing.JLabel appointment12;
    private javax.swing.JLabel appointment2;
    private javax.swing.JLabel appointment3;
    private javax.swing.JLabel appointment4;
    private javax.swing.JLabel appointment5;
    private javax.swing.JLabel appointment6;
    private javax.swing.JLabel appointment7;
    private javax.swing.JLabel appointment8;
    private javax.swing.JLabel appointment9;
    private javax.swing.JPanel appointment_header;
    private javax.swing.JLabel back;
    private javax.swing.JPanel bookPanel;
    private javax.swing.JComboBox<String> category;
    private javax.swing.JTextField fullName;
    private javax.swing.JTextField fullName1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField notes;
    private javax.swing.JTextField patientID;
    private javax.swing.JTextField phone;
    private javax.swing.JComboBox<String> services;
    private javax.swing.JComboBox<String> services1;
    private javax.swing.JTextField time;
    private javax.swing.JTextField time1;
    private javax.swing.JTextField time3;
    // End of variables declaration//GEN-END:variables
}
