
package ADMIN;

import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Security extends javax.swing.JInternalFrame {

    public Admin_Security() {
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
        security_header = new javax.swing.JPanel();
        account1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        account = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        account2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        account3 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        security_header.setBackground(new java.awt.Color(55, 162, 153));
        security_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        security_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account1.setForeground(new java.awt.Color(255, 255, 255));
        account1.setText("Security Settings");
        security_header.add(account1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 210, 50));

        jPanel1.add(security_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("to reset your password securely.");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 280, 40));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sms.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 60, 50));

        account.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        account.setForeground(new java.awt.Color(55, 162, 153));
        account.setText("SMS Code Protection");
        jPanel3.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 310, 50));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Verify your identity with a code sent to your phone");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 280, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 440, 110));

        account2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account2.setForeground(new java.awt.Color(55, 162, 153));
        account2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account2.setText("Account Security");
        jPanel2.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 460, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Manage your account's security.");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 250, 40));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 60, 50));

        account3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        account3.setForeground(new java.awt.Color(55, 162, 153));
        account3.setText("Secure Account");
        jPanel5.add(account3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 310, 50));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 440, 110));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 460, 340));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account;
    private javax.swing.JLabel account1;
    private javax.swing.JLabel account2;
    private javax.swing.JLabel account3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel security_header;
    // End of variables declaration//GEN-END:variables
}
