package esprit.event;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

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
        return repository.save(event);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public boolean isDateReserved(Date date) {
        return repository.existsByDate(date);
    }
}
