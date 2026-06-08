package com.insurance.dto;

import lombok.*;

@Getter
@Setter
public class PolicyDTO {
    private Long policyId;

    private String policyName;

    private Double premiumAmount;
}
