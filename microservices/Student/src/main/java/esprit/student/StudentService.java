package esprit.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            System.out.println("Aucun étudiant trouvé en base !");
        }
        return students;
    }


    // Ajouter un étudiant
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    // Trouver un étudiant par ID
    public Student findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    // Supprimer un étudiant par ID
    public String deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student with ID " + id + " has been deleted.";
        } else {
            return "Student not found.";
        }
    }
}

