package Config;

import javax.swing.JDesktopPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Session {
    private static Session instance;

    // User session fields
    private int userId = 0;
    private String username;
    private String email;
    private JDesktopPane mainDesktop;
    private JDesktopPane desktopSettings;

    // Patient session fields (for appointment pre-fill)
    private int patientId = -1;
    private String patientFirstName;
    private String patientLastName;
    private String patientGender;
    private String patientDob;
    private String patientContact;

    private Session() {}

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // User setters and getters
    public void setUser(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setDesktopPane(JDesktopPane desktopPane) {
        this.mainDesktop = desktopPane;
    }

    public JDesktopPane getDesktopPane() {
        return mainDesktop;
    }

    public void setDesktopSettings(JDesktopPane desktopPane) {
        this.desktopSettings = desktopPane;
    }

    public JDesktopPane getDesktopSettings() {
        return desktopSettings;
    }

    // Patient setters and getters
    public void setPatient(int id, String fname, String lname, String gender, String dob, String contact) {
        this.patientId = id;
        this.patientFirstName = fname;
        this.patientLastName = lname;
        this.patientGender = gender;
        this.patientDob = dob;
        this.patientContact = contact;
    }

    public int getPatientId() {
        return patientId;
    }
    
    // Set the patientId
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    
    // Clear the patientId
    public void clearPatientId() {
        this.patientId = -1;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public String getPatientDob() {
        return patientDob;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void clearPatient() {
        this.patientId = -1;
        this.patientFirstName = null;
        this.patientLastName = null;
        this.patientGender = null;
        this.patientDob = null;
        this.patientContact = null;
    }

    public void logEvent(String event, String description) {
        if (userId == 0) {
            System.out.println("⚠ No user logged in. Skipping log event.");
            return;
        }

        String sql = "INSERT INTO logs (user_id, log_event, log_description) VALUES (?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, event);
            pstmt.setString(3, description);
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.err.println("❌ Error logging event: " + e.getMessage());
        }
    }
}
