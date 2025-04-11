package esprit.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import esprit.student.QuoteResponse;


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

    @GetMapping("/{id}/inspiration")
    public ResponseEntity<?> getClasseInspiration(@PathVariable Long id) {
        Classe classe = classeService.findById(id);
        if (classe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classe not found");
        }

        // Liste simulée de citations
        List<QuoteResponse> quotes = List.of(
                new QuoteResponse("Le savoir est une arme. Apprends bien.", "Malcolm X"),
                new QuoteResponse("L’éducation est l’arme la plus puissante pour changer le monde.", "Nelson Mandela"),
                new QuoteResponse("Le succès, c’est tomber sept fois, se relever huit.", "Proverbe japonais"),
                new QuoteResponse("La persévérance est souvent la clé du succès.", "Albert Einstein"),
                new QuoteResponse("Apprends comme si tu devais vivre pour toujours.", "Gandhi")
        );

        // Choisir une citation selon l'ID (modulo)
        QuoteResponse quote = quotes.get((int)(id % quotes.size()));

        ClasseInspirationResponse response = new ClasseInspirationResponse(
                classe.getNom(),
                classe.getNiveau(),
                quote.getContent(),
                quote.getAuthor()
        );

        return ResponseEntity.ok(response);
    }



    // RestTemplate restTemplate = new RestTemplate();
        // String quoteApiUrl = "https://api.quotable.io/random";
        // QuoteResponse quote = restTemplate.getForObject(quoteApiUrl, QuoteResponse.class);



}
