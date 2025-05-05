
package ADMIN;

import Config.Session;
import java.awt.Color;
import javax.swing.JOptionPane;


public class Admin_Security_Verify extends javax.swing.JFrame {

    public Admin_Security_Verify() {
        initComponents();
    }
    
       
    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputPIN = new javax.swing.JTextField();
        email3 = new javax.swing.JLabel();
        checkPIN = new javax.swing.JPanel();
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
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changePass.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 210, -1));

        inputPIN.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        inputPIN.setForeground(new java.awt.Color(51, 51, 51));
        inputPIN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPINFocusLost(evt);
            }
        });
        inputPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPINActionPerformed(evt);
            }
        });
        jPanel1.add(inputPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 350, 40));

        email3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        email3.setForeground(new java.awt.Color(255, 255, 255));
        email3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email3.setText("Enter the verification PIN sent to your email");
        jPanel1.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 390, 30));

        checkPIN.setBackground(new java.awt.Color(0, 51, 51));
        checkPIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPINMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkPINMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkPINMouseExited(evt);
            }
        });
        checkPIN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete1.setForeground(new java.awt.Color(255, 255, 255));
        delete1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete1.setText("VERIFY");
        checkPIN.add(delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel1.add(checkPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 130, 30));

        security_header.setBackground(new java.awt.Color(255, 255, 255));
        security_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        security_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account1.setBackground(new java.awt.Color(55, 162, 153));
        account1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account1.setForeground(new java.awt.Color(55, 162, 153));
        account1.setText("Verify PIN");
        security_header.add(account1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 210, 50));

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
        delete2.setText("BACK");
        cancel.add(delete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 440));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputPINFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPINFocusLost

    }//GEN-LAST:event_inputPINFocusLost

    private void inputPINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPINActionPerformed

    private void checkPINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPINMouseClicked
        try {
            String inputPin = inputPIN.getText().trim(); // Get user input from the field
            String storedPin = Session.getInstance().getVerificationPin();

            // Check if the user has entered a PIN
            if (inputPin.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>⚠ Please enter the verification PIN.</b></html>",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return; // Exit the method if no input is provided
            }

            // Check if the input PIN matches the stored PIN
            if (inputPin.equals(storedPin)) {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>✅ Verification successful!</b><br>You may now change your password.</html>",
                    "Verified",
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Close current verification frame
                this.dispose();

                // Open the change password frame
                new Admin_Change_Password().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>❌ Incorrect PIN.</b><br>Please try again.</html>",
                    "Verification Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "<html><b>❌ An error occurred during verification:</b><br>" + e.getMessage() + "</html>",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_checkPINMouseClicked

    private void checkPINMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPINMouseEntered
        checkPIN.setBackground(hoverColor);
    }//GEN-LAST:event_checkPINMouseEntered

    private void checkPINMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPINMouseExited
        checkPIN.setBackground(navColor);
    }//GEN-LAST:event_checkPINMouseExited

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        Admin_Security_Email sec = new Admin_Security_Email();
        sec.setVisible(true);
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseEntered
        cancel.setBackground(hoverColor);
    }//GEN-LAST:event_cancelMouseEntered

    private void cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseExited
        cancel.setBackground(navColor);
    }//GEN-LAST:event_cancelMouseExited

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
            java.util.logging.Logger.getLogger(Admin_Security_Verify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Security_Verify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Security_Verify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Security_Verify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Security_Verify().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account1;
    private javax.swing.JPanel cancel;
    private javax.swing.JPanel checkPIN;
    private javax.swing.JLabel delete1;
    private javax.swing.JLabel delete2;
    private javax.swing.JLabel email3;
    private javax.swing.JTextField inputPIN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel security_header;
    // End of variables declaration//GEN-END:variables
}
