package esprit.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;


    public List<Classe> findAll() {
        List<Classe> classes = classeRepository.findAll();
        if (classes.isEmpty()) {
            System.out.println("Aucune classe trouvée !");
        }
        return classes;
    }

    public Classe save(Classe classe) {
        return classeRepository.save(classe);
    }

    public Classe findById(Long id) {
        Optional<Classe> optionalClasse = classeRepository.findById(id);
        return optionalClasse.orElse(null);
    }

    public String deleteById(Long id) {
        if (classeRepository.existsById(id)) {
            classeRepository.deleteById(id);
            return "Classe with ID " + id + " has been deleted.";
        } else {
            return "Classe not found.";
        }
    }
}
