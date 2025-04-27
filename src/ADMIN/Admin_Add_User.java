
package ADMIN;

import AUTHENTICATION.Register;
import Config.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Admin_Add_User extends javax.swing.JFrame {

    public Admin_Add_User() {
        initComponents();
        borderField();
        
        hide.setVisible(true);
        hideCon.setVisible(true);
    }
    
     private void borderField(){
          // Make username transparent with a border
        userName.setBackground(new Color(0, 0, 0, 0));
        userName.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make email transparent with a border
        Email.setBackground(new Color(0, 0, 0, 0));
        Email.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make role transparent with a border
        Role.setBackground(new Color(0, 0, 0, 0));
        Role.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make password transparent with a border
        Password.setBackground(new Color(0, 0, 0, 0));
        Password.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make confirm password transparent with a border
        ConfirmPass.setBackground(new Color(0, 0, 0, 0));
        ConfirmPass.setBorder(new LineBorder(Color.BLACK, 1));
        
         Role.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    component.setBackground(new Color(0, 153, 153)); // Background when selected
                    component.setForeground(Color.BLACK); // Text color when selected
                } else {
                    component.setBackground(new Color(0,51,51)); // Background when not selected
                    component.setForeground(Color.BLACK); // Text color when not selected
                }

                return component;
            }
        });
        
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
    
    private boolean isEmailTaken(String email) {
        ConnectDB connect = new ConnectDB();
        String sql = "SELECT COUNT(*) FROM users WHERE u_email = ?";
        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean isUsernameTaken(String username) {
        ConnectDB connect = new ConnectDB();
        
        String sql = "SELECT COUNT(*) FROM users WHERE u_username = ?"; 
        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) { 
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }
        return false;
    }
    
    
    private boolean validatePassword(javax.swing.JPasswordField passwordField) {
        String password = new String(passwordField.getPassword());
        StringBuilder errorMessage = new StringBuilder();
        boolean isValid = true;

        if (password.isEmpty()) {
            errorMessage.append("Password cannot be empty.\n");
            isValid = false;
        }
        if (password.length() < 8) {
            errorMessage.append("Password must be at least 8 characters long.\n");
            isValid = false;
        }
        if (!password.matches(".*[A-Z].*")) {
            errorMessage.append("Password must contain at least one uppercase letter.\n");
            isValid = false;
        }
        if (!password.matches(".*[a-z].*")) {
            errorMessage.append("Password must contain at least one lowercase letter.\n");
            isValid = false;
        }
        if (!password.matches(".*\\d.*")) {
            errorMessage.append("Password must contain at least one digit.\n");
            isValid = false;
        }
        if (!password.matches(".*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            errorMessage.append("Password must contain at least one special character.\n");
            isValid = false;
        }

        if (!isValid) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/imgs/error.png"));
            if (icon == null) {
                System.err.println("Error: Image not found at /imgs/error.png");
            }
            JOptionPane.showMessageDialog(this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE, icon);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            JOptionPane.showMessageDialog(this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
        }
        return isValid;
 }
    
    
    
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(this, "Hashing error", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private boolean isAllFieldsEmpty() {
        return userName.getText().trim().isEmpty() && Role.getSelectedIndex() == 0 && Email.getText().trim().isEmpty()
                && Password.getPassword().length == 0;
    }
    
    private String selectedImagePath = null;
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        logs_header = new javax.swing.JPanel();
        logs = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        logs1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        email2 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        role1 = new javax.swing.JLabel();
        Role = new javax.swing.JComboBox<>();
        password1 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        confirmPassword = new javax.swing.JLabel();
        ConfirmPass = new javax.swing.JPasswordField();
        create_button = new javax.swing.JLabel();
        showCon = new javax.swing.JLabel();
        hideCon = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        errorUser = new javax.swing.JLabel();
        errorEmail = new javax.swing.JLabel();
        errorRole = new javax.swing.JLabel();
        errorPassword = new javax.swing.JLabel();
        errorConfirmPass = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        add_prof = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        logs_header.setBackground(new java.awt.Color(55, 162, 153));
        logs_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        logs_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logs.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        logs.setForeground(new java.awt.Color(255, 255, 255));
        logs.setText("Add new user");
        logs_header.add(logs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 160, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Add new accounts ");
        logs_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, 50));

        jPanel1.add(logs_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logs1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        logs1.setForeground(new java.awt.Color(255, 255, 255));
        logs1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logs1.setText("CREATE NEW USER ACCOUNT");
        jPanel3.add(logs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 550, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fill out user details.");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 730, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 50));

        username1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        username1.setForeground(new java.awt.Color(51, 51, 51));
        username1.setText("Username");
        jPanel2.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 80, 30));

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
        jPanel2.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 270, 40));

        email2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email2.setForeground(new java.awt.Color(51, 51, 51));
        email2.setText("Email");
        jPanel2.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 80, 30));

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
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 270, 40));

        role1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        role1.setForeground(new java.awt.Color(51, 51, 51));
        role1.setText("Role");
        jPanel2.add(role1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 60, 30));

        Role.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        Role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a role", "Admin", "Patient", "Dentist" }));
        Role.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                RoleFocusLost(evt);
            }
        });
        Role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoleActionPerformed(evt);
            }
        });
        jPanel2.add(Role, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 270, 40));

        password1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        password1.setForeground(new java.awt.Color(51, 51, 51));
        password1.setText("Password");
        jPanel2.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 120, 30));

        Password.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFocusLost(evt);
            }
        });
        jPanel2.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 240, 40));

        confirmPassword.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        confirmPassword.setForeground(new java.awt.Color(51, 51, 51));
        confirmPassword.setText("Confirm Password");
        jPanel2.add(confirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 150, 30));

        ConfirmPass.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        ConfirmPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ConfirmPassFocusLost(evt);
            }
        });
        jPanel2.add(ConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 250, 40));

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
        jPanel2.add(create_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 210, 40));

        showCon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show_bl.png"))); // NOI18N
        showCon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showConMousePressed(evt);
            }
        });
        jPanel2.add(showCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, 40, 40));

        hideCon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hideCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide_bl.png"))); // NOI18N
        hideCon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideConMousePressed(evt);
            }
        });
        jPanel2.add(hideCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, 40, 40));

        show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show_bl.png"))); // NOI18N
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showMousePressed(evt);
            }
        });
        jPanel2.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 40, 40));

        hide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide_bl.png"))); // NOI18N
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideMousePressed(evt);
            }
        });
        jPanel2.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 40, 40));
        jPanel2.add(errorUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 210, 20));
        jPanel2.add(errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 220, 20));
        jPanel2.add(errorRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 200, 20));
        jPanel2.add(errorPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 210, 20));
        jPanel2.add(errorConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, 170, 20));

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 200, 160));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_prof.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        add_prof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_prof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upload.png"))); // NOI18N
        add_prof.setText("  Add");
        add_prof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_profMouseClicked(evt);
            }
        });
        jPanel4.add(add_prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 100, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 860, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailFocusLost

        String em = Email.getText();

        if (em.isEmpty()) {
            errorEmail.setForeground(Color.RED);
            errorEmail.setText("Email is required");
            errorEmail.setForeground(Color.RED);
        } else {
            errorEmail.setForeground(Color.BLACK);
            errorEmail.setText("");
        }

        Email.repaint();
    }//GEN-LAST:event_EmailFocusLost

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void RoleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RoleFocusLost

        String selectedRoleString = (String) Role.getSelectedItem();

        if (selectedRoleString == null || selectedRoleString.isEmpty() || selectedRoleString.equals("Select a role")) {
            errorRole.setForeground(Color.RED);
            errorRole.setText("Please select a valid role");
        } else {
            errorRole.setForeground(Color.BLACK);
            errorRole.setText("");
        }

        Role.repaint();
    }//GEN-LAST:event_RoleFocusLost

    private void RoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoleActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmPassFocusLost

    private void create_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_create_buttonMouseClicked

         ConnectDB connect = new ConnectDB();

        String usernameText = userName.getText().trim();
        String emailText = Email.getText().trim();
        String selectedRole = Role.getSelectedItem().toString().trim();
        char[] passwordChars = Password.getPassword();
        StringBuilder errorMessage = new StringBuilder();

        if (isAllFieldsEmpty()) {
            errorMessage.append("Please fill out the registration form.\n");
        } else {
            if (Role.getSelectedIndex() == 0) {
                errorMessage.append("Please select a type.\n");
            }
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

        // Determine image path
        String imagePath;
        if (selectedImagePath != null) {
            // Copy image to /src/u_images/
            File source = new File(selectedImagePath);
            File dest = new File("src/u_images/" + source.getName());
            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to save image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            imagePath = "src/u_images/" + source.getName(); // This is the saved path
        } else {
            imagePath = "src/default/u_blank.jpg"; // Default image
        }

        String sql = "INSERT INTO dcas_sys.users (u_username, u_email, u_role, u_password, u_status, u_image) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, usernameText);
            pst.setString(2, emailText);
            pst.setString(3, selectedRole);
            pst.setString(4, hashedPassword);
            pst.setString(5, "Pending");
            pst.setString(6, imagePath); // Set image path
            pst.executeUpdate();

            Session.getInstance().logEvent("ADDED USER ACCOUNT", "Admin added user account.");
            JOptionPane.showMessageDialog(this, "Added User Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Reset form
            userName.setText("");
            Email.setText("");
            Password.setText("");
            selectedImagePath = null;
            add_prof.setIcon(null); // clear image preview (optional)

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

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Admin_Manage man = new Admin_Manage();
        man.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void add_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_profMouseClicked
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            selectedImagePath = selectedFile.getAbsolutePath();

            // Show preview in the correct JLabel
            ImageIcon icon = new ImageIcon(selectedImagePath);
            Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
            image.setIcon(new ImageIcon(img));
        }
    }//GEN-LAST:event_add_profMouseClicked

 
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
            java.util.logging.Logger.getLogger(Admin_Add_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Add_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Add_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Add_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Add_User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConfirmPass;
    private javax.swing.JTextField Email;
    private javax.swing.JPasswordField Password;
    private javax.swing.JComboBox<String> Role;
    private javax.swing.JLabel add_prof;
    private javax.swing.JLabel back;
    private javax.swing.JLabel confirmPassword;
    private javax.swing.JLabel create_button;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel errorConfirmPass;
    private javax.swing.JLabel errorEmail;
    private javax.swing.JLabel errorPassword;
    private javax.swing.JLabel errorRole;
    private javax.swing.JLabel errorUser;
    private javax.swing.JLabel hide;
    private javax.swing.JLabel hideCon;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel logs;
    private javax.swing.JLabel logs1;
    private javax.swing.JPanel logs_header;
    private javax.swing.JLabel password1;
    private javax.swing.JLabel role1;
    private javax.swing.JLabel show;
    private javax.swing.JLabel showCon;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel username1;
    // End of variables declaration//GEN-END:variables
}
