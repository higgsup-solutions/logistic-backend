package com.higgsup.base.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FranchiseBaseRateDTO {
    private Long carrierId;
    private BigDecimal franchiseBaseRate1;
    private BigDecimal franchiseBaseRate2;
    private BigDecimal franchiseBaseRate3;
    private BigDecimal franchiseBaseRate4;
}
