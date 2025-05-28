

package AUTHENTICATION;

import ADMIN.Admin_Dashboard;
import Config.ConnectDB;
import Config.Session;
import PATIENT.Patient_Dashboard;
import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;


public class LogIn extends javax.swing.JFrame {

   
    public LogIn() {
        initComponents();
        borderField();
        
        hide.setVisible(true);
        show.setVisible(false);
    }
    
    private void borderField(){
        // Make username transparent with a border
        username.setBackground(new Color(0, 0, 0, 0));
        username.setBorder(new LineBorder(Color.WHITE, 1));

        // Make password transparent with a border
        password.setBackground(new Color(0, 0, 0, 0));
        password.setBorder(new LineBorder(Color.WHITE, 1));
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
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Hashing Error:</b><br>Unable to apply SHA-256 algorithm.</html>",
                "❗ Hashing Failed",
                JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tagline = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        username2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        errorUsername = new javax.swing.JLabel();
        password1 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        errorPassword = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        login_button = new javax.swing.JLabel();
        forgot = new javax.swing.JLabel();
        Register = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        noAcct = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(55, 162, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loginLogo.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 560));

        tagline.setBackground(new java.awt.Color(255, 255, 255));
        tagline.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tagline.setForeground(new java.awt.Color(255, 255, 255));
        tagline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tagline.setText("Effortless Dental Appointments at your fingertips!");
        jPanel1.add(tagline, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 550, 40));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Dental Flow");
        jPanel1.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 550, 60));

        username2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        username2.setForeground(new java.awt.Color(255, 255, 255));
        username2.setText("Username");
        jPanel1.add(username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 120, 40));

        username.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                usernameMouseReleased(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 350, 40));

        errorUsername.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(errorUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, 340, 20));

        password1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        password1.setForeground(new java.awt.Color(255, 255, 255));
        password1.setText("Password");
        jPanel1.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, 90, 40));

        password.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        password.setForeground(new java.awt.Color(255, 255, 255));
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFocusLost(evt);
            }
        });
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                passwordMouseReleased(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 350, 40));

        errorPassword.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(errorPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 340, 20));

        show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/show.png"))); // NOI18N
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showMousePressed(evt);
            }
        });
        jPanel1.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 330, 40, 40));

        login_button.setBackground(new java.awt.Color(0, 153, 153));
        login_button.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        login_button.setForeground(new java.awt.Color(255, 255, 255));
        login_button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        login_button.setText("LOG IN");
        login_button.setOpaque(true);
        login_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login_buttonMouseClicked(evt);
            }
        });
        jPanel1.add(login_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 420, 350, 40));

        forgot.setBackground(new java.awt.Color(255, 255, 255));
        forgot.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        forgot.setForeground(new java.awt.Color(255, 255, 255));
        forgot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        forgot.setText("FORGOT PASSWORD");
        forgot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotMouseEntered(evt);
            }
        });
        jPanel1.add(forgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 460, 180, 30));

        Register.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        Register.setForeground(new java.awt.Color(204, 255, 255));
        Register.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Register.setText("CREATE ACCOUNT");
        Register.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterMouseClicked(evt);
            }
        });
        jPanel1.add(Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 490, 180, 30));

        hide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hide.png"))); // NOI18N
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideMousePressed(evt);
            }
        });
        jPanel1.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 330, 40, 40));

        noAcct.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        noAcct.setForeground(new java.awt.Color(255, 255, 255));
        noAcct.setText("Don't have an account?");
        jPanel1.add(noAcct, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 490, 180, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost

        String user = username.getText();

        if (user.isEmpty()) {
            errorUsername.setForeground(Color.RED);
            errorUsername.setText("Username is required");
            errorUsername.setForeground(Color.RED);
        } else {
            errorUsername.setForeground(Color.BLACK);
            errorUsername.setText("");
        }

        username.repaint();
    }//GEN-LAST:event_usernameFocusLost

    private void usernameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseReleased
        //        username.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12), new java.awt.Color(0, 0, 0)));
        //        errorUsername.setText("");
    }//GEN-LAST:event_usernameMouseReleased

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        show.setVisible(false);
    }//GEN-LAST:event_passwordFocusGained

    private void passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusLost

        String pass = password.getText();

        if (pass.isEmpty()) {
            errorPassword.setForeground(Color.RED);
            errorPassword.setText("Password is required");
            errorPassword.setForeground(Color.RED);
        } else {
            errorPassword.setForeground(Color.BLACK);
            errorPassword.setText("");
        }

        password.repaint();
        hide.setVisible(true);
        show.setVisible(false);
    }//GEN-LAST:event_passwordFocusLost

    private void passwordMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseReleased
       
    }//GEN-LAST:event_passwordMouseReleased

    private void login_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_buttonMouseClicked
        String user = username.getText().trim();
        String pass = new String(password.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please fill in both fields.</b><br>Username and password are required.</html>",
                "❗ Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        ConnectDB db = new ConnectDB();
        Connection con = db.getConnection();
        Session sess = Session.getInstance();
        String sql = "SELECT user_id, u_password, u_status, u_role FROM users WHERE u_username = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("u_password");
                String status = rs.getString("u_status");
                String roleFromDB = rs.getString("u_role");
                int userId = rs.getInt("user_id");

                if (storedPassword.equals(hashPassword(pass))) {
                    if ("Pending".equalsIgnoreCase(status)) {
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Your account is pending approval.</b><br>Please wait for the account activation email confirmation.</html>",
                            "Login Pending",
                            JOptionPane.WARNING_MESSAGE
                        );

                    } else {
                        sess.setUser(userId, user, roleFromDB);
                        sess.logEvent("LOGIN", "User logged in");

                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Login successful!</b><br>You are logged in as <b>" + roleFromDB + "</b>.</html>",
                            "Login Success",
                            JOptionPane.INFORMATION_MESSAGE
                        );

                        this.dispose();

                        if ("Admin".equalsIgnoreCase(roleFromDB)) {
                            new Admin_Dashboard().setVisible(true);
                        } else if ("Patient".equalsIgnoreCase(roleFromDB)) {
                            new Patient_Dashboard().setVisible(true);
                        } else if ("Dentist".equalsIgnoreCase(roleFromDB)) {
                            // new Dentist_Dashboard().setVisible(true); // Enable this when implemented
                        }
                    }
                } else {
                    sess.logEvent("LOGIN", "User attempted login with incorrect password");
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>Invalid username or password.</b><br>Please try again.</html>",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                sess.logEvent("LOGIN", "Unknown username attempted login");
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>Invalid username or password.</b><br>User not found.</html>",
                    "❌ Login Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Database error:</b><br>" + ex.getMessage() + "</html>",
                "❗ SQL Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_login_buttonMouseClicked

    private void RegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterMouseClicked
        Register reg = new Register();
        reg.setLocationRelativeTo(null);
        reg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegisterMouseClicked

    private void showMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMousePressed
        hide.setVisible(true);
        show.setVisible(false);
        password.setEchoChar('*');
    }//GEN-LAST:event_showMousePressed

    private void hideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMousePressed
        show.setVisible(true);
        hide.setVisible(false);
        password.setEchoChar((char) 0);
    }//GEN-LAST:event_hideMousePressed

    private void forgotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotMouseEntered
       
    }//GEN-LAST:event_forgotMouseEntered

    private void forgotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotMouseClicked
        Forgot_Password pass = new Forgot_Password();
        pass.setVisible(true);
    }//GEN-LAST:event_forgotMouseClicked

    
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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Register;
    private javax.swing.JLabel errorPassword;
    private javax.swing.JLabel errorUsername;
    private javax.swing.JLabel forgot;
    private javax.swing.JLabel hide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel login_button;
    private javax.swing.JLabel noAcct;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel password1;
    private javax.swing.JLabel show;
    private javax.swing.JLabel tagline;
    private javax.swing.JLabel title;
    private javax.swing.JTextField username;
    private javax.swing.JLabel username2;
    // End of variables declaration//GEN-END:variables

}
