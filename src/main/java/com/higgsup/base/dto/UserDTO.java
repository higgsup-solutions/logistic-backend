package com.higgsup.base.dto;

import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private String country;
  private String city;
  private String language;
  private String address;
}
