
package ADMIN;

import AUTHENTICATION.LogIn;
import Config.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Admin_Profile_Edit extends javax.swing.JInternalFrame {

    private int userId;
    
    public Admin_Profile_Edit() {
        initComponents();
        borderField();
        loadAdminProfile();
        
         //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        // Delay the image loading until UI is ready
        SwingUtilities.invokeLater(() -> {
            formInternalFrameActivated(null);
        });

        // Still useful for when the frame is re-activated later
        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
        });
    }
    
    private void borderField(){
        
          // Make username transparent with a border
        firstName.setBackground(new Color(0, 0, 0, 0));
        firstName.setBorder(new LineBorder(Color.BLACK, 1));
        
          // Make username transparent with a border
        middleName.setBackground(new Color(0, 0, 0, 0));
        middleName.setBorder(new LineBorder(Color.BLACK, 1));
        
          // Make username transparent with a border
        lastName.setBackground(new Color(0, 0, 0, 0));
        lastName.setBorder(new LineBorder(Color.BLACK, 1));

        // Make username transparent with a border
        userName.setBackground(new Color(0, 0, 0, 0));
        userName.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make email transparent with a border
        Email.setBackground(new Color(0, 0, 0, 0));
        Email.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make contact transparent with a border
        contact.setBackground(new Color(0, 0, 0, 0));
        contact.setBorder(new LineBorder(Color.BLACK, 1));
        
        // Make role transparent with a border
        Gender.setBackground(new Color(0, 0, 0, 0));
        Gender.setBorder(new LineBorder(Color.BLACK, 1));
        
        Gender.setRenderer(new DefaultListCellRenderer() {
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
    

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
    
    private void loadAdminProfile() {
        this.userId = Session.getInstance().getUserId();
        System.out.println("Loading profile for user ID: " + this.userId);

        try (Connection con = ConnectDB.getConnection()) {

            // --- Get from users table ---
            String userSql = "SELECT u_username, u_email, u_image FROM users WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(userSql)) {
                pst.setInt(1, this.userId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("u_username");
                    String email = rs.getString("u_email");
                    String img = rs.getString("u_image");

                    userName.setText(username);
                    Email.setText(email);

                    String imagePath;
                    if (img == null || img.isEmpty()) {
                        imagePath = "src/default/u_blank.jpg";
                    } else {
                        imagePath = "src/u_images/" + img;
                    }

                    String finalImagePath = imagePath; // required for use in lambda

                    SwingUtilities.invokeLater(() -> {
                        ImageIcon icon = new ImageIcon(finalImagePath);

                        int width = image.getWidth();
                        int height = image.getHeight();

                        if (width <= 0 || height <= 0) {
                            Dimension size = image.getPreferredSize();
                            width = size.width > 0 ? size.width : 120;
                            height = size.height > 0 ? size.height : 120;
                        }

                        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        image.setIcon(new ImageIcon(scaledImage));

                        selectedImagePath = finalImagePath;
                        currentImageFromDB = img;
                    });
                }
            }

            // --- Get from staff table ---
            String staffSql = "SELECT s_fname, s_mname, s_lname, s_gender FROM staff WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(staffSql)) {
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String fname = rs.getString("s_fname");
                    String mname = rs.getString("s_mname");
                    String lname = rs.getString("s_lname");
                    String gender = rs.getString("s_gender");

                    System.out.println("Staff Name: " + fname + " " + lname);

                    firstName.setText(fname);
                    middleName.setText(mname);
                    lastName.setText(lname);
                    Gender.setSelectedItem(gender);

                    // 🔄 Update session with latest names
                    Session.getInstance().setStaffName(fname, lname);
                } else {
                    System.out.println("No staff profile found for user ID: " + userId);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading admin profile: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
     private void loadDefaultImage() {
        SwingUtilities.invokeLater(() -> {
            String defaultImagePath = "src/default/u_blank.jpg";
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


      
    private boolean isAllFieldsEmpty() {
        return firstName.getText().trim().isEmpty() &&
                lastName.getText().trim().isEmpty() && userName.getText().trim().isEmpty() &&
                Email.getText().trim().isEmpty() && Gender.getSelectedIndex() == 0;
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
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>❌ Error saving image:</b><br>" + e.getMessage() + "</html>",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
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
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>Error updating image path:</b><br>" + ex.getMessage() + "</html>",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
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
        save_button = new javax.swing.JLabel();
        errorGender = new javax.swing.JLabel();
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
        image = new javax.swing.JLabel();
        email1 = new javax.swing.JLabel();
        contact = new javax.swing.JTextField();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile_header.setBackground(new java.awt.Color(55, 162, 153));
        profile_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        profile_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account.setForeground(new java.awt.Color(255, 255, 255));
        account.setText("Edit Admin Details");
        profile_header.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 210, 50));

        jPanel1.add(profile_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
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
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 80, 30));

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

        save_button.setBackground(new java.awt.Color(0, 153, 153));
        save_button.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        save_button.setForeground(new java.awt.Color(255, 255, 255));
        save_button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        save_button.setText("SAVE CHANGES");
        save_button.setOpaque(true);
        save_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                save_buttonMouseClicked(evt);
            }
        });
        jPanel2.add(save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 210, 40));
        jPanel2.add(errorGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 220, 20));

        delPanel.setBackground(new java.awt.Color(255, 255, 255));
        delPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        delPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delPanelMouseClicked(evt);
            }
        });
        delPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        del_prof1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        del_prof1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        del_prof1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_profile.png"))); // NOI18N
        del_prof1.setText("  Delete");
        delPanel.add(del_prof1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 40));

        jPanel2.add(delPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 100, 40));

        addProf.setBackground(new java.awt.Color(255, 255, 255));
        addProf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        addProf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addProfMouseClicked(evt);
            }
        });
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
        addProf.add(add_prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 40));

        jPanel2.add(addProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 100, 40));

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
        jPanel2.add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, 270, 40));

        email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(51, 51, 51));
        email.setText("Email");
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 80, 30));
        jPanel2.add(errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 220, 20));

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 200, 190));

        email1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email1.setForeground(new java.awt.Color(51, 51, 51));
        email1.setText("Phone Number");
        jPanel2.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 140, 30));

        contact.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        contact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                contactFocusLost(evt);
            }
        });
        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });
        jPanel2.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 270, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 860, 430));

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

    private void save_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_buttonMouseClicked
       // Assuming userId is already set (from loadUser or other logic)
        int selectedUserId = Session.getInstance().getUserId(); 

        ConnectDB connect = new ConnectDB();

        String usernameText = userName.getText().trim();
        String emailText = Email.getText().trim();
        String fName = firstName.getText().trim();
        String mName = middleName.getText().trim();
        String lName = lastName.getText().trim();
        String phone = contact.getText().trim();
        String genderValue = Gender.getSelectedItem().toString().trim();

        StringBuilder errorMessage = new StringBuilder();

        // Validation for fields
        if (isAllFieldsEmpty()) {
            errorMessage.append("Please fill out the registration form.\n");
        } else {
            if (emailText.isEmpty()) {
                errorMessage.append("Email cannot be empty.\n");
            } else if (!isValidEmail(emailText)) {
                errorMessage.append("Invalid email format.\n");
            } else if (isEmailTaken(emailText, selectedUserId)) {
                errorMessage.append("Email is already taken.\n");
            }

            if (usernameText.isEmpty()) {
                errorMessage.append("Username cannot be empty.\n");
            } else if (isUsernameTaken(usernameText, selectedUserId)) {
                errorMessage.append("Username is already taken.\n");
            }
        }

        if (errorMessage.length() > 0) {
            JOptionPane.showMessageDialog(this,
                "<html><b>⚠️ Validation Error:</b><br>" + errorMessage.toString() + "</html>",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Assuming no file was selected for image update (if there's an image field, handle it like this)
        String destinationImagePath = currentImageFromDB;  // Use the current image path from DB by default

        // Update 'users' table with username, email, and image
        String updateUserSql = "UPDATE dcas_sys.users SET u_username = ?, u_email = ?, u_image = ? WHERE user_id = ?";
        try (Connection con = connect.getConnection(); PreparedStatement pst = con.prepareStatement(updateUserSql)) {
            pst.setString(1, usernameText);
            pst.setString(2, emailText);
            pst.setString(3, destinationImagePath);  // Assuming no image change, keeping the current path
            pst.setInt(4, selectedUserId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Profile_Edit.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error updating user data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update or insert into 'staff' table for profile data
        String checkStaffSql = "SELECT COUNT(*) FROM staff WHERE user_id = ?";
        try (Connection con = connect.getConnection(); PreparedStatement checkPst = con.prepareStatement(checkStaffSql)) {
            checkPst.setInt(1, selectedUserId);
            ResultSet rs = checkPst.executeQuery();
            rs.next();
            boolean staffExists = rs.getInt(1) > 0;

                if (staffExists) {
                String updateStaffSql = "UPDATE staff SET s_fname = ?, s_mname = ?, s_lname = ?, s_gender = ?, s_contact = ? WHERE user_id = ?";
                try (PreparedStatement pst = con.prepareStatement(updateStaffSql)) {
                    pst.setString(1, fName);
                    pst.setString(2, mName);
                    pst.setString(3, lName);
                    pst.setString(4, genderValue);
                    pst.setString(5, phone);
                    pst.setInt(6, selectedUserId);
                    pst.executeUpdate();
                }
            } else {
                String insertStaffSql = "INSERT INTO staff (user_id, s_fname, s_mname, s_lname, s_gender, s_contact) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = con.prepareStatement(insertStaffSql)) {
                    pst.setInt(1, selectedUserId);
                    pst.setString(2, fName);
                    pst.setString(3, mName);
                    pst.setString(4, lName);
                    pst.setString(5, genderValue);
                    pst.setString(6, phone);
                    pst.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Profile_Edit.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error updating staff data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Session session = Session.getInstance();
        session.setStaffName(fName, lName);


        try {
            File imgFile = new File(destinationImagePath);
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(destinationImagePath);
                Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                image.setIcon(new ImageIcon(img));
            } else {
                loadDefaultImage();  // Load default image if file does not exist
            }
        } catch (Exception e) {
            loadDefaultImage();
            System.out.println("Error displaying image: " + e.getMessage());
        }

        // Log event and notify success
        Session sess = Session.getInstance();
        sess.logEvent("UPDATED USER PROFILE", "Admin updated a user profile.");
        JOptionPane.showMessageDialog(this, 
            "<html><b>Updated User Successfully!</b><br>Your changes have been saved.</html>", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_save_buttonMouseClicked

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

    private void add_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_profMouseClicked
        
    }//GEN-LAST:event_add_profMouseClicked

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

    private void addProfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addProfMouseClicked
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
                updateUserImagePath(this.userId, newImagePath);
            }
        }
    }//GEN-LAST:event_addProfMouseClicked

    private void delPanelMouseClicked(java.awt.event.MouseEvent evt) {                                      
       if (selectedImagePath == null || selectedImagePath.equals(defaultImagePath)) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Profile picture is already set to default.</b><br>No changes were made.</html>",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
            );
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
    }                                     

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        if (Session.getInstance().getUserId() == 0) {
           JOptionPane.showMessageDialog(null, "No Account, Log in First!", "Notice", JOptionPane.ERROR_MESSAGE);
           new LogIn().setVisible(true);
           this.dispose();
       } else {
           loadAdminProfile(); // ✅ call the clean method
       }
    }//GEN-LAST:event_formInternalFrameActivated

    private void contactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_contactFocusLost

    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JComboBox<String> Gender;
    private javax.swing.JLabel account;
    private javax.swing.JPanel addProf;
    private javax.swing.JLabel add_prof;
    private javax.swing.JTextField contact;
    private javax.swing.JPanel delPanel;
    private javax.swing.JLabel del_prof1;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
    private javax.swing.JLabel email3;
    private javax.swing.JLabel errorEmail;
    private javax.swing.JLabel errorFirst;
    private javax.swing.JLabel errorGender;
    private javax.swing.JLabel errorLast;
    private javax.swing.JLabel errorUser;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel gender;
    private javax.swing.JLabel image;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lastName;
    private javax.swing.JLabel lastname;
    private javax.swing.JTextField middleName;
    private javax.swing.JLabel middlename;
    private javax.swing.JLabel profile;
    private javax.swing.JPanel profile_header;
    private javax.swing.JLabel save_button;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel username2;
    // End of variables declaration//GEN-END:variables
}
