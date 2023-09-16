package com.stateinsurance.insurance.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccessTokenDto {
    @NotBlank
    private String access_token;   
}
