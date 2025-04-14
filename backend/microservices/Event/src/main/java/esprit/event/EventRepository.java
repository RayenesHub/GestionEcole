package esprit.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EventRepository extends JpaRepository<Event, Integer> {

    boolean existsByDate(Date date);



}
