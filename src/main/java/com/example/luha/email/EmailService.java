package com.example.luha.email;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender{
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    @Override
    // research async
    @Async
    public void send(String toEmail, String emailBody) {
        try{
            // research mime message
            MimeMessage  mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            //email = email body
            helper.setText(emailBody,true);
            //to = recepient
            helper.setTo(toEmail);
            helper.setSubject("Confirm your email");
            helper.setFrom("luha@gmail.com");
            mailSender.send(mimeMessage);

        }catch(MessagingException e){
            LOGGER.error("error , failed to send email");
            throw  new IllegalStateException("failed to send email");}

    }
}
