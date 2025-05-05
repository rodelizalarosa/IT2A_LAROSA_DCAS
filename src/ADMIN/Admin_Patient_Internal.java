
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import Validations.Bind_Patient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;


public class Admin_Patient_Internal extends javax.swing.JInternalFrame {

    
    private JDesktopPane parentDesktop;  // Assuming this is already initialized
 
    public Admin_Patient_Internal() {
        initComponents();
        loadPatients();

        // Remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

        // Table header customization
        patients.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        patients.getTableHeader().setOpaque(false);
        patients.getTableHeader().setBorder(null);
        patients.getTableHeader().setBackground(new Color(51, 51, 255));
        patients.getTableHeader().setForeground(new Color(0, 0, 0));
        patients.setRowHeight(25);

        // Table column sizes
        patients.getColumnModel().getColumn(0).setPreferredWidth(25);  // Patient ID
        patients.getColumnModel().getColumn(1).setPreferredWidth(25);  // Has Account
        patients.getColumnModel().getColumn(2).setPreferredWidth(70); // First Name
        patients.getColumnModel().getColumn(3).setPreferredWidth(70); // Last Name
        patients.getColumnModel().getColumn(4).setPreferredWidth(30);  // Gender
        patients.getColumnModel().getColumn(5).setPreferredWidth(20);  // Age
        patients.getColumnModel().getColumn(6).setPreferredWidth(100); // Contact Number
        patients.getColumnModel().getColumn(7).setPreferredWidth(50); // Email

        // Live search
        searchPatient.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchPatient(); // You must implement this
            }
        });

        patients.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedRow = patients.getSelectedRow();
                    if (selectedRow != -1) {
                        int patientId = Integer.parseInt(patients.getValueAt(selectedRow, 0).toString());
                        new Admin_Update_Patient(patientId).setVisible(true);
                    } else {
                         // Modify the message dialog with HTML formatting
                        JOptionPane.showMessageDialog(
                            null,
                            "<html><b>No row selected.</b><br>Please select a patient before double-clicking.</html>",
                            "⚠ Selection Required",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
            }
        });

    }


    private void loadPatients() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Patient ID", "Has Account", "First Name", "Last Name", "Gender", "Age", "Contact Number", "Email"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };

        ConnectDB connect = new ConnectDB();

        try {
            Connection conn = connect.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

            // 🔥 Filter out archived patients
            String query = "SELECT patient_id, user_id, p_fname, p_lname, p_gender, p_dob, p_contactNumber, p_email " +
                           "FROM patients WHERE p_status IS NULL OR p_status != 'Archived'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int age = calculateAge(rs.getDate("p_dob").toLocalDate());

                model.addRow(new Object[]{
                    rs.getInt("patient_id"),
                    rs.getInt("user_id") != 0 ? "Yes" : "No",
                    rs.getString("p_fname"),
                    rs.getString("p_lname"),
                    rs.getString("p_gender"),
                    age,
                    rs.getString("p_contactNumber"),
                    rs.getString("p_email")
                });
            }

            patients.setModel(model);
            model.fireTableDataChanged();

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error loading patients: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    
    private int calculateAge(LocalDate dob) {
        if (dob == null) return 0;
        return Period.between(dob, LocalDate.now()).getYears();
    }

    
    public int getSelectedPatientId() {
        int selectedRow = patients.getSelectedRow(); // Get the selected row index

            if (selectedRow != -1) { // Check if a row is selected
                return Integer.parseInt(patients.getValueAt(selectedRow, 0).toString()); // Get user_id from the first column
            }
        return -1; // Return -1 if no row is selected
    }
    
    private void searchPatient() {
        String keyword = searchPatient.getText().trim(); // JTextField for live search

        try {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            StringBuilder sql = new StringBuilder("SELECT patient_id, user_id, p_fname, p_lname, p_gender, p_dob, p_contactNumber, p_email FROM patients");

            if (!keyword.isEmpty()) {
                sql.append(" WHERE p_fname LIKE ? OR p_lname LIKE ?");
            }

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            if (!keyword.isEmpty()) {
                pstmt.setString(1, "%" + keyword + "%");
                pstmt.setString(2, "%" + keyword + "%");
            }

            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) patients.getModel(); // your JTable for patients
            model.setRowCount(0); // clear old data

            while (rs.next()) {
                int age = calculateAge(rs.getDate("p_dob").toLocalDate());

                model.addRow(new Object[]{
                    rs.getInt("patient_id"),
                    rs.getInt("user_id") != 0 ? "Yes" : "No", // show Yes/No for account
                    rs.getString("p_fname"),
                    rs.getString("p_lname"),
                    rs.getString("p_gender"),
                    age,
                    rs.getString("p_contactNumber"),
                    rs.getString("p_email")
                });
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Error searching patients:</b><br>" + e.getMessage() + "<br><br>Please try again later or contact support if the issue persists.</html>",
                "❌ Error",
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
        patient_header = new javax.swing.JPanel();
        patient = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        addPanel = new javax.swing.JPanel();
        add = new javax.swing.JLabel();
        updatePanel = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        archivePanel = new javax.swing.JPanel();
        delete = new javax.swing.JLabel();
        bookPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patients = new javax.swing.JTable();
        refreshPanel = new javax.swing.JPanel();
        refresh = new javax.swing.JLabel();
        searchPatient = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        patient_header.setBackground(new java.awt.Color(55, 162, 153));
        patient_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        patient_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        patient.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        patient.setForeground(new java.awt.Color(255, 255, 255));
        patient.setText("Patient Information");
        patient_header.add(patient, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 180, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Manage patient accounts.");
        patient_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 190, 50));

        jPanel1.add(patient_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addPanel.setBackground(new java.awt.Color(0, 51, 51));
        addPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPanelMouseExited(evt);
            }
        });
        addPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add.setText("ADD");
        addPanel.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel3.add(addPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, -1));

        updatePanel.setBackground(new java.awt.Color(0, 51, 51));
        updatePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePanelMouseExited(evt);
            }
        });
        updatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit.setText("UPDATE");
        updatePanel.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel3.add(updatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, -1));

        archivePanel.setBackground(new java.awt.Color(0, 51, 51));
        archivePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archivePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                archivePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                archivePanelMouseExited(evt);
            }
        });
        archivePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete.setText("ARCHIVE");
        archivePanel.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel3.add(archivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 110, -1));

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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book.png"))); // NOI18N
        jLabel2.setText("  BOOK AN APPOINTMENT");
        bookPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 230, 30));

        jPanel3.add(bookPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 250, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 50));

        patients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        patients.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(patients);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 840, 380));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 860, 450));

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

        refresh.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh (2).png"))); // NOI18N
        refreshPanel.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 30));

        jPanel1.add(refreshPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 60, -1));

        searchPatient.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        searchPatient.setForeground(new java.awt.Color(153, 153, 153));
        searchPatient.setText("Search");
        searchPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatientActionPerformed(evt);
            }
        });
        jPanel1.add(searchPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 230, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseClicked
        Admin_Add_Patient addPatientForm = new Admin_Add_Patient();
        addPatientForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addPanelMouseClicked

    private void addPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseEntered
        addPanel.setBackground(hoverColor);
    }//GEN-LAST:event_addPanelMouseEntered

    private void addPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseExited
        addPanel.setBackground(navColor);
    }//GEN-LAST:event_addPanelMouseExited

    private void updatePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseClicked

        int selectedPatientId = getSelectedPatientId(); // Get selected patient ID

        if (selectedPatientId != -1) {
            Admin_Update_Patient updateForm = new Admin_Update_Patient(selectedPatientId);
            updateForm.setVisible(true);
            this.dispose(); // Optional: close current window
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please select a patient to edit.</b></html>",
                "🔍 No Patient Selected",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_updatePanelMouseClicked

    private void updatePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseEntered
        updatePanel.setBackground(hoverColor);
    }//GEN-LAST:event_updatePanelMouseEntered

    private void updatePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseExited
        updatePanel.setBackground(navColor);
    }//GEN-LAST:event_updatePanelMouseExited

    private void archivePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivePanelMouseClicked
        int selectedPatientId = getSelectedPatientId(); // Get selected patient ID

        if (selectedPatientId != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Are you sure you want to archive this patient's information?</b><br><br>This action is irreversible.</html>",
                "⚠ Confirm Archive",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    // Step 1: Fetch patient details before archiving (for logging)
                    String fetchQuery = "SELECT p_fname, p_lname FROM patients WHERE patient_id = ?";
                    PreparedStatement fetchStmt = conn.prepareStatement(fetchQuery);
                    fetchStmt.setInt(1, selectedPatientId);
                    ResultSet rs = fetchStmt.executeQuery();

                    String patientName = "";
                    if (rs.next()) {
                        patientName = rs.getString("p_fname") + " " + rs.getString("p_lname");
                    }

                    // Step 2: Archive the patient
                    String archiveQuery = "UPDATE patients SET p_status = 'Archived' WHERE patient_id = ?";
                    PreparedStatement archiveStmt = conn.prepareStatement(archiveQuery);
                    archiveStmt.setInt(1, selectedPatientId);

                    int rowsUpdated = archiveStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        // 🔥 Step 3: Log the archive event with patient details
                        Session sess = Session.getInstance();
                        sess.logEvent("ARCHIVED PATIENT", "Admin archived patient: " + patientName + " (ID: " + selectedPatientId + ")");

                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Patient archived successfully!</b><br><br>The patient's information has been archived.</html>",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Failed to archive patient.</b><br><br>There was an issue with archiving the patient's information.</html>",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }

                    rs.close();
                    fetchStmt.close();
                    archiveStmt.close();
                    conn.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>Database error:</b> " + e.getMessage() + "<br><br>Please check your database connection and try again.</html>",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please select a patient to archive.</b><br><br>You must choose a patient from the list before attempting to archive their information.</html>",
                "Selection Required",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_archivePanelMouseClicked

    private void archivePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivePanelMouseEntered
        archivePanel.setBackground(hoverColor);
    }//GEN-LAST:event_archivePanelMouseEntered

    private void archivePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivePanelMouseExited
        archivePanel.setBackground(navColor);
    }//GEN-LAST:event_archivePanelMouseExited

    private void refreshPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseClicked
        loadPatients();
    }//GEN-LAST:event_refreshPanelMouseClicked

    private void refreshPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseEntered
        refreshPanel.setBackground(hoverColor);
    }//GEN-LAST:event_refreshPanelMouseEntered

    private void refreshPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseExited
        refreshPanel.setBackground(navColor);
    }//GEN-LAST:event_refreshPanelMouseExited

    private void searchPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPatientActionPerformed

    private void bookPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseClicked
       int selectedRow = patients.getSelectedRow(); // Get selected row from the table

        if (selectedRow != -1) {
            int patientId = (int) patients.getValueAt(selectedRow, 0); // Assume ID is in column 0

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Would you like to book an appointment for this patient?</b></html>",
                "📅 Confirm Booking",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                Session.getInstance().setPatientId(patientId); // Optional, if you're using session data
                Admin_Appointment_Add appointmentForm = new Admin_Appointment_Add();
                appointmentForm.setPatientId(patientId); // Set the patient ID field
                appointmentForm.setVisible(true);        // Show the form
            }
        } else {
            // Show warning if no patient is selected
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please select a patient first before booking an appointment.</b></html>",
                "🔍 No Patient Selected",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_bookPanelMouseClicked

    private void bookPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseEntered
        bookPanel.setBackground(hoverColor);
    }//GEN-LAST:event_bookPanelMouseEntered

    private void bookPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseExited
        bookPanel.setBackground(navColor);
    }//GEN-LAST:event_bookPanelMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JPanel addPanel;
    private javax.swing.JPanel archivePanel;
    private javax.swing.JPanel bookPanel;
    private javax.swing.JLabel delete;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel patient;
    private javax.swing.JPanel patient_header;
    private javax.swing.JTable patients;
    private javax.swing.JLabel refresh;
    private javax.swing.JPanel refreshPanel;
    private javax.swing.JTextField searchPatient;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables
}
