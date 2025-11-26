package utilities;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailUtil {

    public static void sendReport(String reportPath) {
        try {
            // Create attachment
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(reportPath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Automation Test Report");
            attachment.setName("ExtentReport.html");

            // Email setup
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("sandbox.smtp.mailtrap.io");
            email.setSmtpPort(587);
            email.setStartTLSRequired(true);
            email.setAuthentication("df9eda1d3a42c5", "4a3b69f254cb93");

            email.setFrom("vswami7829@gmail.com");
            email.setSubject("Automation Execution Report");
            email.setMsg("Hello,\n\nPlease find the attached automation test report.\n\nRegards");
            email.addTo("receiver@gmail.com");

            // Add attachment
            email.attach(attachment);

            // Send email
            email.send();
            System.out.println("Report emailed successfully!");

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
