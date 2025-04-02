
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Update_User extends javax.swing.JInternalFrame {


    private int userId;
    private String originalUsername;
    private String originalEmail;
    private String originalRole;
    
    public Update_User(int id, String username, String email, String role) {
        initComponents(); // Initialize GUI components
        borderField();

        this.userId = id;
        this.originalUsername = username;
        this.originalEmail = email;
        this.originalRole = role;

        populateFields(); // Populate fields with provided data

        userid.setEditable(false); // Make user ID non-editable

        // Add Action Listener for Save Button
        change_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveChanges();
            }
        });

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
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

    private void saveChanges() {
        try {
         ConnectDB db = new ConnectDB();
         Connection conn = db.getConnection();

         // Get updated data from fields
         String newUsername = userName.getText().trim();
         String newEmail = Email.getText().trim();
         String newRole = Role.getSelectedItem().toString().trim();
         String newPassword = new String(Password.getPassword()).trim();

         // If the password field is empty, do NOT update the password
         String query;
         PreparedStatement ps;

         if (newPassword.isEmpty()) {
             query = "UPDATE users SET u_username=?, u_email=?, u_role=? WHERE user_id=?";
             ps = conn.prepareStatement(query);
             ps.setString(1, newUsername);
             ps.setString(2, newEmail);
             ps.setString(3, newRole);
             ps.setInt(4, userId);
         } else {
             // Hash the new password before updating
             String hashedPassword = hashPassword(newPassword);
             if (hashedPassword == null) {
                 return; // Hashing failed, don't proceed with update
             }

             query = "UPDATE users SET u_username=?, u_email=?, u_role=?, u_password=? WHERE user_id=?";
             ps = conn.prepareStatement(query);
             ps.setString(1, newUsername);
             ps.setString(2, newEmail);
             ps.setString(3, newRole);
             ps.setString(4, hashedPassword); 
             ps.setInt(5, userId);
         }

         int rowsUpdated = ps.executeUpdate();
         if (rowsUpdated > 0) {
             Session sess = new Session();  
             sess.logEvent("UPDATED USER DETAILS", "Admin updated user details.");
             JOptionPane.showMessageDialog(this, "User Information Updated Successfully!");
             this.dispose();
         } else {
             Session sess = new Session();  
             sess.logEvent("USER DETAILS UPDATE FAILED", "Admin failed to update user details.");
             JOptionPane.showMessageDialog(this, "Failed to Update User Information.");
         }

         ps.close();
         conn.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
     }
 }

    public void setUserData(int id, String username, String email, String role) {
        this.userId = id;
        this.originalUsername = username;
        this.originalEmail = email;
        this.originalRole = role;

        // Fetch role & password from DB
        try {
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();
            String query = "SELECT u_role, u_password FROM users WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.originalRole = rs.getString("u_role"); 
                String storedPassword = rs.getString("u_password");

                userid.setText(String.valueOf(id));
                userName.setText(username);
                Email.setText(email);
                Role.setSelectedItem(originalRole);
                Password.setText("********"); // Hide actual password for security
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
