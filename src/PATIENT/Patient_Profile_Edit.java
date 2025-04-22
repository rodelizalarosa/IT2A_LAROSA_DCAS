
package PATIENT;

import AUTHENTICATION.Register;
import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Patient_Profile_Edit extends javax.swing.JInternalFrame {

    public Patient_Profile_Edit() {
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
        profile_header = new javax.swing.JPanel();
        account = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        middlename = new javax.swing.JLabel();
        middleName = new javax.swing.JTextField();
        gender = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        password1 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        confirmPassword = new javax.swing.JLabel();
        ConfirmPass = new javax.swing.JPasswordField();
        create_button = new javax.swing.JLabel();
        showCon = new javax.swing.JLabel();
        hideCon = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        errorGender = new javax.swing.JLabel();
        errorPassword = new javax.swing.JLabel();
        errorConfirm = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        delPanel = new javax.swing.JPanel();
        del_prof1 = new javax.swing.JLabel();
        addProf = new javax.swing.JPanel();
        add_prof = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        errorFirst = new javax.swing.JLabel();
        email3 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        errorUser = new javax.swing.JLabel();
        lastname = new javax.swing.JLabel();
        lastName = new javax.swing.JTextField();
        errorLast = new javax.swing.JLabel();
        Gender = new javax.swing.JComboBox<>();
        email = new javax.swing.JLabel();
        errorEmail = new javax.swing.JLabel();

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
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setText("Edit your personal details");
        jPanel3.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 50));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 50));

        middlename.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        middlename.setForeground(new java.awt.Color(51, 51, 51));
        middlename.setText("Middle Name");
        jPanel2.add(middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 100, 30));

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
        jPanel2.add(middleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 270, 40));

        gender.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        gender.setForeground(new java.awt.Color(51, 51, 51));
        gender.setText("Gender");
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 80, 30));

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
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 270, 40));

        password1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        password1.setForeground(new java.awt.Color(51, 51, 51));
        password1.setText("Password");
        jPanel2.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 120, 30));

        Password.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFocusLost(evt);
            }
        });
        jPanel2.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 240, 40));

        confirmPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        confirmPassword.setForeground(new java.awt.Color(51, 51, 51));
        confirmPassword.setText("Confirm Password");
        jPanel2.add(confirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 150, 30));

        ConfirmPass.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        ConfirmPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ConfirmPassFocusLost(evt);
            }
        });
        jPanel2.add(ConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, 250, 40));

        create_button.setBackground(new java.awt.Color(0, 153, 153));
        create_button.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        create_button.setForeground(new java.awt.Color(255, 255, 255));
        create_button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        create_button.setText("CREATE USER ACCOUNT");
        create_button.setOpaque(true);
        create_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                create_buttonMouseClicked(evt);
            }
        });
        jPanel2.add(create_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 210, 40));

        showCon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show_bl.png"))); // NOI18N
        showCon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showConMousePressed(evt);
            }
        });
        jPanel2.add(showCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 40, 40));

        hideCon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hideCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide_bl.png"))); // NOI18N
        hideCon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideConMousePressed(evt);
            }
        });
        jPanel2.add(hideCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 40, 40));

        show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show_bl.png"))); // NOI18N
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showMousePressed(evt);
            }
        });
        jPanel2.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 40, 40));

        hide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide_bl.png"))); // NOI18N
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideMousePressed(evt);
            }
        });
        jPanel2.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 40, 40));
        jPanel2.add(errorGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 220, 20));
        jPanel2.add(errorPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 210, 20));
        jPanel2.add(errorConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, 210, 20));

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 200, 160));

        delPanel.setBackground(new java.awt.Color(255, 255, 255));
        delPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        delPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        del_prof1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        del_prof1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        del_prof1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_profile.png"))); // NOI18N
        del_prof1.setText("  Delete");
        delPanel.add(del_prof1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jPanel2.add(delPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 100, 40));

        addProf.setBackground(new java.awt.Color(255, 255, 255));
        addProf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        addProf.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_prof.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        add_prof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_prof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upload.png"))); // NOI18N
        add_prof.setText("  Add");
        add_prof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_profMouseClicked(evt);
            }
        });
        addProf.add(add_prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jPanel2.add(addProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 100, 40));

        username2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        username2.setForeground(new java.awt.Color(51, 51, 51));
        username2.setText("First Name");
        jPanel2.add(username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 80, 30));

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
        jPanel2.add(firstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 270, 40));
        jPanel2.add(errorFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 210, 20));

        email3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email3.setForeground(new java.awt.Color(51, 51, 51));
        email3.setText("Username");
        jPanel2.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 80, 30));

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
        jPanel2.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 270, 40));
        jPanel2.add(errorUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 220, 20));

        lastname.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname.setForeground(new java.awt.Color(51, 51, 51));
        lastname.setText("Last Name");
        jPanel2.add(lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 80, 30));

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
        jPanel2.add(lastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 270, 40));
        jPanel2.add(errorLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 220, 20));

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
        jPanel2.add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 270, 40));

        email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(51, 51, 51));
        email.setText("Email");
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 80, 30));
        jPanel2.add(errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 220, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 860, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void middleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middleNameFocusLost

    }//GEN-LAST:event_middleNameFocusLost

    private void middleNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_middleNameActionPerformed

    private void EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailFocusLost

        String em = Email.getText();

        if (em.isEmpty()) {
            errorGender.setForeground(Color.RED);
            errorGender.setText("Email is required");
            errorGender.setForeground(Color.RED);
        } else {
            errorGender.setForeground(Color.BLACK);
            errorGender.setText("");
        }

        Email.repaint();
    }//GEN-LAST:event_EmailFocusLost

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void PasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFocusLost

        String pass = Password.getText();

        if (pass.isEmpty()) {
            errorPassword.setForeground(Color.RED);
            errorPassword.setText("Password is required");
            errorPassword.setForeground(Color.RED);
        } else {
            errorPassword.setForeground(Color.BLACK);
            errorPassword.setText("");
        }
        Password.repaint();
    }//GEN-LAST:event_PasswordFocusLost

    private void ConfirmPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ConfirmPassFocusLost
        String pass = ConfirmPass.getText();

        if (pass.isEmpty()) {
            errorConfirm.setForeground(Color.RED);
            errorConfirm.setText("Confirm Password is required");
            errorConfirm.setForeground(Color.RED);
        } else {
            errorConfirm.setForeground(Color.BLACK);
            errorConfirm.setText("");
        }
        ConfirmPass.repaint();
    }//GEN-LAST:event_ConfirmPassFocusLost

    private void create_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_create_buttonMouseClicked

        ConnectDB connect = new ConnectDB();

        String usernameText = middleName.getText().trim();
        String emailText = Email.getText().trim();
        char[] passwordChars = Password.getPassword();
        StringBuilder errorMessage = new StringBuilder();

        if (isAllFieldsEmpty()) {
            errorMessage.append("Please fill out the registration form.\n");
        } else {
            if (emailText.isEmpty()) {
                errorMessage.append("Email cannot be empty.\n");
            } else if (!isValidEmail(emailText)) {
                errorMessage.append("Invalid email format.\n");
            } else if (isEmailTaken(emailText)) {
                errorMessage.append("Email is already taken.\n");
            }
            if (usernameText.isEmpty()) {
                errorMessage.append("Username cannot be empty.\n");
            } else if (isUsernameTaken(usernameText)) {
                errorMessage.append("Username is already taken.\n");
            }
            if (!validatePassword(Password)) {
                return;
            }
        }
        if (errorMessage.length() > 0) {
            JOptionPane.showMessageDialog(this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String passwordText = new String(passwordChars);
        String hashedPassword = hashPassword(passwordText);
        String sql = "INSERT INTO dcas_sys.users (u_username, u_email, u_password, u_status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, usernameText);
            pst.setString(2, emailText);
            pst.setString(3, hashedPassword);
            pst.setString(4, "Pending");
            pst.executeUpdate();
            Session sess = Session.getInstance();
            sess.logEvent("EDITED ADMIN PROFILE", "Admin edited its account.");
            JOptionPane.showMessageDialog(this, "Added User Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            middleName.setText("");
            Email.setText("");
            Password.setText("");

        } catch (SQLException ex) {

            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_create_buttonMouseClicked

    private void showConMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showConMousePressed
        hideCon.setVisible(true);
        showCon.setVisible(false);
        ConfirmPass.setEchoChar('*');
    }//GEN-LAST:event_showConMousePressed

    private void hideConMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideConMousePressed
        showCon.setVisible(true);
        hideCon.setVisible(false);
        ConfirmPass.setEchoChar((char) 0);
    }//GEN-LAST:event_hideConMousePressed

    private void showMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMousePressed
        hide.setVisible(true);
        show.setVisible(false);
        Password.setEchoChar('*');
    }//GEN-LAST:event_showMousePressed

    private void hideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMousePressed
        show.setVisible(true);
        hide.setVisible(false);
        Password.setEchoChar((char) 0);
    }//GEN-LAST:event_hideMousePressed

    private void add_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_profMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/u_images/" + selectedFile.getName();
                path  = selectedFile.getAbsolutePath();

                if(FileExistenceChecker(path) == 1){
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path="";
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_add_profMouseClicked

    private void firstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFocusLost
        String pass = firstName.getText();

        if (pass.isEmpty()) {
            errorFirst.setForeground(Color.RED);
            errorFirst.setText("First Name is required");
            errorFirst.setForeground(Color.RED);
        } else {
            errorFirst.setForeground(Color.BLACK);
            errorFirst.setText("");
        }
        firstName.repaint();
    }//GEN-LAST:event_firstNameFocusLost

    private void firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameActionPerformed

    private void userNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameFocusLost
        String user = userName.getText();

        if (user.isEmpty()) {
            errorUser.setForeground(Color.RED);
            errorUser.setText("Username is required");
            errorUser.setForeground(Color.RED);
        } else {
            errorUser.setForeground(Color.BLACK);
            errorUser.setText("");
        }

        userName.repaint();
    }//GEN-LAST:event_userNameFocusLost

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameActionPerformed

    private void lastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFocusLost
        String pass = lastName.getText();

        if (pass.isEmpty()) {
            errorLast.setForeground(Color.RED);
            errorLast.setText("Last Name is required");
            errorLast.setForeground(Color.RED);
        } else {
            errorLast.setForeground(Color.BLACK);
            errorLast.setText("");
        }
        lastName.repaint();
    }//GEN-LAST:event_lastNameFocusLost

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameActionPerformed

    private void GenderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GenderFocusLost

        String selectedGenderString = (String) Gender.getSelectedItem();

        if (selectedGenderString == null || selectedGenderString.isEmpty() || selectedGenderString.equals("Select gender")) {
            errorGender.setForeground(Color.RED);
            errorGender.setText("Please select a valid gender");
        } else {
            errorGender.setForeground(Color.BLACK);
            errorGender.setText("");
        }

        Gender.repaint();
    }//GEN-LAST:event_GenderFocusLost

    private void GenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConfirmPass;
    private javax.swing.JTextField Email;
    private javax.swing.JComboBox<String> Gender;
    private javax.swing.JPasswordField Password;
    private javax.swing.JLabel account;
    private javax.swing.JPanel addProf;
    private javax.swing.JLabel add_prof;
    private javax.swing.JLabel confirmPassword;
    private javax.swing.JLabel create_button;
    private javax.swing.JPanel delPanel;
    private javax.swing.JLabel del_prof1;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email3;
    private javax.swing.JLabel errorConfirm;
    private javax.swing.JLabel errorEmail;
    private javax.swing.JLabel errorFirst;
    private javax.swing.JLabel errorGender;
    private javax.swing.JLabel errorLast;
    private javax.swing.JLabel errorPassword;
    private javax.swing.JLabel errorUser;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel gender;
    private javax.swing.JLabel hide;
    private javax.swing.JLabel hideCon;
    private javax.swing.JLabel image;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lastName;
    private javax.swing.JLabel lastname;
    private javax.swing.JTextField middleName;
    private javax.swing.JLabel middlename;
    private javax.swing.JLabel password1;
    private javax.swing.JLabel profile;
    private javax.swing.JPanel profile_header;
    private javax.swing.JLabel show;
    private javax.swing.JLabel showCon;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel username2;
    // End of variables declaration//GEN-END:variables
}
