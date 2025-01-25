package scm.SmartContactManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements email_service{

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("bhaveshjadhav9923@gmail.com");

        mailSender.send(message);
        System.out.println("Email sent successfully!");
    }


    @Override
    public void sendEmailwithHtml() {

    }

    @Override
    public void sendEmailwithAttachment() {

    }
}
