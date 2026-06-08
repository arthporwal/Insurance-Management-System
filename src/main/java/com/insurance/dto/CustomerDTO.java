package com.insurance.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CustomerDTO {

    private Long customerId;
    private String name;
    private String email;
    private List<PolicyDTO> policies;

}