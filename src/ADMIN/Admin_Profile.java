
package ADMIN;

import AUTHENTICATION.*;
import Config.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Profile extends javax.swing.JInternalFrame {

  
    public Admin_Profile() {
        initComponents();
       
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        formInternalFrameActivated(null); 
    }
    
    private void editProfile(){
        Admin_Profile_Edit edit = new Admin_Profile_Edit();
        JDesktopPane desktop = Session.getInstance().getDesktopSettings();
        desktop.add(edit);
        edit.setVisible(true);
    }
    
     public String destination = "";
     File selectedFile;
     public String path;
     public String oldpath;
    
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
   
   public ImageIcon ResizeImage(String imagePath, byte[] pic, JLabel label) {
        ImageIcon myImage = null;
        BufferedImage img = null;

        try {
            if (imagePath != null) {
                if (imagePath.startsWith("/")) {
                    // Load from resource folder
                    InputStream is = getClass().getResourceAsStream(imagePath);
                    if (is == null) {
                        throw new IOException("Resource not found: " + imagePath);
                    }
                    img = ImageIO.read(is);
                } else {
                    // Load from file system
                    File file = new File(imagePath);
                    if (!file.exists()) {
                        throw new IOException("File not found: " + imagePath);
                    }
                    img = ImageIO.read(file);
                }
            } else if (pic != null) {
                img = ImageIO.read(new ByteArrayInputStream(pic));
            }

            if (imagePath != null) {
            if (imagePath.startsWith("/")) {
                // Resource path (like default image)
                InputStream is = getClass().getResourceAsStream(imagePath);
                if (is == null) {
                    throw new IOException("Resource not found: " + imagePath);
                }
                img = ImageIO.read(is);
            } else {
                // File system path (like uploaded images)
                File file = new File("src/" + imagePath); // ADD "src/" prefix
                if (!file.exists()) {
                    throw new IOException("File not found: " + file.getAbsolutePath());
                }
                img = ImageIO.read(file);
            }
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

          // Check if existingFile is from default
          if (existingFile.getPath().contains("default")) {
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



    Color hover = new Color (0,51,51);
    Color nav = new Color (0,153,153);
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        profile_header = new javax.swing.JPanel();
        account1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        account = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        Role = new javax.swing.JLabel();
        edit_profile = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        email3 = new javax.swing.JLabel();
        email4 = new javax.swing.JLabel();
        email5 = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        User = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        email6 = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();
        email7 = new javax.swing.JLabel();

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
        profile_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account1.setForeground(new java.awt.Color(255, 255, 255));
        account1.setText("Admin Profile");
        profile_header.add(account1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 150, 50));

        jPanel1.add(profile_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account.setForeground(new java.awt.Color(255, 255, 255));
        account.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account.setText("Admin Personal Details");
        jPanel3.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 410, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("See and manage your personal information.");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 490, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 60));

        username1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        username1.setForeground(new java.awt.Color(51, 51, 51));
        username1.setText("Username:");
        jPanel2.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 110, 30));

        Role.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Role.setForeground(new java.awt.Color(51, 51, 51));
        Role.setText("role");
        jPanel2.add(Role, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 250, 30));

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

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, 180));

        email3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email3.setForeground(new java.awt.Color(51, 51, 51));
        email3.setText("Gender:");
        jPanel2.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 100, 30));

        email4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email4.setForeground(new java.awt.Color(51, 51, 51));
        email4.setText("Email:");
        jPanel2.add(email4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 80, 30));

        email5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email5.setForeground(new java.awt.Color(51, 51, 51));
        email5.setText("Role:");
        jPanel2.add(email5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, -1, 30));

        fullname.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        fullname.setForeground(new java.awt.Color(51, 51, 51));
        fullname.setText("full name");
        jPanel2.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 460, 30));

        User.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        User.setForeground(new java.awt.Color(51, 51, 51));
        User.setText("username");
        jPanel2.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 220, 30));

        Email.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Email.setForeground(new java.awt.Color(51, 51, 51));
        Email.setText("email");
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 460, 30));

        email6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email6.setForeground(new java.awt.Color(51, 51, 51));
        email6.setText("Full Name:");
        jPanel2.add(email6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 100, 30));

        Gender.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Gender.setText("gender");
        jPanel2.add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 400, 30));

        userID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        userID.setText("ID");
        jPanel2.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 90, 30));

        email7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email7.setForeground(new java.awt.Color(51, 51, 51));
        email7.setText("Account ID:");
        jPanel2.add(email7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 100, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 830, 380));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edit_profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_profileMouseClicked
        editProfile();
    }//GEN-LAST:event_edit_profileMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        Session sess = Session.getInstance();

        if (sess.getUserId() == 0) {
            JOptionPane.showMessageDialog(null, "No Account, Log in First!", "Notice", JOptionPane.ERROR_MESSAGE);
            LogIn lgf = new LogIn();
            lgf.setVisible(true);
            this.dispose();
        } else {
            try {
                ConnectDB dbc = new ConnectDB();
                ResultSet rs = dbc.getData("SELECT * FROM users WHERE user_id = '" + sess.getUserId() + "'");

                if (rs.next()) {
                    int id = sess.getUserId();
                    String username = rs.getString("u_username");
                    String email = rs.getString("u_email");
                    String role = rs.getString("u_role");
                    String imagePath = rs.getString("u_image");

                    // Set user ID
                    userID.setText(String.valueOf(id)); // Make sure you have a JLabel named userID

                    // Try to get full name and gender from staff table
                    ResultSet rsStaff = dbc.getData("SELECT CONCAT(s_fname, ' ', s_lname) AS fullname, s_gender FROM staff WHERE staff_id = '" + id + "'");

                    String fullName = "Account not yet updated.";
                    String genderText = "Account not yet updated.";

                    if (rsStaff.next()) {
                        fullName = rsStaff.getString("fullname");
                        genderText = rsStaff.getString("s_gender");
                    }

                    fullname.setText(fullName);
                    Gender.setText(genderText); // Make sure gender is a JLabel
                    User.setText("@" + username);
                    Email.setText(email);
                    Role.setText(role);

                    String imagePathFromDB = rs.getString("u_image");
                    ImageIcon imageIcon = null;

                    if (imagePathFromDB == null || imagePathFromDB.trim().isEmpty()) {
                        // Load default image using ResizeImage
                        SwingUtilities.invokeLater(() -> {
                            image.setIcon(ResizeImage("/default/u_blank.jpg", null, image));
                        });
                    } else {
                        // Load uploaded image using ResizeImage
                        SwingUtilities.invokeLater(() -> {
                            image.setIcon(ResizeImage("u_images/" + imagePathFromDB, null, image));
                        });
                    }

              
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void edit_profileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_profileMouseEntered
        edit_profile.setBackground(hover);
    }//GEN-LAST:event_edit_profileMouseEntered

    private void edit_profileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_profileMouseExited
        edit_profile.setBackground(nav);
    }//GEN-LAST:event_edit_profileMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Gender;
    private javax.swing.JLabel Role;
    private javax.swing.JLabel User;
    private javax.swing.JLabel account;
    private javax.swing.JLabel account1;
    private javax.swing.JLabel edit_profile;
    private javax.swing.JLabel email3;
    private javax.swing.JLabel email4;
    private javax.swing.JLabel email5;
    private javax.swing.JLabel email6;
    private javax.swing.JLabel email7;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel profile_header;
    private javax.swing.JLabel userID;
    private javax.swing.JLabel username1;
    // End of variables declaration//GEN-END:variables
}
