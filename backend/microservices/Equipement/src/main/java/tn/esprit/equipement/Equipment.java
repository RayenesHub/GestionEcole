package tn.esprit.equipement;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String category;
    String etat;

    @JsonFormat(pattern = "yyyy-MM-dd") // exemple : 2024-04-10
    LocalDate dateAchat;

    String marque;

    @JsonFormat(pattern = "yyyy-MM-dd") // exemple : 2025-01-15
    LocalDate dateMaintenance;
}
