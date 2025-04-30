
package Validations;

import ADMIN.Admin_Add_Patient;
import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Bind_Patient extends javax.swing.JFrame {

      private JDesktopPane parentDesktop;  // Assuming this is already initialized
 
    
    public Bind_Patient() {
        initComponents();
        loadActive();
        
        activePatients.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        activePatients.getTableHeader().setOpaque(false);
        activePatients.getTableHeader().setBorder(null);
        activePatients.getTableHeader().setBackground(new Color(51, 51, 255));
        activePatients.getTableHeader().setForeground(new Color(0, 0, 0));
        activePatients.setRowHeight(25);
        
        //table colum size
        activePatients.getColumnModel().getColumn(0).setPreferredWidth(20);
        activePatients.getColumnModel().getColumn(1).setPreferredWidth(50);
        activePatients.getColumnModel().getColumn(2).setPreferredWidth(60);
        
    }
    
    private void loadActive() {
        ConnectDB connect = new ConnectDB();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("User ID");
        model.addColumn("Username");
        model.addColumn("Email");

        try {
            Connection conn = connect.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

            String query = "SELECT user_id, u_username, u_email FROM users WHERE u_role = 'Patient' AND u_status = 'Active'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("user_id"),
                    rs.getString("u_username"),
                    rs.getString("u_email")
                });
            }

            activePatients.setModel(model);
            model.fireTableDataChanged();

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error loading active patients: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        account2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        activePatients = new javax.swing.JTable();
        select = new javax.swing.JPanel();
        delete = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(55, 162, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        account2.setForeground(new java.awt.Color(255, 255, 255));
        account2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account2.setText("BIND PATIENT TO AN ACCOUNT");
        jPanel9.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Select a User Account to bind.");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 620, 30));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        activePatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        activePatients.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(activePatients);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 590, 250));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 610, 270));

        select.setBackground(new java.awt.Color(0, 51, 51));
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
        });
        select.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete.setText("SELECT");
        select.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 120, 30));

        jPanel1.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 140, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 380));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
        int selectedRow = activePatients.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a patient from the table.",
                    "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get user info from the selected row
        int userId = (int) activePatients.getValueAt(selectedRow, 0);
        String username = activePatients.getValueAt(selectedRow, 1).toString();
        String email = activePatients.getValueAt(selectedRow, 2).toString();

         // Store user info in session (pass null for role and imagePath)
        String role = null;  // If you don't have a role at this point
        String imagePath = null;  // If you don't have an image path at this point
        Session.getInstance().setUser(userId, username, email, role, imagePath);
        
        // Show confirmation
        JOptionPane.showMessageDialog(this, String.format(
                "Selected User:\n\nUser ID: %d\nUsername: %s\nEmail: %s", userId, username, email),
                "Selected User", JOptionPane.INFORMATION_MESSAGE);

        // Open the form
        Admin_Add_Patient addPatientForm = new Admin_Add_Patient();
        addPatientForm.setVisible(true);

        if (parentDesktop != null) {
            parentDesktop.add(addPatientForm);
            addPatientForm.setLocationRelativeTo(null);
        }

        this.dispose();
    }//GEN-LAST:event_selectMouseClicked


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bind_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bind_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bind_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bind_Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bind_Patient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account2;
    private javax.swing.JTable activePatients;
    private javax.swing.JLabel delete;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel select;
    // End of variables declaration//GEN-END:variables
}
