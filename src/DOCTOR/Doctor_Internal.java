
package DOCTOR;

import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Doctor_Internal extends javax.swing.JInternalFrame {

    public Doctor_Internal() {
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

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dashboard.setForeground(new java.awt.Color(255, 255, 255));
        dashboard.setText("Doctor Dashboard");
        jPanel1.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 190, 50));

        dashboard_header.setBackground(new java.awt.Color(55, 162, 153));
        dashboard_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.add(dashboard_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dashboard;
    private javax.swing.JPanel dashboard_header;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
