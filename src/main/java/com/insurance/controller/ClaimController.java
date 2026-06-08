package com.insurance.controller;

import com.insurance.dto.ClaimDTO;
import com.insurance.entity.Claim;
import com.insurance.service.ClaimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(
            ClaimService claimService) {

        this.claimService = claimService;
    }

    @PostMapping("/{policyId}/claims")
    public ClaimDTO createClaim(
            @PathVariable Long policyId,
            @RequestBody Claim claim) {

        return claimService.createClaim(
                policyId,
                claim);
    }

    @GetMapping("/{policyId}/claims")
    public List<ClaimDTO> getClaimsByPolicyId(
            @PathVariable Long policyId) {

        return claimService
                .getClaimsByPolicyId(policyId);
    }

    @PutMapping("/{claimId}/approve")
    public ClaimDTO approveClaim(
            @PathVariable Long claimId) {

        return claimService
                .approveClaim(claimId);
    }

    @PutMapping("/{claimId}/reject")
    public ClaimDTO rejectClaim(
            @PathVariable Long claimId) {

        return claimService
                .rejectClaim(claimId);
    }
}