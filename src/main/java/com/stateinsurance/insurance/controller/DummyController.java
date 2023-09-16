package com.stateinsurance.insurance.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stateinsurance.insurance.Message;
import com.stateinsurance.insurance.dto.UserDto;

@RestController
// For simplicity of this sample, allow all origins. Real applications should configure CORS for their use case.
// @CrossOrigin(origins = "*")
public class DummyController {

    private static final Logger logger = LoggerFactory.getLogger(DummyController.class);

    @GetMapping(value = "/lovely")
    public Message signup() {
        return new Message("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope");
    }

    @PostMapping(value = "/api/booya")
    public Message hello(Message message) {
        logger.info("aalasd");
        return new Message("hello");
    }

    @GetMapping(value = "/api/testing")
    public Message keeprocking() {
         // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Define the URL for the POST request
        String url = "http://localhost:8080/api/booya"; // Replace with your actual URL

        // Create headers with content type application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Define your JSON request body as a string
        Message request_message = new Message("request"); // Replace with your JSON data

        // Create an HttpEntity with headers and the JSON body
        HttpEntity<Message> httpEntity = new HttpEntity<>(request_message, headers);

        // Make the POST request
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);

        // Get the response body
        String responseBody = responseEntity.getBody();

        // Log the response to the console
        System.out.println("Response Body: " + responseBody);
        return new Message("keep rocking");
    }
}
