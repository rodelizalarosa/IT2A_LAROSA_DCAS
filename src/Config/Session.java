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
    private JDesktopPane parentDesktop;
    private JDesktopPane desktopSettings;
    
    private String verificationPin;

    // Patient session fields (for appointment pre-fill)
    private int patientId = -1;
    private String patientFirstName;
    private String patientLastName;
    private String patientGender;
    private String patientDob;
    private String patientContact;
    private String patientEmail;

    
    private String staffFirstName;
    private String staffLastName;
    
    // APPOINTMENT DETAILS FIELDS
    private int appointmentId = -1;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentNotes;
    private String appointmentStatus;

    private String dentistFullName; // e.g. Dr. Juan Dela Cruz
    private String serviceCategory;
    private int serviceId = -1;

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
        // Staff name is not automatically loaded here anymore
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
    
    public void setVerificationPin(String pin) {
        this.verificationPin = pin;
    }

    public String getVerificationPin() {
        return verificationPin;
    }

    public void setDesktopPane(JDesktopPane desktopPane) {
        this.mainDesktop = desktopPane;
    }

    public JDesktopPane getDesktopPane() {
        return mainDesktop;
    }
    
    public void setParentPane(JDesktopPane desktopPane) {
        this.parentDesktop = desktopPane;
    }

    public JDesktopPane getParentPane() {
        return parentDesktop;
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
    
    public String getPatientFullName() {
        if (patientFirstName != null && patientLastName != null) {
            return patientFirstName + " " + patientLastName;
        }
        return "";
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
    
    public void setPatientEmail(String email) {
        this.patientEmail = email;
    }

    public String getPatientEmail() {
        return patientEmail;
    }


    public void clearPatient() {
        this.patientId = -1;
        this.patientFirstName = null;
        this.patientLastName = null;
        this.patientGender = null;
        this.patientDob = null;
        this.patientContact = null;
        this.patientEmail = null;
    }
    
    // Setters
    public void setStaffName(String fname, String lname) {
        this.staffFirstName = fname;
        this.staffLastName = lname;
    }

    // Getters
    public String getStaffFirstName() {
        return staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    // APPOINTMENT SETTERS
    public void setAppointmentDetails(int appointmentId, String date, String time, String notes, String status) {
        this.appointmentId = appointmentId;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.appointmentNotes = notes;
        this.appointmentStatus = status;
    }
    
    public void setDentistFullName(String fullName) {
        this.dentistFullName = fullName;
    }

    public void setServiceDetails(int serviceId, String category) {
        this.serviceId = serviceId;
        this.serviceCategory = category;
    }

    // APPOINTMENT GETTERS
    public int getAppointmentId() {
        return appointmentId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentNotes() {
        return appointmentNotes;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getDentistFullName() {
        return dentistFullName;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public int getServiceId() {
        return serviceId;
    }

    // Clear appointment info if needed
    public void clearAppointmentDetails() {
        this.appointmentId = -1;
        this.appointmentDate = null;
        this.appointmentTime = null;
        this.appointmentNotes = null;
        this.appointmentStatus = null;
        this.dentistFullName = null;
        this.serviceCategory = null;
        this.serviceId = -1;
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
    
    private String serviceNameList;

    public void setServiceNameList(String serviceNames) {
        this.serviceNameList = serviceNames;
    }

    public String getServiceNameList() {
        return serviceNameList;
    }

}
