package com.tobeto.hotel_reservation_pair_6.core.utilities.configurations.email;

import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Data
@NoArgsConstructor
@Configuration
public class EmailConfiguration {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailConfiguration(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String fromEmail) {
        this.javaMailSender = javaMailSender;
        this.fromEmail = fromEmail;
    }

    public void sendEmail(String to, String subject, String body) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true for HTML content
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // true for HTML content
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
