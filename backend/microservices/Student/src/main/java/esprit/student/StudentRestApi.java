package esprit.student;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestApi {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> getAllStudents() {
        List<Student> students = studentService.findAll();
        System.out.println("Étudiants trouvés : " + students.size()); // Debug
        return students;
    }


    // Endpoint pour ajouter un nouvel étudiant
    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    // Endpoint pour récupérer un étudiant par ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    // Endpoint pour supprimer un étudiant par ID
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteById(id);
    }
}
