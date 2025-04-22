
package AUTHENTICATION;

import Config.ConnectDB;
import java.awt.Color;
import java.awt.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;


public class Register extends javax.swing.JFrame {

   
    public Register() {
        initComponents();
        
         // Make username transparent with a border
        userName.setBackground(new Color(0, 0, 0, 0));
        userName.setBorder(new LineBorder(Color.WHITE, 1));
        
        // Make email transparent with a border
        Email.setBackground(new Color(0, 0, 0, 0));
        Email.setBorder(new LineBorder(Color.WHITE, 1));
        
        // Makfe role transparent with a border
        Role.setBackground(new Color(0, 0, 0, 0));
        Role.setBorder(new LineBorder(Color.WHITE, 1));
        
        // Make password transparent with a border
        Password.setBackground(new Color(0, 0, 0, 0));
        Password.setBorder(new LineBorder(Color.WHITE, 1));
        
        // Make confirm password transparent with a border
        ConfirmPass.setBackground(new Color(0, 0, 0, 0));
        ConfirmPass.setBorder(new LineBorder(Color.WHITE, 1));
        
         Role.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    component.setBackground(new Color(0, 153, 153)); // Background when selected
                    component.setForeground(Color.WHITE); // Text color when selected
                } else {
                    component.setBackground(new Color(0,51,51)); // Background when not selected
                    component.setForeground(Color.WHITE); // Text color when not selected
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
    
    public String destination = "default/u_blank.jpg";
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reg = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        errorUser = new javax.swing.JLabel();
        email2 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        errorEmail = new javax.swing.JLabel();
        role1 = new javax.swing.JLabel();
        Role = new javax.swing.JComboBox<>();
        errorRole = new javax.swing.JLabel();
        password1 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        errorPassword = new javax.swing.JLabel();
        confirmPassword = new javax.swing.JLabel();
        ConfirmPass = new javax.swing.JPasswordField();
        errorConfirmPass = new javax.swing.JLabel();
        showCon = new javax.swing.JLabel();
        hideCon = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        create_button = new javax.swing.JLabel();
        noAcct2 = new javax.swing.JLabel();
        SignIn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        reg.setBackground(new java.awt.Color(55, 162, 153));
        reg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loginLogo.png"))); // NOI18N
        reg.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 430, 560));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Create an Account");
        reg.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 660, 80));

        username1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        username1.setForeground(new java.awt.Color(255, 255, 255));
        username1.setText("Username");
        reg.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 80, 30));

        userName.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
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
        reg.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 280, 40));
        reg.add(errorUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 210, 20));

        email2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email2.setForeground(new java.awt.Color(255, 255, 255));
        email2.setText("Email");
        reg.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 80, 30));

        Email.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        Email.setForeground(new java.awt.Color(255, 255, 255));
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
        reg.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 280, 40));
        reg.add(errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 220, 20));

        role1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        role1.setForeground(new java.awt.Color(255, 255, 255));
        role1.setText("Role");
        reg.add(role1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 60, 30));

        Role.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        Role.setForeground(new java.awt.Color(255, 255, 255));
        Role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "   Select a role", "   Admin", "   Patient", "   Dentist" }));
        Role.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                RoleFocusLost(evt);
            }
        });
        reg.add(Role, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 280, 40));
        reg.add(errorRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 200, 20));

        password1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        password1.setForeground(new java.awt.Color(255, 255, 255));
        password1.setText("Password");
        reg.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 120, 30));

        Password.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        Password.setForeground(new java.awt.Color(255, 255, 255));
        Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFocusLost(evt);
            }
        });
        reg.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 260, 40));
        reg.add(errorPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 210, 20));

        confirmPassword.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        confirmPassword.setForeground(new java.awt.Color(255, 255, 255));
        confirmPassword.setText("Confirm Password");
        reg.add(confirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 150, 30));

        ConfirmPass.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        ConfirmPass.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ConfirmPassFocusLost(evt);
            }
        });
        reg.add(ConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 260, 40));
        reg.add(errorConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 170, 20));

        showCon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show.png"))); // NOI18N
        showCon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showConMousePressed(evt);
            }
        });
        reg.add(showCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 50, 40));

        hideCon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hideCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png"))); // NOI18N
        hideCon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideConMousePressed(evt);
            }
        });
        reg.add(hideCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 50, 40));

        show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show.png"))); // NOI18N
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showMousePressed(evt);
            }
        });
        reg.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 50, 40));

        hide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png"))); // NOI18N
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideMousePressed(evt);
            }
        });
        reg.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 50, 40));

        create_button.setBackground(new java.awt.Color(0, 153, 153));
        create_button.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        create_button.setForeground(new java.awt.Color(255, 255, 255));
        create_button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        create_button.setText("CREATE ACCOUNT");
        create_button.setOpaque(true);
        create_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                create_buttonMouseClicked(evt);
            }
        });
        reg.add(create_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 310, 40));

        noAcct2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        noAcct2.setForeground(new java.awt.Color(255, 255, 255));
        noAcct2.setText("Already have an account?");
        reg.add(noAcct2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 500, 220, 40));

        SignIn.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        SignIn.setForeground(new java.awt.Color(204, 255, 255));
        SignIn.setText("SIGN IN");
        SignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignInMouseClicked(evt);
            }
        });
        reg.add(SignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 70, 40));

        getContentPane().add(reg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 560));

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

    private void ConfirmPassFocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
    }       

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
        String sql = "INSERT INTO dcas_sys.users (u_username, u_email, u_role, u_status, u_password, u_image) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, usernameText);
            pst.setString(2, emailText);
            pst.setString(3, selectedRole);
            pst.setString(4, "Pending");
            pst.setString(5, hashedPassword);
            pst.setString(6, destination);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            userName.setText("");
            Email.setText("");
            Password.setText("");

            LogIn loginPage = new LogIn();
            loginPage.setVisible(true);
            this.dispose();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_create_buttonMouseClicked

    private void SignInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignInMouseClicked
        LogIn log = new LogIn();
        log.setLocationRelativeTo(null);
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SignInMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConfirmPass;
    private javax.swing.JTextField Email;
    private javax.swing.JPasswordField Password;
    private javax.swing.JComboBox<String> Role;
    private javax.swing.JLabel SignIn;
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
    private javax.swing.JLabel logo;
    private javax.swing.JLabel noAcct2;
    private javax.swing.JLabel password1;
    private javax.swing.JPanel reg;
    private javax.swing.JLabel role1;
    private javax.swing.JLabel show;
    private javax.swing.JLabel showCon;
    private javax.swing.JLabel title;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel username1;
    // End of variables declaration//GEN-END:variables
}
