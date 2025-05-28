
package PATIENT;

import AUTHENTICATION.EmailSender;
import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class Patient_Security_Email extends javax.swing.JFrame {

    public Patient_Security_Email() {
        initComponents();
    }
       
    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        email2 = new javax.swing.JLabel();
        email3 = new javax.swing.JLabel();
        send = new javax.swing.JPanel();
        delete1 = new javax.swing.JLabel();
        security_header = new javax.swing.JPanel();
        account1 = new javax.swing.JLabel();
        cancel = new javax.swing.JPanel();
        delete2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(55, 162, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forgotPass.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 210, -1));

        Email.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        Email.setForeground(new java.awt.Color(51, 51, 51));
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
        jPanel1.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 350, 40));

        email2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email2.setForeground(new java.awt.Color(255, 255, 255));
        email2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email2.setText("verification PIN for changing your password.");
        jPanel1.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 390, 30));

        email3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email3.setForeground(new java.awt.Color(255, 255, 255));
        email3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email3.setText("Enter your email address to receive a");
        jPanel1.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 390, 30));

        send.setBackground(new java.awt.Color(0, 51, 51));
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendMouseExited(evt);
            }
        });
        send.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete1.setForeground(new java.awt.Color(255, 255, 255));
        delete1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete1.setText("SEND");
        send.add(delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel1.add(send, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 130, 30));

        security_header.setBackground(new java.awt.Color(255, 255, 255));
        security_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        security_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account1.setBackground(new java.awt.Color(55, 162, 153));
        account1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account1.setForeground(new java.awt.Color(55, 162, 153));
        account1.setText("Patient Security Settings");
        security_header.add(account1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 290, 50));

        jPanel1.add(security_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 560, 50));

        cancel.setBackground(new java.awt.Color(0, 51, 51));
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelMouseExited(evt);
            }
        });
        cancel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete2.setForeground(new java.awt.Color(255, 255, 255));
        delete2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete2.setText("CANCEL");
        cancel.add(delete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailFocusLost

    }//GEN-LAST:event_EmailFocusLost

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed

    }//GEN-LAST:event_EmailActionPerformed

    private void sendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMouseClicked
        String emailInputValue = Email.getText().trim(); // Replace with your actual JTextField variable

        // Step 1: Validate email format using regular expression
        if (!emailInputValue.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Invalid email format.</b><br>Please enter a valid email address.</html>",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
            );
            return; // Exit if the email is not valid
        }

        // Step 2: Check if email input is empty
        if (emailInputValue.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please enter your email address.</b><br>This field cannot be left blank.</html>",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
            );
            return; // Exit if the email is empty
        }

        try {

            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            String sql = "SELECT u_username FROM users WHERE u_email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, emailInputValue);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("u_username");

                // Set the user session
                Session.getInstance().setUser(0, username, emailInputValue);

                // Send the verification PIN
                String pin = EmailSender.sendVerificationPin(emailInputValue, username);
                Session.getInstance().setVerificationPin(pin);

                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>?Verification PIN sent successfully!</b><br>Please check your email inbox.</html>",
                    "PIN Sent",
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Redirect to Verify frame
                new Patient_Security_Verify().setVisible(true);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>Email not found.</b><br>Please ensure you entered a registered email address.</html>",
                    "User Not Found",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Failed to send verification PIN.</b><br>Please check your internet connection or email settings.</html>",
                "Email Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_sendMouseClicked

    private void sendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMouseEntered
        send.setBackground(hoverColor);
    }//GEN-LAST:event_sendMouseEntered

    private void sendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMouseExited
        send.setBackground(navColor);
    }//GEN-LAST:event_sendMouseExited

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        Patient_Security sec = new Patient_Security();
        sec.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseEntered
        cancel.setBackground(hoverColor);
    }//GEN-LAST:event_cancelMouseEntered

    private void cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseExited
        cancel.setBackground(navColor);
    }//GEN-LAST:event_cancelMouseExited


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
            java.util.logging.Logger.getLogger(Patient_Security_Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Patient_Security_Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Patient_Security_Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Patient_Security_Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Patient_Security_Email().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JLabel account1;
    private javax.swing.JPanel cancel;
    private javax.swing.JLabel delete1;
    private javax.swing.JLabel delete2;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel email3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel security_header;
    private javax.swing.JPanel send;
    // End of variables declaration//GEN-END:variables
}
