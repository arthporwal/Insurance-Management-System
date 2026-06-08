package com.insurance.controller;

import com.insurance.dto.PolicyDTO;
import com.insurance.entity.Policy;
import com.insurance.service.PolicyService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(
            PolicyService policyService) {

        this.policyService = policyService;
    }

    @PostMapping("/{customerId}/policies")
    public PolicyDTO createPolicy(
            @PathVariable Long customerId,
            @RequestBody Policy policy) {

        return policyService.createPolicy(
                customerId,
                policy);
    }

    @GetMapping("/{customerId}/policies")
    public List<PolicyDTO> getPoliciesByCustomerId(
            @PathVariable Long customerId) {

        return policyService
                .getPoliciesByCustomerId(customerId);
    }
}
