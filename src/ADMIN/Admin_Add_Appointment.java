
package ADMIN;

import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Add_Appointment extends javax.swing.JInternalFrame {


    public Admin_Add_Appointment() {
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
        appointment_header = new javax.swing.JPanel();
        appointment = new javax.swing.JLabel();
        back = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Add Appointment");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 50));

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
        });
        appointment_header.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        jPanel1.add(appointment_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Admin_Appointment app = new Admin_Appointment();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
     
    }//GEN-LAST:event_backMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appointment;
    private javax.swing.JPanel appointment_header;
    private javax.swing.JLabel back;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
