package esprit.student;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClasseInspirationResponse {
    // Getters
    private String nom;
    private String niveau;
    private String quote;
    private String author;

    public ClasseInspirationResponse(String nom, String niveau, String quote, String author) {
        this.nom = nom;
        this.niveau = niveau;
        this.quote = quote;
        this.author = author;
    }


    public ClasseInspirationResponse() {
    }

}
