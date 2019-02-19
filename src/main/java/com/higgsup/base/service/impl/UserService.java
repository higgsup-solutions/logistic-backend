package com.higgsup.base.service.impl;

import com.higgsup.base.common.ErrorCode;
import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.entity.*;
import com.higgsup.base.repository.AddressBookRepository;
import com.higgsup.base.repository.DimentionRepository;
import com.higgsup.base.repository.UserRepository;
import com.higgsup.base.repository.UserRoleRepository;
import com.higgsup.base.security.model.UserContext;
import com.higgsup.base.service.IUserRoleService;
import com.higgsup.base.service.IUserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;

    private final IUserRoleService userRoleService;

    private final DimentionRepository dimentionRepository;

    private final AddressBookRepository addressBookRepository;

    private final MapperFacade mapperFacade;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, IUserRoleService userRoleService, DimentionRepository dimentionRepository, AddressBookRepository addressBookRepository, MapperFacade mapperFacade) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userRoleService = userRoleService;
        this.dimentionRepository = dimentionRepository;
        this.addressBookRepository = addressBookRepository;
        this.mapperFacade = mapperFacade;
    }


    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException(String.valueOf(ErrorCode.DUPPLICATE_EMAIL.getErrorCode()));
        }

        if (userRepository.existsByUsername(userDTO.getUserName())) {
            throw new RuntimeException(String.valueOf(ErrorCode.DUPPLICATE_USERNAME.getErrorCode()));
        }

        User user = new User();
        user.setUsername(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRole(Role.MEMBER);
        userRole.setUserId(user.getId());
        userRoleService.create(userRole);

        return user;
    }

    @Override
    public List<DimensionDTO> getTop5Dimension() {
        List<DimensionDTO> dimensionDTOS = new ArrayList<>();
        UserContext userContext = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Dimention> dimentions = dimentionRepository.getTop5Dimention(userContext.getUserId());

        // convert from dimension entity to dimention DTO by bean copy
        for (Dimention dimention : dimentions) {
            DimensionDTO dimensionDTO = new DimensionDTO();
            if (dimention.getDimentionDefault() == 0) {
                dimensionDTO.setDimentionDefault(false);
            } else {
                dimensionDTO.setDimentionDefault(true);
            }
            BeanUtils.copyProperties(dimention, dimensionDTO);
            dimensionDTOS.add(dimensionDTO);

        }
        return dimensionDTOS;
    }

    @Override
    public List<AddressDTO> getAddressList(Long userId) {
        List<Object[]> addressList = userRepository.selectAddressList(userId);

        if (CollectionUtils.isEmpty(addressList)) {
            return null;
        }

        List<AddressDTO> addressDTOList = new ArrayList<>();

        for (Object[] data : addressList
        ) {
            AddressDTO addressDTO = new AddressDTO();
            if (data[0] != null) {
                addressDTO.setId(Long.valueOf(data[0].toString()));
            }

            if (data[1] != null) {
                addressDTO.setCountryId(Long.valueOf(data[1].toString()));
            }

            if (data[2] != null) {
                addressDTO.setUserType(String.valueOf(data[2]));
            }

            if (data[3] != null) {
                addressDTO.setCompany(String.valueOf(data[3]));
            }

            if (data[4] != null) {
                addressDTO.setContactName(String.valueOf(data[4]));
            }

            if (data[5] != null) {
                addressDTO.setSenderDefault(Boolean.valueOf(data[5].toString()));
            }

            if (data[6] != null) {
                addressDTO.setReceipientDefault(Boolean.valueOf(data[6].toString()));
            }

            if (data[7] != null) {
                addressDTO.setAddress1(String.valueOf(data[7]));
            }

            if (data[8] != null) {
                addressDTO.setAddress2(String.valueOf(data[8]));
            }

            if (data[9] != null) {
                addressDTO.setCityId(Long.valueOf(data[9].toString()));
            }

            if (data[10] != null) {
                addressDTO.setCityName(String.valueOf(data[10]));
            }

            if (data[11] != null) {
                addressDTO.setPostalCode(String.valueOf(data[11]));
            }

            if (data[12] != null) {
                addressDTO.setCountryName(String.valueOf(data[12]));
            }

            if (data[13] != null) {
                addressDTO.setStateProvince(String.valueOf(data[13]));
            }

            addressDTOList.add(addressDTO);

        }

        return addressDTOList;
    }

    @Override
    public AddressBook saveAddress(AddressDTO addressDTO, Long userId) {
        if(addressBookRepository.existsByCompanyAndContactNameAndUserId(addressDTO.getCompany(), addressDTO.getContactName(), userId)) {
            throw new RuntimeException(String.valueOf(ErrorCode.DUPPLICATE_ADDRESS.getErrorCode()));
        }
        AddressBook addressBook = mapperFacade.map(addressDTO, AddressBook.class);
        addressBook.setUserId(userId);
        addressBook.setId(null);
        addressBookRepository.save(addressBook);
        return addressBook;
    }
}
