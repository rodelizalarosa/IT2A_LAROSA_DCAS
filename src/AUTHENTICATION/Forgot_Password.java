
package AUTHENTICATION;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Forgot_Password extends javax.swing.JFrame {

    public Forgot_Password() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(55, 162, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forgotPass.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 210, -1));

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
        jPanel1.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 350, 40));

        email2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email2.setForeground(new java.awt.Color(255, 255, 255));
        email2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email2.setText("verification PIN for changing your password.");
        jPanel1.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 390, 30));

        email3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email3.setForeground(new java.awt.Color(255, 255, 255));
        email3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email3.setText("Enter your email address to receive a");
        jPanel1.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 390, 30));

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

        jPanel1.add(send, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailFocusLost

    }//GEN-LAST:event_EmailFocusLost

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void sendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMouseClicked
       String emailInputValue = Email.getText().trim(); // Replace with your actual JTextField variable

        if (emailInputValue.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>⚠ Please enter your email address.</b><br>This field cannot be left blank.</html>",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
            );
            return;
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

                Session.getInstance().setUser(0, username, emailInputValue);

                // Send verification PIN
                String pin = EmailSender.sendVerificationPin(emailInputValue, username);
                Session.getInstance().setVerificationPin(pin);

                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>📧 Verification PIN sent successfully!</b><br>Please check your email inbox.</html>",
                    "PIN Sent",
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Redirect to Verify frame
                new Verify().setVisible(true);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>❌ Email not found.</b><br>Please ensure you entered a registered email address.</html>",
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
                "<html><b>❌ Failed to send verification PIN.</b><br>Please check your internet connection or email settings.</html>",
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
            java.util.logging.Logger.getLogger(Forgot_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Forgot_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Forgot_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Forgot_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Forgot_Password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JLabel delete1;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel email3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel send;
    // End of variables declaration//GEN-END:variables
}
