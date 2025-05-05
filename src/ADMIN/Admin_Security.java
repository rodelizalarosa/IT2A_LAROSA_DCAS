
package ADMIN;

import java.awt.Color;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Security extends javax.swing.JInternalFrame {

    public Admin_Security() {
        initComponents();
        
         //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    Color hoverColor = new Color (204,204,204);
    Color navColor = new Color (255, 255, 255);


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        security_header = new javax.swing.JPanel();
        account1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        secure = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        account = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        account2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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

        secure.setBackground(new java.awt.Color(255, 255, 255));
        secure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                secureMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                secureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                secureMouseExited(evt);
            }
        });
        secure.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("and enter your security code to reset your password.");
        secure.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 320, 30));

        account.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        account.setForeground(new java.awt.Color(55, 162, 153));
        account.setText("Change Password");
        secure.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 310, 50));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("For added protection, you'll need to confirm your email address");
        secure.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 350, 40));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock.png"))); // NOI18N
        secure.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 60));

        jPanel2.add(secure, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 460, 110));

        account2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        account2.setForeground(new java.awt.Color(55, 162, 153));
        account2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account2.setText("Account Security");
        jPanel2.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 480, 50));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/verify.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 250, 190));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 480, 400));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void secureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secureMouseClicked
        Admin_Security_Email em = new Admin_Security_Email();
        em.setVisible(true);
    }//GEN-LAST:event_secureMouseClicked

    private void secureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secureMouseEntered
        secure.setBackground(hoverColor);
    }//GEN-LAST:event_secureMouseEntered

    private void secureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secureMouseExited
        secure.setBackground(navColor);
    }//GEN-LAST:event_secureMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account;
    private javax.swing.JLabel account1;
    private javax.swing.JLabel account2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel secure;
    private javax.swing.JPanel security_header;
    // End of variables declaration//GEN-END:variables
}
