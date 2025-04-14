// controller/AuthController.java
package esprit.gateway.controller;

import esprit.gateway.dto.LoginDto;
import esprit.gateway.dto.TokenDto;
import esprit.gateway.dto.UserDto;
import esprit.gateway.dto.UserInfoDto;
import esprit.gateway.service.KeycloakAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final KeycloakAdminService keycloakAdminService;
    private final WebClient.Builder webClientBuilder;

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.client-id}")
    private String clientId;
    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @PostMapping("/auth/register")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody UserDto userDto) {
        log.info("Requête d'inscription reçue pour l'email: {}", userDto.getEmail());
        return Mono.fromCallable(() -> keycloakAdminService.createUser(userDto))
                .map(success -> {
                    if (success) {
                        log.info("Inscription réussie pour l'email: {}", userDto.getEmail());
                        return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur créé avec succès.");
                    } else {
                        log.warn("Échec de l'inscription pour l'email: {}", userDto.getEmail());
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de la création de l'utilisateur.");
                    }
                })
                .onErrorResume(e -> {
                    log.error("Erreur inattendue lors de l'inscription pour {}: {}", userDto.getEmail(), e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Erreur interne lors de la création de l'utilisateur."));
                });
    }

    @PostMapping("/auth/login")
    public Mono<ResponseEntity<TokenDto>> login(@RequestBody LoginDto loginDto) {
        log.info("Tentative de login pour l'utilisateur: {}", loginDto.getEmail());

        WebClient webClient = webClientBuilder.baseUrl(keycloakServerUrl).build();
        String tokenEndpoint = String.format("/realms/%s/protocol/openid-connect/token", realm);

        return webClient.post()
                .uri(tokenEndpoint)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData("grant_type", "password")
                        .with("client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("username", loginDto.getEmail())
                        .with("password", loginDto.getPassword())
                        .with("scope", "openid profile email roles")
                )
                .retrieve()
                .bodyToMono(TokenDto.class)
                .map(tokenDto -> {
                    log.info("Login réussi pour {}", loginDto.getEmail());
                    return ResponseEntity.ok(tokenDto);
                })
                .onErrorResume(error -> {
                    log.error("Échec du login pour {}: {}", loginDto.getEmail(), error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
                });
    }

    @GetMapping("/admin/users")
    public Mono<ResponseEntity<List<UserInfoDto>>> listUsers() {
        log.info("Requête reçue pour lister les utilisateurs (admin)");

        return Mono.fromCallable(keycloakAdminService::listUsers)
                .map(userList -> ResponseEntity.ok(userList))
                .onErrorResume(e -> {
                    log.error("Erreur lors de la récupération de la liste des utilisateurs: {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}