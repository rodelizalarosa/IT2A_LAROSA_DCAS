
package AUTHENTICATION;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;


public class EmailSender {
        private static final String FROM_EMAIL = "dentalflow2025@gmail.com";  // replace with your clinic's email
        private static final String FROM_PASSWORD = "mpds vlzw cnqh dwco";    // use app password, not account password

        public static void sendActivationEmail(String toEmail, String username) throws MessagingException {
            if (toEmail == null || toEmail.trim().isEmpty()) {
                throw new MessagingException("Recipient email address is missing or null.");
            }

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your Dental Flow Account Has Been Activated");

            String htmlBody = "<html>" +
                "<body style='font-family:Arial, sans-serif;'>" +
                "<h2 style='color:#2E86C1;'>Dental Flow - Account Activated</h2>" +
                "<p>Dear <b>" + username + "</b>,</p>" +
                "<p>We're happy to inform you that your account has been <b>successfully activated</b>.</p>" +
                "<p>You can now log in and start using the Dental Flow system.</p>" +
                "<br><p>If you have any questions, feel free to contact us.</p>" +
                "<br><p>Sincerely,<br><b>Dental Flow Team</b></p>" +
                "</body>" +
                "</html>";

            message.setContent(htmlBody, "text/html");
            Transport.send(message);
        }
        
        public static void sendAppointmentApproval(String toEmail, String patientName, String appointmentDate, String appointmentTime) throws MessagingException {
            if (toEmail == null || toEmail.trim().isEmpty()) {
                throw new MessagingException("Recipient email address is missing or null.");
            }

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Appointment Confirmed - " + appointmentDate);

            String htmlBody = "<html>" +
                "<body style='font-family:Arial, sans-serif;'>" +
                "<h2 style='color:#28a745;'>Dental Flow - Appointment Confirmed</h2>" +
                "<p>Dear <b>" + patientName + "</b>,</p>" +
                "<p>We're pleased to inform you that your dental appointment on <b>" +
                appointmentDate + "</b> at <b>" + appointmentTime + "</b> has been <b>confirmed</b>.</p>" +
                "<p>Please arrive 10 minutes early and bring any necessary documents.</p>" +
                "<p>If you have questions or need to reschedule, don't hesitate to contact us.</p>" +
                "<br><p>Sincerely,<br><b>Dental Flow Team</b></p>" +
                "</body>" +
                "</html>";

            message.setContent(htmlBody, "text/html");
            Transport.send(message);
        }
        
        public static void sendDeclineReason(String toEmail, String patientName, String reason, String appointmentDate, String appointmentTime) throws MessagingException {
            if (toEmail == null || toEmail.trim().isEmpty()) {
                throw new MessagingException("Recipient email address is missing or null.");
            }
            
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Appointment Declined - " + appointmentDate);

            String htmlBody = "<html>" +
                "<body style='font-family:Arial, sans-serif;'>" +
                "<h2 style='color:#FF0000;'>Dental Flow - Appointment Declined</h2>" +
                "<p>Dear <b>" + patientName + "</b>,</p>" +
                "<p>We hope you're doing well.</p>" +
                "<p>We regret to inform you that your dental appointment on <b>" +
                appointmentDate + "</b> at <b>" + appointmentTime + "</b> has been declined.</p>" +
                "<p><b>Reason for Decline:</b><br>" + reason + "</p>" +
                "<p>If you’d like to reschedule or have any questions, please contact us at your earliest convenience.</p>" +
                "<p>We apologize for the inconvenience and appreciate your understanding.</p>" +
                "<br><p>Sincerely,<br><b>Dental Flow Team</b></p>" +
                "</body>" +
                "</html>";

            message.setContent(htmlBody, "text/html");
            Transport.send(message);
        }
        
        public static String sendVerificationPin(String toEmail, String userName) throws MessagingException {
            if (toEmail == null || toEmail.trim().isEmpty()) {
                throw new MessagingException("Recipient email address is missing or null.");
            }

            // Generate 6-digit random number
            Random random = new Random();
            int pin = 100000 + random.nextInt(900000);
            String pinString = String.valueOf(pin);

            // Set email properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Password Reset Verification PIN");

            String htmlBody = "<html>" +
                "<body style='font-family:Arial, sans-serif;'>" +
                "<h2 style='color:#007BFF;'>Dental Flow - Password Reset Verification</h2>" +
                "<p>Dear <b>" + userName + "</b>,</p>" +
                "<p>You have requested to change your password. Please use the PIN below to verify your identity:</p>" +
                "<h3 style='color:#28A745;'>" + pinString + "</h3>" +
                "<p>This PIN is valid for a limited time. Do not share it with anyone.</p>" +
                "<br><p>Sincerely,<br><b>Dental Flow Team</b></p>" +
                "</body>" +
                "</html>";

            message.setContent(htmlBody, "text/html");
            Transport.send(message);

            return pinString; // return the PIN so you can validate it later
        }
}
