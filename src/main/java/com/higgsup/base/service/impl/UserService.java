package com.higgsup.base.service.impl;

import com.higgsup.base.common.ErrorCode;
import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.entity.Role;
import com.higgsup.base.entity.User;
import com.higgsup.base.entity.UserRole;
import com.higgsup.base.repository.UserRepository;
import com.higgsup.base.repository.UserRoleRepository;
import com.higgsup.base.service.IUserRoleService;
import com.higgsup.base.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final UserRoleRepository userRoleRepository;

  private final IUserRoleService userRoleService;


  public UserService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, IUserRoleService userRoleService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userRoleRepository = userRoleRepository;
    this.userRoleService = userRoleService;
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
  @Transactional(rollbackFor = Exception.class)
  public ResponseMessage createUser(UserDTO userDTO) {
    ResponseMessage result = new ResponseMessage();

    if(userRepository.existsByEmail(userDTO.getEmail())) {
      result.setData(false);
      result.setMessageCode(String.valueOf(ErrorCode.DUPPLICATE_EMAIL.getErrorCode()));
      return result;
    }

    if(userRepository.existsByUsername(userDTO.getUserName())) {
      result.setData(false);
      result.setMessageCode(String.valueOf(ErrorCode.DUPPLICATE_USERNAME.getErrorCode()));
      return result;
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

    result.setData(true);
    return result;
  }
}
