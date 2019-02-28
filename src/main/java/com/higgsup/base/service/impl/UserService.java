package com.higgsup.base.service.impl;

import com.higgsup.base.common.ErrorCode;
import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.entity.*;
import com.higgsup.base.model.UserAddress;
import com.higgsup.base.repository.*;
import com.higgsup.base.security.model.UserContext;
import com.higgsup.base.service.IUserRoleService;
import com.higgsup.base.service.IUserService;
import ma.glasnost.orika.MapperFacade;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@CacheConfig(cacheNames = ("loginCache"))
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;

    private final IUserRoleService userRoleService;

    private final DimentionRepository dimentionRepository;

    private final AddressBookRepository addressBookRepository;

    private final CountryRepository countryRepository;

    private final MapperFacade mapperFacade;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, IUserRoleService userRoleService, DimentionRepository dimentionRepository, AddressBookRepository addressBookRepository,  CountryRepository countryRepository, MapperFacade mapperFacade) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userRoleService = userRoleService;
        this.dimentionRepository = dimentionRepository;
        this.addressBookRepository = addressBookRepository;
        this.countryRepository = countryRepository;
        this.mapperFacade = mapperFacade;
    }


    @Override
    @Cacheable
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
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

        User user = new User();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
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
        List<UserAddress> addressList = userRepository.selectAddressList(userId);

        if (CollectionUtils.isEmpty(addressList)) {
            return null;
        }
        List<AddressDTO> addressDTOList = new ArrayList<>();
        for(UserAddress userAddress : addressList){
            AddressDTO addressDTO = new AddressDTO();
            BeanUtils.copyProperties(userAddress, addressDTO);
            addressDTO.setId(userAddress.getId().longValue());
            addressDTO.setCountryId(userAddress.getId().longValue());
            addressDTO.setId(userAddress.getId().longValue());
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

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO, Long userId, Long addressId) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(addressId);
        Optional<Country> countryOptional =   countryRepository.findById(addressDTO.getCountryId());
        if(!countryOptional.isPresent() || !addressBookOptional.isPresent()) {
            throw new RuntimeException(String.valueOf(ErrorCode.VALIDATION.getErrorCode()));
        } else {
            AddressBook addressBook = addressBookOptional.get();
            BeanUtils.copyProperties(addressDTO, addressBook, "id");
            addressBookRepository.save(addressBook);
            return addressDTO;
        }
    }

    @Override
    public void delete(Long userId, Long addressId){
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(addressId);
        if(!addressBookOptional.isPresent()) {
            throw new RuntimeException(String.valueOf(ErrorCode.VALIDATION.getErrorCode()));
        }else{
            addressBookRepository.delete(addressBookOptional.get());
        }
    }
    @Override
    @Cacheable
    public UserDTO findUser(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) {
            throw new RuntimeException(String.valueOf(ErrorCode.VALIDATION.getErrorCode()));
        }else{
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userOptional.get(), userDTO);
            return userDTO;
        }
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()) {
            throw new RuntimeException(String.valueOf(ErrorCode.VALIDATION.getErrorCode()));
        }else{
            User user = userOptional.get();
            BeanUtils.copyProperties(userDTO, user, "id", "email", "password");
            userRepository.save(user);
            return userDTO;
        }
    }
}
