package com.stateinsurance.insurance;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateHelper {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String sendPostRequest(String url, Object requestBody) {
        try {
            // Create headers with content type application/json
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Convert the request object to a JSON string
            String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

            // Create an HttpEntity with headers and the JSON body
            HttpEntity<String> httpEntity = new HttpEntity<>(jsonRequestBody, headers);

            // Make the POST request
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);

            // Get the response body
            String responseBody = responseEntity.getBody();

            // Log the response to the console
            System.out.println("Response Body: " + responseBody);

            return responseBody;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Handle JSON processing exception
            return null; // Or throw an exception
        }
    }

    public static String sendFormUrlEncodedPostRequest(String url, MultiValueMap<String, String> formData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(formData, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);

        String responseBody = responseEntity.getBody();

        System.out.println("Response Body: " + responseBody);

        return responseBody;
    }

    public static String makeGetRequestWithBearerToken(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Set the Bearer token in the headers

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Handle the error response as needed
            throw new RuntimeException("Request failed with status code: " + response.getStatusCode());
        }
    }
}
