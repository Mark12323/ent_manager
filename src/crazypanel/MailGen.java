
package crazypanel;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class MailGen implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String from = "markintospave@gmail.com";
        // Receiver's email address
        String to = "jezicob@yahoo.com";
        // Sender's Gmail password
        String password = "etbkgedaahqiigzt";

        // SMTP server properties
        Properties props = new Properties();
        //props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.host", "smtp.gmail.com");
        //props.put("mail.smtp.port", "587");
        //props.put("mail.smtp.ssl.trust", "*");

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
        // Get Session object
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session.getProperties().put("mail.smtp.starttls.enable", "true");

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("no reply:Test Email from SalesVault Pro");

            
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hello, This is an automated test email sent from the Local host server. Automated with Java Mail API");
            //message.setText("Hello, This is an automated test email sent from the Local host server. Automated with Java Mail API");
            multipart.addBodyPart(textPart);
            MimeBodyPart pdfPart = new MimeBodyPart();
            pdfPart.attachFile("\\Users\\USER\\Documents\\NetBeansProjects\\VaultProII\\invoice.pdf");
            multipart.addBodyPart(pdfPart);
            message.setContent(multipart);
            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }
    }
}
