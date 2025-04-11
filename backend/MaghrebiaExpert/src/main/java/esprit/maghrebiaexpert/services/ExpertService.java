package esprit.maghrebiaexpert.service;

import esprit.maghrebiaexpert.entities.Expert;
import esprit.maghrebiaexpert.repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertService {

    @Autowired
    private ExpertRepository expertRepository;

    public Expert createExpert(Expert expert) {
        return expertRepository.save(expert);
    }

    public List<Expert> getAllExperts() {
        return expertRepository.findAll();
    }
}
