package esprit.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private BadWordsService badWordsService;

    public List<Student> findAll() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            System.out.println("Aucun étudiant trouvé en base !");
        }
        return students;
    }

    public Student save(Student student) {
        if (badWordsService.containsBadWord(student.getFirstName()) ||
                badWordsService.containsBadWord(student.getLastName())) {
            throw new BadWordException("❌ Le nom ou le prénom contient un mot interdit !");
        }

        if (student.getClasse() != null && student.getClasse().getId() != null) {
            Optional<Classe> classeOpt = classeRepository.findById(student.getClasse().getId());
            classeOpt.ifPresent(student::setClasse);
        } else {
            student.setClasse(null);
        }

        return studentRepository.save(student);
    }

    public Student findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public String deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student with ID " + id + " has been deleted.";
        } else {
            return "Student not found.";
        }
    }
}
