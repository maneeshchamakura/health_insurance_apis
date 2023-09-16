package com.stateinsurance.insurance.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stateinsurance.insurance.ConfigService;
import com.stateinsurance.insurance.Helper;
import com.stateinsurance.insurance.Message;
import com.stateinsurance.insurance.RestTemplateHelper;
import com.stateinsurance.insurance.dto.AccessTokenDto;
import com.stateinsurance.insurance.dto.LoginDto;
import com.stateinsurance.insurance.dto.RefreshTokenDto;
import com.stateinsurance.insurance.dto.SignupDto;
import com.stateinsurance.insurance.dto.UserDto;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
// For simplicity of this sample, allow all origins. Real applications should configure CORS for their use case.
// @CrossOrigin(origins = "*")
public class APIController {

    @Autowired
    private ConfigService config;

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @GetMapping(value = "/public")
    public Message publicEndpoint() {
        return new Message("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private")
    public Message privateEndpoint() {
        return new Message("All good. You can see this because you are Authenticated.");
    }

    @GetMapping(value = "/private-scoped")
    public Message privateScopedEndpoint() {
        return new Message("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
    }

    @PostMapping(value = "/signup")
    public String signup(@Valid @RequestBody UserDto userDto) {
        logger.info(userDto.toString());
        SignupDto signupDto = new SignupDto();
        signupDto.setEmail(userDto.getEmail());
        signupDto.setPassword(userDto.getPassword());
        signupDto.setUsername(userDto.getUsername());
        signupDto.setClient_id(config.getClient_id());
        signupDto.setConnection(config.getConnection());
        System.out.println(signupDto);
        String response = RestTemplateHelper.sendPostRequest(config.getSignup_url(), signupDto);
        return response;
    }

    @PostMapping(value = "/login")
    public String login(@Valid @RequestBody LoginDto loginDto) {
        loginDto.setClient_id(config.getClient_id());
        loginDto.setClient_secret(config.getClient_secret());
        String url = config.getLogin_url();
        System.out.println(url);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("username", loginDto.getUsername());
        formData.add("password", loginDto.getPassword());
        formData.add("audience", "https://quickstarts/api");
        formData.add("scope", "openid offline_access");
        formData.add("client_id", loginDto.getClient_id());
        formData.add("client_secret", loginDto.getClient_secret());
        System.out.println(formData);
        String response = RestTemplateHelper.sendFormUrlEncodedPostRequest(url, formData);
        return response;
    }

    @PostMapping(value = "/refresh")
    public String refreshToken(@Valid @RequestBody RefreshTokenDto refreshToken) {
        String url = config.getRefresh_url();
        System.out.println(url);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("refresh_token", refreshToken.getRefresh_token());
        formData.add("client_id", config.getClient_id());
        formData.add("client_secret", config.getClient_secret());
        System.out.println(formData);
        String response = RestTemplateHelper.sendFormUrlEncodedPostRequest(url, formData);
        return response;
    }

    @GetMapping(value = "/userinfo")
    public String getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String url = config.getUserinfo_url();
        String bearerToken="";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the bearer token
            bearerToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
            // Now, you have the bearer token in the 'bearerToken' variable
            // return "Bearer Token: " + bearerToken;
        } else {
            // return "No Bearer Token found in Authorization header.";
        }
        return RestTemplateHelper.makeGetRequestWithBearerToken(url, bearerToken);
    }
}