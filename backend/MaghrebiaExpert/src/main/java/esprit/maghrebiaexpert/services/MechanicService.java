package esprit.maghrebiaexpert.service;

import esprit.maghrebiaexpert.entities.Mechanic;
import esprit.maghrebiaexpert.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicService {

    @Autowired
    private MechanicRepository mechanicRepository;

    public Mechanic createMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }
}
