package esprit.maghrebiaexpert.repository;

import esprit.maghrebiaexpert.entities.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {
}
