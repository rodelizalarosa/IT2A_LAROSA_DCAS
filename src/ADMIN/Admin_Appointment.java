
package ADMIN;

import Config.ConnectDB;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Appointment.getColumnModel().getColumn(0).setPreferredWidth(25);
        Appointment.getColumnModel().getColumn(1).setPreferredWidth(25);
        Appointment.getColumnModel().getColumn(2).setPreferredWidth(25);
        Appointment.getColumnModel().getColumn(3).setPreferredWidth(55);
        Appointment.getColumnModel().getColumn(4).setPreferredWidth(55);
        Appointment.getColumnModel().getColumn(5).setPreferredWidth(150);
        Appointment.getColumnModel().getColumn(6).setPreferredWidth(55);
       
    }
    
    private void loadAppointments(){ 
        ConnectDB connect = new ConnectDB();

        DefaultTableModel model = new DefaultTableModel();
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

            String query = "SELECT appointment_id, patient_id, dentist_id, date, time, notes, a_status FROM appointments"; // Corrected query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getString("dentist_id"),
                    rs.getString("date"),
                    rs.getString("time"),
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
        jScrollPane1 = new javax.swing.JScrollPane();
        Appointment = new javax.swing.JTable();
        bookPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        archivePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        refreshPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        updatePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

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
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 40));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 840, 360));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 860, 420));

        bookPanel.setBackground(new java.awt.Color(0, 51, 51));
        bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookPanelMouseExited(evt);
            }
        });
        bookPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N
        jLabel1.setText("  BOOK AN APPOINTMENT");
        bookPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 230, 40));

        jPanel1.add(bookPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 250, 40));

        archivePanel.setBackground(new java.awt.Color(0, 51, 51));
        archivePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                archivePanelMouseEntered(evt);
            }
        });
        archivePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ARCHIVE");
        archivePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel1.add(archivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 90, 30));

        refreshPanel.setBackground(new java.awt.Color(0, 51, 51));
        refreshPanel.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jPanel1.add(refreshPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 60, 30));

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

        jPanel1.add(updatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bookPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bookPanelMouseClicked

    private void bookPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseEntered
        bookPanel.setBackground(hoverColor);
    }//GEN-LAST:event_bookPanelMouseEntered

    private void bookPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseExited
        bookPanel.setBackground(navColor);
    }//GEN-LAST:event_bookPanelMouseExited

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Appointment;
    private javax.swing.JLabel appointment;
    private javax.swing.JPanel appointment_header;
    private javax.swing.JPanel archivePanel;
    private javax.swing.JPanel bookPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel refreshPanel;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables
}
