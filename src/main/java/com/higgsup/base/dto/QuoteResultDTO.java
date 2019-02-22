package com.higgsup.base.dto;

import lombok.Data;

@Data
public class QuoteResultDTO {
    private Double baseCharge;
    private Double fuelSurcharge;
    private Double dangerousCharge;
    private Double totalCharge;
    private Double totalWeight;
    private String weightType;
}
