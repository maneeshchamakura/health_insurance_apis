package com.stateinsurance.insurance.dto;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class SignupDto {
    @Value("${myauth0.client_id}")
    private String client_id;

    private String email;
    private String password;

    @Value("${myauth0.connection}")
    private String connection;
    
    private String username;
    private String given_name = "John";
    private String family_name = "Doe";
    private String name = "John Doe";
    private String nickname = "Johnny";
    private String picture = "http://example.org/jdoe.png";
    private UserMetadataDto user_metadata = new UserMetadataDto();
}

// {
// 	"client_id": "rYiSO3vVHMLDw8KLOZzc7vwtWlJEdON5",
//   "email": "sunny@gmail.com",
//   "password": "Testing@123",
//   "connection": "Username-Password-Authentication",
//   "username": "johndoe",
//   "given_name": "John",
//   "family_name": "Doe",
//   "name": "John Doe",
//   "nickname": "johnny",
//   "picture": "http://example.org/jdoe.png",
//   "user_metadata": { "plan": "silver", "team_id": "a111" }
// }
