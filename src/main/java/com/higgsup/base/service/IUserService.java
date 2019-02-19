package com.higgsup.base.service;

import com.higgsup.base.dto.AddressDTO;
import com.higgsup.base.dto.DimensionDTO;
import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.entity.AddressBook;
import com.higgsup.base.entity.User;

import java.util.List;

public interface IUserService {
    User getByUsername(String username);

    List<User> getUser();

    ResponseMessage createUser(UserDTO userDTO);

    List<DimensionDTO> getTop5Dimension();

    ResponseMessage getAddressList(Long userId);

    AddressBook saveAddress(AddressDTO addressDTO, Long userId);

}
