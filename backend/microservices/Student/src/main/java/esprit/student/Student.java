package esprit.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] photo;

    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classe_id")
    @JsonIgnoreProperties({"students"})
    private Classe classe;

    public Student() {}

    public Student(String lastName, String firstName, String dateOfBirth, Classe classe) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.classe = classe;
    }
}
