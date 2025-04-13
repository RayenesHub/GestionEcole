package sae5.mailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

@Autowired
 MailRepo mailRepo;

    public Mail addMail(Mail mail) {
        return mailRepo.save(mail);
    }

    public void deleteMail(int id) {
        mailRepo.deleteById(id);
    }

    public List<Mail> getAllMails() {
        return mailRepo.findAll();
    }

    public Mail updateMail(Mail mail) {
        return mailRepo.save(mail);
    }

    public Mail getMailById(int id) {
        return mailRepo.findMailById(id);
    }
}
