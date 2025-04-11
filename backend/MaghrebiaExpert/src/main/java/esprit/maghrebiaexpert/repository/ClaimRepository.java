package esprit.maghrebiaexpert.repository;

import esprit.maghrebiaexpert.entities.Claim;
import esprit.maghrebiaexpert.entities.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByStatus(ClaimStatus status);
}
