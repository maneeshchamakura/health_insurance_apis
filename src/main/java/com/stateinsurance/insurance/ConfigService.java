package com.stateinsurance.insurance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class ConfigService {

    @Value("${myauth0.client_id}")
    private String client_id;

    @Value("${myauth0.client_secret}")
    private String client_secret;

    @Value("${myauth0.connection}")
    private String connection;

    @Value("${myauth0.signup_url}")
    private String signup_url;

    @Value("${myauth0.login_url}")
    private String login_url;

    @Value("${myauth0.refresh_url}")
    private String refresh_url;

    @Value("${myauth0.userinfo_url}")
    private String userinfo_url;
}
