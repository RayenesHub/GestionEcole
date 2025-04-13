package esprit.teacher.Services;

import esprit.teacher.Entity.Teacher;
import esprit.teacher.Repository.TeacherRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TeacherSer implements ITeacherSer {
    TeacherRepo teacherRepo;


    public Teacher create(Teacher teacher) {
        return teacherRepo.save(teacher);
    }


    public List<Teacher> findAll() {
        return teacherRepo.findAll();
    }


    public Teacher findById(Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public Teacher update(Long id, String firstName, String lastName) {
        return null;
    }


    public void delete(Long id) {
        Teacher teacher = findById(id);
        teacherRepo.delete(teacher);
    }
}
