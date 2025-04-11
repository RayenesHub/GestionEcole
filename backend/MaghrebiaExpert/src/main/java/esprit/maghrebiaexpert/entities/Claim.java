package esprit.maghrebiaexpert.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate claimDate;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    // Getters and Setters
}