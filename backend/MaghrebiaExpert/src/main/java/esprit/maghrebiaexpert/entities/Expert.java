package esprit.maghrebiaexpert.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialization;
    private int experienceYears;

    @OneToMany(mappedBy = "expert")
    private List<Claim> claims ;

    // Getters and Setters
}
