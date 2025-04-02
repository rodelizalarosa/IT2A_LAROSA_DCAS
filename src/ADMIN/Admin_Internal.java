
package ADMIN;

import AUTHENTICATION.LogIn;
import Config.ConnectDB;
import Config.Session;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Internal extends javax.swing.JInternalFrame {

    public Admin_Internal() {
        initComponents();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        dashboard_header = new javax.swing.JPanel();

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dashboard;
    private javax.swing.JPanel dashboard_header;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
