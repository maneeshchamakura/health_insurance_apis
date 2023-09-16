package com.stateinsurance.insurance.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;

    private String password;

    private String grant_type = "password";

    private String audience = "https://quickstarts/api";

    private String scope = "openid offline_access";

    private String client_id;

    private String client_secret;
}
