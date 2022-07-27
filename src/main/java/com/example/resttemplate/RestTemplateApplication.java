package com.example.resttemplate;

import com.example.resttemplate.component.Connection;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTemplateApplication {

    public static void main(String[] args) {

        Connection.exchangeMethodsOfRestTemplate();

    }

}
