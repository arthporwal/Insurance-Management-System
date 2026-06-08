package com.insurance.service;

import com.insurance.dto.PolicyDTO;
import com.insurance.entity.Customer;
import com.insurance.entity.Policy;
import com.insurance.exception.ResourceNotFoundException;
import com.insurance.repository.CustomerRepository;
import com.insurance.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    private final CustomerRepository customerRepository;
    private final PolicyRepository policyRepository;

    public PolicyService(
            CustomerRepository customerRepository,
            PolicyRepository policyRepository) {

        this.customerRepository = customerRepository;
        this.policyRepository = policyRepository;
    }

    public PolicyDTO createPolicy(
            Long customerId,
            Policy policy) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id "
                                        + customerId));

        policy.setCustomer(customer);

        Policy savedPolicy =
                policyRepository.save(policy);

        return convertPolicyToDTO(savedPolicy);
    }

    private PolicyDTO convertPolicyToDTO(Policy policy) {

        PolicyDTO dto = new PolicyDTO();

        dto.setPolicyId(policy.getPolicyId());
        dto.setPolicyName(policy.getPolicyName());
        dto.setPremiumAmount(policy.getPremiumAmount());

        return dto;
    }

    public List<PolicyDTO> getPoliciesByCustomerId(
            Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id "
                                        + customerId));

        return customer.getPolicies()
                .stream()
                .map(this::convertPolicyToDTO)
                .toList();
    }
}
