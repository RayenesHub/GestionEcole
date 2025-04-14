package esprit.student;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
=======
import jakarta.persistence.*;

>>>>>>> 0ea3751 (evennnt)
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD
    @Lob
    private byte[] photo;
=======
>>>>>>> 0ea3751 (evennnt)

    private String firstName;
    private String lastName;
    private String dateOfBirth;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classe_id")
    @JsonIgnoreProperties({"students"})
=======
    @ManyToOne
    @JoinColumn(name = "classe_id") // FK dans table Student
>>>>>>> 0ea3751 (evennnt)
    private Classe classe;

    public Student() {}

    public Student(String lastName, String firstName, String dateOfBirth, Classe classe) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.classe = classe;
    }
<<<<<<< HEAD
=======

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
>>>>>>> 0ea3751 (evennnt)
}
