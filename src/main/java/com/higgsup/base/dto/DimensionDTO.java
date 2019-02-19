package com.higgsup.base.dto;

import com.higgsup.base.dto.base.IPagedResponse;
import com.higgsup.base.entity.Dimention;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DimensionDTO extends IPagedResponse<List<Dimention>> {
  private Long id;
  private Long userId;
  private Double length;
  private Double weight;
  private Double height;
  private Byte dimentionDefault;
  private Timestamp lastUpdated;
}
