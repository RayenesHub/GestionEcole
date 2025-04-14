package esprit.event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin("*") // pour autoriser l’accès depuis un frontend (comme Angular)
public class EventController {

    private final EventService service;
    private final WeatherService weatherService;

    public EventController(EventService service, WeatherService weatherService) {
        this.service = service;
        this.weatherService = weatherService;
    }

    // Obtenir tous les événements
    @GetMapping("get")
    public List<Event> getAllEvents() {
        return service.getAll();
    }

    // Obtenir un événement par son ID
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
        return service.getById(id).orElse(null);
    }

    // Ajouter un événement avec retour de météo
    @PostMapping("add")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        if (service.isDateReserved(event.getDate())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("{\"message\": \"⚠️ Cette date est déjà réservée.\"}");
        }

        // Sauvegarder l'événement
        Event savedEvent = service.save(event);

        // Récupérer la météo de Tunis (par défaut)
        String weather = weatherService.getWeatherForDefaultCity();

        // Message de réponse formaté
        String responseMessage = String.format("Événement ajouté avec succès.\nMétéo pour Tunis à la date de l'événement : %s", weather);

        return ResponseEntity.ok().body(new ResponseMessage(responseMessage));
    }

    // Mettre à jour un événement
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable int id, @RequestBody Event event) {
        event.setId(id);
        return service.save(event);
    }

    // Supprimer un événement
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        service.deleteById(id);
    }

    // Vérifier la disponibilité d'une date
    @PostMapping("/check-date")
    public ResponseEntity<?> checkDate(@RequestBody Event event) {
        boolean reserved = service.isDateReserved(event.getDate());
        if (reserved) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("{\"message\": \"⚠️ Cette date est déjà réservée.\"}");
        }
        return ResponseEntity.ok("{\"message\": \"✅ Date disponible.\"}");
    }

    // Appeler la météo par ville (optionnel)
    @GetMapping("/weather/{city}")
    public String getWeather(@PathVariable String city) {
        return weatherService.getWeatherByCity(city);
    }

    // Obtenir la météo par ID d’événement (historique possible)
    @GetMapping("/weather-by-event/{id}")
    public ResponseEntity<?> getWeatherForEvent(@PathVariable int id) {
        Event event = service.getById(id).orElse(null);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Événement non trouvé.");
        }

        String weather = weatherService.getWeatherForDefaultCity();
        return ResponseEntity.ok("Météo pour l'événement : " + weather);
    }

    // Classe interne pour réponse JSON bien formatée
    public static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
