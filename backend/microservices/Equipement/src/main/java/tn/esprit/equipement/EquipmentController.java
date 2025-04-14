package tn.esprit.equipement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService service;

    @GetMapping
    public List<Equipment> getAllEquipments() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Equipment getEquipment(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        try {
            return service.save(equipment);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable Long id, @RequestBody Equipment updated) {
        Equipment existing = service.findById(id);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setCategory(updated.getCategory());
            existing.setEtat(updated.getEtat());
            existing.setDateAchat(updated.getDateAchat());
            existing.setMarque(updated.getMarque());
            existing.setDateMaintenance(updated.getDateMaintenance());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/etat/{etat}")
    public List<Equipment> getByEtat(@PathVariable String etat) {
        return service.findByEtat(etat);
    }

    @GetMapping("/category/{category}")
    public List<Equipment> getByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @GetMapping("/stats/etat")
    public Map<String, Long> getStatsParEtat() {
        return service.getStatistiquesParEtat();
    }
}
