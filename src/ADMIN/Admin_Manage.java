
package ADMIN;

import AUTHENTICATION.LogIn;
import Config.Session;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Admin_Manage extends javax.swing.JFrame {

    public Admin_Manage() {
        initComponents();
        showUser();
    }
    
     private void showUser(){     
        Admin_User_Internal user = new Admin_User_Internal();
        parentDesktop.add(user);
        user.setVisible(true);
    }
    
    private void showPatient() {
        Admin_Patient_Internal patient = new Admin_Patient_Internal();
        parentDesktop.add(patient);
        patient.setVisible(true);
    }
    
    private void showStaff(){
        Admin_Staff_Internal staff = new Admin_Staff_Internal();
        parentDesktop.add(staff);
        staff.setVisible(true);
    }
    
    private void showDoctor(){
        Admin_Doctor_Internal doc = new Admin_Doctor_Internal();
        parentDesktop.add(doc);
        doc.setVisible(true);
    }
    
    Color hoverColor = new Color (0,153,153);
    Color navColor = new Color (55,162,153);
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        navigation = new javax.swing.JPanel();
        manage = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        doctorPanel = new javax.swing.JPanel();
        doctor = new javax.swing.JLabel();
        userPanel = new javax.swing.JPanel();
        users1 = new javax.swing.JLabel();
        patientPanel = new javax.swing.JPanel();
        patient = new javax.swing.JLabel();
        logoutPanel = new javax.swing.JPanel();
        logout = new javax.swing.JLabel();
        logoutLogo = new javax.swing.JLabel();
        staffPanel = new javax.swing.JPanel();
        staff = new javax.swing.JLabel();
        parentDesktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navigation.setBackground(new java.awt.Color(55, 162, 153));
        navigation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        navigation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        manage.setForeground(new java.awt.Color(255, 255, 255));
        manage.setText("  Manage");
        navigation.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 150, 80));

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        navigation.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));

        doctorPanel.setBackground(new java.awt.Color(55, 162, 153));
        doctorPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doctorPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                doctorPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                doctorPanelMouseExited(evt);
            }
        });
        doctorPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doctor.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        doctor.setForeground(new java.awt.Color(255, 255, 255));
        doctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/doctor.png"))); // NOI18N
        doctor.setText("  Doctor");
        doctorPanel.add(doctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));

        navigation.add(doctorPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 160, 40));

        userPanel.setBackground(new java.awt.Color(55, 162, 153));
        userPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userPanelMouseExited(evt);
            }
        });
        userPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        users1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        users1.setForeground(new java.awt.Color(255, 255, 255));
        users1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manageUser.png"))); // NOI18N
        users1.setText("  Users");
        userPanel.add(users1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));

        navigation.add(userPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 160, 40));

        patientPanel.setBackground(new java.awt.Color(55, 162, 153));
        patientPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                patientPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                patientPanelMouseExited(evt);
            }
        });
        patientPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        patient.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        patient.setForeground(new java.awt.Color(255, 255, 255));
        patient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/patient.png"))); // NOI18N
        patient.setText("  Patients");
        patientPanel.add(patient, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));

        navigation.add(patientPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 160, 40));

        logoutPanel.setBackground(new java.awt.Color(0, 51, 51));
        logoutPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutPanelMouseExited(evt);
            }
        });
        logoutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logout.setText("Logout");
        logoutPanel.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 70, 30));
        logoutPanel.add(logoutLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        navigation.add(logoutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 140, 30));

        staffPanel.setBackground(new java.awt.Color(55, 162, 153));
        staffPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                staffPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                staffPanelMouseExited(evt);
            }
        });
        staffPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        staff.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        staff.setForeground(new java.awt.Color(255, 255, 255));
        staff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/staff.png"))); // NOI18N
        staff.setText("  Staff");
        staffPanel.add(staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));

        navigation.add(staffPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 160, 40));

        jPanel1.add(navigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 560));

        parentDesktop.setBackground(new java.awt.Color(255, 255, 255));
        parentDesktop.setLayout(new java.awt.BorderLayout());
        jPanel1.add(parentDesktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 880, 560));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Admin_Dashboard dash = new Admin_Dashboard();
        dash.setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void userPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanelMouseEntered
         userPanel.setBackground(hoverColor);
    }//GEN-LAST:event_userPanelMouseEntered

    private void userPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanelMouseExited
         userPanel.setBackground(navColor);
    }//GEN-LAST:event_userPanelMouseExited

    private void userPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanelMouseClicked
        showUser();
    }//GEN-LAST:event_userPanelMouseClicked

    private void patientPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseEntered
        patientPanel.setBackground(hoverColor);
    }//GEN-LAST:event_patientPanelMouseEntered

    private void patientPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseExited
        patientPanel.setBackground(navColor);
    }//GEN-LAST:event_patientPanelMouseExited

    private void patientPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientPanelMouseClicked
       showPatient();
    }//GEN-LAST:event_patientPanelMouseClicked

    private void staffPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffPanelMouseEntered
         staffPanel.setBackground(hoverColor);
    }//GEN-LAST:event_staffPanelMouseEntered

    private void staffPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffPanelMouseExited
        staffPanel.setBackground(navColor);
    }//GEN-LAST:event_staffPanelMouseExited

    private void staffPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffPanelMouseClicked
        showStaff();
    }//GEN-LAST:event_staffPanelMouseClicked

    private void doctorPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseEntered
         doctorPanel.setBackground(hoverColor);
    }//GEN-LAST:event_doctorPanelMouseEntered

    private void doctorPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseExited
         doctorPanel.setBackground(navColor);
    }//GEN-LAST:event_doctorPanelMouseExited

    private void doctorPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctorPanelMouseClicked
        showDoctor();
    }//GEN-LAST:event_doctorPanelMouseClicked

    private void logoutPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseClicked

        Session sess = Session.getInstance();
        sess.logEvent("LOGOUT", "User logged out");

        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to log out?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon(getClass().getResource("/images/logout.png"))
        );

        if (choice == JOptionPane.YES_OPTION) {
            LogIn lg = new LogIn();
            lg.setLocationRelativeTo(null);
            lg.setVisible(true);

            this.dispose();
        }
    }//GEN-LAST:event_logoutPanelMouseClicked

    private void logoutPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseEntered
        logoutPanel.setBackground(hoverColor);
    }//GEN-LAST:event_logoutPanelMouseEntered

    private void logoutPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseExited
        logoutPanel.setBackground(navColor);
    }//GEN-LAST:event_logoutPanelMouseExited

 
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
            java.util.logging.Logger.getLogger(Admin_Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Manage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel doctor;
    private javax.swing.JPanel doctorPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel logoutLogo;
    private javax.swing.JPanel logoutPanel;
    private javax.swing.JLabel manage;
    private javax.swing.JPanel navigation;
    private javax.swing.JDesktopPane parentDesktop;
    private javax.swing.JLabel patient;
    private javax.swing.JPanel patientPanel;
    private javax.swing.JLabel staff;
    private javax.swing.JPanel staffPanel;
    private javax.swing.JPanel userPanel;
    private javax.swing.JLabel users1;
    // End of variables declaration//GEN-END:variables
}
