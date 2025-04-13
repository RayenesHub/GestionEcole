package esprit.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EmailService emailService;

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getAll() {
        return repository.findAll();
    }

    public Optional<Event> getById(int id) {
        return repository.findById(id);
    }

    public Event save(Event event) {
        Event addedEvent = repository.save(event);
        sendEmployeEmail(addedEvent, "Added Evebt", "Event details: " + addedEvent.toString());
        return addedEvent;
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public boolean isDateReserved(Date date) {
        return repository.existsByDate(date);}

    private void sendEmployeEmail(Event employe, String subject, String message) {
        String recipientEmail = "dina.bouchaddekh@gmail.com"; // Change this as needed
        emailService.sendEmail(recipientEmail, subject, message);
    }
}
