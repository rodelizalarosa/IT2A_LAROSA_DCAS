
package PATIENT;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Patient_Internal extends javax.swing.JInternalFrame {

    public Patient_Internal() {
        initComponents();
        
          //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
         loadPatientInfo();  // Method to load patient info after initialization

        // Update the patient label with the latest full name
        Session sess = Session.getInstance();
        String patientFullName = sess.getPatientFullName();

        // Set the label to the full name
        patient.setText(patientFullName);
    }
    
    private void loadPatientInfo() {
        Session sess = Session.getInstance();
        int userId = sess.getUserId();
        String fname = "", lname = "";

        // Fetch patient info from the database to ensure latest data
        try (Connection con = ConnectDB.getConnection()) {
            String sql = "SELECT p_fname, p_lname FROM patients WHERE user_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    fname = rs.getString("p_fname");
                    lname = rs.getString("p_lname");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fname == null || lname == null || fname.isEmpty() || lname.isEmpty()) {
            // If no data found, set default text
            patient.setText("Update your profile");
        } else {
            // Set the patient full name to session
            sess.setPatientName(fname, lname);

            // Set the label text with the updated full name
            patient.setText(fname + " " + lname);
        }
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
        patient = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        profPanel = new javax.swing.JPanel();
        refresh = new javax.swing.JLabel();
        picture = new javax.swing.JLabel();
        admin1 = new javax.swing.JLabel();
        bookPanel = new javax.swing.JPanel();
        refresh1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        account = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dashboard.setForeground(new java.awt.Color(255, 255, 255));
        dashboard.setText("Patient Dashboard");
        jPanel1.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 440, 50));

        dashboard_header.setBackground(new java.awt.Color(55, 162, 153));
        dashboard_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.add(dashboard_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        patient.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        patient.setText("Full Name");
        jPanel2.add(patient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 310, 40));

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

        profPanel.setBackground(new java.awt.Color(0, 51, 51));
        profPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profPanelMouseExited(evt);
            }
        });
        profPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh.setText("View Profile");
        profPanel.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 30));

        jPanel2.add(profPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 160, 30));

        picture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mother.png"))); // NOI18N
        jPanel2.add(picture, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 380, 190));

        admin1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        admin1.setText("Patient");
        jPanel2.add(admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 30));

        bookPanel.setBackground(new java.awt.Color(0, 51, 51));
        bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookPanelMouseExited(evt);
            }
        });
        bookPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        refresh1.setForeground(new java.awt.Color(255, 255, 255));
        refresh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh1.setText("Book an appointment");
        bookPanel.add(refresh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 30));

        jPanel2.add(bookPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, 30));

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
        account.setText("Appointment History");
        jPanel3.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 560, 40));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Keep track your appointment and treatment status");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 420, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 840, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost

    }//GEN-LAST:event_usernameFocusLost

    private void usernameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseReleased
        //        username.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12), new java.awt.Color(0, 0, 0)));
        //        errorUsername.setText("");
    }//GEN-LAST:event_usernameMouseReleased

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void profPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profPanelMouseClicked

    }//GEN-LAST:event_profPanelMouseClicked

    private void profPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profPanelMouseEntered
        profPanel.setBackground(hoverColor);
    }//GEN-LAST:event_profPanelMouseEntered

    private void profPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profPanelMouseExited
        profPanel.setBackground(navColor);
    }//GEN-LAST:event_profPanelMouseExited

    private void bookPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bookPanelMouseClicked

    private void bookPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bookPanelMouseEntered

    private void bookPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bookPanelMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account;
    private javax.swing.JLabel admin1;
    private javax.swing.JPanel bookPanel;
    private javax.swing.JLabel dashboard;
    private javax.swing.JPanel dashboard_header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel patient;
    private javax.swing.JLabel picture;
    private javax.swing.JPanel profPanel;
    private javax.swing.JLabel refresh;
    private javax.swing.JLabel refresh1;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
