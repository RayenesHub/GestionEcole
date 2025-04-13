package esprit.teacher.RestController;

import esprit.teacher.Entity.Teacher;
import esprit.teacher.Services.TeacherSer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    TeacherSer teacherSer;

    @PostMapping
    public Teacher create(@RequestBody Teacher teacher) {
        return teacherSer.create(teacher);
    }
    @GetMapping
    public List<Teacher> getAll() {
        return teacherSer.findAll();
    }
    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Long id) {
        return teacherSer.findById(id);
    }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id,
                          @RequestParam String firstName,
                          @RequestParam String lastName) {
        return teacherSer.update(id, firstName, lastName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherSer.delete(id);
        return ResponseEntity.ok().build();
    }
}
