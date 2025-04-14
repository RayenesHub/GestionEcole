package tn.esprit.equipement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository repository;

    public List<Equipment> findAll() {
        return repository.findAll();
    }

    public Equipment findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Equipment save(Equipment equipment) {
        return repository.save(equipment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Equipment> findByEtat(String etat) {
        return repository.findByEtat(etat);
    }

    public List<Equipment> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    public Map<String, Long> getStatistiquesParEtat() {
        List<Object[]> results = repository.countEquipementsByEtat();
        Map<String, Long> stats = new HashMap<>();
        for (Object[] row : results) {
            stats.put((String) row[0], (Long) row[1]);
        }
        return stats;
    }
}
