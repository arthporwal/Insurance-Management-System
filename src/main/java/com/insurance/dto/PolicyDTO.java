package com.insurance.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class PolicyDTO {
    private Long policyId;

    private String policyName;

    private Double premiumAmount;

    private List<ClaimDTO> claims;
}
