package esprit.student;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/student-card")
public class StudentCardController {

    @Autowired
    private StudentCardService studentCardService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> generateStudentCard(@PathVariable Long id) {
        byte[] pdf = studentCardService.generateCardForStudent(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "student_card.pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
