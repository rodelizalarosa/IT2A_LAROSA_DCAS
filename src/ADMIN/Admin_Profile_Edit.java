
package ADMIN;

import AUTHENTICATION.Register;
import Config.*;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Admin_Profile_Edit extends javax.swing.JInternalFrame {

    public Admin_Profile_Edit() {
        initComponents();
        borderField();
        loadAdminProfile();
        
         //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
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
    
    private void loadAdminProfile() {
        int userId = Session.getInstance().getUserId();

        try (Connection con = ConnectDB.getConnection()) {

            // --- Get from users table ---
            String userSql = "SELECT u_username, u_email, u_image FROM users WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(userSql)) {
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    userName.setText(rs.getString("u_username"));
                    Email.setText(rs.getString("u_email"));

                    // Handle image
                    String img = rs.getString("u_image");
                    if (img == null || img.isEmpty()) {
                        image.setIcon(new ImageIcon("src/default/u_blank.jpg"));
                    } else {
                        image.setIcon(new ImageIcon("src/u_images/" + img));
                    }
                }
            }

            // --- Get from staff table ---
            String staffSql = "SELECT s_fname, s_mname, s_lname, s_gender FROM staff WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(staffSql)) {
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    firstName.setText(rs.getString("s_fname"));
                    middleName.setText(rs.getString("s_mname"));
                    lastName.setText(rs.getString("s_lname"));
                    Gender.setSelectedItem(rs.getString("s_gender"));
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error loading admin profile: " + e.getMessage());
        }
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
                    // Check if the count is greater than 0 AND if the email is NOT the current user's email
                    return rs.getInt(1) > 0 && !email.equalsIgnoreCase(Session.getInstance().getEmail());
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
                    return rs.getInt(1) > 0 && !username.equalsIgnoreCase(Session.getInstance().getUsername());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    private boolean isAllFieldsEmpty() {
        return firstName.getText().trim().isEmpty() &&
                lastName.getText().trim().isEmpty() && userName.getText().trim().isEmpty() &&
                Email.getText().trim().isEmpty() && Gender.getSelectedIndex() == 0;
    }
    
   public String destination = "";
   File selectedFile;
   public String path;
   public String oldpath;
   private String currentImageFromDB; // holds the original image path
    
   public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/u_images", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }

   public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {

           
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
   
    public ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon myImage = null;
        BufferedImage img = null;

        try {
            if (ImagePath != null) {
                img = ImageIO.read(new File(ImagePath));
            } else if (pic != null) {
                img = ImageIO.read(new ByteArrayInputStream(pic));
            }

            if (img != null) {
                int labelWidth = label.getWidth();
                int labelHeight = label.getHeight();
                int originalWidth = img.getWidth();
                int originalHeight = img.getHeight();

                if (labelWidth > 0 && labelHeight > 0 && originalWidth > 0 && originalHeight > 0) {
                    double widthRatio = (double) labelWidth / originalWidth;
                    double heightRatio = (double) labelHeight / originalHeight;

                    // Use the smaller ratio to fit the image within the label
                    double scaleFactor = Math.min(widthRatio, heightRatio);

                    int newWidth = (int) (originalWidth * scaleFactor);
                    int newHeight = (int) (originalHeight * scaleFactor);

                    Image resizedImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    myImage = new ImageIcon(resizedImg);
                } else {
                    System.out.println("JLabel or image has zero or negative dimensions. Using original image.");
                    myImage = new ImageIcon(img);
                }
            } else {
                System.err.println("Error: No image data loaded for path: " + ImagePath);
            }

        } catch (IOException ex) {
            System.err.println("Error loading image: " + ex.getMessage());
            myImage = null;
        }
        return myImage;
    }
    
    public void imageUpdater(String existingFilePath, String newFilePath) {
      File existingFile = new File(existingFilePath); // Path of the currently used image
      File newFile = new File(newFilePath); // Path of the new image
      String destinationFolder = "src/u_images/";
      File destinationFile = new File(destinationFolder, newFile.getName()); // Final destination for the new image

      try {
          // Ensure the u_images folder exists
          File destinationDir = new File(destinationFolder);
          if (!destinationDir.exists()) {
              destinationDir.mkdirs();
          }

          // Check if existingFile is from u_default
          if (existingFile.getPath().contains("u_default")) {
              // Do not delete; simply copy the new file to u_images
              Files.copy(newFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
              System.out.println("New image added successfully to u_images.");
          } else {
              // For files in u_images, replace the existing image
              if (existingFile.exists()) {
                  Files.copy(newFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                  existingFile.delete(); // Clean up the old image if necessary
                  System.out.println("Image updated successfully in u_images.");
              } else {
                  // If no file exists, simply copy the new one
                  Files.copy(newFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                  System.out.println("Image added to u_images.");
              }
          }
      } catch (IOException e) {
          System.out.println("Error while updating the image: " + e.getMessage());
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
        jPanel2.add(save_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 210, 40));
        jPanel2.add(errorGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 220, 20));

        delPanel.setBackground(new java.awt.Color(255, 255, 255));
        delPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        delPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        del_prof1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        del_prof1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        del_prof1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_profile.png"))); // NOI18N
        del_prof1.setText("  Delete");
        delPanel.add(del_prof1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 40));

        jPanel2.add(delPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 100, 40));

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
        jPanel2.add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 270, 40));

        email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(51, 51, 51));
        email.setText("Email");
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 80, 30));
        jPanel2.add(errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 220, 20));

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, 180));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 860, 410));

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

    private void save_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_buttonMouseClicked

          int userId = Session.getInstance().getUserId();
            ConnectDB connect = new ConnectDB();

            String fName = firstName.getText().trim();
            String mName = middleName.getText().trim();
            String lName = lastName.getText().trim();
            String genderValue = Gender.getSelectedItem().toString().trim();

            String usernameText = userName.getText().trim();
            String emailText = Email.getText().trim();

            StringBuilder errorMessage = new StringBuilder();

            if (fName.isEmpty()) errorMessage.append("First Name is required.\n");
            if (lName.isEmpty()) errorMessage.append("Last Name is required.\n");
            if (Gender.getSelectedIndex() == 0) errorMessage.append("Please select a Gender.\n");

            if (emailText.isEmpty()) {
                errorMessage.append("Email cannot be empty.\n");
            } else if (!isValidEmail(emailText)) {
                errorMessage.append("Invalid email format.\n");
            }

            if (usernameText.isEmpty()) {
                errorMessage.append("Username cannot be empty.\n");
            } else if (isUsernameTaken(usernameText) && !usernameText.equalsIgnoreCase(Session.getInstance().getUsername())) {
                errorMessage.append("Username is already taken.\n");
            }

            if (errorMessage.length() > 0) {
                JOptionPane.showMessageDialog(this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection con = connect.getConnection()) {

                // 🛠️ Correct: use the selected file if available, otherwise use the original path
                String imagePath = selectedFile != null ? "src/u_images/" + selectedFile.getName() : currentImageFromDB;

                // --- Update users table ---
                String updateUser = "UPDATE users SET u_username = ?, u_email = ?, u_image = ? WHERE user_id = ?";
                try (PreparedStatement pst = con.prepareStatement(updateUser)) {
                    pst.setString(1, usernameText);
                    pst.setString(2, emailText);
                    pst.setString(3, imagePath); // 👈 Use correct imagePath
                    pst.setInt(4, userId);
                    pst.executeUpdate();
                }

                // --- Check if staff record exists ---
                String checkSql = "SELECT COUNT(*) FROM staff WHERE user_id = ?";
                try (PreparedStatement checkPst = con.prepareStatement(checkSql)) {
                    checkPst.setInt(1, userId);
                    ResultSet rs = checkPst.executeQuery();
                    rs.next();
                    boolean staffExists = rs.getInt(1) > 0;

                    if (staffExists) {
                        String updateStaff = "UPDATE staff SET s_fname = ?, s_mname = ?, s_lname = ?, s_gender = ? WHERE user_id = ?";
                        try (PreparedStatement pst = con.prepareStatement(updateStaff)) {
                            pst.setString(1, fName);
                            pst.setString(2, mName);
                            pst.setString(3, lName);
                            pst.setString(4, genderValue);
                            pst.setInt(5, userId);
                            pst.executeUpdate();
                        }
                    } else {
                        String insertStaff = "INSERT INTO staff (user_id, s_fname, s_mname, s_lname, s_gender) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement pst = con.prepareStatement(insertStaff)) {
                            pst.setInt(1, userId);
                            pst.setString(2, fName);
                            pst.setString(3, mName);
                            pst.setString(4, lName);
                            pst.setString(5, genderValue);
                            pst.executeUpdate();
                        }
                    }

                    // ✅ Image logic
                    try {
                        File imgFile = new File(imagePath);
                        if (imgFile.exists()) {
                            image.setIcon(ResizeImage(imagePath, null, image));
                        } else {
                            image.setIcon(ResizeImage("src/default/u_blank.jpg", null, image));
                        }
                    } catch (Exception e) {
                        System.out.println("Error loading image: " + e.getMessage());
                        image.setIcon(ResizeImage("src/default/u_blank.jpg", null, image));
                    }
                }

                Session.getInstance().logEvent("EDITED ADMIN PROFILE", "Admin updated their account information.");
                JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                Logger.getLogger(Admin_Profile_Edit.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error updating profile: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
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
       JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/u_images/" + selectedFile.getName();
                path = selectedFile.getAbsolutePath();

                if (FileExistenceChecker(path) == 1) {
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path = "";
                } else {

                    ImageIcon icon = new ImageIcon(path);
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);


                    image.setIcon(new ImageIcon(newImg));
                }
            } catch (Exception ex) {
                System.out.println("File Error! " + ex.getMessage());
            }
        }
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JComboBox<String> Gender;
    private javax.swing.JLabel account;
    private javax.swing.JPanel addProf;
    private javax.swing.JLabel add_prof;
    private javax.swing.JPanel delPanel;
    private javax.swing.JLabel del_prof1;
    private javax.swing.JLabel email;
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
