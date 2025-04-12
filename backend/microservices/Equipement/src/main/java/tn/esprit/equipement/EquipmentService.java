package tn.esprit.equipement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

