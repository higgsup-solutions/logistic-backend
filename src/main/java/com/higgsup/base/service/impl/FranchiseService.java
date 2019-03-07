package com.higgsup.base.service.impl;

import com.higgsup.base.common.ErrorCode;
import com.higgsup.base.dto.*;
import com.higgsup.base.entity.Franchise;
import com.higgsup.base.entity.FranchiseBaseRate;
import com.higgsup.base.entity.User;
import com.higgsup.base.exception.BusinessException;
import com.higgsup.base.repository.FranchiseBaseRateRepository;
import com.higgsup.base.repository.FranchiseRepository;
import com.higgsup.base.repository.UserRepository;
import com.higgsup.base.repository.UserRoleRepository;
import com.higgsup.base.service.IFranchiseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FranchiseService implements IFranchiseService {
    private final FranchiseRepository franchiseRepository;
    private final FranchiseBaseRateRepository franchiseBaseRateRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public FranchiseService(FranchiseRepository franchiseRepository,
                            FranchiseBaseRateRepository franchiseBaseRateRepository,
                            UserRepository userRepository,
                            UserRoleRepository userRoleRepository) {
        this.franchiseRepository = franchiseRepository;
        this.franchiseBaseRateRepository = franchiseBaseRateRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public FranchiseDTO createFranchise(Long id, FranchiseDTO franchiseDTO) {
        FranchiseDTO result = new FranchiseDTO();
        FranchiseBasicInfoDTO franchiseBasicInfoDTO = franchiseDTO.getFranchiseBasicInfoDTO();

        //validate if franchise is existed.
        if (franchiseRepository.emailList().contains(franchiseBasicInfoDTO.getEmail())){
            throw new BusinessException(ErrorCode.DUPPLICATE_EMAIL, String.valueOf(ErrorCode.DUPPLICATE_EMAIL.getErrorCode()));
        }else {
            // Save franchise info
            Franchise franchise = new Franchise();

            //set relationship for new franchise follow relationship of current user id
            String relationship;
            if (franchiseRepository.findRelationshipById(id) != null){
                relationship = franchiseRepository.findRelationshipById(id).concat("-"+id);
            } else {
                relationship = id.toString();
            }
            BeanUtils.copyProperties(franchiseBasicInfoDTO,franchise);
            franchise.setRelationship(relationship);
            franchise.setStartedDate(new Timestamp(System.currentTimeMillis()));
            franchiseRepository.save(franchise);

            result.setFranchiseBasicInfoDTO(franchiseBasicInfoDTO);

            // Save franchise base rate info
            List<FranchiseBaseRateDTO> franchiseBaseRateDTOs = new ArrayList<>();
            for (FranchiseBaseRateDTO franchiseBaseRateDTO : franchiseDTO.getFranchiseBaseRateDTOList()) {
                FranchiseBaseRate franchiseBaseRate = new FranchiseBaseRate();
                BeanUtils.copyProperties(franchiseBaseRateDTO,franchiseBaseRate);
                franchiseBaseRate.setFranchiseId(franchise.getId());
                franchiseBaseRateRepository.save(franchiseBaseRate);

                franchiseBaseRateDTOs.add(franchiseBaseRateDTO);
            }
            result.setFranchiseBaseRateDTOList(franchiseBaseRateDTOs);

            return result;
        }
    }

    @Override
    public SubFranchiseDTO getListInfoFranchise(Long currentFranchiseId) {
        SubFranchiseDTO subFranchiseDTO = new SubFranchiseDTO();
        List<FranchiseBasicInfoDTO> franchiseBasicInfoDTOList = new ArrayList<>();
        List<Franchise> franchiseList = franchiseRepository.getAllSubFranchiseInfo(currentFranchiseId);

        for (Franchise franchise : franchiseList){
            FranchiseBasicInfoDTO franchiseBasicInfoDTO = new FranchiseBasicInfoDTO();
            BeanUtils.copyProperties(franchise,franchiseBasicInfoDTO);
            franchiseBasicInfoDTOList.add(franchiseBasicInfoDTO);
        }
        subFranchiseDTO.setFranchiseBasicInfoDTOs(franchiseBasicInfoDTOList);
        subFranchiseDTO.setTotalSubFranchise(franchiseBasicInfoDTOList.size());
        return subFranchiseDTO;
    }

    @Override
    public List<FranchiseUserDTO> getListUsers(Long currentFranchiseId) {
        List<FranchiseUserDTO> franchiseUserDTOs = new ArrayList<>();
        List<User> users = userRepository.listFranchiseUser(currentFranchiseId);
        for (User user : users) {
            FranchiseUserDTO franchiseUserDTO = new FranchiseUserDTO();
            BeanUtils.copyProperties(user,franchiseUserDTO);
            Long userId = franchiseUserDTO.getId();
            franchiseUserDTO.setStartDate(franchiseRepository.getStartDateOfFranchise(currentFranchiseId, userId));
            franchiseUserDTO.setRole(userRoleRepository.getUserRoleOfFranchise(currentFranchiseId, userId));

            franchiseUserDTOs.add(franchiseUserDTO);
        }
        return franchiseUserDTOs;
    }
}
