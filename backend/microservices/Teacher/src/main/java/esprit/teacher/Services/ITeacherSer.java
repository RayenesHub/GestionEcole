package esprit.teacher.Services;

import esprit.teacher.Entity.Teacher;

import java.util.List;

public interface ITeacherSer {
    Teacher create(Teacher teacher);
    List<Teacher> findAll();
    Teacher findById(Long id);
    Teacher update(Long id, String firstName, String lastName);
     void delete(Long id);
}
