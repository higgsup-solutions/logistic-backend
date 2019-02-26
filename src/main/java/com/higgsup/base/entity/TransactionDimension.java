package com.higgsup.base.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class TransactionDimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long transactionId;
    private Double length;
    private String name;
    private Double width;
    private Double height;
    private Double weights;
    private Integer quantity;
    private Double cubicWeight;
}