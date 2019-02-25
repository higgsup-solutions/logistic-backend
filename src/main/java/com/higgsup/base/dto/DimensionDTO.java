package com.higgsup.base.dto;


import lombok.Data;

@Data
public class DimensionDTO {
  private Long id;
  private Double length;
  private String name;
  private Double width;
  private Double height;
  private Double weights;
  private Integer quantity;
  private Double cubicWeight = 0d;
}
