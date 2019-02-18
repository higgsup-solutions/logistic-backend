package com.higgsup.base.service;

import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.entity.User;

import java.util.List;

public interface IUserService {
  User getByUsername(String username);

  List<User> getUser();

  ResponseMessage createUser(UserDTO userDTO);

  ResponseMessage getAddressList(Long userId);

}
