package com.insurance.service;

import com.insurance.dto.CustomerDTO;
import com.insurance.dto.PolicyDTO;
import com.insurance.entity.Customer;
import com.insurance.entity.Policy;
import com.insurance.exception.ResourceNotFoundException;
import com.insurance.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
public List<CustomerDTO> getAllCustomers() {

    return customerRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .toList();
}

    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id "
                                        + id));
    }

    public CustomerDTO convertToDTO(Customer customer) {

        CustomerDTO dto = new CustomerDTO();

        dto.setCustomerId(customer.getCustomerId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        List<PolicyDTO> policyDTOs =
                customer.getPolicies()
                        .stream()
                        .map(this::convertPolicyToDTO)
                        .toList();

        dto.setPolicies(policyDTOs);

        return dto;
    }

    public Customer updateCustomer(Long id,
                                   Customer updatedCustomer) {

        Customer existingCustomer =
                customerRepository.findById(id)
                        .orElse(null);

        if (existingCustomer == null) {
            return null;
        }

        existingCustomer.setName(
                updatedCustomer.getName());

        existingCustomer.setEmail(
                updatedCustomer.getEmail());

        existingCustomer.setPhone(
                updatedCustomer.getPhone());

        return customerRepository.save(
                existingCustomer);
    }

    public String deleteCustomer(Long id) {

        if (!customerRepository.existsById(id)) {
            return "Customer Not Found";
        }

        customerRepository.deleteById(id);

        return "Customer Deleted Successfully";
    }

    public PolicyDTO convertPolicyToDTO(Policy policy) {

        PolicyDTO dto = new PolicyDTO();

        dto.setPolicyId(policy.getPolicyId());
        dto.setPolicyName(policy.getPolicyName());
        dto.setPremiumAmount(policy.getPremiumAmount());

        return dto;
    }

}
