package com.higgsup.base.dto;

import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String password;
  private String email;
}
