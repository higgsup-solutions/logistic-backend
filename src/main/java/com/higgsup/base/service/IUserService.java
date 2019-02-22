package com.higgsup.base.service;

import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.entity.AddressBook;
import com.higgsup.base.entity.User;

import java.util.List;

public interface IUserService {
    User getByEmail(String email);

    List<User> getUser();

    User createUser(UserDTO userDTO);

    List<DimensionDTO> getDimensions (Long userId, Integer dimensionNumber);

    List<AddressDTO> getAddressList(Long userId);

    AddressBook saveAddress(AddressDTO addressDTO, Long userId);

    AddressDTO updateAddress(AddressDTO addressDTO, Long userId, Long addressId);

    void delete(Long userId, Long addressId);

    UserDTO findUser(Long userId);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    boolean changePassword(Long userId, String oldPassword, String newPassword, String confirmPassword);

    DimensionDTO saveDimension(DimensionDTO dimensionDTO, Long userId);

    DimensionDTO updateDimension(DimensionDTO dimensionDTO);

    void deleteDimension(Long dimensionId);
}
