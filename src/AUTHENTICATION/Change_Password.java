
package AUTHENTICATION;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Change_Password extends javax.swing.JFrame {

    public Change_Password() {
        initComponents();
    }
    
    private boolean validatePassword(javax.swing.JPasswordField passwordField) {
        String password = new String(passwordField.getPassword());
        StringBuilder errorMessage = new StringBuilder();
        boolean isValid = true;

        // Validate password length and content
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

        // If validation fails, display error message
        if (!isValid) {
            try {
                // Load custom error icon
                ImageIcon icon = new ImageIcon(getClass().getResource("/imgs/error.png"));
                if (icon == null) {
                    System.err.println("Error: Image not found at /imgs/error.png");
                }

                // Show error message in JOptionPane
                JOptionPane.showMessageDialog(
                    this, 
                    "<html>" + errorMessage.toString().replace("\n", "<br>") + "</html>", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE, 
                    icon
                );
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                JOptionPane.showMessageDialog(
                    this, 
                    "<html>" + errorMessage.toString().replace("\n", "<br>") + "</html>", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE
                );
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
            // Enhanced JOptionPane error message
            JOptionPane.showMessageDialog(
                this, 
                "<html><b>Error hashing the password:</b><br>" + e.getMessage() + "</html>", 
                "Hashing Error", 
                JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }
    
    private void updatePasswordInDatabase(String hashedPassword) {
        // Get the user's email (or user ID) from the session to identify the user
        String userEmail = Session.getInstance().getEmail(); // Assuming the email is stored in the session

        // Database connection
        try (Connection con = ConnectDB.getConnection()) {
            String updatePasswordQuery = "UPDATE users SET u_password = ? WHERE u_email = ?";

            try (PreparedStatement pstmt = con.prepareStatement(updatePasswordQuery)) {
                pstmt.setString(1, hashedPassword); // Set the hashed password
                pstmt.setString(2, userEmail); // Set the user's email

                int rowsAffected = pstmt.executeUpdate(); // Execute the update query

                if (rowsAffected > 0) {
                    System.out.println("Password updated successfully.");
                } else {
                    System.err.println("Error: No user found with the given email.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>❌ Failed to update password.</b><br>Please try again later.</html>",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "<html><b>❌ Database connection failed.</b><br>Please check your connection.</html>",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    
    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        change = new javax.swing.JPanel();
        delete1 = new javax.swing.JLabel();
        email4 = new javax.swing.JLabel();
        newPass = new javax.swing.JPasswordField();
        email5 = new javax.swing.JLabel();
        confirmPass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(55, 162, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        change.setBackground(new java.awt.Color(0, 51, 51));
        change.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                changeMouseExited(evt);
            }
        });
        change.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete1.setForeground(new java.awt.Color(255, 255, 255));
        delete1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete1.setText("CHANGE PASSWORD");
        change.add(delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 30));

        jPanel1.add(change, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 200, 30));

        email4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email4.setForeground(new java.awt.Color(255, 255, 255));
        email4.setText("New Password");
        jPanel1.add(email4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 350, 30));

        newPass.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        newPass.setForeground(new java.awt.Color(51, 51, 51));
        newPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                newPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                newPassFocusLost(evt);
            }
        });
        newPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                newPassMouseReleased(evt);
            }
        });
        jPanel1.add(newPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 340, 40));

        email5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email5.setForeground(new java.awt.Color(255, 255, 255));
        email5.setText("Confirm Password");
        jPanel1.add(email5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 350, 30));

        confirmPass.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        confirmPass.setForeground(new java.awt.Color(51, 51, 51));
        confirmPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmPassFocusLost(evt);
            }
        });
        confirmPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                confirmPassMouseReleased(evt);
            }
        });
        jPanel1.add(confirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 340, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/verify.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 260, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void changeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeMouseClicked
        try {
            // Get the password values from the JPasswordFields
            String newPassword = new String(newPass.getPassword()).trim(); // New password
            String confirmPassword = new String(confirmPass.getPassword()).trim(); // Confirm password

            // Check if new password and confirm password match
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>❌ New password and confirmation do not match.</b><br>Please check again.</html>",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return; // Stop the process if passwords don't match
            }

            // Validate the new password format (using your existing validatePassword method)
            if (!validatePassword(newPass)) {
                return; // Stop the process if the password does not meet the criteria
            }

            // Hash the new password before saving it
            String hashedNewPassword = hashPassword(newPassword);

            // Update the password in the database
            updatePasswordInDatabase(hashedNewPassword);
            
            Session.getInstance().logEvent("PASSWORD_CHANGE", "User successfully changed their password.");

            // Notify the user of success
            JOptionPane.showMessageDialog(
                this,
                "<html><b>✅ Password changed successfully!</b><br>Your password has been updated.</html>",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );

            // Optionally close the frame or reset fields
            this.dispose(); // Close the current frame (if necessary)
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "<html><b>❌ An error occurred while changing the password.</b><br>Please try again later.</html>",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_changeMouseClicked

    private void changeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeMouseEntered
        change.setBackground(hoverColor);
    }//GEN-LAST:event_changeMouseEntered

    private void changeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeMouseExited
        change.setBackground(navColor);
    }//GEN-LAST:event_changeMouseExited

    private void newPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPassFocusGained
       
    }//GEN-LAST:event_newPassFocusGained

    private void newPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newPassFocusLost

    }//GEN-LAST:event_newPassFocusLost

    private void newPassMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newPassMouseReleased

    }//GEN-LAST:event_newPassMouseReleased

    private void confirmPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPassFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPassFocusGained

    private void confirmPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPassFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPassFocusLost

    private void confirmPassMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmPassMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPassMouseReleased

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
            java.util.logging.Logger.getLogger(Change_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Change_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Change_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Change_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Change_Password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel change;
    private javax.swing.JPasswordField confirmPass;
    private javax.swing.JLabel delete1;
    private javax.swing.JLabel email4;
    private javax.swing.JLabel email5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPass;
    // End of variables declaration//GEN-END:variables
}
