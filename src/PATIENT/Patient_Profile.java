
package PATIENT;

import Config.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Patient_Profile extends javax.swing.JInternalFrame {

    public Patient_Profile() {
        initComponents();
        loadDefaultImage();
        loadPatientProfile();
        setFieldsUneditable();
        
          //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        
    }
    
    private void editProfile(){
        Patient_Profile_Edit edit = new Patient_Profile_Edit();
        JDesktopPane desktop = Session.getInstance().getDesktopFirst();
        desktop.add(edit);
        edit.setVisible(true);
    }
      
      private void loadPatientProfile() {
        int userId = Session.getInstance().getUserId();

        try (Connection con = ConnectDB.getConnection()) {
            String userSql = "SELECT u_username, u_email, u_image FROM users WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(userSql)) {
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String username = rs.getString("u_username");
                    String email = rs.getString("u_email");
                    String imagePath = rs.getString("u_image");

                    userName.setText(username);
                    Email.setText(email);

                    if (imagePath != null && !imagePath.isEmpty()) {
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                            ImageIcon icon = new ImageIcon(imagePath);
                            int width = image.getWidth();
                                int height = image.getHeight();

                                if (width <= 0 || height <= 0) {
                                    Dimension size = image.getPreferredSize();
                                    width = (size.width > 0) ? size.width : 120;
                                    height = (size.height > 0) ? size.height : 120;
                                }

                                Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                            image.setIcon(new ImageIcon(img));
                        } else {
                            loadDefaultImage();
                        }
                    } else {
                        loadDefaultImage();
                    }
                }
            }

            String patientSql = "SELECT p_fname, p_mname, p_lname, p_contactNumber, p_gender, p_dob FROM patients WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(patientSql)) {
                pst.setInt(1, userId);
                ResultSet rsPatient = pst.executeQuery();

                if (rsPatient.next()) {
                    firstName.setText(rsPatient.getString("p_fname"));
                    middleName.setText(rsPatient.getString("p_mname"));
                    lastName.setText(rsPatient.getString("p_lname"));
                    Phone.setText(rsPatient.getString("p_contactNumber"));
                    Gender.setSelectedItem(rsPatient.getString("p_gender"));

                    String dobString = rsPatient.getString("p_dob");
                    try {
                        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobString);
                        Birth.setDate(dob);
                    } catch (ParseException pe) {
                        Birth.setDate(null);
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Invalid birth date format:</b><br>" + pe.getMessage() + "</html>",
                            "Date Parse Error",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }

                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>⚠️ Patient profile not found.</b><br>Please complete your registration.</html>",
                        "Profile Missing",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>❌ Error loading patient profile:</b><br>" + e.getMessage() + "</html>",
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
      
    private void setFieldsUneditable() {
        firstName.setEditable(false);
        middleName.setEditable(false);
        lastName.setEditable(false);
        userName.setEditable(false);
        Phone.setEditable(false);
        Email.setEditable(false);
        Gender.setEditable(false);
        Birth.setEnabled(false); // For JDateChooser
    }

     
      private String selectedImagePath = null;
      private String currentImageFromDB = null; // the image path from database
      private final String defaultImagePath = "src/default/u_blank.jpg"; // constant
      
    private void loadDefaultImage() {
        SwingUtilities.invokeLater(() -> {
            String defaultImagePath = "src/default/u_blank.jpg"; // make sure this file exists
            File defaultImageFile = new File(defaultImagePath);

            if (!defaultImageFile.exists()) {
                System.err.println("Default image file not found at: " + defaultImagePath);
                return;
            }

            ImageIcon icon = new ImageIcon(defaultImagePath);

            int width = image.getWidth();
            int height = image.getHeight();

            // Fallback if width or height is not valid
            if (width <= 0 || height <= 0) {
                Dimension size = image.getPreferredSize();
                width = size.width > 0 ? size.width : 120;
                height = size.height > 0 ? size.height : 120;
            }

            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            image.setIcon(new ImageIcon(img));

            selectedImagePath = defaultImagePath;
            currentImageFromDB = defaultImagePath;
        });
    }


    Color hover = new Color (0,51,51);
    Color nav = new Color (0,153,153);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        profile_header = new javax.swing.JPanel();
        account = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        account1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        middlename = new javax.swing.JLabel();
        middleName = new javax.swing.JTextField();
        lastname = new javax.swing.JLabel();
        lastName = new javax.swing.JTextField();
        email3 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        email = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        gender = new javax.swing.JLabel();
        Gender = new javax.swing.JComboBox<>();
        edit_profile = new javax.swing.JLabel();
        email1 = new javax.swing.JLabel();
        Phone = new javax.swing.JTextField();
        Birth = new com.toedter.calendar.JDateChooser();
        gender1 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile_header.setBackground(new java.awt.Color(55, 162, 153));
        profile_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        profile_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account.setForeground(new java.awt.Color(255, 255, 255));
        account.setText("Patient Account");
        profile_header.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 150, 50));

        jPanel1.add(profile_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account1.setForeground(new java.awt.Color(255, 255, 255));
        account1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account1.setText("Patient Personal Details");
        jPanel3.add(account1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 410, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("See and manage your personal information.");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 490, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 60));

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 200, 170));

        username2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        username2.setForeground(new java.awt.Color(51, 51, 51));
        username2.setText("First Name");
        jPanel2.add(username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 80, 30));

        firstName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        firstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstNameFocusLost(evt);
            }
        });
        firstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameActionPerformed(evt);
            }
        });
        jPanel2.add(firstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 270, 40));

        middlename.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        middlename.setForeground(new java.awt.Color(51, 51, 51));
        middlename.setText("Middle Name");
        jPanel2.add(middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 100, 30));

        middleName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        middleName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                middleNameFocusLost(evt);
            }
        });
        middleName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middleNameActionPerformed(evt);
            }
        });
        jPanel2.add(middleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 270, 40));

        lastname.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname.setForeground(new java.awt.Color(51, 51, 51));
        lastname.setText("Last Name");
        jPanel2.add(lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 80, 30));

        lastName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        lastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameFocusLost(evt);
            }
        });
        lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameActionPerformed(evt);
            }
        });
        jPanel2.add(lastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 270, 40));

        email3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email3.setForeground(new java.awt.Color(51, 51, 51));
        email3.setText("Username");
        jPanel2.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 80, 30));

        userName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        userName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                userNameFocusLost(evt);
            }
        });
        userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameActionPerformed(evt);
            }
        });
        jPanel2.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 270, 40));

        email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(51, 51, 51));
        email.setText("Email");
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 80, 30));

        Email.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        Email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmailFocusLost(evt);
            }
        });
        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 270, 40));

        gender.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        gender.setForeground(new java.awt.Color(51, 51, 51));
        gender.setText("Date of Birth");
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 120, 30));

        Gender.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        Gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select gender", "Male", "Female" }));
        Gender.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                GenderFocusLost(evt);
            }
        });
        Gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenderActionPerformed(evt);
            }
        });
        jPanel2.add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 270, 40));

        edit_profile.setBackground(new java.awt.Color(0, 153, 153));
        edit_profile.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        edit_profile.setForeground(new java.awt.Color(255, 255, 255));
        edit_profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit_profile.setText("EDIT PROFILE");
        edit_profile.setOpaque(true);
        edit_profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit_profileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                edit_profileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                edit_profileMouseExited(evt);
            }
        });
        jPanel2.add(edit_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 200, 40));

        email1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email1.setForeground(new java.awt.Color(51, 51, 51));
        email1.setText("Phone Number");
        jPanel2.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 120, 30));

        Phone.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        Phone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneFocusLost(evt);
            }
        });
        Phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneActionPerformed(evt);
            }
        });
        jPanel2.add(Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 270, 40));
        jPanel2.add(Birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 270, 40));

        gender1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        gender1.setForeground(new java.awt.Color(51, 51, 51));
        gender1.setText("Gender");
        jPanel2.add(gender1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, 80, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 850, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFocusLost
       
    }//GEN-LAST:event_firstNameFocusLost

    private void firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameActionPerformed

    private void middleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middleNameFocusLost

    }//GEN-LAST:event_middleNameFocusLost

    private void middleNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_middleNameActionPerformed

    private void lastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFocusLost
        
    }//GEN-LAST:event_lastNameFocusLost

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameActionPerformed

    private void userNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameFocusLost
      
    }//GEN-LAST:event_userNameFocusLost

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameActionPerformed

    private void EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailFocusLost

    }//GEN-LAST:event_EmailFocusLost

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void GenderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GenderFocusLost

    }//GEN-LAST:event_GenderFocusLost

    private void GenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderActionPerformed

    private void edit_profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_profileMouseClicked
        editProfile();
    }//GEN-LAST:event_edit_profileMouseClicked

    private void edit_profileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_profileMouseEntered
        edit_profile.setBackground(hover);
    }//GEN-LAST:event_edit_profileMouseEntered

    private void edit_profileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_profileMouseExited
        edit_profile.setBackground(nav);
    }//GEN-LAST:event_edit_profileMouseExited

    private void PhoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhoneFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneFocusLost

    private void PhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Birth;
    private javax.swing.JTextField Email;
    private javax.swing.JComboBox<String> Gender;
    private javax.swing.JTextField Phone;
    private javax.swing.JLabel account;
    private javax.swing.JLabel account1;
    private javax.swing.JLabel edit_profile;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
    private javax.swing.JLabel email3;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel gender;
    private javax.swing.JLabel gender1;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lastName;
    private javax.swing.JLabel lastname;
    private javax.swing.JTextField middleName;
    private javax.swing.JLabel middlename;
    private javax.swing.JPanel profile_header;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel username2;
    // End of variables declaration//GEN-END:variables
}
