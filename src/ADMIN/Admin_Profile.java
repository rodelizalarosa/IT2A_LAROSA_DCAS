
package ADMIN;

import AUTHENTICATION.*;
import Config.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Profile extends javax.swing.JInternalFrame {

    private JDesktopPane mainDesktop;
     
    public Admin_Profile() {
        initComponents();
        this.mainDesktop = Session.getInstance().getDesktopPane();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    public String destination = "";
      File selectedFile;
      public String path;
      public String oldpath;
      
    
   public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/images", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }

   public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
   
   public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
            if(ImagePath !=null){
                MyImage = new ImageIcon(ImagePath);
            }else{
                MyImage = new ImageIcon(pic);
            }

        int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
   
    private void showProfile(){
         Admin_Profile_Edit edit = new Admin_Profile_Edit();
         mainDesktop.add(edit);
         edit.setVisible(true);
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        profile_header = new javax.swing.JPanel();
        account1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        account = new javax.swing.JLabel();
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
        account1.setText("Admin Account");
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
        jPanel3.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 410, 50));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 50));

        username1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        username1.setForeground(new java.awt.Color(51, 51, 51));
        username1.setText("Username:");
        jPanel2.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 110, 30));

        Role.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Role.setForeground(new java.awt.Color(51, 51, 51));
        Role.setText("role");
        jPanel2.add(Role, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 460, 30));

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
        });
        jPanel2.add(edit_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 170, 40));

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 200, 160));

        email3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email3.setForeground(new java.awt.Color(51, 51, 51));
        email3.setText("Full Name:");
        jPanel2.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 100, 30));

        email4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email4.setForeground(new java.awt.Color(51, 51, 51));
        email4.setText("Email:");
        jPanel2.add(email4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 80, 30));

        email5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email5.setForeground(new java.awt.Color(51, 51, 51));
        email5.setText("Role:");
        jPanel2.add(email5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 80, 30));

        fullname.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        fullname.setForeground(new java.awt.Color(51, 51, 51));
        fullname.setText("full name");
        jPanel2.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 460, 30));

        User.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        User.setForeground(new java.awt.Color(51, 51, 51));
        User.setText("username");
        jPanel2.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 460, 30));

        Email.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Email.setForeground(new java.awt.Color(51, 51, 51));
        Email.setText("email");
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 460, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 830, 380));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edit_profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_profileMouseClicked
        showProfile();
    }//GEN-LAST:event_edit_profileMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
         Session sess = Session.getInstance();
    
        if(sess.getUserId() == 0) {
            JOptionPane.showMessageDialog(null, "No Account, Log in First!", "Notice", JOptionPane.ERROR_MESSAGE);
            LogIn lgf = new LogIn();
            lgf.setVisible(true);
            this.dispose();
        } else {
            try {
                ConnectDB dbc = new ConnectDB();
                ResultSet rs = dbc.getData("SELECT * FROM users WHERE user_id = '" + sess.getUserId() + "'");

                if(rs.next()) {
                    int id = sess.getUserId();
                    String username = rs.getString("u_username");
                    String email = rs.getString("u_email");
                    String role = rs.getString("u_role");
                    String imagePath = rs.getString("u_image");

                    // Check for full name in the staff table
                    ResultSet rsStaff = dbc.getData("SELECT CONCAT(s_fname, ' ', s_lname) AS fullname FROM staff WHERE staff_id = '" + id + "'");

                    String fullName;
                    if(rsStaff.next()) {
                        fullName = rsStaff.getString("fullname");
                    } else {
                        fullName = "Admin user details not yet completed";
                    }

                    // Set retrieved values to the labels
                    fullname.setText(fullName);
                    User.setText("@" + username);
                    Email.setText(email);
                    Role.setText(role);

                     // Set default image if user image is missing
                    String defaultImagePath = "src/default/u_blank.jpg"; // Path to default image
                    String finalImagePath;

                    if (imagePath == null || imagePath.trim().isEmpty()) {
                        finalImagePath = defaultImagePath;
                    } else {
                        finalImagePath = "src/u_images" + imagePath;
                    }

                    image.setIcon(ResizeImage(finalImagePath, null, image));

                    }
            } catch(SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Role;
    private javax.swing.JLabel User;
    private javax.swing.JLabel account;
    private javax.swing.JLabel account1;
    private javax.swing.JLabel edit_profile;
    private javax.swing.JLabel email3;
    private javax.swing.JLabel email4;
    private javax.swing.JLabel email5;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel image;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel profile_header;
    private javax.swing.JLabel username1;
    // End of variables declaration//GEN-END:variables
}
