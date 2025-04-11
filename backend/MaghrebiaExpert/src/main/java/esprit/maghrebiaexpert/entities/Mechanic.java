package esprit.maghrebiaexpert.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String garageName;
    private String location;

    @OneToMany(mappedBy = "mechanic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Claim> claims;

    // Getters and Setters
}
