
package PRINT;

import ADMIN.Admin_View_Appointment;
import AUTHENTICATION.EmailSender;
import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class Decline_Reason extends javax.swing.JFrame {

    
    public Decline_Reason() {
        initComponents();
    }

    
    public Decline_Reason(String appointmentId, String patientName) {
        initComponents();
        appointmentID.setText(appointmentId);
        appointmentID.setEditable(false);

        patient.setText(patientName);
        patient.setEditable(false);
    }

    
    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sendReason = new javax.swing.JPanel();
        delete1 = new javax.swing.JLabel();
        cancel = new javax.swing.JPanel();
        delete = new javax.swing.JLabel();
        appointment4 = new javax.swing.JLabel();
        appointment3 = new javax.swing.JLabel();
        patient = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        reason = new javax.swing.JTextArea();
        appointment5 = new javax.swing.JLabel();
        appointmentID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(55, 162, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        user.setText("Decline an Appointment");
        jPanel2.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 540, 40));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Please state the reason of declining.");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 480, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 50));

        sendReason.setBackground(new java.awt.Color(0, 51, 51));
        sendReason.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendReasonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendReasonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendReasonMouseExited(evt);
            }
        });
        sendReason.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete1.setForeground(new java.awt.Color(255, 255, 255));
        delete1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete1.setText("SEND");
        sendReason.add(delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel1.add(sendReason, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 130, 30));

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

        delete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete.setText("CANCEL");
        cancel.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 130, 30));

        appointment4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment4.setForeground(new java.awt.Color(51, 51, 51));
        appointment4.setText("Appointment ID:");
        jPanel1.add(appointment4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 160, 30));

        appointment3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment3.setForeground(new java.awt.Color(51, 51, 51));
        appointment3.setText("Reason:");
        jPanel1.add(appointment3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 160, 30));

        patient.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        patient.setForeground(new java.awt.Color(51, 51, 51));
        patient.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(patient, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 380, 30));

        reason.setColumns(20);
        reason.setRows(5);
        jScrollPane1.setViewportView(reason);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 380, 160));

        appointment5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment5.setForeground(new java.awt.Color(51, 51, 51));
        appointment5.setText("Patient Name:");
        jPanel1.add(appointment5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 160, 30));

        appointmentID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointmentID.setForeground(new java.awt.Color(51, 51, 51));
        appointmentID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(appointmentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 100, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 380));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
       Admin_View_Appointment view = new Admin_View_Appointment();
       view.setVisible(true);
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseEntered
       cancel.setBackground(hoverColor);
    }//GEN-LAST:event_cancelMouseEntered

    private void cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseExited
        cancel.setBackground(navColor);
    }//GEN-LAST:event_cancelMouseExited

    private void sendReasonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendReasonMouseClicked
            try {
            // Ensure patient email is retrieved from the DB and stored in the Session
            int patientId = Session.getInstance().getPatientId();
            try (Connection con = ConnectDB.getConnection();
                 PreparedStatement pstmt = con.prepareStatement("SELECT p_email FROM patients WHERE patient_id = ?")) {
                pstmt.setInt(1, patientId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String email = rs.getString("p_email");
                        Session.getInstance().setPatientEmail(email); // Set it in the session
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>❌ Failed to retrieve patient email.</b></html>",
                    "Data Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return; // Exit if email fetch fails
            }

            // Now get the email from the session
            String patientEmail = Session.getInstance().getPatientEmail();
            String patientName = Session.getInstance().getPatientFullName();
            String reasonSend = reason.getText().trim(); // Your JTextArea for input
            String date = Session.getInstance().getAppointmentDate();
            String time = Session.getInstance().getAppointmentTime();

            if (reasonSend.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "<html><b>⚠️ Please enter a reason for declining.</b><br>The message cannot be sent without a reason.</html>",
                    "Missing Reason",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Now send the email
            EmailSender.sendDeclineReason(patientEmail, patientName, reasonSend, date, time);

            JOptionPane.showMessageDialog(
                this,
                "<html><b>📧 Decline reason sent successfully!</b><br>The patient has been notified via email.</html>",
                "Email Sent",
                JOptionPane.INFORMATION_MESSAGE
            );

            this.dispose(); // Close the Decline_Reason frame

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "<html><b>❌ Failed to send email.</b><br>Please check your internet connection or email settings.</html>",
                "Email Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_sendReasonMouseClicked

    private void sendReasonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendReasonMouseEntered
        sendReason.setBackground(hoverColor);
    }//GEN-LAST:event_sendReasonMouseEntered

    private void sendReasonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendReasonMouseExited
         sendReason.setBackground(navColor);
    }//GEN-LAST:event_sendReasonMouseExited

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
            java.util.logging.Logger.getLogger(Decline_Reason.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Decline_Reason.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Decline_Reason.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Decline_Reason.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Decline_Reason().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appointment3;
    private javax.swing.JLabel appointment4;
    private javax.swing.JLabel appointment5;
    private javax.swing.JTextField appointmentID;
    private javax.swing.JPanel cancel;
    private javax.swing.JLabel delete;
    private javax.swing.JLabel delete1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField patient;
    private javax.swing.JTextArea reason;
    private javax.swing.JPanel sendReason;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
