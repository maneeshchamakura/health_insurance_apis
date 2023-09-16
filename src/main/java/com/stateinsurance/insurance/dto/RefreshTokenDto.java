package com.stateinsurance.insurance.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RefreshTokenDto {
    @NotBlank
    private String refresh_token;
}
