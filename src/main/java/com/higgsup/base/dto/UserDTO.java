package com.higgsup.base.dto;

import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String userName;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private String country;
  private String city;
}
