package com.example.school;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String directeur;
    private String telephone;
    private String email;

    // Champ de version pour l'optimistic locking
    @Version
    private Long version;

}
