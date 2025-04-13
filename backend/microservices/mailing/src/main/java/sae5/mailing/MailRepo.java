package sae5.mailing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepo extends JpaRepository<Mail, Integer> {
    public Mail findMailById(int id);
}
