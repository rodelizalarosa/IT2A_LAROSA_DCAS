
package ADMIN;

import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Doctor_Internal extends javax.swing.JInternalFrame {

    public Admin_Doctor_Internal() {
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
        doctor_header = new javax.swing.JPanel();
        doctor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doctor_header.setBackground(new java.awt.Color(55, 162, 153));
        doctor_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        doctor_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doctor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        doctor.setForeground(new java.awt.Color(255, 255, 255));
        doctor.setText("Doctor Information");
        doctor_header.add(doctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 180, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Manage doctor accounts");
        doctor_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 190, 50));

        jPanel1.add(doctor_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel doctor;
    private javax.swing.JPanel doctor_header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
