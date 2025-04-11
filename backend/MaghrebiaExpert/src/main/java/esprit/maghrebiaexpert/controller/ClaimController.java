package esprit.maghrebiaexpert.controller;

import esprit.maghrebiaexpert.entities.Claim;
import esprit.maghrebiaexpert.entities.ClaimStatus;
import esprit.maghrebiaexpert.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim) {
        return ResponseEntity.ok(claimService.createClaim(claim));
    }

    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Claim>> getClaimsByStatus(@PathVariable ClaimStatus status) {
        return ResponseEntity.ok(claimService.getClaimsByStatus(status));
    }
}
