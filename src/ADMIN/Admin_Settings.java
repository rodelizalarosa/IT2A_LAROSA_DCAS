
package ADMIN;

import AUTHENTICATION.LogIn;
import Config.Session;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Admin_Settings extends javax.swing.JFrame {

  
    public Admin_Settings() {
        initComponents();
        showProfile();
        
        Session.getInstance().setDesktopSettings(desktopSettings);

    }
    
     private void showProfile() {
        Admin_Profile prof = new Admin_Profile();
        desktopSettings.add(prof);
        prof.setVisible(true);
    }
     
    private void showSecurity() {
        Admin_Security sec = new Admin_Security();
        desktopSettings.add(sec);
        sec.setVisible(true);
        this.dispose();
    }
     

    Color hoverColor = new Color (0,153,153);
    Color navColor = new Color (55,162,153);
    
    Color logoutHover = new Color (55,162,153);
    Color logoutNav = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navigation = new javax.swing.JPanel();
        manage = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        logoutPanel = new javax.swing.JPanel();
        logout = new javax.swing.JLabel();
        logoutLogo = new javax.swing.JLabel();
        securityPanel = new javax.swing.JPanel();
        security = new javax.swing.JLabel();
        profilePanel = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        desktopSettings = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navigation.setBackground(new java.awt.Color(55, 162, 153));
        navigation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        navigation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        manage.setForeground(new java.awt.Color(255, 255, 255));
        manage.setText("Settings");
        navigation.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 140, 80));

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        navigation.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));

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

        securityPanel.setBackground(new java.awt.Color(55, 162, 153));
        securityPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                securityPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                securityPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                securityPanelMouseExited(evt);
            }
        });
        securityPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        security.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        security.setForeground(new java.awt.Color(255, 255, 255));
        security.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/security.png"))); // NOI18N
        security.setText("  Security");
        securityPanel.add(security, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 40));

        navigation.add(securityPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 160, -1));

        profilePanel.setBackground(new java.awt.Color(55, 162, 153));
        profilePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profilePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profilePanelMouseExited(evt);
            }
        });
        profilePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile.png"))); // NOI18N
        profile.setText("  Profile");
        profilePanel.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 40));

        navigation.add(profilePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 160, -1));

        getContentPane().add(navigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 560));

        desktopSettings.setBackground(new java.awt.Color(255, 255, 255));
        desktopSettings.setLayout(new java.awt.BorderLayout());
        getContentPane().add(desktopSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 880, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Admin_Dashboard dash = new Admin_Dashboard();
        dash.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

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
        logoutPanel.setBackground(logoutHover);
    }//GEN-LAST:event_logoutPanelMouseEntered

    private void logoutPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseExited
        logoutPanel.setBackground(logoutNav);
    }//GEN-LAST:event_logoutPanelMouseExited

    private void securityPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_securityPanelMouseClicked
        showSecurity();
    }//GEN-LAST:event_securityPanelMouseClicked

    private void securityPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_securityPanelMouseEntered
        securityPanel.setBackground(hoverColor);
    }//GEN-LAST:event_securityPanelMouseEntered

    private void securityPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_securityPanelMouseExited
        securityPanel.setBackground(navColor);
    }//GEN-LAST:event_securityPanelMouseExited

    private void profilePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseClicked
        showProfile();
    }//GEN-LAST:event_profilePanelMouseClicked

    private void profilePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseEntered
        profilePanel.setBackground(hoverColor);
    }//GEN-LAST:event_profilePanelMouseEntered

    private void profilePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseExited
        profilePanel.setBackground(navColor);
    }//GEN-LAST:event_profilePanelMouseExited


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
            java.util.logging.Logger.getLogger(Admin_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JDesktopPane desktopSettings;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel logoutLogo;
    private javax.swing.JPanel logoutPanel;
    private javax.swing.JLabel manage;
    private javax.swing.JPanel navigation;
    private javax.swing.JLabel profile;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel security;
    private javax.swing.JPanel securityPanel;
    // End of variables declaration//GEN-END:variables
}
