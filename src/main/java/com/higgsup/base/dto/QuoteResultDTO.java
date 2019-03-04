package com.higgsup.base.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuoteResultDTO {
    private BigDecimal baseCharge;
    private BigDecimal fuelSurcharge;
    private BigDecimal dangerousCharge;
    private BigDecimal totalCharge;
    private Double totalWeight;
    private String weightType = "";
    private List<DimensionDTO> dimensions = new ArrayList<>();
}
