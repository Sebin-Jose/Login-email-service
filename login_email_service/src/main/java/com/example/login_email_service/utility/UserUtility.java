package com.example.login_email_service.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

@Component
public class UserUtility {
    public static void sendMail(String toEmail) {

        
        String host = "smtp.gmail.com";
        String username = "yourmail@gmail.com";
        //get the password for third party applicxation from your google mail settings.
        String password = "**** **** **** ****";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("yourmail@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            msg.setSubject("Login Notification");
            msg.setText("Your latest login was @ "+new Date());

            Transport.send(msg);

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
