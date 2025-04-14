package esprit.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"students"})
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String niveau;

    @OneToMany(mappedBy = "classe")
    private List<Student> students;

    public Classe() {}

    public Classe(String nom, String niveau) {
        this.nom = nom;
        this.niveau = niveau;
    }
}
