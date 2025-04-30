
package ADMIN;

import java.awt.Color;
import ADMIN.*;
import AUTHENTICATION.Register;
import Config.ConnectDB;
import Config.Session;
import static Config.Session.getInstance;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Admin_Update_User extends javax.swing.JFrame {

    private int userId;

    public Admin_Update_User() {
        initComponents();
        borderField();
    }

    public Admin_Update_User(int selectedUserId) {
        initComponents();
        borderField();
        loadUser(selectedUserId);
    }


    public void loadUser(int selectedUserId) {
         this.userId = selectedUserId;

         String query = "SELECT u_username, u_email, u_role, u_image FROM users WHERE user_id = ?";

         try (Connection conn = new ConnectDB().getConnection();
              PreparedStatement pst = conn.prepareStatement(query)) {

             pst.setInt(1, selectedUserId);
             try (ResultSet rs = pst.executeQuery()) {
                 if (rs.next()) {
                     String username = rs.getString("u_username");
                     String email = rs.getString("u_email");
                     String role = rs.getString("u_role");
                     String imagePath = rs.getString("u_image");

                     userID.setText(String.valueOf(selectedUserId));
                     userName.setText(username);
                     Email.setText(email);
                     Role.setSelectedItem(role);

                     // Handle image loading
                     if (imagePath != null && !imagePath.trim().isEmpty()) {
                         File imageFile = new File(imagePath);
                         if (imageFile.exists()) {
                             ImageIcon icon = new ImageIcon(imagePath);
                             Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                             image.setIcon(new ImageIcon(img));

                             this.selectedImagePath = imagePath; // 🔥 added
                             this.currentImageFromDB = imagePath; // 🔥 added
                         } else {
                             loadDefaultImage(); 
                         }
                     } else {
                         loadDefaultImage();
                     }
                 } else {
                     JOptionPane.showMessageDialog(this,
                         "User with ID " + selectedUserId + " not found.",
                         "User Not Found",
                         JOptionPane.WARNING_MESSAGE);
                 }
             }

         } catch (SQLException e) {
             JOptionPane.showMessageDialog(this,
                 "Error loading user data: " + e.getMessage(),
                 "Database Error",
                 JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
         }
     }

    
    private void loadDefaultImage() {
        String defaultImagePath = "src/default/u_blank.jpg";
        ImageIcon icon = new ImageIcon(defaultImagePath);
        Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(img));

        selectedImagePath = defaultImagePath; // 🔥 important!
        currentImageFromDB = defaultImagePath; // 🔥 important!
    }

    
    private void borderField(){
         // Make username transparent with a border
        userID.setBackground(new Color(0, 0, 0, 0));
        userID.setBorder(new LineBorder(Color.BLACK, 1));
        
        userName.setBackground(new Color(0, 0, 0, 0));
        userName.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make email transparent with a border
        Email.setBackground(new Color(0, 0, 0, 0));
        Email.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make role transparent with a border
        Role.setBackground(new Color(0, 0, 0, 0));
        Role.setBorder(new LineBorder(Color.BLACK, 1));
        
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
    
    private boolean isEmailTaken(String email, int currentUserId) {
        ConnectDB connect = new ConnectDB();
        String sql = "SELECT COUNT(*) FROM users WHERE u_email = ? AND user_id != ?";
        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setInt(2, currentUserId);
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

    private boolean isUsernameTaken(String username, int currentUserId) {
        ConnectDB connect = new ConnectDB();
        String sql = "SELECT COUNT(*) FROM users WHERE u_username = ? AND user_id != ?";
        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setInt(2, currentUserId);
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

    private boolean isAllFieldsEmpty() {
        return userName.getText().trim().isEmpty() && Role.getSelectedIndex() == 0 && Email.getText().trim().isEmpty();
    }

        private String selectedImagePath = null;
        private String currentImageFromDB = null; // the image path from database
        private final String defaultImagePath = "src/default/u_blank.jpg"; // constant
    
        private String saveImageToFolder(File selectedFile) {
            try {
                String folderPath = "src/u_images";
                File directory = new File(folderPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String originalFileName = selectedFile.getName();
                File destinationFile = new File(folderPath + File.separator + originalFileName);

                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return destinationFile.getPath();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage());
            }
            return null;
        }

        private void updateUserImagePath(int userId, String newImagePath) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dcas_sys", "root", "");
                 PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET u_image = ? WHERE user_id = ?")) {

                pstmt.setString(1, newImagePath);
                pstmt.setInt(2, userId);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Image path updated successfully.");
                } else {
                    System.out.println("No user found with the specified ID.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error updating image path: " + ex.getMessage());
            }
        }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        logs_header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        logs = new javax.swing.JLabel();
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
        update_button = new javax.swing.JLabel();
        errorUser = new javax.swing.JLabel();
        errorEmail = new javax.swing.JLabel();
        errorRole = new javax.swing.JLabel();
        errorPassword = new javax.swing.JLabel();
        errorConfirmPass = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        delPanel = new javax.swing.JPanel();
        del_prof = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        add_prof = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();

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

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Update a user's account.");
        logs_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 190, 50));

        logs.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        logs.setForeground(new java.awt.Color(255, 255, 255));
        logs.setText("Update User Account");
        logs_header.add(logs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 50));

        jPanel1.add(logs_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logs1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        logs1.setForeground(new java.awt.Color(255, 255, 255));
        logs1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logs1.setText("UPDATE USER ACCOUNT");
        jPanel3.add(logs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Update user details.");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 560, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 50));

        username1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        username1.setForeground(new java.awt.Color(51, 51, 51));
        username1.setText("Username");
        jPanel2.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 80, 30));

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
        jPanel2.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 270, 40));

        email2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email2.setForeground(new java.awt.Color(51, 51, 51));
        email2.setText("Email");
        jPanel2.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 80, 30));

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
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 270, 40));

        role1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        role1.setForeground(new java.awt.Color(51, 51, 51));
        role1.setText("Role");
        jPanel2.add(role1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 60, 30));

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
        jPanel2.add(Role, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 270, 40));

        update_button.setBackground(new java.awt.Color(0, 153, 153));
        update_button.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        update_button.setForeground(new java.awt.Color(255, 255, 255));
        update_button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        update_button.setText("UPDATE");
        update_button.setOpaque(true);
        update_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update_buttonMouseClicked(evt);
            }
        });
        jPanel2.add(update_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 160, 40));
        jPanel2.add(errorUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 210, 20));
        jPanel2.add(errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 220, 20));
        jPanel2.add(errorRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 200, 20));
        jPanel2.add(errorPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 210, 20));
        jPanel2.add(errorConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, 170, 20));

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, 160));

        delPanel.setBackground(new java.awt.Color(255, 255, 255));
        delPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        delPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        del_prof.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        del_prof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        del_prof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_profile.png"))); // NOI18N
        del_prof.setText("  Delete");
        del_prof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                del_profMouseClicked(evt);
            }
        });
        delPanel.add(del_prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jPanel2.add(delPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 100, 40));

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

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 100, 40));

        username2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        username2.setForeground(new java.awt.Color(51, 51, 51));
        username2.setText("Account ID:");
        jPanel2.add(username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 80, 30));

        userID.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        userID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 90, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 560, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked

        Session session = Session.getInstance();
        JDesktopPane parentDesktop = new JDesktopPane();  // Create the parent desktop
        session.setParentPane(parentDesktop);

        if (parentDesktop != null) {
            // Create a new internal frame
            Admin_User_Internal userInternal = new Admin_User_Internal();
            parentDesktop.add(userInternal);
            userInternal.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Parent Desktop is not initialized.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_backMouseClicked

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

    private void update_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_buttonMouseClicked
        ConnectDB connect = new ConnectDB();

       String usernameText = userName.getText().trim();
       String emailText = Email.getText().trim();
       String selectedRole = Role.getSelectedItem().toString().trim();
       StringBuilder errorMessage = new StringBuilder();

       // Parse current user ID
       int currentUserId;
       try {
           currentUserId = Integer.parseInt(userID.getText().trim());
       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(this, "Invalid User ID.", "Error", JOptionPane.ERROR_MESSAGE);
           return;
       }

       // Validation
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
           } else if (isEmailTaken(emailText, currentUserId)) {
               errorMessage.append("Email is already taken.\n");
           }

            if (usernameText.isEmpty()) {
                errorMessage.append("Username cannot be empty.\n");
            } else if (isUsernameTaken(usernameText, currentUserId)) {
                errorMessage.append("Username is already taken.\n");
            }
        }

        if (errorMessage.length() > 0) {
            JOptionPane.showMessageDialog(this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the user in the database
        String sql = "UPDATE dcas_sys.users SET u_username = ?, u_email = ?, u_role = ? WHERE user_id = ?";
        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, usernameText);
            pst.setString(2, emailText);
            pst.setString(3, selectedRole);
            pst.setInt(4, currentUserId);

            pst.executeUpdate();

            Session sess = Session.getInstance();
            sess.logEvent("UPDATED USER ACCOUNT", "Admin updated user account.");
            JOptionPane.showMessageDialog(this, "Updated User Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_update_buttonMouseClicked

    private void add_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_profMouseClicked
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            // Save the selected image to src/u_images/
            String newImagePath = saveImageToFolder(selectedFile);

            if (newImagePath != null) {
                // Update JLabel
                ImageIcon icon = new ImageIcon(newImagePath);
                Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                image.setIcon(new ImageIcon(img));

                // Update selected paths
                selectedImagePath = newImagePath;
                currentImageFromDB = newImagePath;

                // Update database
                updateUserImagePath(userId, newImagePath);
            }
        }
    }//GEN-LAST:event_add_profMouseClicked

    private void del_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_del_profMouseClicked
         if (selectedImagePath == null || selectedImagePath.equals(defaultImagePath)) {
            JOptionPane.showMessageDialog(this, "Profile picture is already set to default.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete the profile picture?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            // Delete file only if it is from u_images and not a default image
            if (selectedImagePath.startsWith("src/u_images/")) {
                File imgFile = new File(selectedImagePath);
                if (imgFile.exists()) {
                    imgFile.delete();
                }
            }

            // Reset to default
            ImageIcon icon = new ImageIcon(defaultImagePath);
            Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
            image.setIcon(new ImageIcon(img));

            // Update paths
            selectedImagePath = defaultImagePath;
            currentImageFromDB = defaultImagePath;

            // Update database
            updateUserImagePath(userId, defaultImagePath);
        }
    }//GEN-LAST:event_del_profMouseClicked

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
            java.util.logging.Logger.getLogger(Admin_Update_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Update_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Update_User().setVisible(true);
            }   
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JComboBox<String> Role;
    private javax.swing.JLabel add_prof;
    private javax.swing.JLabel back;
    private javax.swing.JPanel delPanel;
    private javax.swing.JLabel del_prof;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel errorConfirmPass;
    private javax.swing.JLabel errorEmail;
    private javax.swing.JLabel errorPassword;
    private javax.swing.JLabel errorRole;
    private javax.swing.JLabel errorUser;
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
    private javax.swing.JLabel role1;
    private javax.swing.JLabel update_button;
    private javax.swing.JLabel userID;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel username1;
    private javax.swing.JLabel username2;
    // End of variables declaration//GEN-END:variables
}
