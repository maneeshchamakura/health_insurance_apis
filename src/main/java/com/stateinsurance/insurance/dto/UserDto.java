package com.stateinsurance.insurance.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {

  @NotBlank(message = "username is required")
  private String username;

  @NotBlank(message = "password is required")
  private String password;

  @NotBlank(message = "email is required")
  @Email(message = "Invalid email format")
  private String email;

}
