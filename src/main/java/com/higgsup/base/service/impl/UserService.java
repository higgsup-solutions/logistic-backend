package com.higgsup.base.service.impl;

import com.higgsup.base.dto.UserDTO;
import com.higgsup.base.dto.base.ResponseMessage;
import com.higgsup.base.entity.ExceptionCode;
import com.higgsup.base.entity.User;
import com.higgsup.base.repository.UserRepository;
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


  public UserService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
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
      result.setMessageCode(ExceptionCode.DUPPLICATE_EMAIL);
      return result;
    }

    if(userRepository.existsByUsername(userDTO.getUserName())) {
      result.setData(false);
      result.setMessageCode(ExceptionCode.DUPPLICATE_USERNAME);
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

    result.setData(true);
    return result;
  }
}
