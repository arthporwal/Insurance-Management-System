package com.insurance.dto;

import com.insurance.enums.ClaimStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimDTO {

    private Long claimId;

    private String claimReason;

    private Double claimAmount;

    private ClaimStatus status;
}