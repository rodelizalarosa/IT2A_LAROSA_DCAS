
package PATIENT;

import AUTHENTICATION.Register;
import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.awt.Dimension;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Patient_Profile_Edit extends javax.swing.JInternalFrame {

    
    private int userId;
    
    public Patient_Profile_Edit() {
        initComponents();
        loadPatientProfile();
        
         //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    private void loadPatientProfile() {
        int userId = Session.getInstance().getUserId();

        try (Connection con = ConnectDB.getConnection()) {

            // --- Get from users table ---
            String userSql = "SELECT u_username, u_email, u_image FROM users WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(userSql)) {
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("u_username");
                    String email = rs.getString("u_email");
                    String img = rs.getString("u_image");

                    userName.setText(username); // Set username
                    Email.setText(email); // Set email

                    String imagePath;
                    if (img == null || img.isEmpty()) {
                        imagePath = "src/default/u_blank.jpg"; // Default image if none exists
                    } else {
                        imagePath = "src/u_images/" + img;
                    }

                    String finalImagePath = imagePath;

                    SwingUtilities.invokeLater(() -> {
                        ImageIcon icon = new ImageIcon(finalImagePath);
                        int width = image.getWidth();
                        int height = image.getHeight();

                        if (width <= 0 || height <= 0) {
                            Dimension size = image.getPreferredSize();
                            width = (size.width > 0) ? size.width : 120;
                            height = (size.height > 0) ? size.height : 120;
                        }

                        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        image.setIcon(new ImageIcon(scaledImage));

                        selectedImagePath = finalImagePath;
                        currentImageFromDB = img;
                    });
                }
            }

            // --- Get from patients table ---
            String patientSql = "SELECT p_fname, p_lname, p_contactNumber, p_gender, p_dob FROM patients WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(patientSql)) {
                pst.setInt(1, userId);
                ResultSet rsPatient = pst.executeQuery();
                if (rsPatient.next()) {
                    firstName.setText(rsPatient.getString("p_fname"));
                    lastName.setText(rsPatient.getString("p_lname"));
                    Phone.setText(rsPatient.getString("p_contactNumber"));
                    Gender.setSelectedItem(rsPatient.getString("p_gender"));

                    String dobString = rsPatient.getString("p_dob");
                    try {
                        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobString);
                        Birth.setDate(dob);
                    } catch (ParseException pe) {
                        Birth.setDate(null);
                        JOptionPane.showMessageDialog(this, "<html><b>Invalid birth date format:</b><br>" + pe.getMessage() + "</html>", "Date Parse Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "<html><b>Error loading patient profile:</b><br>" + e.getMessage() + "</html>", "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    
    private boolean isAllFieldsEmpty() {
    return firstName.getText().trim().isEmpty() &&
            lastName.getText().trim().isEmpty() &&
            userName.getText().trim().isEmpty() &&
            Phone.getText().trim().isEmpty() &&
            Email.getText().trim().isEmpty() &&
            Gender.getSelectedIndex() == 0 &&
            Birth.getDate() == null;
    }
    
    //VALIDATIONS
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{11}");
    }
    
    private boolean isValidBirthDate(Date birthDate) {
        if (birthDate == null) return false;
        Date today = new Date();
        return !birthDate.after(today); // true if birthDate is today or before
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
    
    //IS TAKEN
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

    
    
    Color hover = new Color (0,51,51);
    Color nav = new Color (0,153,153);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        profile_header = new javax.swing.JPanel();
        account = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        account1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        middlename = new javax.swing.JLabel();
        middleName = new javax.swing.JTextField();
        lastname = new javax.swing.JLabel();
        lastName = new javax.swing.JTextField();
        email3 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        email = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        gender = new javax.swing.JLabel();
        Gender = new javax.swing.JComboBox<>();
        email1 = new javax.swing.JLabel();
        Phone = new javax.swing.JTextField();
        Birth = new com.toedter.calendar.JDateChooser();
        gender1 = new javax.swing.JLabel();
        delPanel = new javax.swing.JPanel();
        del_prof1 = new javax.swing.JLabel();
        addProf = new javax.swing.JPanel();
        add_prof = new javax.swing.JLabel();
        save = new javax.swing.JLabel();
        errorFirst = new javax.swing.JLabel();
        errorLast = new javax.swing.JLabel();
        errorUser = new javax.swing.JLabel();
        errorPhone = new javax.swing.JLabel();
        errorBirth = new javax.swing.JLabel();
        errorGender = new javax.swing.JLabel();
        errorEmail = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile_header.setBackground(new java.awt.Color(55, 162, 153));
        profile_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        profile_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account.setForeground(new java.awt.Color(255, 255, 255));
        account.setText("Edit Patient Details");
        profile_header.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 200, 50));

        jPanel1.add(profile_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account1.setForeground(new java.awt.Color(255, 255, 255));
        account1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account1.setText("Patient Personal Details");
        jPanel3.add(account1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 410, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("See and manage your personal information.");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 490, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 60));

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 200, 170));

        username2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        username2.setForeground(new java.awt.Color(51, 51, 51));
        username2.setText("First Name");
        jPanel2.add(username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 80, 30));

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
        jPanel2.add(firstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 270, 40));

        middlename.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        middlename.setForeground(new java.awt.Color(51, 51, 51));
        middlename.setText("Middle Name");
        jPanel2.add(middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 100, 30));

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
        jPanel2.add(middleName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 270, 40));

        lastname.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lastname.setForeground(new java.awt.Color(51, 51, 51));
        lastname.setText("Last Name");
        jPanel2.add(lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 80, 30));

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
        jPanel2.add(lastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 270, 40));

        email3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email3.setForeground(new java.awt.Color(51, 51, 51));
        email3.setText("Username");
        jPanel2.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 80, 30));

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
        jPanel2.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 270, 40));

        email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(51, 51, 51));
        email.setText("Email");
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 80, 30));

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
        jPanel2.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, 270, 40));

        gender.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        gender.setForeground(new java.awt.Color(51, 51, 51));
        gender.setText("Date of Birth");
        jPanel2.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 120, 30));

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
        jPanel2.add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 270, 40));

        email1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email1.setForeground(new java.awt.Color(51, 51, 51));
        email1.setText("Phone Number");
        jPanel2.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 120, 30));

        Phone.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        Phone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneFocusLost(evt);
            }
        });
        Phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneActionPerformed(evt);
            }
        });
        jPanel2.add(Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 270, 40));

        Birth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                BirthFocusLost(evt);
            }
        });
        jPanel2.add(Birth, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 270, 40));

        gender1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        gender1.setForeground(new java.awt.Color(51, 51, 51));
        gender1.setText("Gender");
        jPanel2.add(gender1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 80, 30));

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

        jPanel2.add(addProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 100, -1));

        save.setBackground(new java.awt.Color(0, 153, 153));
        save.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        save.setText("SAVE CHANGES ");
        save.setOpaque(true);
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });
        jPanel2.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 200, 40));
        jPanel2.add(errorFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 210, 20));
        jPanel2.add(errorLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 220, 20));
        jPanel2.add(errorUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 220, 20));
        jPanel2.add(errorPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 220, 20));
        jPanel2.add(errorBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 220, 20));
        jPanel2.add(errorGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 220, 20));
        jPanel2.add(errorEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 220, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 850, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        ConnectDB connect = new ConnectDB();
        int userId = Session.getInstance().getUserId(); // Get logged-in user's ID

        String firstNameText = firstName.getText().trim();
        String middleNameText = middleName.getText().trim();
        String lastNameText = lastName.getText().trim();
        String phoneText = Phone.getText().trim();
        String genderText = (String) Gender.getSelectedItem();
        Date birthDate = Birth.getDate();

        StringBuilder errorMessage = new StringBuilder();

        // Validation checks
        if (isAllFieldsEmpty()) {
            errorMessage.append("Please fill out the registration form.\n");
        } else {
            if (firstNameText.isEmpty()) errorMessage.append("First name is required.\n");
            if (lastNameText.isEmpty()) errorMessage.append("Last name is required.\n");

            if (phoneText.isEmpty()) {
                errorMessage.append("Phone number is required.\n");
            } else if (!isValidPhone(phoneText)) {
                errorMessage.append("Phone number must be exactly 11 digits.\n");
            }

            if (genderText == null || genderText.equals("Select gender")) {
                errorMessage.append("Please select a valid gender.\n");
            }

            if (!isValidBirthDate(birthDate)) {
                errorMessage.append("Birthdate must not be in the future.\n");
            }
        }

        // Show error message if validation fails
        if (errorMessage.length() > 0) {
            JOptionPane.showMessageDialog(this, "<html><b>Validation Error:</b><br>" + errorMessage.toString().replace("\n", "<br>") + "</html>", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Assuming no image update (use current image path from DB)
        String destinationImagePath = currentImageFromDB;

        // Check if patient record exists
        boolean patientExists = false;
        String checkSql = "SELECT COUNT(*) FROM patients WHERE user_id = ?";
        try (PreparedStatement pst = connect.getConnection().prepareStatement(checkSql)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                patientExists = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "<html><b>Error checking patient record:</b><br>" + ex.getMessage() + "</html>", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // INSERT or UPDATE patient record
        String sql = patientExists ? 
            "UPDATE patients SET p_fname = ?, p_mname = ?, p_lname = ?, p_contactNumber = ?, p_gender = ?, p_dob = ? WHERE user_id = ?" :
            "INSERT INTO patients (p_fname, p_mname, p_lname, p_contactNumber, p_gender, p_dob, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = connect.getConnection().prepareStatement(sql)) {
            pst.setString(1, firstNameText);
            pst.setString(2, middleNameText);
            pst.setString(3, lastNameText);
            pst.setString(4, phoneText);
            pst.setString(5, genderText);
            pst.setDate(6, new java.sql.Date(birthDate.getTime()));
            pst.setInt(7, userId);

            pst.executeUpdate();

            // Log event
            Session.getInstance().logEvent(patientExists ? "UPDATED PATIENT PROFILE" : "CREATED PATIENT PROFILE", "Patient " + (patientExists ? "updated" : "created") + " their information.");

            // Show success message
            JOptionPane.showMessageDialog(this, "<html><b>Success!</b><br>Patient profile " + (patientExists ? "updated" : "created") + " successfully.</html>", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "<html><b>Error saving patient record:</b><br>" + ex.getMessage() + "</html>", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update session with the latest patient name
        Session.getInstance().setPatientName(firstNameText, lastNameText);

        // Update the image (if exists)
        try {
            File imgFile = new File(destinationImagePath);
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(destinationImagePath);
                Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                image.setIcon(new ImageIcon(img));
            } else {
                loadDefaultImage(); // Load default image if file does not exist
            }
        } catch (Exception e) {
            loadDefaultImage();
            System.out.println("Error displaying image: " + e.getMessage());
        }

        // Log event and notify success
        Session.getInstance().logEvent("UPDATED PATIENT PROFILE", "Patient updated their profile.");
        JOptionPane.showMessageDialog(this, "<html><b>Updated Profile Successfully!</b><br>Your information has been saved.</html>", "Success", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_saveMouseClicked

    private void add_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_profMouseClicked
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            selectedImagePath = selectedFile.getAbsolutePath();

            // Save the selected image to the folder and get the new image path
            String newImagePath = saveImageToFolder(selectedFile);

            if (newImagePath != null) {
                // Show preview in the correct JLabel
                ImageIcon icon = new ImageIcon(newImagePath);
                Image img = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                image.setIcon(new ImageIcon(img));
                
                int userId = Session.getInstance().getUserId();

                // Update the image path in the database for this user
                updateUserImagePath(userId, newImagePath);

                // Update the internal state to reflect the new image
                currentImageFromDB = newImagePath;
            }
        }
    }//GEN-LAST:event_add_profMouseClicked

    private void firstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFocusLost
        String em = firstName.getText();

        if (em.isEmpty()) {
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

    private void middleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middleNameFocusLost

    }//GEN-LAST:event_middleNameFocusLost

    private void middleNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_middleNameActionPerformed

    private void lastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFocusLost
        String em = lastName.getText();

        if (em.isEmpty()) {
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

    private void PhoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhoneFocusLost
        String em = Phone.getText();

        if (em.isEmpty()) {
            errorPhone.setForeground(Color.RED);
            errorPhone.setText("Phone Number is required");
            errorPhone.setForeground(Color.RED);
        } else {
            errorPhone.setForeground(Color.BLACK);
            errorPhone.setText("");
        }

        Phone.repaint();
    }//GEN-LAST:event_PhoneFocusLost

    private void PhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneActionPerformed

    private void BirthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BirthFocusLost
        if (Birth.getDate() == null) {
            errorBirth.setForeground(Color.RED);
            errorBirth.setText("Please select your date of birth");
        } else {
            errorBirth.setForeground(Color.BLACK);
            errorBirth.setText("");
        }

        Birth.repaint();
    }//GEN-LAST:event_BirthFocusLost

    private void delPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delPanelMouseClicked
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
    }//GEN-LAST:event_delPanelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Birth;
    private javax.swing.JTextField Email;
    private javax.swing.JComboBox<String> Gender;
    private javax.swing.JTextField Phone;
    private javax.swing.JLabel account;
    private javax.swing.JLabel account1;
    private javax.swing.JPanel addProf;
    private javax.swing.JLabel add_prof;
    private javax.swing.JPanel delPanel;
    private javax.swing.JLabel del_prof1;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
    private javax.swing.JLabel email3;
    private javax.swing.JLabel errorBirth;
    private javax.swing.JLabel errorEmail;
    private javax.swing.JLabel errorFirst;
    private javax.swing.JLabel errorGender;
    private javax.swing.JLabel errorLast;
    private javax.swing.JLabel errorPhone;
    private javax.swing.JLabel errorUser;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel gender;
    private javax.swing.JLabel gender1;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lastName;
    private javax.swing.JLabel lastname;
    private javax.swing.JTextField middleName;
    private javax.swing.JLabel middlename;
    private javax.swing.JPanel profile_header;
    private javax.swing.JLabel save;
    private javax.swing.JTextField userName;
    private javax.swing.JLabel username2;
    // End of variables declaration//GEN-END:variables
}
