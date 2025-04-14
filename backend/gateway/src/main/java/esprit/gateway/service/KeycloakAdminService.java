package esprit.gateway.service;

import esprit.gateway.dto.UserDto;
import esprit.gateway.dto.UserInfoDto;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakAdminService {

    private final Keycloak keycloakAdminClient;

    @Value("${keycloak.admin.target-realm}")
    private String targetRealm;

    public boolean createUser(UserDto userDto) {
        log.info("Tentative de création d'utilisateur dans le realm '{}': {}", targetRealm, userDto.getEmail());
        try {
            UserRepresentation user = new UserRepresentation();
            user.setEnabled(true);
            user.setUsername(userDto.getEmail());
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmailVerified(true);

            RealmResource realmResource = keycloakAdminClient.realm(targetRealm);
            UsersResource usersResource = realmResource.users();

            Response response = usersResource.create(user);

            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                log.info("Utilisateur '{}' créé avec succès (Status={})", userDto.getEmail(), response.getStatus());
                String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
                log.debug("ID de l'utilisateur créé : {}", userId);

                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(userDto.getPassword());
                usersResource.get(userId).resetPassword(passwordCred);
                log.info("Mot de passe défini pour l'utilisateur '{}'", userDto.getEmail());

                if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
                    assignRolesToUser(realmResource, usersResource, userId, userDto.getRoles());
                }
                return true;
            } else {
                String errorMessage = response.readEntity(String.class);
                log.error("Échec de la création de l'utilisateur '{}'. Status: {}, Raison: {}",
                        userDto.getEmail(), response.getStatus(), errorMessage);
                return false;
            }
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'utilisateur '{}' dans Keycloak", userDto.getEmail(), e);
            return false;
        }
    }

    private void assignRolesToUser(RealmResource realmResource, UsersResource usersResource, String userId, List<String> roleNames) {
        List<RoleRepresentation> rolesToAssign = roleNames.stream()
                .map(roleName -> {
                    try {
                        return realmResource.roles().get(roleName).toRepresentation();
                    } catch (jakarta.ws.rs.NotFoundException e) {
                        log.warn("Le rôle '{}' n'a pas été trouvé dans le realm '{}'. Il ne sera pas assigné.", roleName, targetRealm);
                        return null;
                    }
                })
                .filter(java.util.Objects::nonNull)
                .toList();

        if (!rolesToAssign.isEmpty()) {
            log.info("Assignation des rôles {} à l'utilisateur ID {}", roleNames, userId);
            usersResource.get(userId).roles().realmLevel().add(rolesToAssign);
        } else {
            log.warn("Aucun rôle valide trouvé à assigner pour l'utilisateur ID {}", userId);
        }
    }

    public List<UserInfoDto> listUsers() {
        log.info("Récupération de la liste des utilisateurs du realm '{}'", targetRealm);
        try {
            RealmResource realmResource = keycloakAdminClient.realm(targetRealm);
            UsersResource usersResource = realmResource.users();

            List<UserRepresentation> userRepresentations = usersResource.list();

            return userRepresentations.stream()
                    .map(userRep -> new UserInfoDto(
                            userRep.getId(),
                            userRep.getUsername(),
                            userRep.getEmail(),
                            userRep.getFirstName(),
                            userRep.getLastName(),
                            userRep.isEnabled()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Erreur lors de la récupération de la liste des utilisateurs du realm '{}'", targetRealm, e);
            return Collections.emptyList();
        }
    }
}