package com.revature.rest_template;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class App {

    public static void main(String[] args) {

        try {

            String apiUrl = "http://localhost:5000";
            RestTemplate restTemplate = new RestTemplate();

            Credentials validAdminCreds = new Credentials("wsingleton", "password");

            ResponseEntity<AppUser> response = restTemplate.postForEntity(apiUrl + "/auth", validAdminCreds, AppUser.class);
            String token = response.getHeaders().get("Authorization").get(0);
            System.out.println(token);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", token);
            HttpEntity requestConfig = new HttpEntity(httpHeaders);

            ResponseEntity<AppUser[]> usersResponse = restTemplate.exchange(apiUrl + "/users", HttpMethod.GET, requestConfig, AppUser[].class);
            System.out.println(usersResponse.getStatusCode());

            for (AppUser u : usersResponse.getBody()) {
                System.out.println(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
