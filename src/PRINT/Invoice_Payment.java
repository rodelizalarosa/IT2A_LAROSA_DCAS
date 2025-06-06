
package PRINT;


public class Invoice_Payment extends javax.swing.JFrame {

    public Invoice_Payment() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        invoice = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        invoiceID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        patientFNAME = new javax.swing.JLabel();
        patientNUMBER = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        invoiceDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        appID = new javax.swing.JLabel();
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
        jLabel25 = new javax.swing.JLabel();
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
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

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
        userID.setText("ID");
        invoice.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 100, 20));

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
        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 28)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(55, 162, 153));
        jLabel8.setText("INVOICE");
        invoice.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 260, 30));

        invoiceID.setBackground(new java.awt.Color(55, 162, 153));
        invoiceID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        invoiceID.setForeground(new java.awt.Color(51, 51, 51));
        invoiceID.setText("ID");
        invoice.add(invoiceID, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 120, 20));

        jLabel5.setBackground(new java.awt.Color(55, 162, 153));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Account No.");
        invoice.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 100, 20));

        patientFNAME.setBackground(new java.awt.Color(55, 162, 153));
        patientFNAME.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        patientFNAME.setForeground(new java.awt.Color(51, 51, 51));
        patientFNAME.setText("Full Name");
        invoice.add(patientFNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 230, 20));

        patientNUMBER.setBackground(new java.awt.Color(55, 162, 153));
        patientNUMBER.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        patientNUMBER.setForeground(new java.awt.Color(51, 51, 51));
        patientNUMBER.setText("Contact Number");
        invoice.add(patientNUMBER, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 230, 20));

        jLabel9.setBackground(new java.awt.Color(55, 162, 153));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Minglanilla, Cebu");
        invoice.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 150, 20));

        jPanel4.setBackground(new java.awt.Color(55, 162, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        invoice.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 260, 10));

        jLabel10.setBackground(new java.awt.Color(55, 162, 153));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Invoice no.");
        invoice.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 120, 20));

        jLabel11.setBackground(new java.awt.Color(55, 162, 153));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("dentalflow2025@gmail.com");
        invoice.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 580, 160, 20));

        jLabel12.setBackground(new java.awt.Color(55, 162, 153));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Invoice date:");
        invoice.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 120, 20));

        invoiceDate.setBackground(new java.awt.Color(55, 162, 153));
        invoiceDate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        invoiceDate.setForeground(new java.awt.Color(51, 51, 51));
        invoiceDate.setText("DATE");
        invoice.add(invoiceDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 120, 20));

        jLabel6.setBackground(new java.awt.Color(55, 162, 153));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Dentist");
        invoice.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 130, 20));

        appID.setBackground(new java.awt.Color(55, 162, 153));
        appID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        appID.setForeground(new java.awt.Color(51, 51, 51));
        appID.setText("ID");
        invoice.add(appID, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 100, 20));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost6.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceCost6.setText("0");
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
        totalCost.setText("0");
        totalCost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel8.add(totalCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, -1));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 150, 20));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service1.setBackground(new java.awt.Color(55, 162, 153));
        service1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        service1.setText("service 1");
        jPanel9.add(service1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 340, 30));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service2.setBackground(new java.awt.Color(55, 162, 153));
        service2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        service2.setText("service 2");
        jPanel10.add(service2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 340, 30));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service3.setBackground(new java.awt.Color(55, 162, 153));
        service3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        service3.setText("service 3");
        jPanel11.add(service3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 340, 30));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service4.setBackground(new java.awt.Color(55, 162, 153));
        service4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        service4.setText("service 4");
        jPanel12.add(service4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 340, 30));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        service5.setBackground(new java.awt.Color(55, 162, 153));
        service5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        service5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        service5.setText("service 5");
        jPanel13.add(service5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 340, 30));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setBackground(new java.awt.Color(55, 162, 153));
        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("service 6");
        jPanel14.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        jPanel5.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 340, 30));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost1.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceCost1.setText("0");
        jPanel15.add(serviceCost1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 150, 30));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost2.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceCost2.setText("0");
        jPanel16.add(serviceCost2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, 30));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost3.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceCost3.setText("0");
        jPanel17.add(serviceCost3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 150, 30));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost4.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceCost4.setText("0");
        jPanel18.add(serviceCost4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 150, 30));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        serviceCost5.setBackground(new java.awt.Color(55, 162, 153));
        serviceCost5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        serviceCost5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serviceCost5.setText("0");
        jPanel19.add(serviceCost5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        jPanel5.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 150, 30));

        invoice.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 490, 220));

        dentistFNAME.setBackground(new java.awt.Color(55, 162, 153));
        dentistFNAME.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        dentistFNAME.setForeground(new java.awt.Color(51, 51, 51));
        dentistFNAME.setText("Full Name");
        invoice.add(dentistFNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 230, 20));

        specialization.setBackground(new java.awt.Color(55, 162, 153));
        specialization.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        specialization.setForeground(new java.awt.Color(51, 51, 51));
        specialization.setText("Specialization");
        invoice.add(specialization, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 230, 20));

        jLabel7.setBackground(new java.awt.Color(55, 162, 153));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Appointment No.");
        invoice.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 130, 20));

        jLabel13.setBackground(new java.awt.Color(55, 162, 153));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Patient");
        invoice.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, 20));

        jLabel19.setBackground(new java.awt.Color(55, 162, 153));
        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("0991-794-0262");
        invoice.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 560, 150, 20));

        jLabel20.setBackground(new java.awt.Color(55, 162, 153));
        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Purok Burbos, Ward IV");
        invoice.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 150, 20));

        jPanel1.add(invoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 530, 620));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents


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
            java.util.logging.Logger.getLogger(Invoice_Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Invoice_Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Invoice_Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Invoice_Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Invoice_Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appID;
    private javax.swing.JLabel dentistFNAME;
    private javax.swing.JPanel invoice;
    private javax.swing.JLabel invoiceDate;
    private javax.swing.JLabel invoiceID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
