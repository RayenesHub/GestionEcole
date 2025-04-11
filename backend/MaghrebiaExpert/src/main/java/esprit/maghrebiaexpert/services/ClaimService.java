package esprit.maghrebiaexpert.service;

import esprit.maghrebiaexpert.entities.Claim;
import esprit.maghrebiaexpert.entities.ClaimStatus;
import esprit.maghrebiaexpert.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public List<Claim> getClaimsByStatus(ClaimStatus status) {
        return claimRepository.findByStatus(status);
    }
}
