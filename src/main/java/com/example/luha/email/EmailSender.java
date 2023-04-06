package com.example.luha.email;

import org.springframework.stereotype.Service;


public interface EmailSender {
    void send (String toEmail, String emailBody);
}
