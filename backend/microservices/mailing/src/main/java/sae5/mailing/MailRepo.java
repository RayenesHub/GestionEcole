package sae5.mailing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailRepo extends JpaRepository<Mail, Integer> {
    public Mail findMailById(int id);
    List<Mail> findBySubjectContainingIgnoreCase(String subject);
}
