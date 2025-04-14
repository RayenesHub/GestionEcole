package tn.esprit.equipement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByEtat(String etat);
    List<Equipment> findByCategory(String category);
    @Query("SELECT e.etat, COUNT(e) FROM Equipment e GROUP BY e.etat")
    List<Object[]> countEquipementsByEtat();
}
