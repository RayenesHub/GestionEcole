package tn.esprit.equipement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return service.save(equipment);
    }

    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable Long id, @RequestBody Equipment updated) {
        Equipment existing = service.findById(id);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setQuantity(updated.getQuantity());
            existing.setCategory(updated.getCategory());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        service.delete(id);
    }
}
