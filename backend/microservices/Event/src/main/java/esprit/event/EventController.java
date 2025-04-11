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

    @GetMapping("get")
    public List<Event> getAllEvents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping("add")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        if (service.isDateReserved(event.getDate())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("⚠️ Cette date est déjà réservée.");
        }
        Event saved = service.save(event);
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable int id, @RequestBody Event event) {
        event.setId(id);
        return service.save(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/check-date")
    public ResponseEntity<?> checkDate(@RequestBody Event event) {
        boolean reserved = service.isDateReserved(event.getDate());
        if (reserved) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("⚠️ Cette date est déjà réservée.");
        }
        return ResponseEntity.ok("✅ Date disponible.");
    }

    @GetMapping("/weather/{city}")
    public String getWeather(@PathVariable String city) {
        return weatherService.getWeatherByCity(city);
    }


}
