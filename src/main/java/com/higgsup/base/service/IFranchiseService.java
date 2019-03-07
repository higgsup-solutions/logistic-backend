package com.higgsup.base.service;

import com.higgsup.base.dto.FranchiseDTO;
import com.higgsup.base.dto.FranchiseUserDTO;
import com.higgsup.base.dto.SubFranchiseDTO;
import com.higgsup.base.entity.User;

import java.util.List;

public interface IFranchiseService {
    FranchiseDTO createFranchise(Long id, FranchiseDTO franchiseDTO);
    SubFranchiseDTO getListInfoFranchise(Long currentFranchiseId);
    List<FranchiseUserDTO> getListUsers(Long currentFranchiseId);
}
