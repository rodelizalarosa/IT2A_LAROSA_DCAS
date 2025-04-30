
package ADMIN;

import Config.ConnectDB;
import Config.Session;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;


public class Admin_User_Internal extends javax.swing.JInternalFrame {

   
    public Admin_User_Internal() {
        initComponents();
        loadUsers();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        users.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        users.getTableHeader().setOpaque(false);
        users.getTableHeader().setBorder(null);
        users.getTableHeader().setBackground(new Color(51, 51, 255));
        users.getTableHeader().setForeground(new Color(0, 0, 0));
        users.setRowHeight(25);
        
        //table colum size
        users.getColumnModel().getColumn(0).setPreferredWidth(25);
        users.getColumnModel().getColumn(1).setPreferredWidth(55);
        users.getColumnModel().getColumn(2).setPreferredWidth(150);
        users.getColumnModel().getColumn(3).setPreferredWidth(55);
        users.getColumnModel().getColumn(4).setPreferredWidth(55);    
        
        //FOR LIVE SEARCH LEZGOOO
        searchUser.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchUsers();
            }
        });     
        
        filterUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchUsers();
            }
        });
        
        users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Double-click detected
                    int selectedRow = users.getSelectedRow();
                    if (selectedRow != -1) {
                        int selectedUserId = Integer.parseInt(users.getValueAt(selectedRow, 0).toString());
                        new Admin_Update_User(selectedUserId).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a user.");
                    }
                }
            }
        });


    }
    
    private void loadUsers() {
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"User ID", "Username", "Email", "Role", "Status"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ConnectDB connect = new ConnectDB();

            try {
                Connection conn = connect.getConnection();
                if (conn == null) {
                    System.out.println("Database connection failed!");
                    return;
                }

                // 🔥 Exclude archived users
                String query = "SELECT user_id, u_username, u_email, u_role, u_status FROM users WHERE u_status != 'Archived'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("user_id"),
                        rs.getString("u_username"),
                        rs.getString("u_email"),
                        rs.getString("u_role"),
                        rs.getString("u_status")
                    });
                }

                users.setModel(model);
                model.fireTableDataChanged();

                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException ex) {
                System.out.println("Error loading users: " + ex.getMessage());
                ex.printStackTrace();
            }

            users.setModel(model);
            users.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    
    public int getSelectedUserId() {
        int selectedRow = users.getSelectedRow(); // Get the selected row index

            if (selectedRow != -1) { // Check if a row is selected
                return Integer.parseInt(users.getValueAt(selectedRow, 0).toString()); // Get user_id from the first column
            }
        return -1; // Return -1 if no row is selected
    }
    
    private void searchUsers() {
        String keyword = searchUser.getText().trim();
        String filter = filterUser.getSelectedItem().toString();

        try {
            ConnectDB connect = new ConnectDB();
            Connection conn = connect.getConnection();

            // Build the SQL query
            StringBuilder sql = new StringBuilder("SELECT user_id, u_username, u_email, u_role, u_status FROM users WHERE 1=1");

            if (!keyword.isEmpty()) {
                sql.append(" AND (u_username LIKE ? OR u_email LIKE ?)");
            }

            if (!filter.equals("All")) {
                sql.append(" AND u_role = ?");
            }

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (!keyword.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + keyword + "%");
                pstmt.setString(paramIndex++, "%" + keyword + "%");
            }
            if (!filter.equals("All")) {
                pstmt.setString(paramIndex++, filter);
            }

            ResultSet rs = pstmt.executeQuery();

            // Clear existing table data
            DefaultTableModel model = (DefaultTableModel) users.getModel();
            model.setRowCount(0);

            // Populate table with search results
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("u_username");
                String email = rs.getString("u_email");
                String role = rs.getString("u_role");
                String status = rs.getString("u_status");

                model.addRow(new Object[]{userId, username, email, role, status});
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    Color hoverColor = new Color (55,162,153);
    Color navColor = new Color (0,51,51);
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        user_header = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        activatePanel = new javax.swing.JPanel();
        activate = new javax.swing.JLabel();
        addPanel = new javax.swing.JPanel();
        add = new javax.swing.JLabel();
        updatePanel = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        archivePanel = new javax.swing.JPanel();
        delete = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        users = new javax.swing.JTable();
        refreshPanel = new javax.swing.JPanel();
        refresh = new javax.swing.JLabel();
        searchUser = new javax.swing.JTextField();
        filterUser = new javax.swing.JComboBox<>();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_header.setBackground(new java.awt.Color(55, 162, 153));
        user_header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        user_header.setForeground(new java.awt.Color(204, 204, 204));
        user_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setText("User Accounts");
        user_header.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 150, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Manage user accounts");
        user_header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 5, 190, 40));

        jPanel1.add(user_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(55, 162, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        activatePanel.setBackground(new java.awt.Color(0, 51, 51));
        activatePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activatePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                activatePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                activatePanelMouseExited(evt);
            }
        });
        activatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        activate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        activate.setForeground(new java.awt.Color(255, 255, 255));
        activate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activate.setText("ACTIVATE");
        activatePanel.add(activate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));

        jPanel3.add(activatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, -1));

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

        jPanel3.add(addPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, -1));

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

        jPanel3.add(updatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 110, -1));

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

        jPanel3.add(archivePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 110, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 50));

        users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        users.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(users);

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

        searchUser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        searchUser.setForeground(new java.awt.Color(153, 153, 153));
        searchUser.setText("Search");
        searchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUserActionPerformed(evt);
            }
        });
        jPanel1.add(searchUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 230, 30));

        filterUser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        filterUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Admin", "Patient", "Dentist" }));
        jPanel1.add(filterUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void activatePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activatePanelMouseClicked
         int selectedUserId = getSelectedUserId(); // Get the selected user's ID

        if (selectedUserId != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Are you sure you want to activate this user account?</b><br>This will allow the user to access the system.</html>",
                "🔓 Confirm Activation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    ConnectDB db = new ConnectDB(); // Create an instance of dbConnector
                    Connection conn = db.getConnection(); // Get database connection

                    String query = "UPDATE users SET u_status = 'Active' WHERE user_id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setInt(1, selectedUserId);

                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>User account activated successfully!</b><br>The user can now access the system.</html>",
                            "✅ Activation Successful",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Activation failed.</b><br>No changes were made.</html>",
                            "❌ Activation Failed",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }

                    pstmt.close();
                    conn.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>Database error:</b><br>" + e.getMessage() + "</html>",
                        "❗ SQL Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please select a user to activate.</b></html>",
                "🔍 No User Selected",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_activatePanelMouseClicked

    private void activatePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activatePanelMouseEntered
        activatePanel.setBackground(hoverColor);
    }//GEN-LAST:event_activatePanelMouseEntered

    private void activatePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activatePanelMouseExited
        activatePanel.setBackground(navColor);
    }//GEN-LAST:event_activatePanelMouseExited

    private void addPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseClicked

        Admin_Add_User ad = new Admin_Add_User();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addPanelMouseClicked

    private void addPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseEntered
        addPanel.setBackground(hoverColor);
    }//GEN-LAST:event_addPanelMouseEntered

    private void addPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPanelMouseExited
        addPanel.setBackground(navColor);
    }//GEN-LAST:event_addPanelMouseExited

    private void updatePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePanelMouseClicked

        int selectedUserId = getSelectedUserId(); // Get selected user's ID

        if (selectedUserId != -1) {
            Admin_Update_User updateForm = new Admin_Update_User(selectedUserId);
            updateForm.setVisible(true);
            this.dispose(); // Optional: Close the current form if you want
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please select a user to edit.</b></html>",
                "🔍 No User Selected",
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
       int selectedUserId = getSelectedUserId(); // Get the selected user's ID

        if (selectedUserId != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "<html><b>Are you sure you want to archive this user?</b><br>This action will not delete any records.</html>",
                "⚠ Confirm Archive",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    ConnectDB db = new ConnectDB();
                    Connection conn = db.getConnection();

                    String query = "UPDATE users SET u_status = 'Archived' WHERE user_id = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setInt(1, selectedUserId);

                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>User archived successfully!</b><br>The account is now hidden from the active user list.</html>",
                            "✅ Archive Successful",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                            this,
                            "<html><b>Archive failed.</b><br>Please try again.</html>",
                            "❌ Archive Failed",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }

                    pstmt.close();
                    conn.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                        this,
                        "<html><b>Database error:</b><br>" + e.getMessage() + "</html>",
                        "❗ SQL Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "<html><b>Please select a user to archive.</b></html>",
                "🔍 No User Selected",
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
        loadUsers();
    }//GEN-LAST:event_refreshPanelMouseClicked

    private void refreshPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseEntered
        refreshPanel.setBackground(hoverColor);
    }//GEN-LAST:event_refreshPanelMouseEntered

    private void refreshPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshPanelMouseExited
        refreshPanel.setBackground(navColor);
    }//GEN-LAST:event_refreshPanelMouseExited

    private void searchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activate;
    private javax.swing.JPanel activatePanel;
    private javax.swing.JLabel add;
    private javax.swing.JPanel addPanel;
    private javax.swing.JPanel archivePanel;
    private javax.swing.JLabel delete;
    private javax.swing.JLabel edit;
    private javax.swing.JComboBox<String> filterUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel refresh;
    private javax.swing.JPanel refreshPanel;
    private javax.swing.JTextField searchUser;
    private javax.swing.JPanel updatePanel;
    private javax.swing.JLabel user;
    private javax.swing.JPanel user_header;
    private javax.swing.JTable users;
    // End of variables declaration//GEN-END:variables
}
