package com.higgsup.base.dto;

import lombok.Data;

import java.util.List;

@Data
public class FranchiseDTO {
    FranchiseBasicInfoDTO franchiseBasicInfoDTO;
    List<FranchiseBaseRateDTO> franchiseBaseRateDTOList;
}
