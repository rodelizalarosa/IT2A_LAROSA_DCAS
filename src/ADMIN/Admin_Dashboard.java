
package ADMIN;

import AUTHENTICATION.LogIn;
import Config.Session;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Admin_Dashboard extends javax.swing.JFrame {

  
    public Admin_Dashboard() {
        initComponents();
        showDashboard();
        
        Session.getInstance().setDesktopPane(mainDesktop);
    }

    private void showDashboard(){
        Admin_Internal adm = new Admin_Internal();
        mainDesktop.add(adm);
        adm.setVisible(true);

    }
    
    private void showSettings() {
        Admin_Settings set = new Admin_Settings();
        set.setVisible(true);
       
    }
    
    private void showLogs(){
        Admin_Logs logs = new Admin_Logs();
        mainDesktop.add(logs);
        logs.setVisible(true);
   
    }
    
    private void showManage(){
        Admin_Manage mng = new Admin_Manage();
        mng.setVisible(true);
    
    }
    
    private void showAppointment(){
        Admin_Appointment app = new Admin_Appointment();
        mainDesktop.add(app);
        app.setVisible(true);
      
    }
    
    
    Color hoverColor = new Color (0,153,153);
    Color navColor = new Color (55,162,153);
    
    Color logoutHover = new Color (55,162,153);
    Color logoutNav = new Color (0,51,51);
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        navigation = new javax.swing.JPanel();
        dashPanel = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        managePanel = new javax.swing.JPanel();
        manage = new javax.swing.JLabel();
        logsPanel = new javax.swing.JPanel();
        logs = new javax.swing.JLabel();
        logoutPanel = new javax.swing.JPanel();
        logout = new javax.swing.JLabel();
        logoutLogo = new javax.swing.JLabel();
        appointPanel = new javax.swing.JPanel();
        appointment = new javax.swing.JLabel();
        mainDesktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navigation.setBackground(new java.awt.Color(55, 162, 153));
        navigation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        navigation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashPanel.setBackground(new java.awt.Color(55, 162, 153));
        dashPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashPanelMouseExited(evt);
            }
        });
        dashPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        dashboard.setForeground(new java.awt.Color(255, 255, 255));
        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard.png"))); // NOI18N
        dashboard.setText(" Dashboard");
        dashPanel.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));

        navigation.add(dashPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 160, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 162, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jLabel1.setText("Dental Flow");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 240, 50));

        navigation.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 50));

        settingsPanel.setBackground(new java.awt.Color(55, 162, 153));
        settingsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsPanelMouseExited(evt);
            }
        });
        settingsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profile.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        profile.setText("  Settings");
        settingsPanel.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 40));

        navigation.add(settingsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 160, -1));

        managePanel.setBackground(new java.awt.Color(55, 162, 153));
        managePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                managePanelMouseExited(evt);
            }
        });
        managePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        manage.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        manage.setForeground(new java.awt.Color(255, 255, 255));
        manage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manage.png"))); // NOI18N
        manage.setText("  Manage");
        managePanel.add(manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 40));

        navigation.add(managePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 160, -1));

        logsPanel.setBackground(new java.awt.Color(55, 162, 153));
        logsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logsPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logsPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logsPanelMouseExited(evt);
            }
        });
        logsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logs.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        logs.setForeground(new java.awt.Color(255, 255, 255));
        logs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logs.png"))); // NOI18N
        logs.setText("  Logs");
        logsPanel.add(logs, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 40));

        navigation.add(logsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 160, -1));

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

        appointPanel.setBackground(new java.awt.Color(55, 162, 153));
        appointPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appointPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                appointPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                appointPanelMouseExited(evt);
            }
        });
        appointPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/appointments.png"))); // NOI18N
        appointment.setText("  Appointment");
        appointPanel.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 40));

        navigation.add(appointPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 160, -1));

        jPanel1.add(navigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 560));

        mainDesktop.setBackground(new java.awt.Color(204, 204, 204));
        mainDesktop.setLayout(new java.awt.BorderLayout());
        jPanel1.add(mainDesktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 880, 560));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashPanelMouseClicked
        showDashboard();
    }//GEN-LAST:event_dashPanelMouseClicked

    private void dashPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashPanelMouseEntered
        dashPanel.setBackground(hoverColor);
    }//GEN-LAST:event_dashPanelMouseEntered

    private void dashPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashPanelMouseExited
        dashPanel.setBackground(navColor);
    }//GEN-LAST:event_dashPanelMouseExited

    private void settingsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseClicked
        showSettings();
    }//GEN-LAST:event_settingsPanelMouseClicked

    private void settingsPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseEntered
        settingsPanel.setBackground(hoverColor);
    }//GEN-LAST:event_settingsPanelMouseEntered

    private void settingsPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelMouseExited
        settingsPanel.setBackground(navColor);
    }//GEN-LAST:event_settingsPanelMouseExited

    private void managePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managePanelMouseClicked
        showManage();
    }//GEN-LAST:event_managePanelMouseClicked

    private void managePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managePanelMouseEntered
        managePanel.setBackground(hoverColor);
    }//GEN-LAST:event_managePanelMouseEntered

    private void managePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managePanelMouseExited
        managePanel.setBackground(navColor);
    }//GEN-LAST:event_managePanelMouseExited

    private void logsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseClicked
        showLogs();
    }//GEN-LAST:event_logsPanelMouseClicked

    private void logsPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseEntered
        logsPanel.setBackground(hoverColor);
    }//GEN-LAST:event_logsPanelMouseEntered

    private void logsPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseExited
       logsPanel.setBackground(navColor);
    }//GEN-LAST:event_logsPanelMouseExited

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

    private void appointPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointPanelMouseClicked
        showAppointment();
    }//GEN-LAST:event_appointPanelMouseClicked

    private void appointPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointPanelMouseEntered
         appointPanel.setBackground(hoverColor);
    }//GEN-LAST:event_appointPanelMouseEntered

    private void appointPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointPanelMouseExited
         appointPanel.setBackground(navColor);
    }//GEN-LAST:event_appointPanelMouseExited

 
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
            java.util.logging.Logger.getLogger(Admin_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel appointPanel;
    private javax.swing.JLabel appointment;
    private javax.swing.JPanel dashPanel;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel logoutLogo;
    private javax.swing.JPanel logoutPanel;
    private javax.swing.JLabel logs;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JDesktopPane mainDesktop;
    private javax.swing.JLabel manage;
    private javax.swing.JPanel managePanel;
    private javax.swing.JPanel navigation;
    private javax.swing.JLabel profile;
    private javax.swing.JPanel settingsPanel;
    // End of variables declaration//GEN-END:variables
}
