package com.higgsup.base.service;

import com.higgsup.base.dto.FranchiseDTO;

public interface IFranchiseService {
    FranchiseDTO createFranchise(Long id, FranchiseDTO franchiseDTO);
}
