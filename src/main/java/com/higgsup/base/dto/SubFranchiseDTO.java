package com.higgsup.base.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubFranchiseDTO {
  List<FranchiseBasicInfoDTO> franchiseBasicInfoDTOs;
  Integer totalSubFranchise;

}
