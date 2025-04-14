package sae5.mailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mailing")
@CrossOrigin(origins = "http://localhost:4200")
public class MailRestAPI {
    @Autowired
    MailService mailService;
    @Autowired
    Pdf pdf;
    @Autowired
    private MailRepo mailRepo;

    @PostMapping("add_mail")
    public Mail addMail(@RequestBody Mail mail) {
        return mailService.addMail(mail);
    }

    @GetMapping("show_mails")
    public List<Mail> showMail() {
        List<Mail> mailList = mailService.getAllMails();
        return mailList;
    }

    @PutMapping("/mod_mail/{id}")
    public Mail modMail(@PathVariable int id, @RequestBody Mail mail) {
        mail.setId(id); // s’assurer que l’ID est bien défini
        return mailService.updateMail(mail);
    }

    @DeleteMapping("del_mail/{idMail}")
    public void delMail(@PathVariable int idMail) {
        mailService.deleteMail(idMail);
    }

    @GetMapping("show_mail/{idMail}")
    public Mail showMailById(@PathVariable int idMail) {
        return mailService.getMailById(idMail);
    }

    @GetMapping("/{id}/export-pdf")
    public ResponseEntity<byte[]> exportMailToPdf(@PathVariable int id) {
        Mail mail = mailRepo.findMailById(id);

        if (mail == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdfBytes = pdf.generateMailPdf(mail);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mail_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping("/search")
    public List<Mail> searchMails(@RequestParam String subject) {
        return mailService.searchMailsBySubject(subject);

    }



}
