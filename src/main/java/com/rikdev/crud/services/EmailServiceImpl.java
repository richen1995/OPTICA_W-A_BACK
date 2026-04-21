package com.rikdev.crud.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendResetPasswordEmail(String to, String token) {
        String resetUrl = "https://nissivision.netlify.app/#/reset-password?token=" + token;
        System.out.println("Enviando email a: " + to);
        System.out.println("Link de recuperación: " + resetUrl);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Recuperación de contraseña - OPTICA WP");
        message.setText("Hola. Ha solicitado restablecer su contraseña.\n\n" +
                "Haga clic en el siguiente enlace para continuar:\n" + resetUrl + "\n\n" +
                "Este enlace es válido por 24 horas.\n\n" +
                "Si no solicitó esto, puede ignorar este correo.");

        // Esta línea es la que hace el envío real
        mailSender.send(message);
    }
}
