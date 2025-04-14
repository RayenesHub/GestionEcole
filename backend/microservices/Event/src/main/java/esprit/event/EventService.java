package esprit.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableAsync
@Service
public class EventService {
    @Autowired

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
        return addedEvent;
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public boolean isDateReserved(Date date) {
        return repository.existsByDate(date);}


}