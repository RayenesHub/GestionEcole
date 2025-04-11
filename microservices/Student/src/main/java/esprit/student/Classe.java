package esprit.student;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String nom;
    @Getter
    private String niveau;

    public Classe() {}

    public Classe(String nom, String niveau) {
        this.nom = nom;
        this.niveau = niveau;
    }




    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

}
