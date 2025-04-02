
package ADMIN;

import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Admin_Staff_Internal extends javax.swing.JInternalFrame {

    public Admin_Staff_Internal() {
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
        staff_header = new javax.swing.JPanel();
        staff = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        staff_header.setBackground(new java.awt.Color(55, 162, 153));
        staff_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        staff.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        staff.setForeground(new java.awt.Color(255, 255, 255));
        staff.setText("Staff Information");
        staff_header.add(staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 180, 50));

        jPanel1.add(staff_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel staff;
    private javax.swing.JPanel staff_header;
    // End of variables declaration//GEN-END:variables
}
