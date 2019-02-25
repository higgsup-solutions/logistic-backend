package com.higgsup.base.dto;

import lombok.Data;

@Data
public class TransactionDimensionDTO {
    private Long id;
    private Long transactionId;
    private Double length;
    private String name;
    private Double width;
    private Double height;
    private Double weights;
    private Integer quantity;
}
