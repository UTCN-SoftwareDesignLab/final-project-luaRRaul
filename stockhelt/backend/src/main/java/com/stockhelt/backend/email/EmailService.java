package com.stockhelt.backend.email;

import com.stockhelt.backend.user.UserRepository;
import com.stockhelt.backend.user.dto.UserMinimalDTO;
import com.stockhelt.backend.user.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final UserRepository userRepository;

    public void sendmail(Long id) throws javax.mail.MessagingException, MessagingException, IOException {
        User user = userRepository.findById(id).get();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kikititibanbus@gmail.com", "asdplmki2#");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("kikititibanbus@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
        msg.setSubject("Welcome to our website!");
        msg.setContent("Well well well ...", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Hello\n\nHappy to see you joining our platform!", "text/html");


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();

//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
