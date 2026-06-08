package com.insurance.service;

import com.insurance.dto.ClaimDTO;
import com.insurance.entity.Claim;
import com.insurance.entity.Policy;
import com.insurance.enums.ClaimStatus;
import com.insurance.exception.InvalidClaimStateException;
import com.insurance.exception.ResourceNotFoundException;
import com.insurance.repository.ClaimRepository;
import com.insurance.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public ClaimService(
            ClaimRepository claimRepository,
            PolicyRepository policyRepository) {

        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }

    public ClaimDTO createClaim(
            Long policyId,
            Claim claim) {

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Policy not found with id "
                                        + policyId));

        claim.setPolicy(policy);

        claim.setStatus(
                ClaimStatus.PENDING);

        Claim savedClaim =
                claimRepository.save(claim);

        return convertToDTO(savedClaim);
    }

    private ClaimDTO convertToDTO(
            Claim claim) {

        ClaimDTO dto = new ClaimDTO();

        dto.setClaimId(claim.getClaimId());
        dto.setClaimReason(claim.getClaimReason());
        dto.setClaimAmount(claim.getClaimAmount());
        dto.setStatus(claim.getStatus());

        return dto;
    }

    public List<ClaimDTO> getClaimsByPolicyId(
            Long policyId) {

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Policy not found with id "
                                        + policyId));

        return claimRepository
                .findByPolicyPolicyId(policyId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ClaimDTO approveClaim(
            Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Claim not found with id "
                                        + claimId));

        if (!claim.getStatus().equals(ClaimStatus.PENDING)) {

            throw new InvalidClaimStateException(
                    "Only pending claims can be approved");
        }

        claim.setStatus(
                ClaimStatus.APPROVED);

        Claim updatedClaim =
                claimRepository.save(claim);

        return convertToDTO(updatedClaim);
    }

    public ClaimDTO rejectClaim(
            Long claimId) throws RuntimeException {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Claim not found with id "
                                        + claimId));

        if (!claim.getStatus().equals(ClaimStatus.PENDING)) {

            throw new InvalidClaimStateException(
                    "Only pending claims can be rejected");
        }

        claim.setStatus(
                ClaimStatus.REJECTED);

        Claim updatedClaim =
                claimRepository.save(claim);

        return convertToDTO(updatedClaim);
    }
}