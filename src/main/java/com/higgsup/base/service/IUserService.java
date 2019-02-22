package com.higgsup.base.service;

import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.entity.AddressBook;
import com.higgsup.base.entity.User;

import java.util.List;

public interface IUserService {
    User getByUsername(String username);

    List<User> getUser();

    User createUser(UserDTO userDTO);

    List<DimensionDTO> getDimensions (Long userId, Integer dimensionNumber);

    List<AddressDTO> getAddressList(Long userId);

    AddressBook saveAddress(AddressDTO addressDTO, Long userId);

}
