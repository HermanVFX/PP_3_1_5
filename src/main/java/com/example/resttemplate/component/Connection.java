package com.example.resttemplate.component;

import com.example.resttemplate.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class Connection {

    static RestTemplate restTemplate = new RestTemplate();
    static String URL = "http://94.198.50.185:7081/api/users";

    public static void exchangeMethodsOfRestTemplate() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List> responseEntity = getList(requestEntity);

        headers.set("Cookie", String.join(";", Objects.requireNonNull(responseEntity.getHeaders().get("Set-Cookie"))));

        User user = new User(3L, "James", "Brown", (byte) 18);

        requestEntity = new HttpEntity<>(user,headers);

        String add = add(requestEntity);

        user.setName("Thomas");
        user.setLastName("Shelby");

        String update = update(requestEntity);

        String delete = delete(requestEntity, user.getId());

        System.out.println("Code: " + add + update + delete);
    }

    private static String delete(HttpEntity<Object> requestEntity, Long id) {

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntity.getBody();

    }

    private static String update(HttpEntity<Object> requestEntity) {

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();

    }

    private static String add(HttpEntity<Object> requestEntity) {

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();

    }

    private static ResponseEntity<List> getList(HttpEntity<Object> requestEntity) {
        ResponseEntity<List> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class);
        return responseEntity;
    }
}
