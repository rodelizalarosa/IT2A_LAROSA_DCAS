
package PRINT;
import Config.ConnectDB;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.sql.PreparedStatement;

public class GenerateSlip extends javax.swing.JFrame {

 
    public GenerateSlip() {
        initComponents();
    }
    
    private JTextArea slipTextArea;  // Text area to display the appointment slip

    public GenerateSlip(int appointmentId) {
        setTitle("Appointment Slip");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        slipTextArea = new JTextArea();
        slipTextArea.setEditable(false);  // We don't want to edit the slip
        slipTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        slipTextArea.setLineWrap(true);
        slipTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(slipTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Populate the slip with the appointment information
        populateAppointmentSlip(appointmentId);
    }

    private void populateAppointmentSlip(int appointmentId) {
        // Connect to the database
        try (Connection conn = new ConnectDB().getConnection()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "❌ Database connection failed.");
                return;
            }

            // SQL query to fetch appointment and treatment services details
            String query =
                "SELECT " +
                "  a.appointment_id, a.pref_time, a.pref_date, " +
                "  p.patient_id, CONCAT(p.p_fname, ' ', p.p_lname) AS patient_name, p.p_contactNumber, " +
                "  CONCAT(d.d_fname, ' ', d.d_lname) AS dentist_name, d.specialization, " +
                "  s.service_name, s.service_cost, ts.quantity " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN dentist d ON a.dentist_id = d.user_id " +
                "JOIN treatment_services ts ON a.appointment_id = ts.appointment_id " +
                "JOIN services s ON ts.service_id = s.service_id " +
                "WHERE a.appointment_id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, appointmentId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    StringBuilder slipText = new StringBuilder();
                    double totalCost = 0.0;

                    // Loop through the result set to build the slip
                    while (rs.next()) {
                        // Appointment details
                        slipText.append("Appointment ID: ").append(rs.getInt("appointment_id")).append("\n");
                        slipText.append("Patient: ").append(rs.getString("patient_name")).append("\n");
                        slipText.append("Contact: ").append(rs.getString("p_contactNumber")).append("\n");
                        slipText.append("Dentist: ").append(rs.getString("dentist_name")).append("\n");
                        slipText.append("Specialization: ").append(rs.getString("specialization")).append("\n");
                        slipText.append("Date: ").append(rs.getString("pref_date")).append("\n");
                        slipText.append("Time: ").append(rs.getString("pref_time")).append("\n\n");

                        // Service details
                        String serviceName = rs.getString("service_name");
                        double serviceCost = rs.getDouble("service_cost");
                        int quantity = rs.getInt("quantity");
                        double subtotal = serviceCost * quantity;
                        totalCost += subtotal;

                        slipText.append("Service: ").append(serviceName).append("\n");
                        slipText.append("Cost: ").append(String.format("%.2f", serviceCost)).append("\n");
                        slipText.append("Quantity: ").append(quantity).append("\n");
                        slipText.append("Subtotal: ").append(String.format("%.2f", subtotal)).append("\n\n");
                    }

                    // Append total cost at the bottom
                    slipText.append("Total Cost: ").append(String.format("%.2f", totalCost)).append("\n");
                    slipText.append("====================================\n");
                    slipText.append("Thank you for your visit!");

                    // Set the slip text to the text area
                    slipTextArea.setText(slipText.toString());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Failed to fetch appointment details:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(GenerateSlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateSlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateSlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateSlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateSlip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
