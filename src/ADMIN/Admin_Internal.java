
package ADMIN;

import AUTHENTICATION.LogIn;
import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Admin_Internal extends javax.swing.JInternalFrame {

    public Admin_Internal() {
        initComponents();
        loadDashboardStats();

        // Get session info
        Session sess = Session.getInstance();
        String username = sess.getUsername();
        int userId = sess.getUserId();
        String fname = sess.getStaffFirstName();
        String lname = sess.getStaffLastName();

        if (fname == null || lname == null || fname.isEmpty() || lname.isEmpty()) {
            admin.setText("Update your profile");
        } else {
            admin.setText(fname + " " + lname);
        }
        dashboard.setText(username + "'s Dashboard");
        
        

        // UI tweaks
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    

    private void loadDashboardStats() {
        ConnectDB connect = new ConnectDB();

        try {
            Connection conn = connect.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

            // Active Users
            String activeUserQuery = "SELECT COUNT(*) FROM users WHERE u_status = 'Active'";
            String pendingUserQuery = "SELECT COUNT(*) FROM users WHERE u_status = 'Pending'";

            // Patients with linked accounts
            String patientQuery = "SELECT COUNT(*) FROM patients WHERE user_id IS NOT NULL";

            // Doctors with linked accounts
            String doctorQuery = "SELECT COUNT(*) FROM dentist WHERE user_id IS NOT NULL";

            // Total Appointments
            String appointmentQuery = "SELECT COUNT(*) FROM appointments";

            // Total Services Availed in Appointments
            String servicesQuery = "SELECT COUNT(*) FROM treatment_services";

            // Helper method to run a single count query
            int activeUsersCount = getCount(conn, activeUserQuery);
            int pendingUsersCount = getCount(conn, pendingUserQuery);
            int patientCount = getCount(conn, patientQuery);
            int doctorCount = getCount(conn, doctorQuery);
            int appointmentCount = getCount(conn, appointmentQuery);
            int servicesCount = getCount(conn, servicesQuery);

            // Update labels (replace these with your actual component names)
            activeUsers.setText(String.valueOf(activeUsersCount));
            pendingUsers.setText(String.valueOf(pendingUsersCount));
            activePatients.setText(String.valueOf(patientCount));
            activeDoctors.setText(String.valueOf(doctorCount));
            totalAppointments.setText(String.valueOf(appointmentCount));
            totalServices.setText(String.valueOf(servicesCount));

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error loading dashboard stats: " + ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    private int getCount(Connection conn, String query) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        stmt.close();
        return count;
    }
    
    private void showProfile() {
        Admin_Profile prof = new Admin_Profile();
        JDesktopPane desktop = Session.getInstance().getDesktopPane();
        desktop.add(prof);
        prof.setVisible(true);
    }
    
    private void showLogs(){
        Admin_Logs log = new Admin_Logs();
        JDesktopPane desktop = Session.getInstance().getDesktopPane();
        desktop.add(log);
        log.setVisible(true);
    }

    
    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        dashboard_header = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        admin = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        logsPanel = new javax.swing.JPanel();
        refresh = new javax.swing.JLabel();
        picture = new javax.swing.JLabel();
        admin1 = new javax.swing.JLabel();
        profilePanel = new javax.swing.JPanel();
        refresh1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        activeUsers = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        account1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        account3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        activePatients = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        totalAppointments = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        account5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        account2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        pendingUsers = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        account6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        activeDoctors = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        account7 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        totalServices = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pieChart = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        account4 = new javax.swing.JLabel();
        account10 = new javax.swing.JLabel();
        account11 = new javax.swing.JLabel();
        account12 = new javax.swing.JLabel();
        account13 = new javax.swing.JLabel();
        account14 = new javax.swing.JLabel();
        account15 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        account9 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        account8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dashboard.setForeground(new java.awt.Color(255, 255, 255));
        dashboard.setText("Admin Dashboard");
        jPanel1.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 730, 50));

        dashboard_header.setBackground(new java.awt.Color(55, 162, 153));
        dashboard_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.add(dashboard_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        admin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        admin.setText("Full Name");
        jPanel2.add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 310, 40));

        username.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                usernameMouseReleased(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel2.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 250, 40));

        logsPanel.setBackground(new java.awt.Color(0, 51, 51));
        logsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logsPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logsPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logsPanelMouseExited(evt);
            }
        });
        logsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh.setText("System Logs");
        logsPanel.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 30));

        jPanel2.add(logsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 160, 30));

        picture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mother.png"))); // NOI18N
        jPanel2.add(picture, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 380, 190));

        admin1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        admin1.setText("Admin");
        jPanel2.add(admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 30));

        profilePanel.setBackground(new java.awt.Color(0, 51, 51));
        profilePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profilePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profilePanelMouseExited(evt);
            }
        });
        profilePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        refresh1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        refresh1.setForeground(new java.awt.Color(255, 255, 255));
        refresh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh1.setText("View Profile");
        profilePanel.add(refresh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 30));

        jPanel2.add(profilePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 160, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 850, 190));

        jPanel5.setBackground(new java.awt.Color(55, 162, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        activeUsers.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        activeUsers.setForeground(new java.awt.Color(255, 255, 255));
        activeUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeUsers.setText("0");
        jPanel5.add(activeUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 170, 40));

        jPanel6.setBackground(new java.awt.Color(55, 162, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account1.setForeground(new java.awt.Color(255, 255, 255));
        account1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account1.setText("ACTIVE USERS");
        jPanel6.add(account1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 170, 40));

        jPanel7.setBackground(new java.awt.Color(55, 162, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account3.setForeground(new java.awt.Color(255, 255, 255));
        account3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account3.setText("PATIENTS");
        jPanel7.add(account3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 170, 40));

        jPanel8.setBackground(new java.awt.Color(55, 162, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        activePatients.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        activePatients.setForeground(new java.awt.Color(255, 255, 255));
        activePatients.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activePatients.setText("0");
        jPanel8.add(activePatients, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 40));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 170, 40));

        jPanel11.setBackground(new java.awt.Color(55, 162, 153));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalAppointments.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        totalAppointments.setForeground(new java.awt.Color(255, 255, 255));
        totalAppointments.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalAppointments.setText("0");
        jPanel11.add(totalAppointments, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 40));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 170, 40));

        jPanel12.setBackground(new java.awt.Color(55, 162, 153));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account5.setForeground(new java.awt.Color(255, 255, 255));
        account5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account5.setText("TOTAL APPOINTMENTS");
        jPanel12.add(account5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 170, 40));

        jPanel9.setBackground(new java.awt.Color(55, 162, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        account2.setForeground(new java.awt.Color(255, 255, 255));
        account2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account2.setText("DENTAL SERVICES CLASSIFICATIONS");
        jPanel9.add(account2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 470, 40));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 490, 40));

        jPanel10.setBackground(new java.awt.Color(55, 162, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pendingUsers.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        pendingUsers.setForeground(new java.awt.Color(255, 255, 255));
        pendingUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingUsers.setText("0");
        jPanel10.add(pendingUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 40));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 170, 40));

        jPanel13.setBackground(new java.awt.Color(55, 162, 153));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account6.setForeground(new java.awt.Color(255, 255, 255));
        account6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account6.setText("DOCTORS");
        jPanel13.add(account6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 170, 40));

        jPanel14.setBackground(new java.awt.Color(55, 162, 153));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        activeDoctors.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        activeDoctors.setForeground(new java.awt.Color(255, 255, 255));
        activeDoctors.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeDoctors.setText("0");
        jPanel14.add(activeDoctors, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 40));

        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 170, 40));

        jPanel15.setBackground(new java.awt.Color(55, 162, 153));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account7.setForeground(new java.awt.Color(255, 255, 255));
        account7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account7.setText("TOTAL SERVICES");
        jPanel15.add(account7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 170, 40));

        jPanel16.setBackground(new java.awt.Color(55, 162, 153));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalServices.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        totalServices.setForeground(new java.awt.Color(255, 255, 255));
        totalServices.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalServices.setText("0");
        jPanel16.add(totalServices, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 40));

        jPanel1.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, 170, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(pieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 210));

        jPanel19.setBackground(java.awt.Color.cyan);
        jPanel3.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 40, 20));

        jPanel20.setBackground(java.awt.Color.pink);
        jPanel3.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 40, 20));

        jPanel21.setBackground(java.awt.Color.orange);
        jPanel3.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 40, 20));

        jPanel22.setBackground(java.awt.Color.yellow);
        jPanel3.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 40, 20));

        jPanel23.setBackground(java.awt.Color.green);
        jPanel3.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 40, 20));

        jPanel24.setBackground(java.awt.Color.magenta);
        jPanel3.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 40, 20));

        jPanel25.setBackground(java.awt.Color.blue);
        jPanel3.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 40, 20));

        account4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account4.setText("Wisdom Tooth Removal");
        jPanel3.add(account4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 150, 20));

        account10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account10.setText("Consultations");
        jPanel3.add(account10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 150, 20));

        account11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account11.setText("Cleanings");
        jPanel3.add(account11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 150, 20));

        account12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account12.setText("Tooth Extractions");
        jPanel3.add(account12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 150, 20));

        account13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account13.setText("Root Canals");
        jPanel3.add(account13, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 150, 20));

        account14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account14.setText("Braces");
        jPanel3.add(account14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 150, 20));

        account15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account15.setText("Retainers");
        jPanel3.add(account15, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 150, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 490, 230));

        jPanel17.setBackground(new java.awt.Color(55, 162, 153));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account9.setForeground(new java.awt.Color(255, 255, 255));
        account9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account9.setText("PENDING USERS");
        jPanel17.add(account9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        jPanel1.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 170, 40));

        jPanel18.setBackground(new java.awt.Color(55, 162, 153));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        account8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        account8.setForeground(new java.awt.Color(255, 255, 255));
        account8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        account8.setText("PENDING USERS");
        jPanel18.add(account8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        jPanel1.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 170, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        Session sess = Session.getInstance();

        if (sess.getUserId() == 0) {  // No user is logged in
            JOptionPane.showMessageDialog(null, "No Account, Log in First!", "Notice", JOptionPane.ERROR_MESSAGE);

            SwingUtilities.invokeLater(() -> {
                new LogIn().setVisible(true);
            });

            this.dispose(); 

        } else {
            try {
                ConnectDB dbc = new ConnectDB();
                ResultSet rs = dbc.getData("SELECT u_username FROM users WHERE user_id = " + sess.getUserId());

                if (rs.next()) {
                    String username = rs.getString("u_username");

                    // Update the dashboard text in the Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> {
                        dashboard.setText(username + "'s Dashboard");

                    });

                } else {
                    System.out.println("⚠ No user found for ID: " + sess.getUserId());
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost

    }//GEN-LAST:event_usernameFocusLost

    private void usernameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseReleased
        //        username.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12), new java.awt.Color(0, 0, 0)));
        //        errorUsername.setText("");
    }//GEN-LAST:event_usernameMouseReleased

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void logsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseClicked
        showLogs();
    }//GEN-LAST:event_logsPanelMouseClicked

    private void logsPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseEntered
        logsPanel.setBackground(hoverColor);
    }//GEN-LAST:event_logsPanelMouseEntered

    private void logsPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsPanelMouseExited
        logsPanel.setBackground(navColor);
    }//GEN-LAST:event_logsPanelMouseExited

    private void profilePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseClicked
        showProfile();
    }//GEN-LAST:event_profilePanelMouseClicked

    private void profilePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseEntered
        profilePanel.setBackground(hoverColor);
    }//GEN-LAST:event_profilePanelMouseEntered

    private void profilePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseExited
       profilePanel.setBackground(navColor);
    }//GEN-LAST:event_profilePanelMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel account1;
    private javax.swing.JLabel account10;
    private javax.swing.JLabel account11;
    private javax.swing.JLabel account12;
    private javax.swing.JLabel account13;
    private javax.swing.JLabel account14;
    private javax.swing.JLabel account15;
    private javax.swing.JLabel account2;
    private javax.swing.JLabel account3;
    private javax.swing.JLabel account4;
    private javax.swing.JLabel account5;
    private javax.swing.JLabel account6;
    private javax.swing.JLabel account7;
    private javax.swing.JLabel account8;
    private javax.swing.JLabel account9;
    private javax.swing.JLabel activeDoctors;
    private javax.swing.JLabel activePatients;
    private javax.swing.JLabel activeUsers;
    private javax.swing.JLabel admin;
    private javax.swing.JLabel admin1;
    private javax.swing.JLabel dashboard;
    private javax.swing.JPanel dashboard_header;
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
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JLabel pendingUsers;
    private javax.swing.JLabel picture;
    private javax.swing.JPanel pieChart;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel refresh;
    private javax.swing.JLabel refresh1;
    private javax.swing.JLabel totalAppointments;
    private javax.swing.JLabel totalServices;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
