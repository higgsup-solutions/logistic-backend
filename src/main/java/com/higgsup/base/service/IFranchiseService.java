package com.higgsup.base.service;

import com.higgsup.base.dto.FranchiseBasicInfoDTO;
import com.higgsup.base.dto.FranchiseDTO;
import com.higgsup.base.dto.SubFranchiseDTO;

import java.util.List;

public interface IFranchiseService {
    FranchiseDTO createFranchise(Long id, FranchiseDTO franchiseDTO);
    SubFranchiseDTO getListInfoFranchise(Long currentFranchiseId);
}
