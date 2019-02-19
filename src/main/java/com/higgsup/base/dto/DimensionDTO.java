package com.higgsup.base.dto;


import lombok.Data;

@Data
public class DimensionDTO {
  private Long id;
  private Double length;
  private Double weight;
  private Double height;
  private Boolean dimentionDefault;
}
