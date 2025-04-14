package esprit.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClasseRestApi {

    @Autowired
    private ClasseService classeService;

    @GetMapping("/list")
    public List<Classe> getAllClasses() {
        return classeService.findAll();
    }

    @PostMapping("/add")
    public Classe createClasse(@RequestBody Classe classe) {
        return classeService.save(classe);
    }

    @GetMapping("/{id}")
    public Classe getClasseById(@PathVariable Long id) {
        return classeService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClasse(@PathVariable Long id) {
        return classeService.deleteById(id);
    }




}
