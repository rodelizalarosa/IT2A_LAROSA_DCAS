
package PRINT;

import ADMIN.Admin_View_Appointment;
import Config.ConnectDB;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.TextAlignment;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;





public class AppointmentSlip extends javax.swing.JFrame {

    private int appointmentId;
    private JLabel[] serviceLabels;
    private JLabel[] costLabels;

    
    public AppointmentSlip() {
        initComponents(); // GUI components
        serviceLabels = new JLabel[]{service1, service2, service3, service4, service5, service6};
        costLabels = new JLabel[]{serviceCost1, serviceCost2, serviceCost3, serviceCost4, serviceCost5, serviceCost6};
    }

    // Constructor when called with appointment ID
    public AppointmentSlip(int appointmentId) {
        this.appointmentId = appointmentId;
        initComponents(); // GUI components
        populateAppointmentSlip(appointmentId);
    }

    
    private void populateAppointmentSlip(int appointmentId) {
        ConnectDB connect = new ConnectDB();

        // Swing label arrays for services and costs
        JLabel[] serviceLabels = {service1, service2, service3, service4, service5, service6};
        JLabel[] costLabels = {serviceCost1, serviceCost2, serviceCost3, serviceCost4, serviceCost5, serviceCost6};

        // Clear previous values
        for (int i = 0; i < serviceLabels.length; i++) {
            serviceLabels[i].setText("");
            costLabels[i].setText("");
        }

        try (Connection conn = connect.getConnection()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(this,
                    "<html><b>❌ Failed to connect to the database.</b></html>",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Step 1: Check if appointment is confirmed
            String statusQuery = "SELECT a_status FROM appointments WHERE appointment_id = ?";
            try (PreparedStatement statusStmt = conn.prepareStatement(statusQuery)) {
                statusStmt.setInt(1, appointmentId);
                try (ResultSet statusRs = statusStmt.executeQuery()) {
                    if (statusRs.next()) {
                        String status = statusRs.getString("a_status");
                        if (!"Confirmed".equalsIgnoreCase(status)) {
                            JOptionPane.showMessageDialog(this,
                                "<html>" +
                                "<div style='font-size:10pt;'>" +
                                "<b>❌ Slip generation is only allowed for " +
                                "<span style='color:green;'>Confirmed</span> appointments.</b><br><br>" +
                                "This appointment is currently: " +
                                "<span style='color:red; font-weight:bold;'>" + status + "</span>" +
                                "</div></html>",
                                "Cannot Generate Slip",
                                JOptionPane.WARNING_MESSAGE
                            );
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "<html><b>❌ Appointment not found.</b></html>",
                            "Not Found",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AppointmentSlip.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Step 2: Load appointment and services data
            String query =
                "SELECT a.appointment_id, a.patient_id, a.pref_time, a.pref_date, " +
                "a.notes, a.dentist_id, p.p_fname, p.p_lname, p.p_contactNumber, " +
                "s.service_name, s.service_cost, ts.quantity, " +
                "CONCAT(d.d_fname, ' ', d.d_lname) AS dentist_name " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN treatment_services ts ON ts.appointment_id = a.appointment_id " +
                "JOIN services s ON ts.service_id = s.service_id " +
                "JOIN dentist d ON a.dentist_id = d.dentist_id " +
                "WHERE a.appointment_id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, appointmentId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    boolean dataFound = false;
                    int index = 0;
                    double total = 0.0;

                    while (rs.next()) {
                        dataFound = true;

                       // Only load general info on the first row
            if (index == 0) {
                userID.setText(String.valueOf(rs.getInt("patient_id")));
                appID.setText(String.valueOf(rs.getInt("appointment_id")));
                // Format the time string from "HH:mm:ss" to "hh:mm a" (e.g., 11:00 AM)
                String rawTime = rs.getString("pref_time");
                String formattedTime = rawTime;
                try {
                    java.text.SimpleDateFormat inputFormat = new java.text.SimpleDateFormat("HH:mm:ss");
                    java.text.SimpleDateFormat outputFormat = new java.text.SimpleDateFormat("hh:mm a");
                    java.util.Date date = inputFormat.parse(rawTime);
                    formattedTime = outputFormat.format(date);
                } catch (Exception e) {
                    // If parsing fails, keep the original time string
                    e.printStackTrace();
                }
                appTime.setText(formattedTime);
                appDate.setText(rs.getString("pref_date"));
                patientFNAME.setText(rs.getString("p_fname") + " " + rs.getString("p_lname"));
                patientNUMBER.setText(rs.getString("p_contactNumber"));
                dentistFNAME.setText(rs.getString("dentist_name"));
            }

            if (index < serviceLabels.length) {
                String name = rs.getString("service_name");
                double cost = rs.getDouble("service_cost");
                int quantity = rs.getInt("quantity");
                double subtotal = cost * quantity;

                serviceLabels[index].setText(name);
                costLabels[index].setText(String.format("%.2f", subtotal));

                total += subtotal;
                index++;
            }


                    if (!dataFound) {
                        JOptionPane.showMessageDialog(this,
                            "<html><b>❌ No treatment services found for this appointment.</b></html>",
                            "No Services",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                    totalCost.setText(String.format("%.2f", total));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "<html><b>❌ Database error:</b><br>" + ex.getMessage() + "</html>",
                "SQL Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }   catch (SQLException ex) {
            Logger.getLogger(AppointmentSlip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       private void exportPanelToPDF(JPanel panel, String filePath) throws IOException, com.itextpdf.text.DocumentException {
            int width = panel.getWidth();
            int height = panel.getHeight();

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            panel.paint(g2d);
            g2d.dispose();

            File tempImage = File.createTempFile("panel_capture", ".png");
            ImageIO.write(image, "png", tempImage);

            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            com.itextpdf.text.Image panelImg = com.itextpdf.text.Image.getInstance(tempImage.getAbsolutePath());
            panelImg.scaleToFit(document.getPageSize().getWidth() - 50, document.getPageSize().getHeight() - 50);
            panelImg.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            document.add(panelImg);

            document.close();
            tempImage.delete();
        }





    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        invoice = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        appID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        patientFNAME = new javax.swing.JLabel();
        patientNUMBER = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        appDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        serviceCost6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        totalCost = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        service1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        service2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        service3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        service4 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        service5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        service6 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        serviceCost1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        serviceCost2 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        serviceCost3 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        serviceCost4 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        serviceCost5 = new javax.swing.JLabel();
        dentistFNAME = new javax.swing.JLabel();
        specialization = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        appTime = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cancel = new javax.swing.JPanel();
        delete2 = new javax.swing.JLabel();
        exportPDF = new javax.swing.JPanel();
        delete1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(55, 162, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        invoice.setBackground(new java.awt.Color(255, 255, 255));
        invoice.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO_invoice.png"))); // NOI18N
        invoice.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 190, 80));

        userID.setBackground(new java.awt.Color(55, 162, 153));
        userID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userID.setForeground(new java.awt.Color(51, 51, 51));
        invoice.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 100, 20));

        jPanel2.setBackground(new java.awt.Color(55, 162, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        invoice.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 490, 10));

        jLabel8.setBackground(new java.awt.Color(55, 162, 153));
        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 25)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(55, 162, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("APPOINTMENT");
        invoice.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 260, 30));

        appID.setBackground(new java.awt.Color(55, 162, 153));
        appID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appID.setForeground(new java.awt.Color(51, 51, 51));
        invoice.add(appID, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 120, 20));

        jLabel5.setBackground(new java.awt.Color(55, 162, 153));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Account No.");
        invoice.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 20));

        patientFNAME.setBackground(new java.awt.Color(55, 162, 153));
        patientFNAME.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        patientFNAME.setForeground(new java.awt.Color(51, 51, 51));
        invoice.add(patientFNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 230, 20));

        patientNUMBER.setBackground(new java.awt.Color(55, 162, 153));
        patientNUMBER.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        patientNUMBER.setForeground(new java.awt.Color(51, 51, 51));
        invoice.add(patientNUMBER, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 230, 20));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(55, 162, 153)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        invoice.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 260, 50));

        jLabel10.setBackground(new java.awt.Color(55, 162, 153));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Appointment no.:");
        invoice.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 120, 20));

        jLabel11.setBackground(new java.awt.Color(55, 162, 153));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("dentalflow2025@gmail.com");
        invoice.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 580, 190, 20));

        jLabel12.setBackground(new java.awt.Color(55, 162, 153));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Appointment date:");
        invoice.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 140, 20));

        appDate.setBackground(new java.awt.Color(55, 162, 153));
        appDate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appDate.setForeground(new java.awt.Color(51, 51, 51));
        invoice.add(appDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 120, 20));

        jLabel6.setBackground(new java.awt.Color(55, 162, 153));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Dentist");
        invoice.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 130, 20));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost6.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(serviceCost6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 150, 30));

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(55, 162, 153));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Description");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 20));

        jLabel16.setBackground(new java.awt.Color(55, 162, 153));
        jLabel16.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Amount");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 150, -1));

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, -1));

        jLabel14.setBackground(new java.awt.Color(55, 162, 153));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Amount");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 150, 20));

        jLabel15.setBackground(new java.awt.Color(55, 162, 153));
        jLabel15.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Amount");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 150, 20));

        jPanel7.setBackground(new java.awt.Color(55, 162, 153));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setBackground(new java.awt.Color(55, 162, 153));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("TOTAL");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, -1));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 340, 20));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalCost.setBackground(new java.awt.Color(0, 0, 0));
        totalCost.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        totalCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalCost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel8.add(totalCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 20));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 150, 20));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service1.setBackground(new java.awt.Color(55, 162, 153));
        service1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(service1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, 30));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service2.setBackground(new java.awt.Color(55, 162, 153));
        service2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel10.add(service2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 340, 30));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service3.setBackground(new java.awt.Color(55, 162, 153));
        service3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(service3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 340, 30));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service4.setBackground(new java.awt.Color(55, 162, 153));
        service4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(service4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 340, 30));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service5.setBackground(new java.awt.Color(55, 162, 153));
        service5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel13.add(service5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 340, 30));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service6.setBackground(new java.awt.Color(55, 162, 153));
        service6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel14.add(service6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 340, 30));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost1.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel15.add(serviceCost1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 150, 30));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost2.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel16.add(serviceCost2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, 30));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost3.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel17.add(serviceCost3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 150, 30));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost4.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel18.add(serviceCost4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 150, 30));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost5.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel19.add(serviceCost5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 150, 30));

        invoice.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 490, 220));

        dentistFNAME.setBackground(new java.awt.Color(55, 162, 153));
        dentistFNAME.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        dentistFNAME.setForeground(new java.awt.Color(51, 51, 51));
        invoice.add(dentistFNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 230, 20));

        specialization.setBackground(new java.awt.Color(55, 162, 153));
        specialization.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        specialization.setForeground(new java.awt.Color(51, 51, 51));
        invoice.add(specialization, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 230, 20));

        jLabel13.setBackground(new java.awt.Color(55, 162, 153));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Patient");
        invoice.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, 20));

        jLabel19.setBackground(new java.awt.Color(55, 162, 153));
        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Minglanilla, Cebu");
        invoice.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 170, 20));

        jLabel20.setBackground(new java.awt.Color(55, 162, 153));
        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Purok Burbos, Ward IV ");
        invoice.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 560, 160, 20));

        appTime.setBackground(new java.awt.Color(55, 162, 153));
        appTime.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        appTime.setForeground(new java.awt.Color(51, 51, 51));
        invoice.add(appTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 120, 20));

        jLabel18.setBackground(new java.awt.Color(55, 162, 153));
        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Appointment time:");
        invoice.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 130, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/call.png"))); // NOI18N
        invoice.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 50, 40));

        jLabel21.setBackground(new java.awt.Color(55, 162, 153));
        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("0991-794-0262");
        invoice.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 560, 190, 20));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/address.png"))); // NOI18N
        invoice.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 50, 40));

        jPanel1.add(invoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 530, 620));

        cancel.setBackground(new java.awt.Color(0, 51, 51));
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelMouseExited(evt);
            }
        });
        cancel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete2.setForeground(new java.awt.Color(255, 255, 255));
        delete2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete2.setText("CANCEL");
        cancel.add(delete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 680, 130, 30));

        exportPDF.setBackground(new java.awt.Color(0, 51, 51));
        exportPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportPDFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exportPDFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exportPDFMouseExited(evt);
            }
        });
        exportPDF.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete1.setForeground(new java.awt.Color(255, 255, 255));
        delete1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete1.setText("EXPORT");
        exportPDF.add(delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel1.add(exportPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 680, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseEntered
       cancel.setBackground(hoverColor);
    }//GEN-LAST:event_cancelMouseEntered

    private void cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseExited
       cancel.setBackground(navColor);
    }//GEN-LAST:event_cancelMouseExited

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        Admin_View_Appointment view = new Admin_View_Appointment();
        view.setVisible(true);
    }//GEN-LAST:event_cancelMouseClicked

    private void exportPDFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportPDFMouseEntered
        exportPDF.setBackground(hoverColor);
    }//GEN-LAST:event_exportPDFMouseEntered

    private void exportPDFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportPDFMouseExited
        exportPDF.setBackground(navColor);
    }//GEN-LAST:event_exportPDFMouseExited

    private void exportPDFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportPDFMouseClicked
        JFileChooser fileChooser = new JFileChooser();

        // 💡 Set default file name
        String defaultFileName = "Appointment_Slip_" + appID.getText() + ".pdf"; // Or any logic you prefer
        fileChooser.setSelectedFile(new File(defaultFileName));

        fileChooser.setDialogTitle("Save Appointment Slip as PDF");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Add .pdf extension if not present
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                exportPanelToPDF(invoice, filePath); // Replace 'invoicePanel' with your panel name
                JOptionPane.showMessageDialog(this,
                    "PDF exported successfully:\n" + filePath,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                Admin_View_Appointment adminView = new Admin_View_Appointment(); // replace with your actual class name
                adminView.setVisible(true);
                this.dispose();
                
            } catch (Exception ex) {
                Logger.getLogger(AppointmentSlip.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                    "<html><b>❌ Failed to export PDF:</b><br>" + ex.getMessage() + "</html>",
                    "Export Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_exportPDFMouseClicked


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
            java.util.logging.Logger.getLogger(AppointmentSlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentSlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentSlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentSlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentSlip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appDate;
    private javax.swing.JLabel appID;
    private javax.swing.JLabel appTime;
    private javax.swing.JPanel cancel;
    private javax.swing.JLabel delete1;
    private javax.swing.JLabel delete2;
    private javax.swing.JLabel dentistFNAME;
    private javax.swing.JPanel exportPDF;
    private javax.swing.JPanel invoice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel patientFNAME;
    private javax.swing.JLabel patientNUMBER;
    private javax.swing.JLabel service1;
    private javax.swing.JLabel service2;
    private javax.swing.JLabel service3;
    private javax.swing.JLabel service4;
    private javax.swing.JLabel service5;
    private javax.swing.JLabel service6;
    private javax.swing.JLabel serviceCost1;
    private javax.swing.JLabel serviceCost2;
    private javax.swing.JLabel serviceCost3;
    private javax.swing.JLabel serviceCost4;
    private javax.swing.JLabel serviceCost5;
    private javax.swing.JLabel serviceCost6;
    private javax.swing.JLabel specialization;
    private javax.swing.JLabel totalCost;
    private javax.swing.JLabel userID;
    // End of variables declaration//GEN-END:variables
}
