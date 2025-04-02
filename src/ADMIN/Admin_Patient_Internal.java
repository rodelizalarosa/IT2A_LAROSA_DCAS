
package ADMIN;

import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Patient_Internal extends javax.swing.JInternalFrame {

 
    public Admin_Patient_Internal() {
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
        patient_header = new javax.swing.JPanel();
        patient = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        patient_header.setBackground(new java.awt.Color(55, 162, 153));
        patient_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        patient.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        patient.setForeground(new java.awt.Color(255, 255, 255));
        patient.setText("Patient Information");
        patient_header.add(patient, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 180, 50));

        jPanel1.add(patient_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel patient;
    private javax.swing.JPanel patient_header;
    // End of variables declaration//GEN-END:variables
}
