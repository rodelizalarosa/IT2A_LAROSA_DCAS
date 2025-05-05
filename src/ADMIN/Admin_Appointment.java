
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;


public class Admin_Appointment extends javax.swing.JInternalFrame {

    public Admin_Appointment() {
        initComponents();
        loadAppointments();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        Appointment.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        Appointment.getTableHeader().setOpaque(false);
        Appointment.getTableHeader().setBorder(null);
        Appointment.getTableHeader().setBackground(new Color(51, 51, 255));
        Appointment.getTableHeader().setForeground(new Color(0, 0, 0));
        Appointment.setRowHeight(25);
        
        //table colum size
        Appointment.getColumnModel().getColumn(0).setPreferredWidth(30);
        Appointment.getColumnModel().getColumn(1).setPreferredWidth(25);
        Appointment.getColumnModel().getColumn(2).setPreferredWidth(25);
        Appointment.getColumnModel().getColumn(3).setPreferredWidth(55);
        Appointment.getColumnModel().getColumn(4).setPreferredWidth(55);
        Appointment.getColumnModel().getColumn(5).setPreferredWidth(150);
        Appointment.getColumnModel().getColumn(6).setPreferredWidth(55);
       
         //FOR LIVE SEARCH LEZGOOO
        searchAppointment.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchAppointment();
            }
        });  
        
        Appointment.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && Appointment.getSelectedRow() != -1) {
                    int row = Appointment.getSelectedRow();
                    int appointmentID = (int) Appointment.getValueAt(row, 0);
                    openViewAppointmentFrame(appointmentID);
                }
            }
        });

    }
    
    private void openViewAppointmentFrame(int appointmentID) {
        try {
            Connection conn = new ConnectDB().getConnection();

            String query = "SELECT a.appointment_id, a.patient_id, a.pref_time, a.pref_date, a.notes, " +
                "a.dentist_id, p.p_fname, p.p_lname, p.p_gender, p.p_contactNumber, " +
                "s.service_name, d.d_fname, d.d_lname, a.a_status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN treatment_services ts ON ts.appointment_id = a.appointment_id " +
                "JOIN services s ON ts.service_id = s.service_id " +
                "JOIN dentist d ON a.dentist_id = d.dentist_id " +
                "WHERE a.appointment_id = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, appointmentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Session session = Session.getInstance();

                // Store PATIENT info
                session.setPatient(
                    rs.getInt("patient_id"),
                    rs.getString("p_fname"),
                    rs.getString("p_lname"),
                    rs.getString("p_gender"),
                    "", // DOB not retrieved
                    rs.getString("p_contactNumber")
                );

                // Store APPOINTMENT info
                session.setAppointmentDetails(
                    rs.getInt("appointment_id"),
                    rs.getString("pref_date"),
                    rs.getString("pref_time"),
                    rs.getString("notes"),
                    rs.getString("a_status")
                );

                // Store Dentist name
                session.setDentistFullName(rs.getString("d_fname") + " " + rs.getString("d_lname"));

                StringBuilder serviceNames = new StringBuilder();
                serviceNames.append("• ").append(rs.getString("service_name")).append("\n");
                while (rs.next()) {
                    serviceNames.append("• ").append(rs.getString("service_name")).append("\n");
                }
                session.setServiceNameList(serviceNames.toString());

                // Store the HTML formatted services list
                session.setServiceNameList(serviceNames.toString());

                // Open the view
                Admin_View_Appointment view = new Admin_View_Appointment();
                view.setVisible(true);
            } else {
                System.out.println("No appointment found with ID: " + appointmentID);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
}

  

    private void loadAppointments() {
        ConnectDB connect = new ConnectDB();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };

        model.addColumn("Appointment ID");
        model.addColumn("Patient ID");
        model.addColumn("Dentist ID");
        model.addColumn("Date");
        model.addColumn("Time");
        model.addColumn("Notes");
        model.addColumn("Status");

        try {
            Connection conn = connect.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

            // Use PreparedStatement even if no parameters for consistency
            String query = "SELECT appointment_id, patient_id, dentist_id, pref_date, pref_time, notes, a_status FROM appointments";
            PreparedStatement stmt = conn.prepareStatement(query); // Changed from Statement to PreparedStatement
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getInt("dentist_id"),
                    rs.getString("pref_date"),
                    rs.getString("pref_time"),
                    rs.getString("notes"),
                    rs.getString("a_status")
                });
            }

            Appointment.setModel(model);
            model.fireTableDataChanged();

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error loading appointments: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    private void searchAppointment() {
        String keyword = searchAppointment.getText().trim(); // JTextField for live search

        try {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            // Base query
            StringBuilder sql = new StringBuilder("SELECT appointment_id, patient_id, dentist_id, pref_date, pref_time, notes, a_status FROM appointments");

            // Append WHERE clause if keyword exists
            if (!keyword.isEmpty()) {
                sql.append(" WHERE pref_date LIKE ? OR pref_time LIKE ? OR notes LIKE ?");
            }

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            if (!keyword.isEmpty()) {
                for (int i = 1; i <= 3; i++) {
                    pstmt.setString(i, "%" + keyword + "%");
                }
            }

            ResultSet rs = pstmt.executeQuery();

            // Clear old data in table
            DefaultTableModel model = (DefaultTableModel) Appointment.getModel();
            model.setRowCount(0);

            // Populate table with result set
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getString("dentist_id"),
                    rs.getString("pref_date"),
                    rs.getString("pref_time"),
                    rs.getString("notes"),
                    rs.getString("a_status")
                });
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Failed to search appointments.</b><br>" + e.getMessage() + "</html>",
                "❌ Search Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }



    
    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        appointment_header = new javax.swing.JPanel();
        appointment = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        archivePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        updatePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        viewPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Appointment = new javax.swing.JTable();
        refreshPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        searchAppointment = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Appointments");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 210, 50));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Manage appointment details.");
        appointment_header.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 5, 190, 40));

        jPanel1.add(appointment_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        archivePanel.setBackground(new java.awt.Color(0, 51, 51));
        archivePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                archivePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                archivePanelMouseExited(evt);
            }
        });
        archivePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ARCHIVE");
        archivePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel3.add(archivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 90, -1));

        updatePanel.setBackground(new java.awt.Color(0, 51, 51));
        updatePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePanelMouseExited(evt);
            }
        });
        updatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("UPDATE");
        updatePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel3.add(updatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 90, -1));

        viewPanel.setBackground(new java.awt.Color(0, 51, 51));
        viewPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewPanelMouseExited(evt);
            }
        });
        viewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("VIEW");
        viewPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel3.add(viewPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 50));

        Appointment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Appointment.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Appointment);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 840, 360));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 860, 430));

        refreshPanel.setBackground(new java.awt.Color(0, 51, 51));
        refreshPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshPanelMouseExited(evt);
            }
        });
        refreshPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh (2).png"))); // NOI18N
        refreshPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 30));

        jPanel1.add(refreshPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 60, 30));

        searchAppointment.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        searchAppointment.setForeground(new java.awt.Color(153, 153, 153));
        searchAppointment.setText("Search");
        searchAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAppointmentActionPerformed(evt);
            }
        });
        jPanel1.add(searchAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 230, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updatePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseEntered
        updatePanel.setBackground(hoverColor);
    }//GEN-LAST:event_updatePanelMouseEntered

    private void updatePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseExited
        updatePanel.setBackground(navColor);
    }//GEN-LAST:event_updatePanelMouseExited

    private void archivePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivePanelMouseEntered
        archivePanel.setBackground(hoverColor);
    }//GEN-LAST:event_archivePanelMouseEntered

    private void refreshPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseEntered
        refreshPanel.setBackground(hoverColor);
    }//GEN-LAST:event_refreshPanelMouseEntered

    private void refreshPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseExited
        refreshPanel.setBackground(navColor);
    }//GEN-LAST:event_refreshPanelMouseExited

    private void archivePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivePanelMouseExited
        archivePanel.setBackground(navColor);
    }//GEN-LAST:event_archivePanelMouseExited

    private void searchAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAppointmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchAppointmentActionPerformed

    private void refreshPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseClicked
        loadAppointments();
    }//GEN-LAST:event_refreshPanelMouseClicked

    private void viewPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewPanelMouseEntered
        viewPanel.setBackground(hoverColor);
    }//GEN-LAST:event_viewPanelMouseEntered

    private void viewPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewPanelMouseExited
        viewPanel.setBackground(navColor);
    }//GEN-LAST:event_viewPanelMouseExited

    private void viewPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewPanelMouseClicked
        Admin_View_Appointment view = new Admin_View_Appointment();
        view.setVisible(true);
    }//GEN-LAST:event_viewPanelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Appointment;
    private javax.swing.JLabel appointment;
    private javax.swing.JPanel appointment_header;
    private javax.swing.JPanel archivePanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel refreshPanel;
    private javax.swing.JTextField searchAppointment;
    private javax.swing.JPanel updatePanel;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
}
