package com.rikdev.crud.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${app.frontend.url}")
    private String frontendUrl;

    @Value("${brevo.api.key}")
    private String apiKey;

    @Override
    public void sendResetPasswordEmail(String to, String token) {
        String resetUrl = frontendUrl + "#/reset-password?token=" + token;

        System.out.println("Enviando email a: " + to + " usando Brevo API");

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.brevo.com/v3/smtp/email";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);
        headers.set("accept", "application/json");

        // Construir JSON de Brevo
        Map<String, Object> body = new HashMap<>();
        
        Map<String, String> sender = new HashMap<>();
        sender.put("name", "Óptica Nissi Vision");
        sender.put("email", "ornissivision@gmail.com");
        body.put("sender", sender);

        Map<String, String> toRecipient = new HashMap<>();
        toRecipient.put("email", to);
        body.put("to", List.of(toRecipient));

        body.put("subject", "Recuperación de contraseña - Óptica Nissi Vision");
        
        String htmlContent = "<div style=\"font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 5px;\">" +
                "<h2 style=\"color: #2c3e50; text-align: center;\">Restablecimiento de Contraseña</h2>" +
                "<p style=\"color: #555;\">Hola,</p>" +
                "<p style=\"color: #555;\">Hemos recibido una solicitud para restablecer la contraseña de tu cuenta. Si fuiste tú, haz clic en el siguiente botón para continuar:</p>" +
                "<div style=\"text-align: center; margin: 30px 0;\">" +
                "<a href=\"" + resetUrl + "\" style=\"background-color: #0d6efd; color: white; padding: 12px 25px; text-decoration: none; border-radius: 4px; font-weight: bold; display: inline-block;\">Restablecer mi contraseña</a>" +
                "</div>" +
                "<p style=\"color: #555; font-size: 14px;\"><em>Este enlace es válido por 24 horas.</em></p>" +
                "<hr style=\"border: none; border-top: 1px solid #eee; margin: 20px 0;\">" +
                "<p style=\"color: #999; font-size: 12px; text-align: center;\">Si no solicitaste este cambio, puedes ignorar este correo. Tu contraseña seguirá siendo la misma.</p>" +
                "</div>";
                
        body.put("htmlContent", htmlContent);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(url, request, String.class);
            System.out.println("Email enviado exitosamente vía Brevo API");
        } catch (Exception e) {
            System.err.println("Error al enviar email con Brevo: " + e.getMessage());
            throw new RuntimeException("Error al enviar el correo de recuperación");
        }
    }
}
