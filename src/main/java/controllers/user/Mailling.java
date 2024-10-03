package controllers.user;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Entities.User;
import services.imResetPassword;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class Mailling extends imResetPassword{
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    Properties props = System.getProperties();
    final String username = "siwarachour999@gmail.com";
    final String password = "orwnlwxvngkerhig";
    public void envoyer(String Toemail, String Subject , String Object) {
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try {
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(username,"EFlexBank"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Toemail, false));
            msg.setSubject(Subject);

            // Create HTML content with logo and bank name
            String htmlContent = "<html><body>" +
                    "<img src='https://asset.cloudinary.com/drdfmw55s/6881a904b333c93fde8c8f9ecb5e339c.jpg' alt='Eflex Bank Logo'>" +
                    "<h1>Eflex Bank</h1>" +
                    "<p>" + Object + "</p>" +
                    "</body></html>";


            // Set HTML content
            msg.setContent(htmlContent, "text/html");

            // Set the date
            msg.setSentDate(new Date());

            // Send the message
            Transport.send(msg);
            System.out.println("Message sent.");
        } catch (MessagingException e) {
            System.out.println("Erreur d'envoi, cause: " + e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}