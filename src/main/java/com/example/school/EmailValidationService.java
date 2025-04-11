package com.example.school;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailValidationService {

    @Value("${emaillistverify.api.key}")
    private String apiKey;

    public String verifyEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://apps.emaillistverify.com/api/verifyEmail?secret=" + apiKey + "&email=" + email;

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Erreur lors de la vérification de l'email : " + e.getMessage();
        }
    }
}
