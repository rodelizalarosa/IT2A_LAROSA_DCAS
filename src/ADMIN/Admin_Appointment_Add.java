
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Admin_Appointment_Add extends javax.swing.JFrame {

    private TableModelListener servicesTableListener;
  
    private int patientId;

    public Admin_Appointment_Add() {
        initComponents();  
    }
    
    public Admin_Appointment_Add(int patientId, int dentistId, Date appointmentDate) {
        this.patientId = patientId;
        initComponents();             // Initialize GUI
        setupServicesTable();         // FIXED: Needed to populate services table
        initTimeSlots(dentistId, appointmentDate);
        patientID.setText(String.valueOf(patientId)); // Pre-fill Patient ID
        patientID.setEditable(false);                 // Make Patient ID uneditable
        loadServices();              // Load services into the table
        loadDentists();              // Load dentist names into ComboBox
    }

    
     private void initTimeSlots(int dentistId, Date appointmentDate) {
        // This method initializes the combo box with available time slots, 
        // checking for taken slots for the given dentist and date
        List<String> availableTimes = Arrays.asList("09:00 AM", "10:00 AM", "11:00 AM", "02:00 PM", "03:00 PM");
        List<String> takenTimes = getTakenTimesForDentist(dentistId, appointmentDate);

        // Clear previous items from combo box (if any)
        time.removeAllItems();

        // Add available times to the combo box and disable already taken times
        for (String timeSlot : availableTimes) {
            if (takenTimes.contains(timeSlot)) {
                time.addItem(timeSlot + " (Taken)");
            } else {
                time.addItem(timeSlot);
            }
        }
    }

    // Example of method to fetch taken times for a specific dentist on a given date
    private List<String> getTakenTimesForDentist(int dentistId, Date appointmentDate) {
        List<String> takenTimes = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT pref_time FROM appointments WHERE dentist_id = ? AND pref_date = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, dentistId);
                stmt.setDate(2, new java.sql.Date(appointmentDate.getTime()));

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    takenTimes.add(rs.getString("pref_time"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return takenTimes;
    }
    
    private void loadServices() {
        ConnectDB connect = new ConnectDB();
        DefaultTableModel model = (DefaultTableModel) servicesTable.getModel();
        model.setRowCount(0); // Clear previous rows

        String query = "SELECT service_name, service_cost FROM services";

        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("service_name");
                double cost = rs.getDouble("service_cost");
                int quantity = 1;
                double total = cost * quantity;

                model.addRow(new Object[]{
                    false, // Select checkbox
                    name,
                    quantity,
                    cost,
                    total
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading services: " + e.getMessage());
            e.printStackTrace();
        }
    }
   
   private void setupServicesTable() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Select", "Service Name", "Quantity", "Price", "Total"}
        ) {
            Class[] types = new Class[]{
                Boolean.class, String.class, Integer.class, Double.class, Double.class
            };
            boolean[] canEdit = new boolean[]{
                true, false, true, false, false
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        servicesTable.setModel(model);
        servicesTable.setRowHeight(25);
        servicesTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        servicesTable.getTableHeader().setBackground(new Color(51, 51, 255));
        servicesTable.getTableHeader().setForeground(new Color(0, 0, 0));

        // Set default editor and renderer for checkbox column to avoid selection bugs
        servicesTable.getColumnModel().getColumn(0).setCellEditor(servicesTable.getDefaultEditor(Boolean.class));
        servicesTable.getColumnModel().getColumn(0).setCellRenderer(servicesTable.getDefaultRenderer(Boolean.class));

        // Adjust column widths
        servicesTable.getColumnModel().getColumn(0).setPreferredWidth(20);  // Select
        servicesTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Service Name
        servicesTable.getColumnModel().getColumn(2).setPreferredWidth(60);  // Quantity
        servicesTable.getColumnModel().getColumn(3).setPreferredWidth(60);  // Price
        servicesTable.getColumnModel().getColumn(4).setPreferredWidth(70);  // Total

        servicesTableListener = e -> calculateTotalCost();
        model.addTableModelListener(servicesTableListener);

    }


   private void calculateTotalCost() {
        DefaultTableModel model = (DefaultTableModel) servicesTable.getModel();

        // Temporarily remove listener to avoid infinite loop
        model.removeTableModelListener(servicesTableListener);

        double totalCost = 0.0;

        for (int i = 0; i < model.getRowCount(); i++) {
            Object selectedObj = model.getValueAt(i, 0);
            boolean isSelected = selectedObj instanceof Boolean && (Boolean) selectedObj;

            if (isSelected) {
                Object quantityObj = model.getValueAt(i, 2);
                Object priceObj = model.getValueAt(i, 3);

                int quantity = 1;
                double price = 0.0;

                if (quantityObj instanceof Integer) {
                    quantity = (Integer) quantityObj;
                    if (quantity <= 0) {
                        quantity = 1;
                        model.setValueAt(1, i, 2);
                    }
                }

                if (priceObj instanceof Double) {
                    price = (Double) priceObj;
                }

                double rowTotal = quantity * price;
                model.setValueAt(rowTotal, i, 4);
                totalCost += rowTotal;
            } else {
                model.setValueAt(0.0, i, 4);
            }
        }

        total_cost.setText(String.format("%.2f", totalCost));

        // Re-add listener
        model.addTableModelListener(servicesTableListener);
    }
   
    private void initTimeSlots() {
         // Get available time slots (for demonstration purposes, it's hardcoded)
         List<String> availableTimes = getAvailableTimesForDentist();

         // Add available times to the combo box
         for (String timeSlot : availableTimes) {
             time.addItem(timeSlot);  // Use 'time' instead of 'timeCombo'
         }
     }

     // Example method to get available times (this could be fetched from a database)
     private List<String> getAvailableTimesForDentist() {
         // Sample time slots for demonstration
         List<String> times = new ArrayList<String>();  // Specify the type as <String>
         times.add("09:00 AM");
         times.add("10:00 AM");
         times.add("11:00 AM");
         times.add("02:00 PM");
         times.add("03:00 PM");
         return times;
     }
   
    private void loadDentists() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT d.d_fname AS fname, d.d_lname AS lname " +
                           "FROM dentists d " +
                           "JOIN users u ON d.d_user_id = u.user_id " +
                           "WHERE u.u_role = 'Dentist' AND u.u_status = 'Active'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            dentist.removeAllItems();  // Clear ComboBox

            while (rs.next()) {
                String fullName = rs.getString("fname") + " " + rs.getString("lname");
                dentist.addItem(fullName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


  private void clearFormFields() {
        // Clear input fields
        patientID.setText("");
        time.setSelectedIndex(0);
        notes.setText("");
        app.setDate(null);
        dentist.setSelectedIndex(0);
        total_cost.setText("");  // Clear the total cost field

        // Reset services table: uncheck all and set quantity to 1
        DefaultTableModel model = (DefaultTableModel) servicesTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 0); // Uncheck checkbox
            model.setValueAt(1, i, 2);     // Reset quantity to 1 (column 2)
            model.setValueAt(0.0, i, 4);   // Reset total cost (column 4)
        }
    }

   
   private Date stripTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    
    Color Hover = new Color (55,162,153);
    Color Nav = new Color (0,51,51);



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        appointment_header = new javax.swing.JPanel();
        appointment = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        account2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        appointment5 = new javax.swing.JLabel();
        prefdate = new javax.swing.JLabel();
        time = new javax.swing.JComboBox<>();
        appointment9 = new javax.swing.JLabel();
        appointment11 = new javax.swing.JLabel();
        notes = new javax.swing.JTextField();
        appointment12 = new javax.swing.JLabel();
        app = new com.toedter.calendar.JDateChooser();
        appointment6 = new javax.swing.JLabel();
        patientID = new javax.swing.JTextField();
        dentist = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        account3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        servicesTable = new javax.swing.JTable();
        backPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        bookPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        total_cost = new javax.swing.JTextField();
        appointment7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment_header.setBackground(new java.awt.Color(55, 162, 153));
        appointment_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        appointment_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appointment.setForeground(new java.awt.Color(255, 255, 255));
        appointment.setText("Appointment Setup");
        appointment_header.add(appointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 210, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Fill out patient and appointment details.");
        appointment_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 380, 50));

        jPanel1.add(appointment_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 900, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(55, 162, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        account2.setForeground(new java.awt.Color(255, 255, 255));
        account2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account2.setText("APPOINTMENT DETAILS");
        jPanel9.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fill out appointment details to complete.");
        jPanel9.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 400, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 50));

        appointment5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment5.setForeground(new java.awt.Color(51, 51, 51));
        appointment5.setText("Preferred Time");
        jPanel2.add(appointment5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 160, 30));

        prefdate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        prefdate.setForeground(new java.awt.Color(51, 51, 51));
        prefdate.setText("Preferred Date");
        jPanel2.add(prefdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 160, 30));

        time.setBackground(new java.awt.Color(204, 204, 204));
        time.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        time.setForeground(new java.awt.Color(51, 51, 51));
        time.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                timeFocusLost(evt);
            }
        });
        jPanel2.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 220, 30));

        appointment9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment9.setForeground(new java.awt.Color(51, 51, 51));
        appointment9.setText("Preferred Dentist");
        jPanel2.add(appointment9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 160, 30));

        appointment11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment11.setForeground(new java.awt.Color(51, 51, 51));
        appointment11.setText("or Notes");
        jPanel2.add(appointment11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 160, 30));

        notes.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        notes.setForeground(new java.awt.Color(51, 51, 51));
        notes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                notesFocusLost(evt);
            }
        });
        notes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                notesMouseReleased(evt);
            }
        });
        notes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notesActionPerformed(evt);
            }
        });
        jPanel2.add(notes, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 220, 80));

        appointment12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment12.setForeground(new java.awt.Color(51, 51, 51));
        appointment12.setText("Special Requests");
        jPanel2.add(appointment12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 160, 30));

        app.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(app, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 220, 30));

        appointment6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment6.setForeground(new java.awt.Color(51, 51, 51));
        appointment6.setText("Patient ID");
        jPanel2.add(appointment6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 160, 30));

        patientID.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        patientID.setForeground(new java.awt.Color(51, 51, 51));
        patientID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                patientIDFocusLost(evt);
            }
        });
        patientID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                patientIDMouseReleased(evt);
            }
        });
        patientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientIDActionPerformed(evt);
            }
        });
        jPanel2.add(patientID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 80, 30));

        dentist.setBackground(new java.awt.Color(204, 204, 204));
        dentist.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        dentist.setForeground(new java.awt.Color(51, 51, 51));
        dentist.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dentistFocusLost(evt);
            }
        });
        jPanel2.add(dentist, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 220, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 400, 380));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(55, 162, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        account3.setForeground(new java.awt.Color(255, 255, 255));
        account3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account3.setText("Dental Services");
        jPanel10.add(account3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Select your preferred dental services.");
        jPanel10.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 410, 30));

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 50));

        servicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(servicesTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 390, 310));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 410, 380));

        backPanel.setBackground(new java.awt.Color(0, 51, 51));
        backPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Back");
        backPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel1.add(backPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 490, 90, -1));

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
        jLabel2.setText("Book an Appointment");
        bookPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 30));

        jPanel1.add(bookPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 490, 200, -1));

        total_cost.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        total_cost.setForeground(new java.awt.Color(51, 51, 51));
        total_cost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                total_costFocusLost(evt);
            }
        });
        total_cost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                total_costMouseReleased(evt);
            }
        });
        total_cost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_costActionPerformed(evt);
            }
        });
        jPanel1.add(total_cost, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 130, 30));

        appointment7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appointment7.setForeground(new java.awt.Color(51, 51, 51));
        appointment7.setText("Total Cost");
        jPanel1.add(appointment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 120, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void timeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFocusLost

    private void notesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notesFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_notesFocusLost

    private void notesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notesMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_notesMouseReleased

    private void notesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notesActionPerformed

    private void bookPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseEntered
        bookPanel.setBackground(Hover);
    }//GEN-LAST:event_bookPanelMouseEntered

    private void bookPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseExited
        bookPanel.setBackground(Nav);
    }//GEN-LAST:event_bookPanelMouseExited

    private void bookPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookPanelMouseClicked
         // Step 1: Validate all fields
        String selectedDentist = dentist.getSelectedItem().toString();
        Date appointmentDate = app.getDate(); // java.util.Date
        String preferredTime = time.getSelectedItem().toString(); // Time selected from JComboBox
        String notesText = notes.getText().trim();
        String patientIdStr = patientID.getText().trim();

        // Check if any of the required fields are empty
        if (selectedDentist.equals("Select") || appointmentDate == null || preferredTime.isEmpty() || patientIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete all appointment fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert patient ID from String to int
        int patientId = Integer.parseInt(patientIdStr);

        // Strip time from both today and appointmentDate to avoid time comparison issues
        Date today = stripTime(new Date()); // java.util.Date
        Date appDate = stripTime(appointmentDate); // java.util.Date

        // Validate that the appointment date is after today
        if (!appDate.after(today)) {
            JOptionPane.showMessageDialog(this, "Appointment date must be after today.", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConnectDB.getConnection()) {
            // Step 2: Get Dentist ID based on full name from the selected dentist
            int dentistId = -1;
            String dentistQuery = "SELECT user_id FROM users WHERE CONCAT(u_fname, ' ', u_lname) = ? AND u_role = 'Dentist' AND u_status = 'Active'";
            try (PreparedStatement dentistStmt = conn.prepareStatement(dentistQuery)) {
                dentistStmt.setString(1, selectedDentist);
                ResultSet dentistRs = dentistStmt.executeQuery();
                if (dentistRs.next()) {
                    dentistId = dentistRs.getInt("user_id");
                }
            }

            // Check if the dentist was found
            if (dentistId == -1) {
                JOptionPane.showMessageDialog(this, "Selected Dentist not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Step 3: Validate that the selected time is not already booked for the same dentist
            String checkTimeQuery = "SELECT appointment_id FROM appointments WHERE dentist_id = ? AND pref_date = ? AND pref_time = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkTimeQuery)) {
                checkStmt.setInt(1, dentistId);
                checkStmt.setDate(2, new java.sql.Date(appDate.getTime()));
                checkStmt.setString(3, preferredTime);

                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "⚠ This time slot is already booked for the selected dentist.", "Time Conflict", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Step 4: Insert the new appointment
            String insertAppointment = "INSERT INTO appointments (patient_id, dentist_id, pref_time, pref_date, notes, a_status) VALUES (?, ?, ?, ?, ?, ?)";
            int appointmentId = -1;
            try (PreparedStatement appStmt = conn.prepareStatement(insertAppointment, Statement.RETURN_GENERATED_KEYS)) {
                appStmt.setInt(1, patientId);
                appStmt.setInt(2, dentistId);
                appStmt.setString(3, preferredTime);
                appStmt.setDate(4, new java.sql.Date(appDate.getTime())); // Convert java.util.Date to java.sql.Date for SQL
                appStmt.setString(5, notesText);
                appStmt.setString(6, "Pending");
                appStmt.executeUpdate();

                ResultSet appKeys = appStmt.getGeneratedKeys();
                if (appKeys.next()) {
                    appointmentId = appKeys.getInt(1);
                }
            }

            // Step 5: Insert selected services for the appointment
            DefaultTableModel model = (DefaultTableModel) servicesTable.getModel();
            String insertService = "INSERT INTO treatment_services (appointment_id, service_id, quantity, total_cost) VALUES (?, ?, ?, ?)";
            try (PreparedStatement serviceStmt = conn.prepareStatement(insertService)) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    Boolean selected = (Boolean) model.getValueAt(i, 0); // Checkbox column (0)
                    if (selected != null && selected) {
                        int serviceId = (int) model.getValueAt(i, 1); // Service ID column (1)
                        int quantity = (int) model.getValueAt(i, 3); // Quantity column (3)
                        double totalCost = (double) model.getValueAt(i, 4); // Total Cost column (4)

                        serviceStmt.setInt(1, appointmentId);
                        serviceStmt.setInt(2, serviceId);
                        serviceStmt.setInt(3, quantity);
                        serviceStmt.setDouble(4, totalCost);
                        serviceStmt.addBatch();
                    }
                }
                serviceStmt.executeBatch();
            }

            // Step 6: Log the event (appointment booking)
            Session.getInstance().logEvent(
                "Appointment Booking",
                "Booked appointment (ID: " + appointmentId + ") for Patient ID: " + patientId
            );

            // Step 7: Display success message
            JOptionPane.showMessageDialog(this,
                "🎉 Appointment booked successfully!\nAppointment ID: " + appointmentId,
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );

            // Step 8: Clear Form
            clearFormFields();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bookPanelMouseClicked

    private void patientIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientIDFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDFocusLost

    private void patientIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientIDMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDMouseReleased

    private void patientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIDActionPerformed

    private void total_costFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_total_costFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_total_costFocusLost

    private void total_costMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_total_costMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_total_costMouseReleased

    private void total_costActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_costActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_costActionPerformed

    private void dentistFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dentistFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_dentistFocusLost

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
            java.util.logging.Logger.getLogger(Admin_Appointment_Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Appointment_Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Appointment_Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Appointment_Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Appointment_Add().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account2;
    private javax.swing.JLabel account3;
    private com.toedter.calendar.JDateChooser app;
    private javax.swing.JLabel appointment;
    private javax.swing.JLabel appointment11;
    private javax.swing.JLabel appointment12;
    private javax.swing.JLabel appointment5;
    private javax.swing.JLabel appointment6;
    private javax.swing.JLabel appointment7;
    private javax.swing.JLabel appointment9;
    private javax.swing.JPanel appointment_header;
    private javax.swing.JPanel backPanel;
    private javax.swing.JPanel bookPanel;
    private javax.swing.JComboBox<String> dentist;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField notes;
    private javax.swing.JTextField patientID;
    private javax.swing.JLabel prefdate;
    private javax.swing.JTable servicesTable;
    private javax.swing.JComboBox<String> time;
    private javax.swing.JTextField total_cost;
    // End of variables declaration//GEN-END:variables
}
