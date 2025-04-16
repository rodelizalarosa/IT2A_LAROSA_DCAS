
package ADMIN;

import AUTHENTICATION.LogIn;
import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Internal extends javax.swing.JInternalFrame {

    public Admin_Internal() {
        initComponents();
        
        Session sess = Session.getInstance();
        String username = Session.getInstance().getUsername();
        username = sess.getUsername();
        dashboard.setText(username + "'s Dashboard");
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        dashboard_header = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        admin = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        logsPanel = new javax.swing.JPanel();
        refresh = new javax.swing.JLabel();
        picture = new javax.swing.JLabel();
        admin1 = new javax.swing.JLabel();
        appointmentPanel = new javax.swing.JPanel();
        refresh1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        account = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
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

        dashboard.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dashboard.setForeground(new java.awt.Color(255, 255, 255));
        dashboard.setText("Admin Dashboard");
        jPanel1.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 730, 50));

        dashboard_header.setBackground(new java.awt.Color(55, 162, 153));
        jPanel1.add(dashboard_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        admin.setText("Full Name");
        jPanel2.add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 310, 40));

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
        jPanel2.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 250, 40));

        logsPanel.setBackground(new java.awt.Color(0, 51, 51));
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

        refresh.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh.setText("System Summary");
        logsPanel.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 30));

        jPanel2.add(logsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 160, 30));

        picture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mother.png"))); // NOI18N
        jPanel2.add(picture, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 380, 190));

        admin1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        admin1.setText("Admin");
        jPanel2.add(admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 30));

        appointmentPanel.setBackground(new java.awt.Color(0, 51, 51));
        appointmentPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appointmentPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                appointmentPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                appointmentPanelMouseExited(evt);
            }
        });
        appointmentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        refresh1.setForeground(new java.awt.Color(255, 255, 255));
        refresh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh1.setText("View Appointments");
        appointmentPanel.add(refresh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 30));

        jPanel2.add(appointmentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 160, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 840, 190));

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 840, 220));

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account.setForeground(new java.awt.Color(255, 255, 255));
        account.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account.setText("Recent Patient Appointment");
        jPanel3.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 560, 40));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Keep track of patient data and other information.");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 420, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 840, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        Session sess = Session.getInstance();
        System.out.println("🟢 Debug: Session User ID = " + sess.getUserId()); // Debugging

        if (sess.getUserId() == 0) {  // No user is logged in
            JOptionPane.showMessageDialog(null, "No Account, Log in First!", "Notice", JOptionPane.ERROR_MESSAGE);

            SwingUtilities.invokeLater(() -> {
                new LogIn().setVisible(true);
            });

            this.dispose(); 

        } else {
            try {
                ConnectDB dbc = new ConnectDB();
                ResultSet rs = dbc.getData("SELECT u_username FROM users WHERE user_id = " + sess.getUserId());

                if (rs.next()) {
                    String username = rs.getString("u_username");

                    // Update the dashboard text in the Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> {
                        dashboard.setText(username + "'s Dashboard");

                    });

                } else {
                    System.out.println("⚠ No user found for ID: " + sess.getUserId());
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception: " + ex.getMessage());
            }
        }

    }//GEN-LAST:event_formInternalFrameActivated

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost

    }//GEN-LAST:event_usernameFocusLost

    private void usernameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseReleased
        //        username.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12), new java.awt.Color(0, 0, 0)));
        //        errorUsername.setText("");
    }//GEN-LAST:event_usernameMouseReleased

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void logsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseClicked

    }//GEN-LAST:event_logsPanelMouseClicked

    private void logsPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseEntered
        logsPanel.setBackground(hoverColor);
    }//GEN-LAST:event_logsPanelMouseEntered

    private void logsPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseExited
        logsPanel.setBackground(navColor);
    }//GEN-LAST:event_logsPanelMouseExited

    private void appointmentPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentPanelMouseClicked

    private void appointmentPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentPanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentPanelMouseEntered

    private void appointmentPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentPanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentPanelMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account;
    private javax.swing.JLabel admin;
    private javax.swing.JLabel admin1;
    private javax.swing.JPanel appointmentPanel;
    private javax.swing.JLabel dashboard;
    private javax.swing.JPanel dashboard_header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JLabel picture;
    private javax.swing.JLabel refresh;
    private javax.swing.JLabel refresh1;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
