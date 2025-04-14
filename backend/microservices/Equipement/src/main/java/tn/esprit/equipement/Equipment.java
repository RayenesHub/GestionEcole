package tn.esprit.equipement;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String category;
    String etat;
    @JsonFormat(pattern = "yyyy-MM-dd")// exemple : disponible, en panne, en maintenance
    LocalDate dateAchat;        // format recommandé : yyyy-MM-dd
    String marque;
    @JsonFormat(pattern = "yyyy-MM-dd")// ex : Dell, HP, Epson...
    LocalDate dateMaintenance;// dernière date de maintenance
}
