package esprit.student;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"students"})
=======
import jakarta.persistence.*;
import lombok.Getter;


@Entity
>>>>>>> 0ea3751 (evennnt)
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    private String nom;
    private String niveau;

    @OneToMany(mappedBy = "classe")
    private List<Student> students;

=======
    @Getter
    private String nom;
    @Getter
    private String niveau;

>>>>>>> 0ea3751 (evennnt)
    public Classe() {}

    public Classe(String nom, String niveau) {
        this.nom = nom;
        this.niveau = niveau;
    }
<<<<<<< HEAD
=======




    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

>>>>>>> 0ea3751 (evennnt)
}
